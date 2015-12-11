package com.guoguang.dksq.adapter;

import android.database.DataSetObserver;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.List;

public class DataAdapter implements SpinnerAdapter{

	public List<String> dataList = null;
	public DataAdapter(List<String>  daList) {
		super();
		dataList = daList;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dataList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView textview = new TextView(parent.getContext());
		AbsListView.LayoutParams lParams = new AbsListView.LayoutParams(
				300, 40);
		textview.setLayoutParams(lParams);
		textview.setText(dataList.get(position));
		textview.setTextColor(0xff2a2929);
		textview.setGravity(Gravity.CENTER_VERTICAL);
		textview.setPadding(10, 0, 0, 0);
		textview.setTextSize(18);
		return textview;
	}

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView textview = new TextView(parent.getContext());
		AbsListView.LayoutParams lParams = new AbsListView.LayoutParams(
				300, 40);
		textview.setLayoutParams(lParams);
		textview.setTextColor(0xff2a2929);
		textview.setText(dataList.get(position));
		textview.setGravity(Gravity.CENTER_VERTICAL);
		textview.setPadding(20, 0, 0, 0);
		return textview;
	}

}
