package com.example.okna;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class TaskDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        TextView textViewTaskName = findViewById(R.id.textViewTaskName);
        TextView textViewTaskDescription = findViewById(R.id.textViewTaskDescription);

        String name = getIntent().getStringExtra("task_name");
        String description = getIntent().getStringExtra("task_description");

        textViewTaskName.setText(name);
        textViewTaskDescription.setText(description);
    }
}