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

@WebServlet("/hello")
public class Hello extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(Hello.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.trace("enter");

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        try (PrintWriter writer = response.getWriter()) {
            RegionDao regions = new RegionDao();
            Region region = new Region("X");
            if (regions.create(region)) {
                writer.println("New region: " + region);
                region.setName("Z");
                regions.update(region);
                writer.println("Updated region: " + region);
            }

            EmployeeDao employees = new EmployeeDao();
            writer.println("employees: " + employees.read(100));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
