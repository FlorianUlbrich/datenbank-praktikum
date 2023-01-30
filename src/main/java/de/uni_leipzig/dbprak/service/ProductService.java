package de.uni_leipzig.dbprak.service;

import de.uni_leipzig.dbprak.dao.*;
import de.uni_leipzig.dbprak.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Service handles all product entities.
 */
@Service
public class ProductService {

    /**
     * Field Injection of Repository for handling db transactions.
     */
    @Autowired
    ActorRepository actorRepository;

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    CreatorRepository creatorRepository;

    @Autowired
    DirectorRepository directorRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    DvdRepository dvdRepository;

    @Autowired
    MusicCdRepository musicCdRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    TitleRepository titleRepository;

    @Autowired
    SimilarRepository similarRepository;

    @Autowired
    CommodityValueRepository commodityValueRepository;

    @Autowired
    ReviewRepository reviewRepository;


    /**
     * Default constructor.
     */
    public ProductService() {}

    /**
     * Saving a product with all other attributes to the db.
     * @param product Fully populated product ready for being written to db.
     */
    @Transactional
    public void saveProduct(Product product, List<String> similars, int price, String store) {

        if(getProductByAsin(product.getAsin()) == null) {

            Book book = product.getBook();
            DVD dvd = product.getDvd();
            MusicCD musicCd = product.getMusicCD();

            product.setBook(null);
            product.setDvd(null);
            product.setMusicCD(null);


            if(product.getArtists() != null) {
                for(Artist a : product.getArtists()) {
                    artistRepository.save(a);
                }
            }

            if(product.getActors() != null) {
                for(Actor a : product.getActors()) {
                    actorRepository.save(a);
                }
            }

            if(product.getAuthors() != null) {
                for(Author a: product.getAuthors()) {
                    authorRepository.save(a);
                }
            }

            if(product.getCreators() != null) {
                for(Creator c: product.getCreators()) {
                    creatorRepository.save(c);
                }
            }

            if(product.getDirectors() != null) {
                for(Director d: product.getDirectors()) {
                    directorRepository.save(d);
                }
            }

            product = productRepository.save(product);

            if(book != null) {
                book.setId(product.getId());
                bookRepository.save(book);
            }

            if(dvd != null) {
                dvd.setId(product.getId());
                dvdRepository.save(dvd);
            }

            if(musicCd != null) {
                musicCd.setId(product.getId());
                if(musicCd.getTitle() != null) {
                    for(Title t : musicCd.getTitle()) {
                        titleRepository.save(t);
                    }
                }
                musicCdRepository.save(musicCd);
            }

            for(String s : similars){
                Similar similar = new Similar(product.getId(), s);
                similarRepository.save(similar);
            }
        }

        CommodityValue commodityValue = new CommodityValue();
        commodityValue.setPrice(price);

        if(product.getId() != null) {
            commodityValue.setProductId(product.getId());
        } else {
            commodityValue.setProductId(getProductByAsin(product.getAsin()).getId());
        }

        if(store == "Leipzig") {
            commodityValue.setStoreId(1L);
        } else {
            commodityValue.setStoreId(2L);
        }

        commodityValue.setAvailability(price > 0);
        commodityValue.setCondition("new");
        commodityValueRepository.save(commodityValue);
    }

    // Für eine bestimmte Produkt-Id werden mit dieser Methode die Detailinformationen des Produkts ermittelt.
    public Product getProductByAsin(String asin) {
        return productRepository.findByAsin(asin);
    }

    /* Diese Methode soll eine Liste der in der Datenbank enthaltenen Produkte, deren Titel mit dem übergebenen Pattern
     * übereinstimmen, zurückliefern. Beachten Sie, dass im Falle von pattern=null die komplette Liste zurückgeliefert wird.
     * Das Pattern kann SQL-Wildcards enthalten.
     */
    public List<Product> getProductsLike(String like) {return productRepository.getProductsLike(like); }

    // Diese Methode liefert eine Liste aller Produkte zurück, die unter den Top k sind basierend auf dem Rating.
    public List<Product> getTopProducts(int k) {return productRepository.getTopProducts(k); }

    // Diese Methode liefert für ein Produkt(Id) eine List von Produkten, die ähnlich und billiger sind als das spezifizierte.
    public List<Product> getSimilarCheaperProduct(long id) {

        List<Product> similars = similarRepository.getSimilarProducts(id);
        int reference = commodityValueRepository.returnPriceForProduct(id);
        List<Product> cheaper = new ArrayList<>();

        for (Product p : similars) {
            int sPrice = commodityValueRepository.returnPriceForProduct(p.getId());
            if(sPrice < reference) {
                cheaper.add(p);
            }
        }
        return cheaper;
    }

    /* Die Rahmenapplikation erlaubt sowohl das Ansehen als auch Hinzufügen von Reviews. MIt Hilfe der Methode wird ein
     * neues Review in der Datenbank gespeichert.
     */
    public void addNewReview(Review r) {
        reviewRepository.save(r);
    }

    // Die Methode soll eine Liste von Nutzern ausgeben, deren Durchschnittsbewertung unter einem spezifizierten Rating ist.
    public List<Long> getTrolls(double thresh) {

        List<Long> customers = reviewRepository.getAllCustomers();
        List<Long> trolls = new ArrayList<>();

        for (Long customer : customers) {

            List<Integer> stars = reviewRepository.getStarsFromCustomer(customer);
            double average = stars.stream().mapToInt(val -> val).average().orElse(0.0);

            if(average < thresh) {
                trolls.add(customer);
            }
        }
        return trolls;
    }

    // Für das übergegebene Produkt(Id) werden alle verfügbaren Angebote zurückgeliefert.
    public List<CommodityValue> getOffers(Long id) {
        return commodityValueRepository.getByProductId(id);
    }
}
