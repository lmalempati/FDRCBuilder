package test;

import fdrc.service.Client;
import fdrc.service.DatawireRegistrationService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatawireRegistrationServiceTest {

    @Test
    void doDatawireSRS() {
        Client client = new Client();
        String result = "";
//        result = client.submitDatawireSRS(false, "RCTST1000092852", "3", "10001", "RSU005");
//        assertEquals("AuthenticationError", result.split(";")[0]);

        result = client.submitDatawireSRS(false, "RCTST1000092864", "3", "10001", "RSU005");
        assertEquals("All parameters must be provided.", result.split(";")[0]);

    }
}