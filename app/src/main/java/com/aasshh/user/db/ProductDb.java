package com.aasshh.user.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products")
public class ProductDb {
    @PrimaryKey(autoGenerate = true)
    private  int id ;
    @ColumnInfo(name = "categoryID")
    private  String categoryID ;
    @ColumnInfo(name = "subcategoryID")
    private  String subcategoryID ;

    @ColumnInfo(name = "name")
    private  String name ;
    @ColumnInfo(name = "image")
    private  String image ;
    @ColumnInfo(name = "discount")
    private  int discount ;
    @ColumnInfo(name = "discountStr")
    private String discountStr;
    @ColumnInfo (name = "price")
    private double price ;
    @ColumnInfo(name = "color")
    private String color ;
    @ColumnInfo(name = "size")
    private String size ;
    @ColumnInfo(name = "qty" )
    private int qty ;

    public ProductDb(int id, String categoryID, String subcategoryID, String name, String image, int discount, String discountStr, double price, String color, String size, int qty) {
        this.id = id;
        this.categoryID = categoryID;
        this.subcategoryID = subcategoryID;
        this.name = name;
        this.image = image;
        this.discount = discount;
        this.discountStr = discountStr;
        this.price = price;
        this.color = color;
        this.size = size;
        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getSubcategoryID() {
        return subcategoryID;
    }

    public void setSubcategoryID(String subcategoryID) {
        this.subcategoryID = subcategoryID;
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

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getDiscountStr() {
        return discountStr;
    }

    public void setDiscountStr(String discountStr) {
        this.discountStr = discountStr;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
