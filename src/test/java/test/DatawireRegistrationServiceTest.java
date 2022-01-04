package test;

import fdrc.service.DatawireRegistrationService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatawireRegistrationServiceTest {

    @Test
    void doDatawireSRS() {
        DatawireRegistrationService datawireRegistrationService = new DatawireRegistrationService();
        String result = "";
        result = datawireRegistrationService.doDatawireSRS(false, "RCTST1000092852", "3", "10001", "RSU005");
        assertEquals("AuthenticationError", result.split(";")[0]);

        result = datawireRegistrationService.doDatawireSRS(false, "", "3", "10001", "RSU005");
        assertEquals("All parameters must be provided.", result.split(";")[0]);
    }
}