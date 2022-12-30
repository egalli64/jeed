/*
 * Introduction to Jakarta Enterprise Edition - JPA on Hibernate
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.s11;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jeed.dao.ContextListener;
import com.example.jeed.dao.RegionDao;

/**
 * Use of EntityManager::find()
 * 
 * @see RegionDao the DAO that actually does the job 
 */
@SuppressWarnings("serial")
@WebServlet("/s11/region/get")
public class RegionGetServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(RegionGetServlet.class);
    private RegionDao dao;

    @Override
    public void init() throws ServletException {
        dao = (RegionDao) getServletContext().getAttribute(ContextListener.REGION_DAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String param = request.getParameter("id");
        log.traceEntry(param);

        dao.read(Integer.valueOf(param)).ifPresentOrElse(region -> request.setAttribute("region", region),
                () -> log.info(String.format("Employee %d not found", param)));

        request.getRequestDispatcher("/region.jsp").forward(request, response);
    }
}
