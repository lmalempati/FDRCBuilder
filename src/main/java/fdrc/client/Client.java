package fdrc.client;

import com.fiserv.merchant.gmfv10.PymtTypeType;
import com.fiserv.merchant.gmfv10.ReversalIndType;
import com.fiserv.merchant.gmfv10.TxnTypeType;
import com.google.gson.JsonSyntaxException;
import fdrc.Exceptions.UnsupportedEnumValueException;
import fdrc.base.Request;
import fdrc.base.Response;
import fdrc.utils.JsonBuilder;
import fdrc.utils.Utils;

public class Client {
    // this is called by POS router service
    public String Call(String json) {
        Request request = null;
        try {
            request = JsonBuilder.getRequestFromJsonString(json);
        } catch (JsonSyntaxException e) {
            Response response = new Response("Invalid Json: " + e.getMessage());
            return response.errorMsg;
        }
        Response response = submitRequest(request);
        return Utils.isNotNullOrEmpty(response.errorMsg) ? response.errorMsg : response.responseRaw;
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.submitRequest(null);
    }

    public Response submitRequest(Request request) {
        Response resposne = null;
        String errorMessage = "";
        GenericRequest requestProcessor = null;
        //todo: temp code, to remove in prod: begin
        try {
            if (request == null)
                request = JsonBuilder.getRequestFromJson("payload.json");
            // todo: end
            if (request == null)
                return new Response("invalid payload.");
            errorMessage = Utils.validate(request);
            if (errorMessage != "")
                return new Response(errorMessage);

            if (Utils.isNotNullOrEmpty(request.pymtType))
                switch (Utils.toEnum(PymtTypeType.class, request.pymtType)) {
                    case CREDIT:
                        switch (Utils.toEnum(TxnTypeType.class, request.txnType)) {
                            case TA_TOKEN_REQUEST:
                                requestProcessor = new TransArmorRequest();
                                break;
                            default:
                                requestProcessor = new CreditRequest();
                        }
                        break;
                    // todo:
                    case DEBIT:
                        switch (Utils.toEnum(TxnTypeType.class, request.txnType)) {
                            case TA_TOKEN_REQUEST:
                                requestProcessor = new TransArmorRequest();
                                break;
                            default:
                                requestProcessor = new DebitRequest();
                        }
                        break;
                    case EBT:
                        requestProcessor = new EBTRequest();
                        break;
                    default:
                        throw new UnsupportedEnumValueException(String.format("payment type %s", request.pymtType));
                }
            if (Utils.isNotNullOrEmpty(request.reversalInd))
                switch (Utils.toEnum(ReversalIndType.class, request.reversalInd)) {
                    case VOID:
                    case PARTIAL:
                        requestProcessor = new ReversalRequest();
                        break;

                    default:
                        throw new UnsupportedEnumValueException(String.format("reversalInd %s", request.reversalInd));
                }

            resposne = requestProcessor.processRequest(request);
        } catch (IllegalArgumentException | UnsupportedEnumValueException e) {
            errorMessage = e.getMessage();
        } catch (Exception e) {
            errorMessage = "Error: " + e.getMessage();
        }
        requestProcessor = null; // finally deallocate
        if (Utils.isNotNullOrEmpty(errorMessage))
            if (resposne != null) {
                resposne.errorMsg = errorMessage;
            } else {
                resposne = new Response(errorMessage);
            }
        return resposne;
    }
}
