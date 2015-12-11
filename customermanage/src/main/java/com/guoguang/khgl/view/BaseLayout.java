package com.guoguang.khgl.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.guoguang.khgl.activity.PreLoadCusinfoActivity;
import com.guoguang.khgl.model.Contants;


public abstract class BaseLayout extends FrameLayout{
	public LayoutInflater inflater = null;
	public PreLoadCusinfoActivity paractivity;
	public BaseLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		paractivity = (PreLoadCusinfoActivity) context;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		onCreateView();
		initData();
	}
	
	public BaseLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		paractivity = (PreLoadCusinfoActivity) context;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		onCreateView();
		initData();
	}

	public BaseLayout(Context context) {
		super(context);
		paractivity = (PreLoadCusinfoActivity) context;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		onCreateView();
		initData();
	}
	
	abstract View onCreateView();
	public boolean getData() {
		return true;
	}
	public void saveData() {
	}
	
	public void initData() {
		if(Contants.tempCustomInfo == null){
			return;
		}
	}


}
