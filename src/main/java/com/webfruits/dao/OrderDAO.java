package com.webfruits.dao;

import com.webfruits.model.OrderModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements IOrderDAO{
    @Override
    public List<OrderModel> findAll() {
        List<OrderModel> orders = new ArrayList<>();
        // Câu lệnh SQL để lấy tất cả các cột từ bảng 'orders'
        String sql = "SELECT id, code, status, user_id, created_at FROM orders";

        try (Connection connection = DBConnect.getConnection(); // Lấy kết nối từ DBConnect
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) { // Thực thi truy vấn và nhận ResultSet

            // Lặp qua từng hàng trong ResultSet
            while (rs.next()) {
                OrderModel order = new OrderModel(); // Tạo một đối tượng OrderModel mới

                // Gán giá trị từ ResultSet vào các thuộc tính của OrderModel
                order.setId(rs.getInt("id"));
                order.setCode(rs.getString("code"));
                order.setStatus(rs.getString("status"));
                order.setUserId(rs.getInt("user_id")); // Chú ý: trong DB là user_id, trong model là userId
                order.setCreatedAt(rs.getTimestamp("created_at"));

                orders.add(order); // Thêm đối tượng OrderModel vào danh sách
            }

        } catch (SQLException e) {
            // Xử lý lỗi SQL: in stack trace để debug
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public OrderModel findById(int orderId) {
        OrderModel order = null; // Khởi tạo là null, sẽ gán nếu tìm thấy
        // Câu lệnh SQL để lấy tất cả các cột từ bảng 'orders' WHERE id = ?
        String sql = "SELECT id, code, status, user_id, created_at FROM orders WHERE id = ?";

        try (Connection connection = DBConnect.getConnection(); // Lấy kết nối từ DBConnect
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Đặt tham số ID vào câu lệnh SQL
            preparedStatement.setInt(1, orderId);

            try (ResultSet rs = preparedStatement.executeQuery()) { // Thực thi truy vấn

                // Nếu có kết quả (chỉ nên có tối đa 1 hàng vì ID là duy nhất)
                if (rs.next()) {
                    order = new OrderModel(); // Tạo một đối tượng OrderModel mới

                    // Gán giá trị từ ResultSet vào các thuộc tính của OrderModel
                    order.setId(rs.getInt("id"));
                    order.setCode(rs.getString("code"));
                    order.setStatus(rs.getString("status"));
                    order.setUserId(rs.getInt("user_id"));
                    order.setCreatedAt(rs.getTimestamp("created_at"));
                }
            }

        } catch (SQLException e) {
            // Xử lý lỗi SQL: in stack trace để debug
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public boolean updateStatus(int orderId, String newStatus) {
        // Câu lệnh SQL để cập nhật cột 'status' của một đơn hàng dựa trên 'id'
        String sql = "UPDATE orders SET status = ? WHERE id = ?";

        try (Connection connection = DBConnect.getConnection(); // Lấy kết nối từ DBConnect
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Đặt các tham số vào câu lệnh SQL
            preparedStatement.setString(1, newStatus); // Tham số thứ nhất là status mới
            preparedStatement.setInt(2, orderId);     // Tham số thứ hai là ID của đơn hàng

            // Thực thi câu lệnh cập nhật
            int affectedRows = preparedStatement.executeUpdate();

            // Trả về true nếu có ít nhất một hàng bị ảnh hưởng (cập nhật thành công),
            // ngược lại trả về false (có thể không tìm thấy ID hoặc có lỗi)
            return affectedRows > 0;

        } catch (SQLException e) {
            // Xử lý lỗi SQL: in stack trace để debug
            e.printStackTrace();
            return false; // Cập nhật thất bại
        }
    }
}
