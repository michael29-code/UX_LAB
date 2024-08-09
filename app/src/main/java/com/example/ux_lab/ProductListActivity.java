package com.example.ux_lab;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private ImageView closeBtn, hamburgerMenu;
    private FrameLayout sidebar;
    private TextView sidebarUsernameTextView;
    private TextView welcomeTextView; // TextView for displaying the welcome message
    private BottomNavigationView bottomNavigationView; // BottomNavigationView for navigation

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        setupSidebarNavigation();

        sidebar = findViewById(R.id.sidebar);
        closeBtn = findViewById(R.id.closeBtn);
        hamburgerMenu = findViewById(R.id.hamburgerMenu);
        welcomeTextView = findViewById(R.id.welcomeMessage);
        sidebarUsernameTextView = findViewById(R.id.username_textview_all_items);
        bottomNavigationView = findViewById(R.id.bottom_navigation); // Initialize BottomNavigationView

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Populate product list
        productList = new ArrayList<>();
        productList.add(new Product("THE BOOK 1", "Yoasobi", "THE BOOK adalah album mini atau extended play (EP) debut serta rilis fisik pertama yang direkam oleh Yoasobi. Album mini ini dirilis pada tanggal 6 Januari 2021 melalui Sony Music Entertainment Japan, bertepatan dengan rilis singel ketujuh mereka yang berjudul 'Kaibutsu'. 'Encore' digunakan sebagai singel promosi dari album ini", R.drawable.yoasobi1, "2022"));
        productList.add(new Product("THE BOOK 2", "Yoasobi", "The Book 2 adalah EP kedua dalam bahasa Jepang (ketiga secara keseluruhan) oleh duo Jepang Yoasobi. Album ini dirilis pada tanggal 1 Desember 2021, melalui Sony Music Entertainment Japan, sebelas bulan setelah EP debut mereka, The Book (2021). EP ini terdiri dari delapan lagu, termasuk semua singel mereka yang dirilis pada tahun 2021, serta menampilkan lagu baru 'Moshi mo Inochi ga Egaketara'.", R.drawable.yoasobi2, "2023"));
        productList.add(new Product("THE BOOK 3", "Yoasobi", "The Book 3 adalah EP ketiga dalam bahasa Jepang (keenam secara keseluruhan) oleh duo Jepang Yoasobi. Album ini dirilis pada tanggal 4 Oktober 2023, melalui Sony Music Entertainment Japan, satu tahun sepuluh bulan setelah EP kedua mereka, The Book 2 (2021). Melanjutkan konsep 'reading CD' seperti album-album sebelumnya, EP ini mencakup genre electropop dan synth-pop, terdiri dari sepuluh lagu yang semuanya ditulis dan diproduksi oleh salah satu anggota duo tersebut, Ayase.", R.drawable.yoasobi3, "2024"));

        productAdapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(productAdapter);

        // Set username from Intent
        String username = getIntent().getStringExtra("USERNAME");
        if (username != null) {
            sidebarUsernameTextView.setText(username);
        }

        // Set onClick listeners
        closeBtn.setOnClickListener(v -> toggleSidebar());
        hamburgerMenu.setOnClickListener(v -> toggleSidebar());

        // Handle bottom navigation item selection
        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.navigation_all_items);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId(); /* obtain the selected item ID from your source */
                if (itemId == R.id.navigation_home) {
                    startActivity(new Intent(ProductListActivity.this, HomeActivity.class).putExtra("USERNAME", username));
                } else if (itemId == R.id.navigation_all_items) {
                    startActivity(new Intent(ProductListActivity.this, ProductListActivity.class).putExtra("USERNAME", username));
                } else if (itemId == R.id.navigation_about_us) {
                    startActivity(new Intent(ProductListActivity.this, TabLayoutActivity.class).putExtra("USERNAME", username));
                }
                else {
                    startActivity(new Intent(ProductListActivity.this, HomeActivity.class).putExtra("USERNAME", username));
                }
                return false;
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
            Intent intent = new Intent(ProductListActivity.this, HomeActivity.class);
            intent.putExtra("USERNAME", username);
            startActivity(intent);
        });

        findViewById(R.id.navigation_about_us).setOnClickListener(v -> {
            Intent intent = new Intent(ProductListActivity.this, TabLayoutActivity.class);
            intent.putExtra("USERNAME", username);
            startActivity(intent);
        });

        findViewById(R.id.nav_logout).setOnClickListener(v -> {
            Intent intent = new Intent(ProductListActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Optionally finish the current activity
        });
    }

    private void openAlbumDetail(String albumName, String artistName, int imageResId, String description) {
        Intent intent = new Intent(ProductListActivity.this, ProductDetailActivity.class);
        intent.putExtra("ALBUM_NAME", albumName);
        intent.putExtra("ARTIST_NAME", artistName);
        intent.putExtra("ALBUM_IMAGE", imageResId);
        intent.putExtra("ALBUM_DESCRIPTION", description);
        startActivity(intent);
    }
}
