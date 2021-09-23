package fdrc.test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;

public class test {

    public static void main(String[] args) {
        Instant nowUtc = Instant.now();
        String s = nowUtc.toString().substring(0, 10).replaceAll("-", "") + nowUtc.toString().substring(11, 19).replaceAll(":", "");
        Date nowLcl = new Date();
        System.out.println(nowUtc);
        System.out.println(nowLcl);
        System.out.println(s);
    }
}
