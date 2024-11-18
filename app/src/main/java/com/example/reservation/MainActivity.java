package com.example.reservation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnLogout;
    private Button marketButton;
    private Button resButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        resButton = findViewById(R.id.resButton);
        btnLogout = findViewById(R.id.btnLogout);
        marketButton = findViewById(R.id.marketButton);
        // Handle logout
        btnLogout.setOnClickListener(view -> {
            // Clear session and redirect to login screen
            SharedPreferences prefs = getSharedPreferences("userPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("isLoggedIn", false);
            editor.apply();

            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish(); // End MainActivity
        });
        marketButton.setOnClickListener(view -> {
            // Navigate directly to MarketActivity
            Intent intent = new Intent(MainActivity.this, MarketActivity.class);
            startActivity(intent);
        });
        resButton.setOnClickListener(view -> {
            // Navigate directly to MarketActivity
            Intent intent = new Intent(MainActivity.this, ReservationActivity.class);
            startActivity(intent);
        });
    }
    
}
