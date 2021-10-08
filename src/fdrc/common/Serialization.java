package fdrc.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fdrc.proxy.CreditResponseDetails;
import fdrc.proxy.GMFMessageVariants;

import fdrc.xml.ObjectFactory;
import fdrc.xml.PayloadType;
import fdrc.xml.Response;
import fdrc.xml.TransactionType;

import javax.xml.bind.*;
import java.io.*;

public class Serialization {
    /* The below method will transform the transaction request object to an XML string in UTF-8 encoding.
     * It will convert gmfmv object into serialized XML data which will be sent to Data wire.
     * */
    public String GetXMLData(GMFMessageVariants gmfmv) {
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
            System.out.println("GetXMLData Exception: " + j);
            j.printStackTrace();
        } catch (Exception e) {
            System.out.println("GetXMLData Exception: " + e);
            e.printStackTrace();
        }
        return returnValue;
    }

    public GMFMessageVariants getObjectXML(String xml){ // , T type
        JAXBContext context = null;
        Unmarshaller unmarshaller = null;
        GMFMessageVariants gmf = new GMFMessageVariants();
        GMFMessageVariants g = null;

        try {
            xml = xml.replaceAll("GMF", "gmfMessageVariants");
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
