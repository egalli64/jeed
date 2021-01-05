package com.example.jed.s19;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/s19/teams")
public class TeamAll extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TeamAll.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.trace("enter");

        TeamDao dao = new TeamDao();
        List<TeamMToM> teams = dao.readAllLazy();
        proxyCheckDemo(teams);

        request.setAttribute("teams", teams);
        request.getRequestDispatcher("/teamsMinimal.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void proxyCheckDemo(List<TeamMToM> teams) {
        if (!teams.isEmpty()) {
            TeamMToM team = teams.get(0);
            Set<CoderMToM> coders = team.getCoders();
            if (Hibernate.isInitialized(coders)) {
                log.warn("Unexpected");
            } else {
                log.trace("As expected, we have an unusable proxy in coders");
            }
        }
    }
}
