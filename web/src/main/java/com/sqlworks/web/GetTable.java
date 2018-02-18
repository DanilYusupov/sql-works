package com.sqlworks.web;

import com.sqlworks.model.Engineer;
import com.sqlworks.service.EngineerService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetTable extends HttpServlet implements WebLogger {

    private EngineerService service = new EngineerService();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        List<Engineer> list = service.getDao().getAll();
        request.getSession().setAttribute("table", list);
        response.sendRedirect("/home");
        //TODO: Realize using checkbox!!!
    }

}
