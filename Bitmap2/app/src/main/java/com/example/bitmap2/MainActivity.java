package com.example.bitmap2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Bitmap bitmap;
    private Canvas canvas;
    private Paint paint;
    private ImageView myImageView;
    private TextView myTextView;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.btn1);
        Button button2 = findViewById(R.id.btn2);
        Button button3 = findViewById(R.id.btn3);
        Button button4 = findViewById(R.id.btn4);
        Button button5 = findViewById(R.id.btn5);

        myImageView = findViewById(R.id.myImageView);
        myTextView = findViewById(R.id.myTextView);

        paint = new Paint();
        random = new Random();

        button1.setOnClickListener(view -> {
            createBitmap();
            updateTextView();
        });

        button2.setOnClickListener(view -> {
            if (bitmap != null) {
                clearBitmap();
            }
        });

        button3.setOnClickListener(view -> {
            if (bitmap != null) {
                drawRandomLine();
            }
        });

        button4.setOnClickListener(view -> {
            if (bitmap != null) {
                drawRandomRectangle();
            }
        });

        button5.setOnClickListener(view -> {
            if (bitmap != null) {
                drawRandomEllipse();
            }
        });
    }

    private void createBitmap() {
        bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        myImageView.setImageBitmap(bitmap);
    }

    private void updateTextView() {
        if (bitmap != null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            myTextView.setText("Wielkośc bitmapy: " + width + " x " + height + " px");
        }
    }

    private void clearBitmap() {
        canvas.drawColor(Color.WHITE);
        myImageView.invalidate();
    }

    private int getRandomColor() {
        return Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    private void drawRandomLine() {
        paint.setColor(getRandomColor());
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        int startX = random.nextInt(500);
        int startY = random.nextInt(500);
        int stopX = random.nextInt(500);
        int stopY = random.nextInt(500);
        canvas.drawLine(startX, startY, stopX, stopY, paint);
        myImageView.invalidate();
    }

    private void drawRandomRectangle() {
        paint.setColor(getRandomColor());
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        int left = random.nextInt(400);
        int top = random.nextInt(400);
        int right = left + random.nextInt(100) + 50;
        int bottom = top + random.nextInt(100) + 50;
        canvas.drawRect(left, top, right, bottom, paint);
        myImageView.invalidate();
    }

    private void drawRandomEllipse() {
        paint.setColor(getRandomColor());
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        int left = random.nextInt(400);
        int top = random.nextInt(400);
        int right = left + random.nextInt(100) + 50;
        int bottom = top + random.nextInt(100) + 50;
        RectF oval = new RectF(left, top, right, bottom);
        canvas.drawOval(oval, paint);
        myImageView.invalidate();
    }
}