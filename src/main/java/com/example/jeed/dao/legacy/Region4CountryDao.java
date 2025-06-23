/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.dao.legacy;

import java.util.List;
import jakarta.persistence.EntityManager;

/**
 * DAO for Region entity in one to many relation with Country
 */
public class Region4CountryDao extends Dao<Region4Country, Integer> {
    /**
     * Due to type erasure, the actual JavaBean type has to be passed to the
     * superclass
     * 
     * @param ems required to access the entity manager factory
     */
    public Region4CountryDao(EntityManagerService ems) {
        super(Region4Country.class, ems);
    }

    /**
     * Eager select all
     * 
     * @return a list of region each of them with all the associated countries
     */
    public List<Region4Country> readAllEager() {
        try (EntityManager em = service.createEntityManager()) {
            String jpql = "SELECT DISTINCT r FROM Region4Country r JOIN FETCH r.countries";
            return em.createQuery(jpql, Region4Country.class).getResultList();
        }
    }
}
