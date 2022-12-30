/*
 * Introduction to Jakarta Enterprise Edition - JPA on Hibernate
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.s16;

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
 * Get all entities in a ManyToOne relation
 * 
 * @see Country the associated JPA entity
 * @see CountryDao the DAO that actually does the job
 */
@SuppressWarnings("serial")
@WebServlet("/s16/country/all")
public class CountryGetAllServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(CountryGetAllServlet.class);
    private CountryDao dao;

    @Override
    public void init() throws ServletException {
        dao = (CountryDao) getServletContext().getAttribute(ContextListener.COUNTRY_DAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();

        request.setAttribute("countries", dao.readAll());
        request.getRequestDispatcher("/s16/countriesRegion.jsp").forward(request, response);
    }
}
