package com.webfruits.service;

import com.webfruits.model.OrderModel;

import java.util.List;

public interface IOrderService {
    List<OrderModel> findAll();
    OrderModel findById(int orderId);
    boolean updateStatus(int orderId, String newStatus);
}
