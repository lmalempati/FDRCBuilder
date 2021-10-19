
package fdrc.proxy;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InstallPymtOptionsType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="InstallPymtOptionsType">
 *   &lt;restriction base="{com/fiserv/Merchant/gmfV10.02}Len1AN">
 *     &lt;enumeration value="I"/>
 *     &lt;enumeration value="F"/>
 *     &lt;enumeration value="B"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "InstallPymtOptionsType", namespace = "com/fiserv/Merchant/gmfV10.02")
@XmlEnum
public enum InstallPymtOptionsType {

    I,
    F,
    B;

    public String value() {
        return name();
    }

    public static InstallPymtOptionsType fromValue(String v) {
        return valueOf(v);
    }

}
