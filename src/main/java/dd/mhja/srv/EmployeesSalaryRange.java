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

@WebServlet("/employee/salary/range")
public class EmployeesSalaryRange extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(EmployeesSalaryRange.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOG.trace("enter");

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        String param = request.getParameter("low");
        Double low = null;
        try {
            low = Double.valueOf(param);
        } catch (Exception ex) {
            LOG.error("Can't serve request for low " + param);
        }

        param = request.getParameter("high");
        Double high = null;
        try {
            high = Double.valueOf(param);
        } catch (Exception nfe) {
            LOG.error("Can't serve request for high " + param);
        }
        
        try (PrintWriter writer = response.getWriter()) {
            if (low == null || high == null) {
                writer.println("please provide high and low for salary range");
                return;
            }

            EmployeeDao dao = new EmployeeDao();
            writer.println(dao.readSalaryRange(low, high));
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
