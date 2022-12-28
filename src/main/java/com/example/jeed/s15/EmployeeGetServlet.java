/*
 * Introduction to Jakarta Enterprise Edition - JPA on Hibernate
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.s15;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jeed.dao.ContextListener;
import com.example.jeed.dao.Employee4Car;
import com.example.jeed.dao.Employee4CarDao;

/**
 * Get a single entity using OneToOne JPA annotation
 * 
 * @see Employee4Car the associated JPA entity
 * @see Employee4CarDao the DAO that actually does the job
 */
@SuppressWarnings("serial")
@WebServlet("/s15/employee/get")
public class EmployeeGetServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(EmployeeGetServlet.class);
    private Employee4CarDao dao;

    @Override
    public void init() throws ServletException {
        dao = (Employee4CarDao) getServletContext().getAttribute(ContextListener.EMPLOYEE4CAR_DAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String param = request.getParameter("id");
        log.traceEntry(param);

        dao.read(Integer.valueOf(param)).ifPresentOrElse(emp -> request.setAttribute("employee", emp),
                () -> log.info(String.format("Employee %d not found", param)));

        request.getRequestDispatcher("/carEmployee.jsp").forward(request, response);
    }
}
