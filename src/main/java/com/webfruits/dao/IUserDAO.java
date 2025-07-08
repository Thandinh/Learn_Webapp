package com.webfruits.dao;

import com.webfruits.model.UserModel;

import java.util.List;

public interface IUserDAO {
    List<UserModel> findAll();
    boolean insert(UserModel user);
    UserModel findById(int id);
    UserModel findByEmail(String email);
    UserModel findByEmailAndPassword(String email, String password);
    boolean delete(int userId);
    boolean update(UserModel user);
    int countUsers();
}
