package fdrc.common;

import fdrc.base.Constants;
import fdrc.proxy.GMFMessageVariants;
import fdrc.proxy.TxnTypeType;
import fdrc.utils.Utils;

public class RequestUtils {
    public static String merchantID;
    /* Generate Client Ref Number in the format <STAN>|<TPPID>, right justified and left padded with "0" */
    public static String getClientRef() {
        String clientRef = "";
//        clientRef = String.format("0%SV%S", Utils.getSTAN(), Constants.REQUEST_TPPID);
        clientRef = Utils.getSTAN() + "|" + Constants.REQUEST_TPPID;
        clientRef = "00" + clientRef;
        return clientRef;
    }

    public static String getXMLData(GMFMessageVariants gmfMessageVariants) {
        Serialization serialization = new Serialization();
        return serialization.GetXMLData(gmfMessageVariants).replaceAll("gmfMessageVariants", "GMF");
    }

    public static String mapMidToDID(){
        switch(merchantID){
            case "RCTST1000091636":
                return "00041372277848179310";
            case "RCTST1000091637":
                return "00035488451724571345";
            case "RCTST1000091638":
                return "00035488381390525644";
            default:
                throw new RuntimeException("invalid merchand id");
        }
    }

    public static boolean origAuthGrpRequired(String txnType){
        if (TxnTypeType.fromValue(txnType) == TxnTypeType.REFUND) return false;
        return true;
    }

}
