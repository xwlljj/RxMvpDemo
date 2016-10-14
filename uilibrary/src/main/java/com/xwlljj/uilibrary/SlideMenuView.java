package com.xwlljj.uilibrary;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by XieWei on 16/10/12.
 */

public class SlideMenuView extends FrameLayout {
    private ViewDragHelper dragHelper;
    private float dragDistance;
    private View menuView, mainView;

    public SlideMenuView(Context context) {
        super(context);
        init();
    }

    public SlideMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlideMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SlideMenuView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mainView = findViewById(R.id.main_layout);
        menuView = findViewById(R.id.menu_layout);
    }

    private void init() {
        dragHelper = ViewDragHelper.create(this, cb);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                dragHelper.cancel();
                return false;
            default:
                return super.onInterceptTouchEvent(ev);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dragHelper.processTouchEvent(event);
        return true;
    }

    private ViewDragHelper.Callback cb = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == menuView || child == mainView;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            if (changedView == mainView) {
                if (left < 0) {

                } else if (left > getMeasuredWidth() - mainView.getMeasuredWidth()) {

                }
            }

        }

        @Override
        public void onViewCaptured(View capturedChild, int activePointerId) {
            super.onViewCaptured(capturedChild, activePointerId);


        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return super.getViewHorizontalDragRange(child);
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return super.getViewVerticalDragRange(child);
        }
    };
}
