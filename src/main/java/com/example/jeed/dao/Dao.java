/*
 * Introduction to Jakarta Enterprise Edition - JPA on Hibernate
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.dao;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

/**
 * CRUD operations for the entities in DAO
 */
public abstract class Dao<T, U> {
    static private final Logger log = LogManager.getLogger(Dao.class);

    private final Class<T> clazz;
    protected final EntityManagerService service;

    /**
     * Ctor
     * 
     * @param clazz   workaround for template type erasure
     * @param factory an entity manager factory
     */
    protected Dao(Class<T> clazz, EntityManagerService service) {
        this.clazz = clazz;
        this.service = service;
    }
    
    /**
     * Get all the entities by JPQL
     * 
     * @return all the entities
     */
    public List<T> readAll() {
        try (EntityManager em = service.createEntityManager()) {
            String jpql = "SELECT e FROM " + clazz.getName() + " e";
            return em.createQuery(jpql, clazz).getResultList();
        }
    }

    /**
     * Ask the entity manager to find an entity by id
     * 
     * @param id the entity identity
     * @return the optional entity, possibly empty
     */
    public Optional<T> read(U id) {
        try (EntityManager em = service.createEntityManager()) {
            return Optional.ofNullable(em.find(clazz, id));
        }
    }
    
    /**
     * Persist the passed entity
     * 
     * @param entity the entity to persist
     * @return true if persisted
     */
    public boolean create(T entity) {
        EntityTransaction tx = null;
        try (EntityManager em = service.createEntityManager()) {
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
        }
    }

    /**
     * Check for existence in the current context
     * 
     * @param entity the entity to be checked
     * @return true if it is a managed entity instance in the persistence context
     */
    public boolean contains(T entity) {
        try (EntityManager em = service.createEntityManager()) {
            em.refresh(entity);
            return em.contains(entity);
        } catch (Exception ex) {
            log.error("Can't check if entity is contained in current context", ex);
            return false;
        }
    }

    /**
     * Merge the passed entity in the persistence context
     * 
     * @param entity the entity to be merged
     * @return true if the merging succeed
     */
    public boolean update(T entity) {
        EntityTransaction tx = null;

        try (EntityManager em = service.createEntityManager()) {
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
        }
    }

    /**
     * Remove the entity from the persistence context
     * 
     * @param id the entity identity
     * @return true if removed correctly
     */
    public boolean delete(U id) {
        EntityTransaction tx = null;

        try (EntityManager em = service.createEntityManager()) {
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
        }
    }
}
