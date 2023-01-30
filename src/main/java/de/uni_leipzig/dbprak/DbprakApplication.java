package de.uni_leipzig.dbprak;

import de.uni_leipzig.dbprak.dao.StoreRepository;
import de.uni_leipzig.dbprak.entity.Product;
import de.uni_leipzig.dbprak.entity.Store;
import de.uni_leipzig.dbprak.parser.CategoryParser;
import de.uni_leipzig.dbprak.parser.ItemParser;
import de.uni_leipzig.dbprak.parser.ReviewParser;
import de.uni_leipzig.dbprak.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EntityScan( basePackages = {"de.uni_leipzig.dbprak.entity"} )
public class DbprakApplication {

private static final Logger log = LoggerFactory.getLogger(DbprakApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DbprakApplication.class, args);
    }

    /**
     * CommandLineRunner calls Service Classes.
     * @param itemParser
     * @return
     */
    @Bean
    CommandLineRunner commandLineRunner(StoreRepository storeRepository, ItemParser itemParser, ReviewParser reviewParser, CategoryParser categoryParser, ProductService productService) {
        return args -> {

/*
            Store leipzig = new Store();
            leipzig.setName("Leipzig");
            leipzig.setAddress("Hofe am Brühl");
            leipzig.setId(1L);
            storeRepository.save(leipzig);

            Store dresden = new Store();
            dresden.setName("Dresden");
            dresden.setAddress("Johann-Meyer-Straße");
            dresden.setId(2L);
            storeRepository.save(dresden);

            itemParser.parse("src/main/resources/static/leipzig_transformed.xml");
            itemParser.parse("src/main/resources/static/dresden.xml");
            reviewParser.readReviews();
            categoryParser.readCategories();
            // categoryParser.readCategoryTree();*/

            Product product = productService.getProductByAsin("B0000668PG");
            System.out.println("Product title: " + product.getTitle());
        };
    }
}
