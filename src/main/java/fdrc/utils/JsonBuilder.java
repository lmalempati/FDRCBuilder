package fdrc.utils;

import com.fiserv.merchant.gmfv10.ReversalIndType;
import com.fiserv.merchant.gmfv10.TxnTypeType;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import fdrc.Exceptions.UnsupportedValueException;
import fdrc.model.RCRequest;
import fdrc.model.RCResponse;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * This class has functionality to serialize and deserialize json.
 * This class is not actually used by the Android app
 * */

public class JsonBuilder {
    public static boolean updateJsonFromRequest(RCRequest RCRequest, String fileName) {
        String resultJson = null;
        try {
            Gson gson = new GsonBuilder().serializeNulls().create();
            String tmp = gson.toJson(RCRequest, RCRequest.class);
            resultJson = tmp.replaceAll("\\\\u003d", "=");
            FileWriter writer = new FileWriter(fileName);
            writer.write(resultJson);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static RCRequest getRequestFromJson(String filePath) {
        RCRequest RCRequest = null;
        try (FileReader reader = new FileReader(filePath)) {
            JsonReader jsonReader = new JsonReader(reader);
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(RCRequest.class, new MyTypeAdapter());
            Gson gson = gsonBuilder.create();
            RCRequest = gson.fromJson(reader, RCRequest.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (RCRequest == null) throw new UnsupportedValueException("Json payload.");
        }
        return RCRequest;
    }

    public static RCRequest getRequestFromJsonString(String json) {
        RCRequest RCRequest = null;
        try {
            Gson gson = new GsonBuilder().create();
            RCRequest = gson.fromJson(json, RCRequest.class);
        } catch (JsonSyntaxException e) {
            throw new JsonSyntaxException(e.getMessage());
        }
        return RCRequest;
    }

    public static boolean updateCompletionPayload(RCResponse response, String fileName) {
        RCRequest request = getRequestFromJson(fileName);
        request.origAuthID = response.authID;
        request.origRespCode = response.respCode;
        request.origSTAN = response.stan;
        request.origTranDateTime = response.tranDateTime;
        request.origLocalDateTime = response.localDateTime;
        request.refNum = response.refNum;
        request.banknetData = response.banknetData;

        request.discNRID = response.discNRID;
        request.discPOSData = response.discPOSData;
        request.discPOSEntry = response.discPOSEntry;
        request.discRespCode= response.discRespCode;
        request.discProcCode = response.discProcCode;
        request.motoInd = response.motoInd;

        request.discTransQualifier = response.discTransQualifier;
        request.transID = response.transID;
        request.cardLevelResult = response.cardLevelResult;
        request.aci = response.aci;
        request.amexTranID = response.amexTranID;
        request.amexPOSData = response.amexPOSData;
        request.spendQInd = response.spendQInd;
        if (Utils.toEnum(TxnTypeType.class, request.txnType) == TxnTypeType.COMPLETION ||
        Utils.isNotNullOrEmpty(request.reversalInd) && Utils.toEnum(ReversalIndType.class, request.reversalInd) == ReversalIndType.VOID)
            request.orderNum = response.orderNum;
        request.tkn = response.tkn;

        updateJsonFromRequest(request, fileName);
        return true;
    }


    public static void main(String[] args) {
//        Request request = new Request();
//        CardInfo cardInfo = new CardInfo();
//        cardInfo.acctNum = "4005571702222222";
//        request.cardInfo = cardInfo;
//        GetJsonFromRequest(request);
//        Request r = GetRequestFromJson();

    }
}

class MyTypeAdapter implements JsonDeserializer<RCRequest> {
    @Override
    public RCRequest deserialize(JsonElement json, Type myClassType, JsonDeserializationContext context)
            throws JsonParseException {
        // json = {"field":"one"}
        JsonObject originalJsonObject = json.getAsJsonObject();
        JsonObject replacementJsonObject = new JsonObject();
        for (Map.Entry<String, JsonElement> elementEntry : originalJsonObject.entrySet()) {
            String key = elementEntry.getKey();
            JsonElement value = originalJsonObject.get(key);
//            key = key.toLowerCase();
            replacementJsonObject.add(key, value);
        }
        return new Gson().fromJson(replacementJsonObject, RCRequest.class);
    }
}
