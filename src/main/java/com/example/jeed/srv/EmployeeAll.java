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
@WebServlet("/ex/emp/all")
public class EmployeeAll extends HttpServlet {
    private static final Logger log = LogManager.getLogger(EmployeeAll.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.trace("enter");

        EmployeeDao dao = new EmployeeDao();
        List<Employee> employees = dao.readAllOrderBySalary(false);
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
