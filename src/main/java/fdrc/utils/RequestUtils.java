package fdrc.utils;

import com.fiserv.merchant.gmfv10.ReversalIndType;
import fdrc.Exceptions.UnsupportedEnumValueException;
import fdrc.base.Constants;
import com.fiserv.merchant.gmfv10.GMFMessageVariants;
import com.fiserv.merchant.gmfv10.TxnTypeType;
import fdrc.base.RCRequest;
import fdrc.common.Serialization;

public class RequestUtils {
    public static String merchantID;
    /* Generate Client Ref Number in the format <STAN>|<TPPID>, right justified and left padded with "0" */
    public static String getClientRef() {
        String clientRef = "";
        clientRef = String.format("0%sV%s", Utils.getSTAN(), Constants.REQUEST_TPPID);
        return clientRef;
    }

    public static String getXMLData(GMFMessageVariants gmfMessageVariants) {
        Serialization serialization = new Serialization();
        return serialization.getXmlObject(gmfMessageVariants, "");
    }

    public static String mapMidToDID(String merchantID){
        // Todo: MID, DID, MCC, IndustryType so on has to come from caller, builder has no idea.
        switch(merchantID){
            case "RCTST1000092382":
                return "00035606147505719454";
            case "RCTST1000092383":
                return "00035606151483882257";
            case "RCTST1000092384":
                return "00035606180092691444";

            case "RCTST1000091636":
                return "00041372277848179310";
            case "RCTST1000091637":
                return "00035488451724571345";
            case "RCTST1000091638":
                return "00035488381390525644";

            case "RCTST1000092850":
                return "00042217876975393171";
            case "RCTST1000092851":
                return "00042218039591394672";
            case "RCTST1000092852":
                return "00042217833110503539";
            default:
                throw new UnsupportedEnumValueException(String.format("merchantID %s", merchantID));
        }
    }

    public static boolean origAuthGrpRequired(RCRequest RCRequest){
        // FDRC expecting origGrp to be present in reversals
        TxnTypeType txnType = Utils.toEnum(TxnTypeType.class, RCRequest.txnType);
        boolean isReversalVoid = Utils.isNotNullOrEmpty(RCRequest.reversalInd) &&
                (Utils.toEnum(ReversalIndType.class, RCRequest.reversalInd) == ReversalIndType.VOID ||
                        Utils.toEnum(ReversalIndType.class, RCRequest.reversalInd) == ReversalIndType.PARTIAL);

        if (txnType == TxnTypeType.AUTHORIZATION && !isReversalVoid) return false;
        return txnType != TxnTypeType.REFUND || isReversalVoid;
    }
}
