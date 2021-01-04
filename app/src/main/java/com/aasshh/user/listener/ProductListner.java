package com.aasshh.user.listener;

import com.aasshh.user.Model.Products;

public interface ProductListner {
    void onAddTocart(Products products) ;
    void  onBuyClick(Products products);
}
