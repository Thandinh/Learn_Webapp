package com.webfruits.dao;

import com.webfruits.model.OrderItemModel;

import java.util.List;

public interface IOrderItemDAO {
    int insert(OrderItemModel orderItem);
    List<OrderItemModel> findOrderItemByOrderId(int orderId);
}
