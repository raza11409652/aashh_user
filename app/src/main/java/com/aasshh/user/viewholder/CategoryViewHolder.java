package com.aasshh.user.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aasshh.user.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    public TextView type;
    public Button viewMore;
    public RecyclerView subCategoriesHolder;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        type = itemView.findViewById(R.id.types);
        viewMore = itemView.findViewById(R.id.view_all_btn);
        subCategoriesHolder = itemView.findViewById(R.id.sub_categories_holder);
        subCategoriesHolder.setHasFixedSize(true);


    }
}
