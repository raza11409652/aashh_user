package com.aasshh.user.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aasshh.user.R;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    public ImageView productImage   ;
    public TextView productName  ,pricing , description  ,tag ;
    public Button addToCart , buyNow ;
    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        productImage = itemView.findViewById(R.id.image_view);
        productName = itemView.findViewById(R.id.product_name);
        pricing = itemView.findViewById(R.id.pricing);
        description  =itemView.findViewById(R.id.description);
        tag  =itemView.findViewById(R.id.tag);
        addToCart  =itemView.findViewById(R.id.add_cart) ;
        buyNow  = itemView.findViewById(R.id.buy_now);

    }
}
