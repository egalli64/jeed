/*
 * Introduction to Jakarta Enterprise Edition - JPA on Hibernate
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.s05;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Native Session Manager configured by XML
 */
public final class SessionXmlService {
    private static final Logger log = LogManager.getLogger(SessionXmlService.class);

    private SessionFactory sessionFactory;

    public SessionXmlService() {
        log.traceEntry();
        Configuration configuration = new Configuration().configure("s05/minimal.xml");
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
        log.traceEntry();
        sessionFactory.close();
    }
}
