package fdrc.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class RegistrationResponse {
    @JacksonXmlProperty
    public String DID;
    @JacksonXmlProperty
    public String URL;
}
