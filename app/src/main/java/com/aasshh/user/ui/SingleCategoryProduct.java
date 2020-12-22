package com.aasshh.user.ui;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.aasshh.user.Model.ProductCategory;
import com.aasshh.user.R;

public class SingleCategoryProduct extends AppCompatActivity {

    Toolbar toolbar;
    ProductCategory productCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_category_product);
        toolbar = findViewById(R.id.toolbar);
        try {
            setSupportActionBar(toolbar);
            ActionBar actionbar = getSupportActionBar();
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeButtonEnabled(true);
            productCategory = (ProductCategory) getIntent().getSerializableExtra("category");
            setTitle(productCategory.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}