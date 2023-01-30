package de.uni_leipzig.dbprak.dao;

import de.uni_leipzig.dbprak.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.asin = ?1")
    Product findByAsin(String asin);

    @Query("select p from Product p where p.title like ?1")
    List<Product> getProductsLike(String pattern);

    @Query(nativeQuery = true, value = "select p from Product p order by p.rating desc limit ?1")
    List<Product> getTopProducts(int k);

    @Query("update Product set rating = ?1 where id = ?2")
    void setAverageRating( int rating, Long id);
}