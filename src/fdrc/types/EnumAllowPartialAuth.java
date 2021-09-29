package fdrc.types;

public enum EnumAllowPartialAuth {
    NotSet( -1),
    DoNotAllow (0),
    Allow (1);

    public final int val;

    private EnumAllowPartialAuth(int value) {
        this.val = value;
    }
}
