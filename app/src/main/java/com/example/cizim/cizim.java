package com.example.cizim;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class cizim extends View{ //view oluşturduk
    Path path=new Path();
    Paint paint =new Paint();

    public cizim(Context c, AttributeSet a){
        super(c,a);//her zaman ilk satırda olur
        paint.setColor(Color.GRAY); //gri çizim yapar
        paint.setAntiAlias(true);//pixeller arası renklendirme
        paint.setStrokeJoin(Paint.Join.ROUND);//boyanın birleşimini
        paint.setStyle(Paint.Style.STROKE);//boyanın tipi
        paint.setStrokeWidth(5f);//boyanın genişliği
    }
    @Override
    protected void onDraw(Canvas canvas) {//cizim eylemi
        super.onDraw(canvas);
        canvas.drawPath(path,paint);//draw çizdirmeye yarıyor. nereye çizeceğimiz,çizeceğimiz ortam
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {//dokunma eylemı alglanıyor
        float x=event.getX();
        float y=event.getY();
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x,y);
                return true;
            case  MotionEvent.ACTION_MOVE:
                path.lineTo(x,y);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }
}

