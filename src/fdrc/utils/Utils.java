package fdrc.utils;

import com.fiserv.merchant.gmfv10.ReversalIndType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fdrc.Exceptions.InvalidNumber;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
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
            T obj = (T) cls.getDeclaredConstructor().newInstance();
            Gson gson = new GsonBuilder().create();

            return gson.toJson(obj).equals(gson.toJson(tClass)) ? null : tClass;
            //            return obj.equals(tClass) ? null : tClass;
//             return Objects.equals(obj, tClass) ? null : tClass;
//             obj.hashCode() = tClass.hashCode();
//            tClass.getClass().getDeclaredFields();
//            tClass.getClass().getDeclaredMethod()
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
    public static <T extends Enum<T>> T getEnumValue(Class<T> type, String envVal) {
        return Enum.valueOf(type, envVal.toUpperCase());
    }
    public static void main(String[] args) {
        System.out.println(Utils.getEnumValue(ReversalIndType.class, "Timeout"));
    }
}
