package fdrc.types;

public enum CardCaptCapType {
    Capable("0"),
    NotCapable("1");

    public final String val;

    private CardCaptCapType(String value){this.val = value;}

    public static CardCaptCapType fromValue(String v) {
        for (CardCaptCapType c: CardCaptCapType.values()) {
            if (c.val.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
