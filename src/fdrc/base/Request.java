package fdrc.base;

public class Request {
    // no param constructor
    public Request(){} // without this, de-serialilization expects all fields to be present!
    // card grp
    public String acctNum;
    public String cardExpiryDate;
    public String cardType;
    public String ccvInd;
    public String ccvData;
    public String track2Data;
    public String avsResultCode;
    public OrigAuthInfo origAuthInfo;

    // AddtlAmtGrp
    public AddtlAmtInfo addtlAmtInfo;
    //EMV grp
    public byte[] emvData;
    public String cardSeqNum;
    // PINGrp
     public byte[] pinData;
     public byte[] keySerialNumData;

    // common grp
    public String posCondCode;
    public String posEntryMode;
    public String pymtType;
    public String industrytype;
    public String stan;
    public String refNum;
    public String orderNum;
    public String txnType;
    public String reversalInd;
    public String termCatCode;
    public String termEntryCapablt;
    public Double txnAmt;
    public String txnCrncy;
    public String termLocInd;
    public String cardCaptCap;

    // VisaGrp
    public String aci;
    public String visaBID;
    public String visaAUAR;
    public String taxAmtCapablt;
    public String transID;
    public String cardLevelResult;
    // MC grp
    public String finAuthInd;
    public String banknetData;
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
    public String token;
    // EcommGrp
    public String custSvcPhoneNumber;

    //CustInfoGrp
    public String avsBillingAddr;
    public String avsBillingPostalCode;

    // Alternate merchant name and address group
    public String merchName;
    public String merchAddr;
    public String merchCity;
    public String merchState;
    public String merchPostalCode;
}

