package com.example.jeed.s12;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jeed.s05.CoderPlain;

@SuppressWarnings("serial")
@WebServlet("/s12/coder/create")
public class CoderCreate extends HttpServlet {
    private static final Logger log = LogManager.getLogger(CoderCreate.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.trace("enter");

        String param = request.getParameter("id");
        long id = Long.parseLong(param);

        CoderPlain coder = new CoderPlain(id, "Jim", "Korn", 1000.0);
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
