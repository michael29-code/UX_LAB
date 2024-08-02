package com.example.ux_lab;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ImageView ivDetailImage = findViewById(R.id.ivDetailImage);
        TextView tvDetailTitle = findViewById(R.id.tvDetailTitle);
        TextView tvArtist = findViewById(R.id.tvProductArtist);
        TextView tvDetailDescription = findViewById(R.id.tvDetailDescription);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        String artist = intent.getStringExtra("artist");
        int imageResId = intent.getIntExtra("imageResId", R.drawable.ic_launcher_background);

        tvDetailTitle.setText(title);
        tvArtist.setText(artist);
        tvDetailDescription.setText(description);
        ivDetailImage.setImageResource(imageResId);
    }
}
