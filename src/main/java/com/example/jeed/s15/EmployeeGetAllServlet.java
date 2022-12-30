/*
 * Introduction to Jakarta Enterprise Edition - JPA on Hibernate
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.s15;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jeed.dao.ContextListener;
import com.example.jeed.dao.Employee4Car;
import com.example.jeed.dao.Employee4CarDao;

/**
 * Get all entities using OneToOne JPA annotation
 * 
 * @see Employee4Car the associated JPA entity
 * @see Employee4CarDao the DAO that actually does the job
 */
@SuppressWarnings("serial")
@WebServlet("/s15/employee/all")
public class EmployeeGetAllServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(EmployeeGetAllServlet.class);
    private Employee4CarDao dao;

    @Override
    public void init() throws ServletException {
        dao = (Employee4CarDao) getServletContext().getAttribute(ContextListener.EMPLOYEE4CAR_DAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();

        List<Employee4Car> employees = dao.readAll();
        log.debug(employees.size() + " employees read");
        request.setAttribute("employees", employees);

        request.getRequestDispatcher("/s15/employeesCar.jsp").forward(request, response);
    }
}
