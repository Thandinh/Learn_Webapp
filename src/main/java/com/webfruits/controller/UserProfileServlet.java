package com.webfruits.controller;

import com.webfruits.model.UserModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/thong-tin-ca-nhan"})
public class UserProfileServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        UserModel user = (UserModel) req.getSession().getAttribute("user");
        if(user == null) {
            resp.sendRedirect("dang-nhap");
        }
        req.getRequestDispatcher("profilePage.jsp").forward(req, resp);
    }
}
