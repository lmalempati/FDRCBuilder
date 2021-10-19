//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.01.27 at 03:21:12 PM EST 
//


package fdrc.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CheckRequestDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CheckRequestDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CommonGrp" type="{com/fiserv/Merchant/gmfV10.02}CommonGrp" minOccurs="0"/>
 *         &lt;element name="BillPayGrp" type="{com/fiserv/Merchant/gmfV10.02}BillPayGrp" minOccurs="0"/>
 *         &lt;element name="AltMerchNameAndAddrGrp" type="{com/fiserv/Merchant/gmfV10.02}AltMerchNameAndAddrGrp" minOccurs="0"/>
 *         &lt;element name="CheckGrp" type="{com/fiserv/Merchant/gmfV10.02}CheckGrp" minOccurs="0"/>
 *         &lt;element name="TeleCheckECAGrp" type="{com/fiserv/Merchant/gmfV10.02}TeleCheckECAGrp" minOccurs="0"/>
 *         &lt;element name="TCNFTFGrp" type="{com/fiserv/Merchant/gmfV10.02}TCNFTFGrp" minOccurs="0"/>
 *         &lt;element name="AddtlAmtGrp" type="{com/fiserv/Merchant/gmfV10.02}AddtlAmtGrp" maxOccurs="6" minOccurs="0"/>
 *         &lt;element name="TAGrp" type="{com/fiserv/Merchant/gmfV10.02}TAGrp" minOccurs="0"/>
 *         &lt;element name="EcommGrp" type="{com/fiserv/Merchant/gmfV10.02}EcommGrp" minOccurs="0"/>
 *         &lt;element name="CustInfoGrp" type="{com/fiserv/Merchant/gmfV10.02}CustInfoGrp" minOccurs="0"/>
 *         &lt;element name="ShipGrp" type="{com/fiserv/Merchant/gmfV10.02}ShipGrp" minOccurs="0"/>
 *         &lt;element name="OrigAuthGrp" type="{com/fiserv/Merchant/gmfV10.02}OrigAuthGrp" minOccurs="0"/>
 *         &lt;element name="ProdCodeGrp" type="{com/fiserv/Merchant/gmfV10.02}ProdCodeGrp" minOccurs="0"/>
 *         &lt;element name="ProdCodeDetGrp" type="{com/fiserv/Merchant/gmfV10.02}ProdCodeDetGrp" maxOccurs="10" minOccurs="0"/>
 *         &lt;element name="WexOTRGrp" type="{com/fiserv/Merchant/gmfV10.02}WexOTRGrp" minOccurs="0"/>
 *         &lt;element name="OTRProdDetGrp" type="{com/fiserv/Merchant/gmfV10.02}OTRProdDetGrp" maxOccurs="20" minOccurs="0"/>
 *         &lt;element name="FraudMitGrp" type="{com/fiserv/Merchant/gmfV10.02}FraudMitGrp" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckRequestDetails", propOrder = {
    "commonGrp",
    "billPayGrp",
    "altMerchNameAndAddrGrp",
    "checkGrp",
    "teleCheckECAGrp",
    "tcnftfGrp",
    "addtlAmtGrp",
    "taGrp",
    "ecommGrp",
    "custInfoGrp",
    "shipGrp",
    "origAuthGrp",
    "prodCodeGrp",
    "prodCodeDetGrp",
    "wexOTRGrp",
    "otrProdDetGrp",
    "fraudMitGrp"
})
public class CheckRequestDetails {

