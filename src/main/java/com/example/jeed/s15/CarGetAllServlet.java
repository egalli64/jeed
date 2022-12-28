/*
 * Introduction to Jakarta Enterprise Edition - JPA on Hibernate
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.s15;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jeed.dao.Car;
import com.example.jeed.dao.CarDao;
import com.example.jeed.dao.ContextListener;

/**
 * Get entities using OneToOne JPA annotation
 * 
 * @see Car the associated JPA entity
 * @see CarDao the DAO that actually does the job
 */
@SuppressWarnings("serial")
@WebServlet("/s15/car/get/all")
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
        request.getRequestDispatcher("/cars.jsp").forward(request, response);
    }
}
