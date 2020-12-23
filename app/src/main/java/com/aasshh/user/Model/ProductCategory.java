package com.aasshh.user.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductCategory implements Serializable {
    String id, name, image, description, discountStr, createdAt, updatedAt;
    int status;
    double discount;
    ArrayList<ProductSubCategory> list;

    public ProductCategory() {
    }

    public ProductCategory(String id, String name, String image, String description,
                           String discountStr, String createdAt, String updatedAt,
                           int status, double discount, ArrayList<ProductSubCategory> list) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.discountStr = discountStr;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.discount = discount;
        this.list = list;
    }

    public ProductCategory(String id, String name, String image, String description,
                           String discountStr, String createdAt, String updatedAt,
                           int status, double discount) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.discountStr = discountStr;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.discount = discount;
    }

    public ArrayList<ProductSubCategory> getList() {
        return list;
    }

    public void setList(ArrayList<ProductSubCategory> list) {
        this.list = list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscountStr() {
        return discountStr;
    }

    public void setDiscountStr(String discountStr) {
        this.discountStr = discountStr;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
