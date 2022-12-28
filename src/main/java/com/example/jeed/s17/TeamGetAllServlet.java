/*
 * Introduction to Jakarta Enterprise Edition - JPA on Hibernate
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.s17;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;

import com.example.jeed.dao.ContextListener;
import com.example.jeed.dao.Team;
import com.example.jeed.dao.TeamDao;

/**
 * Get all entities in a ManyToMany relation (lazy)
 * 
 * @see Team the associated JPA entity
 * @see TeamDao the DAO that actually does the job
 */
@SuppressWarnings("serial")
@WebServlet("/s17/team/all")
public class TeamGetAllServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(TeamGetAllServlet.class);
    private TeamDao dao;

    @Override
    public void init() throws ServletException {
        dao = (TeamDao) getServletContext().getAttribute(ContextListener.TEAM_DAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();

        List<Team> teams = dao.readAll();
        proxyCheckDemo(teams);

        request.setAttribute("teams", teams);
        request.getRequestDispatcher("/s17/teams.jsp").forward(request, response);
    }

    /**
     * Just to show that really the set of employees is an uninitialized proxy
     * 
     * @param teams the result of a default JPQL select on a many to many relation
     */
    private void proxyCheckDemo(List<Team> teams) {
        if (!teams.isEmpty()) {
            Team team = teams.get(0);
            if (Hibernate.isInitialized(team.getEmployees())) {
                log.warn("Unexpected");
            } else {
                log.trace("As expected, the car set is uninitialized");
            }
        }
    }
}
