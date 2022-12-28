/*
 * Introduction to Jakarta Enterprise Edition - JPA on Hibernate
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.dao;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * DAOs available to the servlet
 */
@WebListener
public class ContextListener implements ServletContextListener {
    private static final Logger log = LogManager.getLogger(ContextListener.class);
    public static final String DAO_EMS = "DaoEntityManagerService";
    public static final String REGION_DAO = "RegionDao";
    public static final String EMPLOYEE_DAO = "EmployeeDao";

    /**
     * Store DAOs in servlet context attributes
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.traceEntry();
        EntityManagerService ems = new EntityManagerService();
        sce.getServletContext().setAttribute(DAO_EMS, ems);
        sce.getServletContext().setAttribute(REGION_DAO, new RegionDao(ems));
        sce.getServletContext().setAttribute(EMPLOYEE_DAO, new EmployeeDao(ems));
    }

    /**
     * Close the entity manager service
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ((EntityManagerService) sce.getServletContext().getAttribute(DAO_EMS)).close();
        log.traceExit();
    }
}
