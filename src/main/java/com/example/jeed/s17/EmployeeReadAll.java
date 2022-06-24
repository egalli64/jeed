package com.example.jeed.s17;

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
@WebServlet("/s17/employee/all")
public class EmployeeReadAll extends HttpServlet {
    private static final Logger log = LogManager.getLogger(EmployeeReadAll.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();

        List<Employee1To1> employees = new EmployeeDao().readAll();
        log.debug(employees.size() + " employees read");
        request.setAttribute("employees", employees);

        request.getRequestDispatcher("/employeesCar.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
