package com.guoguang.khgl.widget;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.guoguang.dkkhgl.R;


public class ChooseTimeDialogBuilder extends AlertDialog.Builder {
	View contentView;
	TimePicker timePicker;
	DatePicker datePicker;
	OnTimeSureClickListener onTimeSureClickListener;
	String time;
	@SuppressLint("InflateParams")
	public ChooseTimeDialogBuilder(Context arg0) {
		super(arg0);		
		contentView=LayoutInflater.from(arg0).inflate(R.layout.dialog_datatimepicker, null);
		datePicker=(DatePicker) contentView.findViewById(R.id.datePicker1);
//		timePicker=(TimePicker) contentView.findViewById(R.id.timePicker1);
//		timePicker.setIs24HourView(true);				
//		this.contentView = contentView;
		setView(contentView);
		setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if(onTimeSureClickListener!=null){
					onTimeSureClickListener.OnClick(dialog,getTime());
				}
			}
		});
		
	}
	public String getTime(){
//		int hour=timePicker.getCurrentHour();
//		int minite=timePicker.getCurrentMinute();
		
		int day=datePicker.getDayOfMonth();
		int year=datePicker.getYear();
		int month=datePicker.getMonth()+1;
		

		String daystring;
		String monthString;
		if(month <10){
			monthString="0"+month;
		}else {
			monthString=month+"";
		}
		if(day<10){
			daystring="0"+day;
		}else {
			daystring=day+"";
		}
//		return year+"-"+month+"-"+day+" "+hour+":"+minite;	
		return year+monthString+daystring;
	}
	
	public OnTimeSureClickListener getOnTimeSureClickListener() {
		return onTimeSureClickListener;
	}
	public ChooseTimeDialogBuilder setOnTimeSureClickListener(
			OnTimeSureClickListener onTimeSureClickListener) {
		this.onTimeSureClickListener = onTimeSureClickListener;
		return this;
	}

	public interface OnTimeSureClickListener{
		void  OnClick(DialogInterface dialog, String time);
	}	
	
}
