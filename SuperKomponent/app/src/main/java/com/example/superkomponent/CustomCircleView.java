package com.example.superkomponent;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CustomCircleView extends View {
    private Paint paint;
    private float circleX, circleY;
    private float radius = 200f;
    private int circleColor = Color.RED;
    private boolean isTouched = false;

    public CustomCircleView(Context context) {
        super(context);
        init();
    }

    public CustomCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(circleColor);
        paint.setStyle(Paint.Style.FILL);

        circleX = 0;
        circleY = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(circleX, circleY, radius, paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        circleX = w / 2f;
        circleY = h / 2f;
    }

    public void setCircleColor(int color) {
        circleColor = color;
        paint.setColor(color);
        invalidate();
    }

    public void setCircleRadius(float newRadius) {
        radius = newRadius;
        invalidate();
    }

    public void setCirclePosition(float x, float y) {
        circleX = x;
        circleY = y;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            setCircleColor(getRandomColor());
            return true;
        }
        return super.onTouchEvent(event);
    }

    private int getRandomColor() {
        return Color.rgb((int) (Math.random() * 256),
                (int) (Math.random() * 256),
                (int) (Math.random() * 256));
    }
}

/*
SetPixel - przekształcanie, zmiana obrazka po wciśnięciu przycisku II część zadania ! ! !
 */