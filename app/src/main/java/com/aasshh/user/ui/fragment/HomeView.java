package com.aasshh.user.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aasshh.user.Model.ProductCategory;
import com.aasshh.user.Model.ProductSubCategory;
import com.aasshh.user.R;
import com.aasshh.user.adapter.CategoryAdapter;
import com.aasshh.user.listener.CategoryListener;
import com.aasshh.user.services.RequestApi;
import com.aasshh.user.ui.SingleCategoryProduct;
import com.aasshh.user.utils.Server;
import com.aasshh.user.utils.StringHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeView extends Fragment implements CategoryListener {

    EditText searchBox;
    RequestApi requestApi;
    String TAG = HomeView.class.getSimpleName();
    RecyclerView categoriesHolder;
    ArrayList<ProductCategory> list = new ArrayList<>();
    LinearLayoutManager categoriesLayoutManager;
    CategoryAdapter adapter;

    public HomeView() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestApi = new RequestApi(getContext());
        categoriesLayoutManager = new LinearLayoutManager(getContext());

        adapter = new CategoryAdapter(list, getContext(), this);

        getAllProductCategories();
    }

    //Will fetch all the list from the server
    private void getAllProductCategories() {
        requestApi.getRequest(Server.GET_PROD_CATEGORY, response -> {
            Log.d(TAG, "getAllProductCategories: " + response);
            try {
                JSONObject object = new JSONObject(response);
                int status = object.getInt("status");
                if (status != 200) {
                    //Error
                } else {
                    JSONArray data = object.getJSONArray("data");
                    if (data.length() < 1) {
                        Log.d(TAG, "getAllProductCategories: No category found");
                        return;

                    }
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject single = data.getJSONObject(i);
                        String _id = single.getString("id");

                        String _name = single.getString("name");
                        String _image = single.getString("imageStr");
                        String _description = single.getString("description");
                        int _status = single.getInt("status");
                        String _discountVal = single.getString("discount");

                        double _discount = Double.parseDouble(_discountVal == null ||
                                StringHandler.isEmpty(_discountVal) ? String.valueOf(0) : _discountVal);
                        String _discountStr = single.getString("discountStr");
                        String _createdAt = single.getString("createdAt");
                        String _updatedAt = single.getString("updatedAt");
                        JSONArray subCat = single.getJSONArray("subCategories");
                        ArrayList<ProductSubCategory> subCategories = new ArrayList<>();
//                        Log.d(TAG, "getAllProductCategories: " + subCat);
                        for (int j = 0; j < subCat.length(); j++) {
                            JSONObject singleSubCat = subCat.getJSONObject(j);
                            String _subId = singleSubCat.getString("id");
                            String _subCategoryID = singleSubCat.getString("categoryID");
                            String _subName = singleSubCat.getString("name");
                            String _subDescription = singleSubCat.getString("description");
                            String _subImage = singleSubCat.getString("imageStr");
                            int _subStatus = singleSubCat.getInt("status");
                            String _subDiscountStr = singleSubCat.getString("discountStr");
                            String _subCreatedAt = singleSubCat.getString("createdAt");
                            String _subUpdatedAt = singleSubCat.getString("updatedAt");
                            ProductSubCategory productSubCategory = new ProductSubCategory(
                                    _subId, _subCategoryID, _subName, _subDescription, _subImage,
                                    _subDiscountStr, _subCreatedAt, _subUpdatedAt, _subStatus
                            );
                            subCategories.add(productSubCategory);
                        }

                        ProductCategory productCategory = new ProductCategory(
                                _id, _name, _image, _description, _discountStr, _createdAt, _updatedAt,
                                _status, _discount, subCategories);
                        list.add(productCategory);

                    }

                    adapter.notifyDataSetChanged();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchBox = view.findViewById(R.id.search_box);
        categoriesHolder = view.findViewById(R.id.categories_holder);
        categoriesHolder.setHasFixedSize(true);
        categoriesHolder.setLayoutManager(categoriesLayoutManager);
        categoriesHolder.setAdapter(adapter);
        categoriesHolder.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastItemIndex = categoriesLayoutManager.findLastVisibleItemPosition();
                Log.d(TAG, "onScrollStateChanged: " + lastItemIndex);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_view, container, false);
    }


    @Override
    public void onViewMore(ProductCategory category) {
//        Log.d(TAG, "onViewMore: " + category.getName());
        Intent singleCategoricalView = new Intent(getContext(), SingleCategoryProduct.class);
        singleCategoricalView.putExtra("category", category);
        startActivity(singleCategoricalView);

    }
}