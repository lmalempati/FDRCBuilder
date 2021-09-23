package fdrc.common;

import fdrc.types.EnumEngineEntryMethod;
import fdrc.types.EnumIndustryType;

import java.lang.reflect.InvocationTargetException;

public class ValidateRequest {
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
        EnumIndustryType.EnumIndustrytype ind = EnumIndustryType.EnumIndustrytype.none;
        try {
            ind = (EnumIndustryType.EnumIndustrytype) ToEnum(ind, "autorental");
        } catch (Exception e) {
//            e.printStackTrace();
            //throw new IllegalArgumentException(e.getMessage());
        }
        System.out.println(ToEnum(ind, "autorental"));
        EnumIndustryType.EnumIndustrytype type1 = EnumIndustryType.EnumIndustrytype.none;
        System.out.println(Enum.valueOf(EnumIndustryType.EnumIndustrytype.class, "autorental"));
        System.out.println(Enum.valueOf(EnumIndustryType.EnumIndustrytype.class, "autorental"));
        EnumIndustryType.EnumIndustrytype type = EnumIndustryType.EnumIndustrytype.valueOf("autorental");

//        EnumEngineEntryMethod.values()

        System.out.println();
        System.out.println(type);
    }
}
