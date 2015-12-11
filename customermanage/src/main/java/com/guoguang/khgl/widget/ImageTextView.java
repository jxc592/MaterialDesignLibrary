package com.guoguang.khgl.widget;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Spanned;
import android.util.AttributeSet;
import android.widget.TextView;

import com.guoguang.dkkhgl.R;

public class ImageTextView extends TextView {

	public ImageTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initText(context);
	}

	public ImageTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initText(context);
	}

	public ImageTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initText(context);
	}
	
	void initText(final Context context){
		String textString = getText().toString();
		String zp_content = textString+"：    <img src=\"" + R.drawable.bitians +"\" />";
		Spanned mSpanned=Html.fromHtml(zp_content, new ImageGetter() {
			@Override
			public Drawable getDrawable(String source) {
				// TODO Auto-generated method stub
				Drawable mDrawable=null;
				try{
					int resid=Integer.parseInt(source);
					mDrawable=context.getResources().getDrawable(resid);
				}catch (Exception e) {
					// TODO: handle exception
					mDrawable=context.getResources().getDrawable(R.drawable.bitians);
				}
				mDrawable.setBounds(0, 0, mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight()); 
				return mDrawable;
			}
		},null);
		setText(mSpanned);
	}
}
