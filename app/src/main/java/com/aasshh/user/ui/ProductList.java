package com.aasshh.user.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aasshh.user.Model.ProductSubCategory;
import com.aasshh.user.Model.Products;
import com.aasshh.user.R;
import com.aasshh.user.adapter.ProductAdapter;
import com.aasshh.user.db.DatabaseHelper;
import com.aasshh.user.db.ProductDb;
import com.aasshh.user.listener.ProductListner;
import com.aasshh.user.services.RequestApi;
import com.aasshh.user.utils.AppExecutors;
import com.aasshh.user.utils.Constant;
import com.aasshh.user.utils.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductList extends AppCompatActivity implements ProductListner {
    ProductSubCategory subCategory = Constant.CurrentSubCategory;
    String TAG = ProductList.class.getSimpleName();
    RequestApi requestApi;
    String limit, skip, categoryID, subCategoryId, searchText;
    ArrayList<Products> lists = new ArrayList<>();
    RecyclerView productListView ;
    ProductAdapter adapter ;
    DatabaseHelper databaseHelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        Log.d(TAG, "onCreate: " + subCategory.getCategoryId());
        subCategoryId = subCategory.getId();

//        Log.d(TAG, "onCreate: " +url);
        requestApi = new RequestApi(this);
        productListView = findViewById(R.id.product_list);
        productListView.setLayoutManager(new LinearLayoutManager(this));
        productListView.setHasFixedSize(true);
        adapter = new ProductAdapter(this  , this , lists);
        productListView.setAdapter(adapter);
//        productListView.setHasFixedSize();
        databaseHelper = DatabaseHelper.getInstance(this);

        getProducts();


    }

    private void getProducts() {
        String url = Server.GET_PRODUCTS+"?subCategoryId="+subCategoryId;
        requestApi.getRequest(url, response -> {
            Log.d(TAG, "getProducts: " + response);
            try {
                JSONObject res = new JSONObject(response);
                int status = res.getInt("status");
                String message = res.getString("message");
                if (status != 200) {
                    Log.d(TAG, "getProducts: " + message);
                    return;
                }
                JSONArray records = res.getJSONArray("data");
                for (int i = 0; i < records.length(); i++) {
                    JSONObject single = records.getJSONObject(i);
                    String _id = single.getString("id");
                    String _categoryId = single.getString("categoryId");
                    String _subCategoryId = single.getString("subCategoryId");
                    String _name = single.getString("name");
                    JSONArray _description = single.getJSONArray("description");
                    String _thumbnilStr = single.getString("thumbnilStr");
                    String _tags = single.getString("tags");
                    String _discount = single.getString("discount");
                    String _discountStr = single.getString("discountStr");
                    String _varient = single.getString("varientId");
                    String _size = single.getString("size");
                    String _color = single.getString("color");
                    String _price = single.getString("price");
                    Products productList = new Products(_id, _categoryId,
                            _subCategoryId, _name, _thumbnilStr, _tags, _discount,
                            _discountStr, _varient, _size, _color, _price, _description);
                    lists.add(productList);

                }
                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        });
    }

    @Override
    public void onAddTocart(Products products) {
        ProductDb db = new ProductDb(Integer.parseInt( products.getId()) , products.getCategoryId() ,
                products.getSubcategoryId() , products.getName() , products.getImage() ,
                Integer.parseInt( products.getDiscount() ),
                products.getDiscountStr() ,Double.parseDouble( products.getPrice()) , products.getColor() , products.getSize() ,
                1);

//        databaseHelper.productDbDao().insert(db);
////        Log.d(TAG, "onAddTocart: " + products.getName() +"has been added");
//        AppExecutors.getsInstance().diskIO().execute(new Runnable() {
//            @Override
//            public void run() {
//                databaseHelper.productDbDao().insert(db);
//            }
//        });
        AppExecutors.getsInstance().diskIO().execute(() -> {
           ProductDb productDb=  databaseHelper.productDbDao().getProduct(Integer.parseInt(products.getId()));
//            Log.d(TAG, "run: " + productDb.getCategoryID());
            if (productDb==null){
                databaseHelper.productDbDao().insert(db);
            }else{
                ProductDb updateDb = new ProductDb(Integer.parseInt( products.getId()) , products.getCategoryId() ,
                        products.getSubcategoryId() , products.getName() , products.getImage() ,
                        Integer.parseInt( products.getDiscount() ),
                        products.getDiscountStr() ,Double.parseDouble( products.getPrice()) , products.getColor() , products.getSize() ,
                        productDb.getQty()+1);
                databaseHelper.productDbDao().update(updateDb);
            }
//            Toast.makeText(getApplicationContext() , products.getName() + "has been added to cart"
//            ,Toast.LENGTH_SHORT).show();
        });
        Toast.makeText(getApplicationContext() , products.getName()+"has been added to cart",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBuyClick(Products products) {

    }
}