package com.example.jed.s13;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/s13/coder/delete")
public class CoderDelete extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CoderDelete.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.trace("enter");

        String param = request.getParameter("id");
        long id = Long.parseLong(param);

        if (new CoderDao().delete(id)) {
            log.debug(String.format("Coder with id %d deleted", id));
            request.getRequestDispatcher("/").forward(request, response);
        } else {
            log.info("Can't delete coder " + id);
            request.getRequestDispatcher("/coder.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
