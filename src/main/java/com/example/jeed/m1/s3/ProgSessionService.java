/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.m1.s3;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.example.jeed.dao.Region;

/**
 * Hibernate Programmatic Session Manager
 */
public final class ProgSessionService {
    private static final Logger log = LogManager.getLogger(ProgSessionService.class);

    private SessionFactory sessionFactory;

    public ProgSessionService() {
        log.traceEntry();

        Properties settings = new Properties();
        settings.put(Environment.JAKARTA_JTA_DATASOURCE, "java:comp/env/jdbc/hron");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.FORMAT_SQL, "true");

        Configuration configuration = new Configuration();
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
        sessionFactory.close();
        log.traceExit();
    }
}
