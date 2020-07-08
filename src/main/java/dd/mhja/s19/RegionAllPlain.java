package dd.mhja.s19;

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

@WebServlet("/s19/region/all/plain")
public class RegionAllPlain extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(RegionAllPlain.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOG.trace("enter");

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        try (PrintWriter writer = response.getWriter()) {
            RegionDao dao = new RegionDao();
            List<Region> regions = dao.readAll();
            regions.forEach(r -> writer.println(r.getId() + ": " + r.getName()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
