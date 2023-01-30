package de.uni_leipzig.dbprak.parser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "dvdspec")
public class Dvdspec {

    // can be multiple formats seperated by comma
    @JacksonXmlProperty
    private String format;

    @JacksonXmlProperty
    private int regioncode;

    @JacksonXmlProperty
    private String releasedate;

    @JacksonXmlProperty
    private int runningtime;

    public Dvdspec() {
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getRegioncode() {
        return regioncode;
    }

    public void setRegioncode(int regioncode) {
        this.regioncode = regioncode;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    public int getRunningtime() {
        return runningtime;
    }

    public void setRunningtime(int runningtime) {
        this.runningtime = runningtime;
    }
}
