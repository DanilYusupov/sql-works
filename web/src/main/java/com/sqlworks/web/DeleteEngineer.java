package com.sqlworks.web;

import com.sqlworks.dao.DaoException;
import com.sqlworks.service.EngineerService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteEngineer extends HttpServlet implements WebLogger {

    private EngineerService service = new EngineerService();

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
            service.getDao().deleteById(id);
            request.getSession().setAttribute("message", "Engineer with id=" + id + " is deleted.");
        }
        response.sendRedirect("/home");
    }
}
