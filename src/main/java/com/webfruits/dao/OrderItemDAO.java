package com.webfruits.dao;

import com.webfruits.model.OrderItemModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAO implements IOrderItemDAO {
    @Override
    public int insert(OrderItemModel orderItem) {
        String sql = "INSERT INTO order_items (quantity, price, order_id, product_id) VALUES (?, ?, ?, ?)";
        int generatedId = -1;

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, orderItem.getQuantity());
            ps.setDouble(2, orderItem.getPrice()); // Use setBigDecimal
            ps.setInt(3, orderItem.getOrderId());
            ps.setInt(4, orderItem.getProductId());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return generatedId;
    }

    @Override
    public List<OrderItemModel> findOrderItemByOrderId(int orderId) {
        List<OrderItemModel> orderItems = new ArrayList<>();
        // Câu lệnh SQL để chọn tất cả order_items có order_id bằng tham số truyền vào
        String sql = "SELECT id, quantity, price, order_id, product_id FROM order_items WHERE order_id = ?";

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            // Đặt giá trị cho tham số order_id
            ps.setInt(1, orderId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrderItemModel orderItem = new OrderItemModel();
                    orderItem.setId(rs.getInt("id"));
                    orderItem.setQuantity(rs.getInt("quantity"));
                    orderItem.setPrice(rs.getDouble("price"));
                    orderItem.setOrderId(rs.getInt("order_id"));
                    orderItem.setProductId(rs.getInt("product_id"));
                    orderItems.add(orderItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Bạn có thể cân nhắc ném một ngoại lệ RuntimeException hoặc tùy chỉnh ở đây
            // thay vì chỉ in ra lỗi và trả về danh sách rỗng, tùy thuộc vào chiến lược xử lý lỗi của bạn.
        }
        return orderItems;
    }
}
