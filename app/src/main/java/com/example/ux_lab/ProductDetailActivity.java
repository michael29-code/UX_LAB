package com.example.ux_lab;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {

    private EditText setQuantity;
    private TextView tvSuccessMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ImageView ivDetailImage = findViewById(R.id.ivDetailImage);
        TextView tvDetailTitle = findViewById(R.id.tvDetailTitle);
        TextView tvArtist = findViewById(R.id.tvProductArtist);
        TextView tvDetailDescription = findViewById(R.id.tvDetailDescription);
        TextView tvDetailYear = findViewById(R.id.tvDetailYear);
        setQuantity = findViewById(R.id.setQuantity);
        tvSuccessMessage = findViewById(R.id.tvSuccessMessage);
        ImageButton cartButton = findViewById(R.id.cart_button);
        ImageButton backButton = findViewById(R.id.back_button);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        String artist = intent.getStringExtra("artist");
        int imageResId = intent.getIntExtra("imageResId", R.drawable.ic_launcher_background);
        String year = intent.getStringExtra("year");

        tvDetailTitle.setText(title);
        tvArtist.setText(artist);
        tvDetailDescription.setText(description);
        ivDetailImage.setImageResource(imageResId);
        tvDetailYear.setText(year);

        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateQuantityInput();
            }
        });

        String username = getIntent().getStringExtra("USERNAME");

        // Set OnClickListener for back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to ProductListActivity
//                Intent backIntent = new Intent(ProductDetailActivity.this, ProductListActivity.class).putExtra("USERNAME", username);
//                startActivity(backIntent);
                Intent resultIntent = new Intent();
                resultIntent.putExtra("USERNAME", username); // Ganti "key" dan "value" sesuai data yang ingin dikirim
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    private void validateQuantityInput() {
        String quantityStr = setQuantity.getText().toString();
        if (quantityStr.isEmpty()) {
            showErrorDialog("A number should be inputted");
        } else {
            int quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) {
                showErrorDialog("Please input a number greater than 0");
            } else {
                tvSuccessMessage.setVisibility(View.VISIBLE);
            }
        }
    }

    private void showErrorDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
