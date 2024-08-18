/*
 * Introduction to Jakarta Enterprise Edition - JPA on Hibernate
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.dao;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * DAOs available to the servlet
 */
@WebListener
public class ContextListener implements ServletContextListener {
    private static final Logger log = LogManager.getLogger(ContextListener.class);
    public static final String EMS = "EntityManagerService";
    public static final String DB_INFO = "DBInfoService";
    public static final String REGION_DAO = "RegionDao";
    public static final String EMPLOYEE_DAO = "EmployeeDao";
    public static final String CAR_DAO = "CarDao";
    public static final String EMPLOYEE4CAR_DAO = "Employee4CarDao";
    public static final String COUNTRY_DAO = "CountryDao";
    public static final String REGION4COUNTRY_DAO = "Region4CountryDao";
    public static final String REGION_EAGER4COUNTRY_DAO = "RegionEager4CountryDao";
    public static final String TEAM_DAO = "TeamDao";
    public static final String EMPLOYEE4TEAM_DAO = "Employee4TeamDao";

    /**
     * Store DAOs in servlet context attributes
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.traceEntry();
        EntityManagerService ems = new EntityManagerService();
        sce.getServletContext().setAttribute(EMS, ems);
        sce.getServletContext().setAttribute(DB_INFO, new DBInfoService(ems));
        sce.getServletContext().setAttribute(REGION_DAO, new RegionDao(ems));
        sce.getServletContext().setAttribute(EMPLOYEE_DAO, new EmployeeDao(ems));
        sce.getServletContext().setAttribute(CAR_DAO, new CarDao(ems));
        sce.getServletContext().setAttribute(EMPLOYEE4CAR_DAO, new Employee4CarDao(ems));
        sce.getServletContext().setAttribute(COUNTRY_DAO, new CountryDao(ems));
        sce.getServletContext().setAttribute(REGION4COUNTRY_DAO, new Region4CountryDao(ems));
        sce.getServletContext().setAttribute(REGION_EAGER4COUNTRY_DAO, new RegionEager4CountryDao(ems));
        sce.getServletContext().setAttribute(TEAM_DAO, new TeamDao(ems));
        sce.getServletContext().setAttribute(EMPLOYEE4TEAM_DAO, new Employee4TeamDao(ems));
    }

    /**
     * Close the entity manager service
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ((EntityManagerService) sce.getServletContext().getAttribute(EMS)).close();
        log.traceExit();
    }
}
