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
    public String plPOSDebitFlg;

    // MC grp
    public String banknetData;
    public String mcMSDI;

    // Visa grp
    public String aci;
    public String transID;
    public String cardLevelResult;
    public String spendQInd;
    public String mrktSpecificDataInd;

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
    public String amexPOSData;

    // Secure Trxn grp
    public String cavvResultCode; // not in use
    // TA grp
    public String tkn;
    public String sctyLvl;

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
