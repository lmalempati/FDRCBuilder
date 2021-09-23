package fdrc.common;
/*
Check if it is possible first
To call methods that populate common grp, card grp and so on and then get the xml payload the way cardknox does it.
*  */

import fdrc.proxy.*;
import fdrc.utils.Utils;
import jdk.jshell.execution.Util;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Locale;

public class BuildRequest {

    public VisaGrp getVisaGrp() {
        VisaGrp visaGrp = new VisaGrp();
        visaGrp.setACI("Y");
//		/* ACI Type value*/
//		visaGrp.setACI("P");
        /*Visa BID value*/
        visaGrp.setVisaBID("56412");
//		/*Visa AUAR value*/
		visaGrp.setVisaAUAR("000000000000");
        /*Visa TAX Amount capability indicator value*/
        visaGrp.setTaxAmtCapablt("1");
        return visaGrp;
    }

    public CardGrp getCardGrp(Request request) {
        CardGrp cardGrp = new CardGrp();
        cardGrp.setAcctNum(request.cardInfo.acctNum);
        cardGrp.setCardExpiryDate(request.cardInfo.cardExpiryDate); //20160430
//        cardGrp.setCardType(CardTypeType.DISCOVER);
        cardGrp.setCCVInd(CCVIndType.valueOf(request.cardInfo.ccvInd.toUpperCase())); // CCVIndType.PRVDED
        cardGrp.setCCVData(request.cardInfo.ccvData); // "123"
//        cardGrp.setTrack2Data("4000620001234514=25123100000000001");
        cardGrp.setCardType(CardTypeType.valueOf(request.cardInfo.cardType.toUpperCase())); // CardTypeType.VISA
//        cardGrp.setCCVResultCode(CCVResultCodeType.MATCH);
        return cardGrp;
    }

    public CommonGrp getCommonGrp(Request request) {
        CommonGrp cmnGrp = new CommonGrp();
        /* The payment type of the transaction. */
        try {
            cmnGrp.setPymtType(PymtTypeType.valueOf(request.pymtType.toUpperCase())); // PymtTypeType.CREDIT
            /* The type of transaction being performed. */
//        cmnGrp.setTxnType(TxnTypeType.AUTHORIZATION);
            cmnGrp.setTxnType(TxnTypeType.valueOf(request.txnType.toUpperCase())); //TxnTypeType.SALE
            /* The local date and time in which the transaction was performed. */
            //cmnGrp.setLocalDateTime("20210914153400");
            cmnGrp.setLocalDateTime(Utils.getLocalDateTime());
            /* The transmission date and time of the transaction (in GMT/UCT). */
            cmnGrp.setTrnmsnDateTime(Utils.getUTCDateTime());
            /* A number assigned by the merchant to uniquely reference the transaction.
             * This number must be unique within a day per Merchant ID per Terminal ID. */
            cmnGrp.setSTAN(Utils.getSTAN()); // "100003"
            /* A number assigned by the merchant to uniquely reference a set of transactions. */
            if (request.refNum != null)
                cmnGrp.setRefNum(request.refNum); // "20200101012"
//            cmnGrp.setRefNum("123456789"); // "20200101012"
            /* A number assigned by the merchant to uniquely reference a transaction order sequence. */
            cmnGrp.setOrderNum(Utils.getOrderNum());
            /* An ID assigned by Fiserv, for the Third Party Processor or
             * Software Vendor that generated the transaction. */
            cmnGrp.setTPPID(fdrc.client.Constants.REQUEST_TPPID); // ToDo, get from req
            /* A unique ID assigned to a terminal. */
            cmnGrp.setTermID(fdrc.client.Constants.REQUEST_TERMID); // ToDo, get from req

            /* A unique ID assigned by Fiserv, to identify the Merchant. */
            cmnGrp.setMerchID(fdrc.client.Constants.REQUEST_MERCHID); // ToDo, get from req

            /* An identifier used to indicate the terminal’s account number entry mode
             * and authentication capability via the Point-of-Service. */
            cmnGrp.setPOSEntryMode("010"); // 011
            /* An identifier used to indicate the authorization conditions at the Point-of-Service (POS). */
            cmnGrp.setPOSCondCode("08"); // 59
            /* An identifier used to describe the type of terminal being used for the transaction. */
            cmnGrp.setTermCatCode("01");
            /* An identifier used to indicate the entry mode capability of the terminal. */
            cmnGrp.setTermEntryCapablt("00"); // 04
            /* The amount of the transaction. This may be an authorization amount,
             * adjustment amount or a reversal amount based on the type of transaction.
             * It is inclusive of all additional amounts.
             * It is submitted in the currency represented by the Transaction Currency field.
             * The field is overwritten in the response for a partial authorization. */
            cmnGrp.setTxnAmt(String.format("%.0f", request.txnAmt * 100));

            /* The numeric currency of the Transaction Amount. */
            cmnGrp.setTxnCrncy(request.txnCrncy); //"840"
            /* An indicator that describes the location of the terminal. */
            cmnGrp.setTermLocInd("0");
            /* Indicates whether or not the terminal has the capability to capture the card data. */
            cmnGrp.setCardCaptCap("1");
            /* Indicates Group ID. */
            cmnGrp.setGroupID(fdrc.client.Constants.REQUEST_GROUPID);
//        cmnGrp.setPLPOSDebitFlg("1");
            cmnGrp.setMerchCatCode(request.merchCatCode); //"5967"
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cmnGrp;
    }

    public EcommGrp getEcommGrp(){
        EcommGrp ecommGrp = new EcommGrp();
        /*ECommerce Transaction Indicator capability indicator value*/
//        ecommGrp.setEcommTxnInd("01");
        ecommGrp.setCustSvcPhoneNumber("4048900000");
        return ecommGrp;
    }

    public AltMerchNameAndAddrGrp getAltMerchNameAndAddrGrp(Request request){
        AltMerchNameAndAddrGrp altMerchNameAndAddrGrp = new AltMerchNameAndAddrGrp();
        altMerchNameAndAddrGrp.setMerchName(request.merchName);
        altMerchNameAndAddrGrp.setMerchAddr(request.merchAddr);
        altMerchNameAndAddrGrp.setMerchCity(request.merchCity);
        altMerchNameAndAddrGrp.setMerchState(request.merchState);
        altMerchNameAndAddrGrp.setMerchPostalCode(request.merchPostalCode);
        return altMerchNameAndAddrGrp;
    }

    public CustInfoGrp getCustInfoGrp(Request request){
        /* Populate values for CustInfoGrp Group */
        CustInfoGrp custInfoGrp = new CustInfoGrp();
        /*Billing address of the card holder*/
        custInfoGrp.setAVSBillingAddr(request.avsBillingAddr);
        /*Postal ZIP Code of the card holder*/
        custInfoGrp.setAVSBillingPostalCode(request.avsBillingPostalCode);
        return custInfoGrp;
    }

    protected String getXmlPayload() {
        GMFMessageVariants gmfMessageVariants;
        return "";
    }
}
