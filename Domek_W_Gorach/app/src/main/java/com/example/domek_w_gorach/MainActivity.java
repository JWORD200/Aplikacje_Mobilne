package com.example.domek_w_gorach;

import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int likeCount = 0;
    private TextView likeCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        likeCounter = findViewById(R.id.likeCounter);
        Button btnLike = findViewById(R.id.btnLike);
        Button btnDelete = findViewById(R.id.btnDelete);

        btnLike.setOnClickListener(v -> {
            likeCount++;
            updateLikeCounter();
        });

        btnDelete.setOnClickListener(v -> {
            if (likeCount > 0) {
                likeCount--;
            }
            updateLikeCounter();
        });
    }

    private void updateLikeCounter() {
        likeCounter.setText(likeCount + " polubień");
    }
}