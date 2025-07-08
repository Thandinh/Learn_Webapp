package com.webfruits.controller.admin.order;

import com.webfruits.model.OrderModel;
import com.webfruits.service.IOrderService;
import com.webfruits.service.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin-order"})
public class OrderServlet extends HttpServlet {
    private IOrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<OrderModel> orderList = orderService.findAll();
        req.setAttribute("orderList", orderList);
        req.getRequestDispatcher("admin/order/index.jsp").forward(req, resp);
    }
}
