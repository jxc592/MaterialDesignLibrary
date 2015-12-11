package com.guoguang.dksq.widget;

import android.database.DataSetObserver;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.guoguang.dksq.database.CodeLib;

import java.util.List;


public class AutoLoadSpinnerAdapter implements SpinnerAdapter {
	
	List<CodeLib> codeLibs;
	
	
	public AutoLoadSpinnerAdapter(List<CodeLib> codeLibs) {
		super();
		this.codeLibs = codeLibs;
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
		return codeLibs.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return codeLibs.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView textview=new TextView(parent.getContext());
		AbsListView.LayoutParams lParams=new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, 40);
		textview.setLayoutParams(lParams);
		textview.setText(codeLibs.get(position).getITEMNAME());
		textview.setTextColor(0xff292a2a);
		textview.setGravity(Gravity.CENTER_VERTICAL);
		textview.setPadding(10, 0, 0, 0);
		textview.setTextSize(16);
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
		TextView textview=new TextView(parent.getContext());
		AbsListView.LayoutParams lParams=new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, 40);
		textview.setLayoutParams(lParams);
		textview.setText(codeLibs.get(position).getITEMNAME());
		textview.setTextColor(0xff292a2a);
		textview.setGravity(Gravity.CENTER_VERTICAL);
		textview.setPadding(18, 0, 0, 0);
		return textview;
	}

}
