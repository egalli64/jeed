package com.example.jeed.s08;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jeed.s05.CoderPlain;

@SuppressWarnings("serial")
@WebServlet("/s08/coder/all")
public class CoderAll extends HttpServlet {
    private static final Logger log = LogManager.getLogger(CoderAll.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.trace("enter");

        List<CoderPlain> coders = new CoderDao().getAll();
        request.setAttribute("coders", coders);
        log.debug(String.format("Found %d coders", coders.size()));

        request.getRequestDispatcher("/coders.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
