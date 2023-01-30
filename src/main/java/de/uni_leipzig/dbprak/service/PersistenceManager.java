package de.uni_leipzig.dbprak.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Service
public class PersistenceManager {

    @Autowired
    private static EntityManagerFactory factory;

    public PersistenceManager() {
    }

    public static EntityManager getEntityManager () {
        if (factory == null) {
            EntityManagerFactory factory =
                    Persistence.createEntityManagerFactory("media_world");
        }
        return factory.createEntityManager();
    }
}
