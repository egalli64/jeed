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
@WebServlet("/ex/emp/salaryTop")
public class EmployeesSalaryTop extends HttpServlet {
    private static final Logger log = LogManager.getLogger(EmployeesSalaryTop.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        double low = -1;
        try {
            low = Double.valueOf(request.getParameter("low"));
        } catch (Exception ex) {
            log.error("Bad low parameter");
        }

        if (low < 0) {
            request.setAttribute("error", "Please, specify an acceptable lower limit");
        } else {
            log.trace("from " + low);

            List<Employee> employees = new EmployeeDao().readSalaryTop(low);
            request.setAttribute("employees", employees);
        }

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
