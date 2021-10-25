package fdrc.client;

import com.fiserv.merchant.gmfv10.PymtTypeType;
import com.fiserv.merchant.gmfv10.ReversalIndType;
import fdrc.Exceptions.InvalidValueException;
import fdrc.base.IRequestProcessor;
import fdrc.base.Request;
import fdrc.base.Response;
import fdrc.common.ValidateRequest;
import fdrc.utils.JsonBuilder;
import fdrc.utils.Utils;

public class Client {
    // this is called by POS router service
    public String Call(String json) {
        Request request = JsonBuilder.getRequestFromJsonString(json);
        Response response = processRequest(request);
        return Utils.isNotNullOrEmpty (response.errorMsg) ? response.errorMsg :  response.responseRaw;
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.processRequest(null);
    }

    public Response processRequest(Request request) {
        Response resposne = null;
        String errorMessage = null;
        IRequestProcessor requestProcessor = null;
        //todo: temp code, to remove in prod: begin
        try {
            if (request == null)
                request = JsonBuilder.getRequestFromJson("debit-payload-1.json");
//            request = JsonBuilder.getRequestFromJson("debit-payload-200070100010.json");

            // todo: end
            if (request == null) return new Response("invalid payload.");
            errorMessage = new ValidateRequest().validate(request);
            if (errorMessage != "")
                return new Response(errorMessage);

            if (Utils.isNotNullOrEmpty(request.pymtType))
            switch (Utils.getEnumValue (PymtTypeType.class, request.pymtType)) {
                case CREDIT:
                    requestProcessor = new CreditRequest();
                    break;
                // todo:
                case DEBIT:
                    requestProcessor = new DebitRequest();
                    break;
                case EBT:
                    requestProcessor = new EBTRequest();
                    break;
                default:
                    throw new InvalidValueException(String.format("payment type: %s", request.pymtType));
            }
            if (Utils.isNotNullOrEmpty(request.reversalInd))
                switch (Utils.getEnumValue(ReversalIndType.class, request.reversalInd)) {
                    case VOID:
                        requestProcessor = new ReversalRequest();
                        break;
                    default:
                        throw new InvalidValueException(String.format("reversalInd: %s", request.reversalInd));
                }

            resposne = requestProcessor.processRequest(request);
        } catch (IllegalArgumentException | InvalidValueException e) {
            errorMessage = "Invalid Value: " + e.getMessage();
        } catch (Exception e) {
            errorMessage = "Error: " + e.getMessage();
        }
        requestProcessor = null; // finally deallocate
        if (Utils.isNotNullOrEmpty(errorMessage))
            if (resposne == null)
                resposne = new Response(errorMessage);
            else
                resposne.errorMsg = errorMessage;
        return resposne;
    }
}
