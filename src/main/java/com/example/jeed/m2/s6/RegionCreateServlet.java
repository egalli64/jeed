/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.m2.s6;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jeed.dao.legacy.Region;
import com.example.jeed.dao.legacy.RegionDao;
import com.example.jeed.listener.ContextListener;

/**
 * Use of EntityManager::persist()
 * 
 * @see RegionDao the DAO that actually does the job
 */
@SuppressWarnings("serial")
@WebServlet("/m2/s6/region/create")
public class RegionCreateServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(RegionCreateServlet.class);
    private RegionDao dao;

    @Override
    public void init() throws ServletException {
        dao = (RegionDao) getServletContext().getAttribute(ContextListener.REGION_DAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        log.traceEntry(name);

        Region region = new Region(name);
        if (dao.create(region)) {
            request.setAttribute("region", region);
        } else {
            log.debug("Can't create region {}", name);
        }

        request.getRequestDispatcher("/region.jsp").forward(request, response);
    }
}
