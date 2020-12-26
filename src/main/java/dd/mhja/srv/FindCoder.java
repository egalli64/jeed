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

import dd.mhja.dao.Coder;
import dd.mhja.dao.CoderDao;

@WebServlet("/coder/get")
public class FindCoder extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(FindCoder.class);

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
            if (id == null) {
                writer.println("please provide an id for the region");
                return;
            }

            CoderDao dao = new CoderDao();

            Optional<Coder> opt = dao.read(id);
            if (opt.isPresent()) {
                writer.println("Coder found: " + opt.get());
            } else {
                writer.println("Can't find coder: " + id);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
