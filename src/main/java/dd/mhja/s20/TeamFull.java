package dd.mhja.s20;

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

@WebServlet("/s20/team/full")
public class TeamFull extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(TeamFull.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOG.trace("enter");

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        try (PrintWriter writer = response.getWriter()) {
            TeamDao dao = new TeamDao();
            Optional<Team> opt = dao.readEager(1);
            if(opt.isPresent()) {
                Team team = opt.get();
                writer.println(team.getName());
                for(Coder coder: team.getCoders()) {
                    writer.println(coder);
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
