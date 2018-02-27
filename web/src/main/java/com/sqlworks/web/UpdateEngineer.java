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
        Long id = Long.valueOf(request.getParameter("idUpdate"));
        String firstName = request.getParameter("firstNameUpdate");
        String lastName = request.getParameter("lastNameUpdate");
        String major = request.getParameter("majorUpdate");
        Long tel = Long.valueOf(request.getParameter("telUpdate"));
        String result = service.updateEngineer(id, firstName, lastName, major, tel);
        request.getSession().setAttribute("message", result);
        response.sendRedirect("/home");
        //TODO: make alert on engineer existence while filling fields.
    }
}
