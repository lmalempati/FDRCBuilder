package fdrc.xml;
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2012.05.07 at 02:08:05 AM CDT
//

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransactionType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="TransactionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServiceID" type="{http://securetransport.dw/rcservice/xml}ServiceIDType"/>
 *         &lt;element name="Payload" type="{http://securetransport.dw/rcservice/xml}PayloadType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransactionType", propOrder = {
        "serviceID",
        "payload"
})
public class TransactionType {

    @XmlElement(name = "ServiceID", required = true)
    protected String serviceID;
    @XmlElement(name = "Payload", required = true)
    protected PayloadType payload;

    /**
     * Gets the value of the serviceID property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getServiceID() {
        return serviceID;
    }

    /**
     * Sets the value of the serviceID property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setServiceID(String value) {
        this.serviceID = value;
    }

    /**
     * Gets the value of the payload property.
     *
     * @return
     *     possible object is
     *     {@link PayloadType }
     *
     */
    public PayloadType getPayload() {
        return payload;
    }

    /**
     * Sets the value of the payload property.
     *
     * @param value
     *     allowed object is
     *     {@link PayloadType }
     *
     */
    public void setPayload(PayloadType value) {
        this.payload = value;
    }

}