    @XmlElement(name = "CommonGrp")
    protected CommonGrp commonGrp;
    @XmlElement(name = "BillPayGrp")
    protected BillPayGrp billPayGrp;
    @XmlElement(name = "AltMerchNameAndAddrGrp")
    protected AltMerchNameAndAddrGrp altMerchNameAndAddrGrp;
    @XmlElement(name = "CheckGrp")
    protected CheckGrp checkGrp;
    @XmlElement(name = "TeleCheckECAGrp")
    protected TeleCheckECAGrp teleCheckECAGrp;
    @XmlElement(name = "TCNFTFGrp")
    protected TCNFTFGrp tcnftfGrp;
    @XmlElement(name = "AddtlAmtGrp")
    protected List<AddtlAmtGrp> addtlAmtGrp;
    @XmlElement(name = "TAGrp")
    protected TAGrp taGrp;
    @XmlElement(name = "EcommGrp")
    protected EcommGrp ecommGrp;
    @XmlElement(name = "CustInfoGrp")
    protected CustInfoGrp custInfoGrp;
    @XmlElement(name = "ShipGrp")
    protected ShipGrp shipGrp;
    @XmlElement(name = "OrigAuthGrp")
    protected OrigAuthGrp origAuthGrp;
    @XmlElement(name = "ProdCodeGrp")
    protected ProdCodeGrp prodCodeGrp;
    @XmlElement(name = "ProdCodeDetGrp")
    protected List<ProdCodeDetGrp> prodCodeDetGrp;
    @XmlElement(name = "WexOTRGrp")
    protected WexOTRGrp wexOTRGrp;
    @XmlElement(name = "OTRProdDetGrp")
    protected List<OTRProdDetGrp> otrProdDetGrp;
    @XmlElement(name = "FraudMitGrp")
    protected FraudMitGrp fraudMitGrp;

    /**
     * Gets the value of the commonGrp property.
     * 
     * @return
     *     possible object is
     *     {@link CommonGrp }
     *     
     */
    public CommonGrp getCommonGrp() {
        return commonGrp;
    }

    /**
     * Sets the value of the commonGrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link CommonGrp }
     *     
     */
    public void setCommonGrp(CommonGrp value) {
        this.commonGrp = value;
    }

    /**
     * Gets the value of the billPayGrp property.
     * 
     * @return
     *     possible object is
     *     {@link BillPayGrp }
     *     
     */
    public BillPayGrp getBillPayGrp() {
        return billPayGrp;
    }

    /**
     * Sets the value of the billPayGrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link BillPayGrp }
     *     
     */
    public void setBillPayGrp(BillPayGrp value) {
        this.billPayGrp = value;
    }

    /**
     * Gets the value of the altMerchNameAndAddrGrp property.
     * 
     * @return
     *     possible object is
     *     {@link AltMerchNameAndAddrGrp }
     *     
     */
    public AltMerchNameAndAddrGrp getAltMerchNameAndAddrGrp() {
        return altMerchNameAndAddrGrp;
    }

    /**
     * Sets the value of the altMerchNameAndAddrGrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link AltMerchNameAndAddrGrp }
     *     
     */
    public void setAltMerchNameAndAddrGrp(AltMerchNameAndAddrGrp value) {
        this.altMerchNameAndAddrGrp = value;
    }

    /**
     * Gets the value of the checkGrp property.
     * 
     * @return
     *     possible object is
     *     {@link CheckGrp }
     *     
     */
    public CheckGrp getCheckGrp() {
        return checkGrp;
    }

    /**
     * Sets the value of the checkGrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link CheckGrp }
     *     
     */
    public void setCheckGrp(CheckGrp value) {
        this.checkGrp = value;
    }

    /**
     * Gets the value of the teleCheckECAGrp property.
     * 
     * @return
     *     possible object is
     *     {@link TeleCheckECAGrp }
     *     
     */
    public TeleCheckECAGrp getTeleCheckECAGrp() {
        return teleCheckECAGrp;
    }

    /**
     * Sets the value of the teleCheckECAGrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link TeleCheckECAGrp }
     *     
     */
    public void setTeleCheckECAGrp(TeleCheckECAGrp value) {
        this.teleCheckECAGrp = value;
    }

    /**
     * Gets the value of the tcnftfGrp property.
     * 
     * @return
     *     possible object is
     *     {@link TCNFTFGrp }
     *     
     */
    public TCNFTFGrp getTCNFTFGrp() {
        return tcnftfGrp;
    }

    /**
     * Sets the value of the tcnftfGrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link TCNFTFGrp }
     *     
     */
    public void setTCNFTFGrp(TCNFTFGrp value) {
        this.tcnftfGrp = value;
    }

    /**
     * Gets the value of the addtlAmtGrp property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the addtlAmtGrp property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddtlAmtGrp().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AddtlAmtGrp }
     * 
     * 
     */
    public List<AddtlAmtGrp> getAddtlAmtGrp() {
        if (addtlAmtGrp == null) {
            addtlAmtGrp = new ArrayList<AddtlAmtGrp>();
        }
        return this.addtlAmtGrp;
    }

