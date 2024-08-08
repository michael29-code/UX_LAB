package com.example.ux_lab;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ItemPageActivity extends AppCompatActivity {
    private ImageView closeBtn, hamburgerMenu, imageSong;
    private FrameLayout sidebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_page);
        setupSidebarNavigation();

        sidebar = findViewById(R.id.sidebar);
        closeBtn = findViewById(R.id.closeBtn);
        hamburgerMenu = findViewById(R.id.hamburgerMenu);
//        imageSong = findViewById(R.id.songsId);

        // Example of setting up click listeners for album items
        LinearLayout album1 = findViewById(R.id.album1);
        LinearLayout album2 = findViewById(R.id.album2);
        LinearLayout album3 = findViewById(R.id.album3);

        // Set onClick listeners
        closeBtn.setOnClickListener(v -> toggleSidebar());
//        imageSong.setOnClickListener(v -> {
//            Intent intent = new Intent(ItemPageActivity.this, ProductDetailActivity.class);
//            startActivity(intent);
//        });
        hamburgerMenu.setOnClickListener(v -> toggleSidebar());

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
    private void toggleSidebar() {
        if (sidebar.getVisibility() == View.GONE) {
            sidebar.setVisibility(View.VISIBLE);
        } else {
            sidebar.setVisibility(View.GONE);
        }
    }
    private void setupSidebarNavigation() {
        String username = getIntent().getStringExtra("USERNAME");

        findViewById(R.id.navigation_home).setOnClickListener(v -> {
            Intent intent = new Intent(ItemPageActivity.this, HomeActivity.class);
            intent.putExtra("USERNAME", username);
            startActivity(intent);
            finish();

        });

        findViewById(R.id.navigation_about_us).setOnClickListener(v -> {
            Intent intent = new Intent(ItemPageActivity.this, TabLayoutActivity.class);
            intent.putExtra("USERNAME", username);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.nav_logout).setOnClickListener(v -> {
            Intent intent = new Intent(ItemPageActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Optionally finish the current activity
        });
    }

    private void openAlbumDetail(String albumName, String artistName, int imageResId, String description) {
        Intent intent = new Intent(ItemPageActivity.this, ProductDetailActivity.class);
        intent.putExtra("ALBUM_NAME", albumName);
        intent.putExtra("ARTIST_NAME", artistName);
        intent.putExtra("ALBUM_IMAGE", imageResId);
        intent.putExtra("ALBUM_DESCRIPTION", description);
        startActivity(intent);
    }
}
