package com.sqlworks.web;

import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Ajax extends HttpServlet implements WebLogger, Service {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Long id = Long.valueOf(request.getParameter("id"));
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String json = new Gson().toJson(service.getDao().getById(id));
            response.getWriter().write(json);
        } catch (NumberFormatException e){
            response.getWriter().write("null");
        }
        //TODO:make faded animation on alert!
    }
}
