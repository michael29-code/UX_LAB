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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class TabLayoutActivity extends AppCompatActivity {

    private ImageView closeBtn, hamburgerMenu;
    private FrameLayout sidebar;
    private TextView sidebarUsernameTextView; // TextView for displaying the username in the sidebar
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablayout);

        sidebar = findViewById(R.id.sidebar);
        closeBtn = findViewById(R.id.closeBtn);
        hamburgerMenu = findViewById(R.id.hamburgerMenu);
        closeBtn.setOnClickListener(v -> toggleSidebar());
        hamburgerMenu.setOnClickListener(v -> toggleSidebar());
        getLayoutInflater().inflate(R.layout.tablayout, findViewById(R.id.content_frame));
        sidebarUsernameTextView = findViewById(R.id.username_textview_all_items);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);
        setupSidebarNavigation();

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        // Retrieve the username from the Intent
        String username = getIntent().getStringExtra("USERNAME");
        if (username != null) {
            sidebarUsernameTextView.setText(username);
        }
        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.navigation_about_us);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId(); /* obtain the selected item ID from your source */
                if (itemId == R.id.navigation_home) {
                    startActivity(new Intent(TabLayoutActivity.this, HomeActivity.class).putExtra("USERNAME", username));
                } else if (itemId == R.id.navigation_all_items) {
                    startActivity(new Intent(TabLayoutActivity.this, ItemPageActivity.class).putExtra("USERNAME", username));
                } else if (itemId == R.id.navigation_about_us) {
                    startActivity(new Intent(TabLayoutActivity.this, TabLayoutActivity.class).putExtra("USERNAME", username));
                } else {
                    startActivity(new Intent(TabLayoutActivity.this, HomeActivity.class).putExtra("USERNAME", username));
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
            Intent intent = new Intent(TabLayoutActivity.this, HomeActivity.class);
            intent.putExtra("USERNAME", username);
            startActivity(intent);
            finish();

        });

        findViewById(R.id.navigation_all_items).setOnClickListener(v -> {
            Intent intent = new Intent(TabLayoutActivity.this, ItemPageActivity.class);
            intent.putExtra("USERNAME", username);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.nav_logout).setOnClickListener(v -> {
            Intent intent = new Intent(TabLayoutActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Optionally finish the current activity
        });
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new AboutUsFragment();
                case 1:
                    return new ContactUsFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2; // Number of tabs
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "About Us";
                case 1:
                    return "Contact Us";
                default:
                    return null;
            }
        }
    }
}
