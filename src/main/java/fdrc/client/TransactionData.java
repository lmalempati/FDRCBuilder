package fdrc.client;

import java.io.Serializable;

/***
 * This is not in use
 */

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
}
