/*
 * Introduction to Jakarta Enterprise Edition - JPA on Hibernate
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.s06;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Manage the NativeSessionManager for the web app
 */
@WebListener
public class ContextListener implements ServletContextListener {
    private static final Logger log = LogManager.getLogger(ContextListener.class);
    public static final String HRON_SESSION = "nativeSessionManager";

    /**
     * Store a session service in a servlet context attribute
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.traceEntry();
        sce.getServletContext().setAttribute(HRON_SESSION, new SessionService());
    }

    /**
     * Close the session service
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ((SessionService) sce.getServletContext().getAttribute(HRON_SESSION)).close();
        log.traceExit();
    }
}
