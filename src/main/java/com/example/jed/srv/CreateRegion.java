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

import com.example.jed.dao.Region;
import com.example.jed.dao.RegionDao;

@WebServlet("/region/new")
public class CreateRegion extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CreateRegion.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.trace("enter");
        
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        
        try (PrintWriter writer = response.getWriter()) {
            String name = request.getParameter("name");
            if(name == null || name.isBlank()) {
                writer.println("please provide a name for the region");
                return;
            }

            RegionDao regions = new RegionDao();
            Region region = new Region(name);
    
            if(regions.create(region)) {
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
