package com.example.jed.s14;

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

@WebServlet("/s14/coder/named")
public class CoderByName extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CoderByName.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.trace("enter");

        String first = request.getParameter("first");
        String last = request.getParameter("last");

        Optional<CoderPlain> coder = new CoderDao().getByName(first, last);
        if (coder.isPresent()) {
            request.setAttribute("coder", coder.get());
        }
        request.getRequestDispatcher("/coder.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
