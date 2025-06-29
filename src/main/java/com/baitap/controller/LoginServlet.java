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
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = {"/dang-nhap"})
public class LoginServlet extends BaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();


        if(session.getAttribute("user") != null) {
            resp.sendRedirect("trang-chu");
        } else {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        IUserDAO userDAO = new UserDAO();
        UserModel user = userDAO.findByEmailAndPassword(email, password);

        if(user == null) {
            session.setAttribute("error", "Email hoặc Mật khẩu không hợp lệ!");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } else {
            session.setAttribute("user", user);
            resp.sendRedirect("trang-chu");
        }
    }
}
