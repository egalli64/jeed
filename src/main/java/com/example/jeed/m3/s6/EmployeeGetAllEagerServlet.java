/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.m3.s6;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jeed.dao.Employee4Team;
import com.example.jeed.dao.Employee4TeamDao;
import com.example.jeed.listener.ContextListener;

/**
 * Get all entities in a ManyToMany relation (eager on demand)
 * 
 * @see Employee4Team the associated JPA entity
 * @see Employee4TeamDao the DAO that actually does the job
 */
@SuppressWarnings("serial")
@WebServlet("/m3/s6/employee/allEager")
public class EmployeeGetAllEagerServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(EmployeeGetAllEagerServlet.class);
    private Employee4TeamDao dao;

    @Override
    public void init() throws ServletException {
        dao = (Employee4TeamDao) getServletContext().getAttribute(ContextListener.EMPLOYEE4TEAM_DAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();

        request.setAttribute("employees", dao.readAllEager());
        request.getRequestDispatcher("/m3/s6/employeesTeams.jsp").forward(request, response);
    }
}
