package com.example.jed.s12;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jed.s06.CoderPlain;

@WebServlet("/s12/coder/proxy/get")
public class CoderGetProxy extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CoderGetProxy.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.trace("enter");

        String param = request.getParameter("id");
        long id = Long.parseLong(param);

        Optional<CoderPlain> opt = new CoderDao().readProxy(id);
        if (opt.isPresent()) {
            log.debug("Found coder " + id);
            request.setAttribute("coder", opt.get());
        } else {
            log.info(String.format("Coder %d not found", id));
        }

        request.getRequestDispatcher("/coder.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
