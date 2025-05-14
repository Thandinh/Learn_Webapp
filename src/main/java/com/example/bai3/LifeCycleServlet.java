package com.example.bai3;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/lifecycle")
public class LifeCycleServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.print("LifeCycle: init()");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("LifeCycleServlet: doGet()");
        response.setContentType("text/html");
        response.getWriter().println("<h2>This is LifeCycleServlet</h2>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("LifeCycleServlet: doPost()");
        response.setContentType("text/html");
        response.getWriter().println("<h1>LifeCycleServlet POST request</h1>");
    }

    @Override
    public void destroy() {
        System.out.println("LifeCycleServlet: destroy()");
    }
}