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
 * <p>Java class for DiscAuthIndType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DiscAuthIndType">
 *   &lt;restriction base="{com/fiserv/Merchant/gmfV10.02}Max9AN">
 *     &lt;enumeration value="ReAuth"/>
 *     &lt;enumeration value="CrdOnFile"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DiscAuthIndType")
@XmlEnum
public enum DiscAuthIndType {

    @XmlEnumValue("ReAuth")
    RE_AUTH("ReAuth"),
    @XmlEnumValue("CrdOnFile")
    CRD_ON_FILE("CrdOnFile");
    private final String value;

    DiscAuthIndType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DiscAuthIndType fromValue(String v) {
        for (DiscAuthIndType c: DiscAuthIndType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
