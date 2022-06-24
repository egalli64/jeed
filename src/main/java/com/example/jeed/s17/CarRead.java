package com.example.jeed.s17;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("serial")
@WebServlet("/s17/car/read")
public class CarRead extends HttpServlet {
    private static final Logger log = LogManager.getLogger(CarRead.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();

        int id = Integer.parseInt(request.getParameter("id"));
        new CarDao().read(id).ifPresent(car -> request.setAttribute("car", car));

        request.getRequestDispatcher("/s17/carEmployee.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
