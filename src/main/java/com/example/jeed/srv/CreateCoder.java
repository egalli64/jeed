package com.example.jeed.srv;

import java.io.IOException;
import java.io.PrintWriter;

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
@WebServlet("/coder/create")
public class CreateCoder extends HttpServlet {
    private static final Logger log = LogManager.getLogger(CreateCoder.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        try (PrintWriter writer = response.getWriter()) {
            String first = request.getParameter("first");
            String last = request.getParameter("last");
            String param = request.getParameter("phone");
            int phone = param == null || param.isBlank() ? 0 : Integer.parseInt(param);
            if (first == null || first.isBlank() || last == null || last.isBlank() || phone < 1) {
                writer.println("please provide first, last and phone for the coder");
                return;
            }

            Coder coder = new Coder(first, last, phone, 3_000);

            if (new CoderDao().create(coder)) {
                writer.println("new coder created: " + coder);
            } else {
                writer.println("can't create coder: " + coder);
            }
        }
    }
}
