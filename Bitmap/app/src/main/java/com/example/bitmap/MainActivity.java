package com.example.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView imageSizeLabel;
    private Bitmap bitmap;
    private int rotationAngle = 0;
    private boolean isImageVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        imageSizeLabel = findViewById(R.id.imgSizeLabel);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground);
        imageView.setImageBitmap(bitmap);

        updateImageSizeLabel();

        Button buttonShow = findViewById(R.id.btnShow);
        Button buttonHide = findViewById(R.id.btnHide);
        Button buttonRotateLeft = findViewById(R.id.btnRotateLeft);
        Button buttonRotateRight = findViewById(R.id.btnRotateRight);

        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.VISIBLE);
                isImageVisible = true;
            }
        });

        buttonHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.GONE);
                isImageVisible = false;
            }
        });

        buttonRotateLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotationAngle -= 90;
                imageView.setRotation(rotationAngle);
            }
        });

        buttonRotateRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotationAngle += 90;
                imageView.setRotation(rotationAngle);
            }
        });
    }

    private void updateImageSizeLabel() {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        String sizeText = "Image Size: " + width + "x" + height;
        imageSizeLabel.setText(sizeText);
    }
}
