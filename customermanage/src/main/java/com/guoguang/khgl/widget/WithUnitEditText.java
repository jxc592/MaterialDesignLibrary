package com.guoguang.khgl.widget;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

import com.guoguang.dkkhgl.R;

public class WithUnitEditText extends EditText{

	@SuppressLint("NewApi")
	public WithUnitEditText(Context context, AttributeSet attrs,
			int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		// TODO Auto-generated constructor stub
		initView(context, attrs, defStyleAttr);
	}

	public WithUnitEditText(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		initView(context, attrs, defStyleAttr);
	}

	public WithUnitEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initView(context, attrs, 0);
	}

	public WithUnitEditText(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initView(context, null, 0);
	}
	String unit="";
	Paint mPaint;
	void initView(Context mcontext,AttributeSet attrs,
			int defStyleAttr){
		 mPaint = new Paint();
		 mPaint.setColor(Color.DKGRAY);
		 mPaint.setTextSize(16);
		 mPaint.setAntiAlias(true);
		 final Resources.Theme theme = mcontext.getTheme();
	     TypedArray a = theme.obtainStyledAttributes(attrs,
	                R.styleable.withunitEdittext, defStyleAttr,0);
	    
		if (a != null) {
			int n = a.getIndexCount();
			for (int i = 0; i < n; i++) {
				int attr = a.getIndex(i);
				switch (attr) {
				case R.styleable.withunitEdittext_unittext:
					unit = a.getString(attr);
					break;
				default:
					break;
				}
			}
		}
		a.recycle();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		float width = getWidth()-30;
		float height = getHeight()/2+5;
		canvas.drawText(unit,width,height, mPaint);
	}
}
