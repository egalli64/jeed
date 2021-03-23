package com.example.jeed.srv;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jeed.dao.Employee;
import com.example.jeed.dao.EmployeeDao;

@WebServlet("/ex/emp/salaryTop")
public class EmployeesSalaryTop extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(EmployeesSalaryTop.class);

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
