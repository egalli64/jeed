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
@WebServlet("/s12/employee/create")
public class EmployeeCreate extends HttpServlet {
    private static final Logger log = LogManager.getLogger(EmployeeCreate.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();

        String param = request.getParameter("id");
        long id = Long.parseLong(param);

        EmployeePlain employee = new EmployeePlain(id, "Jim", "Korn", 9119, 1000.0);
        if (new EmployeeDao().create(employee)) {
            log.debug("Employee persists with id " + employee.getId());
            request.setAttribute("employee", employee);
        } else {
            log.info("Can't create " + employee);
        }

        request.getRequestDispatcher("/employee.jsp").forward(request, response);
    }
}
