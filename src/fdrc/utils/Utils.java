package fdrc.utils;

import fdrc.proxy.POSEntryModeChgType;

import java.time.Instant;
import java.time.LocalDate;

public class Utils {
    public String getPOSCondCode() {
        // Enum is not auto generated!! to check alternates
        return "";
    }

    public static String getLocalDateTime() {
        return LocalDate.now().toString().replaceAll("-", "")
                + java.time.LocalTime.now().toString().replaceAll(":", "").substring(0, 6);
    }

    public static String getUTCDateTime() {
        Instant nowUtc = Instant.now();
        return nowUtc.toString().substring(0, 10).replaceAll("-", "")
                + nowUtc.toString().substring(11, 19).replaceAll(":", "");
    }

    public static String getSTAN(){
        return java.time.LocalTime.now().toString().replaceAll(":", "").substring(0, 6);
    }

    public static String getOrderNum(){
        return java.time.LocalDate.now().toString().replaceAll("-", "").substring(4, 8) + java.time.LocalTime.now().toString().replaceAll(":", "").substring(0, 6);
    }



}
