package de.uni_leipzig.dbprak.dao;

import de.uni_leipzig.dbprak.entity.DVD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DvdRepository extends JpaRepository<DVD, Long> {
}
