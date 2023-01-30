package de.uni_leipzig.dbprak.dao;

import de.uni_leipzig.dbprak.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select r from Review r where r.productId = ?1")
    List<Review> findByAsin(Long id);

    @Query("select r.stars from Review r where r.customerId = ?1")
    List<Integer> getStarsFromCustomer(Long customerId);

    @Query("select distinct r.customerId from Review r")
    List<Long> getAllCustomers();
}
