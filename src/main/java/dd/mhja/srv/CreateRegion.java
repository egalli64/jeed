package dd.mhja.srv;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dd.mhja.dao.EmployeeDao;
import dd.mhja.dao.Region;
import dd.mhja.dao.RegionDao;

@WebServlet("/region/new")
public class CreateRegion extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(CreateRegion.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOG.trace("enter");
        
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
