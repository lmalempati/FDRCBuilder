package fdrc.base;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class RCResponse {
    // Response grp
    public String respCode;
    public String origRespCode;
    public String addtlRespData;
    public String origAuthID;
    // Common grp
    public String origLocalDateTime;
    public String origTranDateTime;
    public String origSTAN;
    public String refNum;
    public String orderNum;
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

    // Amex grp
    public String amexTranID;
    public String sctyLvl;

    // Secure Trxn grp
    public String cavvResultCode; // not in use
    // TA grp
    public String tkn;

    public String errorMsg;
    public String responseRaw = "";
    public String trnmsnDateTime; // to store request's trnmsnDateTime

    public String StatusCode;
    public String DWStatusCode;
    public String DWStatus;
    public String DWReturnCode;
    public String ResponseCode;
    public RCResponse(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public RCResponse() {
    }
}
