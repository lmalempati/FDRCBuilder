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
 * <p>Java class for FleetResponseDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FleetResponseDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CommonGrp" type="{com/fiserv/Merchant/gmfV10.02}CommonGrp" minOccurs="0"/>
 *         &lt;element name="CardGrp" type="{com/fiserv/Merchant/gmfV10.02}CardGrp" minOccurs="0"/>
 *         &lt;element name="AddtlAmtGrp" type="{com/fiserv/Merchant/gmfV10.02}AddtlAmtGrp" maxOccurs="6" minOccurs="0"/>
 *         &lt;element name="EMVGrp" type="{com/fiserv/Merchant/gmfV10.02}EMVGrp" minOccurs="0"/>
 *         &lt;element name="TAGrp" type="{com/fiserv/Merchant/gmfV10.02}TAGrp" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="VisaGrp" type="{com/fiserv/Merchant/gmfV10.02}VisaGrp" minOccurs="0"/>
 *           &lt;element name="MCGrp" type="{com/fiserv/Merchant/gmfV10.02}MCGrp" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="RespGrp" type="{com/fiserv/Merchant/gmfV10.02}RespGrp" minOccurs="0"/>
 *         &lt;element name="FileDLGrp" type="{com/fiserv/Merchant/gmfV10.02}FileDLGrp" minOccurs="0"/>
 *         &lt;element name="FltGrp" type="{com/fiserv/Merchant/gmfV10.02}FltGrp" minOccurs="0"/>
 *         &lt;element name="WexOTRGrp" type="{com/fiserv/Merchant/gmfV10.02}WexOTRGrp" minOccurs="0"/>
 *         &lt;element name="OTRProdDetGrp" type="{com/fiserv/Merchant/gmfV10.02}OTRProdDetGrp" maxOccurs="20" minOccurs="0"/>
 *         &lt;element name="OTRFuelLimitGrp" type="{com/fiserv/Merchant/gmfV10.02}OTRFuelLimitGrp" maxOccurs="12" minOccurs="0"/>
 *         &lt;element name="OTRPromptGrp" type="{com/fiserv/Merchant/gmfV10.02}OTRPromptGrp" maxOccurs="8" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FleetResponseDetails", propOrder = {
    "commonGrp",
    "cardGrp",
    "addtlAmtGrp",
    "emvGrp",
    "taGrp",
    "visaGrp",
    "mcGrp",
    "respGrp",
    "fileDLGrp",
    "fltGrp",
    "wexOTRGrp",
    "otrProdDetGrp",
    "otrFuelLimitGrp",
    "otrPromptGrp"
})
public class FleetResponseDetails {

    @XmlElement(name = "CommonGrp")
    protected CommonGrp commonGrp;
    @XmlElement(name = "CardGrp")
    protected CardGrp cardGrp;
    @XmlElement(name = "AddtlAmtGrp")
    protected List<AddtlAmtGrp> addtlAmtGrp;
    @XmlElement(name = "EMVGrp")
    protected EMVGrp emvGrp;
    @XmlElement(name = "TAGrp")
    protected TAGrp taGrp;
    @XmlElement(name = "VisaGrp")
    protected VisaGrp visaGrp;
    @XmlElement(name = "MCGrp")
    protected MCGrp mcGrp;
    @XmlElement(name = "RespGrp")
    protected RespGrp respGrp;
    @XmlElement(name = "FileDLGrp")
    protected FileDLGrp fileDLGrp;
    @XmlElement(name = "FltGrp")
    protected FltGrp fltGrp;
    @XmlElement(name = "WexOTRGrp")
    protected WexOTRGrp wexOTRGrp;
    @XmlElement(name = "OTRProdDetGrp")
    protected List<OTRProdDetGrp> otrProdDetGrp;
    @XmlElement(name = "OTRFuelLimitGrp")
    protected List<OTRFuelLimitGrp> otrFuelLimitGrp;
    @XmlElement(name = "OTRPromptGrp")
    protected List<OTRPromptGrp> otrPromptGrp;

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
     * Gets the value of the emvGrp property.
     * 
     * @return
     *     possible object is
     *     {@link EMVGrp }
     *     
     */
    public EMVGrp getEMVGrp() {
        return emvGrp;
    }

    /**
     * Sets the value of the emvGrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link EMVGrp }
     *     
     */
    public void setEMVGrp(EMVGrp value) {
        this.emvGrp = value;
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
     * Gets the value of the visaGrp property.
     * 
     * @return
     *     possible object is
     *     {@link VisaGrp }
     *     
     */
    public VisaGrp getVisaGrp() {
        return visaGrp;
    }

    /**
     * Sets the value of the visaGrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link VisaGrp }
     *     
     */
    public void setVisaGrp(VisaGrp value) {
        this.visaGrp = value;
    }

    /**
     * Gets the value of the mcGrp property.
     * 
     * @return
     *     possible object is
     *     {@link MCGrp }
     *     
     */
    public MCGrp getMCGrp() {
        return mcGrp;
    }

    /**
     * Sets the value of the mcGrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link MCGrp }
     *     
     */
    public void setMCGrp(MCGrp value) {
        this.mcGrp = value;
    }

    /**
     * Gets the value of the respGrp property.
     * 
     * @return
     *     possible object is
     *     {@link RespGrp }
     *     
     */
    public RespGrp getRespGrp() {
        return respGrp;
    }

    /**
     * Sets the value of the respGrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link RespGrp }
     *     
     */
    public void setRespGrp(RespGrp value) {
        this.respGrp = value;
    }

    /**
     * Gets the value of the fileDLGrp property.
     * 
     * @return
     *     possible object is
     *     {@link FileDLGrp }
     *     
     */
    public FileDLGrp getFileDLGrp() {
        return fileDLGrp;
    }

    /**
     * Sets the value of the fileDLGrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link FileDLGrp }
     *     
     */
    public void setFileDLGrp(FileDLGrp value) {
        this.fileDLGrp = value;
    }

    /**
     * Gets the value of the fltGrp property.
     * 
     * @return
     *     possible object is
     *     {@link FltGrp }
     *     
     */
    public FltGrp getFltGrp() {
        return fltGrp;
    }

    /**
     * Sets the value of the fltGrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link FltGrp }
     *     
     */
    public void setFltGrp(FltGrp value) {
        this.fltGrp = value;
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
     * Gets the value of the otrFuelLimitGrp property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the otrFuelLimitGrp property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOTRFuelLimitGrp().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OTRFuelLimitGrp }
     * 
     * 
     */
    public List<OTRFuelLimitGrp> getOTRFuelLimitGrp() {
        if (otrFuelLimitGrp == null) {
            otrFuelLimitGrp = new ArrayList<OTRFuelLimitGrp>();
        }
        return this.otrFuelLimitGrp;
    }

    /**
     * Gets the value of the otrPromptGrp property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the otrPromptGrp property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOTRPromptGrp().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OTRPromptGrp }
     * 
     * 
     */
    public List<OTRPromptGrp> getOTRPromptGrp() {
        if (otrPromptGrp == null) {
            otrPromptGrp = new ArrayList<OTRPromptGrp>();
        }
        return this.otrPromptGrp;
    }

}
