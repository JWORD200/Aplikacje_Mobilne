package com.example.kosci;


import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int gameScore = 0;
    private final Random random = new Random();
    private final int[] diceImages = {
            R.drawable.k6_1, R.drawable.k6_2, R.drawable.k6_3,
            R.drawable.k6_4, R.drawable.k6_5, R.drawable.k6_6
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView rollResult = findViewById(R.id.rollResult);
        TextView gameResult = findViewById(R.id.gameResult);
        ImageView[] diceViews = {
                findViewById(R.id.dice1),
                findViewById(R.id.dice2),
                findViewById(R.id.dice3),
                findViewById(R.id.dice4),
                findViewById(R.id.dice5)
        };

        Button rollButton = findViewById(R.id.rollButton);
        Button resetButton = findViewById(R.id.resetButton);

        rollButton.setOnClickListener(v -> {
            int rollScore = 0;
            for (ImageView diceView : diceViews) {
                int roll = random.nextInt(6);
                diceView.setImageResource(diceImages[roll]);
                rollScore += roll + 1;
            }
            rollResult.setText("Wynik tego losowania: " + rollScore);
            gameScore += rollScore;
            gameResult.setText("Wynik gry: " + gameScore);
        });

        resetButton.setOnClickListener(v -> {
            for (ImageView diceView : diceViews) {
                diceView.setImageResource(R.drawable.k6_0);
            }
            rollResult.setText("Wynik tego losowania: 0");
            gameResult.setText("Wynik gry: 0");
            gameScore = 0;
        });
    }
}