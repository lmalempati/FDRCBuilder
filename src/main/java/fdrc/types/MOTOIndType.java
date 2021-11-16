package fdrc.types;

public enum MOTOIndType {
    MailOrder("1"),
    TelephoneOrder("2");

    public final String val;

    private MOTOIndType(String value){this.val = value;}

    public static MOTOIndType fromValue(String v) {
        for (MOTOIndType c: MOTOIndType.values()) {
            if (c.val.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
