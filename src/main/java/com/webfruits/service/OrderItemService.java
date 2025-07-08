package com.webfruits.service;

import com.webfruits.dao.IOrderItemDAO;
import com.webfruits.dao.OrderItemDAO;
import com.webfruits.model.OrderItemModel;

import java.util.List;

public class OrderItemService implements IOrderItemService {
    private IOrderItemDAO orderItemDAO = new OrderItemDAO();

    @Override
    public int insert(OrderItemModel orderItem) {
        return orderItemDAO.insert(orderItem);
    }

    @Override
    public List<OrderItemModel> findOrderItemByOrderId(int orderId) {
        return orderItemDAO.findOrderItemByOrderId(orderId);
    }
}
