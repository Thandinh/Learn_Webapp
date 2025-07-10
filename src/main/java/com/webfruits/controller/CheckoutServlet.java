package com.webfruits.controller;

import com.webfruits.model.OrderItemModel;
import com.webfruits.model.OrderModel;
import com.webfruits.model.UserModel;
import com.webfruits.service.IOrderItemService;
import com.webfruits.service.IOrderService;
import com.webfruits.service.OrderItemService;
import com.webfruits.service.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/dat-hang"})
public class CheckoutServlet extends BaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        HttpSession session = req.getSession();
        UserModel user = (UserModel) session.getAttribute("user");
        List<OrderItemModel> cart = (List<OrderItemModel>) session.getAttribute("cart");
        if(user == null) {
            resp.sendRedirect("dang-nhap");
        } else {
            proccessCheckout(req, user, cart);
            resp.sendRedirect("gio-hang");
        }
    }

    private void proccessCheckout(HttpServletRequest req, UserModel user, List<OrderItemModel> cart) {
        if(cart != null && cart.size() > 0) {
            OrderModel order = new OrderModel();
            order.setUserId(user.getId());
            order.setStatus("processing");
            IOrderService orderService = new OrderService();
            int orderId = orderService.insert(order);

            IOrderItemService orderItemService = new OrderItemService();
            for(OrderItemModel item: cart) {
                item.setOrderId(orderId);
                orderItemService.insert(item);
            }
            req.getSession().setAttribute("CheckoutSuccess", "Đặt hàng thành công!");
            req.getSession().removeAttribute("cart");
        }
    }
}
