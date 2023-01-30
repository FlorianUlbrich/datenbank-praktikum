package de.uni_leipzig.dbprak.dao;

import de.uni_leipzig.dbprak.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
