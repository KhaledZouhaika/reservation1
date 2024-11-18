package com.example.reservation;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class ReservationActivity extends AppCompatActivity {

    private EditText nameEditText, phoneEditText, userIdEditText;
    private Button dateButton, timeButton, reserveButton;
    private String selectedDate, selectedTime;
    private DatabaseHelperr dbHelper;

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
        setContentView(R.layout.activity_reservation); // Ensure this points to activity_reservation.xml

        dbHelper = new DatabaseHelperr(this);

        nameEditText = findViewById(R.id.nameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        userIdEditText = findViewById(R.id.userIdEditText);
        dateButton = findViewById(R.id.dateButton);
        timeButton = findViewById(R.id.timeButton);
        reserveButton = findViewById(R.id.reserveButton);

        // Date and time pickers, and reservation logic
        dateButton.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    ReservationActivity.this,
                    (view, year1, month1, dayOfMonth) -> {
                        selectedDate = year1 + "-" + (month1 + 1) + "-" + dayOfMonth;
                        dateButton.setText(selectedDate);
                    }, year, month, day);
            datePickerDialog.show();
        });

        timeButton.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    ReservationActivity.this,
                    (view, hourOfDay, minute1) -> {
                        selectedTime = hourOfDay + ":" + (minute1 < 10 ? "0" + minute1 : minute1);
                        timeButton.setText(selectedTime);
                    }, hour, minute, true);
            timePickerDialog.show();
        });

        reserveButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            String phone = phoneEditText.getText().toString();
            String userId = userIdEditText.getText().toString();

            if (selectedDate != null && selectedTime != null && !name.isEmpty() && !phone.isEmpty() && !userId.isEmpty()) {
                boolean success = dbHelper.addAppointment(selectedDate, selectedTime, userId, name, phone);
                if (success) {
                    Toast.makeText(this, "Appointment reserved for " + selectedDate + " at " + selectedTime, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "This time is already reserved. Please choose another.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
