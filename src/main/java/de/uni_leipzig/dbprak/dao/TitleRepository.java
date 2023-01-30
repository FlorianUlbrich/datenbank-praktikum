package de.uni_leipzig.dbprak.dao;

import de.uni_leipzig.dbprak.entity.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleRepository extends JpaRepository<Title, Long> {
}
