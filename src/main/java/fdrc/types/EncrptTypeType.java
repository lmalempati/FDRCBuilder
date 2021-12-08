package fdrc.types;

public enum EncrptTypeType {
    RSA("RSA"),
    Verifone("Verifone"),
    ThreeDES("3DES"),
    VSP("VSP"),
    Onguard("Onguard"),
    AESDUKPT("AESDUKPT");

    public final String val;

    private EncrptTypeType(String value) {
        this.val = value;
    }

    public static EncrptTypeType fromValue(String v) {
        for (EncrptTypeType c : EncrptTypeType.values()) {
            if (c.val.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}