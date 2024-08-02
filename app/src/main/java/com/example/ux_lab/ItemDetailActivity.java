package com.example.ux_lab;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ItemDetailActivity extends AppCompatActivity {

    private EditText quantityInput;
    private ImageView albumCover, cartButton, backButton;
    private TextView albumTitle, artistName, albumDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);

        albumCover = findViewById(R.id.album_cover);
        cartButton = findViewById(R.id.cart_button);
        backButton = findViewById(R.id.back_button);
        quantityInput = findViewById(R.id.quantity_input);
        albumTitle = findViewById(R.id.album_title);
        artistName = findViewById(R.id.artist_name);
        albumDescription = findViewById(R.id.album_description);

        // Retrieve the album details from the intent
        Intent intent = getIntent();
        String albumName = intent.getStringExtra("ALBUM_NAME");
        String artistNameValue = intent.getStringExtra("ARTIST_NAME");
        int albumImage = intent.getIntExtra("ALBUM_IMAGE", R.drawable.yoasobi1);
        String description = intent.getStringExtra("ALBUM_DESCRIPTION");

        // Set the album details to the views
        albumTitle.setText(albumName);
        artistName.setText(artistNameValue);
        albumCover.setImageResource(albumImage);
        albumDescription.setText(description);

        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quantityText = quantityInput.getText().toString();
                if (TextUtils.isEmpty(quantityText) || Integer.parseInt(quantityText) <= 0) {
                    AlertDialog dialog = new AlertDialog.Builder(ItemDetailActivity.this).create();
                    dialog.setTitle("Error");
                    dialog.setMessage("Quantity must be filled and more than zero");
                    dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                } else {
                    // Handle adding to cart
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemDetailActivity.this, ItemPageActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
