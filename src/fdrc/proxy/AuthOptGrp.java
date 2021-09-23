
package fdrc.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AuthOptGrp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AuthOptGrp">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}DfrdAuthTranID" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}OverrideInd" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}AuthOptReasonCode" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthOptGrp", namespace = "com/fiserv/Merchant/gmfV10.02", propOrder = {
    "dfrdAuthTranID",
    "overrideInd",
    "authOptReasonCode"
})
public class AuthOptGrp {

    @XmlElement(name = "DfrdAuthTranID", namespace = "com/fiserv/Merchant/gmfV10.02")
    protected String dfrdAuthTranID;
    @XmlElement(name = "OverrideInd", namespace = "com/fiserv/Merchant/gmfV10.02")
    @XmlSchemaType(name = "string")
    protected Max3ANYesNo overrideInd;
    @XmlElement(name = "AuthOptReasonCode", namespace = "com/fiserv/Merchant/gmfV10.02")
    protected String authOptReasonCode;

    /**
     * Gets the value of the dfrdAuthTranID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDfrdAuthTranID() {
        return dfrdAuthTranID;
    }

    /**
     * Sets the value of the dfrdAuthTranID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDfrdAuthTranID(String value) {
        this.dfrdAuthTranID = value;
    }

    /**
     * Gets the value of the overrideInd property.
     * 
     * @return
     *     possible object is
     *     {@link Max3ANYesNo }
     *     
     */
    public Max3ANYesNo getOverrideInd() {
        return overrideInd;
    }

    /**
     * Sets the value of the overrideInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Max3ANYesNo }
     *     
     */
    public void setOverrideInd(Max3ANYesNo value) {
        this.overrideInd = value;
    }

    /**
     * Gets the value of the authOptReasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthOptReasonCode() {
        return authOptReasonCode;
    }

    /**
     * Sets the value of the authOptReasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthOptReasonCode(String value) {
        this.authOptReasonCode = value;
    }

}
