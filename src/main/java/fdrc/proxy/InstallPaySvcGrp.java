
package fdrc.proxy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InstallPaySvcGrp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InstallPaySvcGrp">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}InstallType" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}InstallPymtOptions" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}NoOfInstall" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}InstallIntRate" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}InstallFee" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}InstallAPR" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}FirstInstallAmt" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}SubInstallAmt" minOccurs="0"/>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}TotInstallAmtDue" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InstallPaySvcGrp", namespace = "com/fiserv/Merchant/gmfV10.02", propOrder = {
    "installType",
    "installPymtOptions",
    "noOfInstall",
    "installIntRate",
    "installFee",
    "installAPR",
    "firstInstallAmt",
    "subInstallAmt",
    "totInstallAmtDue"
})
public class InstallPaySvcGrp {

    @XmlElement(name = "InstallType", namespace = "com/fiserv/Merchant/gmfV10.02")
    protected String installType;
    @XmlElement(name = "InstallPymtOptions", namespace = "com/fiserv/Merchant/gmfV10.02")
    @XmlSchemaType(name = "string")
    protected InstallPymtOptionsType installPymtOptions;
    @XmlElement(name = "NoOfInstall", namespace = "com/fiserv/Merchant/gmfV10.02")
    protected String noOfInstall;
    @XmlElement(name = "InstallIntRate", namespace = "com/fiserv/Merchant/gmfV10.02")
    protected String installIntRate;
    @XmlElement(name = "InstallFee", namespace = "com/fiserv/Merchant/gmfV10.02")
    protected String installFee;
    @XmlElement(name = "InstallAPR", namespace = "com/fiserv/Merchant/gmfV10.02")
    protected String installAPR;
    @XmlElement(name = "FirstInstallAmt", namespace = "com/fiserv/Merchant/gmfV10.02")
    protected String firstInstallAmt;
    @XmlElement(name = "SubInstallAmt", namespace = "com/fiserv/Merchant/gmfV10.02")
    protected String subInstallAmt;
    @XmlElement(name = "TotInstallAmtDue", namespace = "com/fiserv/Merchant/gmfV10.02")
    protected String totInstallAmtDue;

    /**
     * Gets the value of the installType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstallType() {
        return installType;
    }

    /**
     * Sets the value of the installType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstallType(String value) {
        this.installType = value;
    }

    /**
     * Gets the value of the installPymtOptions property.
     * 
     * @return
     *     possible object is
     *     {@link InstallPymtOptionsType }
     *     
     */
    public InstallPymtOptionsType getInstallPymtOptions() {
        return installPymtOptions;
    }

    /**
     * Sets the value of the installPymtOptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link InstallPymtOptionsType }
     *     
     */
    public void setInstallPymtOptions(InstallPymtOptionsType value) {
        this.installPymtOptions = value;
    }

    /**
     * Gets the value of the noOfInstall property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoOfInstall() {
        return noOfInstall;
    }

    /**
     * Sets the value of the noOfInstall property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoOfInstall(String value) {
        this.noOfInstall = value;
    }

    /**
     * Gets the value of the installIntRate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstallIntRate() {
        return installIntRate;
    }

    /**
     * Sets the value of the installIntRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstallIntRate(String value) {
        this.installIntRate = value;
    }

    /**
     * Gets the value of the installFee property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstallFee() {
        return installFee;
    }

    /**
     * Sets the value of the installFee property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstallFee(String value) {
        this.installFee = value;
    }

    /**
     * Gets the value of the installAPR property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstallAPR() {
        return installAPR;
    }

    /**
     * Sets the value of the installAPR property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstallAPR(String value) {
        this.installAPR = value;
    }

    /**
     * Gets the value of the firstInstallAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstInstallAmt() {
        return firstInstallAmt;
    }

    /**
     * Sets the value of the firstInstallAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstInstallAmt(String value) {
        this.firstInstallAmt = value;
    }

    /**
     * Gets the value of the subInstallAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubInstallAmt() {
        return subInstallAmt;
    }

    /**
     * Sets the value of the subInstallAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubInstallAmt(String value) {
        this.subInstallAmt = value;
    }

    /**
     * Gets the value of the totInstallAmtDue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotInstallAmtDue() {
        return totInstallAmtDue;
    }

    /**
     * Sets the value of the totInstallAmtDue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotInstallAmtDue(String value) {
        this.totInstallAmtDue = value;
    }

}
