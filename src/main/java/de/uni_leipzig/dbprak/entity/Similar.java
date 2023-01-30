package de.uni_leipzig.dbprak.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sim_product")
public class Similar {

    @Id
    @Column(name = "product_id")
    private Long id;

    @Column(name = "sim_product_id")
    private String similarId;

    public Similar() {
    }

    public Similar(Long id, String similarId) {
        this.id = id;
        this.similarId = similarId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSimilarId() {
        return similarId;
    }

    public void setSimilarId(String similarId) {
        this.similarId = similarId;
    }
}
