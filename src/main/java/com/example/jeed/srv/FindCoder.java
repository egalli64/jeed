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

import com.example.jeed.dao.CoderDao;

@SuppressWarnings("serial")
@WebServlet("/coder/get")
public class FindCoder extends HttpServlet {
    private static final Logger log = LogManager.getLogger(FindCoder.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.trace("enter");

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        String param = request.getParameter("id");
        try (PrintWriter writer = response.getWriter()) {
            Integer id = Integer.valueOf(param);
            if (id == null) {
                writer.println("please provide an id for the region");
                return;
            }

            new CoderDao().read(id).ifPresentOrElse(coder -> writer.println("Coder found: " + coder),
                    () -> writer.println("Can't find coder: " + id));
        } catch (Exception ex) {
            log.error("Can't serve request for id " + param);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
