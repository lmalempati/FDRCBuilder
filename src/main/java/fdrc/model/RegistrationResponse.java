package fdrc.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

public class RegistrationResponse {
    @JacksonXmlProperty
    String DID;
    @JacksonXmlProperty
    String URL;
}
