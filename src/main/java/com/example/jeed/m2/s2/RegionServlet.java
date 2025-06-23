/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.m2.s2;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jeed.dao.legacy.EntityManagerService;
import com.example.jeed.dao.legacy.Region;
import com.example.jeed.listener.ContextListener;

import jakarta.persistence.EntityManager;

/**
 * Use of EntityManager::find() to get a region by id. The code here to manage
 * the entity should be part of a DAO. Actually, the support classes used here
 * are defined in the dao package.
 * 
 * @see ContextListener ServletContextListener to get the EntityManagerService
 * @see EntityManagerService Single access point to the EntityManagerFactory to
 *      get an EntityManager
 * @see Region JPA entity
 */
@SuppressWarnings("serial")
@WebServlet("/m2/s2/region")
public class RegionServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(RegionServlet.class);
    private EntityManagerService service;

    @Override
    public void init() throws ServletException {
        service = (EntityManagerService) getServletContext().getAttribute(ContextListener.EMS);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String parameter = request.getParameter("id");
        log.traceEntry(parameter);

        // each EM should be closed at its termination
        try (EntityManager em = service.createEntityManager()) {
            Region region = em.find(Region.class, Integer.parseInt(parameter));
            request.setAttribute("region", region);
        } catch (Exception ex) {
            log.error("Can't get region", ex);
        }

        request.getRequestDispatcher("/region.jsp").forward(request, response);
    }
}
