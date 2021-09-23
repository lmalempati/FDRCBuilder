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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PinlessDebitRequestDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PinlessDebitRequestDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CommonGrp" type="{com/fiserv/Merchant/gmfV10.02}CommonGrp" minOccurs="0"/>
 *         &lt;element name="BillPayGrp" type="{com/fiserv/Merchant/gmfV10.02}BillPayGrp" minOccurs="0"/>
 *         &lt;element name="AltMerchNameAndAddrGrp" type="{com/fiserv/Merchant/gmfV10.02}AltMerchNameAndAddrGrp" minOccurs="0"/>
 *         &lt;element name="CardGrp" type="{com/fiserv/Merchant/gmfV10.02}CardGrp" minOccurs="0"/>
 *         &lt;element name="TAGrp" type="{com/fiserv/Merchant/gmfV10.02}TAGrp" minOccurs="0"/>
 *         &lt;element name="EcommGrp" type="{com/fiserv/Merchant/gmfV10.02}EcommGrp" minOccurs="0"/>
 *         &lt;element name="DebitGrp" type="{com/fiserv/Merchant/gmfV10.02}DebitGrp" minOccurs="0"/>
 *         &lt;element name="CustInfoGrp" type="{com/fiserv/Merchant/gmfV10.02}CustInfoGrp" minOccurs="0"/>
 *         &lt;element name="OrderGrp" type="{com/fiserv/Merchant/gmfV10.02}OrderGrp" minOccurs="0"/>
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
@XmlType(name = "PinlessDebitRequestDetails", propOrder = {
    "commonGrp",
    "billPayGrp",
    "altMerchNameAndAddrGrp",
    "cardGrp",
    "taGrp",
    "ecommGrp",
    "debitGrp",
    "custInfoGrp",
    "orderGrp",
    "fraudMitGrp"
})
public class PinlessDebitRequestDetails {

    @XmlElement(name = "CommonGrp")
    protected CommonGrp commonGrp;
    @XmlElement(name = "BillPayGrp")
    protected BillPayGrp billPayGrp;
    @XmlElement(name = "AltMerchNameAndAddrGrp")
    protected AltMerchNameAndAddrGrp altMerchNameAndAddrGrp;
    @XmlElement(name = "CardGrp")
    protected CardGrp cardGrp;
    @XmlElement(name = "TAGrp")
    protected TAGrp taGrp;
    @XmlElement(name = "EcommGrp")
    protected EcommGrp ecommGrp;
    @XmlElement(name = "DebitGrp")
    protected DebitGrp debitGrp;
    @XmlElement(name = "CustInfoGrp")
    protected CustInfoGrp custInfoGrp;
    @XmlElement(name = "OrderGrp")
    protected OrderGrp orderGrp;
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
     * Gets the value of the cardGrp property.
     * 
     * @return
     *     possible object is
     *     {@link CardGrp }
     *     
     */
    public CardGrp getCardGrp() {
        return cardGrp;
    }

    /**
     * Sets the value of the cardGrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardGrp }
     *     
     */
    public void setCardGrp(CardGrp value) {
        this.cardGrp = value;
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
     * Gets the value of the debitGrp property.
     * 
     * @return
     *     possible object is
     *     {@link DebitGrp }
     *     
     */
    public DebitGrp getDebitGrp() {
        return debitGrp;
    }

    /**
     * Sets the value of the debitGrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link DebitGrp }
     *     
     */
    public void setDebitGrp(DebitGrp value) {
        this.debitGrp = value;
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
     * Gets the value of the orderGrp property.
     * 
     * @return
     *     possible object is
     *     {@link OrderGrp }
     *     
     */
    public OrderGrp getOrderGrp() {
        return orderGrp;
    }

    /**
     * Sets the value of the orderGrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderGrp }
     *     
     */
    public void setOrderGrp(OrderGrp value) {
        this.orderGrp = value;
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
