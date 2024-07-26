package com.example.ux_lab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    private LinearLayout sidebar;
    private ImageView closeBtn, hamburgerMenu, imageSong;
    private ViewPager viewPager;
    private int[] images = {R.drawable.jumbotron1, R.drawable.jumbotron2, R.drawable.jumbotron3};
    private Handler handler = new Handler();
    private Runnable runnable;
    private int delay = 3000; // 3000 milliseconds delay
    private TextView welcomeTextView; // TextView for displaying the welcome message
    private TextView sidebarUsernameTextView; // TextView for displaying the username in the sidebar

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

        // Initialize ViewPager
        viewPager = findViewById(R.id.viewPager);
        ImagePagerAdapter adapter = new ImagePagerAdapter(this, images);
        viewPager.setAdapter(adapter);

        // Setup automatic sliding
        runnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPager.getCurrentItem();
                int nextItem = currentItem == images.length - 1 ? 0 : currentItem + 1;
                viewPager.setCurrentItem(nextItem, true);
                handler.postDelayed(this, delay);
            }
        };
        handler.postDelayed(runnable, delay);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable); // Stop the automatic sliding when the activity is destroyed
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
            Intent intent = new Intent(HomeActivity.this, AboutUsActivity.class);
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
        int currentItem = viewPager.getCurrentItem();
        if (currentItem > 0) {
            viewPager.setCurrentItem(currentItem - 1);
        }
    }

    public void nextPage(View view) {
        int currentItem = viewPager.getCurrentItem();
        if (currentItem < images.length - 1) {
            viewPager.setCurrentItem(currentItem + 1);
        }
    }
}
