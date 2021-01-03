package com.example.jed.s14;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jed.dao.JpaUtil;
import com.example.jed.s05.Coder05;

public class CoderDao {
    private static final Logger log = LoggerFactory.getLogger(CoderDao.class);

    public List<Coder05> getPayedMoreThan(double low) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "FROM Coder05 c WHERE c.salary > :low ORDER BY salary DESC";
            TypedQuery<Coder05> query = em.createQuery(jpql, Coder05.class);
            query.setParameter("low", low);
            return query.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public List<Coder05> getUntypedPayedMoreThan(double low) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "FROM Coder05 c WHERE c.salary > :low ORDER BY salary DESC";
            Query query = em.createQuery(jpql);
            query.setParameter("low", low);
            return (List<Coder05>) query.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Optional<Coder05> getByName(String first, String last) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "FROM Coder05 c WHERE c.firstName = :first and c.lastName = :last";
            TypedQuery<Coder05> query = em.createQuery(jpql, Coder05.class);
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
            String jpql = "DELETE FROM Coder05 c WHERE c.id >= :low";
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
