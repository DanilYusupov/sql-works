package com.sqlworks.web;

import com.sqlworks.model.Engineer;
import com.sqlworks.service.EngineerService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ReadEngineer extends HttpServlet implements WebLogger {

    private EngineerService service = new EngineerService();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String fullName = request.getParameter("readFullName");
        Engineer read = service.getByName(fullName);

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<script type=\"text/javascript\">");
        out.println("$(window).on('load',function(){");
        out.println("$('#myModal').modal('show');");
        out.println("});");
        out.println("</script>");

        response.sendRedirect("/home");
    }

}
