package fdrc.common;

import fdrc.Exceptions.InvalidNumber;
import fdrc.base.Request;
import fdrc.proxy.PymtTypeType;
import fdrc.proxy.TxnTypeType;
import fdrc.types.EnumEngineEntryMethod;
import fdrc.types.EnumIndustryType;

import java.lang.reflect.InvocationTargetException;
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
    public static Enum<?> ToEnum(Enum<?> en, String indType) {
        Enum result = null;
        try {
//            Class<?> c = Class.forName(en);
//            if (c.isEnum()) {
//                Object obj = c.getDeclaredConstructor().newInstance();
//            }

            result = Enum.valueOf(en.getClass(), indType);
//            result = EnumIndustryType.EnumIndustrytype.valueOf(indType);
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) throws IllegalArgumentException {
        EnumIndustryType ind = EnumIndustryType.none;
        try {
            ind = (EnumIndustryType) ToEnum(ind, "autorental");
        } catch (Exception e) {
//            e.printStackTrace();
            //throw new IllegalArgumentException(e.getMessage());
        }
        System.out.println(ToEnum(ind, "autorental"));
        EnumIndustryType type1 = EnumIndustryType.none;
        System.out.println(Enum.valueOf(EnumIndustryType.class, "autorental"));
        System.out.println(Enum.valueOf(EnumIndustryType.class, "autorental"));
        EnumIndustryType type = EnumIndustryType.valueOf("autorental");

//        EnumEngineEntryMethod.values()

        System.out.println();
        System.out.println(type);
    }

}
