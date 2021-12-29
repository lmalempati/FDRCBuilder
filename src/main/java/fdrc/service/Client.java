package fdrc.service;

import com.fiserv.merchant.gmfv10.PymtTypeType;
import com.fiserv.merchant.gmfv10.ReversalIndType;
import com.fiserv.merchant.gmfv10.TxnTypeType;
import com.google.gson.JsonSyntaxException;
import fdrc.Exceptions.UnsupportedEnumValueException;
import fdrc.model.RCResponse;
import fdrc.model.RCRequest;
import fdrc.utils.JsonBuilder;
import fdrc.utils.Utils;

public class Client {
    /**
     * this is called by POS router service
     */
    public String Call(String json) {
        RCRequest RCRequest = null;
        try {
            RCRequest = JsonBuilder.getRequestFromJsonString(json);
        } catch (JsonSyntaxException e) {
            RCResponse RCResponse = new RCResponse("Invalid Json: " + e.getMessage());
            return RCResponse.errorMsg;
        }
        RCResponse RCResponse = submitRequest(RCRequest);
        return Utils.isNotNullOrEmpty(RCResponse.errorMsg) ? RCResponse.errorMsg : RCResponse.responseRaw;
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.submitRequest(null);
    }

    public RCResponse submitRequest(RCRequest RCRequest) {
        RCResponse resposne = null;
        String errorMessage = "";
        GenericService requestProcessor = null;
        //todo: temp code, to remove in prod: begin
        try {
            if (RCRequest == null) RCRequest = JsonBuilder.getRequestFromJson("payload.json");
            // todo: end
            if (RCRequest == null) return new RCResponse("invalid payload.");
//            errorMessage = Utils.validate(request);
            if (errorMessage != "") return new RCResponse(errorMessage);

            requestProcessor = getCreditDebitEBTService(RCRequest, requestProcessor);
            requestProcessor = getReversalService(RCRequest, requestProcessor);

            if (Utils.toEnum(TxnTypeType.class, RCRequest.txnType) == TxnTypeType.HOST_TOTALS)
                requestProcessor = new AdminService();
            if (requestProcessor != null)
                resposne = requestProcessor.processRequest(RCRequest);
        } catch (IllegalArgumentException | UnsupportedEnumValueException e) {
            errorMessage = e.getMessage();
        } catch (Exception e) {
            errorMessage = "Error: " + e.getMessage();
//            Logger logger = Logger.getLogger("FDRCLogger");
//            logger.logp(Level.SEVERE, e.getStackTrace().getClass().getName(), "", e.getMessage());

        }
        requestProcessor = null; // finally deallocate
        if (Utils.isNotNullOrEmpty(errorMessage)) if (resposne != null) {
            resposne.errorMsg = errorMessage;
        } else {
            resposne = new RCResponse(errorMessage);
        }
        return resposne;
    }

    private GenericService getCreditDebitEBTService(RCRequest RCRequest, GenericService requestProcessor) {
        if (Utils.isNotNullOrEmpty(RCRequest.pymtType))
            switch (Utils.toEnum(PymtTypeType.class, RCRequest.pymtType)) {
                case CREDIT:
                    switch (Utils.toEnum(TxnTypeType.class, RCRequest.txnType)) {
                        case TA_TOKEN_REQUEST:
                            requestProcessor = new TransArmorService();
                            break;
                        default:
                            requestProcessor = new CreditService();
                    }
                    break;
                case DEBIT:
                    switch (Utils.toEnum(TxnTypeType.class, RCRequest.txnType)) {
                        case TA_TOKEN_REQUEST:
                            requestProcessor = new TransArmorService();
                            break;
                        default:
                            requestProcessor = new DebitService();
                    }
                    break;
                case EBT:
                    requestProcessor = new EBTService();
                    break;
                default:
                    throw new UnsupportedEnumValueException(String.format("payment type %s", RCRequest.pymtType));
            }
        return requestProcessor;
    }

    private GenericService getReversalService(RCRequest RCRequest, GenericService requestProcessor) {
        if (Utils.isNotNullOrEmpty(RCRequest.reversalInd))
            switch (Utils.toEnum(ReversalIndType.class, RCRequest.reversalInd)) {
                case VOID:
                case PARTIAL:
                case TIMEOUT:
                    requestProcessor = new ReversalService();
                    break;
                default:
                    throw new UnsupportedEnumValueException(String.format("reversalInd %s", RCRequest.reversalInd));
            }
        return requestProcessor;
    }
}
