package com.example.jeed.s12;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jeed.dao.JpaUtil;
import com.example.jeed.s05.EmployeePlain;

public class EmployeeDao {
    private static final Logger log = LogManager.getLogger(EmployeeDao.class);

    public boolean create(EmployeePlain employee) {
        EntityManager em = null;
        EntityTransaction tx = null;
        log.trace("enter");

        try {
            em = JpaUtil.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.persist(employee);
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

    public boolean update(EmployeePlain employee) {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = JpaUtil.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.merge(employee);
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
            EmployeePlain entity = em.find(EmployeePlain.class, id);
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
