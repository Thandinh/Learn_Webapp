package com.webfruits.model;

import java.sql.Timestamp;

public class ProductModel {
    private Integer id;
    private String name;
    private String description;
    private double price;
    private Double pricesale;
    private Integer quantity;
    private Integer view;
    private String thumbnail;
    private Integer categoryId;
    private Timestamp createdAt;

    // Constructors
    public ProductModel() {}

    public ProductModel(String name, String description, double price, Double pricesale, Integer quantity,
                        Integer view, String thumbnail, Integer categoryId, Timestamp createdAt) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.pricesale = pricesale;
        this.quantity = quantity;
        this.view = view;
        this.thumbnail = thumbnail;
        this.categoryId = categoryId;
        this.createdAt = createdAt;
    }

    // Getters & Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Double getPricesale() {
        return pricesale;
    }

    public void setPricesale(Double pricesale) {
        this.pricesale = pricesale;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    // Optional: toString()
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", pricesale=" + pricesale +
                ", quantity=" + quantity +
                ", view=" + view +
                ", thumbnail='" + thumbnail + '\'' +
                ", categoryId=" + categoryId +
                ", createdAt=" + createdAt +
                '}';
    }
}
