package com.webfruits.controller.admin.user;

import com.webfruits.dao.CategoryDAO;
import com.webfruits.dao.ICategoryDAO;
import com.webfruits.dao.IUserDAO;
import com.webfruits.dao.UserDAO;
import com.webfruits.model.CategoryModel;
import com.webfruits.model.UserModel;
import com.webfruits.util.PasswordUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin-create-user"})
public class CreateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("admin/user/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
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
                resp.sendRedirect("admin-users");
            }
        }
    }
}
