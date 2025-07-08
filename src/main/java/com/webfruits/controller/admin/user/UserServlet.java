package com.webfruits.controller.admin.user;

import com.webfruits.model.UserModel;
import com.webfruits.service.IUserService;
import com.webfruits.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin-users"})
public class UserServlet extends HttpServlet {
    private IUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserModel> users = userService.findAll();
        req.setAttribute("users", users);

        req.getRequestDispatcher("admin/user/index.jsp").forward(req, resp);
    }
}
