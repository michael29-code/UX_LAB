package com.example.ux_lab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ItemPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_page);

        // Example of setting up click listeners for album items
        LinearLayout album1 = findViewById(R.id.album1);
        LinearLayout album2 = findViewById(R.id.album2);
        LinearLayout album3 = findViewById(R.id.album3);

        album1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlbumDetail("Album Name 1", "Artist 1", R.drawable.yoasobi1, "Album description 1");
            }
        });

        album2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlbumDetail("Album Name 2", "Artist 2", R.drawable.yoasobi2, "Album description 2");
            }
        });

        album3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlbumDetail("Album Name 3", "Artist 3", R.drawable.yoasobi3, "Album description 3");
            }
        });
    }

    private void openAlbumDetail(String albumName, String artistName, int imageResId, String description) {
        Intent intent = new Intent(ItemPageActivity.this, ItemDetailActivity.class);
        intent.putExtra("ALBUM_NAME", albumName);
        intent.putExtra("ARTIST_NAME", artistName);
        intent.putExtra("ALBUM_IMAGE", imageResId);
        intent.putExtra("ALBUM_DESCRIPTION", description);
        startActivity(intent);
    }
}
