package de.uni_leipzig.dbprak.entity;

import javax.persistence.*;

@Entity
public class DVD {

    @Id
    private Long id;

    private long duration;

    private String format;

    @Column(name = "region_code")
    private String regionCode;

    public DVD() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }
}
