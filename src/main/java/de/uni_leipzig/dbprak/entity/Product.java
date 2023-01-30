package de.uni_leipzig.dbprak.entity;

import javax.persistence.*;
import java.util.List;

/**
 * #@Table(name = "product") is not needed. Hibernate maps it automatically, same with @Column()
 */
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String asin;

    private String title;

    private double rating;

    @Column(name = "sales_rank")
    private int salesRank;

    private String image;

    @ManyToMany(targetEntity = Actor.class)
    @JoinTable(
            name = "product_actor",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> actors;

    @ManyToMany(targetEntity = Artist.class)
    @JoinTable(
            name = "product_artist",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id"))
    private List<Artist> artists;

    @ManyToMany(targetEntity = Author.class)
    @JoinTable(
            name = "product_author",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

    @ManyToMany(targetEntity = Creator.class)
    @JoinTable(
            name = "product_creator",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "creator_id"))
    private List<Creator> creators;

    @ManyToMany(targetEntity = Director.class)
    @JoinTable(
            name = "product_director",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id"))
    private List<Director> directors;

    @ManyToMany(targetEntity = Category.class)
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    @OneToOne
    @PrimaryKeyJoinColumn
    private MusicCD musicCD;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Book book;

    @OneToOne
    @PrimaryKeyJoinColumn
    private DVD dvd;

    // constructor
    public Product() {}

    // getter and setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getSalesRank() {
        return salesRank;
    }

    public void setSalesRank(int salesRank) {
        this.salesRank = salesRank;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public MusicCD getMusicCD() {
        return musicCD;
    }

    public void setMusicCD(MusicCD musicCD) {
        this.musicCD = musicCD;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public DVD getDvd() {
        return dvd;
    }

    public void setDvd(DVD dvd) {
        this.dvd = dvd;
    }
}
