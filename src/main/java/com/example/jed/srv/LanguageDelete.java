package com.example.jed.srv;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jed.dao.LanguageDao;

@WebServlet("/ex/lang/del")
public class LanguageDelete extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(LanguageDelete.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String param = request.getParameter("id");
        log.trace("enter, id: " + param);

        Integer id = null;
        try {
            id = Integer.valueOf(param);
        } catch (NumberFormatException nfe) {
            log.error("Can't convert id to int - " + nfe.getMessage());
        }

        if (id == null) {
            request.setAttribute("error", String.format("Can't delete language with id \"%s\"", param));
        } else if (!new LanguageDao().delete(id)) {
            request.setAttribute("error", "Can't delete language " + id);
        }

        request.getRequestDispatcher("all").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
