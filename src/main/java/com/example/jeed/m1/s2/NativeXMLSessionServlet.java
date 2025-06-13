/*
 * Introduction to Hibernate - JEE ORM
 * 
 * https://github.com/egalli64/jeed
 */
package com.example.jeed.m1.s2;

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
 * Check connection on a Hibernate native session
 */
@SuppressWarnings("serial")
@WebServlet("/m1/s2/session")
public class NativeXMLSessionServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(NativeXMLSessionServlet.class);
    private static NativeXMLSessionService service;

    @Override
    public void init() throws ServletException {
        service = (NativeXMLSessionService) getServletContext().getAttribute(NativeXMLContextListener.NATIVE_XML_SESSION);
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
