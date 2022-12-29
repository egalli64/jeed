/*
 * Introduction to Jakarta Enterprise Edition - JPA on Hibernate
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.s11;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;

import com.example.jeed.dao.ContextListener;
import com.example.jeed.dao.EntityManagerService;
import com.example.jeed.dao.Region;

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
@WebServlet("/s11/region/reference")
public class RegionReferenceServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(RegionReferenceServlet.class);
    private EntityManagerService service;

    @Override
    public void init() throws ServletException {
        service = (EntityManagerService) getServletContext().getAttribute(ContextListener.DAO_EMS);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String parameter = request.getParameter("id");
        log.traceEntry(parameter);

        EntityManager em = service.createEntityManager();
        try {
            Region region = em.getReference(Region.class, Integer.parseInt(parameter));
            log.trace("Got reference for entity");

            PersistenceUnitUtil uu = service.getPersistenceUnitUtil();
            log.trace("Entity loading status is {}", uu.isLoaded(region));

            Hibernate.initialize(region);
            log.trace("Entity loading status is {}", uu.isLoaded(region));

            request.setAttribute("region", region);
        } catch (Exception ex) {
            log.error("Can't get region", ex);
        } finally {
            em.close();
        }

        request.getRequestDispatcher("/region.jsp").forward(request, response);
    }
}