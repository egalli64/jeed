/*
 * Introduction to Jakarta Enterprise Edition - JPA on Hibernate
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.s08;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jeed.dao.ContextListener;
import com.example.jeed.dao.Country;
import com.example.jeed.dao.CountryDao;

/**
 * Create entity by EntityManager::persist() with id passed by caller
 * 
 * @see Country entity with plain id
 * @see CountryDao the DAO that actually does the job
 */
@SuppressWarnings("serial")
@WebServlet("/s08/country/create")
public class CountryCreateServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(CountryCreateServlet.class);
    private CountryDao dao;

    @Override
    public void init() throws ServletException {
        dao = (CountryDao) getServletContext().getAttribute(ContextListener.COUNTRY_DAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        log.traceEntry("{} {}", id, name);

        Country country = new Country(id, name);
        request.setAttribute("message", dao.create(country) ? country : //
                String.format("Can't create country with id %s and name %s", id, name));

        request.getRequestDispatcher("/s08/newEntity.jsp").forward(request, response);
    }
}
