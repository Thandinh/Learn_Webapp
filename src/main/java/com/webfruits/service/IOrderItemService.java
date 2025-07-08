package com.webfruits.service;

import com.webfruits.model.OrderItemModel;

import java.util.List;

public interface IOrderItemService {
    int insert(OrderItemModel orderItem);
    List<OrderItemModel> findOrderItemByOrderId(int orderId);
}
