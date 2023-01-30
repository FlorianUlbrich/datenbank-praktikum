package de.uni_leipzig.dbprak.parser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "isbn")
public class Isbn {

    @JacksonXmlProperty(isAttribute = true)
    private String val;

    public Isbn(String val) {
        this.val = val;
    }

    public Isbn() {
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
