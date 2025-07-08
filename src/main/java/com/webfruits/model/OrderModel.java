package com.webfruits.model;

import com.webfruits.service.IUserService;
import com.webfruits.service.UserService;

import java.sql.Timestamp;

public class OrderModel {
    private int id;
    private String code;
    private String status;
    private int userId; // Sử dụng camelCase cho tên biến trong Java
    private Timestamp createdAt;

    public OrderModel() {
    }


    public OrderModel(String code, String status, int userId) {
        this.code = code;
        this.status = status;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public UserModel getUser() {
        IUserService userService = new UserService();
        return userService.findById(this.userId);
    }
}
