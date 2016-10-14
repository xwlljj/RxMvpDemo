package com.xwlljj.uilibrary;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by XieWei on 16/10/14.
 */

public class SwipeLayout extends FrameLayout {
    private static final String TAG = "SwipeLayout";
    private View contentView;
    private View deleteView;
    private int deleteWidth, deleteHeight;

    private int contentWidth;

    private ViewDragHelper dragHelper;

    public SwipeLayout(Context context) {
        super(context);
        init();
    }

    public SwipeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SwipeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SwipeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        dragHelper = ViewDragHelper.create(this, cb);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        contentView = findViewById(R.id.content_view);
        deleteView = findViewById(R.id.delete_view);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        deleteWidth = deleteView.getMeasuredWidth();
        deleteHeight = deleteView.getMeasuredHeight();
        contentWidth = contentView.getMeasuredWidth();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        super.onLayout(changed, left, top, right, bottom);
        contentView.layout(0, 0, contentWidth, deleteHeight);
        deleteView.layout(contentView.getRight(), 0,
                contentView.getRight() + deleteWidth, deleteHeight);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return dragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dragHelper.processTouchEvent(event);
        return true;
    }

    private ViewDragHelper.Callback cb = new ViewDragHelper.Callback() {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == contentView || child == deleteView;
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return deleteWidth;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if (child == contentView) {
                if (left > 0) {
                    left = 0;
                }
                if (left < -deleteWidth) {
                    left = -deleteWidth;
                }
            } else if (child == deleteView) {
                if (left > contentWidth) {
                    left = contentWidth;
                }
                if (left < (contentWidth - deleteWidth)) {
                    left = contentWidth - deleteWidth;
                }
            }
            return left;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            if (changedView == contentView) {
                deleteView.layout(deleteView.getLeft() + dx, deleteView.getTop() + dy,
                        deleteView.getRight() + dx, deleteView.getBottom() + dy);
            } else if (changedView == deleteView) {
                contentView.layout(contentView.getLeft() + dx, contentView.getTop() + dy,
                        contentView.getRight() + dx, contentView.getBottom() + dy);
            }
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            if (contentView.getLeft() < -deleteWidth / 2) {
                dragHelper.smoothSlideViewTo(contentView, -deleteWidth, contentView.getTop());
                ViewCompat.postInvalidateOnAnimation(SwipeLayout.this);
            } else {
                dragHelper.smoothSlideViewTo(contentView, 0, contentView.getTop());
                ViewCompat.postInvalidateOnAnimation(SwipeLayout.this);
            }
        }
    };

    @Override
    public void computeScroll() {
        if (dragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
