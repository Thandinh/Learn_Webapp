package com.webfruits.dao;

import com.webfruits.model.ProductModel;

import java.sql.*;
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

    @Override
    public int insert(ProductModel product) {
        String sql = "INSERT INTO products (name, description, price, pricesale, quantity, view, thumbnail, category_id, created_at) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        int generatedId = -1;

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // 1. name (NOT NULL)
            ps.setString(1, product.getName());

            // 2. description (NULLABLE)
            if (product.getDescription() != null) {
                ps.setString(2, product.getDescription());
            } else {
                ps.setNull(2, Types.VARCHAR);
            }

            // 3. price (NOT NULL)
            ps.setDouble(3, product.getPrice());

            // 4. pricesale (NULLABLE)
            if (product.getPricesale() != null) {
                ps.setDouble(4, product.getPricesale());
            } else {
                ps.setNull(4, Types.DECIMAL);
            }

            // 5. quantity (NOT NULL)
            ps.setInt(5, product.getQuantity());

            // 6. view (nullable in DB, but default to 0 if null)
            if (product.getView() != null) {
                ps.setInt(6, product.getView());
            } else {
                ps.setInt(6, 0);
            }

            // 7. thumbnail (nullable)
            if (product.getThumbnail() != null) {
                ps.setString(7, product.getThumbnail());
            } else {
                ps.setNull(7, Types.VARCHAR);
            }

            // 8. category_id (NOT NULL)
            ps.setInt(8, product.getCategoryId());

            // 9. created_at (nullable, default to now)
            if (product.getCreatedAt() != null) {
                ps.setTimestamp(9, product.getCreatedAt());
            } else {
                ps.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
            }

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
    public boolean delete(int productId) {
        // Câu lệnh SQL để xóa sản phẩm
        String sql = "DELETE FROM products WHERE id = ?";

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, productId);

            int affectedRows = ps.executeUpdate();

            return affectedRows > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(ProductModel product) {
        String sql = "UPDATE products SET name = ?, description = ?, price = ?, pricesale = ?, quantity = ?, view = ?, thumbnail = ?, category_id = ?, updated_at = ? "
                + "WHERE id = ?";

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            // 1. name
            ps.setString(1, product.getName());

            // 2. description
            if (product.getDescription() != null) {
                ps.setString(2, product.getDescription());
            } else {
                ps.setNull(2, Types.VARCHAR);
            }

            // 3. price
            ps.setDouble(3, product.getPrice());

            // 4. pricesale
            if (product.getPricesale() != null) {
                ps.setDouble(4, product.getPricesale());
            } else {
                ps.setNull(4, Types.DECIMAL);
            }

            // 5. quantity
            ps.setInt(5, product.getQuantity());

            // 6. view
            if (product.getView() != null) {
                ps.setInt(6, product.getView());
            } else {
                ps.setInt(6, 0);
            }

            // 7. thumbnail
            if (product.getThumbnail() != null) {
                ps.setString(7, product.getThumbnail());
            } else {
                ps.setNull(7, Types.VARCHAR);
            }

            // 8. category_id
            ps.setInt(8, product.getCategoryId());

            // 9. updated_at (set hiện tại)
            ps.setTimestamp(9, new Timestamp(System.currentTimeMillis()));

            // 10. id (for WHERE clause)
            ps.setInt(10, product.getId());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


}
