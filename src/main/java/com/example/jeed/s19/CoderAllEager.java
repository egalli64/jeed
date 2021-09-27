package com.example.jeed.s19;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("serial")
@WebServlet("/s19/coders/eager")
public class CoderAllEager extends HttpServlet {
    private static final Logger log = LogManager.getLogger(CoderAllEager.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.trace("enter");

        CoderDao dao = new CoderDao();
        List<CoderMToM> coders = dao.readAllEager();
        request.setAttribute("coders", coders);
        request.getRequestDispatcher("/codersTeams.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
