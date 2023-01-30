package de.uni_leipzig.dbprak.dao;

import de.uni_leipzig.dbprak.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
}
