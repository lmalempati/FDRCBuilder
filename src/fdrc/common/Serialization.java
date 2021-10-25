package fdrc.common;

import com.fiserv.merchant.gmfv10.GMFMessageVariants;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class Serialization {
    /* The below method will transform the transaction request object to an XML string in UTF-8 encoding.
     * It will convert gmfmv object into serialized XML data which will be sent to Data wire.
     * */
    public String getXMLData(GMFMessageVariants gmfmv, String error) {
        StringWriter stringWriter = new StringWriter();
        String returnValue = "";
        try {
            JAXBContext context = null;
            Marshaller marshaller = null;
            context = JAXBContext.newInstance(GMFMessageVariants.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.marshal(gmfmv, stringWriter);

            returnValue = stringWriter.toString();
        } catch (JAXBException j) {
            error = j.getMessage();
        } catch (Exception e) {
            error = e.getMessage();
        }
        return returnValue;
    }

    public GMFMessageVariants getObjectXML(String xml){ // , T type
        JAXBContext context = null;
        Unmarshaller unmarshaller = null;
        GMFMessageVariants gmf = new GMFMessageVariants();
        GMFMessageVariants g = null;

        try {
            // this is not needed after replacing proxys with gmf lib.
            // xml = xml.replaceAll("GMF", "gmfMessageVariants");
            context = JAXBContext.newInstance(GMFMessageVariants.class);
            unmarshaller = context.createUnmarshaller();
            g = (GMFMessageVariants)unmarshaller.unmarshal(new StringReader(xml));
//            g.getCreditResponse()
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return g;
    }
    // todo: figure out if we need to validate teh xml payload.
    public boolean validateXMLSchema(String xml){
        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File("UMF_XML_SCHEMA.xsd"));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xml));
        } catch (IOException e){
            System.out.println("Exception: "+e.getMessage());
            return false;
        }catch(SAXException e1){
            System.out.println("SAX Exception: "+e1.getMessage());
            return false;
        }

        return true;

    }

    public static void main(String[] args) {
        Object resp = new Serialization().getObjectXML("");
//        System.out.println(resp.getRespGrp().getRespCode());
    }
}
