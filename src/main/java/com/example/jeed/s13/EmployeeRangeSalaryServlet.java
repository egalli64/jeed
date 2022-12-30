/*
 * Introduction to Jakarta Enterprise Edition - JPA on Hibernate
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.s13;

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
import com.example.jeed.dao.Employee;
import com.example.jeed.dao.EmployeeDao;

/**
 * Use of EntityManager::createQuery() - "typed" overload 
 * 
 * @see EmployeeDao the DAO that actually does the job
 * @see Employee the associated DTO
 */
@SuppressWarnings("serial")
@WebServlet("/s13/employee/salary/range")
public class EmployeeRangeSalaryServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(EmployeeRangeSalaryServlet.class);
    private EmployeeDao dao;

    @Override
    public void init() throws ServletException {
        dao = (EmployeeDao) getServletContext().getAttribute(ContextListener.EMPLOYEE_DAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String low = request.getParameter("low");
        String high = request.getParameter("high");
        log.traceEntry("[{}...{}]", low, high);

        List<Employee> employees = dao.readBySalaryRange(Double.parseDouble(low), Double.parseDouble(high));
        request.setAttribute("employees", employees);

        request.getRequestDispatcher("/employees.jsp").forward(request, response);
    }
}
