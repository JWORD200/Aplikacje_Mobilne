package com.example.watekbubble;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Arrays;

public class SortView extends View {

    private int[] data = new int[0];
    private Paint paint = new Paint();

    public SortView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5);
    }

    public void updateData(int[] newData) {
        this.data = Arrays.copyOf(newData, newData.length);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (data.length == 0) return;

        int width = getWidth();
        int height = getHeight();
        int barWidth = width / data.length;

        int maxVal = Arrays.stream(data).max().orElse(1);

        for (int i = 0; i < data.length; i++) {
            int barHeight = (int) (((float) data[i] / maxVal) * height);
            canvas.drawRect(i * barWidth, height - barHeight, (i + 1) * barWidth, height, paint);
        }
    }
}

