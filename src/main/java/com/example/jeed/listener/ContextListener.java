/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jeed.dao.CarDao;
import com.example.jeed.dao.CarDaoImpl;
import com.example.jeed.dao.Employee4CarDao;
import com.example.jeed.dao.Employee4CarDaoImpl;
import com.example.jeed.dao.legacy.CountryDao;
import com.example.jeed.dao.legacy.DBInfoService;
import com.example.jeed.dao.legacy.Employee4TeamDao;
import com.example.jeed.dao.legacy.EmployeeDao;
import com.example.jeed.dao.legacy.Region4CountryDao;
import com.example.jeed.dao.legacy.RegionDao;
import com.example.jeed.dao.legacy.RegionEager4CountryDao;
import com.example.jeed.dao.legacy.TeamDao;
import com.example.jeed.svc.EmployeeCarService;
import com.example.jeed.util.EntityManagerService;

/**
 * Services and DAOs available to the servlets
 */
@WebListener
public class ContextListener implements ServletContextListener {
    private static final Logger log = LogManager.getLogger(ContextListener.class);
    public static final String EMS = "EntityManagerService";
    public static final String DB_INFO = "DBInfoService";
    public static final String EMPLOYEE_CAR_SVC = "EmployeeCarSvc";

    public static final String REGION_DAO = "RegionDao";
    public static final String EMPLOYEE_DAO = "EmployeeDao";
    public static final String CAR_DAO = "CarDao";
    public static final String CAR_DAO_LEGACY = "CarDaoLegacy";
    public static final String EMPLOYEE4CAR_DAO = "Employee4CarDao";
    public static final String EMPLOYEE4CAR_DAO_LEGACY = "Employee4CarDaoLegacy";
    public static final String COUNTRY_DAO = "CountryDao";
    public static final String REGION4COUNTRY_DAO = "Region4CountryDao";
    public static final String REGION_EAGER4COUNTRY_DAO = "RegionEager4CountryDao";
    public static final String TEAM_DAO = "TeamDao";
    public static final String EMPLOYEE4TEAM_DAO = "Employee4TeamDao";

    /**
     * Store singletons in servlet context attributes
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.traceEntry();

        ServletContext context = sce.getServletContext();

        // Entity Manager Service
        EntityManagerService ems = new EntityManagerService();
        context.setAttribute(EMS, ems);

        // Legacy DAOs
        context.setAttribute(REGION_DAO, new RegionDao(ems));
        context.setAttribute(EMPLOYEE_DAO, new EmployeeDao(ems));
        context.setAttribute(COUNTRY_DAO, new CountryDao(ems));
        context.setAttribute(REGION4COUNTRY_DAO, new Region4CountryDao(ems));
        context.setAttribute(REGION_EAGER4COUNTRY_DAO, new RegionEager4CountryDao(ems));
        context.setAttribute(TEAM_DAO, new TeamDao(ems));
        context.setAttribute(EMPLOYEE4TEAM_DAO, new Employee4TeamDao(ems));
        context.setAttribute(CAR_DAO_LEGACY, new com.example.jeed.dao.legacy.CarDao(ems));
        context.setAttribute(EMPLOYEE4CAR_DAO_LEGACY, new com.example.jeed.dao.legacy.Employee4CarDao(ems));

        // Refactored DAOs
        CarDao car = new CarDaoImpl();
        Employee4CarDao emp4car = new Employee4CarDaoImpl();
        context.setAttribute(CAR_DAO, car);
        context.setAttribute(EMPLOYEE4CAR_DAO, emp4car);

        // Other services
        context.setAttribute(DB_INFO, new DBInfoService(ems));
        context.setAttribute(EMPLOYEE_CAR_SVC, new EmployeeCarService(car, emp4car, ems));
    }

    /**
     * Close the entity manager service
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        // EMS should be shut down
        ((EntityManagerService) context.getAttribute(EMS)).close();

        // defensive approach, even if it is not a necessity, remove each attribute set
        // in contextInitialized()
        context.removeAttribute(EMPLOYEE_CAR_SVC);
        context.removeAttribute(DB_INFO);
        context.removeAttribute(EMPLOYEE4CAR_DAO);
        context.removeAttribute(CAR_DAO);

        context.removeAttribute(EMPLOYEE4TEAM_DAO);
        context.removeAttribute(TEAM_DAO);
        context.removeAttribute(REGION_EAGER4COUNTRY_DAO);
        context.removeAttribute(REGION4COUNTRY_DAO);
        context.removeAttribute(COUNTRY_DAO);
        context.removeAttribute(EMPLOYEE_DAO);
        context.removeAttribute(REGION_DAO);

        log.traceExit();
    }
}
