package com.example.taskerapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAddReminder = findViewById(R.id.btnAddReminder);
        Button btnViewReminders = findViewById(R.id.btnViewReminders);

        btnAddReminder.setOnClickListener(v -> startActivity(new Intent(this, ReminderActivity.class)));
        btnViewReminders.setOnClickListener(v -> startActivity(new Intent(this, SavedRemindersActivity.class)));
    }
}
