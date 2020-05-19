package dd.mhja.srv;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dd.mhja.dao.Region;
import dd.mhja.dao.RegionDao;

@WebServlet("/hello")
public class Hello extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(Hello.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOG.trace("enter");

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        try (PrintWriter writer = response.getWriter()) {
            RegionDao dao = new RegionDao();
            writer.println("regions: " + dao.readAll());

            Region region = new Region("Another region");
            if (dao.create(region)) {
                List<Region> regions = dao.readAll();
                writer.println("another region: " + regions);
                regions.stream().map(Region::getId).filter(pk -> pk > 4).forEach(dao::delete);
            } else {
                writer.println("can't create more regions");
            }
            writer.println("regions: " + dao.readAll());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
