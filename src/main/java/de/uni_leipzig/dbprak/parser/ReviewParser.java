package de.uni_leipzig.dbprak.parser;

import de.uni_leipzig.dbprak.dao.CustomerRepository;
import de.uni_leipzig.dbprak.dao.ReviewRepository;
import de.uni_leipzig.dbprak.entity.Customer;
import de.uni_leipzig.dbprak.entity.Product;
import de.uni_leipzig.dbprak.entity.Review;
import de.uni_leipzig.dbprak.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class ReviewParser {

    @Autowired
    ProductService productService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ReviewRepository reviewRepository;

    public void readReviews() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/static/reviews.csv"));

        String newLine;
        newLine = bufferedReader.readLine();

        // print header "product","rating","helpful","reviewdate","user","summary","content" and move to first data row
        System.out.println(newLine);

        while((newLine = bufferedReader.readLine()) != null) {

            newLine = newLine.replaceAll("\"", "");
            String[] line = newLine.split(",");

            // get product by asin
            Product product = productService.getProductByAsin(line[0]);

            // save customer if he doesn't exist
            Customer customer = customerRepository.getCustomerByName(line[4]);
            if(customer == null) {
                customer = customerRepository.save(new Customer(line[4]));
            }

            // set fields of review and save with service
            if(product != null) {
                Review review = new Review();
                review.setProductId(product.getId());
                review.setCustomerId(customer.getId());
                review.setStars(Integer.parseInt(line[1]));
                review.setText(line[5]);
                reviewRepository.save(review);
            }
        }
    }
}
