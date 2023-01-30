package de.uni_leipzig.dbprak.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "music_cd")
public class MusicCD {

    @Id
    private Long id;

    private String release;

    @ManyToMany(targetEntity = Title.class)
    @JoinTable(
            name = "music_cd_music_title",
            joinColumns = @JoinColumn(name = "music_cd_id"),
            inverseJoinColumns = @JoinColumn(name = "title_id"))
    private List<Title> title;

    public MusicCD() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public List<Title> getTitle() {
        return title;
    }

    public void setTitle(List<Title> title) {
        this.title = title;
    }
}
