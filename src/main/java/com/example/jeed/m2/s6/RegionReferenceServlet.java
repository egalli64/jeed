/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.m2.s6;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;

import com.example.jeed.dao.EntityManagerService;
import com.example.jeed.dao.Region;
import com.example.jeed.listener.ContextListener;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceUnitUtil;

/**
 * EntityManager::getReference(). The code here to manage the entity should be
 * part of a DAO. Actually, the support classes used here are defined in the dao
 * package.
 * 
 * @see ContextListener ServletContextListener to get the EntityManagerService
 * @see EntityManagerService Single access point to the EntityManagerFactory to
 *      get an EntityManager
 * @see Region JPA entity
 */
@SuppressWarnings("serial")
@WebServlet("/m2/s6/region/reference")
public class RegionReferenceServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(RegionReferenceServlet.class);
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

        try (EntityManager em = service.createEntityManager()) {
            Region region = em.getReference(Region.class, Integer.parseInt(parameter));
            log.trace("Got reference for entity");

            PersistenceUnitUtil uu = service.getPersistenceUnitUtil();
            log.trace("Entity loading status is {}", uu.isLoaded(region));

            Hibernate.initialize(region);
            log.trace("Entity loading status is {}", uu.isLoaded(region));

            request.setAttribute("region", region);
        } catch (Exception ex) {
            log.error("Can't get region", ex);
        }

        request.getRequestDispatcher("/region.jsp").forward(request, response);
    }
}
