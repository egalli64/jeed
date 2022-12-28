/*
 * Introduction to Jakarta Enterprise Edition - JPA on Hibernate
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.dao;

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
        EntityManager em = service.createEntityManager();

        try {
            String jpql = "SELECT DISTINCT e FROM Employee4Team e JOIN FETCH e.teams";
            return em.createQuery(jpql, Employee4Team.class).getResultList();
        } finally {
            em.close();
        }
    }
}
