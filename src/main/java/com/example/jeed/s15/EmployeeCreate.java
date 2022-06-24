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
@WebServlet("/s15/employee/create")
public class EmployeeCreate extends HttpServlet {
    private static final Logger log = LogManager.getLogger(EmployeeCreate.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();

        EmployeeGV employee = new EmployeeGV("Frank", "Fripp", 8593, 1500.0);
        if (new EmployeeDao().create(employee)) {
            log.debug("Employee persisted with id " + employee.getId());
            request.setAttribute("employee", employee);
        } else {
            log.info("Can't create " + employee);
        }

        request.getRequestDispatcher("/employee.jsp").forward(request, response);
    }
}
