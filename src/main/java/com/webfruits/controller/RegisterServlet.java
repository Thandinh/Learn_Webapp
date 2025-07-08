package com.webfruits.controller;

import com.webfruits.dao.CategoryDAO;
import com.webfruits.dao.ICategoryDAO;
import com.webfruits.dao.IUserDAO;
import com.webfruits.dao.UserDAO;
import com.webfruits.model.CategoryModel;
import com.webfruits.model.UserModel;
import com.webfruits.service.IUserService;
import com.webfruits.service.UserService;
import com.webfruits.util.EmailUtils;
import com.webfruits.util.PasswordUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet(urlPatterns = {"/dang-ky"})
public class RegisterServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        IUserService userService = new UserService();
        if(userService.findByEmail(email) != null) {
            req.setAttribute("errorEmail", "Email đã tồn tại!");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }
        String token = UUID.randomUUID().toString();

        HttpSession session = req.getSession();
        session.setAttribute("pending_email", email);
        session.setAttribute("pending_password", password);
        session.setAttribute("verify_token", token);

        String scheme = req.getScheme();
        String serverName = req.getServerName();
        int serverPort = req.getServerPort();
        String contextPath = req.getContextPath();

        String baseUrl = scheme + "://" + serverName +
                ((serverPort == 80 || serverPort == 443) ? "" : ":" + serverPort) +
                contextPath;

        String verifyLink = baseUrl + "/verify-register?token=" + token;
        EmailUtils.send(email,"Xác minh đăng ký WebFruits", "Vui lòng xác minh tài khoản bằng cách nhấp vào liên kết sau:\n" + verifyLink);
        resp.sendRedirect("verify-register.jsp");
    }
}
