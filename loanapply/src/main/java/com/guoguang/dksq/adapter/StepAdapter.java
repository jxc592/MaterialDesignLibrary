package com.guoguang.dksq.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guoguang.dksq.R;
import com.guoguang.dksq.contants.Contants;

import java.util.List;

@SuppressLint("ViewHolder")
public class StepAdapter extends BaseAdapter {

	List<String> mList;
	Context mContext;
	int positionStep;

	public StepAdapter(List<String> mList, 
			int position) {
		super();
		this.mList = mList;
		this.positionStep = position;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView ==null){
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,
					null);
		}
		TextView tv_step = (TextView) convertView.findViewById(R.id.tv_step);
		LinearLayout ll_step=(LinearLayout)convertView.findViewById(R.id.ll_step);
		tv_step.setText(mList.get(position));
		if (position == positionStep) {
			ll_step.setBackgroundColor(Contants.othercolor);
			tv_step.setTextColor(Color.WHITE);
		} else if(position < positionStep){
			ll_step.setBackgroundColor(Color.argb(00, 00, 00, 00));
			tv_step.setTextColor(Color.DKGRAY);
		}else if(position > positionStep){
			tv_step.setTextColor(Color.DKGRAY);
			ll_step.setBackgroundColor(Color.argb(00, 00, 00, 00));
		}
		return convertView;
	}
	
	View currentView = null;

	public View getCurrentView() {
		return currentView;
	}

	public void setCurrentView(View currentView) {
		this.currentView = currentView;
	}
	
}
