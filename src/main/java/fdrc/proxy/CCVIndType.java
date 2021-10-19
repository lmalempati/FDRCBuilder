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
 * <p>Java class for CCVIndType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CCVIndType">
 *   &lt;restriction base="{com/fiserv/Merchant/gmfV10.02}Max12AN">
 *     &lt;enumeration value="Ntprvd"/>
 *     &lt;enumeration value="Prvded"/>
 *     &lt;enumeration value="Illegible"/>
 *     &lt;enumeration value="NtOnCrd"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CCVIndType")
@XmlEnum
public enum CCVIndType {

    @XmlEnumValue("Ntprvd")
    NTPRVD("Ntprvd"),
    @XmlEnumValue("Prvded")
    PRVDED("Prvded"),
    @XmlEnumValue("Illegible")
    ILLEGIBLE("Illegible"),
    @XmlEnumValue("NtOnCrd")
    NT_ON_CRD("NtOnCrd");
    private final String value;

    CCVIndType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CCVIndType fromValue(String v) {
        for (CCVIndType c: CCVIndType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
