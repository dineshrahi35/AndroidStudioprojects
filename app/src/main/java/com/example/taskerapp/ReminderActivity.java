package com.example.taskerapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class ReminderActivity extends AppCompatActivity {

    private EditText reminderTitle;
    private TextView selectedDate, selectedTime;
    private Button saveReminderBtn, datePickerBtn, timePickerBtn;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        dbHelper = new DatabaseHelper(this);
        reminderTitle = findViewById(R.id.reminderTitle);
        selectedDate = findViewById(R.id.selectedDate);
        selectedTime = findViewById(R.id.selectedTime);
        saveReminderBtn = findViewById(R.id.saveReminderBtn);
        datePickerBtn = findViewById(R.id.datePickerBtn);
        timePickerBtn = findViewById(R.id.timePickerBtn);

        datePickerBtn.setOnClickListener(v -> showDatePickerDialog());
        timePickerBtn.setOnClickListener(v -> showTimePickerDialog());

        saveReminderBtn.setOnClickListener(v -> {
            String title = reminderTitle.getText().toString();
            if (title.isEmpty()) {
                reminderTitle.setError("Please enter a reminder");
            } else {
                boolean inserted = dbHelper.addReminder(title, selectedDate.getText().toString(), selectedTime.getText().toString());
                if (inserted) {
                    Toast.makeText(this, "Reminder Saved!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Failed to save reminder!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(this, (view, year, month, day) -> selectedDate.setText(day + "/" + (month + 1) + "/" + year),
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        new TimePickerDialog(this, (view, hour, minute) -> selectedTime.setText(hour + ":" + minute),
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
    }
}
