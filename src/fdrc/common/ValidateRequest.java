package fdrc.common;

import com.fiserv.merchant.gmfv10.PymtTypeType;
import com.fiserv.merchant.gmfv10.TxnTypeType;
import fdrc.base.Request;
import fdrc.types.EnumIndustryType;
import fdrc.utils.Utils;

import java.util.regex.Pattern;

public class ValidateRequest {
    public String validate(final Request request){
        //todo: check what else to check
        if(request == null)
            return "invalid or empty request";
        // to check payment and trxn types are valid
        PymtTypeType.fromValue(request.pymtType);
        TxnTypeType.fromValue(request.txnType);

        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        if (request.txnAmt == null || !pattern.matcher(request.txnAmt.toString()).matches()) {
            return "Invalid amount.";
        }

        return "";
    }
    public static void main(String[] args) throws IllegalArgumentException {
        EnumIndustryType ind = EnumIndustryType.none;
        try {
            ind = (EnumIndustryType) Utils.getEnumValue(EnumIndustryType.class, "autorental");
        } catch (Exception e) {
//            e.printStackTrace();
            //throw new IllegalArgumentException(e.getMessage());
        }
    }

}
