package com.sqlworks.web;

import com.sqlworks.model.Engineer;
import com.sqlworks.service.EngineerService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Home extends HttpServlet implements WebLogger{

    public void init(ServletConfig config){
        log.info("Started servlet: " + config.getServletName());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("/home.jsp").forward(request, response);
        request.getSession().removeAttribute("message");
    }
}
