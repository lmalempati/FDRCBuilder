package test;

import com.fiserv.merchant.gmfv10.*;
import fdrc.Exceptions.InvalidNumber;
import fdrc.Exceptions.UnsupportedValueException;
import fdrc.types.CardCaptCapType;
import fdrc.types.MOTOIndType;
import fdrc.utils.Utils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void isNotNullOrEmpty() {
        assertTrue(Utils.isNotNullOrEmpty(null) == false);
        assertTrue(Utils.isNotNullOrEmpty(new ABC()) == true);
        assertTrue(Utils.isNotNullOrEmpty("") == false);
        assertTrue(Utils.isNotNullOrEmpty(String.valueOf(' ')) == false);
    }

    @Test
    void valueOrNothing() {
        assertTrue(Utils.valueOrNothing(null) == null);
        assertTrue(Utils.valueOrNothing(new Object()) == null);
        assertThrows(RuntimeException.class, () -> {Utils.valueOrNothing(540);});
        assertThrows(RuntimeException.class, () -> {Utils.valueOrNothing(ABC.class);});
    }

    @Test
    void formatAmount() {
        assertTrue(Utils.formatAmount("12.33").equals("000000001233"));
        assertTrue(Utils.formatAmount("0.00").equals("000000000000"));
        assertThrows(InvalidNumber.class, () -> {Utils.formatAmount("-2");});
    }

    @Test
    void getEnumValue() {
        assertThrows(UnsupportedValueException.class, () -> {Utils.toEnum(CardTypeType.class, "MASTERCARD");});
        assertTrue(Utils.toEnum(TxnTypeType.class, "Verification") == TxnTypeType.VERIFICATION);
        assertTrue(Utils.toEnum(TxnTypeType.class, "Activation") == TxnTypeType.ACTIVATION);
        assertTrue(Utils.toEnum(CCVIndType.class, "Prvded") == CCVIndType.PRVDED);
        assertTrue(Utils.toEnum(ReversalIndType.class, "Partial") == ReversalIndType.PARTIAL);
        assertThrows(UnsupportedValueException.class, () -> {Utils.toEnum(CardTypeType.class, "JunkValue");});
        assertThrows(NullPointerException.class, () -> {Utils.toEnum(CardTypeType.class, null);});

        assertTrue(Utils.toEnum(CardCaptCapType.class, "0").val == "0");
        assertTrue(Utils.toEnum(CardCaptCapType.class, "1").val == "1");
        assertThrows(UnsupportedValueException.class, () -> {Utils.toEnum(CardCaptCapType.class, "2");});

        assertTrue(Utils.toEnum(MOTOIndType.class, "1").val == "1");
        assertTrue(Utils.toEnum(MOTOIndType.class, "2").val == "2");
        assertThrows(UnsupportedValueException.class, () -> {Utils.toEnum(MOTOIndType.class, "3");});

        assertTrue(Utils.toEnum(RefundTypeType.class, "Online") == RefundTypeType.ONLINE);
        assertTrue(Utils.toEnum(RefundTypeType.class, "AuthOnly") == RefundTypeType.AUTH_ONLY);
        assertThrows(UnsupportedValueException.class, () -> {Utils.toEnum(RefundTypeType.class, "Invalid");});
    }

    class ABC{
    }
}