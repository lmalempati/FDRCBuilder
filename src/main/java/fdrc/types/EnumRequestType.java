package fdrc.types;

public enum EnumRequestType {
    trans("transaction"),
    discovery("discovery"),
    register("registration"),
    activate("activation");

    public String val;

    EnumRequestType(String val) {
        this.val = val;
    }
}
