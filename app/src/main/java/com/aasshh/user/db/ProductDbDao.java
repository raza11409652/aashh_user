package com.aasshh.user.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDbDao {
    @Query("SELECT * from products")
    List<ProductDb>getProductlist();
    @Insert()
    void  insert(ProductDb db);
    @Delete
    void delete(ProductDb db);
    @Update
    void  update(ProductDb db);
    @Query("SELECT * from products where id=:id")
    ProductDb getProduct(int id);


}
