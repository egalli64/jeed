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

import com.example.jeed.dao.Car;
import com.example.jeed.dao.CarDao;
import com.example.jeed.dao.ContextListener;

/**
 * Get a single entity using OneToOne JPA annotation
 * 
 * @see Car the associated JPA entity 
 * @see CarDao the DAO that actually does the job 
 */
@SuppressWarnings("serial")
@WebServlet("/m3/s4/car/get")
public class CarGetServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(CarGetServlet.class);
    private CarDao dao;

    @Override
    public void init() throws ServletException {
        dao = (CarDao) getServletContext().getAttribute(ContextListener.CAR_DAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String param = request.getParameter("id");
        log.traceEntry(param);

        dao.read(Integer.valueOf(param)).ifPresentOrElse(car -> request.setAttribute("car", car),
                () -> log.info(String.format("Car %d not found", param)));

        request.getRequestDispatcher("/m3/s4/carEmployee.jsp").forward(request, response);
    }
}
