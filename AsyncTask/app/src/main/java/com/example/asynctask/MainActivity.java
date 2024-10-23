package com.example.asynctask;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private EditText editTextItem;
    private ArrayAdapter<String> adapter;
    private List<String> displayedDataList;
    private List<String> bufferDataList;
    private DataTask dataTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        editTextItem = findViewById(R.id.editTextItem);
        Button buttonAdd = findViewById(R.id.buttonAdd);

        displayedDataList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayedDataList);
        listView.setAdapter(adapter);

        bufferDataList = new ArrayList<>();

        buttonAdd.setOnClickListener(v -> {
            String newItem = editTextItem.getText().toString().trim();
            if (!newItem.isEmpty()) {
                bufferDataList.add(newItem);
                editTextItem.setText("");
            }
        });

        dataTask = new DataTask();
        dataTask.execute();
    }

    private class DataTask extends AsyncTask<Void, String, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                while (true) {
                    if (isCancelled()) break;

                    if (!bufferDataList.isEmpty()) {
                        String item = bufferDataList.remove(0);
                        publishProgress(item);
                    }

                    Thread.sleep(5000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            displayedDataList.add(values[0]);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dataTask != null) {
            dataTask.cancel(true);
        }
    }
}