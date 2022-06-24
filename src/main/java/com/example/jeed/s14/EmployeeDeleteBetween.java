package com.example.jeed.s14;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("serial")
@WebServlet("/s14/employee/deleteBetween")
public class EmployeeDeleteBetween extends HttpServlet {
    private static final Logger log = LogManager.getLogger(EmployeeDeleteBetween.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();

        long low = Long.parseLong(request.getParameter("low"));
        long high = Long.parseLong(request.getParameter("high"));
        int result = new EmployeeDao().deleteBetween(low, high);
        log.debug(String.format("Deleted %d elements from coders", result));

        request.getRequestDispatcher("/index.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
