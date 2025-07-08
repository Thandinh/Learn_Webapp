package com.webfruits.controller;

import com.webfruits.dao.IUserDAO;
import com.webfruits.dao.UserDAO;
import com.webfruits.model.UserModel;
import com.webfruits.util.PasswordUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = {"/dang-nhap"})
public class LoginServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("user") != null) {
            resp.sendRedirect("trang-chu");
        } else {
            // ✅ Forward đúng đến file JSP
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        IUserDAO userDAO = new UserDAO();
        UserModel user = userDAO.findByEmail(email);

        if (user != null) {
            String hashedPasswordFromDB = user.getPassword();
            try {
                if (PasswordUtils.checkPassword(password, hashedPasswordFromDB) && user.getStatus() == 1) {
                    session.setAttribute("user", user);
                    if (user.getRole().equals("admin")) {
                        resp.sendRedirect(req.getContextPath() + "/admin-home");
                    } else {
                        resp.sendRedirect(req.getContextPath() + "/trang-chu");
                    }
                    return;
                } else {
                    req.setAttribute("error", "Email hoặc Mật khẩu không hợp lệ!");
                }
            } catch (IllegalArgumentException e) {
                req.setAttribute("error", "Dữ liệu mật khẩu không hợp lệ.");
            }
        } else {
            req.setAttribute("error", "Email hoặc Mật khẩu không hợp lệ!");
        }

        // ✅ Trả lại form nếu login thất bại
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}
