package com.sqlworks.web;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Ajax extends HttpServlet implements WebLogger, Service{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
//        String json = new Gson().toJson(service.getDao().getById(1L));
        String data = "Ajax works!";
        response.getWriter().write(data);
    }
}
