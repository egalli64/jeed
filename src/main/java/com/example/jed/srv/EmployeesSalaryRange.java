package com.example.jed.srv;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jed.dao.Employee;
import com.example.jed.dao.EmployeeDao;

@WebServlet("/ex/emp/salaryRange")
public class EmployeesSalaryRange extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(EmployeesSalaryRange.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String paramLow = request.getParameter("low");
        Double low = null;
        try {
            low = Double.valueOf(paramLow);
        } catch (Exception ex) {
            log.error("Can't serve request for low " + paramLow);
        }

        String paramHigh = request.getParameter("high");
        Double high = null;
        try {
            high = Double.valueOf(paramHigh);
        } catch (Exception nfe) {
            log.error("Can't serve request for high " + paramHigh);
        }

        if (low == null || high == null) {
            request.setAttribute("error", String.format("Can't get salary in range [%s, %s]", paramLow, paramHigh));
        } else {
            log.trace(String.format("range [%.2f .. %.2f]", low, high));

            EmployeeDao dao = new EmployeeDao();
            List<Employee> employees = dao.readSalaryRange(low, high);
            request.setAttribute("employees", employees);
        }

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
