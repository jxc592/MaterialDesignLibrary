package com.guoguang.dksq.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.method.MovementMethod;
import android.util.AttributeSet;

import com.guoguang.dksq.R;


public class WithUnitEditText extends AppCompatEditText{


    public WithUnitEditText(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		initView(context, attrs, defStyleAttr);
	}

	public WithUnitEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initView(context, attrs, android.R.style.Widget_EditText);
	}

	public WithUnitEditText(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initView(context, null, android.R.style.Widget_EditText);
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
    public void extendSelection(int index) {
        super.extendSelection(index);
    }

    @Override
    public CharSequence getAccessibilityClassName() {
        return super.getAccessibilityClassName();
    }

    @Override
    protected boolean getDefaultEditable() {
        return super.getDefaultEditable();
    }

    @Override
    protected MovementMethod getDefaultMovementMethod() {
        return super.getDefaultMovementMethod();
    }

    @Override
    public Editable getText() {
        return super.getText();
    }

    @Override
    public void selectAll() {
        super.selectAll();
    }

    @Override
    public void setEllipsize(TextUtils.TruncateAt ellipsis) {
        super.setEllipsize(ellipsis);
    }

    @Override
    public void setSelection(int index) {
        super.setSelection(index);
    }

    @Override
    public void setSelection(int start, int stop) {
        super.setSelection(start, stop);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
    }

    @Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		float height = getHeight()/2+5;
		float tosp =unit.length()*15;
		float width = getWidth()-(10+tosp);
		canvas.drawText(unit,width,height, mPaint);

	}
}
