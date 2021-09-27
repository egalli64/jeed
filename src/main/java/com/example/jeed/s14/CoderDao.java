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
import com.example.jeed.s05.CoderPlain;

public class CoderDao {
    private static final Logger log = LogManager.getLogger(CoderDao.class);

    public List<CoderPlain> getPayedMoreThan(double low) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "FROM CoderPlain c WHERE c.salary > :low ORDER BY salary DESC";
            TypedQuery<CoderPlain> query = em.createQuery(jpql, CoderPlain.class);
            query.setParameter("low", low);
            return query.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public List<CoderPlain> getUntypedPayedMoreThan(double low) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "FROM CoderPlain c WHERE c.salary > :low ORDER BY salary DESC";
            Query query = em.createQuery(jpql);
            query.setParameter("low", low);
            return (List<CoderPlain>) query.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Optional<CoderPlain> getByName(String first, String last) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "FROM CoderPlain c WHERE c.firstName = :first and c.lastName = :last";
            TypedQuery<CoderPlain> query = em.createQuery(jpql, CoderPlain.class);
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

    public int deleteAllById(long low) {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = JpaUtil.createEntityManager();
            tx = em.getTransaction();
            String jpql = "DELETE FROM CoderPlain c WHERE c.id >= :low";
            Query query = em.createQuery(jpql);
            query.setParameter("low", low);
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
