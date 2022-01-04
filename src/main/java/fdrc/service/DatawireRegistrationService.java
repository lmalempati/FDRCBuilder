package fdrc.service;

import fdrc.common.Constants;
import fdrc.common.Serialization;
import fdrc.model.ActivationResponse;
import fdrc.model.RegistrationResponse;
import fdrc.utils.RequestUtils;
import fdrc.utils.Utils;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatawireRegistrationService {
    Logger logger = Logger.getLogger(DatawireRegistrationService.class.getName());
    public String doDatawireSRS(boolean stagOrProd, String merchantId, String terminalId, String groupId, String tppId) {
        String DID;
        if (!Utils.isNotNullOrEmpty(new Object[]{stagOrProd, merchantId, terminalId, groupId, tppId}))
            return "All parameters must be provided.";

        // check the discovery of the url
        String discResp = Utils.upload(stagOrProd ? Constants.stgUrl : Constants.prodUrl, "");
        String discResponseUrl = discResp.substring(discResp.indexOf("<URL>") + 5, discResp.indexOf("</URL>"));
        // register merchant using the discovery url
        String xml = getRegistrationRequest(merchantId, terminalId, groupId, tppId);
        String response = Utils.upload(discResponseUrl, xml);
        RegistrationResponse regresp = (RegistrationResponse) Serialization.getObjectXML(RegistrationResponse.class, response);
        logger.log(Level.INFO, "Registration Response" + response);
        if (regresp.status.StatusCode.equalsIgnoreCase("OK")) {
            DID = response.substring(response.lastIndexOf("<DID>") + 5, response.lastIndexOf("</DID>"));
        } else {
            return "Registration request failed.;" + regresp.status.StatusCode;
        }

        // SRS activation
        // note: check if 'DID' exists - (something like this: "00041836971547330349")
        if (DID != "") {
            xml = getActivationRequest(merchantId, terminalId, groupId, tppId, DID);
            response = Utils.upload(discResponseUrl, xml);
            logger.log(Level.INFO, "Activation Response" + response);
            ActivationResponse activationResponse = (ActivationResponse) Serialization.getObjectXML(ActivationResponse.class, response);
            if (activationResponse.status.StatusCode.equalsIgnoreCase("OK")) {
                return "Success;" + DID;
            } else
                return activationResponse.status.StatusCode;
        } else {
            return "DID empty, Activation request failed.;" + regresp.status.StatusCode;
        }
    }
    public static String getRegistrationRequest(String merchantId, String terminalId, String groupId, String tppId) {
        String regReq = Constants.REGISTRATION_TEMPLATE;
        return regReq.replace("{groupId}", groupId)
                .replace("{merchantId}", merchantId)
                .replace("{terminalId}", "0000000" + terminalId)
                .replace("{clientRef}", RequestUtils.getClientRef() + tppId);
    }
    private static String getActivationRequest(String merchantId, String terminalId, String groupId, String tppId, String datawireId) {
        if (datawireId == null)
            datawireId = "";
        String activationTemplate = Constants.ACTIVATION_TEMPLATE;
        return activationTemplate = activationTemplate.replace("{datawireId}", datawireId)
                .replace("{groupId}", groupId)
                .replace("{merchantId}", merchantId)
                .replace("{terminalId}", "0000000" + terminalId)
                .replace("{tppId}", tppId)
                .replace("{clientRef}", RequestUtils.getClientRef() + tppId);
    }
}