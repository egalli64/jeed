package com.example.jeed.s19;

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
@WebServlet("/s19/car/all/eager")
public class CarAllEager extends HttpServlet {
    private static final Logger log = LogManager.getLogger(CarAllEager.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();

        List<CarMToM> cars = new CarDao().readAllEager();
        request.setAttribute("cars", cars);
        request.getRequestDispatcher("/s19/carsServices.jsp").forward(request, response);
    }
}
