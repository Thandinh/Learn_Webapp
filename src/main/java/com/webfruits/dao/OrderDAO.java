package com.webfruits.dao;

import com.webfruits.model.OrderModel;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAO implements IOrderDAO{
    @Override
    public int insert(OrderModel orderModel) {
        String sql = "INSERT INTO orders (code, status, user_id) VALUES (?, ?, ?)";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Bước 1: Insert với code tạm rỗng
            ps.setString(1, "");
            ps.setString(2, orderModel.getStatus());
            ps.setInt(3, orderModel.getUserId());
            ps.executeUpdate();

            // Bước 2: Lấy ID sinh ra
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);

                    // Bước 3: Tạo code từ ngày + id
                    String code = String.format("ORD-%s-%03d",
                            new SimpleDateFormat("yyyyMMdd").format(new Date()), id);

                    // Bước 4: Update lại code
                    String updateSql = "UPDATE orders SET code = ? WHERE id = ?";
                    try (PreparedStatement psUpdate = conn.prepareStatement(updateSql)) {
                        psUpdate.setString(1, code);
                        psUpdate.setInt(2, id);
                        psUpdate.executeUpdate();
                    }

                    return id;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

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

    @Override
    public int countOrdersByDate(Date createdAt) {
        String sql = "SELECT COUNT(*) FROM orders WHERE DATE(created_at) = ?";
        int count = 0;
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            try (ResultSet rs = preparedStatement.executeQuery()) { // Thực thi truy vấn

                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            // Xử lý lỗi SQL: in stack trace để debug
            e.printStackTrace();
        }
        return count;
    }
}
