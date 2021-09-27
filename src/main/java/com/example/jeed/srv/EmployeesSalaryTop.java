package com.example.jeed.srv;

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
@WebServlet("/ex/emp/salaryTop")
public class EmployeesSalaryTop extends HttpServlet {
    private static final Logger log = LogManager.getLogger(EmployeesSalaryTop.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String paramLow = request.getParameter("low");
        Double low = null;
        try {
            low = Double.valueOf(paramLow);
        } catch (Exception ex) {
            log.error("Can't serve request for low " + paramLow);
        }

        if (low == null) {
            request.setAttribute("error", String.format("Can't get salary from _%s_ on", paramLow));
        } else {
            log.trace("from " + low);

            EmployeeDao dao = new EmployeeDao();
            List<Employee> employees = dao.readSalaryTop(low);
            request.setAttribute("employees", employees);
        }

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
