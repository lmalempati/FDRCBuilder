package fdrc.types;

public enum HttpMethod {
    GET("GET"),
    POST("POST");
    public final String val;

    HttpMethod(String value){
        val = value;
    }
}
