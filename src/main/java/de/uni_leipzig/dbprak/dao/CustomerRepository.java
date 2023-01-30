package de.uni_leipzig.dbprak.dao;

import de.uni_leipzig.dbprak.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select c from Customer c where c.name = ?1")
    Customer getCustomerByName(String name);
}
