package dd.mhja.srv;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dd.mhja.dao.Region;
import dd.mhja.dao.RegionDao;

@WebServlet("/region/get")
public class FindRegion extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(FindRegion.class);

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

        Optional<Region> opt = Optional.empty();
//        Optional<Region> opt = id == null ? Optional.empty() : new RegionDao().read(id);

        try (PrintWriter writer = response.getWriter()) {
            try {
                if (id != null) {
                    opt = new RegionDao().read(id);
                }
            } catch (Throwable th) {
                writer.println("Can't get data from database");
                throw th;
            }

            if (opt.isPresent()) {
                writer.println("region found: " + opt.get());
            } else {
                writer.println("can't find region: " + id);
            }
        } catch (Throwable th) {
            log.error("!!!");
            throw th;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
