/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;

/**
 * Provides CRUD operations, plus contains
 */
public interface GenericDao<T, PK extends Serializable> {
    void create(T entity);

    Optional<T> read(PK id);

    List<T> readAll();

    void update(T entity);

    void delete(PK id);

    boolean contains(T entity);

    /**
     * Let the service set the Entity Manager on a thread-local basis
     * 
     * @param em the entity manager to be used in the current DAO
     */
    void setEm(EntityManager em);

    void removeEm();
}
