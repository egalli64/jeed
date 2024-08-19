/*
 * Introduction to Jakarta Enterprise Edition - JPA on Hibernate
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.dao;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

/**
 * DAO for Employee
 */
public class EmployeeDao extends Dao<Employee, Integer> {
    private static final Logger log = LogManager.getLogger(EmployeeDao.class);

    /**
     * Ctor
     * 
     * @param service access provider to the entity manager
     */
    public EmployeeDao(EntityManagerService service) {
        super(Employee.class, service);
    }

    /**
     * Get the less payed employees ordered by salary descending
     * 
     * Untyped query, positional parameter
     * 
     * @param limit the top salary accepted
     * @return the selected employees
     */
    public List<Employee> readBySalaryBottom(double limit) {
        log.traceEntry();

        try (EntityManager em = service.createEntityManager()) {
            String jpql = "SELECT e FROM Employee e WHERE e.salary <= ?1 ORDER BY e.salary DESC";
            Query query = em.createQuery(jpql);
            query.setParameter(1, limit);

            @SuppressWarnings("unchecked")
            List<Employee> result = (List<Employee>) query.getResultList();
            return result;
        }
    }

    /**
     * Get the descending ordered employees in a range of salary
     * 
     * Typed query, named parameters
     * 
     * @param low  the lowest salary (included)
     * @param high the highest salary (included)
     * @return the selected employees
     */
    public List<Employee> readBySalaryRange(double low, double high) {
        log.traceEntry();
        EntityManager em = service.createEntityManager();

        try {
            String jpql = "SELECT e FROM Employee e WHERE e.salary BETWEEN :min AND :max ORDER BY e.salary DESC";
            TypedQuery<Employee> query = em.createQuery(jpql, Employee.class);
            query.setParameter("min", low);
            query.setParameter("max", high);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Get the most payed employees ordered by salary descending
     * 
     * Named and typed query, named parameter
     * 
     * @param limit the lowest salary accepted
     * @return the selected employees
     */
    public List<Employee> readBySalaryTop(double limit) {
        log.traceEntry();
        EntityManager em = service.createEntityManager();

        try {
            return em.createNamedQuery("getTopSalaried", Employee.class) //
                    .setParameter("low", limit).getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Get the employee having the specified last name, not more than one!
     * 
     * @param name the employee last name
     * @return empty if no employee or more than one found
     */
    public Optional<Employee> readByLastName(String name) {
        log.traceEntry();
        EntityManager em = service.createEntityManager();

        try {
            String jpql = "FROM Employee e WHERE e.lastName = :name";
            TypedQuery<Employee> query = em.createQuery(jpql, Employee.class);
            query.setParameter("name", name);
            return Optional.of(query.getSingleResult());
        } catch (NonUniqueResultException | NoResultException ex) {
            log.warn(name, ex);
            return Optional.empty();
        } finally {
            em.close();
        }
    }

    public int deleteByIdBetween(int low, int high) {
        log.traceEntry();
        EntityManager em = service.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            String jpql = "DELETE FROM Employee e WHERE e.id BETWEEN :low AND :high";
            Query query = em.createQuery(jpql).setParameter("low", low).setParameter("high", high);
            tx.begin();
            return query.executeUpdate();
        } catch (Exception ex) {
            log.error(String.format("[%d, %d]", low, high), ex);
            if (tx.isActive()) {
                tx.rollback();
            }
            return 0;
        } finally {
            if (tx.isActive()) {
                tx.commit();
            }
            em.close();
        }
    }
}
