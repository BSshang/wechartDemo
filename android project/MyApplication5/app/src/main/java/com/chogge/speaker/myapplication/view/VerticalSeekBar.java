package com.chogge.speaker.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;

public class VerticalSeekBar extends android.support.v7.widget.AppCompatSeekBar {

    public VerticalSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

//    public boolean onTouchEvent(MotionEvent event) {
//        if (!isEnabled()) {
//            return false;
//        }
//        switch (event.getAction()) {
//            case 0:
//            case 1:
//            case 2:
//                setProgress(getMax() - ((int) ((((float) getMax()) * event.getY()) / ((float) getHeight()))));
//                onSizeChanged(getWidth(), getHeight(), 0, 0);
//                break;
//        }
//        return true;
//    }

    /*public VerticalSeekBar(Context context) {
        super(context);
    }

    public VerticalSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public VerticalSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(h, w, oldh, oldw);
    }

    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    protected void onDraw(Canvas c) {
    //    c.rotate(-90.0f);
    //    c.translate((float) (-getHeight()), 0.0f);
        super.onDraw(c);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return false;
        }
        switch (event.getAction()) {
            case 0:
            case 1:
            case 2:
                setProgress(getMax() - ((int) ((((float) getMax()) * event.getY()) / ((float) getHeight()))));
                onSizeChanged(getWidth(), getHeight(), 0, 0);
                break;
        }
        return true;
    }*/
}
