/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.m1.s2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Native Session Manager
 */
public final class NativeXMLSessionService {
    private static final Logger log = LogManager.getLogger(NativeXMLSessionService.class);
    private static final String HIBERNATE_CONFIG = "m1/s2/simple.xml";

    private SessionFactory sessionFactory;

    public NativeXMLSessionService() {
        log.traceEntry();

        Configuration configuration = new Configuration().configure(HIBERNATE_CONFIG);
        sessionFactory = configuration.buildSessionFactory();
    }

    /**
     * A session from the factory, it has to be closed after use
     * 
     * @return a session
     */
    public Session getSession() {
        log.traceEntry();
        return sessionFactory.openSession();
    }

    /**
     * Close the factory
     */
    public void close() {
        sessionFactory.close();
        log.traceExit();
    }
}
