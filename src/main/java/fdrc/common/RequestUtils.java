package fdrc.common;

import com.fiserv.merchant.gmfv10.ReversalIndType;
import fdrc.Exceptions.UnsupportedEnumValueException;
import fdrc.base.Constants;
import com.fiserv.merchant.gmfv10.GMFMessageVariants;
import com.fiserv.merchant.gmfv10.TxnTypeType;
import fdrc.base.Request;
import fdrc.utils.Utils;

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
        return serialization.getXMLPayload(gmfMessageVariants, "");
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
            default:
                throw new UnsupportedEnumValueException(String.format("merchantID %s", merchantID));
        }
    }

    public static boolean origAuthGrpRequired(Request request){
        // FDRC expecting origGrp to be present in reversals
        TxnTypeType txnType = Utils.toEnum(TxnTypeType.class, request.txnType);
        boolean isReversalVoid = Utils.isNotNullOrEmpty(request.reversalInd) &&
                (Utils.toEnum(ReversalIndType.class, request.reversalInd) == ReversalIndType.VOID ||
                        Utils.toEnum(ReversalIndType.class, request.reversalInd) == ReversalIndType.PARTIAL);

        if (txnType == TxnTypeType.AUTHORIZATION && !isReversalVoid) return false;
        return txnType != TxnTypeType.REFUND || isReversalVoid;
    }
}
