package fdrc.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

public class Response {
    @JacksonXmlText
    String Response;
    String Version;
}
