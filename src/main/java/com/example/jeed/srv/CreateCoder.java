package com.example.jeed.srv;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jeed.dao.Coder;
import com.example.jeed.dao.CoderDao;

@SuppressWarnings("serial")
@WebServlet("/coder/new")
public class CreateCoder extends HttpServlet {
    private static final Logger log = LogManager.getLogger(CreateCoder.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.trace("enter");

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        try (PrintWriter writer = response.getWriter()) {
            String first = request.getParameter("first");
            String last = request.getParameter("last");
            if (first == null || first.isBlank() || last == null || last.isBlank()) {
                writer.println("please provide a name for the coder");
                return;
            }

            CoderDao dao = new CoderDao();
            Coder coder = new Coder();
            coder.setFirstName(first);
            coder.setLastName(last);
            coder.setHireDate(LocalDate.now());
            coder.setSalary(0.0);

            if (dao.create(coder)) {
                writer.println("new coder created: " + coder);
            } else {
                writer.println("can't create coder: " + coder);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
