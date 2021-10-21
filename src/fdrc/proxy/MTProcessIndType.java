//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.01.27 at 03:21:12 PM EST 
//


package fdrc.proxy;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MTProcessIndType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MTProcessIndType">
 *   &lt;restriction base="{com/fiserv/Merchant/gmfV10.02}Len7AN">
 *     &lt;enumeration value="Funding"/>
 *     &lt;enumeration value="Payment"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MTProcessIndType")
@XmlEnum
public enum MTProcessIndType {

    @XmlEnumValue("Funding")
    FUNDING("Funding"),
    @XmlEnumValue("Payment")
    PAYMENT("Payment");
    private final String value;

    MTProcessIndType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MTProcessIndType fromValue(String v) {
        for (MTProcessIndType c: MTProcessIndType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}