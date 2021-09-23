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
 * <p>Java class for LodChargeTypeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LodChargeTypeType">
 *   &lt;restriction base="{com/fiserv/Merchant/gmfV10.02}Max10AN">
 *     &lt;enumeration value="Lodging"/>
 *     &lt;enumeration value="Restaurant"/>
 *     &lt;enumeration value="GiftShop"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LodChargeTypeType")
@XmlEnum
public enum LodChargeTypeType {

    @XmlEnumValue("Lodging")
    LODGING("Lodging"),
    @XmlEnumValue("Restaurant")
    RESTAURANT("Restaurant"),
    @XmlEnumValue("GiftShop")
    GIFT_SHOP("GiftShop");
    private final String value;

    LodChargeTypeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LodChargeTypeType fromValue(String v) {
        for (LodChargeTypeType c: LodChargeTypeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
