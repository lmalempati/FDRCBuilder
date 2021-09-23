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
 * <p>Java class for WexOTRGrp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WexOTRGrp">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}SiteTypeInd" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}WexOTRSetInd" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}MnyCode" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}MnyCodePayeeName" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}MnyCodeChkNum" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}CustName" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}CustCity" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}CustState" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}CustAcctCode" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}CashAdvLimit" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WexOTRGrp", propOrder = {
    "siteTypeInd",
    "wexOTRSetInd",
    "mnyCode",
    "mnyCodePayeeName",
    "mnyCodeChkNum",
    "custName",
    "custCity",
    "custState",
    "custAcctCode",
    "cashAdvLimit"
})
public class WexOTRGrp {

    @XmlElement(name = "SiteTypeInd")
    @XmlSchemaType(name = "string")
    protected SiteTypeIndType siteTypeInd;
    @XmlElement(name = "WexOTRSetInd")
    protected String wexOTRSetInd;
    @XmlElement(name = "MnyCode")
    protected String mnyCode;
    @XmlElement(name = "MnyCodePayeeName")
    protected String mnyCodePayeeName;
    @XmlElement(name = "MnyCodeChkNum")
    protected String mnyCodeChkNum;
    @XmlElement(name = "CustName")
    protected String custName;
    @XmlElement(name = "CustCity")
    protected String custCity;
    @XmlElement(name = "CustState")
    @XmlSchemaType(name = "string")
    protected StateCodeType custState;
    @XmlElement(name = "CustAcctCode")
    protected String custAcctCode;
    @XmlElement(name = "CashAdvLimit")
    protected String cashAdvLimit;

    /**
     * Gets the value of the siteTypeInd property.
     * 
     * @return
     *     possible object is
     *     {@link SiteTypeIndType }
     *     
     */
    public SiteTypeIndType getSiteTypeInd() {
        return siteTypeInd;
    }

    /**
     * Sets the value of the siteTypeInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link SiteTypeIndType }
     *     
     */
    public void setSiteTypeInd(SiteTypeIndType value) {
        this.siteTypeInd = value;
    }

    /**
     * Gets the value of the wexOTRSetInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWexOTRSetInd() {
        return wexOTRSetInd;
    }

    /**
     * Sets the value of the wexOTRSetInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWexOTRSetInd(String value) {
        this.wexOTRSetInd = value;
    }

    /**
     * Gets the value of the mnyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMnyCode() {
        return mnyCode;
    }

    /**
     * Sets the value of the mnyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMnyCode(String value) {
        this.mnyCode = value;
    }

    /**
     * Gets the value of the mnyCodePayeeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMnyCodePayeeName() {
        return mnyCodePayeeName;
    }

    /**
     * Sets the value of the mnyCodePayeeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMnyCodePayeeName(String value) {
        this.mnyCodePayeeName = value;
    }

    /**
     * Gets the value of the mnyCodeChkNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMnyCodeChkNum() {
        return mnyCodeChkNum;
    }

    /**
     * Sets the value of the mnyCodeChkNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMnyCodeChkNum(String value) {
        this.mnyCodeChkNum = value;
    }

    /**
     * Gets the value of the custName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustName() {
        return custName;
    }

    /**
     * Sets the value of the custName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustName(String value) {
        this.custName = value;
    }

    /**
     * Gets the value of the custCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustCity() {
        return custCity;
    }

    /**
     * Sets the value of the custCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustCity(String value) {
        this.custCity = value;
    }

    /**
     * Gets the value of the custState property.
     * 
     * @return
     *     possible object is
     *     {@link StateCodeType }
     *     
     */
    public StateCodeType getCustState() {
        return custState;
    }

    /**
     * Sets the value of the custState property.
     * 
     * @param value
     *     allowed object is
     *     {@link StateCodeType }
     *     
     */
    public void setCustState(StateCodeType value) {
        this.custState = value;
    }

    /**
     * Gets the value of the custAcctCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustAcctCode() {
        return custAcctCode;
    }

    /**
     * Sets the value of the custAcctCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustAcctCode(String value) {
        this.custAcctCode = value;
    }

    /**
     * Gets the value of the cashAdvLimit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCashAdvLimit() {
        return cashAdvLimit;
    }

    /**
     * Sets the value of the cashAdvLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCashAdvLimit(String value) {
        this.cashAdvLimit = value;
    }

}
