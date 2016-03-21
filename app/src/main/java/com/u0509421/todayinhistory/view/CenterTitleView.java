package com.u0509421.todayinhistory.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Terry on 21/3/16.
 */
public class CenterTitleView extends TextView {

    private StaticLayout mStaticLayout;
    private TextPaint textPaint;

    public CenterTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    private void initView(){
        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(getTextSize());
        textPaint.setColor(getCurrentTextColor());
        mStaticLayout = new StaticLayout(getText(),textPaint,getWidth(), Layout.Alignment.ALIGN_CENTER,1.0f,0.0f,false);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        mStaticLayout.draw(canvas);
    }
}
