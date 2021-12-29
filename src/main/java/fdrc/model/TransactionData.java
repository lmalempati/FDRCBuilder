package fdrc.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransactionData implements Serializable {
    public String ARQC;
    public String ATC;
    public String CID;
    public String EMVTags;
    public String PINCipher;
    public String TSI;
    public String TVR;
    public String aid;
    public long amount = 0;
    public String appLabel;
    public String appName;
    public String cardHolderName;
    public String cardNo;
    public String cardSerialNo;
    public int cardType;
    public long cashBack;
    public String currency_symbol = "$";
    public byte[] emvTags;
    public String expireDate;
    public boolean isAEMagneticMode;
    public boolean isCardSerialNo;
    public boolean isFallback;
    public boolean isMagneticMode;
    public boolean isRequestSignature;
    public byte[] ksn;
    public byte[] pinBlock;
    public int pinType;
    public String scriptResult;
    public String serviceCode;
    public String tag9F02;
    public String tag9F06Value;
    public String tag9F34;
    public long tip;
    public String track1;
    public String track2;
    public String track3;
    // card info
    public String ccvInd;
    public String ccvData;
    public String avsResultCode;
    // common group
    public String posCondCode;
    public String posEntryMode;
    public String pymtType;
    public String industrytype;
    public String stan;
    public String refNum;
    public String orderNum;
    public String txnType;
    public String termCatCode;
    public String termEntryCapablt;
//    public Double txnAmt;
    public String txnCrncy;
    public String termLocInd;
    public String cardCaptCap;

    // OrigAuthInfo
    public String origAuthID;
    public String origLocalDateTime;
    public String origTranDateTime;
    public String origRespCode;
    public String origSTAN;
    // AddtlAmtInfo
    public String partAuthrztnApprvlCapablt;
    public BigDecimal firstAuthAmt;
    public BigDecimal totalAuthAmt;
    // VisaGrp
    public String aci;
    public String visaBID;
    public String visaAUAR;
    public String taxAmtCapablt;
    public String transID;
    public String cardLevelResult;
    //MCgrp
    public int finAuthInd;
    public String banknetData;
    // config
    public String merchCatCode;
}
