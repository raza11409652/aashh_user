package com.aasshh.user.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aasshh.user.R;

public class SubcategoryHolder extends RecyclerView.ViewHolder {
    public ImageView subCategoryImage;
    public TextView name, offer, description;

    public SubcategoryHolder(@NonNull View itemView) {
        super(itemView);
        subCategoryImage = itemView.findViewById(R.id.image);
        name = itemView.findViewById(R.id.name);
        offer = itemView.findViewById(R.id.offer);
        description  =itemView.findViewById(R.id.description);


    }
}
