import fdrc.base.Response;
import fdrc.client.Client;
import fdrc.utils.JsonBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestRetailOrRestaurant {
    public static final String PATH = "src/test/resources/RetailOrRestt/";

    // Todo: use parameterized testing.

    @Test
    public void creditVisaAuthKeyed() {
        Response response = new Client().processRequest(JsonBuilder.getRequestFromJson("payloads/RetailOrRestt/Credit-Visa-Auth-swipe-200019960020.json"));
        assertEquals("000", response.respCode);
//        updateCompletionPayload(response, "payloads/Credit-Visa-AuthComple-swipe-200019960021.json");
        if (JsonBuilder.updateCompletionPayload(response,  PATH + "Credit-Visa-AuthComple-swipe-200019960021.json")){
            creditVisaAuthCompletionKeyed();
        }
    }
    @Test
    public void creditVisaAuthCompletionKeyed() {
        Response response = new Client().processRequest(JsonBuilder.getRequestFromJson(PATH + "Credit-Visa-AuthComple-swipe-200019960021.json"));
        assertEquals("000", response.respCode);
    }

    @Test // Verification
    public void testVerification() {
        Response response = new Client().processRequest(JsonBuilder.getRequestFromJson(PATH + "Moto-Verification-NoEncr-4012000000000529.json"));
        assertEquals("000", response.respCode);
    }



}
