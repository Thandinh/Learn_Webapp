package com.webfruits.controller;

import com.webfruits.model.UserModel;
import com.webfruits.service.IUserService;
import com.webfruits.service.UserService;
import com.webfruits.util.PasswordUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = {"/verify-register"})
public class VerifyRegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        HttpSession session = req.getSession();

        String savedToken = (String) session.getAttribute("verify_token");
        String email = (String) session.getAttribute("pending_email");
        String password = (String) session.getAttribute("pending_password");

        if(token != null && token.equals(savedToken) && email != null && password != null) {
            String hashedPassword = PasswordUtils.hashPassword(password);
            UserModel user = new UserModel();
            user.setEmail(email);
            user.setPassword(hashedPassword);
            user.setRole("user");

            IUserService userService = new UserService();
            userService.insert(user);

            session.removeAttribute("verify_token");
            session.removeAttribute("pending_email");
            session.removeAttribute("pending_password");

            resp.sendRedirect("dang-nhap?register=success");
        } else {
            resp.getWriter().println("Xác minh thất bại hoặc đã hết hạn!");
        }
    }
}
