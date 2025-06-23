/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.util;

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
        try {
            this.factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        } catch (Exception e) {
            log.error("Can't create Entity Manager Factory", e);
            throw e;
        }
    }

    /**
     * An entity manager from the factory, it has to be closed after use
     * 
     * @return an entity manager
     */
    public EntityManager createEntityManager() {
        log.traceEntry();
        return factory.createEntityManager();
    }

    /**
     * Access to utility methods for the persistence unit
     * 
     * @return reference to the persistence unit utility
     */
    public PersistenceUnitUtil getPersistenceUnitUtil() {
        log.traceEntry();
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
