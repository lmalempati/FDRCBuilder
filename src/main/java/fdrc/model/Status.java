package fdrc.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

public class Status {
    @JacksonXmlText(value = true)
    public String Status; // this is required!!
    public String StatusCode;
}
