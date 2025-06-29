package com.baitap.model;

import com.baitap.dao.IProductDAO;
import com.baitap.dao.ProductDAO;

public class OrderItemModel {
    private int id;
    private int quantity;
    private double price;
    private int orderId;
    private int productId;

    public OrderItemModel() {
    }

    public OrderItemModel(int id, int quantity, double price, int orderId, int productId) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.orderId = orderId;
        this.productId = productId;
    }

    // Getter và Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public ProductModel getProduct() {
        IProductDAO productDAO = new ProductDAO();
        return productDAO.findOne(this.productId);
    }
}
