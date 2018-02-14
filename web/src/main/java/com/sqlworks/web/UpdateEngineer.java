package com.sqlworks.web;

import com.sqlworks.service.EngineerService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateEngineer extends HttpServlet implements WebLogger {

    private EngineerService service = new EngineerService();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        Long id = Long.valueOf(request.getParameter("updateId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String major = request.getParameter("major");
        String tel = request.getParameter("tel");
        service.updateEngineer(id, firstName, lastName, major, tel);
        response.sendRedirect("/home");
    }

}
