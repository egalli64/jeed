/*
 * Introduction to Jakarta Enterprise Edition - JPA on Hibernate
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.s05;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

/**
 * Check connection on a hibernate native session
 */
@SuppressWarnings("serial")
@WebServlet("/s05/session")
public class NativeSessionServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(NativeSessionServlet.class);
    private static NativeSessionService service;

    @Override
    public void init() throws ServletException {
        service = (NativeSessionService) getServletContext().getAttribute(ContextListener.NATIVE_SESSION);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();

        if (service.type != InitializationType.DISABLED) {
            try (Session session = service.getSession()) {
                request.setAttribute("connected", session.isConnected());
            }
        }

        request.getRequestDispatcher("connect.jsp").forward(request, response);
    }
}
