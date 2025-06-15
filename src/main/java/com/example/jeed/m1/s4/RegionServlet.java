/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.m1.s4;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.jeed.dao.Region;
import com.example.jeed.m1.s3.ProgContextListener;
import com.example.jeed.m1.s3.ProgSessionService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Find region by id
 */
@SuppressWarnings("serial")
@WebServlet("/m1/s4/region")
public class RegionServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(RegionServlet.class);
    private ProgSessionService service;

    @Override
    public void init() throws ServletException {
        service = (ProgSessionService) getServletContext().getAttribute(ProgContextListener.SESSION_SVC);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String parameter = request.getParameter("id");
        log.traceEntry(parameter);

        try (Session session = service.getSession()) {
            // robust but painful - a framework could take care of it for us
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Region region = session.find(Region.class, Integer.parseInt(parameter));
                request.setAttribute("region", region);

                tx.commit();
            } catch (Exception ex) {
                try {
                    if (tx != null && tx.isActive()) {
                        tx.rollback();
                    }
                } catch (Exception e) {
                    ex.addSuppressed(e);
                }
                log.error("Can't get region", ex);
            }
        }

        request.getRequestDispatcher("/region.jsp").forward(request, response);
    }
}
