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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/san-pham"})
public class ProductsServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        IProductDAO productDAO = new ProductDAO();

        int page = 1;
        int limit = 6;

        String keyword = req.getParameter("keyword");

        if (req.getParameter("page") != null) {
            try {
                page = Integer.parseInt(req.getParameter("page"));
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        int offset = (page - 1) * limit;

        int total;
        List<ProductModel> productList;

        if (keyword != null && !keyword.trim().isEmpty()) {
            total = productDAO.countProductsByKeyword(keyword);
            productList = productDAO.searchProducts(keyword, offset, limit);
        } else {
            total = productDAO.countProducts();
            productList = productDAO.getProducts(offset, limit);
        }

        int numberPage = (int) Math.ceil((double) total / limit);

        req.setAttribute("productList", productList);
        req.setAttribute("page", page);
        req.setAttribute("numberPage", numberPage);
        req.setAttribute("total", total);
        req.setAttribute("keyword", keyword); // Gửi lại keyword cho form tìm kiếm

        req.getRequestDispatcher("products.jsp").forward(req, resp);
    }
}

