/*
 * Introduction to Jakarta Enterprise Edition - JPA on Hibernate
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.s06;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate Session service keeping track of an annotated class
 */
public final class SessionService {
    private static final Logger log = LogManager.getLogger(SessionService.class);

    private SessionFactory sessionFactory;

    public SessionService() {
        log.traceEntry();
        Configuration configuration = new Configuration();
        Properties settings = new Properties();

        settings.put(Environment.JAKARTA_JTA_DATASOURCE, "java:comp/env/jdbc/hron");
        settings.put(Environment.SHOW_SQL, "true");
        configuration.setProperties(settings);
        configuration.addAnnotatedClass(Region.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
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
