package com.aasshh.user.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aasshh.user.Model.ProductSubCategory;
import com.aasshh.user.R;
import com.aasshh.user.listener.SubCategoryListener;
import com.aasshh.user.viewholder.SubcategoryHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SubcategoryAdapter extends RecyclerView.Adapter<SubcategoryHolder> {
    ArrayList<ProductSubCategory> list;
    Context context;
    SubCategoryListener subCategoryListener;
    String TAG = SubcategoryAdapter.class.getSimpleName();

    public SubcategoryAdapter(ArrayList<ProductSubCategory> list,
                              Context context, SubCategoryListener subCategoryListener) {
        this.list = list;
        this.context = context;
        this.subCategoryListener = subCategoryListener;
    }

    public SubcategoryAdapter(ArrayList<ProductSubCategory> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public SubcategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_sub_category, parent, false);
        return new SubcategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubcategoryHolder holder, int position) {
        ProductSubCategory subCategory = list.get(position);
        Picasso.get().load(subCategory.getImageStr())
                .error(R.drawable.logo_primary)
                .placeholder(R.drawable.logo_primary)
                .into(holder.subCategoryImage);

        holder.offer.setText(subCategory.getDiscountStr());
        holder.itemView.setOnClickListener(v -> {
            subCategoryListener.onClcik(subCategory);
        });

    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + list.size());
        return list.size();
    }
}
