package com.webfruits.controller;

import com.webfruits.dao.IProductDAO;
import com.webfruits.dao.ProductDAO;
import com.webfruits.model.ProductModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/chi-tiet-san-pham"})
public class ProductServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        int productId = Integer.parseInt(req.getParameter("productId"));
        IProductDAO productDAO = new ProductDAO();
        ProductModel product = productDAO.findOne(productId);

        req.setAttribute("product", product);
        req.getRequestDispatcher("product.jsp").include(req, resp);
    }
}
