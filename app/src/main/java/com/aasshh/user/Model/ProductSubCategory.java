package com.aasshh.user.Model;

public class ProductSubCategory {
    String id ,categoryId ,  name , description , imageStr , discountStr ,createdAt ,updatedAt ;
    int status  ;
    //need to modify discount value ;


    public ProductSubCategory(String id, String categoryId, String name, String description,
                              String imageStr, String discountStr, String createdAt, String updatedAt, int status) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.imageStr = imageStr;
        this.discountStr = discountStr;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public ProductSubCategory(String id, String name, String description, String imageStr, String discountStr, String createdAt, String updatedAt, int status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageStr = imageStr;
        this.discountStr = discountStr;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageStr() {
        return imageStr;
    }

    public void setImageStr(String imageStr) {
        this.imageStr = imageStr;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
