package com.webfruits.controller.admin;

import com.webfruits.model.OrderItemModel;
import com.webfruits.model.OrderModel;
import com.webfruits.service.IOrderItemService;
import com.webfruits.service.IOrderService;
import com.webfruits.service.OrderItemService;
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
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String action = req.getParameter("action");
        if(action != null) {
            switch (action) {
                case "index":
                    showOrders(req, resp);
                    break;
                case "edit":
                    showEdit(req, resp);
                    break;
                case "detail":
                    showDetailOrder(req, resp);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String action = req.getParameter("action");
        if(action != null) {
            switch (action) {
                case "editStatus":
                    editStatusOrder(req, resp);
                    break;
            }
        }
    }

    private void editStatusOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String orderIdStr = req.getParameter("orderId").trim();
        int orderId = Integer.parseInt(orderIdStr);
        String status = req.getParameter("status");
        if(orderService.updateStatus(orderId, status)) {
            resp.sendRedirect(req.getContextPath() + "/admin-order?action=index");
        } else {
            resp.getWriter().println("Cập nhật thất bại.");
        }
    }

    private void showDetailOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        String code = req.getParameter("code");

        IOrderItemService orderItemService = new OrderItemService();
        List<OrderItemModel> orderItems = orderItemService.findOrderItemByOrderId(orderId);

        req.setAttribute("orderItems", orderItems);
        req.setAttribute("code", code);
        req.getRequestDispatcher("admin/orderitem/index.jsp").forward(req, resp);
    }

    private void showEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        OrderModel order = orderService.findById(orderId);
        req.setAttribute("order", order);
        req.getRequestDispatcher("admin/order/edit.jsp").forward(req, resp);
    }

    private void showOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<OrderModel> orderList = orderService.findAll();
        req.setAttribute("orderList", orderList);
        req.getRequestDispatcher("admin/order/index.jsp").forward(req, resp);
    }
}
