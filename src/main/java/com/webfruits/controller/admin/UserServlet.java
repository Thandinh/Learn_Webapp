package com.webfruits.controller.admin;

import com.webfruits.dao.IUserDAO;
import com.webfruits.dao.UserDAO;
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
import java.util.List;

@WebServlet(urlPatterns = {"/admin-user"})
public class UserServlet extends HttpServlet {
    private IUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String action = req.getParameter("action");
        if(action != null) {
            switch (action) {
                case "index":
                    showUsers(req, resp);
                    break;
                case "create":
                    showCreate(req, resp);
                    break;
                case "edit":
                    showEdit(req, resp);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String action = req.getParameter("action");
        if(action != null) {
            switch (action) {
                case "create":
                    createUser(req, resp);
                    break;
                case "edit":
                    editUser(req, resp);
                    break;
                case "delete":
                    deleteUser(req, resp);
                    break;
            }
        }
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int userId = Integer.parseInt(req.getParameter("userId"));
        IUserService userService = new UserService();
        userService.delete(userId);
        resp.sendRedirect("admin-user?action=index");
    }

    private void editUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
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
            resp.sendRedirect("admin-user?action=index");
        } else {
            req.setAttribute("user", existingUser);
            req.setAttribute("errorEmail", "Cập nhật thất bại!");
            req.getRequestDispatcher("admin/user/edit.jsp").forward(req, resp);
        }
    }

    private void createUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        int status = Integer.parseInt(req.getParameter("status"));
        IUserDAO userDAO = new UserDAO();

        if (userDAO.findByEmail(email) != null) {
            req.setAttribute("errorEmail", "Email đã tồn tại!");

            req.setAttribute("errorEmail", "Email đã tồn tại!");
            req.setAttribute("email", email);
            req.setAttribute("role", role);
            req.setAttribute("status", status);
            req.getRequestDispatcher("admin/user/create.jsp").forward(req, resp);
        } else {
            String hashedPassword  = PasswordUtils.hashPassword(password);
            UserModel user = new UserModel();
            user.setEmail(email);
            user.setPassword(hashedPassword);
            user.setRole("user");
            user.setStatus(status);
            user.setRole(role);
            if(userDAO.insert(user)) {
                resp.sendRedirect(req.getContextPath() + "/admin-user?action=index");
            }
        }

    }

    private void showEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("userId"));
        UserModel user = userService.findById(id);

        if (user == null) {
            resp.sendRedirect("admin-users?action=index");
            return;
        }

        req.setAttribute("user", user);
        req.getRequestDispatcher("admin/user/edit.jsp").forward(req, resp);
    }

    private void showCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("admin/user/create.jsp").forward(req, resp);
    }

    private void showUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserModel> users = userService.findAll();
        req.setAttribute("users", users);

        req.getRequestDispatcher("admin/user/index.jsp").forward(req, resp);
    }


}
