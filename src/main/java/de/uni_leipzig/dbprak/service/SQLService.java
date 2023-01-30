package de.uni_leipzig.dbprak.service;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Service
public class SQLService {

    public SQLService() {
    }

    public void one() {
        EntityManager entityManager = PersistenceManager.getEntityManager();
        Query query = entityManager.createNativeQuery("select \n" + "(SELECT  COUNT(*)  FROM book as Book_Count) ,\n" +
                "(SELECT COUNT(*) FROM music_cd  as  CD_Count),\n" +
                "(SELECT COUNT(*) FROM dvd  as  DVD_Count)");
    }
}
