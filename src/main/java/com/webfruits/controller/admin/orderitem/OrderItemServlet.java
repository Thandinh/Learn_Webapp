package com.webfruits.controller.admin.orderitem;

import com.webfruits.model.OrderItemModel;
import com.webfruits.model.OrderModel;
import com.webfruits.service.IOrderItemService;
import com.webfruits.service.OrderItemService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin-detail-order"})
public class OrderItemServlet extends HttpServlet {
    private IOrderItemService orderItemService = new OrderItemService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        String code = req.getParameter("code");
        List<OrderItemModel> orderItems = orderItemService.findOrderItemByOrderId(orderId);

        req.setAttribute("orderItems", orderItems);
        req.setAttribute("code", code);
        req.getRequestDispatcher("admin/orderitem/index.jsp").forward(req, resp);
    }
}
