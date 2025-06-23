/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.m3.s5;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;

import com.example.jeed.dao.legacy.Country;
import com.example.jeed.dao.legacy.Region4Country;
import com.example.jeed.dao.legacy.Region4CountryDao;
import com.example.jeed.listener.ContextListener;

/**
 * Get all "one" entities in a OneToMany relation (lazy)
 * 
 * @see Region4Country the associated JPA entity
 * @see Region4CountryDao the DAO that actually does the job
 */
@SuppressWarnings("serial")
@WebServlet("/m3/s5/region/all")
public class RegionGetAllServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(RegionGetAllServlet.class);
    private Region4CountryDao dao;

    @Override
    public void init() throws ServletException {
        dao = (Region4CountryDao) getServletContext().getAttribute(ContextListener.REGION4COUNTRY_DAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();

        List<Region4Country> regions = dao.readAll();
        proxyCheckDemo(regions);

        request.setAttribute("regions", regions);
        request.getRequestDispatcher("/m3/s5/regions.jsp").forward(request, response);
    }

    /**
     * Just to show that really the set of countries is an uninitialized proxy
     * 
     * @param regions the result of a default JPQL select on a one to many relation
     */
    private void proxyCheckDemo(List<Region4Country> regions) {
        if (!regions.isEmpty()) {
            Region4Country region = regions.get(0);
            Set<Country> countries = region.getCountries();
            if (Hibernate.isInitialized(countries)) {
                log.warn("Unexpected");
            } else {
                log.trace("As expected, the country set is an uninitialized proxy");
            }
        }
    }
}
