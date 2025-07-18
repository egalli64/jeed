/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.dao.legacy;

import java.util.List;

import com.example.jeed.util.EntityManagerService;

import jakarta.persistence.EntityManager;

/**
 * DAO for Team
 */
public class TeamDao extends Dao<Team, Integer> {
    /**
     * Ctor
     * 
     * @param service access provider to the entity manager
     */
    public TeamDao(EntityManagerService service) {
        super(Team.class, service);
    }

    /**
     * Eager select all
     * 
     * @return a list of teams each of them with all the associated employees
     */
    public List<Team> readAllEager() {
        try (EntityManager em = service.createEntityManager()) {
            String jpql = "SELECT DISTINCT t FROM Team t JOIN FETCH t.employees";
            return em.createQuery(jpql, Team.class).getResultList();
        }
    }
}
