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
@WebServlet("/s15/language/createBad")
public class LanguageCreateBad extends HttpServlet {
    private static final Logger log = LogManager.getLogger(LanguageCreateBad.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.trace("enter");

        String param = request.getParameter("id");
        long id = Long.parseLong(param);

        LanguageGV language = new LanguageGV("Greek");

        // !! ERROR, when id is a GeneratedValue, it should not be set !!
        language.setId(id);
        if (new LanguageDao().create(language)) {
            log.debug("Language persisted with id " + language.getId());
        } else {
            log.info("Can't create " + language);
        }

        request.getRequestDispatcher("/index.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
