package com.example.ux_lab;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public abstract class BaseActivity extends AppCompatActivity {

    protected BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base); // Common layout with BottomNavigationView

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.navigation_home) {
                    startActivity(new Intent(BaseActivity.this, HomeActivity.class));
                    return true;
                } else if (id == R.id.navigation_all_items) {
                    startActivity(new Intent(BaseActivity.this, ProductListActivity.class));
                    return true;
                } else if (id == R.id.navigation_about_us) {
                    startActivity(new Intent(BaseActivity.this, TabLayoutActivity.class));
                    return true;
                }
                return false;
            }
        });

        // Inflate the specific layout into the content_frame
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(getLayoutResourceId(), (ViewGroup) findViewById(R.id.content_frame), true);
    }

    protected abstract int getLayoutResourceId();
}
