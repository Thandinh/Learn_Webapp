package com.baitap.controller;

import com.baitap.dao.CategoryDAO;
import com.baitap.dao.ICategoryDAO;
import com.baitap.dao.IUserDAO;
import com.baitap.dao.UserDAO;
import com.baitap.model.CategoryModel;
import com.baitap.model.UserModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

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

        IUserDAO userDAO = new UserDAO();

        if (userDAO.findByEmail(email) != null) {
            req.setAttribute("errorEmail", "Email đã tồn tại!");

            // Lấy lại danh sách category để tránh lỗi khi JSP cần nó
            ICategoryDAO categoryDAO = new CategoryDAO();
            List<CategoryModel> categoryList = categoryDAO.findAll();
            req.setAttribute("categoryList", categoryList);

            req.getRequestDispatcher("/register.jsp").forward(req, resp);

        } else {
            UserModel user = new UserModel();
            user.setEmail(email);
            user.setPassword(password);
            user.setRole("user");
            if(userDAO.insert(user)) {
                resp.sendRedirect("dang-nhap");
            }
        }
    }

}
