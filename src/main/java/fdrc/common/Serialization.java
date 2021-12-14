package fdrc.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.fiserv.merchant.gmfv10.GMFMessageVariants;
import fdrc.Exceptions.InvalidResponseXml;
import fdrc.base.Constants;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class Serialization {
    /* The below method will transform the transaction request object to an XML string in UTF-8 encoding.
     * It will convert gmfmv object into serialized XML data which will be sent to Data wire.
     * */
    public String getXMLPayload(GMFMessageVariants gmfmv, String error) {
        StringWriter stringWriter = new StringWriter();
        String returnValue = "";
        try {
            JacksonXmlModule xmlModule = new JacksonXmlModule();
            xmlModule.setDefaultUseWrapper(false);
            ObjectMapper mapper = new XmlMapper(xmlModule);

//            xmlModule.addSerializer(JAXBElement.class, new JsonSerializer<JAXBElement>() {
//                @Override
//                public void serialize(JAXBElement value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
//                    if (value.isNil()) {
//                        gen.writeNull();
//                    } else {
//                        gen.writeObject(value.getValue());
//                    }
//                }
//            });

//            mapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
//            XmlJaxbAnnotationIntrospector j = new XmlJaxbAnnotationIntrospector();
            // j.findNamespace(mapper.getSerializationConfig(), )
//            mapper.setAnnotationIntrospector(new XmlJaxbAnnotationIntrospector());

            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // to ignore empty groups
            mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
            mapper.registerModule(new JaxbAnnotationModule()); // to follow xml annotations on model classes
            returnValue = mapper.writeValueAsString(gmfmv);
            returnValue = returnValue.replaceAll(Constants.GMF, Constants.GMF_NS); // todo: xml not getting namespace, hardcoded?????
        } catch (JacksonException e) {
            error = e.getMessage();
        } catch (Exception e) {
            error = e.getMessage();
        }
        return returnValue;
    }

    public GMFMessageVariants getObjectXML(String xml) { // , T type
        GMFMessageVariants gmf = null;
        String exceptionMsg = null;

        XmlMapper mapper = getXmlMapperDeserializer(false);

        try {
            gmf = (GMFMessageVariants) mapper.readValue(new StringReader(xml), GMFMessageVariants.class);
        } catch (JacksonException e) {
            exceptionMsg = e.getMessage();
        } catch (IOException e) {
            exceptionMsg = e.getMessage();
        } catch (Exception e) {
            exceptionMsg = e.getMessage();
        }
        if (exceptionMsg != null)
            throw new InvalidResponseXml(exceptionMsg);

        return gmf;
    }

    public static XmlMapper getXmlMapperDeserializer(Boolean failOnUnknownProperties) {
        XmlMapper mapper = (XmlMapper) new XmlMapper()
                .configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false)
                .configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, failOnUnknownProperties)
                .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true);
        return mapper;
    }
}
