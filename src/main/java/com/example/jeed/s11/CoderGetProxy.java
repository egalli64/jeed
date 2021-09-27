package com.example.jeed.s11;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("serial")
@WebServlet("/s11/coder/proxy/get")
public class CoderGetProxy extends HttpServlet {
    private static final Logger log = LogManager.getLogger(CoderGetProxy.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.trace("enter");

        String param = request.getParameter("id");
        long id = Long.parseLong(param);

        new CoderDao().readProxy(id).ifPresentOrElse(coder -> {
            log.debug("Found coder " + id);
            request.setAttribute("coder", coder);
        }, () -> log.info(String.format("Coder %d not found", id)));

        request.getRequestDispatcher("/coder.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
