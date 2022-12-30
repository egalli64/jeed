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
import com.example.jeed.dao.RegionEager4Country;
import com.example.jeed.dao.RegionEager4CountryDao;

/**
 * Get all entities in a OneToMany relation - always eager!
 * 
 * @see RegionEager4Country the associated JPA entity
 * @see RegionEager4CountryDao the DAO that actually does the job
 */
@SuppressWarnings("serial")
@WebServlet("/s16/regionEager/all")
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
        request.getRequestDispatcher("/s16/regionsCountry.jsp").forward(request, response);
    }
}
