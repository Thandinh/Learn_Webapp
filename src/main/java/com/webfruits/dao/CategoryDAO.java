package com.webfruits.dao;

import com.webfruits.model.CategoryModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements ICategoryDAO{
    @Override
    public List<CategoryModel> findAll() {
        List<CategoryModel> listCategory = new ArrayList<>();
        String sql = "SELECT * FROM categories";

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

             while (rs.next()) {
                CategoryModel categoryModel = new CategoryModel();
                categoryModel.setId(rs.getInt("id"));
                categoryModel.setName(rs.getString("name"));
                listCategory.add(categoryModel);
             }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listCategory;
    }

    @Override
    public CategoryModel findOne(int id) {
        CategoryModel category = null;
        String sql = "SELECT * FROM categories WHERE id = ?";

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    category = new CategoryModel();
                    category.setId(rs.getInt("id"));
                    category.setName(rs.getString("name"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category;
    }

    @Override
    public void insert(CategoryModel categoryModel) {
        String sql = "INSERT INTO categories (name) VALUES (?)";

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, categoryModel.getName());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(CategoryModel categoryModel) {
        String sql = "UPDATE categories SET name = ? WHERE id = ?";

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, categoryModel.getName());
            preparedStatement.setInt(2, categoryModel.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM categories WHERE id = ?";

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
