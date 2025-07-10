package com.webfruits.dao;

import com.webfruits.model.OrderModel;

import java.util.Date;
import java.util.List;

public interface IOrderDAO {
    int insert(OrderModel orderModel);
    List<OrderModel> findAll();
    OrderModel findById(int orderId);
    boolean updateStatus(int orderId, String newStatus);
    int countOrdersByDate(Date createdAt);
}
