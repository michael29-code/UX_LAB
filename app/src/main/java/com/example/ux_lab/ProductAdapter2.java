package com.example.ux_lab;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductAdapter2 extends RecyclerView.Adapter<ProductAdapter2.ProductViewHolder> {

    private final List<Product> productList;
    private final Context context;

    public ProductAdapter2(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item_2, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.nameTextView.setText(product.getName());
        holder.artistTextView.setText(product.getArtist());
        holder.imageView.setImageResource(product.getImageResId());
        holder.descriptionTextView.setText(product.getDescription());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("title", product.getName());
            intent.putExtra("artist", product.getArtist());
            intent.putExtra("description", product.getDescription());
            intent.putExtra("year", product.getYear());
            intent.putExtra("imageResId", product.getImageResId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, artistTextView, descriptionTextView;
        ImageView imageView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.product_name);
            artistTextView = itemView.findViewById(R.id.product_artist);
            imageView = itemView.findViewById(R.id.product_image);
            descriptionTextView = itemView.findViewById(R.id.product_description);
        }
    }
}
