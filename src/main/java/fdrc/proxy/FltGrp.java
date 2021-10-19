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
 * <p>Java class for FltGrp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FltGrp">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}Odo" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}VehNum" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}JobNum" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}DrvNum" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}FltEmpNum" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}LicNum" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}JobID" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}DeptNum" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}CustData" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}UserID" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}VehIDNum" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}ServResCd" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}TrailerID" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}ReeferHr" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}ExtDrvID" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}UnitID" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}DrvLicState" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}VehLicState" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}TrailerLic" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}TrailerLicState" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}FleetID" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}PurchaseOrder" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}MaintenanceID" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}Hubometer" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}TripNumber" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}VoyagerData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FltGrp", propOrder = {
    "odo",
    "vehNum",
    "jobNum",
    "drvNum",
    "fltEmpNum",
    "licNum",
    "jobID",
    "deptNum",
    "custData",
    "userID",
    "vehIDNum",
    "servResCd",
    "trailerID",
    "reeferHr",
    "extDrvID",
    "unitID",
    "drvLicState",
    "vehLicState",
    "trailerLic",
    "trailerLicState",
    "fleetID",
    "purchaseOrder",
    "maintenanceID",
    "hubometer",
    "tripNumber",
    "voyagerData"
})
public class FltGrp {

    @XmlElement(name = "Odo")
    protected String odo;
    @XmlElement(name = "VehNum")
    protected String vehNum;
    @XmlElement(name = "JobNum")
    protected String jobNum;
    @XmlElement(name = "DrvNum")
    protected String drvNum;
    @XmlElement(name = "FltEmpNum")
    protected String fltEmpNum;
    @XmlElement(name = "LicNum")
    protected String licNum;
    @XmlElement(name = "JobID")
    protected String jobID;
    @XmlElement(name = "DeptNum")
    protected String deptNum;
    @XmlElement(name = "CustData")
    protected String custData;
    @XmlElement(name = "UserID")
    protected String userID;
    @XmlElement(name = "VehIDNum")
    protected String vehIDNum;
    @XmlElement(name = "ServResCd")
    protected String servResCd;
    @XmlElement(name = "TrailerID")
    protected String trailerID;
    @XmlElement(name = "ReeferHr")
    protected String reeferHr;
    @XmlElement(name = "ExtDrvID")
    protected String extDrvID;
    @XmlElement(name = "UnitID")
    protected String unitID;
    @XmlElement(name = "DrvLicState")
    @XmlSchemaType(name = "string")
    protected StateCodeType drvLicState;
    @XmlElement(name = "VehLicState")
    @XmlSchemaType(name = "string")
    protected StateCodeType vehLicState;
    @XmlElement(name = "TrailerLic")
    protected String trailerLic;
    @XmlElement(name = "TrailerLicState")
    @XmlSchemaType(name = "string")
    protected StateCodeType trailerLicState;
    @XmlElement(name = "FleetID")
    protected String fleetID;
    @XmlElement(name = "PurchaseOrder")
    protected String purchaseOrder;
    @XmlElement(name = "MaintenanceID")
    protected String maintenanceID;
    @XmlElement(name = "Hubometer")
    protected String hubometer;
    @XmlElement(name = "TripNumber")
    protected String tripNumber;
    @XmlElement(name = "VoyagerData")
    protected String voyagerData;

