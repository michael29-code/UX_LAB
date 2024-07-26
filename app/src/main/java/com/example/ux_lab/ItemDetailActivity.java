package com.example.ux_lab;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ItemDetailActivity extends AppCompatActivity {

    private EditText quantityInput;
    private ImageButton cartButton;

    private static final String TAG = "ItemDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);

        quantityInput = findViewById(R.id.quantity_input);
        cartButton = findViewById(R.id.cart_button);

        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Cart button clicked");
                String quantityText = quantityInput.getText().toString();
                if (TextUtils.isEmpty(quantityText)) {
                    Log.d(TAG, "Quantity is empty");
                    showInvalidInputDialog();
                    return;
                }

                try {
                    int quantity = Integer.parseInt(quantityText);

                    if (quantity <= 0) {
                        Log.d(TAG, "Quantity is less than or equal to 0");
                        showInvalidInputDialog();
                    } else {
                        addToCart(quantity);
                    }
                } catch (NumberFormatException e) {
                    Log.d(TAG, "NumberFormatException: " + e.getMessage());
                    showInvalidInputDialog();
                }
            }
        });
    }

    private void showInvalidInputDialog() {
        Log.d(TAG, "Showing invalid input dialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Invalid input!");
        builder.setMessage("Input must be a number greater than zero");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing
            }
        });
        builder.show();
    }

    private void addToCart(int quantity) {
        Log.d(TAG, "Adding " + quantity + " items to cart");
        // Implement the logic to add the item to the shopping cart
        // For example, you can start a new activity or update a cart database
        // Here we just display a simple message for demonstration
        Toast.makeText(this, "Added " + quantity + " items to your cart.", Toast.LENGTH_SHORT).show();
    }
}
