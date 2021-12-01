package fdrc.base;

import java.math.BigDecimal;

public class Request {
    // no param constructor
    public Request(){} // todo:tmp code, we can remove in prod. without this, de-serialilization expects all fields to be present!
    // card grp
    public String acctNum;
    public String cardExpiryDate;
    public String cardType;
    public String ccvInd;
    public String ccvData;
    public String track2Data;
    public String ccvResultCode;
    public String origAuthID;
    public String origLocalDateTime;
    public String origTranDateTime;
    public String origRespCode;
    public String origSTAN;
    public String refundType;
    // AddtlAmtGrp
    public String partAuthrztnApprvlCapablt;
    public BigDecimal firstAuthAmt;
    public BigDecimal totalAuthAmt;
    public BigDecimal cashbackAmt;
    public BigDecimal healthcareAmt;
    public BigDecimal rxAmt;

    //EMV grp
    public String emvData; // byte[]
    public String cardSeqNum;
    // PINGrp
     public String pinData; // byte[]
     public String keySerialNumData; // byte[]
    // common grp
    public String posCondCode;
    public String posEntryMode;
    public String pymtType;
//    public String industrytype; -- not required
    public String stan;
    public String refNum;
    public String orderNum;
    public String txnType;
    public String reversalInd;
    public String termCatCode;
    public String termEntryCapablt;
    public Double txnAmt; // todo: should we use BigDecimal?
    public String txnCrncy;
    public String termLocInd;
    public String cardCaptCap;
    // VisaGrp -
    public String aci;
    public String visaBID;
    public String visaAUAR;
    public String taxAmtCapablt;
    public String transID;
    public String cardLevelResult;
    public String spendQInd;
    // SecrTxnGrp
    public String cavvResultCode;
    // MC grp
    public String finAuthInd;
    public String banknetData;
    public String mcACI;
    // Discover grp
    public String discProcCode;
    public String discPOSEntry;
    public String discRespCode;
    public String discPOSData;
    public String discTransQualifier;
    public String discNRID;
    public String motoInd;
    //Amex grp
    public String amexTranID;
    // config
    public String tppID;
    public String termID;
    public String merchID;
    public String merchCatCode;
    public String groupID;
    public String DigWltProgType;
    public String MobileInd;
    public String DfrdAuthInd;
    // TAGrp - Transarmor group
    public String sctyLvl;
    public String encrptType;
    public String encrptTrgt;
    public String keyID;
    public String encrptBlock;
    public String tknType;
    public String tkn;
    public String deviceType;
    // EcommGrp
    public String custSvcPhoneNumber;
    //CustInfoGrp
    public String avsBillingAddr;
    public String avsBillingPostalCode;
    public String avsResultCode;
    // Alternate merchant name and address group
    public String merchName;
    public String merchAddr;
    public String merchCity;
    public String merchState;
    public String merchPostalCode;
//    public String addtlAmtType;
//    public String addtlAmt;
    //todo: not required in prod
    public String merchantMID;
    public String ebtType;
    public String merchFNSNum; // ToDo: This has to come from setup
}

