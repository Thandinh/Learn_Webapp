package com.baitap.controller;

import com.baitap.dao.CategoryDAO;
import com.baitap.dao.ICategoryDAO;
import com.baitap.model.CategoryModel;
import com.baitap.model.OrderItemModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/gio-hang"})
public class CartServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        HttpSession session = req.getSession();
        List<OrderItemModel> cart = (List<OrderItemModel>) session.getAttribute("cart");
        if(cart == null) {
            cart = new ArrayList<>();
        }

        long totalCart = 0;
        for(OrderItemModel orderItem: cart) {
            totalCart += orderItem.getPrice() * orderItem.getQuantity();
        }

        req.setAttribute("cart", cart);
        req.setAttribute("totalCart", totalCart);


        req.getRequestDispatcher("cart.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "create":
                createOrder(req);
                break;
            case "delete":
                deleteOrder(req);
                break;
            case "update":
                updateOrder(req);
                break;
        }
        resp.sendRedirect("gio-hang");
    }

    private void updateOrder(HttpServletRequest req) {
        int productId = Integer.parseInt(req.getParameter("productId"));
        String action = req.getParameter("typeChange");
        HttpSession session = req.getSession();
        List<OrderItemModel> cart = (List<OrderItemModel>) session.getAttribute("cart");
        if(cart != null) {
            for(OrderItemModel item: cart) {
                if(item.getProductId() == productId) {
                    if(action.equals("decrease")) {
                        item.setQuantity(item.getQuantity() - 1);
                    } else if(action.equals("increase")) {
                        item.setQuantity(item.getQuantity() + 1);
                    }
                    break;
                }
            }
            session.setAttribute("cart", cart);
        }
    }

    private void deleteOrder(HttpServletRequest req) {
        int productId = Integer.parseInt(req.getParameter("productId"));
        HttpSession session = req.getSession();
        List<OrderItemModel> cart = (List<OrderItemModel>) session.getAttribute("cart");
        if (cart != null) {
            for(OrderItemModel item: cart) {
                if (item.getProductId() == productId) {
                    cart.remove(item);
                    break;
                }
            }
        }
        session.setAttribute("cart", cart);
    }

    private void createOrder(HttpServletRequest req) {
        int productId = Integer.parseInt(req.getParameter("productId"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        int price = (int) Double.parseDouble(req.getParameter("price"));;

        OrderItemModel orderItem = new OrderItemModel();
        orderItem.setProductId(productId);
        orderItem.setQuantity(quantity);
        orderItem.setPrice(price);


        HttpSession session = req.getSession();
        List<OrderItemModel> cart = (List<OrderItemModel>) session.getAttribute("cart");
        boolean isExistInCart = false;

        if (cart == null) {
            cart = new ArrayList<>();
        } else {
            for (OrderItemModel item : cart) {
                if (item.getProductId() == productId) {
                    item.setQuantity(item.getQuantity() + quantity);
                    isExistInCart = true;
                    break;
                }
            }
        }

        if (!isExistInCart) {
            cart.add(orderItem);
        }

        session.setAttribute("cart", cart);
    }
}
