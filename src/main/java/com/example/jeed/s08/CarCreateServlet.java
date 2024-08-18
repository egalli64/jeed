/*
 * Introduction to Jakarta Enterprise Edition - JPA on Hibernate
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.s08;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jeed.dao.Car;
import com.example.jeed.dao.CarDao;
import com.example.jeed.dao.ContextListener;
import com.example.jeed.dao.DBInfoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Create entity by EntityManager::persist() with id generated by DBMS
 * 
 * @see Car entity with id as GeneratedValue following SEQUENCE strategy
 * @see CarDao the DAO that actually does the job
 */
@SuppressWarnings("serial")
@WebServlet("/s08/car/create")
public class CarCreateServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(CarCreateServlet.class);
    private DBInfoService dbInfo;

    @Override
    public void init() throws ServletException {
        dbInfo = (DBInfoService) getServletContext().getAttribute(ContextListener.DB_INFO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String dbName = dbInfo.getName();
        log.traceEntry("{} on a {} DBMS", name, dbName);

        if (dbName.equals("MySQL")) {
            request.setAttribute("message", "MySQL does not support id generated by sequence");
        } else {
            throw new IllegalStateException("Only call for MySQL expected here");
        }

        request.getRequestDispatcher("/s08/newEntity.jsp").forward(request, response);
    }
}
