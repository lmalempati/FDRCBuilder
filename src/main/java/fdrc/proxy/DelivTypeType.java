//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.01.27 at 03:21:12 PM EST 
//


package fdrc.proxy;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DelivTypeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DelivTypeType">
 *   &lt;restriction base="{com/fiserv/Merchant/gmfV10.02}Len3AN">
 *     &lt;enumeration value="CNC"/>
 *     &lt;enumeration value="DIG"/>
 *     &lt;enumeration value="PHY"/>
 *     &lt;enumeration value="SVC"/>
 *     &lt;enumeration value="TBD"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DelivTypeType")
@XmlEnum
public enum DelivTypeType {

    CNC,
    DIG,
    PHY,
    SVC,
    TBD;

    public String value() {
        return name();
    }

    public static DelivTypeType fromValue(String v) {
        return valueOf(v);
    }

}
