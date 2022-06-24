package com.example.jeed.s14;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jeed.s05.EmployeePlain;

@SuppressWarnings("serial")
@WebServlet("/s14/employee/salary/top")
public class EmployeeTopSalary extends HttpServlet {
    private static final Logger log = LogManager.getLogger(EmployeeTopSalary.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();

        String param = request.getParameter("lowest");
        double lowest = Double.parseDouble(param);
        List<EmployeePlain> employees = new EmployeeDao().getPayedMoreThan(lowest);
        request.setAttribute("employees", employees);
        log.debug(String.format("Found %d employees", employees.size()));

        request.getRequestDispatcher("/employees.jsp").forward(request, response);
    }
}
