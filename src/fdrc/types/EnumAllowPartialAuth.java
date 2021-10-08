package fdrc.types;

import fdrc.proxy.CardTypeType;

public enum EnumAllowPartialAuth {
    NotSet(""),
    DoNotAllow("0"),
    Allow("1");

    public final String val;

    private EnumAllowPartialAuth(String value) {
        this.val = value;
    }

    public static EnumAllowPartialAuth fromValue(String v) {
        for (EnumAllowPartialAuth c: EnumAllowPartialAuth.values()) {
            if (c.val.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
