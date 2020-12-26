package com.example.jed.srv;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jed.dao.RegionDaoSimple;
import com.example.jed.dao.RegionSimple;

@WebServlet("/simple/region/new")
public class CreateRegionSimple extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CreateRegionSimple.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.trace("enter");

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        try (PrintWriter writer = response.getWriter()) {
            String param = request.getParameter("id");
            Integer id = null;
            try {
                id = Integer.valueOf(param);
            } catch (NumberFormatException nfe) {
                log.error("Can't serve request for id " + param);
            }

            String name = request.getParameter("name");
            if (id == null || name == null || name.isBlank()) {
                writer.println("please provide an id and a name for the region");
                return;
            }

            RegionDaoSimple regions = new RegionDaoSimple();
            RegionSimple region = new RegionSimple(id, name);

            if (regions.create(region)) {
                writer.println("new region created: " + region);
            } else {
                writer.println("can't create region: " + region);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
