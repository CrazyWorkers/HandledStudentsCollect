package com.ziyan.handledstudentscollect;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by Administrator on 2016/1/4.
 */
public class DrawBellwether extends View {

    private Paint mPaint;
    private Paint mTextPaint;
    private Path mPath;
    private Canvas mCanvas;
    private Bitmap mBitmap;

    private int mLastX;
    private int mLastY;

    public DrawBellwether(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPath = new Path();
        //定义形状画笔
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.newBlue));
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        //定义文字画笔
        mTextPaint = new Paint();
        mTextPaint.setColor(getResources().getColor(R.color.text_font_white));
        mTextPaint.setAntiAlias(true);
        mTextPaint.setDither(true);
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setStrokeJoin(Paint.Join.ROUND);
        mTextPaint.setStrokeCap(Paint.Cap.ROUND);
        mTextPaint.setTextSize(30);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(325, 200, 100, mPaint);
        canvas.drawText("学习", 300, 200, mTextPaint);
        canvas.drawCircle(525, 200, 100, mPaint);
        canvas.drawText("生活", 500, 200, mTextPaint);
        canvas.drawCircle(725, 200, 100, mPaint);
        canvas.drawText("娱乐",700,200,mTextPaint);
        canvas.drawCircle(225, 400, 100, mPaint);
        canvas.drawCircle(425, 400, 100, mPaint);
        canvas.drawCircle(625, 400, 100, mPaint);
        canvas.drawCircle(825, 400, 100, mPaint);
        canvas.drawCircle(325, 600, 100, mPaint);
        canvas.drawCircle(525, 600, 100, mPaint);
        canvas.drawCircle(725, 600, 100, mPaint);
        canvas.drawLine(325, 200, 525, 1100, mPaint);
        canvas.drawLine(525, 200, 525, 1100, mPaint);
        canvas.drawLine(725, 200, 525, 1100, mPaint);
        canvas.drawLine(225, 400, 525, 1100, mPaint);
        canvas.drawLine(425, 400, 525, 1100, mPaint);
        canvas.drawLine(625, 400, 525, 1100, mPaint);
        canvas.drawLine(825, 400, 525, 1100, mPaint);
        canvas.drawLine(325, 600, 525, 1100, mPaint);
        canvas.drawLine(525, 600, 525, 1100, mPaint);
        canvas.drawLine(725, 600, 525, 1100, mPaint);
        canvas.drawRect(400,1000,650,1200,mPaint);
    }
}
