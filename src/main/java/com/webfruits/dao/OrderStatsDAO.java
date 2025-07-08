package com.webfruits.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderStatsDAO implements IOrderStatsDAO{

    @Override
    public double getRevenueByDate(String date) {
        String sql = "SELECT SUM(oi.quantity * oi.price) AS total_revenue\n" +
                "            FROM orders o\n" +
                "            JOIN order_items oi ON o.id = oi.order_id\n" +
                "            WHERE DATE(o.created_at) = ?";
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, date);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    return rs.getDouble("total_revenue");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int getOrderCountByDate(String date) {
        String sql = "SELECT COUNT(*) AS total_orders\n" +
                "            FROM orders\n" +
                "            WHERE DATE(created_at) = ?";
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, date);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt("total_orders");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public double getRevenueByMonth(String yearMonth) {
        String sql = " SELECT SUM(oi.quantity * oi.price) AS total_revenue\n" +
                "            FROM orders o\n" +
                "            JOIN order_items oi ON o.id = oi.order_id\n" +
                "            WHERE DATE_FORMAT(o.created_at, '%Y-%m') = ?";
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, yearMonth);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    return rs.getDouble("total_revenue");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int getOrderCountByMonth(String yearMonth) {
        String sql = " SELECT COUNT(*) AS total_orders\n" +
                "            FROM orders\n" +
                "            WHERE DATE_FORMAT(created_at, '%Y-%m') = ?";
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, yearMonth);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt("total_orders");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Object[]> getDailyStats() {
        List<Object[]> list = new ArrayList<>();

        String sql = " SELECT \n" +
                "            DATE(o.created_at) AS day,\n" +
                "            COUNT(DISTINCT o.id) AS orderCount,\n" +
                "            SUM(oi.quantity * oi.price) AS revenue\n" +
                "        FROM \n" +
                "            orders o\n" +
                "        JOIN \n" +
                "            order_items oi ON o.id = oi.order_id\n" +
                "        WHERE \n" +
                "            DATE_FORMAT(o.created_at, '%Y-%m') = DATE_FORMAT(CURDATE(), '%Y-%m')\n" +
                "        GROUP BY \n" +
                "            DATE(o.created_at)\n" +
                "        ORDER BY \n" +
                "            day";

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                Object[] row = new Object[3];
                row[0] = rs.getString("day");              // yyyy-MM-dd
                row[1] = rs.getInt("orderCount");          // int
                row[2] = rs.getBigDecimal("revenue");      // BigDecimal
                list.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

}
