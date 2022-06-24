package com.example.jeed.s15;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("serial")
@WebServlet("/s15/car/createBad")
public class CarCreateBad extends HttpServlet {
    private static final Logger log = LogManager.getLogger(CarCreateBad.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();

        int id = Integer.parseInt(request.getParameter("id"));
        CarGV car = new CarGV("Tzatziki");

        // !! ERROR, when id is a GeneratedValue, it should not be set !!
        car.setId(id);
        if (new CarDao().create(car)) {
            log.error("This car is not exptected to persist with id " + car.getId());
        } else {
            log.info("Can't create " + car);
        }

        request.getRequestDispatcher("/index.html").forward(request, response);
    }
}
