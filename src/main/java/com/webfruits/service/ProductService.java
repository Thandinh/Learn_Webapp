package com.webfruits.service;

import com.webfruits.dao.IProductDAO;
import com.webfruits.dao.ProductDAO;
import com.webfruits.model.ProductModel;

import java.util.List;

public class ProductService implements IProductService{
    private IProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    @Override
    public List<ProductModel> findAll() {
        return productDAO.findAll();
    }

    @Override
    public ProductModel findOne(int productId) {
        return productDAO.findOne(productId);
    }

    @Override
    public List<ProductModel> findProductsByCategoryId(int categoryId) {
        return productDAO.findProductsByCategoryId(categoryId);
    }

    @Override
    public List<ProductModel> getProducts(int from, int amount) {
        return productDAO.getProducts(from, amount);
    }

    @Override
    public int countProducts() {
        return productDAO.countProducts();
    }

    @Override
    public List<ProductModel> searchProducts(String keyword, int offset, int limit) {
        return productDAO.searchProducts(keyword, offset, limit);
    }

    @Override
    public int countProductsByKeyword(String keyword) {
        return productDAO.countProductsByKeyword(keyword);
    }

    @Override
    public int insert(ProductModel product) {
        return productDAO.insert(product);
    }

    @Override
    public boolean delete(int productId) {
        return productDAO.delete(productId);
    }

    @Override
    public boolean update(ProductModel product) {
        return productDAO.update(product);
    }
}
