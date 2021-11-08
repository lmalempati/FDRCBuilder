package fdrc.types;

public enum EnumEngineEntryMethod {
    Unknown(0),
    Keyed(1),
    EMV(5),
    Contactless_EMV(7),
    Amex_Digital_Wallet(8),
    Credential_On_File(10),
    Fallback_Swipe(80),
    Swipe(90),
    Contactless_MSD(91);

    public final int val;

    private EnumEngineEntryMethod(int value) {
        this.val = value;
    }
}
