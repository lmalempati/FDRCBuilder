package fdrc.base;

public class Response {
    public String respCode;
    public String addtlRespData;
    public String errorMsg;

    public Response(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    public Response() {
    }
}
