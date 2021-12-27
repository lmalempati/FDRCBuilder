package fdrc.utils;

import com.fiserv.merchant.gmfv10.ReversalIndType;
import com.fiserv.merchant.gmfv10.TxnTypeType;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import fdrc.Exceptions.UnsupportedEnumValueException;
import fdrc.base.RCRequest;
import fdrc.base.RCResponse;

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
    public static String getJsonFromRequest(RCRequest RCRequest, String fileName) {
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
        return resultJson;
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
            if (RCRequest == null) throw new UnsupportedEnumValueException("Json payload.");
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

    public static boolean updateCompletionPayload(RCResponse RCResponse, String fileName) {
        RCRequest RCRequest = getRequestFromJson(fileName);
        RCRequest.origAuthID = RCResponse.origAuthID;
        RCRequest.origRespCode = RCResponse.origRespCode;
        RCRequest.origSTAN = RCResponse.origSTAN;
        RCRequest.origTranDateTime = RCResponse.origTranDateTime;
        RCRequest.origLocalDateTime = RCResponse.origLocalDateTime;
        RCRequest.refNum = RCResponse.refNum;
        RCRequest.banknetData = RCResponse.banknetData;
        RCRequest.discNRID = RCResponse.discNRID;
        RCRequest.discTransQualifier = RCResponse.discTransQualifier;
        RCRequest.transID = RCResponse.transID;
        RCRequest.cardLevelResult = RCResponse.cardLevelResult;
        RCRequest.aci = RCResponse.aci;
        RCRequest.amexTranID = RCResponse.amexTranID;
        RCRequest.spendQInd = RCResponse.spendQInd;
        if (Utils.toEnum(TxnTypeType.class, RCRequest.txnType) == TxnTypeType.COMPLETION ||
        Utils.isNotNullOrEmpty(RCRequest.reversalInd) && Utils.toEnum(ReversalIndType.class, RCRequest.reversalInd) == ReversalIndType.VOID)
            RCRequest.orderNum = RCResponse.orderNum;
        RCRequest.tkn = RCResponse.tkn;

        getJsonFromRequest(RCRequest, fileName);
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
