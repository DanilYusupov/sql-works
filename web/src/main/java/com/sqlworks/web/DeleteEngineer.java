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
        Long id = Long.valueOf(request.getParameter("idOnDelete"));
        if (fullName != null) {
            if (service.deleteByName(fullName)) {
                log.info(fullName + " deleted.");
            } else {
                throw new DaoException(fullName + " isn't deleted!");
            }
        } else {
            service.getDao().deleteById(id);
        }
        response.sendRedirect("/home");
    }
}
