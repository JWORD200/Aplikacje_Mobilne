package com.example.superkomponent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private CustomCircleView customCircleView;
    private Handler handler = new Handler();
    private float deltaX = 5f;
    private float deltaY = 5f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customCircleView = findViewById(R.id.customCircleView);

        startCircleAnimation();
    }

    private void startCircleAnimation() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                float x = customCircleView.getX() + deltaX;
                float y = customCircleView.getY() + deltaY;

                if (x < 0 || x + customCircleView.getWidth() > customCircleView.getWidth()) {
                    deltaX = -deltaX;
                }
                if (y < 0 || y + customCircleView.getHeight() > customCircleView.getHeight()) {
                    deltaY = -deltaY;
                }

                customCircleView.setCirclePosition(x, y);

                handler.postDelayed(this, 16);  // Ok. 60 FPS
            }
        }, 16);
    }
}
