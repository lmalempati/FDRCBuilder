package test;

import fdrc.service.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DatawireRegistrationServiceTest {

    @Test
    public void doDatawireSRS() {
        Client client = new Client();
        String result = "";
//        result = client.submitDatawireSRS(false, "", "3", "10001", "RSU005");
//        assertEquals("All parameters must be provided.", result.split(";")[0]);
        // Already on boarded mid
        result = client.submitDatawireSRS(false, "RCTST1000094637", "00003", "10001", "RSU005");
        // "Registration request failed, status: Merchant Already Provisioned and statuscode: AccessDenied"
        boolean found = result.indexOf("Merchant Already Provisioned") >= 0;
        assertEquals(found, true);
    }
}