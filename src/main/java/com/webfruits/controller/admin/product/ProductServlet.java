package com.webfruits.controller.admin.product;

import com.webfruits.model.ProductModel;
import com.webfruits.service.IProductService;
import com.webfruits.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin-product"})
public class ProductServlet extends HttpServlet {
    private IProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductModel> productList = productService.findAll();

        req.setAttribute("productList", productList);
        req.getRequestDispatcher("admin/product/index.jsp").forward(req, resp);
    }
}
