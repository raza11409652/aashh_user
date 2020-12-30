package com.aasshh.user.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aasshh.user.Model.ProductSubCategory;
import com.aasshh.user.R;
import com.aasshh.user.viewholder.SubcategoryHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SingleSubCategoryAdapter extends RecyclerView.Adapter<SubcategoryHolder> {
    ArrayList<ProductSubCategory> list;
    Context context;

    public SingleSubCategoryAdapter(ArrayList<ProductSubCategory> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public SubcategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_sub_category_black, parent, false);

        return new SubcategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubcategoryHolder holder, int position) {
        ProductSubCategory category = list.get(position);
        String image = category.getImageStr();
        Picasso.get().load(image).error(R.drawable.logo_primary)
                .placeholder(R.drawable.logo_primary).into(holder.subCategoryImage);
        holder.offer.setText(category.getDiscountStr());
        holder.name.setText(category.getName());
        holder.description.setText(category.getDescription());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
