package fdrc.model;

public class DatawireSRSRequest {
    public boolean stagOrProd;
    public String merchantId;
    public String terminalId;
    public String groupId;
    public String tppId;

    public DatawireSRSRequest(boolean stagOrProd, String merchantId, String terminalId, String groupId, String tppId) {
        this.stagOrProd = stagOrProd;
        this.merchantId = merchantId;
        this.terminalId = terminalId;
        this.groupId = groupId;
        this.tppId = tppId;
    }
    public DatawireSRSRequest() {}
}
