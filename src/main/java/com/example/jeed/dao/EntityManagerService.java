/*
 * Introduction to Jakarta Enterprise Edition - JPA on Hibernate
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnitUtil;

/**
 * Service to create entity managers and access to the persistence unit utility
 */
public final class EntityManagerService {
    private static final Logger log = LogManager.getLogger(EntityManagerService.class);
    private static final String PERSISTENCE_UNIT = "hron";

    private EntityManagerFactory factory;

    public EntityManagerService() {
        log.traceEntry("EMF for {}", PERSISTENCE_UNIT);
        this.factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    }

    /**
     * An entity manager from the factory, it has to be closed after use
     * 
     * @return an entity manager
     */
    public EntityManager createEntityManager() {
        return factory.createEntityManager();
    }

    /**
     * Access to utility methods for the persistence unit
     * 
     * @return reference to the persistence unit utility
     */
    public PersistenceUnitUtil getPersistenceUnitUtil() {
        return factory.getPersistenceUnitUtil();
    }

    /**
     * Close the factory
     */
    public void close() {
        factory.close();
        log.traceExit("EMF for {}", PERSISTENCE_UNIT);
    }
}
