package com.webfruits.controller;

import com.webfruits.dao.IProductDAO;
import com.webfruits.dao.ProductDAO;
import com.webfruits.model.ProductModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet (urlPatterns = {"/trang-chu"})
public class HomeServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        IProductDAO productDAO = new ProductDAO();
        List<ProductModel> productList = productDAO.findAll();

        if(productList == null) productList = new ArrayList<>();
        req.setAttribute("productList", productList);


        req.getRequestDispatcher("shop.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
