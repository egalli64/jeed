package com.example.jeed.s12;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jeed.dao.JpaUtil;
import com.example.jeed.s05.CoderPlain;

public class CoderDao {
    private static final Logger log = LoggerFactory.getLogger(CoderDao.class);

    public boolean create(CoderPlain coder) {
        EntityManager em = null;
        EntityTransaction tx = null;
        log.trace("enter");

        try {
            em = JpaUtil.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.persist(coder);
            tx.commit();
            return true;
        } catch (Exception ex) {
            log.error("Can't persist entity", ex);
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

    public boolean update(CoderPlain coder) {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = JpaUtil.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.merge(coder);
            tx.commit();
            return true;
        } catch (Exception ex) {
            log.warn("Can't merge entity", ex);
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

    public boolean delete(long id) {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = JpaUtil.createEntityManager();
            CoderPlain entity = em.find(CoderPlain.class, id);
            if (entity != null) {
                tx = em.getTransaction();
                tx.begin();
                em.remove(entity);
                tx.commit();
                return true;
            } else {
                log.info("Can't remove missing coder " + id);
                return false;
            }
        } catch (Exception ex) {
            log.warn("Can't remove coder " + id, ex);
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
