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

@WebServlet(urlPatterns = {"/admin-edit-order"})
public class EditOrderServlet extends HttpServlet {
    private IOrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        OrderModel order = orderService.findById(orderId);
        req.setAttribute("order", order);
        req.getRequestDispatcher("admin/order/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderIdStr = req.getParameter("orderId").trim();
        int orderId = Integer.parseInt(orderIdStr);
        String status = req.getParameter("status");
        if(orderService.updateStatus(orderId, status)) {
            resp.sendRedirect(req.getContextPath() + "/admin-order");
        } else {
            resp.getWriter().println("Cập nhật thất bại.");
        }
    }
}
