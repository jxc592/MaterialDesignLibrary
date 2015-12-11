package com.guoguang.dksq.customerview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aidl.cilent.library.datepicker.IosDateTimePicker;
import com.aidl.cilent.library.datepicker.IosDateTimePicker.MDate;
import com.aidl.cilent.library.datepicker.IosDateTimePicker.Mode;
import com.guoguang.dksq.R;

public class TimePop extends Dialog {

	public TimePop(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
	}
	
	Button btn_sure,btn_cancel;
	
	IosDateTimePicker DateTimePicker;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_datetime);
		btn_sure = (Button) findViewById(R.id.btn_sure);
		btn_cancel = (Button) findViewById(R.id.btn_cancel);
		DateTimePicker = (IosDateTimePicker) findViewById(R.id.datetimepicker);
		DateTimePicker.setMode(Mode.ShowDate);
		btn_sure.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(onTimeSureClickListener!=null){
					onTimeSureClickListener.onTimeSure(TimePop.this, DateTimePicker.getmDate());
				}else {
					TimePop.this.dismiss();
				}
			}
		});
		btn_cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TimePop.this.dismiss();
			}
		});
	}
	OnTimeSureClickListener onTimeSureClickListener;
	
	public OnTimeSureClickListener getOnTimeSureClickListener() {
		return onTimeSureClickListener;
	}

	public TimePop setOnTimeSureClickListener(
			OnTimeSureClickListener onTimeSureClickListener) {
		this.onTimeSureClickListener = onTimeSureClickListener;
		return this;
	}

	public interface OnTimeSureClickListener{
		void onTimeSure(TimePop timePop, MDate date);
	}
}
