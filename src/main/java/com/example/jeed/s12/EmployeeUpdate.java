package com.example.jeed.s12;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jeed.s05.EmployeePlain;

@SuppressWarnings("serial")
@WebServlet("/s12/employee/update")
public class EmployeeUpdate extends HttpServlet {
    private static final Logger log = LogManager.getLogger(EmployeeUpdate.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();

        String param = request.getParameter("id");
        long id = Long.parseLong(param);
        String firstName = request.getParameter("first");

        EmployeePlain employee = new EmployeePlain(id, firstName == null ? "Jimmy" : firstName, "Gorn", 8467, 1200.0);
        if (new EmployeeDao().update(employee)) {
            log.debug("Employee merged with id " + employee.getId());
            request.setAttribute("employee", employee);
        } else {
            log.info("Can't merge " + employee);
        }

        request.getRequestDispatcher("/employee.jsp").forward(request, response);
    }
}
