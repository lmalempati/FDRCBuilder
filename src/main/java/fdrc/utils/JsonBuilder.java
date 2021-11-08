package fdrc.utils;

import com.fiserv.merchant.gmfv10.ReversalIndType;
import com.fiserv.merchant.gmfv10.TxnTypeType;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import fdrc.Exceptions.UnsupportedEnumValueException;
import fdrc.base.Request;
import fdrc.base.Response;

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
    public static String getJsonFromRequest(Request request, String fileName) {
        String resultJson = null;
        try {
            Gson gson = new GsonBuilder().serializeNulls().create();
            String tmp = gson.toJson(request, Request.class);
            resultJson = tmp.replaceAll("\\\\u003d", "=");
            FileWriter writer = new FileWriter(fileName);
            writer.write(resultJson);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultJson;
    }

    public static Request getRequestFromJson(String filePath) {
        Request request = null;
        try (FileReader reader = new FileReader(filePath)) {
            JsonReader jsonReader = new JsonReader(reader);
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Request.class, new MyTypeAdapter());
            Gson gson = gsonBuilder.create();
            request = gson.fromJson(reader, Request.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (request == null) throw new UnsupportedEnumValueException("Json payload.");
        }
        return request;
    }

    public static Request getRequestFromJsonString(String json) {
        Request request = null;
        try {
            Gson gson = new GsonBuilder().create();
            request = gson.fromJson(json, Request.class);
        } catch (JsonSyntaxException e) {
            throw new JsonSyntaxException(e.getMessage());
        }
        return request;
    }

    public static boolean updateCompletionPayload(Response response, String fileName) {
        Request request = getRequestFromJson(fileName);
        request.origAuthID = response.origAuthID;
        request.origRespCode = response.origRespCode;
        request.origSTAN = response.origSTAN;
        request.origTranDateTime = response.origTranDateTime;
        request.origLocalDateTime = response.origLocalDateTime;
        request.refNum = response.refNum;
        request.banknetData = response.banknetData;
        request.discNRID = response.discNRID;
        request.discTransQualifier = response.discTransQualifier;
        request.transID = response.transID;
        request.cardLevelResult = response.cardLevelResult;
        request.aci = response.aci;

        if (Utils.toEnum(TxnTypeType.class, request.txnType) == TxnTypeType.COMPLETION ||
        Utils.isNotNullOrEmpty(request.reversalInd) && Utils.toEnum(ReversalIndType.class, request.reversalInd) == ReversalIndType.VOID)
            request.orderNum = response.orderNum;

        getJsonFromRequest(request, fileName);
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

class MyTypeAdapter implements JsonDeserializer<Request> {
    @Override
    public Request deserialize(JsonElement json, Type myClassType, JsonDeserializationContext context)
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
        return new Gson().fromJson(replacementJsonObject, Request.class);
    }
}
