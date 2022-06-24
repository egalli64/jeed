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
@WebServlet("/s15/employee/createBad")
public class EmployeeCreateBad extends HttpServlet {
    private static final Logger log = LogManager.getLogger(EmployeeCreateBad.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();

        String param = request.getParameter("id");
        long id = Long.parseLong(param);

        EmployeeGV employee = new EmployeeGV("Buck", "Roger", 2332, 1300.0);

        // !! ERROR, when id is a GeneratedValue, it should not be set !!
        employee.setId(id);
        if (new EmployeeDao().create(employee)) {
            log.error("Employee with id " + employee.getId() + " NOT expected to persist!");
            request.setAttribute("employee", employee);
        } else {
            log.info("Can't create " + employee);
        }

        request.getRequestDispatcher("/employee.jsp").forward(request, response);
    }
}
