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

@WebServlet("/employee/salary/top")
public class EmployeesSalaryTop extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(EmployeesSalaryTop.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.trace("enter");

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        String param = request.getParameter("low");
        Double low = null;
        try {
            low = Double.valueOf(param);
        } catch (Exception ex) {
            log.error("Can't serve request for low " + param);
        }
        
        try (PrintWriter writer = response.getWriter()) {
            if (low == null) {
                writer.println("please provide low for salary");
                return;
            }

            EmployeeDao dao = new EmployeeDao();
            writer.println(dao.readSalaryTop(low));
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
