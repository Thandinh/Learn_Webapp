package com.baitap.dao;

import com.baitap.model.ProductModel;

import java.util.List;

public interface IProductDAO {
    List<ProductModel> findAll();
    ProductModel findOne(int productId);
    List<ProductModel> findProductsByCategoryId(int categoryId);
    List<ProductModel> getProducts(int from, int amount);
    int countProducts();
    List<ProductModel> searchProducts(String keyword, int offset, int limit);
    int countProductsByKeyword(String keyword);
}
