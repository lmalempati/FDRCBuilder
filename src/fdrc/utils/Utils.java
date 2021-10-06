package fdrc.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fdrc.Exceptions.InvalidNumber;
import fdrc.base.Response;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Utils {
    public String getPOSCondCode() {
        // Enum is not auto generated!! to check alternatives
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

    public static String getSTAN() {
        return java.time.LocalTime.now().toString().replaceAll(":", "").substring(0, 6);
    }

    public static String getOrderNum() {
        return java.time.LocalDate.now().toString().replaceAll("-", "").substring(4, 8) + java.time.LocalTime.now().toString().replaceAll(":", "").substring(0, 6);
    }

    public static boolean isNotNull(Object o) {
        return o != null ? true : false;
    }

    public static boolean isNotNullOrEmpty(Object s) {
        // string utls apache common lang library
        // return StringUtils.isNotBlank(o);
        return (s != null && s.toString().trim() != String.valueOf("")) ? true : false;
    }

    public static String formatAmount(String amount) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        if (amount == null || !pattern.matcher(amount.toString()).matches()) {
            throw new InvalidNumber("Invalid amount.");
        }
        return String.format("0" + "%.0f", new BigDecimal(amount).multiply(new BigDecimal(100)));
    }

    public static <T> T valueOrNothing(T tClass) {
        String className = tClass.getClass().getName();
        try {
            Class cls = Class.forName(className);
//            tClass.getClass().getDeclaredFields()
            T obj = (T) cls.getDeclaredConstructor().newInstance();
//            ObjectUtils.notEqual(obj, tClass);
            Gson gson = new GsonBuilder().create();
//            String s1 =gson.toJson(tClass);
//            String s2 =gson.toJson(obj);

            return gson.toJson(obj).equals(gson.toJson(tClass)) ? null : tClass;
            //            return obj.equals(tClass) ? null : tClass;
//             return Objects.equals(obj, tClass) ? null : tClass;
//             obj.hashCode() = tClass.hashCode();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return tClass;
    }


    public static void main(String[] args) {
//        String s = null;
//        String s1 = "";
//        Object o = null;
//        Object o1 = new Object();
//        String s2 = "abc";
//
//        System.out.println(isNotNullOrEmpty(s));
//        System.out.println(isNotNullOrEmpty(s1));
//        System.out.println(isNotNullOrEmpty(s2));
//        System.out.println(isNotNull(o));
//        System.out.println(isNotNull(o1));
        System.out.println(formatAmount("12.32"));
        System.out.println(formatAmount("1232"));
        System.out.println(formatAmount("12.00"));
        System.out.println(formatAmount("12.0"));
        System.out.println(formatAmount("00.00"));
        System.out.println(formatAmount("adsds"));
        System.out.println(formatAmount(null));
    }


}
