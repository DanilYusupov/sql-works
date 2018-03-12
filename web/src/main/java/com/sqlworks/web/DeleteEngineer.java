package com.sqlworks.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteEngineer extends HttpServlet implements WebLogger, Service {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            String idStr = request.getParameter("id");
            if (idStr.equals("")) {
                String fullName = request.getParameter("fullName");
                if (service.deleteByName(fullName)) {
                    response.getWriter().write(fullName + " is deleted.");
                } else {
                    response.getWriter().write("null");
                }
            } else {
                Long id = Long.valueOf(idStr);
                if (service.getDao().deleteById(id)) {
                    response.getWriter().write("Engineer with id: " + id + " is deleted.");
                } else {
                    response.getWriter().write("null");
                }
            }
        } catch (NumberFormatException e) {
            response.getWriter().write("null");
        }

    }
}
