package de.uni_leipzig.dbprak.dao;

import de.uni_leipzig.dbprak.entity.Product;
import de.uni_leipzig.dbprak.entity.Similar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimilarRepository extends JpaRepository<Similar, Long> {

    @Query("select s.similarId from Similar s where s.id = ?1")
    List<Product> getSimilarProducts(Long id);
}
