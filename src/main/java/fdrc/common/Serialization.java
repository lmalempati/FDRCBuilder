package fdrc.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.fiserv.merchant.gmfv10.GMFMessageVariants;

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
            JacksonXmlModule xmlModule = new JacksonXmlModule();
            xmlModule.setDefaultUseWrapper(false);
            ObjectMapper mapper = new XmlMapper(xmlModule);
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
            mapper.registerModule(new JaxbAnnotationModule());
            returnValue = mapper.writeValueAsString(gmfmv);
            returnValue = returnValue.replaceAll("<GMF>", "<GMF xmlns=\"com/fiserv/Merchant/gmfV10.02\">"); // todo: hardcoded?????
        } catch (Exception e) {
            error = e.getMessage();
        }
        return returnValue;
    }

    public GMFMessageVariants getObjectXML(String xml) { // , T type
        GMFMessageVariants gmf = null;

        JacksonXmlModule module = new JacksonXmlModule();
        module.setXMLTextElementName("Payload");
        module.setDefaultUseWrapper(false);

        XmlMapper mapper = (XmlMapper) new XmlMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false)
                .configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true);

        try {
            gmf = (GMFMessageVariants) mapper.readValue(new StringReader(xml), GMFMessageVariants.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gmf;
    }

    public static void main(String[] args) {
//        Object resp = new Serialization().getObjectXML("");
//        System.out.println(resp.getRespGrp().getRespCode());
    }
}
