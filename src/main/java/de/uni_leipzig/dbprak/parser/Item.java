package de.uni_leipzig.dbprak.parser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

/* structure of xml
item
    asin -> product.id
    pgroup -> Music | DVD | Book
    salesrank
    picture
    detailpage
    ean
    price
        mult
        state
        currency
    title
    dvdspec
        regioncode
        runningtime
        format
        releasedate
    bookspec
        isbn val
        pages
        publication date
    musicspec
        releasedate
        labels
            label
        tracks
            track
    publishers
        publisher
    studios
        studio
    similars
        sim_product
    actors
        actor
    artists
        artist
    authors
        author
    creators
        creator
    directors
        director


 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "item")
public class Item {

    @JacksonXmlProperty(isAttribute = true)
    private String asin;

    @JacksonXmlProperty(isAttribute = true)
    private String pgroup;

    @JacksonXmlProperty(isAttribute = true)
    private int salesrank;

    @JacksonXmlProperty(isAttribute = true)
    private String picture;

    @JacksonXmlProperty
    private int price;

    @JacksonXmlProperty
    private String title;

    @JacksonXmlProperty
    private Bookspec bookspec;

    @JacksonXmlProperty
    private Dvdspec dvdspec;

    @JacksonXmlProperty
    private Musicspec musicspec;

    @JacksonXmlProperty
    private Tracks tracks;

    @JacksonXmlProperty
    private String store;

    @JacksonXmlElementWrapper(localName = "publishers")
    @JacksonXmlProperty(localName = "publisher")
    private List<Publisher> publishers;

    @JacksonXmlElementWrapper(localName = "studios")
    @JacksonXmlProperty(localName = "studio")
    private List<Studio> studios;

    @JacksonXmlElementWrapper(localName = "similars")
    @JacksonXmlProperty(localName = "sim_product")
    private List<Sim_product> similars;

    @JacksonXmlElementWrapper(localName = "actors")
    @JacksonXmlProperty(localName = "actor")
    private List<Actor> actors;

    @JacksonXmlElementWrapper(localName = "artists")
    @JacksonXmlProperty(localName = "artist")
    private List<Artist> artists;

    @JacksonXmlElementWrapper(localName = "authors")
    @JacksonXmlProperty(localName = "author")
    private List<Author> authors;

    @JacksonXmlElementWrapper(localName = "creators")
    @JacksonXmlProperty(localName = "creator")
    private List<Creator> creators;

    @JacksonXmlElementWrapper(localName = "directors")
    @JacksonXmlProperty(localName = "director")
    private List<Director> directors;

    public Item() {
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getPgroup() {
        return pgroup;
    }

    public void setPgroup(String pgroup) {
        this.pgroup = pgroup;
    }

    public int getSalesrank() {
        return salesrank;
    }

    public void setSalesrank(int salesrank) {
        this.salesrank = salesrank;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bookspec getBookspec() {
        return bookspec;
    }

    public void setBookspec(Bookspec bookspec) {
        this.bookspec = bookspec;
    }

    public Dvdspec getDvdspec() {
        return dvdspec;
    }

    public void setDvdspec(Dvdspec dvdspec) {
        this.dvdspec = dvdspec;
    }

    public Musicspec getMusicspec() {
        return musicspec;
    }

    public void setMusicspec(Musicspec musicspec) {
        this.musicspec = musicspec;
    }

    public Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public List<Publisher> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<Publisher> publishers) {
        this.publishers = publishers;
    }

    public List<Studio> getStudios() {
        return studios;
    }

    public void setStudios(List<Studio> studios) {
        this.studios = studios;
    }

    public List<Sim_product> getSimilars() {
        return similars;
    }

    public void setSimilars(List<Sim_product> similars) {
        this.similars = similars;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Creator> getCreators() {
        return creators;
    }

    public void setCreators(List<Creator> creators) {
        this.creators = creators;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
