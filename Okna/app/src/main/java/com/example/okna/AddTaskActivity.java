package com.example.okna;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

public class AddTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        EditText editTextTaskName = findViewById(R.id.editTextTaskName);
        EditText editTextTaskDescription = findViewById(R.id.editTextTaskDescription);
        Button buttonSaveTask = findViewById(R.id.buttonSaveTask);

        buttonSaveTask.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("task_name", editTextTaskName.getText().toString());
            intent.putExtra("task_description", editTextTaskDescription.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}