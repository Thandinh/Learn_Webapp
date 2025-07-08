package com.webfruits.dao;

import com.webfruits.model.CategoryModel;

import java.util.List;

public interface ICategoryDAO {
    List<CategoryModel> findAll();
    CategoryModel findOne(int id);
    void insert(CategoryModel categoryModel);
    void update(CategoryModel categoryModel);
    void delete(int id);

}
