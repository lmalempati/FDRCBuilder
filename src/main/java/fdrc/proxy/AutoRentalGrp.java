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
 * <p>Java class for AutoRentalGrp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AutoRentalGrp">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}RentalCity" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}RentalState" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}RentalCtry" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}RentalDate" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}RentalTime" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}ReturnCity" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}ReturnState" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}ReturnCtry" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}ReturnDate" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}ReturnTime" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}AmtExtraChrgs" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}RenterName" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}AutoAgreeNum" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}RentalDuration" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}RentalExtraChrgs" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}AutoNoShow" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}RentalClsID" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}RentalTaxInd" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}DelChrgInd" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AutoRentalGrp", propOrder = {
    "rentalCity",
    "rentalState",
    "rentalCtry",
    "rentalDate",
    "rentalTime",
    "returnCity",
    "returnState",
    "returnCtry",
    "returnDate",
    "returnTime",
    "amtExtraChrgs",
    "renterName",
    "autoAgreeNum",
    "rentalDuration",
    "rentalExtraChrgs",
    "autoNoShow",
    "rentalClsID",
    "rentalTaxInd",
    "delChrgInd"
})
public class AutoRentalGrp {

    @XmlElement(name = "RentalCity")
    protected String rentalCity;
    @XmlElement(name = "RentalState")
    protected String rentalState;
    @XmlElement(name = "RentalCtry")
    protected String rentalCtry;
    @XmlElement(name = "RentalDate")
    protected String rentalDate;
    @XmlElement(name = "RentalTime")
    protected String rentalTime;
    @XmlElement(name = "ReturnCity")
    protected String returnCity;
    @XmlElement(name = "ReturnState")
    protected String returnState;
    @XmlElement(name = "ReturnCtry")
    protected String returnCtry;
    @XmlElement(name = "ReturnDate")
    protected String returnDate;
    @XmlElement(name = "ReturnTime")
    protected String returnTime;
    @XmlElement(name = "AmtExtraChrgs")
    protected String amtExtraChrgs;
    @XmlElement(name = "RenterName")
    protected String renterName;
    @XmlElement(name = "AutoAgreeNum")
    protected String autoAgreeNum;
    @XmlElement(name = "RentalDuration")
    protected String rentalDuration;
    @XmlElement(name = "RentalExtraChrgs")
    protected String rentalExtraChrgs;
    @XmlElement(name = "AutoNoShow")
    protected String autoNoShow;
    @XmlElement(name = "RentalClsID")
    protected String rentalClsID;
    @XmlElement(name = "RentalTaxInd")
    protected String rentalTaxInd;
    @XmlElement(name = "DelChrgInd")
    @XmlSchemaType(name = "string")
    protected DelChrgIndType delChrgInd;

    /**
     * Gets the value of the rentalCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRentalCity() {
        return rentalCity;
    }

    /**
     * Sets the value of the rentalCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRentalCity(String value) {
        this.rentalCity = value;
    }

    /**
     * Gets the value of the rentalState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRentalState() {
        return rentalState;
    }

    /**
     * Sets the value of the rentalState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRentalState(String value) {
        this.rentalState = value;
    }

    /**
     * Gets the value of the rentalCtry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRentalCtry() {
        return rentalCtry;
    }

    /**
     * Sets the value of the rentalCtry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRentalCtry(String value) {
        this.rentalCtry = value;
    }

    /**
     * Gets the value of the rentalDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRentalDate() {
        return rentalDate;
    }

    /**
     * Sets the value of the rentalDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRentalDate(String value) {
        this.rentalDate = value;
    }

    /**
     * Gets the value of the rentalTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRentalTime() {
        return rentalTime;
    }

    /**
     * Sets the value of the rentalTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRentalTime(String value) {
        this.rentalTime = value;
    }

    /**
     * Gets the value of the returnCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnCity() {
        return returnCity;
    }

    /**
     * Sets the value of the returnCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnCity(String value) {
        this.returnCity = value;
    }

    /**
     * Gets the value of the returnState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnState() {
        return returnState;
    }

    /**
     * Sets the value of the returnState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnState(String value) {
        this.returnState = value;
    }

    /**
     * Gets the value of the returnCtry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnCtry() {
        return returnCtry;
    }

    /**
     * Sets the value of the returnCtry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnCtry(String value) {
        this.returnCtry = value;
    }

    /**
     * Gets the value of the returnDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnDate() {
        return returnDate;
    }

    /**
     * Sets the value of the returnDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnDate(String value) {
        this.returnDate = value;
    }

    /**
     * Gets the value of the returnTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnTime() {
        return returnTime;
    }

    /**
     * Sets the value of the returnTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnTime(String value) {
        this.returnTime = value;
    }

    /**
     * Gets the value of the amtExtraChrgs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmtExtraChrgs() {
        return amtExtraChrgs;
    }

    /**
     * Sets the value of the amtExtraChrgs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmtExtraChrgs(String value) {
        this.amtExtraChrgs = value;
    }

    /**
     * Gets the value of the renterName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRenterName() {
        return renterName;
    }

    /**
     * Sets the value of the renterName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRenterName(String value) {
        this.renterName = value;
    }

    /**
     * Gets the value of the autoAgreeNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutoAgreeNum() {
        return autoAgreeNum;
    }

    /**
     * Sets the value of the autoAgreeNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutoAgreeNum(String value) {
        this.autoAgreeNum = value;
    }

    /**
     * Gets the value of the rentalDuration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRentalDuration() {
        return rentalDuration;
    }

    /**
     * Sets the value of the rentalDuration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRentalDuration(String value) {
        this.rentalDuration = value;
    }

    /**
     * Gets the value of the rentalExtraChrgs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRentalExtraChrgs() {
        return rentalExtraChrgs;
    }

    /**
     * Sets the value of the rentalExtraChrgs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRentalExtraChrgs(String value) {
        this.rentalExtraChrgs = value;
    }

    /**
     * Gets the value of the autoNoShow property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutoNoShow() {
        return autoNoShow;
    }

    /**
     * Sets the value of the autoNoShow property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutoNoShow(String value) {
        this.autoNoShow = value;
    }

    /**
     * Gets the value of the rentalClsID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRentalClsID() {
        return rentalClsID;
    }

    /**
     * Sets the value of the rentalClsID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRentalClsID(String value) {
        this.rentalClsID = value;
    }

    /**
     * Gets the value of the rentalTaxInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRentalTaxInd() {
        return rentalTaxInd;
    }

    /**
     * Sets the value of the rentalTaxInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRentalTaxInd(String value) {
        this.rentalTaxInd = value;
    }

    /**
     * Gets the value of the delChrgInd property.
     * 
     * @return
     *     possible object is
     *     {@link DelChrgIndType }
     *     
     */
    public DelChrgIndType getDelChrgInd() {
        return delChrgInd;
    }

    /**
     * Sets the value of the delChrgInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link DelChrgIndType }
     *     
     */
    public void setDelChrgInd(DelChrgIndType value) {
        this.delChrgInd = value;
    }

}
