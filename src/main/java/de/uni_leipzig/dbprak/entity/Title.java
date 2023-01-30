package de.uni_leipzig.dbprak.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany(targetEntity = MusicCD.class)
    @JoinTable(
            name = "music_cd_music_title",
            joinColumns = @JoinColumn(name = "title_id"),
            inverseJoinColumns = @JoinColumn(name = "music_cd_id"))
    private List<MusicCD> musicCDs;

    public Title() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
