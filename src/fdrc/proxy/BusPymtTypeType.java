
package fdrc.proxy;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BusPymtTypeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BusPymtTypeType">
 *   &lt;restriction base="{com/fiserv/Merchant/gmfV10.02}Max12AN">
 *     &lt;enumeration value="ConsrBillPay"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BusPymtTypeType", namespace = "com/fiserv/Merchant/gmfV10.02")
@XmlEnum
public enum BusPymtTypeType {

    @XmlEnumValue("ConsrBillPay")
    CONSR_BILL_PAY("ConsrBillPay");
    private final String value;

    BusPymtTypeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BusPymtTypeType fromValue(String v) {
        for (BusPymtTypeType c: BusPymtTypeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
