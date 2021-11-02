package fdrc.utils;

import com.fiserv.merchant.gmfv10.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fdrc.Exceptions.InvalidNumber;
import fdrc.Exceptions.UnsupportedEnumValueException;
import fdrc.base.Request;
import fdrc.common.RequestUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    public static boolean isNotNullOrEmpty(Object s) {
        try {
            return s != null && !s.toString().trim().equals("");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String formatAmount(String amount) {
        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
        if (amount == null || !pattern.matcher(amount).matches()) {
            throw new InvalidNumber("Invalid amount.");
        }
        return String.format("%012.0f", new BigDecimal(amount).multiply(new BigDecimal(100)));
    }

    public static <T> T valueOrNothing(T tClass) {
        if (tClass == null) return null;
        String errorMsg = null;
        String className = tClass.getClass().getName();
        Gson gson = null;
        try {
            Class cls = Class.forName(className);
            T obj = (T) cls.getDeclaredConstructor().newInstance();
            gson = new GsonBuilder().create();
            return gson.toJson(obj).equals(gson.toJson(tClass)) ? null : tClass;
        } catch (ClassNotFoundException e) {
            errorMsg = e.getMessage();
        } catch (InvocationTargetException e) {
            errorMsg = e.getMessage();
        } catch (InstantiationException e) {
            errorMsg = e.getMessage();
        } catch (IllegalAccessException e) {
            errorMsg = e.getMessage();
        } catch (NoSuchMethodException e) {
            errorMsg = e.getMessage();
        } finally {
            gson = null;
        }
        if (errorMsg != null) throw new RuntimeException("error in valueOrNothing: " + errorMsg);
        return tClass;
    }

    public static <T extends Enum<T>> T toEnum(final Class<T> type, final String envVal) {
        String errorMsg = "";
        final String methodFromValue = "fromValue";
        String className = type.getName();

        try {
            Class cls = Class.forName(className);
            try {
                // 'fromValue' method exists on every Enum class in gmf library.
                Method method = cls.getDeclaredMethod(methodFromValue, String.class);
                return type.cast(method.invoke(type, envVal));

            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                try {
                    return Enum.valueOf(type, envVal.toUpperCase());
                } catch (IllegalArgumentException ex) {
                    throw new UnsupportedEnumValueException(String.format("%s for %s", envVal, type.getName()));
                }
            }
        } catch (ClassNotFoundException e) {
            errorMsg = String.format("Invalid type: %s", type);
        }
        throw new UnsupportedEnumValueException(errorMsg);
    }

    public static String validate(final Request request) {
        //todo: what else to validate?
        if (request == null)
            return "invalid or empty request";
        if (!Utils.isNotNullOrEmpty(request.pymtType))
            return "Payment type can't be empty";
        Utils.toEnum(PymtTypeType.class, request.pymtType);
        if (!Utils.isNotNullOrEmpty(request.txnType))
            return "Transaction type can't be empty";
        Utils.toEnum(TxnTypeType.class, request.txnType);
        if (!(Utils.toEnum(PymtTypeType.class, request.pymtType) == PymtTypeType.DEBIT ||
                Utils.toEnum(PymtTypeType.class, request.pymtType) == PymtTypeType.EBT)) {
//            if (!Utils.isNotNullOrEmpty(request.cardType))
//                return "Card type can't be empty";
//            else
            Utils.toEnum(CardTypeType.class, request.cardType);
        }

        if (Utils.isNotNullOrEmpty(request.ccvInd))
            Utils.toEnum(CCVIndType.class, request.ccvInd);
        if (Utils.isNotNullOrEmpty(request.merchantMID))
            RequestUtils.merchantID = RequestUtils.mapMidToDID(request.merchantMID); // todo: no need in prod

        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
        if (request.txnAmt == null || !pattern.matcher(request.txnAmt.toString()).matches()) {
            return "Invalid amount.";
        }
        return "";
    }

}
