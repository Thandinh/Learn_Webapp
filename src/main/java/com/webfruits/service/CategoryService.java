package com.webfruits.service;

import com.webfruits.dao.CategoryDAO;
import com.webfruits.dao.ICategoryDAO;
import com.webfruits.model.CategoryModel;

import java.util.List;

public class CategoryService implements ICategoryService{
    private ICategoryDAO categoryDAO;

    public CategoryService() {
        this.categoryDAO = new CategoryDAO();
    }

    @Override
    public List<CategoryModel> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public CategoryModel findOne(int id) {
        return categoryDAO.findOne(id);
    }

    @Override
    public void insert(CategoryModel categoryModel) {
        categoryDAO.insert(categoryModel);
    }

    @Override
    public void update(CategoryModel categoryModel) {
        categoryDAO.update(categoryModel);
    }

    @Override
    public void delete(int id) {
        categoryDAO.delete(id);
    }
}
