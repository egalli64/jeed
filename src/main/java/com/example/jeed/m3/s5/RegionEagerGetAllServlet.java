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

import com.example.jeed.dao.legacy.RegionEager4Country;
import com.example.jeed.dao.legacy.RegionEager4CountryDao;
import com.example.jeed.listener.ContextListener;

/**
 * Get all entities in a OneToMany relation - always eager!
 * 
 * @see RegionEager4Country the associated JPA entity
 * @see RegionEager4CountryDao the DAO that actually does the job
 */
@SuppressWarnings("serial")
@WebServlet("/m3/s5/regionEager/all")
public class RegionEagerGetAllServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(RegionEagerGetAllServlet.class);
    private RegionEager4CountryDao dao;

    @Override
    public void init() throws ServletException {
        dao = (RegionEager4CountryDao) getServletContext().getAttribute(ContextListener.REGION_EAGER4COUNTRY_DAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();

        request.setAttribute("regions", dao.readAll());
        request.getRequestDispatcher("/m3/s5/regionsCountry.jsp").forward(request, response);
    }
}
