package com.sqlworks.web;

import com.sqlworks.model.Engineer;
import com.sqlworks.service.EngineerService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateEngineer extends HttpServlet implements WebLogger, Service {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String major = request.getParameter("major");
        Long tel = Long.valueOf(request.getParameter("tel"));
        log.info("Получено из формы: " + firstName + ", " + lastName + ", " + major + ", " + tel + ".");
        service.getDao().save(new Engineer(firstName, lastName, major, tel));
        request.getSession().setAttribute("message", "Engineer " + firstName + " " + lastName + " is created!");
        response.sendRedirect("/home");
    }
}
