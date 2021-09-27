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
@WebServlet("/s12/coder/update")
public class CoderUpdate extends HttpServlet {
    private static final Logger log = LogManager.getLogger(CoderUpdate.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.trace("enter");

        String param = request.getParameter("id");
        long id = Long.parseLong(param);
        String firstName = request.getParameter("first");

        CoderPlain coder = new CoderPlain(id, firstName == null ? "Jimmy" : firstName, "Gorn", 1200.0);
        if (new CoderDao().update(coder)) {
            log.debug("Coder merged with id " + coder.getId());
            request.setAttribute("coder", coder);
        } else {
            log.info("Can't merge " + coder);
        }

        request.getRequestDispatcher("/coder.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
