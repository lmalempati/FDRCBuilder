package fdrc.service;

import com.fiserv.merchant.gmfv10.PymtTypeType;
import com.fiserv.merchant.gmfv10.ReversalIndType;
import com.fiserv.merchant.gmfv10.TxnTypeType;
import com.google.gson.JsonSyntaxException;
import fdrc.Exceptions.UnsupportedEnumValueException;
import fdrc.model.RCRequest;
import fdrc.model.RCResponse;
import fdrc.utils.JsonBuilder;
import fdrc.utils.Utils;

import java.security.GeneralSecurityException;

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

    public RCResponse submitRequest(RCRequest rcRequest) {
        RCResponse rcResponse = null;
        String errorMessage = "";
        GenericService requestProcessor = null;
        try {
            //todo: temp code, to remove in prod: begin
            if (rcRequest == null) rcRequest = JsonBuilder.getRequestFromJson("payload.json");
            //todo: temp code, to remove in prod: end
            if (rcRequest == null) return new RCResponse("invalid payload.");
            errorMessage = Utils.validate(rcRequest);
            if (errorMessage != "") return new RCResponse(errorMessage);

            requestProcessor = getCreditDebitEBTService(rcRequest, requestProcessor);
            requestProcessor = getReversalService(rcRequest, requestProcessor);

            if (Utils.toEnum(TxnTypeType.class, rcRequest.txnType) == TxnTypeType.HOST_TOTALS)
                requestProcessor = new AdminService();
            if (requestProcessor != null)
                rcResponse = requestProcessor.processRequest(rcRequest);
            else
                return new RCResponse("Unable to for request for the given request data.");
        } catch (IllegalArgumentException | UnsupportedEnumValueException e) {
            errorMessage = e.getMessage();
        } catch (Exception e) {
            errorMessage = "Error: " + e.getMessage();
        }
        if (Utils.isNotNullOrEmpty(errorMessage)) if (rcResponse != null) {
            rcResponse.errorMsg = errorMessage;
        } else {
            rcResponse = new RCResponse(errorMessage);
        }
        return rcResponse;
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
