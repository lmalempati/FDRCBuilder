
package fdrc.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PreNetRtGrp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PreNetRtGrp">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{com/fiserv/Merchant/gmfV10.02}PreNetRtID" maxOccurs="5" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PreNetRtGrp", namespace = "com/fiserv/Merchant/gmfV10.02", propOrder = {
    "preNetRtID"
})
public class PreNetRtGrp {

    @XmlElement(name = "PreNetRtID", namespace = "com/fiserv/Merchant/gmfV10.02")
    protected List<String> preNetRtID;

    /**
     * Gets the value of the preNetRtID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the preNetRtID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPreNetRtID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPreNetRtID() {
        if (preNetRtID == null) {
            preNetRtID = new ArrayList<String>();
        }
        return this.preNetRtID;
    }

}
