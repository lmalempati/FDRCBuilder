package fdrc.common;

import fdrc.base.CardInfo;
import fdrc.proxy.PymtTypeType;
import fdrc.proxy.TxnTypeType;
import fdrc.xml.TransactionType;

import java.io.Serializable;

public class Request implements Serializable {
    public CardInfo cardInfo;
    // common grp
    public String posCondCode;
    public String POSEntryMode;
    public String pymtType;
    public String industrytype;
    public String stan;
    public String refNum;
    public String orderNum;
    public String txnType;
    public String termCatCode;
    public String termEntryCapablt;
    public Double txnAmt;
    public String txnCrncy;
    public String termLocInd;
    public String cardCaptCap;
    // config
    public String tppID;
    public String termID;
    public String merchID;
    public String merchCatCode;
    public String groupID;
    public String DigWltProgType;
    public String MobileInd;
    public String DfrdAuthInd;

    // AddtlAmtGrp
    public String partAuthrztnApprvlCapablt;

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
    // VisaGrp
    public String aci;
    public String visaBID;
    public String visaAUAR;
    public String taxAmtCapablt;

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

//    public static void main(String[] args) {
//        if (EnumEngineEntryMethod.Keyed == Enum.valueOf(EnumEngineEntryMethod.class, "Keyed")) {
//            System.out.println("matched");
//        }
//    }


