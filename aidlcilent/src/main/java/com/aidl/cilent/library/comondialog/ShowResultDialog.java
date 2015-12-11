package com.aidl.cilent.library.comondialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aidl.cilent.R;


public class ShowResultDialog extends Dialog {
	
	public static final int SUCCESS_TYPR=0;
	public static final int FAILED_TYPR=1;
	public static final int NORESULT_TYPE=2;
	private int Type;
	TextView showResultTextView;
	Button sureButton;
	ImageView imageView;
	Button cancelButton;
	public ShowResultDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		setCancelable(false);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_transationresult);
		showResultTextView=(TextView) findViewById(R.id.tv_showreult);
		cancelButton=(Button) findViewById(R.id.btn_cancel);
		cancelButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ShowResultDialog.this.dismiss();
				if(onCancelClickListener!=null)
					onCancelClickListener.onCancel();
			}
		});
		sureButton=(Button) findViewById(R.id.btn_sure);imageView=(ImageView) findViewById(R.id.imageView_type);
		sureButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ShowResultDialog.this.dismiss();
				if(onSureClickListener!=null){
					onSureClickListener.onSure();
				}
			}
		});
		if(suretextString!=null){
			sureButton.setText(suretextString);
			sureButton.setVisibility(View.VISIBLE);
		}
		
		if(canceltextString!=null){
			cancelButton.setText(canceltextString);
			cancelButton.setVisibility(View.VISIBLE);
		}
		if(messageString!=null){
			showResultTextView.setText(messageString);
		}
		if(Type==SUCCESS_TYPR){
			imageView.setBackgroundResource(R.drawable.success);
		}else if(Type==NORESULT_TYPE) {
			imageView.setVisibility(View.INVISIBLE);;
		}else if(Type==FAILED_TYPR){
			imageView.setBackgroundResource(R.drawable.failed);
		}
	}
	String canceltextString;
	String suretextString;
	String messageString;
	public ShowResultDialog setCancelText(String text){
		canceltextString=text;
		return this;
	}
	public ShowResultDialog setSureText(String text){
		suretextString=text;
		return this;
	}
	public ShowResultDialog setType(int tType){
		this.Type=tType;
		return this;
	}
	
	public ShowResultDialog setMessage(String message){
		this.messageString=message;
		return this;
	}
	


	public OnSureClickListener getOnSureClickListener() {
		return onSureClickListener;
	}
	public ShowResultDialog setOnSureClickListener(OnSureClickListener onSureClickListener) {
		this.onSureClickListener = onSureClickListener;
		return ShowResultDialog.this;
	}
	public OnCancelClickListener getOnCancelClickListener() {
		return onCancelClickListener;
	}
	public ShowResultDialog setOnCancelClickListener(OnCancelClickListener onCancelClickListener) {
		this.onCancelClickListener = onCancelClickListener;
		return this;
	}

	OnSureClickListener onSureClickListener;
	OnCancelClickListener onCancelClickListener;
	public interface OnSureClickListener {
		void onSure();
	}
	public interface OnCancelClickListener {
		void onCancel();
	}
}
