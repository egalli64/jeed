package com.example.jed.s12;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnitUtil;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jed.dao.JpaUtil;
import com.example.jed.s06.CoderPlain;

public class CoderDao {
    private static final Logger log = LoggerFactory.getLogger(CoderDao.class);

    public Optional<CoderPlain> read(long id) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            CoderPlain coder = em.find(CoderPlain.class, id);
            return Optional.ofNullable(coder);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public CoderPlain readRaw(long id) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            return em.find(CoderPlain.class, id);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Optional<CoderPlain> readAndIncrease(long id, int delta) {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = JpaUtil.createEntityManager();
            tx = em.getTransaction();
            CoderPlain coder = em.find(CoderPlain.class, id);
            tx.begin();
            coder.setSalary(coder.getSalary() + delta);
            tx.commit();
            return Optional.of(coder);
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Optional<CoderPlain> readProxy(long id) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            CoderPlain coder = em.getReference(CoderPlain.class, id);

            PersistenceUnitUtil uu = JpaUtil.getPersistenceUnitUtil();
            if (!uu.isLoaded(coder)) {
                log.trace("Converting from proxy to real coder");
                Hibernate.initialize(coder);
            }

            return Optional.ofNullable(coder);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Optional<CoderPlain> readRefresh(long id) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            CoderPlain coder = em.find(CoderPlain.class, id);

            coder.setLastName("Something else");

            log.debug("Coder is: " + coder);
            em.refresh(coder);
            log.debug("Coder is: " + coder);

            return Optional.ofNullable(coder);
        } catch (Exception ex) {
            log.error("Find + refresh failure", ex);
            return Optional.empty();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
