package com.example.jed.s11;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnitUtil;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jed.dao.JpaUtil;
import com.example.jed.s05.Coder05;

public class CoderDao {
    private static final Logger log = LoggerFactory.getLogger(CoderDao.class);

    public Optional<Coder05> read(long id) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            Coder05 coder = em.find(Coder05.class, id);
            return Optional.ofNullable(coder);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Coder05 readRaw(long id) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            return em.find(Coder05.class, id);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Optional<Coder05> readAndIncrease(long id, int delta) {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = JpaUtil.createEntityManager();
            tx = em.getTransaction();
            Coder05 coder = em.find(Coder05.class, id);
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

    public Optional<Coder05> readProxy(long id) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            Coder05 coder = em.getReference(Coder05.class, id);

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
}
