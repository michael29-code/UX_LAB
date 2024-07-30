package com.example.ux_lab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class AboutUsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);

        Button btnContactUs = findViewById(R.id.btnContactUs);
        btnContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Contact Us activity
                Intent intent = new Intent(AboutUsActivity.this, ContactUsActivity.class);
                startActivity(intent);
            }
        });
    }
}