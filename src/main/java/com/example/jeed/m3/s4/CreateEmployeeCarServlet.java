/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.m3.s4;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jeed.dao.CarDao;
import com.example.jeed.listener.ContextListener;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Create an Employee and its associated Car
 */
@SuppressWarnings("serial")
@WebServlet("/m3/s4/createEmployeeCar")
public class CreateEmployeeCarServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(CreateEmployeeCarServlet.class);
    private CarDao dao;

    @Override
    public void init() throws ServletException {
        dao = (CarDao) getServletContext().getAttribute(ContextListener.CAR_DAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();

        request.setAttribute("cars", dao.readAll());
        request.getRequestDispatcher("/m3/s4/carsEmployee.jsp").forward(request, response);
    }
}
