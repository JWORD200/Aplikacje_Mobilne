package com.example.okna;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTasks;
    private TaskAdapter taskAdapter;
    private ArrayList<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewTasks);
        recyclerView.setPadding(0, 0, 0, 128);
        recyclerView.setClipToPadding(false);

        recyclerViewTasks = findViewById(R.id.recyclerViewTasks);
        FloatingActionButton fabAddTask = findViewById(R.id.fabAddTask);

        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(taskList, this);
        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTasks.setAdapter(taskAdapter);

        fabAddTask.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
            startActivityForResult(intent, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String name = data.getStringExtra("task_name");
            String description = data.getStringExtra("task_description");
            taskList.add(new Task(name, description));
            taskAdapter.notifyDataSetChanged();
        }
    }
}