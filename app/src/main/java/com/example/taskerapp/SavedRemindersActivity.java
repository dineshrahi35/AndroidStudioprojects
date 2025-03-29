package com.example.taskerapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class SavedRemindersActivity extends AppCompatActivity {

    private ListView remindersListView;
    private ArrayList<String> remindersList;
    private ArrayAdapter<String> adapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_reminders);

        dbHelper = new DatabaseHelper(this);
        remindersListView = findViewById(R.id.remindersListView);
        remindersList = new ArrayList<>();

        loadReminders();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, remindersList);
        remindersListView.setAdapter(adapter);
    }

    private void loadReminders() {
        Cursor cursor = dbHelper.getReminders();
        while (cursor.moveToNext()) {
            remindersList.add(cursor.getString(1) + " | " + cursor.getString(2) + " | " + cursor.getString(3));
        }
    }
}
