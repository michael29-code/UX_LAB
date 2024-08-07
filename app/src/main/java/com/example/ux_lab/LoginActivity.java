package com.example.ux_lab;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView textViewError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login); // Make sure this is the correct layout file

        editTextUsername = findViewById(R.id.editTextTextEmailAddress);
        editTextPassword = findViewById(R.id.editTextTextPassword);
        buttonLogin = findViewById(R.id.signUpButton);

        // Handle button touch events for opacity change
        buttonLogin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Change opacity to 100% when button is pressed
                        buttonLogin.setAlpha(1.0f);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        // Revert opacity to 80% when button is released or touch is canceled
                        buttonLogin.setAlpha(0.8f);
                        break;
                }
                return false; // Return false to allow the button to still perform its default action
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextUsername.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Username must be filled", Toast.LENGTH_SHORT).show();
                } else if (editTextPassword.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Password must be filled", Toast.LENGTH_SHORT).show();
                } else if (editTextUsername.length() < 5 || editTextUsername.length() > 10) {
                    editTextUsername.setError("Username length must be greater than 5 and less than 10");
                } else {
                    AlertDialog dialog = new AlertDialog.Builder(LoginActivity.this).create();
                    dialog.setTitle("Confirmation");
                    dialog.setMessage("Are you sure?");
                    dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String username = editTextUsername.getText().toString();
                            // Redirect to HomeActivity
                            Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
                            homeIntent.putExtra("USERNAME", username); // Pass the username
                            startActivity(homeIntent);

                        }
                    });
                    dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss(); // Dismiss the dialog
                        }
                    });
                    dialog.show(); // Show the dialog
                }
            }
        });
    }
}
