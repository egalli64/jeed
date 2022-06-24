package com.example.jeed.s19;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;

@SuppressWarnings("serial")
@WebServlet("/s19/service/all")
public class ServiceAll extends HttpServlet {
    private static final Logger log = LogManager.getLogger(ServiceAll.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();

        List<ServiceMToM> services = new ServiceDao().readAllLazy();
        proxyCheckDemo(services);

        request.setAttribute("services", services);
        request.getRequestDispatcher("/servicesMinimal.jsp").forward(request, response);
    }

    private void proxyCheckDemo(List<ServiceMToM> services) {
        if (!services.isEmpty()) {
            ServiceMToM service = services.get(0);
            if (Hibernate.isInitialized(service.getCars())) {
                log.warn("Unexpected");
            } else {
                log.trace("As expected, we have an unusable proxy in cars");
            }
        }
    }
}
