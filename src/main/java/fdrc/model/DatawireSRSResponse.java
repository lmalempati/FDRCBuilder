package fdrc.model;

public class DatawireSRSResponse {
    private String status;
    private String statusCode;
    public String errorMsg;
    private String did;

    public DatawireSRSResponse(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getStatus() {
        return status;
    }

    public DatawireSRSResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public DatawireSRSResponse setStatusCode(String statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public String getDid() {
        return did;
    }

    public DatawireSRSResponse setDid(String did) {
        this.did = did;
        return this;
    }
}
