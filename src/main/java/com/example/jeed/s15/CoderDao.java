package com.example.jeed.s15;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jeed.dao.JpaUtil;

public class CoderDao {
    private static final Logger log = LogManager.getLogger(CoderDao.class);

    public boolean create(CoderGV coder) {
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
}
