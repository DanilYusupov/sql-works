package com.sqlworks.web;

import com.sqlworks.model.Engineer;
import com.sqlworks.service.EngineerService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateEngineer extends HttpServlet implements WebLogger, Service {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String major = request.getParameter("major");
        Long tel = Long.valueOf(request.getParameter("tel"));
        Long id = service.getDao().save(new Engineer(firstName, lastName, major, tel));
        response.getWriter().write(firstName + " " + lastName + " was successfully crated with id: " + id);
    }

}
