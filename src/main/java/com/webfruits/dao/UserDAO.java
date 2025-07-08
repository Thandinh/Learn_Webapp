package com.webfruits.dao;

import com.webfruits.model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO{
    @Override
    public List<UserModel> findAll() {
        List<UserModel> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                UserModel user = new UserModel();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setAvatar(rs.getString("avatar"));
                user.setStatus(rs.getInt("status"));
                user.setCreatedAt(rs.getTimestamp("created_at"));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public boolean insert(UserModel user) {
        String sql = "INSERT INTO users (email, password, role, avatar, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());

            if(user.getAvatar() != null) {
                preparedStatement.setString(4, user.getAvatar());
            } else {
                preparedStatement.setString(4, "assets/images/uploads/avatar/default.jpg");
            }

            if(user.getStatus() != null) {
                preparedStatement.setInt(5, user.getStatus());
            } else {
                preparedStatement.setInt(5, 1);
            }


            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public UserModel findById(int id) {
        UserModel user = null;
        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    user = new UserModel();
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setRole(rs.getString("role"));
                    user.setAvatar(rs.getString("avatar"));
                    user.setStatus(rs.getInt("status"));
                    user.setCreatedAt(rs.getTimestamp("created_at"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
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
                   user.setAvatar(rs.getString("avatar"));
                   user.setStatus(rs.getInt("status"));
                   user.setCreatedAt(rs.getTimestamp("created_at"));
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
                    user.setAvatar(rs.getString("avatar"));
                    user.setStatus(rs.getInt("status"));
                    user.setCreatedAt(rs.getTimestamp("created_at"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean delete(int userId) {

        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection connection = DBConnect.getConnection(); // Lấy kết nối từ DBConnect
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {


            preparedStatement.setInt(1, userId);


            int rowsDeleted = preparedStatement.executeUpdate();


            return rowsDeleted > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(UserModel user) {
        // Câu lệnh SQL để cập nhật các trường của bảng users
        // Dựa vào ID để xác định bản ghi cần cập nhật
        String sql = "UPDATE users SET email = ?, password = ?, role = ?, avatar = ?, status = ? WHERE id = ?";

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {


            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());

            if(user.getAvatar() != null) {
                preparedStatement.setString(4, user.getAvatar());
            } else {
                preparedStatement.setString(4, "assets/images/uploads/avatar/default.jpg");
            }


            if(user.getStatus() != null) {
                preparedStatement.setInt(5, user.getStatus());
            } else {
                preparedStatement.setInt(5, 1);
            }

            preparedStatement.setInt(6, user.getId());
            int rowsUpdated = preparedStatement.executeUpdate();

            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int countUsers() {
        String sql = "SELECT COUNT(*) FROM users WHERE role = 'user'";
        int count = -1; // Mặc định là -1 để chỉ ra lỗi

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
