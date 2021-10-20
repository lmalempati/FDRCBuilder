package fdrc.common;

import com.fiserv.merchant.gmfv10.GMFMessageVariants;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Serialization {
    /* The below method will transform the transaction request object to an XML string in UTF-8 encoding.
     * It will convert gmfmv object into serialized XML data which will be sent to Data wire.
     * */
    public String getXMLData(GMFMessageVariants gmfmv, String error) {
        StringWriter stringWriter = new StringWriter();
        String xmlString = "";
        try {
            JAXBContext context = null;
            Marshaller marshaller = null;
            context = JAXBContext.newInstance(GMFMessageVariants.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.marshal(gmfmv, stringWriter);

            xmlString = stringWriter.toString();
        } catch (JAXBException j) {
            error = j.getMessage();
        } catch (Exception e) {
            error = e.getMessage();
        }
        return xmlString;
    }

    public GMFMessageVariants getObjectXML(String xml){ // , T type
        JAXBContext context = null;
        Unmarshaller unmarshaller = null;
        GMFMessageVariants gmf = new GMFMessageVariants();
        GMFMessageVariants g = null;

        try {
//            xml = xml.replaceAll("GMF", "gmfMessageVariants");
            context = JAXBContext.newInstance(GMFMessageVariants.class);
            unmarshaller = context.createUnmarshaller();
            g = (GMFMessageVariants)unmarshaller.unmarshal(new StringReader(xml));
//            g.getCreditResponse()
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return g;
    }

    public static void main(String[] args) {
        Object resp = new Serialization().getObjectXML("");
//        System.out.println(resp.getRespGrp().getRespCode());
    }
}
