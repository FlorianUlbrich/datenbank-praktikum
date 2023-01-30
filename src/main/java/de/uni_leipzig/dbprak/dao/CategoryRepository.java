package de.uni_leipzig.dbprak.dao;

import de.uni_leipzig.dbprak.entity.Category;
import de.uni_leipzig.dbprak.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Modifying
    @Query("update Category c set c.products = ?1 where c.id = ?2")
    void addProductToCategory(List<Product> products, Long id);

    @Query("select c.id from Category c where c.name = ?1")
    Long getCategoryByName(String name);

}
