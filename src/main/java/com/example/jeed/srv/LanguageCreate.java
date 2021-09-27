package com.example.jeed.srv;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jeed.dao.Language;
import com.example.jeed.dao.LanguageDao;

@SuppressWarnings("serial")
@WebServlet("/ex/lang/new")
public class LanguageCreate extends HttpServlet {
    private static final Logger log = LogManager.getLogger(LanguageCreate.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");

        log.trace("enter, name: " + name);

        if (name == null || name.isBlank()) {
            request.setAttribute("error", "Can't create a new language with no name");
        } else if (!new LanguageDao().create(new Language(name))) {
            request.setAttribute("error", "Can't create a language named " + name);
        }

        request.getRequestDispatcher("all").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
