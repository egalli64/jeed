package com.example.jeed.s15;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("serial")
@WebServlet("/s15/coder/createBad")
public class CoderCreateBad extends HttpServlet {
    private static final Logger log = LogManager.getLogger(CoderCreateBad.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.trace("enter");

        String param = request.getParameter("id");
        long id = Long.parseLong(param);

        CoderGV coder = new CoderGV("Buck", "Roger", 1300.0);

        // !! ERROR, when id is a GeneratedValue, it should not be set !!
        coder.setId(id);
        if (new CoderDao().create(coder)) {
            log.debug("Coder persisted with id " + coder.getId());
            request.setAttribute("coder", coder);
        } else {
            log.info("Can't create " + coder);
        }

        request.getRequestDispatcher("/coder.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
