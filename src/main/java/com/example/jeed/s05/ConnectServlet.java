/*
 * Introduction to Jakarta Enterprise Edition - JPA on Hibernate
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.s05;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

/**
 * Check connection on a hibernate native session
 */
@SuppressWarnings("serial")
@WebServlet("/s05/connect")
public class ConnectServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(ConnectServlet.class);
    private static SessionService service;

    @Override
    public void init() throws ServletException {
        service = (SessionService) getServletContext().getAttribute(ContextListener.HRON_SESSION);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();

        try (Session session = service.getSession()) {
            request.setAttribute("connected", session.isConnected());
        }

        request.getRequestDispatcher("connect.jsp").forward(request, response);
    }
}
