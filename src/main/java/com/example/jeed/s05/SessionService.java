/*
 * Introduction to Jakarta Enterprise Edition - JPA on Hibernate
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.s05;

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
 * Hibernate Native Session Manager
 */
public final class SessionService {
    private static final Logger log = LogManager.getLogger(SessionService.class);

    private SessionFactory sessionFactory;
    public final InitializationType type;

    public SessionService(InitializationType type) {
        this.type = type;
        log.traceEntry("Initialization in {} mode", type);

        switch (type) {
        case CODE -> {
            Configuration configuration = new Configuration();
            Properties settings = new Properties();

            settings.put(Environment.JAKARTA_JTA_DATASOURCE, "java:comp/env/jdbc/hron");
            settings.put(Environment.SHOW_SQL, "true");
            configuration.setProperties(settings);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        case XML -> {
            Configuration configuration = new Configuration().configure("s05/minimal.xml");
            sessionFactory = configuration.buildSessionFactory();
        }
        default -> log.info("Session Factory not initialized");
        }
    }

    /**
     * A session from the factory, it has to be closed after use
     * 
     * @return a session
     * @throws NullPointerException when called on a SessionType.DISABLED
     */
    public Session getSession() {
        log.traceEntry();
        return sessionFactory.openSession();
    }

    /**
     * Close the factory
     */
    public void close() {
        if (type == InitializationType.DISABLED) {
            log.trace("Native session disabled");
        } else {
            log.traceEntry();
            sessionFactory.close();
        }
    }
}
