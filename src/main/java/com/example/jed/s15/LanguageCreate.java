package com.example.jed.s15;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/s15/language/create")
public class LanguageCreate extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(LanguageCreate.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.trace("enter");

        LanguageGV language = new LanguageGV("Spanish");
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
