package fdrc.service;
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
import fdrc.utils.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FDRCRequestService { // todo name
    static final BigDecimal BD_ZERO = new BigDecimal(0);

    public static RCRequest getRcRequest() {
        return rcRequest;
    }

    private static RCRequest rcRequest = null;

    public FDRCRequestService(RCRequest RCRequestPassedIn) {
        // request already checked for null
        this.rcRequest = RCRequestPassedIn;
    }

    CommonGrp getCommonGrp() {
        CommonGrp cmnGrp = new CommonGrp();
        /* The payment type of the transaction. */
        if (Utils.isNotNullOrEmpty(rcRequest.pymtType))
            cmnGrp.setPymtType(Utils.toEnum(PymtTypeType.class, rcRequest.pymtType)); // PymtTypeType.CREDIT
        if (Utils.isNotNullOrEmpty(rcRequest.reversalInd))
            cmnGrp.setReversalInd(Utils.toEnum(ReversalIndType.class, rcRequest.reversalInd));
        /* The type of transaction being performed. */
        cmnGrp.setTxnType(Utils.toEnum(TxnTypeType.class, rcRequest.txnType)); //TxnTypeType.SALE
        /* The local date and time in which the transaction was performed. */
        if (Utils.isNotNullOrEmpty(rcRequest.localDateTime))
            cmnGrp.setLocalDateTime(rcRequest.localDateTime);
        else
            cmnGrp.setLocalDateTime(Utils.getLocalDateTime());
        /* The transmission date and time of the transaction (in GMT/UCT). */
        if (Utils.isNotNullOrEmpty(rcRequest.trnsmitDateTime))
            cmnGrp.setTrnmsnDateTime(rcRequest.trnsmitDateTime);
        else
            cmnGrp.setTrnmsnDateTime(Utils.getUTCDateTime());
        rcRequest.trnsmitDateTime = cmnGrp.getTrnmsnDateTime(); // ToDo: Can we remove this now?
        /* A number assigned by the merchant to uniquely reference the transaction.
         * This number must be unique within a day per Merchant ID per Terminal ID. */
        if (Utils.isNotNullOrEmpty(rcRequest.stan))
            cmnGrp.setSTAN(rcRequest.stan);
        else
            cmnGrp.setSTAN(Utils.getSTAN());
        /* A number assigned by the merchant to uniquely reference a set of transactions. */
        if (Utils.isNotNullOrEmpty(rcRequest.refNum))
            cmnGrp.setRefNum(rcRequest.refNum); // "20200101012"
        else if (Utils.toEnum(TxnTypeType.class, rcRequest.txnType) != TxnTypeType.HOST_TOTALS)
            cmnGrp.setRefNum(Utils.getOrderRefNum()); // "20200101012"
        /* A number assigned by the merchant to uniquely reference a transaction order sequence. */
        // ToDo, completions should have ordernum, need to validate?
        if (Utils.isNotNullOrEmpty(rcRequest.orderNum))
            cmnGrp.setOrderNum(rcRequest.orderNum); // "20200101012"
        else if (Utils.toEnum(TxnTypeType.class, rcRequest.txnType) != TxnTypeType.HOST_TOTALS)
            cmnGrp.setOrderNum(Utils.isNotNullOrEmpty(rcRequest.orderNum) ? rcRequest.orderNum : Utils.getOrderRefNum());
        /* An ID assigned by Fiserv, for the Third Party Processor or
         * Software Vendor that generated the transaction. */
        cmnGrp.setTPPID(rcRequest.tppID);
        /* A unique ID assigned to a terminal. */
        cmnGrp.setTermID(rcRequest.termID);

        /* A unique ID assigned by Fiserv, to identify the Merchant. */
        cmnGrp.setMerchID(rcRequest.merchantMID);
        cmnGrp.setGroupID(rcRequest.groupID);
        // TATikenRequest don't need the following fields?
        if (Utils.toEnum(TxnTypeType.class, rcRequest.txnType) == TxnTypeType.TA_TOKEN_REQUEST) return cmnGrp;

        /* An identifier used to indicate the terminal’s account number entry mode
         * and authentication capability via the Point-of-Service. */
        cmnGrp.setPOSEntryMode(rcRequest.posEntryMode); //010// 011
        /* An identifier used to indicate the authorization conditions at the Point-of-Service (POS). */
        cmnGrp.setPOSCondCode(rcRequest.posCondCode); //08, 59
        /* An identifier used to describe the type of terminal being used for the transaction. */
        cmnGrp.setTermCatCode(rcRequest.termCatCode); //00, 01
        /* An identifier used to indicate the entry mode capability of the terminal. */
        cmnGrp.setTermEntryCapablt(rcRequest.termEntryCapablt); //00 04
        /* The amount of the transaction. This may be an authorization amount,
         * adjustment amount or a reversal amount based on the type of transaction.
         * It is inclusive of all additional amounts.
         * It is submitted in the currency represented by the Transaction Currency field.
         * The field is overwritten in the response for a partial authorization. */
        if (Utils.isNotNullOrEmpty(rcRequest.txnAmt))
            cmnGrp.setTxnAmt(Utils.formatAmount(rcRequest.txnAmt));

        /* The numeric currency of the Transaction Amount. */
        cmnGrp.setTxnCrncy(rcRequest.txnCrncy); //"840"
        /* An indicator that describes the location of the terminal. */
        cmnGrp.setTermLocInd(rcRequest.termLocInd); // 0
        /* Indicates whether or not the terminal has the capability to capture the card data. */
        cmnGrp.setCardCaptCap(rcRequest.cardCaptCap); //1

        if (Utils.isNotNullOrEmpty(rcRequest.cardCaptCap)) // todo: this can't be on request as we need to derive it..
            cmnGrp.setCardCaptCap(Utils.toEnum(CardCaptCapType.class, rcRequest.cardCaptCap).val); //1
        /* Indicates Group ID. */
        if (Utils.isNotNullOrEmpty(rcRequest.merchCatCode))
            cmnGrp.setMerchCatCode(rcRequest.merchCatCode);
        if (Utils.isNotNullOrEmpty(rcRequest.refundType))
            cmnGrp.setRefundType(Utils.toEnum(RefundTypeType.class, rcRequest.refundType));
        if (Utils.isNotNullOrEmpty(rcRequest.digWltProgType))
            cmnGrp.setDigWltProgType(Utils.toEnum(DigWltProgTypeType.class, rcRequest.digWltProgType));
        if (Utils.isNotNullOrEmpty(rcRequest.mobileInd))
            cmnGrp.setMobileInd(Utils.toEnum(MobileIndType.class, rcRequest.mobileInd));
        if (Utils.isNotNullOrEmpty(rcRequest.plPOSDebitFlg))
            cmnGrp.setPLPOSDebitFlg(rcRequest.plPOSDebitFlg);
        return cmnGrp;
    }

    EcommGrp getEcommGrp() {
        EcommGrp ecommGrp = new EcommGrp();
//        ecommGrp.setEcommTxnInd("01");
        if (Utils.isNotNullOrEmpty(rcRequest.custSvcPhoneNumber))
            ecommGrp.setCustSvcPhoneNumber(rcRequest.custSvcPhoneNumber);
        return Utils.valueOrNothing(ecommGrp);
    }

    CardGrp getCardGrp() {
        if (rcRequest == null) return null;
        CardGrp cardGrp = new CardGrp();
        if (!Utils.isNotNullOrEmpty(rcRequest.encrptType) && !Utils.isNotNullOrEmpty(rcRequest.tkn))
            if (Utils.isNotNullOrEmpty(rcRequest.acctNum))
                cardGrp.setAcctNum(rcRequest.acctNum);
        if (Utils.isNotNullOrEmpty(rcRequest.cardExpiryDate))
            cardGrp.setCardExpiryDate(rcRequest.cardExpiryDate); //20160430
        if (Utils.isNotNullOrEmpty(rcRequest.ccvInd))
            cardGrp.setCCVInd(Utils.toEnum(CCVIndType.class, rcRequest.ccvInd)); // CCVIndType.PRVDED
        if (Utils.isNotNullOrEmpty(rcRequest.ccvData))
            cardGrp.setCCVData(rcRequest.ccvData); // "123"
        if (Utils.isNotNullOrEmpty(rcRequest.cardType))
            cardGrp.setCardType(Utils.toEnum(CardTypeType.class, rcRequest.cardType));
        if (Utils.isNotNullOrEmpty(rcRequest.avsResultCode))
            cardGrp.setAVSResultCode(rcRequest.avsResultCode);
        if (Utils.isNotNullOrEmpty(rcRequest.ccvResultCode))
            cardGrp.setCCVResultCode(Utils.toEnum(CCVResultCodeType.class, rcRequest.ccvResultCode));

        if (Utils.isNotNullOrEmpty(rcRequest.encrptTrgt)
                && (Utils.toEnum(EncrptTrgtType.class, rcRequest.encrptTrgt) == EncrptTrgtType.TRACK_2))
            cardGrp.setTrack2Data(null);
        else if (Utils.isNotNullOrEmpty(rcRequest.track2Data))
            cardGrp.setTrack2Data(rcRequest.track2Data);
        return Utils.valueOrNothing(cardGrp);
    }

    VisaGrp getVisaGrp() {
        VisaGrp visaGrp = new VisaGrp();
        if (Utils.isNotNullOrEmpty(rcRequest.aci))
            visaGrp.setACI(rcRequest.aci.toUpperCase());
        if (Utils.isNotNullOrEmpty(rcRequest.visaBID))
            visaGrp.setVisaBID(rcRequest.visaBID); // ToDo
        if (Utils.toEnum(TxnTypeType.class, rcRequest.txnType) != TxnTypeType.AUTHORIZATION)
            visaGrp.setVisaAUAR(rcRequest.visaAUAR);
        /*Visa TAX Amount capability indicator value*/
        if (Utils.isNotNullOrEmpty(rcRequest.transID))
            visaGrp.setTransID(rcRequest.transID);
        if (Utils.isNotNullOrEmpty(rcRequest.taxAmtCapablt))
            visaGrp.setTaxAmtCapablt(rcRequest.taxAmtCapablt);
        if (Utils.isNotNullOrEmpty(rcRequest.cardLevelResult) && !Utils.isNotNullOrEmpty(rcRequest.reversalInd))
            visaGrp.setCardLevelResult(rcRequest.cardLevelResult);
        if (Utils.isNotNullOrEmpty(rcRequest.transID))
            visaGrp.setTransID(rcRequest.transID);
        if (Utils.isNotNullOrEmpty(rcRequest.spendQInd))
            visaGrp.setSpendQInd(rcRequest.spendQInd);
        if (Utils.isNotNullOrEmpty(rcRequest.mrktSpecificDataInd)) {
            visaGrp.setMrktSpecificDataInd(Utils.toEnum(MrktSpecificDataIndType.class, rcRequest.mrktSpecificDataInd));
        }

        return Utils.valueOrNothing(visaGrp);
    }

    SecrTxnGrp getSecrTxnGrp() {
        SecrTxnGrp secrTxnGrp = new SecrTxnGrp();
        secrTxnGrp.setCAVVResultCode(rcRequest.cavvResultCode);
        return Utils.valueOrNothing(secrTxnGrp);
    }

    MCGrp getMasterCardGrp() {
        MCGrp mcGrp = new MCGrp();
        if (Utils.isNotNullOrEmpty(rcRequest.finAuthInd))
            mcGrp.setFinAuthInd(String.valueOf(rcRequest.finAuthInd));
        if (Utils.isNotNullOrEmpty(rcRequest.banknetData))
            mcGrp.setBanknetData(rcRequest.banknetData); // as per
        if (Utils.isNotNullOrEmpty(rcRequest.healthcareAmt))
            if (!rcRequest.healthcareAmt.equals(BD_ZERO))
                mcGrp.setMCMSDI(MCMSDIType.HEALTHCARE);

        if (Utils.isNotNullOrEmpty(rcRequest.mcACI)) {
            mcGrp.setMCACI(rcRequest.mcACI);
        }
        if (Utils.isNotNullOrEmpty(rcRequest.mcMSDI)) {
            mcGrp.setMCMSDI(Utils.toEnum(MCMSDIType.class, rcRequest.mcMSDI));
        }
        return Utils.valueOrNothing(mcGrp);
    }

    DSGrp getDiscoverGrp() {
        DSGrp dsGrp = new DSGrp();
        if (Utils.isNotNullOrEmpty(rcRequest.discProcCode))
            dsGrp.setDiscProcCode(rcRequest.discProcCode);
        if (Utils.isNotNullOrEmpty(rcRequest.discPOSEntry))
            dsGrp.setDiscPOSEntry(rcRequest.discPOSEntry);
        if (Utils.isNotNullOrEmpty(rcRequest.discRespCode))
            dsGrp.setDiscRespCode(rcRequest.discRespCode);
        if (Utils.isNotNullOrEmpty(rcRequest.discPOSData))
            dsGrp.setDiscPOSData(rcRequest.discPOSData);
        if (Utils.isNotNullOrEmpty(rcRequest.discTransQualifier))
            dsGrp.setDiscTransQualifier(rcRequest.discTransQualifier);
        if (Utils.isNotNullOrEmpty(rcRequest.discNRID))
            dsGrp.setDiscNRID(rcRequest.discNRID);
        // todo - MotoInd?
        if (Utils.isNotNullOrEmpty(rcRequest.motoInd))
            dsGrp.setMOTOInd(Utils.toEnum(MOTOIndType.class, rcRequest.motoInd).val);

        return Utils.valueOrNothing(dsGrp);
    }

    AmexGrp getAmexGrp() {
        AmexGrp amexGrp = new AmexGrp();
        if (Utils.isNotNullOrEmpty(rcRequest.amexTranID))
            amexGrp.setAmExTranID(rcRequest.amexTranID);
        if (Utils.isNotNullOrEmpty(rcRequest.amexPOSData))
            amexGrp.setAmExPOSData(rcRequest.amexPOSData);
        return Utils.valueOrNothing(amexGrp);
    }

    AltMerchNameAndAddrGrp getAltMerchNameAndAddrGrp() {
        AltMerchNameAndAddrGrp altMerchNameAndAddrGrp = new AltMerchNameAndAddrGrp();
        altMerchNameAndAddrGrp = new AltMerchNameAndAddrGrp();
        if (Utils.isNotNullOrEmpty(rcRequest.merchName))
            altMerchNameAndAddrGrp.setMerchName(rcRequest.merchName);
        if (Utils.isNotNullOrEmpty(rcRequest.merchAddr))
            altMerchNameAndAddrGrp.setMerchAddr(rcRequest.merchAddr);
        if (Utils.isNotNullOrEmpty(rcRequest.merchCity))
            altMerchNameAndAddrGrp.setMerchCity(rcRequest.merchCity);
        if (Utils.isNotNullOrEmpty(rcRequest.merchState))
            altMerchNameAndAddrGrp.setMerchState(rcRequest.merchState);
        if (Utils.isNotNullOrEmpty(rcRequest.merchCtry))
            altMerchNameAndAddrGrp.setMerchCtry(rcRequest.merchCtry);
        if (Utils.isNotNullOrEmpty(rcRequest.merchPostalCode))
            altMerchNameAndAddrGrp.setMerchPostalCode(rcRequest.merchPostalCode);
        return Utils.valueOrNothing(altMerchNameAndAddrGrp);
    }

    CustInfoGrp getCustInfoGrp() {
        /* Populate values for CustInfoGrp Group */
        CustInfoGrp custInfoGrp = new CustInfoGrp();
        /*Billing address of the card holder*/
        if (Utils.isNotNullOrEmpty(rcRequest.avsBillingAddr))
            custInfoGrp.setAVSBillingAddr(rcRequest.avsBillingAddr);
        /*Postal ZIP Code of the card holder*/
        if (Utils.isNotNullOrEmpty(rcRequest.avsBillingPostalCode))
            custInfoGrp.setAVSBillingPostalCode(rcRequest.avsBillingPostalCode);
        return Utils.valueOrNothing(custInfoGrp);
    }

    OrigAuthGrp getOrigAuthGrp() {
        // check if origAuthGrp is required
        if (!origAuthGrpRequired()) return null;
        OrigAuthGrp origAuthGrp = new OrigAuthGrp();
        if (Utils.isNotNullOrEmpty(rcRequest.origAuthID))
            origAuthGrp.setOrigAuthID(rcRequest.origAuthID);
        if (Utils.isNotNullOrEmpty(rcRequest.origSTAN))
            origAuthGrp.setOrigSTAN(rcRequest.origSTAN);
        if (Utils.isNotNullOrEmpty(rcRequest.origRespCode))
            origAuthGrp.setOrigRespCode(rcRequest.origRespCode);
        if (Utils.isNotNullOrEmpty(rcRequest.origLocalDateTime))
            origAuthGrp.setOrigLocalDateTime(rcRequest.origLocalDateTime);
        if (Utils.isNotNullOrEmpty(rcRequest.origTranDateTime))
            origAuthGrp.setOrigTranDateTime(rcRequest.origTranDateTime);
        return Utils.valueOrNothing(origAuthGrp);
    }

    List<AddtlAmtGrp> getAddtlAmtGrp() {
//        if (request.addtlAmtInfo == null) return null;
        List<AddtlAmtGrp> list = new ArrayList<>();
        if (Utils.isNotNullOrEmpty(rcRequest.firstAuthAmt))
            if (!rcRequest.firstAuthAmt.equals(BD_ZERO))
                list.add(getAddtlAmtGrp(rcRequest, rcRequest.firstAuthAmt, AddAmtTypeType.FIRST_AUTH_AMT));

        if (Utils.isNotNullOrEmpty(rcRequest.totalAuthAmt))
            if (!rcRequest.totalAuthAmt.equals(BD_ZERO))
                list.add(getAddtlAmtGrp(rcRequest, rcRequest.totalAuthAmt, AddAmtTypeType.TOTAL_AUTH_AMT));

        if (Utils.isNotNullOrEmpty(rcRequest.cashbackAmt))
            if (!rcRequest.cashbackAmt.equals(BD_ZERO))
                list.add(getAddtlAmtGrp(rcRequest, rcRequest.cashbackAmt, AddAmtTypeType.CASHBACK));

        if (Utils.isNotNullOrEmpty(rcRequest.healthcareAmt))
            if (!rcRequest.healthcareAmt.equals(BD_ZERO))
                list.add(getAddtlAmtGrp(rcRequest, rcRequest.healthcareAmt, AddAmtTypeType.HLTCARE));

        if (Utils.isNotNullOrEmpty(rcRequest.rxAmt))
            if (!rcRequest.rxAmt.equals(BD_ZERO))
                list.add(getAddtlAmtGrp(rcRequest, rcRequest.rxAmt, AddAmtTypeType.RX));

        if (Utils.isNotNullOrEmpty(rcRequest.partAuthrztnApprvlCapablt)) {
            if (Utils.toEnum(EnumAllowPartialAuth.class, rcRequest.partAuthrztnApprvlCapablt) != EnumAllowPartialAuth.NotSet) {
                AddtlAmtGrp addtlAmtGrp = new AddtlAmtGrp();
                addtlAmtGrp.setPartAuthrztnApprvlCapablt(String.valueOf(rcRequest.partAuthrztnApprvlCapablt));
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

    PINGrp getPINGrp() {
        PINGrp pinGrp = new PINGrp();
        pinGrp.setPINData(String.valueOf(rcRequest.pinData));
        pinGrp.setKeySerialNumData(String.valueOf(rcRequest.keySerialNumData));
        return pinGrp;
    }

    EbtGrp getEBTGrp() {
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

    TAGrp getTAGrp() {
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

    HostTotGrp getHostTotGrp() {
        HostTotGrp hostTotGrp = new HostTotGrp();
        hostTotGrp.setTotReqDate(rcRequest.totReqDate); // CloseShift
        hostTotGrp.setPassword(rcRequest.password);
        hostTotGrp.setNetSettleAmt(rcRequest.netSettleAmt);

        return hostTotGrp;
    }

    EMVGrp getEmvGrp() {
        EMVGrp emvGrp = new EMVGrp();
        if (Utils.isNotNullOrEmpty(rcRequest.emvData))
            emvGrp.setEMVData(rcRequest.emvData);
        return Utils.valueOrNothing(emvGrp);
    }

    // moved from RequestUtils
    boolean origAuthGrpRequired() {
        // FDRC expecting origGrp to be present in reversals
        TxnTypeType txnType = Utils.toEnum(TxnTypeType.class, rcRequest.txnType);
        boolean isReversalVoid = Utils.isNotNullOrEmpty(rcRequest.reversalInd) &&
                (Utils.toEnum(ReversalIndType.class, rcRequest.reversalInd) == ReversalIndType.VOID ||
                        Utils.toEnum(ReversalIndType.class, rcRequest.reversalInd) == ReversalIndType.PARTIAL);

        if (txnType == TxnTypeType.AUTHORIZATION && !isReversalVoid) return false;
        return txnType != TxnTypeType.REFUND || isReversalVoid;
    }
}