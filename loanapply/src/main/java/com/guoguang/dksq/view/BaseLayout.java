package com.guoguang.dksq.view;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;


public abstract class BaseLayout extends FrameLayout{
	public LayoutInflater inflater = null;
	public BaseLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		onCreateView();
		initData();
	}
	
	public BaseLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		onCreateView();
		initData();
	}

	public BaseLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		onCreateView();
		initData();
	}
	
	public abstract View onCreateView();
	
	public void saveData(){}
	
	public void initData(){}
	
	public boolean checkData(){
		return true;
	}
}
