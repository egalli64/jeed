package com.example.jeed.srv;

import java.io.IOException;
import java.util.List;

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
@WebServlet("/ex/lang/all")
public class LanguageAll extends HttpServlet {
    private static final Logger log = LogManager.getLogger(LanguageAll.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.trace("enter");

        LanguageDao dao = new LanguageDao();
        List<Language> languages = dao.readAllOrderByName();
        request.setAttribute("languages", languages);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
