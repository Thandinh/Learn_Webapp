package com.webfruits.service;

import com.webfruits.dao.IUserDAO;
import com.webfruits.dao.UserDAO;
import com.webfruits.model.UserModel;

import java.util.List;

public class UserService implements IUserService{
    private IUserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    @Override
    public List<UserModel> findAll() {
        return userDAO.findAll();
    }

    @Override
    public boolean insert(UserModel user) {
        return userDAO.insert(user);
    }

    @Override
    public UserModel findById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public UserModel findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public UserModel findByEmailAndPassword(String email, String password) {
        return userDAO.findByEmailAndPassword(email, password);
    }

    @Override
    public boolean delete(int userId) {
        return userDAO.delete(userId);
    }

    @Override
    public boolean update(UserModel user) {
        return userDAO.update(user);
    }

    @Override
    public int countUsers() {
        return userDAO.countUsers();
    }
}
