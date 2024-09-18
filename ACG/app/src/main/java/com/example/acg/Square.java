package com.example.acg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Square extends View{

    public Square(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Square(Context context, AttributeSet attrs, int xd) {
        super(context, attrs, xd);
    }

    public Square(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas){
        int szer = getWidth(), wys = getHeight();
        int r = 200;

        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.CYAN);
        canvas.drawRect(szer/2-r, wys/2-r, szer+r, wys/2+r,p);

        p.setColor(Color.RED);
        canvas.drawCircle(szer/2, wys/2, r, p);

        super.onDraw(canvas);
    }


}
