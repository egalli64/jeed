/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.m3.s5;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jeed.dao.ContextListener;
import com.example.jeed.dao.Region4Country;
import com.example.jeed.dao.Region4CountryDao;

/**
 * Get all entities in a OneToMany relation - eager on demand
 * 
 * @see Region4Country the associated JPA entity
 * @see Region4CountryDao the DAO that actually does the job
 */
@SuppressWarnings("serial")
@WebServlet("/m3/s5/region/allEager")
public class RegionGetAllEagerServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(RegionGetAllEagerServlet.class);
    private Region4CountryDao dao;

    @Override
    public void init() throws ServletException {
        dao = (Region4CountryDao) getServletContext().getAttribute(ContextListener.REGION4COUNTRY_DAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();

        request.setAttribute("regions", dao.readAllEager());
        request.getRequestDispatcher("/m3/s5/regionsCountry.jsp").forward(request, response);
    }
}
