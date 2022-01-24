package fdrc.service;

import com.google.gson.JsonSyntaxException;
import fdrc.Exceptions.InvalidRequest;
import fdrc.Exceptions.UnsupportedValueException;
import fdrc.model.RCRequest;
import fdrc.model.RCResponse;
import fdrc.utils.JsonBuilder;
import fdrc.utils.Utils;

public class Client {
    /**
     * todo: this is not required in prod
     */
    public static void main(String[] args) {
        RCRequest rcRequest = null;
        new Client().submitRequest(rcRequest);
    }

    public String Call(String json) {
        RCRequest RCRequest = null;
        try {
            RCRequest = JsonBuilder.getRequestFromJsonString(json);
        } catch (JsonSyntaxException e) {
            RCResponse RCResponse = new RCResponse("Invalid Json: " + e.getMessage());
            return RCResponse.errorMsg;
        }
        RCResponse RCResponse = submitRequest(RCRequest);
        return Utils.isNotNullOrEmpty(RCResponse.errorMsg) ? RCResponse.errorMsg : RCResponse.responsePayload;
    }

    public RCResponse submitRequest(RCRequest rcRequest) {
        RCResponse rcResponse = null;
        String errorMessage = "";
        BaseService baseService;
        try {
            //todo: temp code, to remove in prod: begin
            //if (rcRequest == null) rcRequest = JsonBuilder.getRequestFromJson("payload.json");
            //todo: temp code, to remove in prod: end
            baseService = new ServiceUtil().getGMFServiceFromRequest(rcRequest);
            if (baseService != null)
                rcResponse = baseService.processRequest(rcRequest);
            else
                return new RCResponse("Unable to submit request for the given request data.");
        } catch (IllegalArgumentException | UnsupportedValueException | InvalidRequest e) {
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
    public String submitDatawireSRS(boolean stagOrProd, String merchantId, String terminalId, String groupId, String tppId) {
        try {
            DatawireRegistrationService datawireRegistrationService = new DatawireRegistrationService();
            return datawireRegistrationService.doDatawireSRS(stagOrProd, merchantId, terminalId, groupId, tppId);
        } catch (Exception e) {
            return "DatawireSRS failure: " + e.getMessage();
        }
    }
}