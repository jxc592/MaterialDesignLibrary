package com.guoguang.dksq.customerview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.aidl.cilent.library.datepicker.IosDateTimePicker;
import com.aidl.cilent.util.ToastCoustom;
import com.guoguang.dksq.R;

public class AddGurDialog extends Dialog {

	public AddGurDialog(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
	}
	
	Button btn_sure,btn_cancel;
	
	EditText et_FullName,et_CertID,et_phone;
	CheckBox cb_isAgree;
	IosDateTimePicker DateTimePicker;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_addgyr);
		btn_sure = (Button) findViewById(R.id.btn_sure);
		btn_cancel = (Button) findViewById(R.id.btn_cancel);
		
		et_FullName = (EditText) findViewById(R.id.et_FullName);
		et_CertID= (EditText) findViewById(R.id.et_CertID);
		et_phone = (EditText) findViewById(R.id.et_phone);
		
		cb_isAgree = (CheckBox) findViewById(R.id.cb_agreegur);
		
		
		btn_sure.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String fullName = et_FullName.getText().toString();
				String certID = et_CertID.getText().toString();
				String phone = et_phone.getText().toString();
				
				if("".equals(fullName)||"".equals(certID)||"".equals(phone)){
					ToastCoustom.show("基础信息不能为空");
					return;
				}
				String value = "";
				if(cb_isAgree.isChecked()){
					value = "产权人姓名："+fullName+"\n"+"身份证号码："+certID +"\n" +"联系电话："+phone+"\n" +"是否同意抵押："+"是";
				}else {
					value = "产权人姓名："+fullName+"\n"+"身份证号码："+certID +"\n" +"联系电话："+phone+"\n" +"是否同意抵押："+"否";
				}
				
				if(onSureClickListener!=null){
					onSureClickListener.onSureClick(AddGurDialog.this, value);
				}else {
					AddGurDialog.this.dismiss();
				}
			}
		});
		btn_cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AddGurDialog.this.dismiss();
			}
		});
		
		
	}
	OnSureClickListener onSureClickListener;
	
	public OnSureClickListener getOnTimeSureClickListener() {
		return onSureClickListener;
	}

	

	public AddGurDialog setOnSureClickListener(OnSureClickListener onSureClickListener) {
		this.onSureClickListener = onSureClickListener;
		return this;
	}



	public interface OnSureClickListener{
		void onSureClick(AddGurDialog dialog, String value);
	}
}
