//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.01.27 at 03:21:12 PM EST 
//


package fdrc.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CanDebitGrp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CanDebitGrp">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}CanDebitTransCode" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}CanNetRespCode" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}MAC" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}MAWrkKeyChkDgts" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}MARespCode" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}MACWrkKey" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}MsgEncrptWrkKey" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}PINEncrptWrkKey" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}CANKeySerialNumData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CanDebitGrp", propOrder = {
    "canDebitTransCode",
    "canNetRespCode",
    "mac",
    "maWrkKeyChkDgts",
    "maRespCode",
    "macWrkKey",
    "msgEncrptWrkKey",
    "pinEncrptWrkKey",
    "canKeySerialNumData"
})
public class CanDebitGrp {

    @XmlElement(name = "CanDebitTransCode")
    @XmlSchemaType(name = "string")
    protected CanDebitTransCodeType canDebitTransCode;
    @XmlElement(name = "CanNetRespCode")
    protected String canNetRespCode;
    @XmlElement(name = "MAC")
    protected String mac;
    @XmlElement(name = "MAWrkKeyChkDgts")
    protected String maWrkKeyChkDgts;
    @XmlElement(name = "MARespCode")
    protected String maRespCode;
    @XmlElement(name = "MACWrkKey")
    protected String macWrkKey;
    @XmlElement(name = "MsgEncrptWrkKey")
    protected String msgEncrptWrkKey;
    @XmlElement(name = "PINEncrptWrkKey")
    protected String pinEncrptWrkKey;
    @XmlElement(name = "CANKeySerialNumData")
    protected String canKeySerialNumData;

    /**
     * Gets the value of the canDebitTransCode property.
     * 
     * @return
     *     possible object is
     *     {@link CanDebitTransCodeType }
     *     
     */
    public CanDebitTransCodeType getCanDebitTransCode() {
        return canDebitTransCode;
    }

    /**
     * Sets the value of the canDebitTransCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link CanDebitTransCodeType }
     *     
     */
    public void setCanDebitTransCode(CanDebitTransCodeType value) {
        this.canDebitTransCode = value;
    }

    /**
     * Gets the value of the canNetRespCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCanNetRespCode() {
        return canNetRespCode;
    }

    /**
     * Sets the value of the canNetRespCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCanNetRespCode(String value) {
        this.canNetRespCode = value;
    }

    /**
     * Gets the value of the mac property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMAC() {
        return mac;
    }

    /**
     * Sets the value of the mac property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMAC(String value) {
        this.mac = value;
    }

    /**
     * Gets the value of the maWrkKeyChkDgts property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMAWrkKeyChkDgts() {
        return maWrkKeyChkDgts;
    }

    /**
     * Sets the value of the maWrkKeyChkDgts property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMAWrkKeyChkDgts(String value) {
        this.maWrkKeyChkDgts = value;
    }

    /**
     * Gets the value of the maRespCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMARespCode() {
        return maRespCode;
    }

    /**
     * Sets the value of the maRespCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMARespCode(String value) {
        this.maRespCode = value;
    }

    /**
     * Gets the value of the macWrkKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMACWrkKey() {
        return macWrkKey;
    }

    /**
     * Sets the value of the macWrkKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMACWrkKey(String value) {
        this.macWrkKey = value;
    }

    /**
     * Gets the value of the msgEncrptWrkKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgEncrptWrkKey() {
        return msgEncrptWrkKey;
    }

    /**
     * Sets the value of the msgEncrptWrkKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsgEncrptWrkKey(String value) {
        this.msgEncrptWrkKey = value;
    }

    /**
     * Gets the value of the pinEncrptWrkKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPINEncrptWrkKey() {
        return pinEncrptWrkKey;
    }

    /**
     * Sets the value of the pinEncrptWrkKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPINEncrptWrkKey(String value) {
        this.pinEncrptWrkKey = value;
    }

    /**
     * Gets the value of the canKeySerialNumData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCANKeySerialNumData() {
        return canKeySerialNumData;
    }

    /**
     * Sets the value of the canKeySerialNumData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCANKeySerialNumData(String value) {
        this.canKeySerialNumData = value;
    }

}
