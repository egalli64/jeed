package com.example.jeed.s06;

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
@WebServlet("/s06/employee/all")
public class EmployeeAll extends HttpServlet {
    private static final Logger log = LogManager.getLogger(EmployeeAll.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.trace("enter");

        List<EmployeePlain> employees = new EmployeeDao().getAll();
        request.setAttribute("employees", employees);
        log.debug(String.format("Found %d employees", employees.size()));

        request.getRequestDispatcher("/employees.jsp").forward(request, response);
    }
}
