package com.example.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
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
    private boolean isDarkened = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        imageSizeLabel = findViewById(R.id.imageDimensions);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground);
        imageView.setImageBitmap(bitmap);

        updateImageSizeLabel();

        Button buttonShow = findViewById(R.id.btnShow);
        Button buttonHide = findViewById(R.id.btnHide);
        Button buttonRotateLeft = findViewById(R.id.btnRotateLeft);
        Button buttonRotateRight = findViewById(R.id.btnRotateRight);
        Button buttonSetDark = findViewById(R.id.btnSetDark);

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


        buttonSetDark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isDarkened) {
                    ColorMatrix colorMatrix = new ColorMatrix();
                    colorMatrix.setScale(0.5f, 0.5f, 0.5f, 1.0f);
                    imageView.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
                    buttonSetDark.setText("Przywróć obrazek");
                    isDarkened = true;
                } else {
                    imageView.clearColorFilter();
                    buttonSetDark.setText("Przyciemnij obrazek");
                    isDarkened = false;
                }
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
