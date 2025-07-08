package com.webfruits.dao;

import com.webfruits.model.OrderModel;

import java.util.List;

public interface IOrderDAO {
    List<OrderModel> findAll();
    OrderModel findById(int orderId);
    boolean updateStatus(int orderId, String newStatus);
}
