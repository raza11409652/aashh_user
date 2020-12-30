package com.aasshh.user.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aasshh.user.Model.ProductCategory;
import com.aasshh.user.Model.ProductSubCategory;
import com.aasshh.user.R;
import com.aasshh.user.adapter.SingleSubCategoryAdapter;
import com.aasshh.user.services.RequestApi;

import java.util.ArrayList;

public class SingleCategoryProduct extends AppCompatActivity {

    Toolbar toolbar;
    ProductCategory productCategory;
    RequestApi requestApi;
    String TAG = SingleCategoryProduct.class.getSimpleName();
    RecyclerView listHolder;
    SingleSubCategoryAdapter subcategoryAdapter;
    ArrayList<ProductSubCategory> productSubCategories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_category_product);
        toolbar = findViewById(R.id.toolbar);
        listHolder = findViewById(R.id.sub_categories_holder);
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

        requestApi = new RequestApi(this);

        getSubCategories();


    }

    private void getSubCategories() {
//        String url = Server.GET_PROD_CATEGORY + "?categoryID=" + productCategory.getId();
//        Log.d(TAG, "getSubCategories: " + url);
//        this.requestApi.getRequest(url, response -> {
//
//            Log.d(TAG, "getSubCategories: " + response);
//            try {
//                JSONObject object = new JSONObject(response);
//                int status = object.getInt("status");
//                String message = object.getString("message");
//                if (status != 200) {
//                    //Error
//                    return;
//                }
//
//                JSONArray data = object.getJSONArray("data");
//                if (data.length() < 1) {
//                    return;
//                }
//
//                for (int i = 0; i < data.length(); i++) {
////                    String _id = data.getString("id");
//                    JSONObject single = data.getJSONObject(i);
//                    String id = single.getString("id");
//                    String name = single.getString("name");
//                    String imageStr = single.getString("imageStr");
//                    String description = single.getString("description");
////                    int _status
//
//                }
//
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//        });

        int length = productCategory.getList().size();
        Log.d(TAG, "getSubCategories: " + length);
        if (length < 1) {
            Log.d(TAG, "getSubCategories: No sub categories found");
            //sub categories not found
            return;
        }

        productSubCategories = productCategory.getList();
        Log.d(TAG, "getSubCategories: " + productSubCategories.size());
        listHolder.setLayoutManager(new GridLayoutManager(this, 2));
        listHolder.setHasFixedSize(true);
        subcategoryAdapter = new SingleSubCategoryAdapter(productSubCategories, this);
        listHolder.setAdapter(subcategoryAdapter);
        subcategoryAdapter.notifyDataSetChanged();


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}