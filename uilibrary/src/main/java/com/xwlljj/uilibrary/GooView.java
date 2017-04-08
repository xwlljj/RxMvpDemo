package com.xwlljj.uilibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by XieWei on 16/10/17.
 */

public class GooView extends View {
    private static final String TAG = "GooView";
    private Paint paint;
    private float dragRadius = 14F;
    private float stickyRadius = 14F;
    private PointF dragCenter;
    private PointF stickyCenter;

    public GooView(Context context) {
        super(context);
        init();
    }

    public GooView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GooView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public GooView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setDither(true);
        paint.setColor(Color.RED);

        dragCenter = new PointF(120F, 120F);
        stickyCenter = new PointF(180F, 120F);
    }

    private PointF[] stickyPoint = {new PointF(180F, 106F), new PointF(180F, 134F)};
    private PointF[] dragPoint = {new PointF(120F, 106F), new PointF(120F, 134F)};

    private PointF ctrlPoint = new PointF(140F, 120F);

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(dragCenter.x, dragCenter.y, dragRadius, paint);
        canvas.drawCircle(stickyCenter.x, stickyCenter.y, stickyRadius, paint);

        Path path = new Path();
        path.moveTo(stickyPoint[0].x, stickyPoint[0].y);
        path.quadTo(ctrlPoint.x, ctrlPoint.y, dragPoint[0].x, dragPoint[0].y);
        path.lineTo(dragPoint[1].x, dragPoint[1].y);
        path.quadTo(ctrlPoint.x, ctrlPoint.y, stickyPoint[1].x, stickyPoint[1].y);
        canvas.drawPath(path, paint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getRawX();
        float y = event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                dragCenter.set(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                dragCenter.set(x, y);
                break;
            case MotionEvent.ACTION_UP:

                break;
            default:
        }
        return true;
    }
}
