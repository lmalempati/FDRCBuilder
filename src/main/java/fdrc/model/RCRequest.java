package fdrc.model;

public class RCRequest {
    // no param constructor
    public RCRequest(){} // TODO:tmp code, we can remove in prod. without this, json de-serialization expects all fields to be present!
    // config
    public String tppID;
    public String termID;
    public String merchID;
    public String merchCatCode;
    public String dfrdAuthInd;
    public String merchFNSNum; // TODO: This has to come from setup
    public String dataWireID;

    // common grp
    public String posCondCode;
    public String posEntryMode;
    public String pymtType;
    public String trnsmitDateTime;
    public String localDateTime;
    public String stan;
    public String refNum;
    public String orderNum;
    public String txnType;
    public String reversalInd;
    public String termCatCode;
    public String termEntryCapablt;
    public String txnAmt; // TODO: should we use BigDecimal?
    public String txnCrncy;
    public String termLocInd;
    public String cardCaptCap;
    public String netAccInd;
    public String merchantMID;
    public String groupID;
    public String digWltProgType;
    public String mobileInd;
    public String plPOSDebitFlg;

    // card grp
    public String acctNum;
    public String cardExpiryDate;
    public String cardType;
    public String ccvInd;
    public String ccvData;
    public String track2Data;
    public String ccvResultCode;

    // OrigAuthGrp
    public String origAuthID;
    public String origLocalDateTime;
    public String origTranDateTime;
    public String origRespCode;
    public String origSTAN;
    public String refundType;
    public String responseDate;
    public String origAthNtwkID;
    public String origDebitTraceNum;

    // AddtlAmtGrp
    public String partAuthrztnApprvlCapablt;
    public String firstAuthAmt;
    public String totalAuthAmt;
    public String cashbackAmt;
    public String healthcareAmt;
    public String rxAmt;

    //EMV grp
    public String emvData; // byte[]
    public String cardSeqNum;
    public String xCodeResp;
    // PINGrp
     public String pinData; // byte[]
     public String keySerialNumData; // byte[]

    // Visa Grp
    public String aci;
    public String visaBID;
    public String visaAUAR;
    public String taxAmtCapablt;
    public String transID;
    public String cardLevelResult;
    public String spendQInd;
    public String mrktSpecificDataInd;


    // MC grp
    public String finAuthInd;
    public String banknetData;
    public String mcACI;
    public String mcMSDI;

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
    public String amexPOSData;
    // TAGrp - Transarmor group
    public String sctyLvl;
    public String encrptType;
    public String encrptTrgt;
    public String keyID;
    public String encrptBlock;
    public String tknType;
    public String tkn;
    public String deviceType;

    // Ecomm Grp
    public String custSvcPhoneNumber;

    // SecrTxnGrp
    public String cavvResultCode;

    //CustInfoGrp
    public String avsBillingAddr;
    public String avsBillingPostalCode;
    public String avsResultCode;

    // Alternate merchant name and address group
    public String merchName;
    public String merchAddr;
    public String merchCity;
    public String merchState;
    public String merchCtry;
    public String merchPostalCode;
//    public String addtlAmtType;
//    public String addtlAmt;

    // HostTot Grp (AdminRequest)
    public String totReqDate;
    public String password;
    public String netSettleAmt;
    //TODO: not required in prod

    // EBT Grp
    public String ebtType;

    // Tokenization Grp
    public String tknReqID;
    public String tknLvl;
}

