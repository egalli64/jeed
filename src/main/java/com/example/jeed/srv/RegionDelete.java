package com.example.jeed.srv;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jeed.dao.RegionDao;

@SuppressWarnings("serial")
@WebServlet("/ex/reg/del")
public class RegionDelete extends HttpServlet {
    private static final Logger log = LogManager.getLogger(RegionDelete.class);

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
            request.setAttribute("error", String.format("Can't delete region with id \"%s\"", param));
        } else if (!new RegionDao().delete(id)) {
            request.setAttribute("error", "Can't delete region " + id);
        }

        request.getRequestDispatcher("all").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
