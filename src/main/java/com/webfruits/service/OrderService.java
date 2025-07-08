package com.webfruits.service;

import com.webfruits.dao.IOrderDAO;
import com.webfruits.dao.OrderDAO;
import com.webfruits.model.OrderModel;

import java.util.List;

public class OrderService implements IOrderService{
    private IOrderDAO orderDAO = new OrderDAO();

    @Override
    public List<OrderModel> findAll() {
        return orderDAO.findAll();
    }

    @Override
    public OrderModel findById(int orderId) {
        return orderDAO.findById(orderId);
    }

    @Override
    public boolean updateStatus(int orderId, String newStatus) {
        return orderDAO.updateStatus(orderId, newStatus);
    }
}
