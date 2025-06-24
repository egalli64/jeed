/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

/**
 * Basic implementation for DAO - a richer DAO could add more features
 */
public abstract class AbstractGenericDao<T, PK extends Serializable> implements GenericDao<T, PK> {
    static private final Logger log = LogManager.getLogger(AbstractGenericDao.class);

    protected static final ThreadLocal<EntityManager> currentEntityManager = new ThreadLocal<>();

    protected final Class<T> clazz;

    public AbstractGenericDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void setEm(EntityManager em) {
        Objects.requireNonNull(em);
        currentEntityManager.set(em);
    }

    @Override
    public void removeEm() {
        currentEntityManager.remove();
    }

    protected EntityManager getEm() {
        EntityManager em = currentEntityManager.get();
        if (em == null || !em.isOpen()) {
            throw new IllegalStateException("Bad EM");
        }
        return em;
    }

    @Override
    public void create(T entity) {
        getEm().persist(entity);
    }

    @Override
    public Optional<T> read(PK id) {
        return Optional.ofNullable(getEm().find(clazz, id));
    }

    @Override
    public void update(T entity) {
        getEm().merge(entity);
    }

    @Override
    public void delete(PK id) {
        EntityManager em = getEm();
        T entity = em.find(clazz, id);
        if (entity != null) {
            em.remove(entity);
        } else {
            // TODO: consider if it is worth throwing an exception
            log.warn("Can't remove missing entity {}", id);
        }
    }

    @Override
    public List<T> readAll() {
        TypedQuery<T> query = getEm().createQuery("SELECT e FROM " + clazz.getSimpleName() + " e", clazz);
        return query.getResultList();
    }

    /**
     * Check for existence in the current context
     * 
     * @param entity the entity to be checked
     * @return true if it is a managed entity instance in the persistence context
     */
    public boolean contains(T entity) {
        log.traceEntry();

        EntityManager em = getEm();
        em.refresh(entity);
        return em.contains(entity);
    }
}
