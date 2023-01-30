package de.uni_leipzig.dbprak.dao;

import de.uni_leipzig.dbprak.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
}
