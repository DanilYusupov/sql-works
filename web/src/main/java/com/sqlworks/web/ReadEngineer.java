package com.sqlworks.web;

import com.sqlworks.service.EngineerService;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReadEngineer extends HttpServlet implements WebLogger {

    private EngineerService service = new EngineerService();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String fullName = request.getParameter("readFullName");
        request.getSession().setAttribute("message", service.getInfo(fullName));
        response.sendRedirect("/home");
        //TODO: add opportunity to read by id!
    }

}
