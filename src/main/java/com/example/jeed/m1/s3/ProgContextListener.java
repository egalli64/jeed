/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.m1.s3;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Manage the SessionManager (native, programmatic) for the application
 */
@WebListener
public class ProgContextListener implements ServletContextListener {
    private static final Logger log = LogManager.getLogger(ProgContextListener.class);
    public static final String SESSION_SVC = "ProgSessionSvc";

    /**
     * Store the session service in a servlet context attribute
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.traceEntry();
        sce.getServletContext().setAttribute(SESSION_SVC, new ProgSessionService());
    }

    /**
     * Close the session services
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ((ProgSessionService) sce.getServletContext().getAttribute(SESSION_SVC)).close();
        log.traceExit();
    }
}
