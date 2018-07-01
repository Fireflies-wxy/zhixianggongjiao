package com.bnrc.bnrcsdk.ui.subview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.bnrc.bnrcsdk.R;


/**
 * Created by frank on 15/10/14.
 */
public class RotateLoading extends View {
    private static final String TAG = RotateLoading.class.getSimpleName();
    private static final int DEFAULT_WIDTH = 6;
    private static final int DEFAULT_SHADOW_POSITION = 2;

    private Paint mPaint;

    private RectF loadingRectF;
    private RectF shadowRectF;

    private int topDegree = 10;
    private int bottomDegree = 190;

    private float arc;

    private int width;

    private boolean changeBigger = true;

    private int shadowPosition;

    private boolean isStart = false;

    private int color;

    public RotateLoading(Context context) {
        super(context);
        initView(context, null);
    }

    public RotateLoading(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public RotateLoading(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        color = Color.WHITE;
        width = dpToPx(context, DEFAULT_WIDTH);
        shadowPosition = dpToPx(getContext(), DEFAULT_SHADOW_POSITION);

        if (null != attrs) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RotateLoading);
            color = typedArray.getColor(R.styleable.RotateLoading_loadingColor, Color.WHITE);
            width = typedArray.getDimensionPixelSize(R.styleable.RotateLoading_loadingWidth, dpToPx(context, DEFAULT_WIDTH));
            shadowPosition = typedArray.getInt(R.styleable.RotateLoading_shadowPosition, DEFAULT_SHADOW_POSITION);
            typedArray.recycle();
        }

        mPaint = new Paint();
        mPaint.setColor(color);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(width);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        arc = 10;
        loadingRectF = new RectF(2 * width, 2 * width, w - 2 * width, h - 2 * width);
        shadowRectF = new RectF(2 * width + shadowPosition, 2 * width + shadowPosition, w - 2 * width + shadowPosition, h - 2 * width + shadowPosition);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.parseColor("#1a000000"));
        Log.i(TAG, "if canvas==null: " + (canvas == null));
        Log.i(TAG, "if shadowRectF==null: " + (shadowRectF == null));
        Log.i(TAG, "if mPaint==null: " + (mPaint == null));
        if (shadowRectF == null)
//            shadowRectF = new RectF(2 * width + shadowPosition, 2 * width + shadowPosition, w - 2 * width + shadowPosition, h - 2 * width + shadowPosition);
            return;
        canvas.drawArc(shadowRectF, topDegree, arc, false, mPaint);
        canvas.drawArc(shadowRectF, bottomDegree, arc, false, mPaint);

        mPaint.setColor(color);
        canvas.drawArc(loadingRectF, topDegree, arc, false, mPaint);
        canvas.drawArc(loadingRectF, bottomDegree, arc, false, mPaint);

        topDegree += 10;
        bottomDegree += 10;
        if (topDegree > 360) {
            topDegree = topDegree - 360;
        }
        if (bottomDegree > 360) {
            bottomDegree = bottomDegree - 360;
        }

        if (changeBigger) {
            if (arc < 160) {
                arc += 2.5;
            }
        } else {
            if (arc > 10) {
                arc -= 5;
            }
        }
        if (arc == 160 || arc == 10) {
            changeBigger = !changeBigger;
        }

    }

    public void start() {
        startAnimator();
        isStart = true;
        new DrawThread().start();
    }

    public void stop() {
        stopAnimator();
        invalidate();
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        this.isStart = start;
    }

    private void startAnimator() {
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(this, "scaleX", 0.0f, 1);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(this, "scaleY", 0.0f, 1);
        scaleXAnimator.setDuration(300);
        scaleXAnimator.setInterpolator(new LinearInterpolator());
        scaleYAnimator.setDuration(300);
        scaleYAnimator.setInterpolator(new LinearInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleXAnimator, scaleYAnimator);
        animatorSet.start();
    }


    private void stopAnimator() {
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(this, "scaleX", 1, 0);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(this, "scaleY", 1, 0);
        scaleXAnimator.setDuration(300);
        scaleXAnimator.setInterpolator(new LinearInterpolator());
        scaleYAnimator.setDuration(300);
        scaleYAnimator.setInterpolator(new LinearInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleXAnimator, scaleYAnimator);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isStart = false;
                setScaleX(1);
                setScaleY(1);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();
    }

    public int dpToPx(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, context.getResources().getDisplayMetrics());
    }

    class DrawThread extends Thread {
        @Override
        public void run() {
            while (isStart) {
                postInvalidate();
                SystemClock.sleep(10);
            }
        }
    }

}
