package com.xwlljj.uilibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by XieWei on 16/10/14.
 */

public class QuickIndexBar extends View {
    private static final String TAG = "QuickIndexBar";
    private static final String[] INDEX_CHARS =
            {"A", "B", "C", "D", "E",
                    "F", "G", "H", "I", "J",
                    "K", "L", "M", "N", "O",
                    "P", "Q", "R", "S", "T",
                    "U", "V", "W", "X", "Y", "Z", "#"};
    private Paint paint;
    private float width;
    private float cellHeight;
    private int lastIndex = -1;
    private int textColorNormal = Color.BLUE;

    public QuickIndexBar(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setDither(true);
        paint.setColor(textColorNormal);
        paint.setTextSize(32);
        paint.setTextAlign(Paint.Align.CENTER);
    }

    public QuickIndexBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public QuickIndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public QuickIndexBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void setTextSize(float textSize) {
        paint.setTextSize(textSize);
        invalidate();
    }

    public void setTextColor(int color) {
        textColorNormal = color;
        paint.setColor(color);
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getMeasuredWidth();
        cellHeight = getMeasuredHeight() / INDEX_CHARS.length;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < INDEX_CHARS.length; i++) {
//            绘制文本x坐标: width/2;
//            绘制文本y坐标: 格子高度的一半 + 文本高度的一半 + position*格子高度
            float x = width / 2;
            float textHeight = getTextHeight(INDEX_CHARS[i]);
            float y = cellHeight / 2 + textHeight / 2F + i * cellHeight;
            paint.setColor(lastIndex == i ? Color.GRAY : textColorNormal);
            canvas.drawText(INDEX_CHARS[i], x, y, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();
                int index = (int) (y / cellHeight) - 1;
                if (index >= 0 && index < INDEX_CHARS.length) {
                    if (index != lastIndex) {
                        Log.i(TAG, INDEX_CHARS[index]);
                        if (indexTextListener != null) {
                            indexTextListener.onIndexText(INDEX_CHARS[index]);
                        }
                        lastIndex = index;
                        invalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                lastIndex = -1;
                invalidate();
                break;
            default:
                return super.onTouchEvent(event);
        }
        return true;
    }

    private int getTextHeight(String indexChar) {
        Rect bounds = new Rect();
        paint.getTextBounds(indexChar, 0, indexChar.length(), bounds);
        return bounds.height();
    }

    private OnIndexTextListener indexTextListener;

    public void setIndexTextListener(OnIndexTextListener listener) {
        indexTextListener = listener;
    }

    public OnIndexTextListener getIndexTextListener() {
        return indexTextListener;
    }

    public interface OnIndexTextListener {
        void onIndexText(String txt);
    }
}
