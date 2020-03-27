package com.example.myappdemo01.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
//自定义View
public class MyNiceView extends FrameLayout {

    private Paint paint;
    private Paint paint1;

    public MyNiceView(@NonNull Context context) {
        super(context);
        init();
    }

    public MyNiceView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyNiceView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
         init();
    }

    private void init() {
        paint = new Paint();
        paint1 = new Paint();
        //设置画笔的颜色
        this.paint.setColor(Color.RED);
        //设置画笔的样式
        this.paint.setStyle(Paint.Style.STROKE);
        //设置画笔文字的大小
        this.paint.setTextSize(66);
        //设置画笔文字的颜色
        this.paint.setStrokeWidth(55);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //写文字
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画线
        canvas.drawLine(50,50,50,300,paint);
        //画圆
        canvas.drawCircle(100,100,80,paint1);

        //画矩形
        Rect rect = new Rect(300,600,400,700);
        canvas.drawRect(rect,paint1);

        Path path = new Path();
        path.moveTo(200,200);//moveTo方法是移动到路径的起点位置
        path.lineTo(500,200);//连接下一个路径点
        path.lineTo(500,400);
//        path.lineTo(200,400);
        path.close();//闭合路径
        canvas.drawPath(path,paint);//画路径

        //二阶贝塞尔曲线
        Path quad = new Path();
        quad.moveTo(0,400);
        quad.quadTo(200,800,400,400);//二阶贝塞尔
        quad.quadTo(600,200,800,400);
        canvas.drawPath(quad,paint);

        //三阶贝塞尔曲线
        Path cquad = new Path();
        cquad.moveTo(0,600);
        cquad.cubicTo(200,1500,600,200,800,600);
        canvas.drawPath(cquad,paint);


        //画文字
        canvas.drawText("贾涵",800,800,paint);
    }
}
