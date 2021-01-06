package com.example.jed.srv;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jed.dao.Region;
import com.example.jed.dao.RegionDao;

@WebServlet("/ex/reg/edit")
public class RegionEdit extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(RegionEdit.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String param = request.getParameter("id");
        String name = request.getParameter("name");

        log.trace(String.format("enter, id %s name %s: ", param, name));

        Integer id = null;
        try {
            id = Integer.valueOf(param);
        } catch (NumberFormatException nfe) {
            log.error("Can't convert id to int - " + nfe.getMessage());
        }

        if (id == null) {
            request.setAttribute("error", "Can't edit region id " + param);
        } else if (name == null || name.isBlank()) {
            request.setAttribute("error", "Can't set region name to " + name);
        } else if (!new RegionDao().update(new Region(id, name))) {
            request.setAttribute("error", "Can't create a region named " + name);
        }

        request.getRequestDispatcher("all").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