    /**
     * Gets the value of the odo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOdo() {
        return odo;
    }

    /**
     * Sets the value of the odo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOdo(String value) {
        this.odo = value;
    }

    /**
     * Gets the value of the vehNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVehNum() {
        return vehNum;
    }

    /**
     * Sets the value of the vehNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVehNum(String value) {
        this.vehNum = value;
    }

    /**
     * Gets the value of the jobNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJobNum() {
        return jobNum;
    }

    /**
     * Sets the value of the jobNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJobNum(String value) {
        this.jobNum = value;
    }

    /**
     * Gets the value of the drvNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrvNum() {
        return drvNum;
    }

    /**
     * Sets the value of the drvNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrvNum(String value) {
        this.drvNum = value;
    }

    /**
     * Gets the value of the fltEmpNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFltEmpNum() {
        return fltEmpNum;
    }

    /**
     * Sets the value of the fltEmpNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFltEmpNum(String value) {
        this.fltEmpNum = value;
    }

    /**
     * Gets the value of the licNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLicNum() {
        return licNum;
    }

    /**
     * Sets the value of the licNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLicNum(String value) {
        this.licNum = value;
    }

    /**
     * Gets the value of the jobID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJobID() {
        return jobID;
    }

    /**
     * Sets the value of the jobID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJobID(String value) {
        this.jobID = value;
    }

    /**
     * Gets the value of the deptNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeptNum() {
        return deptNum;
    }

    /**
     * Sets the value of the deptNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeptNum(String value) {
        this.deptNum = value;
    }

    /**
     * Gets the value of the custData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustData() {
        return custData;
    }

    /**
     * Sets the value of the custData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustData(String value) {
        this.custData = value;
    }

    /**
     * Gets the value of the userID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets the value of the userID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserID(String value) {
        this.userID = value;
    }

    /**
     * Gets the value of the vehIDNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVehIDNum() {
        return vehIDNum;
    }

    /**
     * Sets the value of the vehIDNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVehIDNum(String value) {
        this.vehIDNum = value;
    }

    /**
     * Gets the value of the servResCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServResCd() {
        return servResCd;
    }

    /**
     * Sets the value of the servResCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServResCd(String value) {
        this.servResCd = value;
    }

    /**
     * Gets the value of the trailerID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrailerID() {
        return trailerID;
    }

    /**
     * Sets the value of the trailerID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrailerID(String value) {
        this.trailerID = value;
    }

    /**
     * Gets the value of the reeferHr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReeferHr() {
        return reeferHr;
    }

    /**
     * Sets the value of the reeferHr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReeferHr(String value) {
        this.reeferHr = value;
    }

    /**
     * Gets the value of the extDrvID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtDrvID() {
        return extDrvID;
    }

    /**
     * Sets the value of the extDrvID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtDrvID(String value) {
        this.extDrvID = value;
    }

    /**
     * Gets the value of the unitID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitID() {
        return unitID;
    }

    /**
     * Sets the value of the unitID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitID(String value) {
        this.unitID = value;
    }

    /**
     * Gets the value of the drvLicState property.
     * 
     * @return
     *     possible object is
     *     {@link StateCodeType }
     *     
     */
    public StateCodeType getDrvLicState() {
        return drvLicState;
    }

    /**
     * Sets the value of the drvLicState property.
     * 
     * @param value
     *     allowed object is
     *     {@link StateCodeType }
     *     
     */
    public void setDrvLicState(StateCodeType value) {
        this.drvLicState = value;
    }

    /**
     * Gets the value of the vehLicState property.
     * 
     * @return
     *     possible object is
     *     {@link StateCodeType }
     *     
     */
    public StateCodeType getVehLicState() {
        return vehLicState;
    }

    /**
     * Sets the value of the vehLicState property.
     * 
     * @param value
     *     allowed object is
     *     {@link StateCodeType }
     *     
     */
    public void setVehLicState(StateCodeType value) {
        this.vehLicState = value;
    }

    /**
     * Gets the value of the trailerLic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrailerLic() {
        return trailerLic;
    }

    /**
     * Sets the value of the trailerLic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrailerLic(String value) {
        this.trailerLic = value;
    }

    /**
     * Gets the value of the trailerLicState property.
     * 
     * @return
     *     possible object is
     *     {@link StateCodeType }
     *     
     */
    public StateCodeType getTrailerLicState() {
        return trailerLicState;
    }

    /**
     * Sets the value of the trailerLicState property.
     * 
     * @param value
     *     allowed object is
     *     {@link StateCodeType }
     *     
     */
    public void setTrailerLicState(StateCodeType value) {
        this.trailerLicState = value;
    }

    /**
     * Gets the value of the fleetID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFleetID() {
        return fleetID;
    }

    /**
     * Sets the value of the fleetID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFleetID(String value) {
        this.fleetID = value;
    }

    /**
     * Gets the value of the purchaseOrder property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPurchaseOrder() {
        return purchaseOrder;
    }

    /**
     * Sets the value of the purchaseOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPurchaseOrder(String value) {
        this.purchaseOrder = value;
    }

    /**
     * Gets the value of the maintenanceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaintenanceID() {
        return maintenanceID;
    }

    /**
     * Sets the value of the maintenanceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaintenanceID(String value) {
        this.maintenanceID = value;
    }

    /**
     * Gets the value of the hubometer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHubometer() {
        return hubometer;
    }

    /**
     * Sets the value of the hubometer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHubometer(String value) {
        this.hubometer = value;
    }

    /**
     * Gets the value of the tripNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTripNumber() {
        return tripNumber;
    }

    /**
     * Sets the value of the tripNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTripNumber(String value) {
        this.tripNumber = value;
    }

    /**
     * Gets the value of the voyagerData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVoyagerData() {
        return voyagerData;
    }

    /**
     * Sets the value of the voyagerData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVoyagerData(String value) {
        this.voyagerData = value;
    }

}
