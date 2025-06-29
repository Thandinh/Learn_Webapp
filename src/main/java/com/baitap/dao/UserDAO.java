package com.baitap.dao;

import com.baitap.model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO implements IUserDAO{
    @Override
    public boolean insert(UserModel user) {
        String sql = "INSERT INTO users (email, password, role) VALUES (?, ?, ?)";

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public UserModel findByEmail(String email) {
        UserModel user = null;
        String sql = "SELECT * FROM users WHERE email = ?";

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, email);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                   user = new UserModel();
                   user.setId(rs.getInt("id"));
                   user.setEmail(rs.getString("email"));
                   user.setPassword(rs.getString("password"));
                   user.setRole(rs.getString("role"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public UserModel findByEmailAndPassword(String email, String password) {
        UserModel user = null;
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    user = new UserModel();
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setRole(rs.getString("role"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
