package com.baitap.controller;

import com.baitap.dao.CategoryDAO;
import com.baitap.dao.ICategoryDAO;
import com.baitap.dao.IProductDAO;
import com.baitap.dao.ProductDAO;
import com.baitap.model.CategoryModel;
import com.baitap.model.ProductModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/danh-muc"})
public class CategoryServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        IProductDAO productDAO = new ProductDAO();
        ICategoryDAO categoryDAO = new CategoryDAO();
        List<ProductModel> productList = productDAO.findProductsByCategoryId(categoryId);
        CategoryModel category = categoryDAO.findOne(categoryId);



        req.setAttribute("category", category);
        req.setAttribute("productList", productList);
        req.getRequestDispatcher("category.jsp").include(req, resp);
    }
}
