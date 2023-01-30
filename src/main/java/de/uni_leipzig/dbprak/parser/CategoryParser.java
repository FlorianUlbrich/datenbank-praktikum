package de.uni_leipzig.dbprak.parser;

import de.uni_leipzig.dbprak.dao.CategoryRepository;
import de.uni_leipzig.dbprak.entity.Category;
import de.uni_leipzig.dbprak.entity.Product;
import de.uni_leipzig.dbprak.service.ProductService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryParser {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryRepository categoryRepository;

    public void readCategories() throws IOException {

        File xmlFile = new File("src/main/resources/static/categories.xml");
        FileInputStream fis = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(fis, null, "", Parser.xmlParser());

        Elements categories = doc.select("category:has(> item)");
        categories.forEach(c -> {

            // set category name
            Category category = new Category();
            category.setName(c.ownText());
            List<Product> products = new ArrayList<>();

            // set items in category
            Elements children = c.children();
            children.forEach(ch -> {

                ch.parent().ownText();

                Product product = productService.getProductByAsin(ch.text());
                if(product != null) {
                    products.add(product);
                }
                System.out.println("Category: " +ch.parent().ownText());
                System.out.println("Item: "+ ch.text());
            });
            category.setProducts(products);
            categoryRepository.save(category);
        });
    }

    /*
    public void readCategoryTree() throws IOException {

        File xmlFile = new File("src/main/resources/static/categories.xml");
        FileInputStream fis = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(fis, null, "", Parser.xmlParser());

        // go through all category tags
        Element categories = doc.select("categories");
        categories.forEach( c -> {

                Category category = new Category();
                category.setName(c.ownText());
                List<Product> products = new ArrayList<>();

                // check if the category has a parent category
                if(c.parent() != null) {
                    String parent = c.parent().ownText();
                    category.setParentId(categoryRepository.getCategoryByName(parent));
                }

                // check if the category has items
                if(c.child(0).is("item")) {
                    Elements items = c.children(); }

                    for (Element i : items) {
                        Product product = productService.getProductByAsin(i.text());
                        if(product != null) {
                            products.add(product);
                        }
                    }
                    category.setProducts(products);
                }
                categoryRepository.save(category);
            }
        );
    }*/
}