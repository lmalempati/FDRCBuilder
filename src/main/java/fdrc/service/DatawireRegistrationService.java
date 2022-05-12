package fdrc.service;

import fdrc.common.Constants;
import fdrc.types.HttpMethod;
import fdrc.common.Serialization;
import fdrc.model.DatawireSRSActivationResponse;
import fdrc.model.DatawireSRSRegistrationResponse;
import fdrc.utils.Utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DatawireRegistrationService {
    Logger logger = Logger.getLogger(DatawireRegistrationService.class.getName());

    public String doDatawireSRS(boolean stagOrProd, String merchantId, String terminalId, String groupId, String tppId) {
        String DID;
        String response;
        DatawireSRSActivationResponse activationResponse;
        DatawireSRSRegistrationResponse regResp;
        if (!Utils.isNotNullOrEmpty(new Object[]{stagOrProd, merchantId, terminalId, groupId, tppId}))
            return "All parameters must be provided.";
        if (groupId.length() + merchantId.length() > 32)
            return "COMBINED LENGTH OF GROUPID AND MID CANNOT EXCEED 32 CHARACTERS";
        if (terminalId.length() > 8)
            return "TID CANNOT EXCEED 8 CHARACTERS";
        // validate terminal id.
        try {
            Integer.parseInt(terminalId);
        } catch (Exception e) {
            return "INVALID TERMINAL ID";
        }
        // check the discovery of the url
        String discResp = Utils.upload(stagOrProd ? Constants.prodUrl : Constants.stgUrl, "", HttpMethod.GET);
        String discoveryResponseUrl;
        if (discResp.indexOf("<URL>") > 0) {
            discoveryResponseUrl = discResp.substring(discResp.indexOf("<URL>") + 5, discResp.indexOf("</URL>"));
        }
        else
            return "Service discovery failure.";

        // register merchant using the discovery url
        String xml = getRegistrationRequest(merchantId, terminalId, groupId, tppId);
        do {
            response = Utils.upload(discoveryResponseUrl, xml, HttpMethod.POST);
            logger.log(Level.INFO, "Registration Response" + response);
            regResp = (DatawireSRSRegistrationResponse) Serialization.getObjectFromXML(DatawireSRSRegistrationResponse.class, response, false);
        }while (retryNeeded(regResp.Status.StatusCode));
        if (regResp.Status.StatusCode.equalsIgnoreCase("OK")) {
            DID = response.substring(response.lastIndexOf("<DID>") + 5, response.lastIndexOf("</DID>"));
        } else {
            return String.format("Registration request failed, status: %s and statuscode: %s", regResp.Status.StatusCode, regResp.Status.StatusCode);
        }

        // SRS activation
        // example DID: "00041836971547330349")
         xml = getActivationRequest("RCTST1000096442", "00000003", "10001", "RSU005", "00043816825221507852");
        do {
            response = Utils.upload("https://stg.dw.us.fdcnet.biz/rc", xml, HttpMethod.POST);
            activationResponse = (DatawireSRSActivationResponse) Serialization.getObjectFromXML(DatawireSRSActivationResponse.class, response, false);
        } while (retryNeeded(activationResponse.Status.StatusCode));
        logger.log(Level.INFO, "Activation Response" + response);
        if (activationResponse.Status.StatusCode.equalsIgnoreCase("OK")) {
            return "Success;" + "DID";
        } else
            return activationResponse.Status.StatusCode;
//        } else {
//            return String.format("DID empty, Activation request failed, status: %s and statuscode: %s", regResp.status.Status, regResp.status.StatusCode);
//        }
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

    public static void main(String[] args) {
        String xml = getActivationRequest("RCTST1000096442", "00000003", "10001", "RSU005", "00043816825221507852");
        String resp = Utils.upload("https://stg.dw.us.fdcnet.biz/rc", xml, HttpMethod.POST);


        String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE Response PUBLIC \"-//Datawire Communication Networks INC//DTD VXN API Self-Registration 3.0//EN\" \"http://www.datawire.net/xmldtd/srs.dtd\"><Response Version=\"3\"><RespClientID><DID>00043816825221507852</DID><ClientRef>0164704VRSU005</ClientRef></RespClientID><Status StatusCode=\"OK\"/><RegistrationResponse><DID>00043816825221507852</DID><URL>https://stg.dw.us.fdcnet.biz/rc</URL><URL>https://stg.dw.us.fdcnet.biz/rc</URL></RegistrationResponse></Response>\n" +
                "\n";
        String actResp = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE Response PUBLIC \"-//Datawire Communication Networks INC//DTD VXN API Self-Registration 3.0//EN\" \"http://www.datawire.net/xmldtd/srs.dtd\"><Response Version=\"3\"><RespClientID><DID/><ClientRef>935406V1RSU005</ClientRef></RespClientID><Status StatusCode=\"OK\"/><ActivationResponse/></Response>";

        DatawireSRSRegistrationResponse regResp = (DatawireSRSRegistrationResponse) Serialization.getObjectFromXML(DatawireSRSRegistrationResponse.class, response, false);

        DatawireSRSRegistrationResponse regResp1 = (DatawireSRSRegistrationResponse) Serialization.getObjectFromXML(DatawireSRSRegistrationResponse.class, actResp, false);
    }
}