package com.aasshh.user.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aasshh.user.Model.Products;
import com.aasshh.user.R;
import com.aasshh.user.viewholder.ProductViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {
    Context context;
    ArrayList<Products> list;

    public ProductAdapter(Context context, ArrayList<Products> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Products products = list.get(position) ;
        holder.productName.setText(products.getName());
        holder.pricing.setText(products.getPrice());
        holder.description.setText(products.getDiscountStr());
        holder.tag.setText(products.getTags());
        Picasso.get().load(products.getImage())
                .error(R.drawable.logo_primary)
                .placeholder(R.drawable.logo_primary)
                .into(holder.productImage);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
