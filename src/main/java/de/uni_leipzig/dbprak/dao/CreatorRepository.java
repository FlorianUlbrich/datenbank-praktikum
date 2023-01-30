package de.uni_leipzig.dbprak.dao;

import de.uni_leipzig.dbprak.entity.Creator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatorRepository extends JpaRepository<Creator, Long> {
}