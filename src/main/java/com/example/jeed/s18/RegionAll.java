package com.example.jeed.s18;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;

@SuppressWarnings("serial")
@WebServlet("/s18/regions")
public class RegionAll extends HttpServlet {
    private static final Logger log = LogManager.getLogger(RegionAll.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.trace("enter");

        RegionDao dao = new RegionDao();
        List<Region1ToM> regions = dao.readAllLazy();

        proxyCheckDemo(regions);

        request.setAttribute("regions", regions);
        request.getRequestDispatcher("/regions.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void proxyCheckDemo(List<Region1ToM> regions) {
        if (!regions.isEmpty()) {
            Region1ToM region = regions.get(0);
            Set<CountryMTo1> countries = region.getCountries();
            if (Hibernate.isInitialized(countries)) {
                log.warn("Unexpected");
            } else {
                log.trace("As expected, we have an unusable proxy in countries");
            }
        }
    }
}