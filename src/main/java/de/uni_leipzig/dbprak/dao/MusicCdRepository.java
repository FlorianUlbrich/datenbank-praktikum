package de.uni_leipzig.dbprak.dao;

import de.uni_leipzig.dbprak.entity.MusicCD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicCdRepository extends JpaRepository<MusicCD, Long> {
}
