package com.example.bai4;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class WelcomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("<h1>Xin chào từ WelcomeServlet!</h1>");
    }
}
