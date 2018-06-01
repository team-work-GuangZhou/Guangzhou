package com.stardust.tool.divider;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.stardust.guangzhou.R;

/**
 * Created by Bob on 2017/11/25.
 */

public class ColourLineView extends View{
    //线条高度
    private float line_height;
    //每个颜色块的宽度
    private float item_width;
    //每两个颜色快之间的间距
    private float separation_width;
    //平行四边形倾斜的程度
    private float lean_degree;
    //第一种颜色块的颜色
    private int first_color;
    //第二种颜色块的颜色
    private int second_color;
    //线条底色
    private int canvas_color;

    public ColourLineView(Context context) {
        super(context, null);
    }

    public ColourLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(context, attrs);
    }

    public ColourLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
    }

    public void initAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ColourLineView);
        line_height = typedArray.getDimension(R.styleable.ColourLineView_line_height, 20);
        item_width = typedArray.getDimension(R.styleable.ColourLineView_item_width, 20);
        separation_width = typedArray.getDimension(R.styleable.ColourLineView_separation_width, 20);
        lean_degree = typedArray.getDimension(R.styleable.ColourLineView_lean_degree, 5);
        first_color = typedArray.getColor(R.styleable.ColourLineView_first_color, Color.RED);
        second_color = typedArray.getColor(R.styleable.ColourLineView_second_color, Color.GREEN);
        canvas_color = typedArray.getColor(R.styleable.ColourLineView_canvas_color, Color.WHITE);
        typedArray.recycle();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();
        int lineWidth = getWidth();
        int lineHeight = getHeight();
        int count = (item_width + separation_width == 0) ? 0 : lineWidth / (int) (item_width + separation_width) + 1;
        for(int i=0; i < count; i++){
            canvas.save();
            path.reset();//重置路径
            path.moveTo(lean_degree + (item_width + separation_width) * i, 0);//左上点
            path.lineTo((item_width + separation_width) * i, lineHeight);//左下点
            path.lineTo(item_width * (i + 1) + separation_width * i, lineHeight);//右下点
            path.lineTo(lean_degree + item_width * (i + 1) + separation_width * i, 0);//右上点
            canvas.clipPath(path);//截取路径所绘制的图形
            if(i % 2 == 0){
                canvas.drawColor(first_color);
            }else{
                canvas.drawColor(second_color);
            }
            canvas.restore();
        }
    }
}
