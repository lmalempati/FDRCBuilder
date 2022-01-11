package fdrc.utils;

import com.fiserv.merchant.gmfv10.CCVIndType;
import com.fiserv.merchant.gmfv10.CardTypeType;
import com.fiserv.merchant.gmfv10.PymtTypeType;
import com.fiserv.merchant.gmfv10.TxnTypeType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fdrc.Exceptions.InvalidNumber;
import fdrc.Exceptions.UnsupportedEnumValueException;
import fdrc.common.Constants;
import fdrc.model.RCRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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

    public static String getOrderRefNum() {
        return java.time.LocalDate.now().toString().replaceAll("-", "").substring(4, 8) + java.time.LocalTime.now().toString().replaceAll(":", "").substring(0, 6);
    }

    public static boolean isNotNullOrEmpty(Object s) {
        try {
            return s != null && !s.toString().trim().equals("");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static boolean isNotNullOrEmpty(Object[] src) {
        if (src == null) return false;
        try {
            for (Object obj : src
            ) {
                if (obj == null || obj.toString().trim().equals("")) return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return true;
    }

    public static String formatAmount(String amount) {
        if (amount == null) return null;
        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
        if (!pattern.matcher(amount).matches()) {
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
    /* Generate Client Ref Number in the format <STAN>|<TPPID>, right justified and left padded with "0" */
    public static String getClientRef() {
        String clientRef = "";
        clientRef = String.format("0%sV%s", Utils.getSTAN(), Constants.REQUEST_TPPID);
        return clientRef;
    }
    public static String mapMidToDID(String merchantID){
        // Todo: MID, DID, MCC, IndustryType so on has to come from caller, builder has no idea.
        switch(merchantID){
            case "RCTST1000092382":
                return "00035606147505719454";
            case "RCTST1000092383":
                return "00035606151483882257";
            case "RCTST1000092384":
                return "00035606180092691444";

            case "RCTST1000091636":
                return "00041372277848179310";
            case "RCTST1000091637":
                return "00035488451724571345";
            case "RCTST1000091638":
                return "00035488381390525644";

            case "RCTST1000092850":
                return "00042217876975393171";
            case "RCTST1000092851":
                return "00042218039591394672";
            case "RCTST1000092852":
                return "00042217833110503539";
            default:
                throw new UnsupportedEnumValueException(String.format("merchantID %s", merchantID));
        }
    }
    public static String validate(final RCRequest rcRequest) {
        //todo: what else to validate?
        if (rcRequest == null)
            return "invalid or empty request";
        if (!Utils.isNotNullOrEmpty(rcRequest.pymtType))
            return "Payment type can't be empty";
        Utils.toEnum(PymtTypeType.class, rcRequest.pymtType);
        if (!Utils.isNotNullOrEmpty(rcRequest.txnType))
            return "Transaction type can't be empty";
        Utils.toEnum(TxnTypeType.class, rcRequest.txnType);
        if (!(Utils.toEnum(PymtTypeType.class, rcRequest.pymtType) == PymtTypeType.DEBIT ||
                Utils.toEnum(PymtTypeType.class, rcRequest.pymtType) == PymtTypeType.EBT)) {
//            if (!Utils.isNotNullOrEmpty(request.cardType))
//                return "Card type can't be empty";
//            else
            Utils.toEnum(CardTypeType.class, rcRequest.cardType);
        }

        if (!Utils.isNotNullOrEmpty(rcRequest.cardCaptCap)) // todo: this can't be on request as we need to derive it..
            return "Invalid cardCaptCap";

        if (Utils.isNotNullOrEmpty(rcRequest.ccvInd))
            Utils.toEnum(CCVIndType.class, rcRequest.ccvInd);
        if (!Utils.isNotNullOrEmpty(rcRequest.merchantMID))
            return "Invalid MID";
//            RequestUtils.merchantID = RequestUtils.mapMidToDID(rcRequest.merchantMID); // todo: no need in prod

        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
        if (rcRequest.txnAmt == null || !pattern.matcher(rcRequest.txnAmt.toString()).matches()) {
            return "Invalid amount.";
        }
        return "";
    }
    public static String upload(String urlPath, String reqXml) {
        URL url;
        StringBuilder response = null;
        try {
            url = new URL(urlPath); //"https://stg.dw.us.fdcnet.biz/sd/srsxml.rc"

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "text/xml; utf-8");
            con.setRequestProperty("Accept", "text/xml");
            con.setDoOutput(true);
            con.connect();

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = reqXml.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
                response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response != null ? response.toString() : "";
    }
}