    /**
     * Gets the value of the taGrp property.
     * 
     * @return
     *     possible object is
     *     {@link TAGrp }
     *     
     */
    public TAGrp getTAGrp() {
        return taGrp;
    }

    /**
     * Sets the value of the taGrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link TAGrp }
     *     
     */
    public void setTAGrp(TAGrp value) {
        this.taGrp = value;
    }

    /**
     * Gets the value of the ecommGrp property.
     * 
     * @return
     *     possible object is
     *     {@link EcommGrp }
     *     
     */
    public EcommGrp getEcommGrp() {
        return ecommGrp;
    }

    /**
     * Sets the value of the ecommGrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link EcommGrp }
     *     
     */
    public void setEcommGrp(EcommGrp value) {
        this.ecommGrp = value;
    }

    /**
     * Gets the value of the custInfoGrp property.
     * 
     * @return
     *     possible object is
     *     {@link CustInfoGrp }
     *     
     */
    public CustInfoGrp getCustInfoGrp() {
        return custInfoGrp;
    }

    /**
     * Sets the value of the custInfoGrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustInfoGrp }
     *     
     */
    public void setCustInfoGrp(CustInfoGrp value) {
        this.custInfoGrp = value;
    }

    /**
     * Gets the value of the shipGrp property.
     * 
     * @return
     *     possible object is
     *     {@link ShipGrp }
     *     
     */
    public ShipGrp getShipGrp() {
        return shipGrp;
    }

    /**
     * Sets the value of the shipGrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipGrp }
     *     
     */
    public void setShipGrp(ShipGrp value) {
        this.shipGrp = value;
    }

    /**
     * Gets the value of the origAuthGrp property.
     * 
     * @return
     *     possible object is
     *     {@link OrigAuthGrp }
     *     
     */
    public OrigAuthGrp getOrigAuthGrp() {
        return origAuthGrp;
    }

    /**
     * Sets the value of the origAuthGrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrigAuthGrp }
     *     
     */
    public void setOrigAuthGrp(OrigAuthGrp value) {
        this.origAuthGrp = value;
    }

    /**
     * Gets the value of the prodCodeGrp property.
     * 
     * @return
     *     possible object is
     *     {@link ProdCodeGrp }
     *     
     */
    public ProdCodeGrp getProdCodeGrp() {
        return prodCodeGrp;
    }

    /**
     * Sets the value of the prodCodeGrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProdCodeGrp }
     *     
     */
    public void setProdCodeGrp(ProdCodeGrp value) {
        this.prodCodeGrp = value;
    }

    /**
     * Gets the value of the prodCodeDetGrp property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the prodCodeDetGrp property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProdCodeDetGrp().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProdCodeDetGrp }
     * 
     * 
     */
    public List<ProdCodeDetGrp> getProdCodeDetGrp() {
        if (prodCodeDetGrp == null) {
            prodCodeDetGrp = new ArrayList<ProdCodeDetGrp>();
        }
        return this.prodCodeDetGrp;
    }

    /**
     * Gets the value of the wexOTRGrp property.
     * 
     * @return
     *     possible object is
     *     {@link WexOTRGrp }
     *     
     */
    public WexOTRGrp getWexOTRGrp() {
        return wexOTRGrp;
    }

    /**
     * Sets the value of the wexOTRGrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link WexOTRGrp }
     *     
     */
    public void setWexOTRGrp(WexOTRGrp value) {
        this.wexOTRGrp = value;
    }

    /**
     * Gets the value of the otrProdDetGrp property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the otrProdDetGrp property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOTRProdDetGrp().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OTRProdDetGrp }
     * 
     * 
     */
    public List<OTRProdDetGrp> getOTRProdDetGrp() {
        if (otrProdDetGrp == null) {
            otrProdDetGrp = new ArrayList<OTRProdDetGrp>();
        }
        return this.otrProdDetGrp;
    }

    /**
     * Gets the value of the fraudMitGrp property.
     * 
     * @return
     *     possible object is
     *     {@link FraudMitGrp }
     *     
     */
    public FraudMitGrp getFraudMitGrp() {
        return fraudMitGrp;
    }

    /**
     * Sets the value of the fraudMitGrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link FraudMitGrp }
     *     
     */
    public void setFraudMitGrp(FraudMitGrp value) {
        this.fraudMitGrp = value;
    }

}
