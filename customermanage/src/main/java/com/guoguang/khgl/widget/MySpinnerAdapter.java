package com.guoguang.khgl.widget;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

/**
	 * 
	 * @author tk
	 *
	 */
	public class MySpinnerAdapter implements SpinnerAdapter{
		
		List<String> list;
		
		public List<String> getList() {
			return list;
		}

		public void setList(List<String> list) {
			this.list = list;
		}
		
		public MySpinnerAdapter(List<String> list) {
			super();
			this.list = list;
		}
		
		Context mContext;
		int srcId;
		
		
		public MySpinnerAdapter(int srcId,Context mContext) {
			super();
			this.mContext = mContext;
			this.srcId = srcId;
			String[] arrayString=mContext.getResources().getStringArray(srcId);
			list = new ArrayList<>();
			for(int i=0;i<arrayString.length;i++){
				list.add(arrayString[i]);
			}
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
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
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
			TextView textview=new TextView(parent.getContext());
			AbsListView.LayoutParams lParams=new AbsListView.LayoutParams(300, 40);
			textview.setLayoutParams(lParams);
			textview.setText(list.get(position));
			textview.setTextColor(0xff292a2a);
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
		public View getDropDownView(int position, View convertView,
				ViewGroup parent) {
			// TODO Auto-generated method stub
			TextView textview=new TextView(parent.getContext());
			AbsListView.LayoutParams lParams=new AbsListView.LayoutParams(300, 40);
			textview.setLayoutParams(lParams);
			textview.setText(list.get(position));
			textview.setTextColor(0xff292a2a);
			textview.setGravity(Gravity.CENTER_VERTICAL);
			textview.setPadding(20, 0, 0, 0);
			return textview;
		}
	}