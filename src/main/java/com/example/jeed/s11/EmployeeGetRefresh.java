package com.example.jeed.s11;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("serial")
@WebServlet("/s11/employee/refresh/get")
public class EmployeeGetRefresh extends HttpServlet {
    private static final Logger log = LogManager.getLogger(EmployeeGetRefresh.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();

        String param = request.getParameter("id");
        long id = Long.parseLong(param);

        new EmployeeDao().readRefresh(id).ifPresentOrElse(employee -> {
            log.debug("Found employee " + id);
            request.setAttribute("employee", employee);
        }, () -> {
            log.info(String.format("Employee %d not found", id));
        });

        request.getRequestDispatcher("/employee.jsp").forward(request, response);
    }
}
