package com.webfruits.controller;

import com.webfruits.dao.CategoryDAO;
import com.webfruits.dao.ICategoryDAO;
import com.webfruits.model.CategoryModel;
import com.webfruits.model.OrderItemModel;
import com.webfruits.service.CategoryService;
import com.webfruits.service.ICategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<OrderItemModel> cart = (List<OrderItemModel>) req.getSession().getAttribute("cart");
        if(cart == null) cart = new ArrayList<>();
        int totalCart = 0;
        int countCart = cart.size();
        for(OrderItemModel item: cart) {
            totalCart = (int) item.getPrice() * item.getQuantity();
        }
        req.setAttribute("cart", cart);
        req.setAttribute("totalCart", totalCart);
        req.setAttribute("countCart", countCart);

        ICategoryService categoryService = new CategoryService();
        List<CategoryModel> categoryList = categoryService.findAll();

        req.setAttribute("categoryList", categoryList);
    }
}
