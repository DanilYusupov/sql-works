package com.sqlworks.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteEngineer extends HttpServlet implements WebLogger, Service{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            Long id = Long.valueOf(request.getParameter("id"));
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            if (service.getDao().deleteById(id)){
                response.getWriter().write("Engineer with id: " + id + " is deleted.");
            } else {
                response.getWriter().write("null");
            }
        } catch (NumberFormatException e){
            response.getWriter().write("null");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String fullName = request.getParameter("fullName");
        if (fullName != null) {
            if (service.deleteByName(fullName)) {
                request.getSession().setAttribute("message", fullName + " is deleted.");
            } else {
                request.getSession().setAttribute("message",fullName + " isn't deleted! Check Your input.");
            }
        } else {
            Long id = Long.valueOf(request.getParameter("idOnDelete"));
            if (service.getDao().deleteById(id)) {
                request.getSession().setAttribute("message", "Engineer with id=" + id + " is deleted.");
            } else {
                request.getSession().setAttribute("message", "Error deleting engineer! No such engineer with id=" + id);
            }
        }
        response.sendRedirect("/home");
    }
}
