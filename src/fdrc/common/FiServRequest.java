package fdrc.common;
/*
Check if it is possible first
To call methods that populate common grp, card grp and so on and then get the xml payload the way cardknox does it.
*  */

import fdrc.base.Constants;
import fdrc.base.Request;
import fdrc.proxy.*;
import fdrc.types.EnumAllowPartialAuth;
import fdrc.utils.Utils;

import java.io.InvalidObjectException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FiServRequest { // todo name

    private final Request request;
    public static final BigDecimal BD_ZERO = new BigDecimal(0);

    public FiServRequest(Request requestPassedIn) {
        // request alrady checked for null
        this.request = requestPassedIn;
    }

    public CommonGrp getCommonGrp() {
        CommonGrp cmnGrp = new CommonGrp();
        /* The payment type of the transaction. */
//        try {
        cmnGrp.setPymtType(PymtTypeType.fromValue(request.pymtType)); // PymtTypeType.CREDIT
        if (Utils.isNotNullOrEmpty(request.reversalInd))
            cmnGrp.setReversalInd(ReversalIndType.fromValue(request.reversalInd));
        /* The type of transaction being performed. */
//        cmnGrp.setTxnType(TxnTypeType.AUTHORIZATION);
        cmnGrp.setTxnType(TxnTypeType.fromValue(request.txnType)); //TxnTypeType.SALE
        /* The local date and time in which the transaction was performed. */
        //cmnGrp.setLocalDateTime("20210914153400");
        cmnGrp.setLocalDateTime(Utils.getLocalDateTime());
        /* The transmission date and time of the transaction (in GMT/UCT). */
        cmnGrp.setTrnmsnDateTime(Utils.getUTCDateTime());
        /* A number assigned by the merchant to uniquely reference the transaction.
         * This number must be unique within a day per Merchant ID per Terminal ID. */
        cmnGrp.setSTAN(Utils.getSTAN()); // "100003"
        /* A number assigned by the merchant to uniquely reference a set of transactions. */
        if (Utils.isNotNullOrEmpty(request.refNum))
            cmnGrp.setRefNum(request.refNum); // "20200101012"
        else
            cmnGrp.setRefNum(Utils.getOrderNum()); // "20200101012"
        /* A number assigned by the merchant to uniquely reference a transaction order sequence. */
        // ToDo, completions should have ordernum, need to validate?
        if (Utils.isNotNull(request.orderNum))
            cmnGrp.setOrderNum(request.orderNum);
        else
            cmnGrp.setOrderNum(Utils.getOrderNum());
        /* An ID assigned by Fiserv, for the Third Party Processor or
         * Software Vendor that generated the transaction. */
        cmnGrp.setTPPID(Constants.REQUEST_TPPID); // ToDo, get from req
        /* A unique ID assigned to a terminal. */
        cmnGrp.setTermID(Constants.REQUEST_TERMID); // ToDo, get from req

        /* A unique ID assigned by Fiserv, to identify the Merchant. */
        cmnGrp.setMerchID(Constants.REQUEST_MERCHID); // ToDo, get from req

        /* An identifier used to indicate the terminal’s account number entry mode
         * and authentication capability via the Point-of-Service. */
        cmnGrp.setPOSEntryMode(request.posEntryMode); //010// 011
        /* An identifier used to indicate the authorization conditions at the Point-of-Service (POS). */
        cmnGrp.setPOSCondCode(request.posCondCode); //08, 59
        /* An identifier used to describe the type of terminal being used for the transaction. */
        cmnGrp.setTermCatCode(request.termCatCode); //00, 01
        /* An identifier used to indicate the entry mode capability of the terminal. */
        cmnGrp.setTermEntryCapablt(request.termEntryCapablt); //00 04
        /* The amount of the transaction. This may be an authorization amount,
         * adjustment amount or a reversal amount based on the type of transaction.
         * It is inclusive of all additional amounts.
         * It is submitted in the currency represented by the Transaction Currency field.
         * The field is overwritten in the response for a partial authorization. */
        cmnGrp.setTxnAmt(Utils.formatAmount(request.txnAmt.toString()));

        /* The numeric currency of the Transaction Amount. */
        cmnGrp.setTxnCrncy(request.txnCrncy); //"840"
        /* An indicator that describes the location of the terminal. */
        cmnGrp.setTermLocInd(request.termLocInd); // 0
        /* Indicates whether or not the terminal has the capability to capture the card data. */
        cmnGrp.setCardCaptCap(request.cardCaptCap); //1
        /* Indicates Group ID. */
        cmnGrp.setGroupID(Constants.REQUEST_GROUPID);
//        cmnGrp.setPLPOSDebitFlg("1");
        cmnGrp.setMerchCatCode(request.merchCatCode); //"5967"
//        } catch (IllegalArgumentException e) {
//            throw new RuntimeException(e.getMessage());
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
        return cmnGrp;
    }

    public EcommGrp getEcommGrp() {
        EcommGrp ecommGrp = new EcommGrp();
//        ecommGrp.setEcommTxnInd("01");
        if (Utils.isNotNullOrEmpty(request.custSvcPhoneNumber))
            ecommGrp.setCustSvcPhoneNumber(request.custSvcPhoneNumber);
        return Utils.valueOrNothing(ecommGrp);
    }

    public CardGrp getCardGrp() {
        if (request == null) return null;
        CardGrp cardGrp = new CardGrp();
        try {
            if (Utils.isNotNullOrEmpty(request.acctNum))
                cardGrp.setAcctNum(request.acctNum);
            if (Utils.isNotNullOrEmpty(request.cardExpiryDate))
                cardGrp.setCardExpiryDate(request.cardExpiryDate); //20160430
//        cardGrp.setCardType(CardTypeType.DISCOVER);
            if (Utils.isNotNullOrEmpty(request.ccvInd))
                cardGrp.setCCVInd(CCVIndType.fromValue(request.ccvInd)); // CCVIndType.PRVDED
            if (Utils.isNotNullOrEmpty(request.ccvData))
                cardGrp.setCCVData(request.ccvData); // "123"
//        cardGrp.setTrack2Data("4000620001234514=25123100000000001");
            if (Utils.isNotNullOrEmpty(request.cardType))
                cardGrp.setCardType(CardTypeType.fromValue(request.cardType));
            if (Utils.isNotNullOrEmpty(request.avsResultCode))
                cardGrp.setAVSResultCode(request.avsResultCode);
            if (Utils.isNotNullOrEmpty(request.ccvResultCode))
                cardGrp.setCCVResultCode(CCVResultCodeType.fromValue(request.ccvResultCode));
            cardGrp.setTrack2Data(request.track2Data);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return Utils.valueOrNothing(cardGrp);
    }

    public VisaGrp getVisaGrp() {
        VisaGrp visaGrp = new VisaGrp();
        try {
            if (Utils.isNotNullOrEmpty(request.aci))
                visaGrp.setACI(request.aci.toUpperCase());
            if (Utils.isNotNullOrEmpty(request.visaBID))
                visaGrp.setVisaBID(request.visaBID); // ToDo
            if (TxnTypeType.fromValue(request.txnType) != TxnTypeType.AUTHORIZATION)
                visaGrp.setVisaAUAR(request.visaAUAR);
            /*Visa TAX Amount capability indicator value*/
            if (Utils.isNotNullOrEmpty(request.transID))
                visaGrp.setTransID(request.transID);
            if (Utils.isNotNullOrEmpty(request.taxAmtCapablt))
                visaGrp.setTaxAmtCapablt(request.taxAmtCapablt);
            if (Utils.isNotNullOrEmpty(request.cardLevelResult))
                visaGrp.setCardLevelResult(request.cardLevelResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Utils.valueOrNothing(visaGrp);
    }

    public MCGrp getMasterCardGrp() {
        MCGrp mcGrp = new MCGrp();
        if (Utils.isNotNullOrEmpty(request.finAuthInd))
            mcGrp.setFinAuthInd(String.valueOf(request.finAuthInd));
        if (Utils.isNotNullOrEmpty(request.banknetData))
            mcGrp.setBanknetData(request.banknetData); // as per

        if (Utils.isNotNullOrEmpty(request.addtlAmtType) && Utils.isNotNullOrEmpty(request.addtlAmtType.split(",")[0]))
            if (!request.addtlAmt.split(",")[0].equals(BD_ZERO) && (AddAmtTypeType.fromValue(request.addtlAmtType.split(",")[0]) == AddAmtTypeType.HLTCARE))
                mcGrp.setMCMSDI(MCMSDIType.HEALTHCARE);
        return Utils.valueOrNothing(mcGrp);
    }

    public DSGrp getDiscoverGrp() {
        DSGrp dsGrp = new DSGrp();
        if (Utils.isNotNullOrEmpty(request.discProcCode))
            dsGrp.setDiscProcCode(request.discProcCode);
        if (Utils.isNotNullOrEmpty(request.discPOSEntry))
            dsGrp.setDiscPOSEntry(request.discPOSEntry);
        if (Utils.isNotNullOrEmpty(request.discRespCode))
            dsGrp.setDiscRespCode(request.discRespCode);
        if (Utils.isNotNullOrEmpty(request.discPOSData))
            dsGrp.setDiscPOSData(request.discPOSData);
        if (Utils.isNotNullOrEmpty(request.discTransQualifier))
            dsGrp.setDiscTransQualifier(request.discTransQualifier);
        if (Utils.isNotNullOrEmpty(request.discNRID))
            dsGrp.setDiscNRID(request.discNRID);
        return Utils.valueOrNothing(dsGrp);
    }

    public AltMerchNameAndAddrGrp getAltMerchNameAndAddrGrp() {
        AltMerchNameAndAddrGrp altMerchNameAndAddrGrp = new AltMerchNameAndAddrGrp();
        try {
            altMerchNameAndAddrGrp = new AltMerchNameAndAddrGrp();
            if (Utils.isNotNullOrEmpty(request.merchName))
                altMerchNameAndAddrGrp.setMerchName(request.merchName);
            if (Utils.isNotNullOrEmpty(request.merchAddr))
                altMerchNameAndAddrGrp.setMerchAddr(request.merchAddr);
            if (Utils.isNotNullOrEmpty(request.merchCity))
                altMerchNameAndAddrGrp.setMerchCity(request.merchCity);
            if (Utils.isNotNullOrEmpty(request.merchState))
                altMerchNameAndAddrGrp.setMerchState(request.merchState);
            if (Utils.isNotNullOrEmpty(request.merchPostalCode))
                altMerchNameAndAddrGrp.setMerchPostalCode(request.merchPostalCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Utils.valueOrNothing(altMerchNameAndAddrGrp);
//        if (!Utils.isNotNullOrEmpty(altMerchNameAndAddrGrp.getMerchName())) return null;
//        return altMerchNameAndAddrGrp;
    }

    public CustInfoGrp getCustInfoGrp() {
        /* Populate values for CustInfoGrp Group */
        CustInfoGrp custInfoGrp = new CustInfoGrp();
        try {
            /*Billing address of the card holder*/
            if (Utils.isNotNullOrEmpty(request.avsBillingAddr))
                custInfoGrp.setAVSBillingAddr(request.avsBillingAddr);
            /*Postal ZIP Code of the card holder*/
            if (Utils.isNotNullOrEmpty(request.avsBillingPostalCode))
                custInfoGrp.setAVSBillingPostalCode(request.avsBillingPostalCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Utils.valueOrNothing(custInfoGrp);
    }

    public OrigAuthGrp getOrigAuthGrp() {
        OrigAuthGrp origAuthGrp = new OrigAuthGrp();
        if (Utils.isNotNullOrEmpty(request.origAuthID))
            origAuthGrp.setOrigAuthID(request.origAuthID);
        if (Utils.isNotNullOrEmpty(request.origSTAN))
            origAuthGrp.setOrigSTAN(request.origSTAN);
        if (Utils.isNotNullOrEmpty(request.origRespCode))
            origAuthGrp.setOrigRespCode(request.origRespCode);
        if (Utils.isNotNullOrEmpty(request.origLocalDateTime))
            origAuthGrp.setOrigLocalDateTime(request.origLocalDateTime);
        if (Utils.isNotNullOrEmpty(request.origTranDateTime))
            origAuthGrp.setOrigTranDateTime(request.origTranDateTime);
        return Utils.valueOrNothing(origAuthGrp);
    }

    public List<AddtlAmtGrp> getAddtlAmtGrp() {
//        if (request.addtlAmtInfo == null) return null;
        List<AddtlAmtGrp> list = new ArrayList<>();
        if (Utils.isNotNullOrEmpty(request.firstAuthAmt))
            if (!request.firstAuthAmt.equals(BD_ZERO))
                list.add(getAddtlAmtGrp(request, request.firstAuthAmt, AddAmtTypeType.FIRST_AUTH_AMT));

        if (Utils.isNotNullOrEmpty(request.firstAuthAmt))
            if (!request.totalAuthAmt.equals(BD_ZERO))
                list.add(getAddtlAmtGrp(request, request.totalAuthAmt, AddAmtTypeType.TOTAL_AUTH_AMT));

        if (Utils.isNotNullOrEmpty(request.addtlAmtType) && Utils.isNotNullOrEmpty(request.addtlAmtType.split(",")[0]))
            if (!request.addtlAmt.split(",")[0].equals(BD_ZERO) && (AddAmtTypeType.fromValue(request.addtlAmtType.split(",")[0]) == AddAmtTypeType.HLTCARE))
                list.add(getAddtlAmtGrp(request, new BigDecimal(request.addtlAmt.split(",")[0]), AddAmtTypeType.HLTCARE));

        if (Utils.isNotNullOrEmpty(request.addtlAmtType) && Utils.isNotNullOrEmpty(request.addtlAmtType.split(",")[1]))
            if (!request.addtlAmt.split(",")[1].equals(BD_ZERO) && (AddAmtTypeType.fromValue(request.addtlAmtType.split(",")[1]) == AddAmtTypeType.RX))
                list.add(getAddtlAmtGrp(request, new BigDecimal(request.addtlAmt.split(",")[1]), AddAmtTypeType.RX));

        if (Utils.isNotNullOrEmpty(request.partAuthrztnApprvlCapablt)) {
            if (EnumAllowPartialAuth.fromValue(request.partAuthrztnApprvlCapablt) != EnumAllowPartialAuth.NotSet) {
                AddtlAmtGrp addtlAmtGrp = new AddtlAmtGrp();
                addtlAmtGrp.setPartAuthrztnApprvlCapablt(String.valueOf(request.partAuthrztnApprvlCapablt));
                list.add(addtlAmtGrp);
            }
        }
        return Utils.valueOrNothing(list);
    }

    AddtlAmtGrp getAddtlAmtGrp(final Request request, BigDecimal val, AddAmtTypeType type) {
        AddtlAmtGrp addtlAmtGrp = new AddtlAmtGrp();
        addtlAmtGrp.setAddAmtType(type);
        addtlAmtGrp.setAddAmt(Utils.formatAmount(val.toString()));
        addtlAmtGrp.setAddAmtCrncy(request.txnCrncy);
        return addtlAmtGrp;
    }

    public PINGrp getPinGrp() {
        PINGrp pinGrp = new PINGrp();
        pinGrp.setPINData(String.valueOf(request.pinData));
        pinGrp.setKeySerialNumData(String.valueOf(request.keySerialNumData));
        return pinGrp;
    }

    protected String getXmlPayload() {
        GMFMessageVariants gmfMessageVariants;
        return "";
    }
}
