package com.example.jeed.s18;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("serial")
@WebServlet("/s18/region/country/all")
public class RegionCountryAll extends HttpServlet {
    private static final Logger log = LogManager.getLogger(RegionCountryAll.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();
        List<Region1ToM> regions = new RegionDao().readAllEager();
        request.setAttribute("regions", regions);

        request.getRequestDispatcher("/s18/regionsCountries.jsp").forward(request, response);
    }
}
