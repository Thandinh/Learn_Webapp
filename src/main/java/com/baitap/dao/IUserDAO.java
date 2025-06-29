package com.baitap.dao;

import com.baitap.model.UserModel;

public interface IUserDAO {
    boolean insert(UserModel user);
    UserModel findByEmail(String email);
    UserModel findByEmailAndPassword(String email, String password);
}
