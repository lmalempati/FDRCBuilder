package test;

import fdrc.base.Request;
import fdrc.base.Response;
import fdrc.client.Client;
import fdrc.utils.JsonBuilder;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

public class TestsMoto {
    @Test
    public void testVerification(){
         Response response =  new Client().ProcessRequest(JsonBuilder.GetRequestFromJson("payloads/Moto-Verification-NoEncr-4012000000000529.json"));
         assertEquals("785", response.respCode);
    }

    @Test
    public void visaSaleSwipe(){
        Response response =  new Client().ProcessRequest(JsonBuilder.GetRequestFromJson("payloads/Moto-VisaSaleSwipe.json"));
        assertEquals("000", response.respCode);
    }

    @Test
    public void creditVisaAuthKeyed(){
        Response response =  new Client().ProcessRequest(JsonBuilder.GetRequestFromJson("payloads/Credit-Visa-Auth-keyed.json"));
        assertEquals("000", response.respCode);
    }

    @Test
    public void creditVisaAuthCompletionKeyed(){
        Response response =  new Client().ProcessRequest(JsonBuilder.GetRequestFromJson("payloads/Credit-Visa-AuthComple-keyed.json"));
        assertEquals("000", response.respCode);
    }
    // Todo: explore to extract respone details off teh response xml, rather than by deserializing it?
    public void runAllTests(){
        File filePath = new File(".\\payloads");
        File[] files = filePath.listFiles();

        for (File file: files
             ) {
            Request req = getPayload(file.getPath());
            Response response = new Client().ProcessRequest(req);
            assertEquals("", response.errorMsg);
        }
    }
    public Request getPayload(String fileName){
            return JsonBuilder.getRequestFromJsonString(fileName);
    }

}
