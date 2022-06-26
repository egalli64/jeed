package com.example.jeed.dao;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public abstract class Dao<T, U> {
    static private final Logger log = LogManager.getLogger(Dao.class);

    private final Class<T> clazz;

    // workaround for template type erasure
    public Dao(Class<T> clazz) {
        this.clazz = clazz;
    }

    public List<T> readAll() {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            String jpql = "SELECT e FROM " + clazz.getName() + " e";
            return em.createQuery(jpql, clazz).getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Optional<T> read(U id) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            return Optional.ofNullable(em.find(clazz, id));
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean create(T entity) {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = JpaUtil.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.persist(entity);
            tx.commit();
            return true;
        } catch (Exception ex) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception e) {
                log.error("Can't rollback transaction", e);
            }
            log.error("Can't persist entity", ex);
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean contains(T entity) {
        EntityManager em = null;

        try {
            em = JpaUtil.createEntityManager();
            em.refresh(entity);
            return em.contains(entity);
        } catch (Exception ex) {
            log.error("Can't check if entity is contained in current context", ex);
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean update(T entity) {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = JpaUtil.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.merge(entity);
            tx.commit();
            return true;
        } catch (Exception ex) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception e) {
                log.error("Can't rollback transaction", e);
            }
            log.error("Can't merge entity", ex);
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean delete(U id) {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = JpaUtil.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            T entity = em.find(clazz, id);
            if (entity != null) {
                em.remove(entity);
                tx.commit();
                return true;
            } else {
                log.info("Can't remove missing entity " + id);
                tx.rollback();
                return false;
            }
        } catch (Exception ex) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception e) {
                log.error("Can't rollback transaction", e);
            }
            log.error("Can't remove entity " + id, ex);
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
