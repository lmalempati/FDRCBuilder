package fdrc.base;

import com.fiserv.merchant.gmfv10.CreditResponseDetails;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Response {
    @XmlElement
    public String respCode;
    @XmlElement
    public String addtlRespData;
    @XmlElement
    public String errorMsg;
    @XmlElement
    public String origAuthID;
    @XmlElement
    public String origLocalDateTime;
    @XmlElement
    public String origTranDateTime;
    @XmlElement
    public String origSTAN;
    @XmlElement
    public String origRespCode;
    @XmlElement
    public String refNum;
    public String orderNum;
    public String banknetData;
    public String aci;
    public String discNRID;
    public String discTransQualifier;
    public String cavvResultCode;
    public String transID;
    public String cardLevelResult;
    public String amexTranID;
    public String spendQInd;

    public String responseRaw;

    public Response(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Response() {
    }

    CreditResponseDetails creditResponseDetails = new CreditResponseDetails();
}
