package de.uni_leipzig.dbprak.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import de.uni_leipzig.dbprak.entity.*;
import de.uni_leipzig.dbprak.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

/**
 * Maps XML data with object mapper and saves it with injected service class. To be managed by Spring IoC Container (Inversion of Control),
 * it is necessary for the class to be a bean, hence the @Component annotation.
 */
@Component
public class ItemParser {

    /**
     * Injection of service class.
     */
    @Autowired
    ProductService productService;

    /**
     * ObjectMapper maps xml code to similar classes one item at a time.
     * @throws IOException
     */
    public void parse(String path) throws IOException {

        String store = "";

        if(path.contains("leipzig")) {
            store = "Leipzig";
        } else {
            store = "Dresden";
        }

        try {
            ObjectMapper mapper = new XmlMapper();
            mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
            InputStream inputStream = new FileInputStream(new File(path));
            TypeReference<List<Item>> typeReference = new TypeReference<>() {};
            List<Item> items = mapper.readValue(inputStream, typeReference);

            // get xml data with object mapper
            for(Item i: items) {

                // proof of concept
                System.out.println("id: " + i.getAsin() + " pgroup: " + i.getPgroup() + " salesrank:" + i.getSalesrank());

                Item item = new Item();
                item.setAsin(i.getAsin());

                // if id is missing, skip to next item
                if(item.getAsin() == null || item.getAsin().isEmpty()) {
                    continue;
                }

                // length checks
                if(i.getPicture().length() > 250) {
                    continue;
                }

                item.setPgroup(i.getPgroup());
                item.setSalesrank(i.getSalesrank());
                item.setPrice(i.getPrice());
                item.setPicture(i.getPicture());
                item.setTitle(i.getTitle());
                item.setStore("Leipzig");
                item.setSimilars(i.getSimilars());
                item.setStudios(i.getStudios());
                item.setPublishers(i.getPublishers());
                item.setActors(i.getActors());
                item.setArtists(i.getArtists());
                item.setAuthors(i.getAuthors());
                item.setCreators(i.getCreators());
                item.setDirectors(i.getDirectors());
                item.setTracks(i.getTracks());

                // vermeiden von leeren books, dvds, musicCds
                switch (i.getPgroup()) {
                    case "Book":
                        Bookspec bookspec = new Bookspec();
                        String d = i.getBookspec().getPublication().getDate();
                        if (d.length() > 0) {
                            bookspec.setPublication(new Publication(d));
                        }
                        bookspec.setPages(i.getBookspec().getPages());
                        bookspec.setIsbn(new Isbn(i.getBookspec().getIsbn().getVal()));
                        item.setBookspec(bookspec);
                        break;
                    case "DVD":
                        Dvdspec dvdspec = new Dvdspec();
                        dvdspec.setFormat(i.getDvdspec().getFormat());
                        dvdspec.setRunningtime(i.getDvdspec().getRunningtime());
                        dvdspec.setReleasedate(i.getDvdspec().getReleasedate());
                        dvdspec.setRegioncode(i.getDvdspec().getRegioncode());
                        item.setDvdspec(dvdspec);
                        break;
                    case "Music":
                        Musicspec musicspec = new Musicspec();
                        musicspec.setReleasedate(i.getMusicspec().getReleasedate());
                        item.setMusicspec(musicspec);
                        break;
                    default: break;
                }

                // write to db
                fillProduct(item, store);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Maps from classes necessary for xml object mapper to entities and sends fully populated object to service class
     * for being written to db.
     * @param item Shell needed by object mapper.
     */
    public void fillProduct(Item item, String store) {

        Product product = new Product();
        try {
            product.setAsin(item.getAsin());
            product.setTitle(item.getTitle());
            product.setSalesRank(item.getSalesrank());
            product.setImage(item.getPicture());
        } catch(Exception e) {
            e.printStackTrace();
        }

        // short circuit evaluation
        if(item.getActors() != null && item.getActors().size() > 0) {
            List<de.uni_leipzig.dbprak.entity.Actor> actors = new ArrayList<>();
            for(Actor a : item.getActors()) {
                de.uni_leipzig.dbprak.entity.Actor actor = new de.uni_leipzig.dbprak.entity.Actor();
                actor.setName(a.getName());
                actors.add(actor);
            }
            product.setActors(actors);
        }

        if(item.getArtists() != null && item.getArtists().size() > 0) {
            List<de.uni_leipzig.dbprak.entity.Artist> artists = new ArrayList<>();
            for(Artist a : item.getArtists()) {
                de.uni_leipzig.dbprak.entity.Artist artist = new de.uni_leipzig.dbprak.entity.Artist();
                artist.setName(a.getName());
                artists.add(artist);
            }
            product.setArtists(artists);
        }

        if(item.getAuthors() != null && item.getAuthors().size() > 0) {
            List<de.uni_leipzig.dbprak.entity.Author> authors = new ArrayList<>();
            for(Author a : item.getAuthors()) {
                de.uni_leipzig.dbprak.entity.Author author = new de.uni_leipzig.dbprak.entity.Author();
                author.setName(a.getName());
                authors.add(author);
            }
            product.setAuthors(authors);
        }

        if(item.getCreators() != null && item.getCreators().size() > 0) {
            List<de.uni_leipzig.dbprak.entity.Creator> creators = new ArrayList<>();
            for(Creator c : item.getCreators()) {
                de.uni_leipzig.dbprak.entity.Creator creator = new de.uni_leipzig.dbprak.entity.Creator();
                creator.setName(c.getName());
                creators.add(creator);
            }
            product.setCreators(creators);
        }

        if(item.getDirectors() != null && item.getDirectors().size() > 0) {
            List<de.uni_leipzig.dbprak.entity.Director> directors = new ArrayList<>();
            for(Director d : item.getDirectors()) {
                de.uni_leipzig.dbprak.entity.Director director = new de.uni_leipzig.dbprak.entity.Director();
                director.setName(d.getName());
                directors.add(director);
                product.setDirectors(directors);
            }
        }

        switch(item.getPgroup()) {
            case "Book":
                Book book = new Book();
                try {
                    book.setIsbn(item.getBookspec().getIsbn().getVal());
                    book.setPages(item.getBookspec().getPages());
                    book.setRelease(item.getBookspec().getPublication().getDate());
                    product.setBook(book);
                } catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case "Music":
                MusicCD musicCD = new MusicCD();
                try {
                    musicCD.setRelease(item.getMusicspec().getReleasedate());
                    if(item.getTracks() != null && item.getTracks().getTitles().size() > 0) {
                        List<Title> titles = new ArrayList<>();
                        for(String t : item.getTracks().getTitles()) {
                            de.uni_leipzig.dbprak.entity.Title title = new de.uni_leipzig.dbprak.entity.Title();
                            title.setTitle(t);
                            titles.add(title);
                        }
                        musicCD.setTitle(titles);
                    }
                    product.setMusicCD(musicCD);
                } catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case "DVD":
                DVD dvd = new DVD();
                try {
                    dvd.setDuration(item.getDvdspec().getRunningtime());
                    dvd.setFormat(item.getDvdspec().getFormat());
                    dvd.setRegionCode(valueOf(item.getDvdspec().getRegioncode()));
                    product.setDvd(dvd);
                } catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            default: break;
        }

        List<String> simItems = new ArrayList<>();
        for(Sim_product s : item.getSimilars()) {
            simItems.add(s.getAsin());
        }

        // service writes product to db
        productService.saveProduct(product, simItems, item.getPrice(), store);
    }
}
