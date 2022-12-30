/*
 * Introduction to Jakarta Enterprise Edition - JPA on Hibernate
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.s17;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jeed.dao.ContextListener;
import com.example.jeed.dao.Team;
import com.example.jeed.dao.TeamDao;

/**
 * Get all entities in a ManyToMany relation (eager on demand)
 * 
 * @see Team the associated JPA entity
 * @see TeamDao the DAO that actually does the job
 */
@SuppressWarnings("serial")
@WebServlet("/s17/team/allEager")
public class TeamGetAllEagerServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(TeamGetAllEagerServlet.class);
    private TeamDao dao;

    @Override
    public void init() throws ServletException {
        dao = (TeamDao) getServletContext().getAttribute(ContextListener.TEAM_DAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();

        request.setAttribute("teams", dao.readAllEager());
        request.getRequestDispatcher("/s17/teamsEmployees.jsp").forward(request, response);
    }
}
