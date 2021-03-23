package com.example.jeed.srv;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jeed.dao.Region;
import com.example.jeed.dao.RegionDao;

@WebServlet("/region/save")
public class SaveRegion extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SaveRegion.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.trace("enter");

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        String param = request.getParameter("id");
        Integer id = null;
        try {
            id = Integer.valueOf(param);
        } catch (NumberFormatException nfe) {
            log.error("Can't serve request for id " + param);
        }

        try (PrintWriter writer = response.getWriter()) {
            String name = request.getParameter("name");
            if (name == null || name.isBlank() || id == null) {
                writer.println("please provide an id and a name for the region");
                return;
            }

            RegionDao regions = new RegionDao();
            Region region = new Region(name);
            region.setId(id);

            if (regions.update(region)) {
                writer.println("region updated: " + region);
            } else {
                writer.println("can't update region: " + region);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
