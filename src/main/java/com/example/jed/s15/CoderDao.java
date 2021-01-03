package com.example.jed.s15;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jed.dao.JpaUtil;

public class CoderDao {
    private static final Logger log = LoggerFactory.getLogger(CoderDao.class);

    public boolean create(Coder15 coder) {
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
