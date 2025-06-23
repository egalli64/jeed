/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.m3.s4;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jeed.dao.legacy.Car;
import com.example.jeed.dao.legacy.CarDao;
import com.example.jeed.listener.ContextListener;

/**
 * Get entities using OneToOne JPA annotation
 * 
 * @see Car the associated JPA entity
 * @see CarDao the DAO that actually does the job
 */
@SuppressWarnings("serial")
@WebServlet("/m3/s4/car/all")
public class CarGetAllServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(CarGetAllServlet.class);
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
