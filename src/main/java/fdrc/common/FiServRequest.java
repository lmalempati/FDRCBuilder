package fdrc.common;
/*
Check if it is possible first
To call methods that populate common grp, card grp and so on and then get the xml payload the way cardknox does it.
*  */

import com.fiserv.merchant.gmfv10.*;
import fdrc.base.Constants;
import fdrc.base.Request;
import fdrc.types.CardCaptCapType;
import fdrc.types.EncrptTypeType;
import fdrc.types.EnumAllowPartialAuth;
import fdrc.types.MOTOIndType;
import fdrc.utils.Utils;

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
        if (Utils.isNotNullOrEmpty(request.pymtType))
            cmnGrp.setPymtType(Utils.toEnum(PymtTypeType.class, request.pymtType)); // PymtTypeType.CREDIT
        if (Utils.isNotNullOrEmpty(request.reversalInd))
            cmnGrp.setReversalInd(Utils.toEnum(ReversalIndType.class, request.reversalInd));
        /* The type of transaction being performed. */
        cmnGrp.setTxnType(Utils.toEnum(TxnTypeType.class, request.txnType)); //TxnTypeType.SALE
        /* The local date and time in which the transaction was performed. */
        cmnGrp.setLocalDateTime(Utils.getLocalDateTime());
        /* The transmission date and time of the transaction (in GMT/UCT). */
        cmnGrp.setTrnmsnDateTime(Utils.getUTCDateTime());
        /* A number assigned by the merchant to uniquely reference the transaction.
         * This number must be unique within a day per Merchant ID per Terminal ID. */
        cmnGrp.setSTAN(Utils.getSTAN());
        /* A number assigned by the merchant to uniquely reference a set of transactions. */
        if (Utils.isNotNullOrEmpty(request.refNum))
            cmnGrp.setRefNum(request.refNum); // "20200101012"
        else
            cmnGrp.setRefNum(Utils.getOrderNum()); // "20200101012"
        /* A number assigned by the merchant to uniquely reference a transaction order sequence. */
        // ToDo, completions should have ordernum, need to validate?
        if (Utils.isNotNullOrEmpty(request.orderNum))
            cmnGrp.setOrderNum(request.orderNum);
        else
            cmnGrp.setOrderNum(Utils.getOrderNum());
        /* An ID assigned by Fiserv, for the Third Party Processor or
         * Software Vendor that generated the transaction. */
        cmnGrp.setTPPID(Constants.REQUEST_TPPID); // ToDo, get from req
        /* A unique ID assigned to a terminal. */
        cmnGrp.setTermID(Constants.REQUEST_TERMID); // ToDo, get from req

        /* A unique ID assigned by Fiserv, to identify the Merchant. */
        if (Utils.isNotNullOrEmpty(request.merchantMID)) {
            cmnGrp.setMerchID(request.merchantMID); // ToDo, get from req
            RequestUtils.merchantID = request.merchantMID;
        } else {
            cmnGrp.setMerchID(Constants.REQUEST_MERCHID); // ToDo, get from req
        }

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

        if (Utils.isNotNullOrEmpty(request.cardCaptCap)) // todo: this can't be on request as we need to derive it..
            cmnGrp.setCardCaptCap(Utils.toEnum(CardCaptCapType.class, request.cardCaptCap).val); //1
        /* Indicates Group ID. */
        cmnGrp.setGroupID(Constants.REQUEST_GROUPID);
        if (Utils.isNotNullOrEmpty(request.merchCatCode))
            cmnGrp.setMerchCatCode(request.merchCatCode);
        if (Utils.isNotNullOrEmpty(request.refundType))
            cmnGrp.setRefundType(Utils.toEnum(RefundTypeType.class, request.refundType));
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
        if (!Utils.isNotNullOrEmpty(request.encrptType))
            if (Utils.isNotNullOrEmpty(request.acctNum))
                cardGrp.setAcctNum(request.acctNum);
        if (Utils.isNotNullOrEmpty(request.cardExpiryDate))
            cardGrp.setCardExpiryDate(request.cardExpiryDate); //20160430
        if (Utils.isNotNullOrEmpty(request.ccvInd))
            cardGrp.setCCVInd(Utils.toEnum(CCVIndType.class, request.ccvInd)); // CCVIndType.PRVDED
        if (Utils.isNotNullOrEmpty(request.ccvData))
            cardGrp.setCCVData(request.ccvData); // "123"
        if (Utils.isNotNullOrEmpty(request.cardType))
            cardGrp.setCardType(Utils.toEnum(CardTypeType.class, request.cardType));
        if (Utils.isNotNullOrEmpty(request.avsResultCode))
            cardGrp.setAVSResultCode(request.avsResultCode);
        if (Utils.isNotNullOrEmpty(request.ccvResultCode))
            cardGrp.setCCVResultCode(Utils.toEnum(CCVResultCodeType.class, request.ccvResultCode));
        cardGrp.setTrack2Data(request.track2Data);
        return Utils.valueOrNothing(cardGrp);
    }

    public VisaGrp getVisaGrp() {
        VisaGrp visaGrp = new VisaGrp();
        if (Utils.isNotNullOrEmpty(request.aci))
            visaGrp.setACI(request.aci.toUpperCase());
        if (Utils.isNotNullOrEmpty(request.visaBID))
            visaGrp.setVisaBID(request.visaBID); // ToDo
        if (Utils.toEnum(TxnTypeType.class, request.txnType) != TxnTypeType.AUTHORIZATION)
            visaGrp.setVisaAUAR(request.visaAUAR);
        /*Visa TAX Amount capability indicator value*/
        if (Utils.isNotNullOrEmpty(request.transID))
            visaGrp.setTransID(request.transID);
        if (Utils.isNotNullOrEmpty(request.taxAmtCapablt))
            visaGrp.setTaxAmtCapablt(request.taxAmtCapablt);
        if (Utils.isNotNullOrEmpty(request.cardLevelResult) && !Utils.isNotNullOrEmpty(request.reversalInd))
            visaGrp.setCardLevelResult(request.cardLevelResult);
        if (Utils.isNotNullOrEmpty(request.transID))
            visaGrp.setTransID(request.transID);
        if (Utils.isNotNullOrEmpty(request.spendQInd))
            visaGrp.setSpendQInd(request.spendQInd);

        return Utils.valueOrNothing(visaGrp);
    }

    public SecrTxnGrp getSecrTxnGrp() {
        SecrTxnGrp secrTxnGrp = new SecrTxnGrp();
        secrTxnGrp.setCAVVResultCode(request.cavvResultCode);
        return Utils.valueOrNothing(secrTxnGrp);
    }

    public MCGrp getMasterCardGrp() {
        MCGrp mcGrp = new MCGrp();
        if (Utils.isNotNullOrEmpty(request.finAuthInd))
            mcGrp.setFinAuthInd(String.valueOf(request.finAuthInd));
        if (Utils.isNotNullOrEmpty(request.banknetData))
            mcGrp.setBanknetData(request.banknetData); // as per
        if (Utils.isNotNullOrEmpty(request.healthcareAmt))
            if (!request.healthcareAmt.equals(BD_ZERO))
                mcGrp.setMCMSDI(MCMSDIType.HEALTHCARE);

        if (Utils.isNotNullOrEmpty(request.mcACI)) {
            mcGrp.setMCACI(request.mcACI);
        }
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
        // todo - MotoInd?
        if (Utils.isNotNullOrEmpty(request.motoInd))
            dsGrp.setMOTOInd(Utils.toEnum(MOTOIndType.class, request.motoInd).val);

        return Utils.valueOrNothing(dsGrp);
    }

    public AmexGrp getAmexGrp() {
        AmexGrp amexGrp = new AmexGrp();
        if (Utils.isNotNullOrEmpty(request.amexTranID))
            amexGrp.setAmExTranID(request.amexTranID);
        return Utils.valueOrNothing(amexGrp);
    }

    public AltMerchNameAndAddrGrp getAltMerchNameAndAddrGrp() {
        AltMerchNameAndAddrGrp altMerchNameAndAddrGrp = new AltMerchNameAndAddrGrp();
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
        return Utils.valueOrNothing(altMerchNameAndAddrGrp);
    }

    public CustInfoGrp getCustInfoGrp() {
        /* Populate values for CustInfoGrp Group */
        CustInfoGrp custInfoGrp = new CustInfoGrp();
        /*Billing address of the card holder*/
        if (Utils.isNotNullOrEmpty(request.avsBillingAddr))
            custInfoGrp.setAVSBillingAddr(request.avsBillingAddr);
        /*Postal ZIP Code of the card holder*/
        if (Utils.isNotNullOrEmpty(request.avsBillingPostalCode))
            custInfoGrp.setAVSBillingPostalCode(request.avsBillingPostalCode);
        return Utils.valueOrNothing(custInfoGrp);
    }

    public OrigAuthGrp getOrigAuthGrp() {
        // check if origAuthGrp is required
        if (!RequestUtils.origAuthGrpRequired(request)) return null;
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

        if (Utils.isNotNullOrEmpty(request.totalAuthAmt))
            if (!request.totalAuthAmt.equals(BD_ZERO))
                list.add(getAddtlAmtGrp(request, request.totalAuthAmt, AddAmtTypeType.TOTAL_AUTH_AMT));

        if (Utils.isNotNullOrEmpty(request.cashbackAmt))
            if (!request.cashbackAmt.equals(BD_ZERO))
                list.add(getAddtlAmtGrp(request, request.cashbackAmt, AddAmtTypeType.CASHBACK));

        if (Utils.isNotNullOrEmpty(request.healthcareAmt))
            if (!request.healthcareAmt.equals(BD_ZERO))
                list.add(getAddtlAmtGrp(request, request.healthcareAmt, AddAmtTypeType.HLTCARE));

        if (Utils.isNotNullOrEmpty(request.rxAmt))
            if (!request.rxAmt.equals(BD_ZERO))
                list.add(getAddtlAmtGrp(request, request.rxAmt, AddAmtTypeType.RX));

        if (Utils.isNotNullOrEmpty(request.partAuthrztnApprvlCapablt)) {
            if (Utils.toEnum(EnumAllowPartialAuth.class, request.partAuthrztnApprvlCapablt) != EnumAllowPartialAuth.NotSet) {
                AddtlAmtGrp addtlAmtGrp = new AddtlAmtGrp();
                addtlAmtGrp.setPartAuthrztnApprvlCapablt(String.valueOf(request.partAuthrztnApprvlCapablt));
                list.add(addtlAmtGrp);
            }
        }
//        // todo: as per sample trxns, voids are expecting this. to know why?
//        if (Utils.isNotNullOrEmpty(request.reversalInd) && Utils.getEnumValue(ReversalIndType.class, request.reversalInd) == ReversalIndType.VOID) {
//            AddtlAmtGrp addtlAmtGrp = new AddtlAmtGrp();
//            addtlAmtGrp.setAddAmt("100");
//            addtlAmtGrp.setAddAmtCrncy("840");
//            addtlAmtGrp.setAddAmtType(AddAmtTypeType.TOTAL_AUTH_AMT);
//            list.add(addtlAmtGrp);
//        }
        return Utils.valueOrNothing(list);
    }

    AddtlAmtGrp getAddtlAmtGrp(final Request request, final BigDecimal val, final AddAmtTypeType type) {
        AddtlAmtGrp addtlAmtGrp = new AddtlAmtGrp();
        addtlAmtGrp.setAddAmtType(type);
        addtlAmtGrp.setAddAmt(Utils.formatAmount(val.toString()));
        addtlAmtGrp.setAddAmtCrncy(request.txnCrncy);
        return addtlAmtGrp;
    }

    public PINGrp getPINGrp() {
        PINGrp pinGrp = new PINGrp();
        pinGrp.setPINData(String.valueOf(request.pinData));
        pinGrp.setKeySerialNumData(String.valueOf(request.keySerialNumData));
        return pinGrp;
    }

    public EbtGrp getEBTGrp(Request request) {
        EbtGrp ebtGrp = new EbtGrp();
        if (Utils.isNotNullOrEmpty(request.ebtType))
            ebtGrp.setEBTType(Utils.toEnum(EBTTypeType.class, request.ebtType));


//        if (Utils.toEnum(EBTTypeType.class, request.ebtType)  )
        return Utils.valueOrNothing(ebtGrp);
    }

    public TAGrp getTAGrp(Request request) {
        TAGrp taGrp = new TAGrp();
        if (Utils.isNotNullOrEmpty(request.sctyLvl))
            taGrp.setSctyLvl(Utils.toEnum(SctyLvlType.class, request.sctyLvl));
        if (Utils.isNotNullOrEmpty(request.encrptType))
            taGrp.setEncrptType(Utils.toEnum(EncrptTypeType.class, request.encrptType).toString());
        if (Utils.isNotNullOrEmpty(request.encrptTrgt))
            taGrp.setEncrptTrgt(Utils.toEnum(EncrptTrgtType.class, request.encrptTrgt));
        if (Utils.isNotNullOrEmpty(request.keyID))
            taGrp.setKeyID(request.keyID);
        if (Utils.isNotNullOrEmpty(request.encrptBlock))
            taGrp.setEncrptBlock(request.encrptBlock);
        if (Utils.isNotNullOrEmpty(request.tknType))
            taGrp.setTknType(request.tknType);
        if (Utils.isNotNullOrEmpty(request.deviceType))
            taGrp.setDeviceType(request.deviceType);
        return Utils.valueOrNothing(taGrp);
    }

    protected String getXmlPayload() {
        GMFMessageVariants gmfMessageVariants;
        return "";
    }
}
