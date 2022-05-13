package test;

import fdrc.common.Serialization;
import fdrc.model.DatawireSRSActivationResponse;
import fdrc.model.DatawireSRSRegistrationResponse;
import fdrc.model.DatawireSRSRequest;
import fdrc.model.DatawireSRSResponse;
import fdrc.service.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DatawireRegistrationServiceTest {

    @Test
    public void doDatawireSRS() {
        Client client = new Client();
        DatawireSRSResponse srsResponse;
//        result = client.submitDatawireSRS(false, "", "3", "10001", "RSU005");
//        assertEquals("All parameters must be provided.", result.split(";")[0]);
        // Already on boarded mid
        srsResponse = client.submitDatawireSRS( new DatawireSRSRequest(false, "RCTST1000094637", "3", "10001", "RSU005"));
        // "Registration request failed, status: Merchant Already Provisioned and statuscode: AccessDenied"
        boolean found = srsResponse.errorMsg.indexOf("Merchant Already Provisioned") >= 0;
        boolean found1 = srsResponse.errorMsg.indexOf("AccessDenied") >= 0;
        assertEquals(found || found1, true);
    }

    @Test
    public void testRegistrationRespSerilization(){
        String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE Response PUBLIC \"-//Datawire Communication Networks INC//DTD VXN API Self-Registration 3.0//EN\" \"http://www.datawire.net/xmldtd/srs.dtd\"><Response Version=\"3\"><RespClientID><DID>00043816825221507852</DID><ClientRef>0164704VRSU005</ClientRef></RespClientID><Status StatusCode=\"OK\"/><RegistrationResponse><DID>00043816825221507852</DID><URL>https://stg.dw.us.fdcnet.biz/rc</URL><URL>https://stg.dw.us.fdcnet.biz/rc</URL></RegistrationResponse></Response>\n" +
                "\n";
        DatawireSRSRegistrationResponse regResp = (DatawireSRSRegistrationResponse) Serialization.getObjectFromXML(DatawireSRSRegistrationResponse.class, response, false);
        assertEquals("00043816825221507852", regResp.RegistrationResponse.DID);
        assertEquals("OK", regResp.Status.StatusCode);
    }

    @Test
    public void testactivationRespSerilization() {
        String actResp = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE Response PUBLIC \"-//Datawire Communication Networks INC//DTD VXN API Self-Registration 3.0//EN\" \"http://www.datawire.net/xmldtd/srs.dtd\"><Response Version=\"3\"><RespClientID><DID/><ClientRef>935406V1RSU005</ClientRef></RespClientID><Status StatusCode=\"OK\"/><ActivationResponse/></Response>";

        DatawireSRSActivationResponse datawireSRSActivationResponse = (DatawireSRSActivationResponse) Serialization.getObjectFromXML(DatawireSRSActivationResponse.class, actResp, false);
        assertEquals("935406V1RSU005", datawireSRSActivationResponse.RespClientID.ClientRef);
        assertEquals("OK", datawireSRSActivationResponse.Status.StatusCode);

    }
}