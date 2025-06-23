/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.m2.s3;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jeed.dao.legacy.Country;
import com.example.jeed.dao.legacy.CountryDao;
import com.example.jeed.listener.ContextListener;

/**
 * Create entity by EntityManager::persist() with id passed by caller
 * 
 * @see Country entity with plain id
 * @see CountryDao the DAO that actually does the job
 */
@SuppressWarnings("serial")
@WebServlet("/m2/s3/country/create")
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

        request.getRequestDispatcher("/m2/s3/newEntity.jsp").forward(request, response);
    }
}
