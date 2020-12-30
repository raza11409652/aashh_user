package com.aasshh.user.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aasshh.user.Model.ProductCategory;
import com.aasshh.user.Model.ProductSubCategory;
import com.aasshh.user.R;
import com.aasshh.user.listener.CategoryListener;
import com.aasshh.user.listener.SubCategoryListener;
import com.aasshh.user.viewholder.CategoryViewHolder;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {
    ArrayList<ProductCategory> list;
    Context context;
    CategoryListener listener;
    SubCategoryListener subCategoryListener ;

    public CategoryAdapter(ArrayList<ProductCategory> list, Context context, CategoryListener listener, SubCategoryListener subCategoryListener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
        this.subCategoryListener = subCategoryListener;
    }

//    public CategoryAdapter(ArrayList<ProductCategory> list, Context context, CategoryListener listener) {
//        this.list = list;
//        this.context = context;
//        this.listener = listener;
//    }

//    public CategoryAdapter(ArrayList<ProductCategory> list, Context context) {
//        this.list = list;
//        this.context = context;
//    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_categories_view, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        ProductCategory category = list.get(position);
        holder.type.setText(category.getName());

        GridLayoutManager subcategoriesManager = new GridLayoutManager(this.context, 2);
        holder.subCategoriesHolder.setLayoutManager(subcategoriesManager);
        ArrayList<ProductSubCategory> subCategories = category.getList();
        SubcategoryAdapter adapter = new SubcategoryAdapter(subCategories, context , subCategoryListener);
        holder.subCategoriesHolder.setAdapter(adapter);

        holder.viewMore.setOnClickListener(v -> {
            listener.onViewMore(category);
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
