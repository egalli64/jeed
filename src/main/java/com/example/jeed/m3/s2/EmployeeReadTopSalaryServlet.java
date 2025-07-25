/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.m3.s2;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jeed.dao.legacy.Employee;
import com.example.jeed.dao.legacy.EmployeeDao;
import com.example.jeed.listener.ContextListener;

/**
 * Use of EntityManager::createNamedQuery()
 * 
 * @see EmployeeDao the DAO that actually does the job
 * @see Employee the associated DTO
 */
@SuppressWarnings("serial")
@WebServlet("/m3/s2/employee/salary/top")
public class EmployeeReadTopSalaryServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(EmployeeReadTopSalaryServlet.class);
    private EmployeeDao dao;

    @Override
    public void init() throws ServletException {
        dao = (EmployeeDao) getServletContext().getAttribute(ContextListener.EMPLOYEE_DAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String param = request.getParameter("limit");
        log.traceEntry(param);

        List<Employee> employees = dao.readBySalaryTop(Double.parseDouble(param));
        request.setAttribute("employees", employees);

        request.getRequestDispatcher("/employees.jsp").forward(request, response);
    }
}
