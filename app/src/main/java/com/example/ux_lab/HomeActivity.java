package com.example.ux_lab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class HomeActivity extends AppCompatActivity {
    private LinearLayout sidebar;
    private ImageView closeBtn, hamburgerMenu, imageSong;
    private Handler handler = new Handler();
    private Runnable runnable;
    private TextView welcomeTextView; // TextView for displaying the welcome message
    private TextView sidebarUsernameTextView; // TextView for displaying the username in the sidebar
    private ViewFlipper carousel;
    private ViewPager viewPager;
    private int[] images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        // Initialize views
        sidebar = findViewById(R.id.sidebar);
        closeBtn = findViewById(R.id.closeBtn);
        hamburgerMenu = findViewById(R.id.hamburgerMenu);
        welcomeTextView = findViewById(R.id.welcome_message); // Make sure this TextView exists in your layout
        sidebarUsernameTextView = findViewById(R.id.username_textview); // TextView in the sidebar
        imageSong = findViewById(R.id.songsId);
        carousel = findViewById(R.id.carousel);

        // Retrieve the username from the Intent
        String username = getIntent().getStringExtra("USERNAME");
        if (username != null) {
            welcomeTextView.setText("Welcome, " + username); // Set the welcome message
            sidebarUsernameTextView.setText(username); // Set the username in the sidebar
        }

        // Set onClick listeners
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSidebar();
            }
        });
        imageSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ItemDetailActivity.class);
                startActivity(intent);
                finish();
            }
        });
        hamburgerMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSidebar();
            }
        });

        ImageButton buttonLeft = findViewById(R.id.buttonLeft);
        ImageButton buttonRight = findViewById(R.id.buttonRight);

        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousPage(v);
            }
        });

        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextPage(v);
            }
        });

        // Initialize Carousel
        images = new int[] {
                R.drawable.jumbotron1,
                R.drawable.jumbotron2,
                R.drawable.jumbotron3
        };

        for (int image : images) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(image);
            carousel.addView(imageView);
        }

        carousel.setFlipInterval(3000); // 5 seconds
        carousel.setAutoStart(true);
        carousel.setInAnimation(this, R.anim.slide_out_left);
        carousel.setOutAnimation(this, R.anim.slide_in_right);


        // Setup sidebar navigation
        setupSidebarNavigation();
    }

    private void toggleSidebar() {
        if (sidebar.getVisibility() == View.GONE) {
            sidebar.setVisibility(View.VISIBLE);
        } else {
            sidebar.setVisibility(View.GONE);
        }
    }

    private void setupSidebarNavigation() {
        findViewById(R.id.all_items).setOnClickListener(v -> {
            // Navigate to ItemDetailActivity
            Intent intent = new Intent(HomeActivity.this, ItemDetailActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.about_us).setOnClickListener(v -> {
            // Navigate to AboutUsActivity
            Intent intent = new Intent(HomeActivity.this, AboutUsFragment.class);
            startActivity(intent);
        });

        findViewById(R.id.logout).setOnClickListener(v -> {
            // Navigate to LoginActivity
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
