/*
 * Introduction to Jakarta Enterprise Edition - JPA on Hibernate
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.s07;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jeed.dao.ContextListener;
import com.example.jeed.dao.EntityManagerService;
import com.example.jeed.dao.Region;

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
@WebServlet("/s07/region")
public class RegionServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(RegionServlet.class);
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
            Region region = em.find(Region.class, Integer.parseInt(parameter));
            request.setAttribute("region", region);
        } catch (Exception ex) {
            log.error("Can't get region", ex);
        } finally {
            // each EM created should be closed
            em.close();
        }

        request.getRequestDispatcher("/region.jsp").forward(request, response);
    }
}
