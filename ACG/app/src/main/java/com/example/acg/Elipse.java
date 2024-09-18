package com.example.acg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

import java.util.Random;

public class Elipse extends View {
    public Elipse(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int szer = getWidth();
        int szer2 = szer / 2;
        int wys = getHeight();
        int rozmiar = (szer < wys ? szer2 : wys) - 10;
        int x, y, dx, dy;
        Paint p = new Paint();
        canvas.drawRect(0, 0, szer - 1, wys - 1, p);
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.FILL);
        Random r = new Random();
        p.setColor(Color.GRAY);
        canvas.drawRect(0, 0, szer - 1, wys - 1, p);

        CharSequence opis = getContentDescription();
        if (opis == null)
            opis = "brak";
        for (int i = 0; i < 10; i++) {
            p.setARGB(255, r.nextInt(256), r.nextInt(256), r.nextInt(256));
            if (opis.equals("koło")) {
                dx = r.nextInt(rozmiar);
                x = r.nextInt(szer2 - dx);
                y = r.nextInt(wys - dx);
                canvas.drawCircle(x, y, dx, p);
            } else if (opis.equals("elipsa")) {
                dx = r.nextInt(rozmiar);
                dy = r.nextInt(rozmiar);
                x = r.nextInt(szer2 - dx);
                y = r.nextInt(wys - dy);
                y = r.nextInt(wys - dy);
                RectF rect = new RectF(x, y, x + dx, y + dy);
                canvas.drawOval(rect, p);
            } else if (opis.equals("prostokąt")) {
                dx = r.nextInt(rozmiar);
                dy = r.nextInt(rozmiar);
                x = r.nextInt(szer2 - dx);
                y = r.nextInt(wys - dy);
                RectF rect = new RectF(x, y, x + dx, y + dy);
                canvas.drawRect(rect, p);
            } else if (opis.equals("prostokąt okrągły")) {
                dx = r.nextInt(rozmiar);
                dy = r.nextInt(rozmiar);
                x = r.nextInt(szer2 - dx);
                y = r.nextInt(wys - dy);
                RectF rect = new RectF(x, y, x + dx, y + dy);
                canvas.drawRoundRect(rect, 10, 10, p);
            } else if (opis.equals("łuk")) {
                dx = r.nextInt(rozmiar);
                dy = r.nextInt(rozmiar);
                x = r.nextInt(szer2 - dx);
                y = r.nextInt(wys - dy);
                RectF rect = new RectF(x, y, x + dx, y + dy);
                canvas.drawArc(rect, r.nextInt(360), r.nextInt(360), false, p);
            } else if (opis.equals("linia")) {
                dx = r.nextInt(szer2);
                dy = r.nextInt(szer2);
                x = r.nextInt(szer2);
                y = r.nextInt(wys);
                canvas.drawLine(x, y, dx, dy, p);
            }
            p.setTextSize(getResources().getDimension(R.dimen.wys_napisu));
            p.setTextAlign(Paint.Align.RIGHT);
            p.setColor(Color.BLUE);
            canvas.drawText((String) opis, szer - 20, wys / 2, p);

            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(1);
            canvas.drawRect(0, 0, szer - 1, wys - 1, p);
            super.onDraw(canvas);
        }
    }
}