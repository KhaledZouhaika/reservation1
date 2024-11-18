package com.example.reservation;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MarketActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private List<DentistItem> dentistItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Customize system UI for immersive layout
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        // Initialize RecyclerView and items list
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Populate dentist items with images
        dentistItems = new ArrayList<>();
        dentistItems.add(new DentistItem("Dental Drill", 120.00, R.drawable.drill));
        dentistItems.add(new DentistItem("Dental Mirror", 15.00, R.drawable.dental_mirror));
        dentistItems.add(new DentistItem("Tooth Extractor", 35.50, R.drawable.tooth_extractor));
        dentistItems.add(new DentistItem("Scaler", 22.75, R.drawable.scaler));

        itemAdapter = new ItemAdapter(this, dentistItems);
        recyclerView.setAdapter(itemAdapter);
    }
}

