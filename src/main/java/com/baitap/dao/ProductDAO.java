package com.baitap.dao;

import com.baitap.model.ProductModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {

    @Override
    public List<ProductModel> findAll() {
        List<ProductModel> products = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                ProductModel product = new ProductModel();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setPricesale(rs.getDouble("pricesale"));
                product.setQuantity(rs.getInt("quantity"));
                product.setView(rs.getInt("view"));
                product.setThumbnail(rs.getString("thumbnail"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setCreatedAt(rs.getTimestamp("created_at"));
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public ProductModel findOne(int productId) {
        ProductModel product = null;
        String sql = "SELECT * FROM products WHERE id = ?";

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, productId);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    product = new ProductModel();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getDouble("price"));
                    product.setPricesale(rs.getDouble("pricesale"));
                    product.setQuantity(rs.getInt("quantity"));
                    product.setView(rs.getInt("view"));
                    product.setThumbnail(rs.getString("thumbnail"));
                    product.setCategoryId(rs.getInt("category_id"));
                    product.setCreatedAt(rs.getTimestamp("created_at"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<ProductModel> findProductsByCategoryId(int categoryId) {
        List<ProductModel> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE category_id = ?";

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, categoryId);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    ProductModel product = new ProductModel();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getDouble("price"));
                    product.setPricesale(rs.getDouble("pricesale"));
                    product.setQuantity(rs.getInt("quantity"));
                    product.setView(rs.getInt("view"));
                    product.setThumbnail(rs.getString("thumbnail"));
                    product.setCategoryId(rs.getInt("category_id"));
                    product.setCreatedAt(rs.getTimestamp("created_at"));
                    products.add(product);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<ProductModel> getProducts(int from, int amount) {
        List<ProductModel> products = new ArrayList<>();
        String sql = "SELECT * FROM products LIMIT ?, ?";

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, from);
            preparedStatement.setInt(2, amount);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    ProductModel product = new ProductModel();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getDouble("price"));
                    product.setPricesale(rs.getDouble("pricesale"));
                    product.setQuantity(rs.getInt("quantity"));
                    product.setView(rs.getInt("view"));
                    product.setThumbnail(rs.getString("thumbnail"));
                    product.setCategoryId(rs.getInt("category_id"));
                    product.setCreatedAt(rs.getTimestamp("created_at"));
                    products.add(product);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public int countProducts() {
        String sql = "SELECT COUNT(*) FROM products";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<ProductModel> searchProducts(String keyword, int offset, int limit) {
        List<ProductModel> list = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE name LIKE ? LIMIT ? OFFSET ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");
            stmt.setInt(2, limit);
            stmt.setInt(3, offset);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ProductModel product = new ProductModel();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setPricesale(rs.getDouble("pricesale"));
                product.setQuantity(rs.getInt("quantity"));
                product.setView(rs.getInt("view"));
                product.setThumbnail(rs.getString("thumbnail"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setCreatedAt(rs.getTimestamp("created_at"));
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public int countProductsByKeyword(String keyword) {
        String sql = "SELECT COUNT(*) FROM products WHERE name LIKE ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }


}
