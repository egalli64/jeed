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
@WebServlet("/s18/country/all")
public class CountryAll extends HttpServlet {
    private static final Logger log = LogManager.getLogger(CountryAll.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();

        List<CountryMTo1> countries = new CountryDao().readAll();
        log.debug(countries.size() + " countries read");

        request.setAttribute("countries", countries);
        request.getRequestDispatcher("/s18/countriesRegion.jsp").forward(request, response);
    }
}
