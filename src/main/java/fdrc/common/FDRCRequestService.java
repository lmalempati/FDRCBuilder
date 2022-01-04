package fdrc.common;
/*
Check if it is possible first
To call methods that populate common grp, card grp and so on and then get the xml payload the way cardknox does it.
*  */

import com.fiserv.merchant.gmfv10.*;
import fdrc.model.RCRequest;
import fdrc.types.CardCaptCapType;
import fdrc.types.EncrptTypeType;
import fdrc.types.EnumAllowPartialAuth;
import fdrc.types.MOTOIndType;
import fdrc.utils.RequestUtils;
import fdrc.utils.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FDRCRequestService { // todo name

    public static final BigDecimal BD_ZERO = new BigDecimal(0);
    private final RCRequest RCRequest;

    public FDRCRequestService(RCRequest RCRequestPassedIn) {
        // request already checked for null
        this.RCRequest = RCRequestPassedIn;
    }

    public CommonGrp getCommonGrp() {
        CommonGrp cmnGrp = new CommonGrp();
        /* The payment type of the transaction. */
        if (Utils.isNotNullOrEmpty(RCRequest.pymtType))
            cmnGrp.setPymtType(Utils.toEnum(PymtTypeType.class, RCRequest.pymtType)); // PymtTypeType.CREDIT
        if (Utils.isNotNullOrEmpty(RCRequest.reversalInd))
            cmnGrp.setReversalInd(Utils.toEnum(ReversalIndType.class, RCRequest.reversalInd));
        /* The type of transaction being performed. */
        cmnGrp.setTxnType(Utils.toEnum(TxnTypeType.class, RCRequest.txnType)); //TxnTypeType.SALE
        /* The local date and time in which the transaction was performed. */
        cmnGrp.setLocalDateTime(Utils.getLocalDateTime());
        /* The transmission date and time of the transaction (in GMT/UCT). */
        cmnGrp.setTrnmsnDateTime(Utils.getUTCDateTime());
        RCRequest.trnsmitDateTime = cmnGrp.getTrnmsnDateTime();
        /* A number assigned by the merchant to uniquely reference the transaction.
         * This number must be unique within a day per Merchant ID per Terminal ID. */
        cmnGrp.setSTAN(Utils.getSTAN());
        /* A number assigned by the merchant to uniquely reference a set of transactions. */
        if (Utils.isNotNullOrEmpty(RCRequest.refNum))
            cmnGrp.setRefNum(RCRequest.refNum); // "20200101012"
        else if (Utils.toEnum(TxnTypeType.class, RCRequest.txnType) != TxnTypeType.HOST_TOTALS)
            cmnGrp.setRefNum(Utils.getOrderRefNum()); // "20200101012"
        /* An ID assigned by Fiserv, for the Third Party Processor or
         * Software Vendor that generated the transaction. */
        cmnGrp.setTPPID(Constants.REQUEST_TPPID); // ToDo, get from req
        /* A unique ID assigned to a terminal. */
        cmnGrp.setTermID(Constants.REQUEST_TERMID); // ToDo, get from req

        /* A unique ID assigned by Fiserv, to identify the Merchant. */
        if (Utils.isNotNullOrEmpty(RCRequest.merchantMID)) {
            cmnGrp.setMerchID(RCRequest.merchantMID); // ToDo, get from req
            RequestUtils.merchantID = RCRequest.merchantMID;
        } else {
            cmnGrp.setMerchID(Constants.REQUEST_MERCHID); // ToDo, get from req
        }
        cmnGrp.setGroupID(Utils.isNotNullOrEmpty(RCRequest.groupID) ? RCRequest.groupID : Constants.REQUEST_GROUPID);
        // TATikenRequest don't need the following fields?
        if (Utils.toEnum(TxnTypeType.class, RCRequest.txnType) == TxnTypeType.TA_TOKEN_REQUEST) return cmnGrp;

        /* A number assigned by the merchant to uniquely reference a transaction order sequence. */
        // ToDo, completions should have ordernum, need to validate?
        if (Utils.toEnum(TxnTypeType.class, RCRequest.txnType) != TxnTypeType.HOST_TOTALS)
            cmnGrp.setOrderNum(Utils.isNotNullOrEmpty(RCRequest.orderNum) ? RCRequest.orderNum : Utils.getOrderRefNum());
        /* An identifier used to indicate the terminal’s account number entry mode
         * and authentication capability via the Point-of-Service. */
        cmnGrp.setPOSEntryMode(RCRequest.posEntryMode); //010// 011
        /* An identifier used to indicate the authorization conditions at the Point-of-Service (POS). */
        cmnGrp.setPOSCondCode(RCRequest.posCondCode); //08, 59
        /* An identifier used to describe the type of terminal being used for the transaction. */
        cmnGrp.setTermCatCode(RCRequest.termCatCode); //00, 01
        /* An identifier used to indicate the entry mode capability of the terminal. */
        cmnGrp.setTermEntryCapablt(RCRequest.termEntryCapablt); //00 04
        /* The amount of the transaction. This may be an authorization amount,
         * adjustment amount or a reversal amount based on the type of transaction.
         * It is inclusive of all additional amounts.
         * It is submitted in the currency represented by the Transaction Currency field.
         * The field is overwritten in the response for a partial authorization. */
        if (Utils.isNotNullOrEmpty(RCRequest.txnAmt))
            cmnGrp.setTxnAmt(Utils.formatAmount(RCRequest.txnAmt));

        /* The numeric currency of the Transaction Amount. */
        cmnGrp.setTxnCrncy(RCRequest.txnCrncy); //"840"
        /* An indicator that describes the location of the terminal. */
        cmnGrp.setTermLocInd(RCRequest.termLocInd); // 0
        /* Indicates whether or not the terminal has the capability to capture the card data. */
        cmnGrp.setCardCaptCap(RCRequest.cardCaptCap); //1

        if (Utils.isNotNullOrEmpty(RCRequest.cardCaptCap)) // todo: this can't be on request as we need to derive it..
            cmnGrp.setCardCaptCap(Utils.toEnum(CardCaptCapType.class, RCRequest.cardCaptCap).val); //1
        /* Indicates Group ID. */
        if (Utils.isNotNullOrEmpty(RCRequest.merchCatCode))
            cmnGrp.setMerchCatCode(RCRequest.merchCatCode);
        if (Utils.isNotNullOrEmpty(RCRequest.refundType))
            cmnGrp.setRefundType(Utils.toEnum(RefundTypeType.class, RCRequest.refundType));
        return cmnGrp;
    }

    public EcommGrp getEcommGrp() {
        EcommGrp ecommGrp = new EcommGrp();
//        ecommGrp.setEcommTxnInd("01");
        if (Utils.isNotNullOrEmpty(RCRequest.custSvcPhoneNumber))
            ecommGrp.setCustSvcPhoneNumber(RCRequest.custSvcPhoneNumber);
        return Utils.valueOrNothing(ecommGrp);
    }

    public CardGrp getCardGrp() {
        if (RCRequest == null) return null;
        CardGrp cardGrp = new CardGrp();
        if (!Utils.isNotNullOrEmpty(RCRequest.encrptType) && !Utils.isNotNullOrEmpty(RCRequest.tkn))
            if (Utils.isNotNullOrEmpty(RCRequest.acctNum))
                cardGrp.setAcctNum(RCRequest.acctNum);
        if (Utils.isNotNullOrEmpty(RCRequest.cardExpiryDate))
            cardGrp.setCardExpiryDate(RCRequest.cardExpiryDate); //20160430
        if (Utils.isNotNullOrEmpty(RCRequest.ccvInd))
            cardGrp.setCCVInd(Utils.toEnum(CCVIndType.class, RCRequest.ccvInd)); // CCVIndType.PRVDED
        if (Utils.isNotNullOrEmpty(RCRequest.ccvData))
            cardGrp.setCCVData(RCRequest.ccvData); // "123"
        if (Utils.isNotNullOrEmpty(RCRequest.cardType))
            cardGrp.setCardType(Utils.toEnum(CardTypeType.class, RCRequest.cardType));
        if (Utils.isNotNullOrEmpty(RCRequest.avsResultCode))
            cardGrp.setAVSResultCode(RCRequest.avsResultCode);
        if (Utils.isNotNullOrEmpty(RCRequest.ccvResultCode))
            cardGrp.setCCVResultCode(Utils.toEnum(CCVResultCodeType.class, RCRequest.ccvResultCode));

        if (Utils.isNotNullOrEmpty(RCRequest.encrptTrgt)
                && (Utils.toEnum(EncrptTrgtType.class, RCRequest.encrptTrgt) == EncrptTrgtType.TRACK_2))
            cardGrp.setTrack2Data(null);
        else
            cardGrp.setTrack2Data(RCRequest.track2Data);
        return Utils.valueOrNothing(cardGrp);
    }

    public VisaGrp getVisaGrp() {
        VisaGrp visaGrp = new VisaGrp();
        if (Utils.isNotNullOrEmpty(RCRequest.aci))
            visaGrp.setACI(RCRequest.aci.toUpperCase());
        if (Utils.isNotNullOrEmpty(RCRequest.visaBID))
            visaGrp.setVisaBID(RCRequest.visaBID); // ToDo
        if (Utils.toEnum(TxnTypeType.class, RCRequest.txnType) != TxnTypeType.AUTHORIZATION)
            visaGrp.setVisaAUAR(RCRequest.visaAUAR);
        /*Visa TAX Amount capability indicator value*/
        if (Utils.isNotNullOrEmpty(RCRequest.transID))
            visaGrp.setTransID(RCRequest.transID);
        if (Utils.isNotNullOrEmpty(RCRequest.taxAmtCapablt))
            visaGrp.setTaxAmtCapablt(RCRequest.taxAmtCapablt);
        if (Utils.isNotNullOrEmpty(RCRequest.cardLevelResult) && !Utils.isNotNullOrEmpty(RCRequest.reversalInd))
            visaGrp.setCardLevelResult(RCRequest.cardLevelResult);
        if (Utils.isNotNullOrEmpty(RCRequest.transID))
            visaGrp.setTransID(RCRequest.transID);
        if (Utils.isNotNullOrEmpty(RCRequest.spendQInd))
            visaGrp.setSpendQInd(RCRequest.spendQInd);

        return Utils.valueOrNothing(visaGrp);
    }

    public SecrTxnGrp getSecrTxnGrp() {
        SecrTxnGrp secrTxnGrp = new SecrTxnGrp();
        secrTxnGrp.setCAVVResultCode(RCRequest.cavvResultCode);
        return Utils.valueOrNothing(secrTxnGrp);
    }

    public MCGrp getMasterCardGrp() {
        MCGrp mcGrp = new MCGrp();
        if (Utils.isNotNullOrEmpty(RCRequest.finAuthInd))
            mcGrp.setFinAuthInd(String.valueOf(RCRequest.finAuthInd));
        if (Utils.isNotNullOrEmpty(RCRequest.banknetData))
            mcGrp.setBanknetData(RCRequest.banknetData); // as per
        if (Utils.isNotNullOrEmpty(RCRequest.healthcareAmt))
            if (!RCRequest.healthcareAmt.equals(BD_ZERO))
                mcGrp.setMCMSDI(MCMSDIType.HEALTHCARE);

        if (Utils.isNotNullOrEmpty(RCRequest.mcACI)) {
            mcGrp.setMCACI(RCRequest.mcACI);
        }
        return Utils.valueOrNothing(mcGrp);
    }

    public DSGrp getDiscoverGrp() {
        DSGrp dsGrp = new DSGrp();
        if (Utils.isNotNullOrEmpty(RCRequest.discProcCode))
            dsGrp.setDiscProcCode(RCRequest.discProcCode);
        if (Utils.isNotNullOrEmpty(RCRequest.discPOSEntry))
            dsGrp.setDiscPOSEntry(RCRequest.discPOSEntry);
        if (Utils.isNotNullOrEmpty(RCRequest.discRespCode))
            dsGrp.setDiscRespCode(RCRequest.discRespCode);
        if (Utils.isNotNullOrEmpty(RCRequest.discPOSData))
            dsGrp.setDiscPOSData(RCRequest.discPOSData);
        if (Utils.isNotNullOrEmpty(RCRequest.discTransQualifier))
            dsGrp.setDiscTransQualifier(RCRequest.discTransQualifier);
        if (Utils.isNotNullOrEmpty(RCRequest.discNRID))
            dsGrp.setDiscNRID(RCRequest.discNRID);
        // todo - MotoInd?
        if (Utils.isNotNullOrEmpty(RCRequest.motoInd))
            dsGrp.setMOTOInd(Utils.toEnum(MOTOIndType.class, RCRequest.motoInd).val);

        return Utils.valueOrNothing(dsGrp);
    }

    public AmexGrp getAmexGrp() {
        AmexGrp amexGrp = new AmexGrp();
        if (Utils.isNotNullOrEmpty(RCRequest.amexTranID))
            amexGrp.setAmExTranID(RCRequest.amexTranID);
        return Utils.valueOrNothing(amexGrp);
    }

    public AltMerchNameAndAddrGrp getAltMerchNameAndAddrGrp() {
        AltMerchNameAndAddrGrp altMerchNameAndAddrGrp = new AltMerchNameAndAddrGrp();
        altMerchNameAndAddrGrp = new AltMerchNameAndAddrGrp();
        if (Utils.isNotNullOrEmpty(RCRequest.merchName))
            altMerchNameAndAddrGrp.setMerchName(RCRequest.merchName);
        if (Utils.isNotNullOrEmpty(RCRequest.merchAddr))
            altMerchNameAndAddrGrp.setMerchAddr(RCRequest.merchAddr);
        if (Utils.isNotNullOrEmpty(RCRequest.merchCity))
            altMerchNameAndAddrGrp.setMerchCity(RCRequest.merchCity);
        if (Utils.isNotNullOrEmpty(RCRequest.merchState))
            altMerchNameAndAddrGrp.setMerchState(RCRequest.merchState);
        if (Utils.isNotNullOrEmpty(RCRequest.merchCtry))
            altMerchNameAndAddrGrp.setMerchCtry(RCRequest.merchCtry);
        if (Utils.isNotNullOrEmpty(RCRequest.merchPostalCode))
            altMerchNameAndAddrGrp.setMerchPostalCode(RCRequest.merchPostalCode);
        return Utils.valueOrNothing(altMerchNameAndAddrGrp);
    }

    public CustInfoGrp getCustInfoGrp() {
        /* Populate values for CustInfoGrp Group */
        CustInfoGrp custInfoGrp = new CustInfoGrp();
        /*Billing address of the card holder*/
        if (Utils.isNotNullOrEmpty(RCRequest.avsBillingAddr))
            custInfoGrp.setAVSBillingAddr(RCRequest.avsBillingAddr);
        /*Postal ZIP Code of the card holder*/
        if (Utils.isNotNullOrEmpty(RCRequest.avsBillingPostalCode))
            custInfoGrp.setAVSBillingPostalCode(RCRequest.avsBillingPostalCode);
        return Utils.valueOrNothing(custInfoGrp);
    }

    public OrigAuthGrp getOrigAuthGrp() {
        // check if origAuthGrp is required
        if (!RequestUtils.origAuthGrpRequired(RCRequest)) return null;
        OrigAuthGrp origAuthGrp = new OrigAuthGrp();
        if (Utils.isNotNullOrEmpty(RCRequest.origAuthID))
            origAuthGrp.setOrigAuthID(RCRequest.origAuthID);
        if (Utils.isNotNullOrEmpty(RCRequest.origSTAN))
            origAuthGrp.setOrigSTAN(RCRequest.origSTAN);
        if (Utils.isNotNullOrEmpty(RCRequest.origRespCode))
            origAuthGrp.setOrigRespCode(RCRequest.origRespCode);
        if (Utils.isNotNullOrEmpty(RCRequest.origLocalDateTime))
            origAuthGrp.setOrigLocalDateTime(RCRequest.origLocalDateTime);
        if (Utils.isNotNullOrEmpty(RCRequest.origTranDateTime))
            origAuthGrp.setOrigTranDateTime(RCRequest.origTranDateTime);
        return Utils.valueOrNothing(origAuthGrp);
    }

    public List<AddtlAmtGrp> getAddtlAmtGrp() {
//        if (request.addtlAmtInfo == null) return null;
        List<AddtlAmtGrp> list = new ArrayList<>();
        if (Utils.isNotNullOrEmpty(RCRequest.firstAuthAmt))
            if (!RCRequest.firstAuthAmt.equals(BD_ZERO))
                list.add(getAddtlAmtGrp(RCRequest, RCRequest.firstAuthAmt, AddAmtTypeType.FIRST_AUTH_AMT));

        if (Utils.isNotNullOrEmpty(RCRequest.totalAuthAmt))
            if (!RCRequest.totalAuthAmt.equals(BD_ZERO))
                list.add(getAddtlAmtGrp(RCRequest, RCRequest.totalAuthAmt, AddAmtTypeType.TOTAL_AUTH_AMT));

        if (Utils.isNotNullOrEmpty(RCRequest.cashbackAmt))
            if (!RCRequest.cashbackAmt.equals(BD_ZERO))
                list.add(getAddtlAmtGrp(RCRequest, RCRequest.cashbackAmt, AddAmtTypeType.CASHBACK));

        if (Utils.isNotNullOrEmpty(RCRequest.healthcareAmt))
            if (!RCRequest.healthcareAmt.equals(BD_ZERO))
                list.add(getAddtlAmtGrp(RCRequest, RCRequest.healthcareAmt, AddAmtTypeType.HLTCARE));

        if (Utils.isNotNullOrEmpty(RCRequest.rxAmt))
            if (!RCRequest.rxAmt.equals(BD_ZERO))
                list.add(getAddtlAmtGrp(RCRequest, RCRequest.rxAmt, AddAmtTypeType.RX));

        if (Utils.isNotNullOrEmpty(RCRequest.partAuthrztnApprvlCapablt)) {
            if (Utils.toEnum(EnumAllowPartialAuth.class, RCRequest.partAuthrztnApprvlCapablt) != EnumAllowPartialAuth.NotSet) {
                AddtlAmtGrp addtlAmtGrp = new AddtlAmtGrp();
                addtlAmtGrp.setPartAuthrztnApprvlCapablt(String.valueOf(RCRequest.partAuthrztnApprvlCapablt));
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

    AddtlAmtGrp getAddtlAmtGrp(final RCRequest rcRequest, final String val, final AddAmtTypeType type) {
        AddtlAmtGrp addtlAmtGrp = new AddtlAmtGrp();
        addtlAmtGrp.setAddAmtType(type);
        addtlAmtGrp.setAddAmt(Utils.formatAmount(val));
        addtlAmtGrp.setAddAmtCrncy(rcRequest.txnCrncy);
        return addtlAmtGrp;
    }

    public PINGrp getPINGrp() {
        PINGrp pinGrp = new PINGrp();
        pinGrp.setPINData(String.valueOf(RCRequest.pinData));
        pinGrp.setKeySerialNumData(String.valueOf(RCRequest.keySerialNumData));
        return pinGrp;
    }

    public EbtGrp getEBTGrp(RCRequest rcRequest) {
        EbtGrp ebtGrp = new EbtGrp();
        if (Utils.isNotNullOrEmpty(rcRequest.ebtType))
            ebtGrp.setEBTType(Utils.toEnum(EBTTypeType.class, rcRequest.ebtType));

        EBTTypeType ebtType = null;
        if (Utils.isNotNullOrEmpty(rcRequest.merchFNSNum))
            ebtType = Utils.toEnum(EBTTypeType.class, rcRequest.ebtType);
        if (ebtType == EBTTypeType.SNAP || ebtType == EBTTypeType.E_WIC)
            ebtGrp.setMerchFNSNum(rcRequest.merchFNSNum);
        return Utils.valueOrNothing(ebtGrp);
    }

    public TAGrp getTAGrp(RCRequest rcRequest) {
        TAGrp taGrp = new TAGrp();
        if (Utils.isNotNullOrEmpty(rcRequest.sctyLvl))
            taGrp.setSctyLvl(Utils.toEnum(SctyLvlType.class, rcRequest.sctyLvl));
        if (Utils.isNotNullOrEmpty(rcRequest.encrptType))
            taGrp.setEncrptType(Utils.toEnum(EncrptTypeType.class, rcRequest.encrptType).toString());
        if (Utils.isNotNullOrEmpty(rcRequest.encrptTrgt))
            taGrp.setEncrptTrgt(Utils.toEnum(EncrptTrgtType.class, rcRequest.encrptTrgt));
        if (Utils.isNotNullOrEmpty(rcRequest.keyID))
            taGrp.setKeyID(rcRequest.keyID);
        if (Utils.isNotNullOrEmpty(rcRequest.encrptBlock))
            taGrp.setEncrptBlock(rcRequest.encrptBlock);
        if (Utils.isNotNullOrEmpty(rcRequest.tknType))
            taGrp.setTknType(rcRequest.tknType);
        if (Utils.isNotNullOrEmpty(rcRequest.deviceType))
            taGrp.setDeviceType(rcRequest.deviceType);
        if (Utils.isNotNullOrEmpty(rcRequest.tkn))
            taGrp.setTkn(rcRequest.tkn);
        return Utils.valueOrNothing(taGrp);
    }

    public HostTotGrp getHostTotGrp() {
        HostTotGrp hostTotGrp = new HostTotGrp();
        hostTotGrp.setTotReqDate(RCRequest.totReqDate); // CloseShift
        hostTotGrp.setPassword(RCRequest.password);
        hostTotGrp.setNetSettleAmt(RCRequest.netSettleAmt);

        return hostTotGrp;
    }
}