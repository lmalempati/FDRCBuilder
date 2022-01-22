package fdrc.model;

public class RCResponse {
    // Response grp
    public String respCode;
    public String addtlRespData;
    public String authID;
    public String responseDate;
    public String rtInd;
    public String origAthNtwkID;
    public String origDebitTraceNum;

    // Common grp
    public String localDateTime;
    public String tranDateTime;
    public String stan;
    public String refNum;
    public String orderNum;
    public String netAccInd;

    // MC grp
    public String banknetData;

    // Visa grp
    public String aci;
    public String transID;
    public String cardLevelResult;
    public String spendQInd;

    // Discover grp
    public String discNRID;
    public String discTransQualifier;
    public String discPOSEntry;
    public String discPOSData;
    public String discRespCode;
    public String discProcCode;
    public String motoInd;
    // Amex grp
    public String amexTranID;
    public String sctyLvl;
    public String amExPOSData;

    // Secure Trxn grp
    public String cavvResultCode; // not in use
    // TA grp
    public String tkn;

    // Token Grp
    public String tknReqID;

    // EMV Grp
    public String xCodeResp;

    public String errorMsg;
    public String responsePayload = "";
    public String trnmsnDateTime; // to store request's trnmsnDateTime

    public String DWStatusCode;
    public String DWReturnCode;

    public RCResponse(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public RCResponse() {
    }
}
