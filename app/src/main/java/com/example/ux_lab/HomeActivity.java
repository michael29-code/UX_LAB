package com.example.ux_lab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity{
    private RecyclerView recyclerView;
    private ProductAdapter2 productAdapter;
    private List<Product> productList;
    private FrameLayout sidebar;
    private ImageView closeBtn, hamburgerMenu, imageSong;
    private Handler handler = new Handler();
    private Runnable runnable;
    private TextView welcomeTextView; // TextView for displaying the welcome message
    private TextView sidebarUsernameTextView; // TextView for displaying the username in the sidebar
    private ViewFlipper carousel;

    private int[] images;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        setupSidebarNavigation();

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Initialize product list
        productList = new ArrayList<>();
        productList.add(new Product("THE BOOK 1", "Yoasobi", "Description of THE BOOK 1", R.drawable.yoasobi1, "2022"));
        productList.add(new Product("THE BOOK 2", "Yoasobi", "Description of THE BOOK 2", R.drawable.yoasobi2, "2023"));
        productList.add(new Product("THE BOOK 3", "Yoasobi", "Description of THE BOOK 3", R.drawable.yoasobi3, "2024"));

        // Set the adapter
        productAdapter = new ProductAdapter2(this, productList);
        recyclerView.setAdapter(productAdapter);

        // Initialize views
        sidebar = findViewById(R.id.sidebar);
        closeBtn = findViewById(R.id.closeBtn);
        hamburgerMenu = findViewById(R.id.hamburgerMenu);
        welcomeTextView = findViewById(R.id.welcome_message);
        sidebarUsernameTextView = findViewById(R.id.username_textview);
        imageSong = findViewById(R.id.songsId);
        carousel = findViewById(R.id.carousel);

        // Retrieve the username from the Intent
        String username = getIntent().getStringExtra("USERNAME");
        if (username != null) {
            welcomeTextView.setText("Welcome, " + username);
            sidebarUsernameTextView.setText(username);
        }

        // Set onClick listeners
        closeBtn.setOnClickListener(v -> toggleSidebar());
        imageSong.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ProductDetailActivity.class);
            startActivity(intent);
        });
        hamburgerMenu.setOnClickListener(v -> toggleSidebar());

        ImageButton buttonLeft = findViewById(R.id.buttonLeft);
        ImageButton buttonRight = findViewById(R.id.buttonRight);

        buttonLeft.setOnClickListener(v -> previousPage(v));
        buttonRight.setOnClickListener(v -> nextPage(v));

        // Initialize Carousel
        images = new int[]{
                R.drawable.jumbotron1,
                R.drawable.jumbotron2,
                R.drawable.jumbotron3
        };

        for (int image : images) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(image);
            carousel.addView(imageView);
        }

        carousel.setFlipInterval(3000); // 3 seconds
        carousel.setAutoStart(true);
        carousel.setInAnimation(this, R.anim.slide_out_left);
        carousel.setOutAnimation(this, R.anim.slide_in_right);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId(); /* obtain the selected item ID from your source */
                if (itemId == R.id.navigation_home) {
                    startActivity(new Intent(HomeActivity.this, HomeActivity.class).putExtra("USERNAME", username));
                } else if (itemId == R.id.navigation_all_items) {
                    startActivity(new Intent(HomeActivity.this, ItemPageActivity.class).putExtra("USERNAME", username));
                } else if (itemId == R.id.navigation_about_us) {
                    startActivity(new Intent(HomeActivity.this, TabLayoutActivity.class).putExtra("USERNAME", username));
                } else {
                    startActivity(new Intent(HomeActivity.this, HomeActivity.class).putExtra("USERNAME", username));
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

        findViewById(R.id.navigation_all_items).setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ItemPageActivity.class);
            intent.putExtra("USERNAME", username);
            startActivity(intent);

        });

        findViewById(R.id.navigation_about_us).setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, TabLayoutActivity.class);
            intent.putExtra("USERNAME", username);
            startActivity(intent);
        });

        findViewById(R.id.nav_logout).setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Optionally finish the current activity
        });
    }

    public void previousPage(View view) {
        carousel.showPrevious();
    }

    public void nextPage(View view) {
        carousel.showNext();
    }
}
