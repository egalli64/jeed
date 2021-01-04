package com.example.jed.s17;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jed.dao.JpaUtil;

public class CoderDao {
    static private final Logger log = LoggerFactory.getLogger(CoderDao.class);

    public List<Coder1To1> readAll() {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "SELECT c FROM Coder1To1 c";
            return em.createQuery(jpql, Coder1To1.class).getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Optional<Coder1To1> read(int id) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            return Optional.ofNullable(em.find(Coder1To1.class, id));
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean create(Coder1To1 coder) {
        log.trace("enter");
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = JpaUtil.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.persist(coder);
            tx.commit();
            return true;
        } catch (Exception ex) {
            log.warn("Can't persist entity", ex);
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception e) {
                log.warn("Can't rollback transaction", e);
            }

            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
