package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw1.Model.Data;

import java.util.ArrayList;
import java.util.List;

public class Practice10HistogramView extends View {
    private List<Data> mDatas;
    private Paint mPaint;
    private float startX;
    private float space;
    private float width;
    private float max;
    private final static String NAME = "直方图";

    public Practice10HistogramView(Context context) {
        super(context);
        init();
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mDatas = new ArrayList<>();
        Data data = new Data("Froyo", 10.0f, Color.GREEN);
        mDatas.add(data);
        data = new Data("ICS", 18.0f, Color.GREEN);
        mDatas.add(data);
        data = new Data("JB", 22.0f, Color.GREEN);
        mDatas.add(data);
        data = new Data("KK", 27.0f, Color.GREEN);
        mDatas.add(data);
        data = new Data("L", 40.0f, Color.GREEN);
        mDatas.add(data);
        data = new Data("M", 60.0f, Color.GREEN);
        mDatas.add(data);
        data = new Data("N", 33.5f, Color.GREEN);
        mDatas.add(data);
        max = Float.MIN_VALUE;
        for (Data d : mDatas) {
            max = Math.max(max, d.getNumber());
        }
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(72);
        canvas.drawText(NAME, (canvas.getWidth() - mPaint.measureText(NAME)) / 2, canvas.getHeight() * 0.9f, mPaint);
        canvas.translate(canvas.getWidth() * 0.1f, canvas.getHeight() * 0.7f);
        width = (canvas.getWidth() * 0.8f - 100) / mDatas.size() * 0.8f;
        space = (canvas.getWidth() * 0.8f - 100) / mDatas.size() * 0.2f;
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawLine(0, 0, canvas.getWidth() * 0.8f, 0, mPaint);//画x 轴
        canvas.drawLine(0, 0, 0, -canvas.getHeight() * 0.6f, mPaint);//画y 轴

        startX = 0f;
        mPaint.setTextSize(36);
        mPaint.setStyle(Paint.Style.FILL);
        for (Data data : mDatas) {
            mPaint.setColor(data.getColor());
            canvas.drawRect(startX + space, -(data.getNumber() / max * canvas.getHeight() * 0.6f), startX + space + width, 0, mPaint);
            mPaint.setColor(Color.WHITE);
            canvas.drawText(data.getName(), startX + space + (width - mPaint.measureText(data.getName())) / 2, 40, mPaint);
            startX += space + width;
        }
    }
}
