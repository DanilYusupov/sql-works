package com.sqlworks.web;

import com.sqlworks.dao.DaoException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateEngineer extends HttpServlet implements WebLogger, Service {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        Long id = Long.valueOf(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String major = request.getParameter("major");
        Long tel = 0L;
        String telStr = request.getParameter("tel");
        if (!telStr.equals("")){
            tel = Long.valueOf(telStr);
        }
        try {
            String message = service.updateEngineer(id, firstName, lastName, major, tel);
            response.getWriter().write(message);
        } catch (DaoException e){
            response.getWriter().write("false");
        }
    }
        //TODO: make alert on engineer existence while filling fields.
}
