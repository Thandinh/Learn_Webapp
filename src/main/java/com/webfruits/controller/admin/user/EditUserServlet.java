package com.webfruits.controller.admin.user;

import com.webfruits.model.UserModel;
import com.webfruits.service.IUserService;
import com.webfruits.service.UserService;
import com.webfruits.util.PasswordUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/admin-edit-user"})
public class EditUserServlet extends HttpServlet {
    private final IUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("userId"));
        UserModel user = userService.findById(id);

        if (user == null) {
            resp.sendRedirect("admin-users");
            return;
        }

        req.setAttribute("user", user);
        req.getRequestDispatcher("admin/user/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        int status = Integer.parseInt(req.getParameter("status"));
        String avatar = req.getParameter("avatar");

        UserModel existingUser = userService.findByEmail(email);
        if (existingUser == null) {
            req.setAttribute("errorEmail", "Không tìm thấy người dùng!");
            req.getRequestDispatcher("admin/user/edit.jsp").forward(req, resp);
            return;
        }

        // Nếu có nhập mật khẩu mới thì mã hóa, không thì giữ nguyên mật khẩu cũ
        if (password != null && !password.trim().isEmpty()) {
            String hashedPassword = PasswordUtils.hashPassword(password);
            existingUser.setPassword(hashedPassword);
        }

        existingUser.setRole(role);
        existingUser.setStatus(status);

        if (userService.update(existingUser)) {
            resp.sendRedirect("admin-users");
        } else {
            req.setAttribute("user", existingUser);
            req.setAttribute("errorEmail", "Cập nhật thất bại!");
            req.getRequestDispatcher("admin/user/edit.jsp").forward(req, resp);
        }
    }
}
