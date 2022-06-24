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
@WebServlet("/s14/employee/named")
public class EmployeeByName extends HttpServlet {
    private static final Logger log = LogManager.getLogger(EmployeeByName.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();

        String first = request.getParameter("first");
        String last = request.getParameter("last");

        new EmployeeDao().getByName(first, last).ifPresent(employee -> {
            request.setAttribute("employee", employee);
        });

        request.getRequestDispatcher("/employee.jsp").forward(request, response);
    }
}
