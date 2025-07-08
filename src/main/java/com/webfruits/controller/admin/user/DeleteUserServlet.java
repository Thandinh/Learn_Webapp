package com.webfruits.controller.admin.user;

import com.webfruits.service.IProductService;
import com.webfruits.service.IUserService;
import com.webfruits.service.ProductService;
import com.webfruits.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/admin-delete-user"})
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("userId"));
        IUserService userService = new UserService();
        userService.delete(userId);
        resp.sendRedirect("admin-users");
    }
}
