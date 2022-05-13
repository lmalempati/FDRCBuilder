package fdrc.service;

import fdrc.common.Constants;
import fdrc.common.Serialization;
import fdrc.model.DatawireSRSActivationResponse;
import fdrc.model.DatawireSRSRegistrationResponse;
import fdrc.model.DatawireSRSRequest;
import fdrc.model.DatawireSRSResponse;
import fdrc.types.HttpMethod;
import fdrc.utils.Utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DatawireRegistrationService {
    Logger logger = Logger.getLogger(DatawireRegistrationService.class.getName());

    public DatawireSRSResponse doDatawireSRS(DatawireSRSRequest dw) {
        String DID = "";
        String xml = "";
        String discoveryResponseUrl = "";
        String response;
        DatawireSRSActivationResponse activationResponse;
        DatawireSRSRegistrationResponse regResp;
        DatawireSRSResponse srsResponse = new DatawireSRSResponse("");
        if (!Utils.isNotNullOrEmpty(new Object[]{dw.stagOrProd, dw.merchantId, dw.terminalId, dw.groupId, dw.tppId}))
            return new DatawireSRSResponse("All parameters must be provided.");
        if (dw.groupId.length() + dw.merchantId.length() > 32) {
            srsResponse.errorMsg = "COMBINED LENGTH OF GROUPID AND MID CANNOT EXCEED 32 CHARACTERS";
            return srsResponse;
        }
        if (dw.terminalId.length() > 8) {
            srsResponse.errorMsg = "TID CANNOT EXCEED 8 CHARACTERS";
            return srsResponse;
        }
        // validate terminal id.
        try {
            Integer.parseInt(dw.terminalId);
        } catch (Exception e) {
            srsResponse.errorMsg ="INVALID TERMINAL ID";
            return srsResponse;
        }
        // 1. check the discovery of the url
        String discResp = Utils.upload(dw.stagOrProd ? Constants.prodUrl : Constants.stgUrl, "", HttpMethod.GET);
        if (discResp.indexOf("<URL>") > 0) {
            discoveryResponseUrl = discResp.substring(discResp.indexOf("<URL>") + 5, discResp.indexOf("</URL>"));
        } else {
            srsResponse.errorMsg = "Service discovery failure.";
            return srsResponse;
        }

        // 2. register merchant using the discovery url
        xml = getRegistrationRequest(dw.merchantId, dw.terminalId, dw.groupId, dw.tppId);
        do {
            logger.log(Level.INFO, "Registration Request" + xml);
            response = Utils.upload(discoveryResponseUrl, xml, HttpMethod.POST);
            logger.log(Level.INFO, "Registration Response" + response);
            regResp = (DatawireSRSRegistrationResponse) Serialization.getObjectFromXML(DatawireSRSRegistrationResponse.class, response, false);
        } while (retryNeeded(regResp.Status.StatusCode));
        if (regResp.Status.StatusCode.equalsIgnoreCase("OK")) {
            DID = response.substring(response.lastIndexOf("<DID>") + 5, response.lastIndexOf("</DID>"));
//            DID = regResp.RegistrationResponse.DID;
            srsResponse.setDid(DID);
        } else {
             srsResponse.errorMsg = String.format("Registration request failed, statuscode: %s", regResp.Status.StatusCode);
             return srsResponse;
        }

        // 3. activate merchant with the received DID
        // example DID: "00041836971547330349")
        if (DID.equals("")){
            srsResponse.errorMsg = "Empty DID";
            return srsResponse;
        }
        xml = getActivationRequest(dw.merchantId, dw.terminalId, dw.groupId, dw.tppId, DID);
        do {
            logger.log(Level.INFO, "Activation Request " + xml);
            response = Utils.upload(discoveryResponseUrl, xml, HttpMethod.POST);
            logger.log(Level.INFO, "Activation Response" + response);
            activationResponse = (DatawireSRSActivationResponse) Serialization.getObjectFromXML(DatawireSRSActivationResponse.class, response, false);
        } while (retryNeeded(activationResponse.Status.StatusCode));
        if (activationResponse.Status.StatusCode.equalsIgnoreCase("OK")) {
            srsResponse.setStatus(activationResponse.Status.StatusCode);
            return  srsResponse;
        } else {
            srsResponse.errorMsg = String.format("DID empty, Activation request failed, statuscode: %s", activationResponse.Status.StatusCode);
            return srsResponse;
        }
    }

    private boolean retryNeeded(String StatusCode) {
        if (Utils.containsInArray(Constants.RetryCodes, StatusCode)) {
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        }
        return false;
    }

    private String getRegistrationRequest(String merchantId, String terminalId, String groupId, String tppId) {
        String regReq = Constants.REGISTRATION_TEMPLATE;
        return regReq.replace("{groupId}", groupId)
                .replace("{merchantId}", merchantId)
                .replace("{terminalId}", String.format("%8S", terminalId).replaceAll(" ", "0"))
                .replace("{clientRef}", Utils.getClientRef(tppId));
    }

    public static String getActivationRequest(String merchantId, String terminalId, String groupId, String tppId, String datawireId) {
        if (datawireId == null)
            datawireId = "";
        String activationTemplate = Constants.ACTIVATION_TEMPLATE;
        return activationTemplate = activationTemplate.replace("{datawireId}", datawireId)
                .replace("{groupId}", groupId)
                .replace("{merchantId}", merchantId)
                .replace("{terminalId}", String.format("%8S", terminalId).replaceAll(" ", "0"))
                .replace("{tppId}", tppId)
                .replace("{clientRef}", Utils.getClientRef(tppId));
    }
}