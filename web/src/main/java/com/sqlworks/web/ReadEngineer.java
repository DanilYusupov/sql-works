package com.sqlworks.web;

import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReadEngineer extends HttpServlet implements WebLogger, Service {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fullName = request.getParameter("readFullName");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String json = new Gson().toJson(service.getByName(fullName));
        response.getWriter().write(json);
    }

}
