/*
 * Introduction to Jakarta Enterprise Edition - JPA on Hibernate
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.s06;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Manage a session service for the web app
 */
@WebListener
public class ContextListener implements ServletContextListener {
    private static final Logger log = LogManager.getLogger(ContextListener.class);
    public static final String SESSION_6 = "sessionManager6";

    /**
     * Store a session service in a servlet context attribute
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.traceEntry();
        sce.getServletContext().setAttribute(SESSION_6, new SessionService());
    }

    /**
     * Close the session service
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ((SessionService) sce.getServletContext().getAttribute(SESSION_6)).close();
        log.traceExit();
    }
}
