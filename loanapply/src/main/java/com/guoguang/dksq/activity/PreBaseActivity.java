package com.guoguang.dksq.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.aidl.cilent.util.ToastCoustom;
import com.guoguang.dksq.R;
import com.guoguang.dksq.adapter.StepAdapter;
import com.guoguang.dksq.contants.Contants;

import java.util.List;

public class PreBaseActivity extends AppCompatActivity{
	ListView lv_step;
	List<String> stepStrList;
	protected Button btn_last, btn_save, btn_next;
	FrameLayout contentframe;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pre_base);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Contants.defaultcolor));
		getSupportActionBar().setElevation(2);

		btn_last = (Button) findViewById(R.id.btn_last);
		btn_next = (Button) findViewById(R.id.btn_next);
		btn_save = (Button) findViewById(R.id.btn_save);
		lv_step = (ListView) findViewById(R.id.lv_leftnav);
		contentframe= (FrameLayout) findViewById(R.id.fy_content);
		
		btn_last.setOnClickListener(new ButtonClick());
		btn_next.setOnClickListener(new ButtonClick());
		btn_save.setOnClickListener(new ButtonClick());
	}

	boolean waitAble=false;
	boolean hasRunnuable = false;
	class ButtonClick implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_last:
				onLast();
				break;
			case R.id.btn_next:
				if(!waitAble){
					waitAble=true;
					onNext();
				}else{
					if(!hasRunnuable){
						hasRunnuable = true;
						v.postDelayed(new Runnable() {
							public void run() {
								waitAble = false;
								hasRunnuable = false;
								onNext();
							}
						}, 100);
					}else {
						ToastCoustom.show("您点击过快");
					}
				}
				break;
			case R.id.btn_save:
				onSave();
				break;
			default:
				break;
			}
		}
	}

	protected void onLast() {

	}

	protected void onNext() {

	}

	protected void onSave() {

	}
	
	public void setTaskView(View view){
		contentframe.removeAllViews();
		contentframe.addView(view);
	}
	
	public void setStepList(List<String> list,int position){
		lv_step.setAdapter(new StepAdapter(list,position));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
			case android.R.id.home:
				PreBaseActivity.this.finish();
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}
