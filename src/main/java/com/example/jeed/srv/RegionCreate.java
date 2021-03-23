package com.example.jeed.srv;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jeed.dao.Region;
import com.example.jeed.dao.RegionDao;

@WebServlet("/ex/reg/new")
public class RegionCreate extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(RegionCreate.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");

        log.trace("enter, name: " + name);

        if (name == null || name.isBlank()) {
            request.setAttribute("error", "Can't create a new region with no name");
        } else if (!new RegionDao().create(new Region(name))) {
            request.setAttribute("error", "Can't create a region named " + name);
        }

        request.getRequestDispatcher("all").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
