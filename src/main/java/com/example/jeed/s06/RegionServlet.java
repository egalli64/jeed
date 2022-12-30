/*
 * Introduction to Jakarta Enterprise Edition - JPA on Hibernate
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.s06;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

/**
 * Get region by id
 */
@SuppressWarnings("serial")
@WebServlet("/s06/region")
public class RegionServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(RegionServlet.class);
    private SessionService service;

    @Override
    public void init() throws ServletException {
        service = (SessionService) getServletContext().getAttribute(ContextListener.SESSION_6);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String parameter = request.getParameter("id");
        log.traceEntry(parameter);

        try (Session session = service.getSession()) {
            Region region = session.get(Region.class, Integer.parseInt(parameter));
            request.setAttribute("region", region);
        } catch (Exception ex) {
            log.error("Can't get region", ex);
        }

        request.getRequestDispatcher("/region.jsp").forward(request, response);
    }
}
