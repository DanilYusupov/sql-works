package com.sqlworks.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateEngineer extends HttpServlet implements WebLogger, Service {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        Long id = Long.valueOf(request.getParameter("idUpdate"));
        String firstName = request.getParameter("firstNameUpdate");
        String lastName = request.getParameter("lastNameUpdate");
        String major = request.getParameter("majorUpdate");
        String telStr = request.getParameter("telUpdate");
        Long tel = 0L;
        if (!telStr.equals("")){
            tel = Long.valueOf(telStr);
        }
        String result = service.updateEngineer(id, firstName, lastName, major, tel);
        request.getSession().setAttribute("message", result);
        response.sendRedirect("/home");
        //TODO: make alert on engineer existence while filling fields.
    }
}
