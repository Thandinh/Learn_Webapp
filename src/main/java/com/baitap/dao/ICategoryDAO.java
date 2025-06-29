package com.baitap.dao;

import com.baitap.model.CategoryModel;

import java.util.List;

public interface ICategoryDAO {
    List<CategoryModel> findAll();
    CategoryModel findOne(int id);

}
