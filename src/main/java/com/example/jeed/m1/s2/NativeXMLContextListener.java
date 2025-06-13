/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.m1.s2;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Manage the SessionManager (native, XML-based) for the application
 */
@WebListener
public class NativeXMLContextListener implements ServletContextListener {
    private static final Logger log = LogManager.getLogger(NativeXMLContextListener.class);
    public static final String NATIVE_XML_SESSION = "nativeXMLSessionManager";

    /**
     * Store session services in servlet context attributes
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.traceEntry();
        sce.getServletContext().setAttribute(NATIVE_XML_SESSION, new NativeXMLSessionService());
    }

    /**
     * Close the session services
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ((NativeXMLSessionService) sce.getServletContext().getAttribute(NATIVE_XML_SESSION)).close();
        log.traceExit();
    }
}
