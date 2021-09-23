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
 * <p>Java class for LodgingGrp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LodgingGrp">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}FolioNum" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}RoomNum" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}LodRefNum" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}RoomRt" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}RmTax" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}ProgramInd" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}ArvDate" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}DepDate" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}ArvTime" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}DepTime" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}Duration" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}HotelNoShow" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}LodPhnNum" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}CHName" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}LodChargeType" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}ExtraChrgs" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LodgingGrp", propOrder = {
    "folioNum",
    "roomNum",
    "lodRefNum",
    "roomRt",
    "rmTax",
    "programInd",
    "arvDate",
    "depDate",
    "arvTime",
    "depTime",
    "duration",
    "hotelNoShow",
    "lodPhnNum",
    "chName",
    "lodChargeType",
    "extraChrgs"
})
public class LodgingGrp {

    @XmlElement(name = "FolioNum")
    protected String folioNum;
    @XmlElement(name = "RoomNum")
    protected String roomNum;
    @XmlElement(name = "LodRefNum")
    protected String lodRefNum;
    @XmlElement(name = "RoomRt")
    protected String roomRt;
    @XmlElement(name = "RmTax")
    protected String rmTax;
    @XmlElement(name = "ProgramInd")
    protected String programInd;
    @XmlElement(name = "ArvDate")
    protected String arvDate;
    @XmlElement(name = "DepDate")
    protected String depDate;
    @XmlElement(name = "ArvTime")
    protected String arvTime;
    @XmlElement(name = "DepTime")
    protected String depTime;
    @XmlElement(name = "Duration")
    protected String duration;
    @XmlElement(name = "HotelNoShow")
    protected String hotelNoShow;
    @XmlElement(name = "LodPhnNum")
    protected String lodPhnNum;
    @XmlElement(name = "CHName")
    protected String chName;
    @XmlElement(name = "LodChargeType")
    @XmlSchemaType(name = "string")
    protected LodChargeTypeType lodChargeType;
    @XmlElement(name = "ExtraChrgs")
    protected String extraChrgs;

    /**
     * Gets the value of the folioNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolioNum() {
        return folioNum;
    }

    /**
     * Sets the value of the folioNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolioNum(String value) {
        this.folioNum = value;
    }

    /**
     * Gets the value of the roomNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomNum() {
        return roomNum;
    }

    /**
     * Sets the value of the roomNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomNum(String value) {
        this.roomNum = value;
    }

    /**
     * Gets the value of the lodRefNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLodRefNum() {
        return lodRefNum;
    }

    /**
     * Sets the value of the lodRefNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLodRefNum(String value) {
        this.lodRefNum = value;
    }

    /**
     * Gets the value of the roomRt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomRt() {
        return roomRt;
    }

    /**
     * Sets the value of the roomRt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomRt(String value) {
        this.roomRt = value;
    }

    /**
     * Gets the value of the rmTax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRmTax() {
        return rmTax;
    }

    /**
     * Sets the value of the rmTax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRmTax(String value) {
        this.rmTax = value;
    }

    /**
     * Gets the value of the programInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProgramInd() {
        return programInd;
    }

    /**
     * Sets the value of the programInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProgramInd(String value) {
        this.programInd = value;
    }

    /**
     * Gets the value of the arvDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArvDate() {
        return arvDate;
    }

    /**
     * Sets the value of the arvDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArvDate(String value) {
        this.arvDate = value;
    }

    /**
     * Gets the value of the depDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepDate() {
        return depDate;
    }

    /**
     * Sets the value of the depDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepDate(String value) {
        this.depDate = value;
    }

    /**
     * Gets the value of the arvTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArvTime() {
        return arvTime;
    }

    /**
     * Sets the value of the arvTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArvTime(String value) {
        this.arvTime = value;
    }

    /**
     * Gets the value of the depTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepTime() {
        return depTime;
    }

    /**
     * Sets the value of the depTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepTime(String value) {
        this.depTime = value;
    }

    /**
     * Gets the value of the duration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDuration(String value) {
        this.duration = value;
    }

    /**
     * Gets the value of the hotelNoShow property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHotelNoShow() {
        return hotelNoShow;
    }

    /**
     * Sets the value of the hotelNoShow property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHotelNoShow(String value) {
        this.hotelNoShow = value;
    }

    /**
     * Gets the value of the lodPhnNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLodPhnNum() {
        return lodPhnNum;
    }

    /**
     * Sets the value of the lodPhnNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLodPhnNum(String value) {
        this.lodPhnNum = value;
    }

    /**
     * Gets the value of the chName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCHName() {
        return chName;
    }

    /**
     * Sets the value of the chName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCHName(String value) {
        this.chName = value;
    }

    /**
     * Gets the value of the lodChargeType property.
     * 
     * @return
     *     possible object is
     *     {@link LodChargeTypeType }
     *     
     */
    public LodChargeTypeType getLodChargeType() {
        return lodChargeType;
    }

    /**
     * Sets the value of the lodChargeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link LodChargeTypeType }
     *     
     */
    public void setLodChargeType(LodChargeTypeType value) {
        this.lodChargeType = value;
    }

    /**
     * Gets the value of the extraChrgs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtraChrgs() {
        return extraChrgs;
    }

    /**
     * Sets the value of the extraChrgs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtraChrgs(String value) {
        this.extraChrgs = value;
    }

}
