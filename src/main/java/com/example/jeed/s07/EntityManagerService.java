/*
 * Introduction to Jakarta Enterprise Edition - JPA on Hibernate
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.s07;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Service for Entity Manager
 */
public final class EntityManagerService {
    private static final Logger log = LogManager.getLogger(EntityManagerService.class);

    private EntityManagerFactory factory;

    public EntityManagerService() {
        log.traceEntry();
        this.factory = Persistence.createEntityManagerFactory("hron");
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
     * Close the factory
     */
    public void close() {
        log.traceEntry();
        factory.close();
    }
}
