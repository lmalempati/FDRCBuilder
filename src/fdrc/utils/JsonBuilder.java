package fdrc.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import fdrc.base.CardInfo;
import fdrc.common.Request;
import jdk.jfr.Frequency;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonBuilder {
    public static String GetJsonFromRequest(Request request){
        String resultJson = null;
        try {
            Gson gson = new GsonBuilder().serializeNulls().create();
            resultJson = gson.toJson(request, Request.class);
//            String tmp =resultJson.replaceAll("null", "");
//            resultJson =tmp;
            FileWriter writer = new FileWriter("payload.json");
            writer.write(resultJson);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultJson;
    }

    public static Request GetRequestFromJson(){
        Request request = null;
        try (FileReader reader = new FileReader("payload.json")) {
            JsonReader jsonReader = new JsonReader(reader);
            Gson gson = new GsonBuilder().create();
            request = gson.fromJson(reader, Request.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return request;
    }

    public static Request GetRequestFromJsonString(String json){
        Request request = null;
        try {
            Gson gson = new GsonBuilder().create();
            request = gson.fromJson(json, Request.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return request;
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
