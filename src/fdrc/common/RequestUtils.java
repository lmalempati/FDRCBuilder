package fdrc.common;

import com.fiserv.merchant.gmfv10.ReversalIndType;
import fdrc.Exceptions.UnsupportedValueException;
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
//        clientRef = Utils.getSTAN() + "|" + Constants.REQUEST_TPPID;
//        clientRef = "00" + clientRef;
        return clientRef;
    }

    public static String getXMLData(GMFMessageVariants gmfMessageVariants) {
        Serialization serialization = new Serialization();
        return serialization.getXMLData(gmfMessageVariants, "");
    }

    public static String mapMidToDID(String merchantID){
        switch(merchantID){
            case "RCTST1000091636":
                return "00041372277848179310";
            case "RCTST1000091637":
                return "00035488451724571345";
            case "RCTST1000091638":
                return "00035488381390525644";
            default:
                throw new UnsupportedValueException(String.format("merchantID %s", merchantID));
        }
    }

    public static boolean origAuthGrpRequired(Request request){
        // FDRC expecting origGrp to be present in reversals
        TxnTypeType txnType = Utils.getEnumValue(TxnTypeType.class, request.txnType);
        boolean isRevVoid = Utils.isNotNullOrEmpty(request.reversalInd) &&  Utils.getEnumValue(ReversalIndType.class, request.reversalInd) == ReversalIndType.VOID;
        if (txnType == TxnTypeType.AUTHORIZATION && !isRevVoid) return false;
         if (txnType == TxnTypeType.REFUND && !isRevVoid) return false;
        return true;
    }
}
