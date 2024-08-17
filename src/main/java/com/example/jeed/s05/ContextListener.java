/*
 * Introduction to Jakarta Enterprise Edition - JPA on Hibernate
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.s05;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Manage the NativeSessionManager for the web app
 */
@WebListener
public class ContextListener implements ServletContextListener {
    private static final Logger log = LogManager.getLogger(ContextListener.class);
    public static final String NATIVE_SESSION = "nativeSessionManager";
    private static final InitializationType TYPE = InitializationType.XML;

    /**
     * Store session services in servlet context attributes
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.traceEntry();
        sce.getServletContext().setAttribute(NATIVE_SESSION, new NativeSessionService(TYPE));
    }

    /**
     * Close the session services
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ((NativeSessionService) sce.getServletContext().getAttribute(NATIVE_SESSION)).close();
        log.traceExit();
    }
}
