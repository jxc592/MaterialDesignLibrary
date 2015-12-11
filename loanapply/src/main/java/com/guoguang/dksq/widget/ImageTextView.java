package com.guoguang.dksq.widget;


import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Spanned;
import android.util.AttributeSet;
import android.widget.TextView;

import com.guoguang.dksq.R;

public class ImageTextView extends TextView {

	public ImageTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context, attrs, defStyle);
		initText(context);
	}

	public ImageTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initView(context, attrs, 0);
		initText(context);
	}

	public ImageTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initView(context, null, 0);
		initText(context);
	}
	
	String unit="";
	
	void initView(Context mcontext,AttributeSet attrs,
			int defStyleAttr){
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


	String srcstr ;
	void initText(final Context context){
		if("no".equals(unit)){
			String textString = getText().toString();
			String mSpanned =textString+"：";
			setText(mSpanned);
		}else {
			String textString = getText().toString();
			srcstr=  textString;
			String zp_content = textString+"：   <img src=\"" +R.drawable.bitians +"\" />";
			Spanned mSpanned=Html.fromHtml(zp_content, new ImageGetter() {
				@Override
				public Drawable getDrawable(String source) {
					// TODO Auto-generated method stub
					Drawable mDrawable=null;
//					try{
//						int resid=Integer.parseInt(source);
//						mDrawable=context.getResources().getDrawable(resid);
//					}catch (Exception e) {
//						// TODO: handle exception
						mDrawable=context.getResources().getDrawable(R.drawable.bitians);
//					}
					mDrawable.setBounds(0, 0, mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight()); 
					return mDrawable;
				}
			},null);
			setText(mSpanned);
		}
	}

	public void setNecessary(boolean necessary){
		if(necessary){
			String zp_content = srcstr+"：   <img src=\"" +R.drawable.bitians +"\" />";
			Spanned mSpanned=Html.fromHtml(zp_content, new ImageGetter() {
				@Override
				public Drawable getDrawable(String source) {
					// TODO Auto-generated method stub
					Drawable mDrawable=null;
//					try{
//						int resid=Integer.parseInt(source);
//						mDrawable=context.getResources().getDrawable(resid);
//					}catch (Exception e) {
//						// TODO: handle exception
					mDrawable=getContext().getResources().getDrawable(R.drawable.bitians);
//					}
					mDrawable.setBounds(0, 0, mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight());
					return mDrawable;
				}
			},null);
			setText(mSpanned);
		}else {
			setText(srcstr);
		}
	}
}
