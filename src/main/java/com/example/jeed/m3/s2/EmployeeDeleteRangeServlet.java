/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.m3.s2;

import java.io.IOException;

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
 * Use of Query::executeUpdate() - DML commands
 * 
 * @see EmployeeDao the DAO that actually does the job
 * @see Employee the associated DTO
 */
@SuppressWarnings("serial")
@WebServlet("/m3/s2/employee/delete/range")
public class EmployeeDeleteRangeServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(EmployeeDeleteRangeServlet.class);
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

        int count = dao.deleteByIdBetween(Integer.parseInt(low), Integer.parseInt(high));
        request.setAttribute("count", count);
        log.debug("{} employee(s) deleted", count);

        request.getRequestDispatcher("/m3/s2/deletedEmployees.jsp").forward(request, response);
    }
}
