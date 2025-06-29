package com.baitap.controller;

import com.baitap.dao.CategoryDAO;
import com.baitap.dao.ICategoryDAO;
import com.baitap.model.CategoryModel;
import com.baitap.model.OrderItemModel;
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

        ICategoryDAO categoryDAO = new CategoryDAO();
        List<CategoryModel> categoryList = categoryDAO.findAll();

        req.setAttribute("categoryList", categoryList);
    }
}
