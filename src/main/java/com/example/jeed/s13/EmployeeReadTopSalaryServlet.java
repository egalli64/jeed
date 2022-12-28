/*
 * Introduction to Jakarta Enterprise Edition - JPA on Hibernate
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.s13;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jeed.dao.ContextListener;
import com.example.jeed.dao.Employee;
import com.example.jeed.dao.EmployeeDao;

/**
 * Use of EntityManager::createNamedQuery()
 * 
 * @see EmployeeDao the DAO that actually does the job
 * @see Employee the associated DTO
 */
@SuppressWarnings("serial")
@WebServlet("/s13/employee/salary/top")
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
