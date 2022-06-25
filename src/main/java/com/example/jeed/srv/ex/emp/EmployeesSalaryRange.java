package com.example.jeed.srv.ex.emp;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jeed.dao.Employee;
import com.example.jeed.dao.EmployeeDao;

@SuppressWarnings("serial")
@WebServlet("/ex/emp/salaryRange")
public class EmployeesSalaryRange extends HttpServlet {
    private static final Logger log = LogManager.getLogger(EmployeesSalaryRange.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        double low = -1;
        try {
            low = Double.parseDouble(request.getParameter("low"));
        } catch (Exception ex) {
            log.error("Bad low parameter");
        }

        double high = -1;
        try {
            high = Double.parseDouble(request.getParameter("high"));
        } catch (Exception nfe) {
            log.error("Bad high parameter");
        }

        if (low < 0 || high < 0) {
            request.setAttribute("error", "Please, specify an acceptable range");
        } else {
            log.trace(String.format("range [%.2f .. %.2f]", low, high));

            List<Employee> employees = new EmployeeDao().readSalaryRange(low, high);
            request.setAttribute("employees", employees);
        }

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
