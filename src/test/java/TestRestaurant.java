import fdrc.base.Response;
import fdrc.client.Client;
import fdrc.utils.JsonBuilder;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;
//@RunWith(Parameterized.class)
public class TestRestaurant {
    public static final String PATH = "src/test/resources/Restaurant/";
    
    @Test // Bal.Inquiry
    public void visaBalanceInquirySaleSwipe() {
        Response response = new Client().processRequest(JsonBuilder.getRequestFromJson( PATH + "BalInq-VisaSaleSwipe-200066500010.json"));

        assertEquals("000", response.respCode);
    }

    @Test // auth & completion
    public void creditVisaAuthKeyed201012850010() {
        Response response = new Client().processRequest(JsonBuilder.getRequestFromJson(PATH + "Credit-Visa-Auth-keyed-201012850010.json"));
        assertEquals("000", response.respCode);
//        updateCompletionPayload(response, "payloads/Credit-Visa-AuthComple-swipe-200019960021.json");
        if (JsonBuilder.updateCompletionPayload(response, PATH + "Credit-Visa-Compl-keyed-201012850011.json"))
            creditVisaAuthCompletionKeyed100070290011();
    }

    public void creditVisaAuthCompletionKeyed100070290011() {
        Response response = new Client().processRequest(JsonBuilder.getRequestFromJson(PATH + "Credit-Visa-Compl-keyed-201012850011.json"));
        assertEquals("000", response.respCode);
    }


    @Test // refund
    public void creditMCRefundKeyedin100071710010() {
        Response response = new Client().processRequest(JsonBuilder.getRequestFromJson(PATH + "Credit-Visa-Refund-Keyedin-200070140010.json"));
        assertEquals("000", response.respCode);
    }

}
