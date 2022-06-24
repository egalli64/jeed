package com.example.jeed.s14;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jeed.dao.JpaUtil;
import com.example.jeed.s05.EmployeePlain;

public class EmployeeDao {
    private static final Logger log = LogManager.getLogger(EmployeeDao.class);

    public List<EmployeePlain> getPayedMoreThan(double low) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "FROM EmployeePlain e WHERE e.salary > :low ORDER BY salary DESC";
            TypedQuery<EmployeePlain> query = em.createQuery(jpql, EmployeePlain.class);
            query.setParameter("low", low);
            return query.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public List<EmployeePlain> getUntypedPayedMoreThan(double low) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "FROM EmployeePlain e WHERE e.salary > :low ORDER BY salary DESC";
            Query query = em.createQuery(jpql);
            query.setParameter("low", low);
            return (List<EmployeePlain>) query.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Optional<EmployeePlain> getByName(String first, String last) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "FROM EmployeePlain e WHERE e.firstName = :first and e.lastName = :last";
            TypedQuery<EmployeePlain> query = em.createQuery(jpql, EmployeePlain.class);
            query.setParameter("first", first);
            query.setParameter("last", last);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException nre) {
            log.debug(String.format("Coder %s %s: %s", first, last, nre.getMessage()));
            return Optional.empty();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public int deleteBetween(long low, long high) {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = JpaUtil.createEntityManager();
            tx = em.getTransaction();
            String jpql = "DELETE FROM EmployeePlain e WHERE e.id BETWEEN :low AND :high";
            Query query = em.createQuery(jpql);
            query.setParameter("low", low);
            query.setParameter("high", high);
            tx.begin();
            return query.executeUpdate();
        } catch (Exception ex) {
            log.error("Can't delete for " + low, ex);
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            return 0;
        } finally {
            if (em != null) {
                if (tx != null && tx.isActive()) {
                    tx.commit();
                }
                em.close();
            }
        }
    }
}
