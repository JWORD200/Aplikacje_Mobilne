package com.example.watekbubble;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private EditText inputSize;
    private Button startSortButton;
    private ProgressBar progressBar;
    private SortView sortView;
    private ExecutorService executorService;
    private Handler handler;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputSize = findViewById(R.id.inputSize);
        startSortButton = findViewById(R.id.startSortButton);
        progressBar = findViewById(R.id.progressBar);
        sortView = findViewById(R.id.sortView);

        handler = new Handler(Looper.getMainLooper());
        executorService = Executors.newSingleThreadExecutor();

        startSortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = inputSize.getText().toString();
                if (!inputText.isEmpty()) {
                    int size = Integer.parseInt(inputText);
                    startSorting(size);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void startSorting(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(500);
        }

        progressBar.setProgress(0);

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                visualBubbleSort(array);
                handler.post(() -> Toast.makeText(MainActivity.this, "Sorting Completed!", Toast.LENGTH_SHORT).show());
            }
        });
    }

    private void visualBubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    int[] currentArrayState = array.clone();
                    int finalI = i;
                    int finalJ = j;
                    handler.post(() -> {
                        sortView.updateData(currentArrayState);
                        int progress = (int) (((float) (finalI * n + finalJ) / (n * (n - 1) / 2)) * 100);
                        progressBar.setProgress(progress);
                    });

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}