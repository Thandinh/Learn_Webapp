package com.webfruits.dao;

import com.webfruits.model.ProductModel;

import java.util.List;

public interface IProductDAO {
    List<ProductModel> findAll();
    ProductModel findOne(int productId);
    List<ProductModel> findProductsByCategoryId(int categoryId);
    List<ProductModel> getProducts(int from, int amount);
    int countProducts();
    List<ProductModel> searchProducts(String keyword, int offset, int limit);
    int countProductsByKeyword(String keyword);
    int insert(ProductModel product);
    boolean delete(int productId);
    boolean update(ProductModel product);
}
