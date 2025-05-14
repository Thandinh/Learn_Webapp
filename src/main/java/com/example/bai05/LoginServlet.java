package com.example.bai05;


import java.io.IOException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Servlet với đường dẫn /login và initParam
@WebServlet(
        urlPatterns = {"/login"},
        initParams = {
                @WebInitParam(name = "welcomeMessage", value = "Chào mừng bạn đến với hệ thống!")
        }
)
public class LoginServlet extends HttpServlet {
    private String message;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        message = config.getInitParameter("welcomeMessage");
        System.out.println("Thông điệp khởi tạo: " + message);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println("<h2>Đăng nhập thành công!</h2>");
        response.getWriter().println("<p>" + message + "</p>");
    }
}
