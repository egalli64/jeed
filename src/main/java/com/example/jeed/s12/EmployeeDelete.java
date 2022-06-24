package com.example.jeed.s12;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("serial")
@WebServlet("/s12/employee/delete")
public class EmployeeDelete extends HttpServlet {
    private static final Logger log = LogManager.getLogger(EmployeeDelete.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();

        String param = request.getParameter("id");
        long id = Long.parseLong(param);

        if (new EmployeeDao().delete(id)) {
            log.debug(String.format("Employee with id %d deleted", id));
            request.getRequestDispatcher("/").forward(request, response);
        } else {
            log.info("Can't delete employee " + id);
            request.getRequestDispatcher("/employee.jsp").forward(request, response);
        }
    }
}
