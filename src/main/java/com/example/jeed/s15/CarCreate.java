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
@WebServlet("/s15/car/create")
public class CarCreate extends HttpServlet {
    private static final Logger log = LogManager.getLogger(CarCreate.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();

        CarGV car = new CarGV("Spanish Fandango");
        if (new CarDao().create(car)) {
            log.debug("Car persists with id " + car.getId());
        } else {
            log.info("Can't create " + car);
        }

        request.getRequestDispatcher("/index.html").forward(request, response);
    }
}
