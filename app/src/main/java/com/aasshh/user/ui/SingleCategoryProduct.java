package com.aasshh.user.ui;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
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
        //get All The sub categories here


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}