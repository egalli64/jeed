/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.dao.legacy;

import java.util.List;

import jakarta.persistence.EntityManager;

/**
 * DAO for Team
 */
public class Employee4TeamDao extends Dao<Employee4Team, Integer> {
    /**
     * Ctor
     * 
     * @param service access provider to the entity manager
     */
    public Employee4TeamDao(EntityManagerService service) {
        super(Employee4Team.class, service);
    }

    /**
     * Eager select all
     * 
     * @return a list of employees each of them with all the associated teams
     */
    public List<Employee4Team> readAllEager() {
        try (EntityManager em = service.createEntityManager()) {
            String jpql = "SELECT DISTINCT e FROM Employee4Team e JOIN FETCH e.teams";
            return em.createQuery(jpql, Employee4Team.class).getResultList();
        }
    }
}
