/*
 * Introduction to Jakarta Enterprise Edition - JPA on Hibernate
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.s07;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jeed.dao.ContextListener;
import com.example.jeed.dao.EntityManagerService;

import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Check connection on a JPA entity manager
 */
@SuppressWarnings("serial")
@WebServlet("/s07/em")
public class EntityManagerServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(EntityManagerServlet.class);
    private EntityManagerService service;

    @Override
    public void init() throws ServletException {
        service = (EntityManagerService) getServletContext().getAttribute(ContextListener.EMS);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();

        // each EM should be closed at its termination
        try (EntityManager em = service.createEntityManager()) {
            request.setAttribute("activated", true);
        } catch (Exception ex) {
            log.error("Can't get an entity manager", ex);
        }

        request.getRequestDispatcher("active.jsp").forward(request, response);
    }
}
