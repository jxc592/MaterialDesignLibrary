package com.guoguang.khgl.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import com.aidl.cilent.util.ToastCoustom;
import com.guoguang.dkkhgl.R;
import com.guoguang.khgl.entity.CustomInfo;
import com.guoguang.khgl.model.Contants;

public class ContactInfoLayout extends BaseLayout {
//	LocalLinkMan	,//	联系人
//	LocalAdd	,//	联系人地址
//	LocalTel	,//	联系人电话
//	LocalCellPhone	,//	联系人手机
//	UrgencyLinkMan	,//	紧急联系人
//	RelaToLender	,//	同借款人关系
//	UrgencyAdd	,//	紧急联系人地址
//	UrgencyTel	,//	紧急联系人电话
//	UrgencyCellPhone	,//	紧急联系人手机
//	CustomerResource	,//	客户来源
	
	EditText et_LocalLinkMan	,//	联系人
	et_LocalAdd	,//	联系人地址
	et_LocalTel	,//	联系人电话
	et_LocalCellPhone	,//	联系人手机
	et_UrgencyLinkMan	,//	紧急联系人
	et_RelaToLender	;//	同借款人关系
	//EditText et_UrgencyAdd	,//	紧急联系人地址
	EditText et_UrgencyTel	,//	紧急联系人电话
	//et_UrgencyCellPhone	,//	紧急联系人手机
	et_CustomerResource	;//	客户来源

	public ContactInfoLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public ContactInfoLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public ContactInfoLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	View onCreateView() {
		// TODO Auto-generated method stub
		inflater.inflate(R.layout.view_contactinfo, this);
		
		et_LocalLinkMan	=(EditText) findViewById(R.id.et_LocalLinkMan);//	联系人
		et_LocalAdd	=(EditText) findViewById(R.id.et_LocalAdd);//	联系人地址
		et_LocalTel	=(EditText) findViewById(R.id.et_LocalTel);//	联系人电话
		et_LocalCellPhone	=(EditText) findViewById(R.id.et_LocalCellPhone);//	联系人手机
		et_UrgencyLinkMan	=(EditText) findViewById(R.id.et_UrgencyLinkMan);//	紧急联系人
		et_RelaToLender	=(EditText) findViewById(R.id.et_RelaToLender);//	同借款人关系
		//et_UrgencyAdd	=(EditText) findViewById(R.id.et_UrgencyAdd);//	紧急联系人地址
		et_UrgencyTel	=(EditText) findViewById(R.id.et_UrgencyTel);//	紧急联系人电话
		//et_UrgencyCellPhone	=(EditText) findViewById(R.id.et_UrgencyCellPhone);//	紧急联系人手机
		et_CustomerResource	=(EditText) findViewById(R.id.et_CustomerResource);//	客户来源

		return null;
	}

	@Override
	public boolean getData() {
		// TODO Auto-generated method stub
		String LocalLinkMan	=et_LocalLinkMan.getText().toString(),//	联系人
		LocalAdd	= et_LocalAdd.getText().toString(),//	联系人地址
		LocalTel	= et_LocalTel.getText().toString(),//	联系人电话
		LocalCellPhone	=et_LocalCellPhone.getText().toString(),//	联系人手机
		UrgencyLinkMan	= et_UrgencyLinkMan.getText().toString(),//	紧急联系人
		RelaToLender	=et_RelaToLender.getText().toString(),//	同借款人关系
		UrgencyTel	= et_UrgencyTel.getText().toString(),//	紧急联系人电话
		CustomerResource =et_CustomerResource.getText().toString();//	客户来源
		
		if("".equals(LocalLinkMan)){
			ToastCoustom.show("联系人不能为空");
			return false;
		}

		if("".equals(LocalAdd)){
			ToastCoustom.show("联系地址不能为空");
			return false;
		}

		if("".equals(LocalCellPhone)){
			ToastCoustom.show("联系手机不能为空");
			return false;
		}else if(LocalCellPhone.length()!=11){
			ToastCoustom.show("联系人手机号码长度应为11位");
			return false;
		}

		if("".equals(UrgencyLinkMan)){
			ToastCoustom.show("紧急联系人不能为空");
			return false;
		}

		if("".equals(RelaToLender)){
			ToastCoustom.show("同借款人关系不能为空");
			return false;
		}

		if("".equals(UrgencyTel)){
			ToastCoustom.show("紧急联系人电话不能为空");
			return false;
		}

		if("".equals(CustomerResource)){
			ToastCoustom.show("客户来源不能为空");
			return false;
		}
		
		saveData();
		return super.getData();
	}
	
	@Override
	public void saveData() {
		// TODO Auto-generated method stub
		String LocalLinkMan	=et_LocalLinkMan.getText().toString(),//	联系人
		LocalAdd	= et_LocalAdd.getText().toString(),//	联系人地址
		LocalTel	= et_LocalTel.getText().toString(),//	联系人电话
		LocalCellPhone	=et_LocalCellPhone.getText().toString(),//	联系人手机
		UrgencyLinkMan	= et_UrgencyLinkMan.getText().toString(),//	紧急联系人
		RelaToLender	=et_RelaToLender.getText().toString(),//	同借款人关系
		UrgencyTel	= et_UrgencyTel.getText().toString(),//	紧急联系人电话
		CustomerResource =et_CustomerResource.getText().toString();//	客户来源
		
		
		CustomInfo mCustomInfo = Contants.tempCustomInfo;
		if(mCustomInfo == null){
			mCustomInfo = new CustomInfo();
		}
		mCustomInfo.setLocalLinkMan(LocalLinkMan);
		mCustomInfo.setLocalAdd(LocalAdd);
		mCustomInfo.setLocalTel(LocalTel);
		mCustomInfo.setLocalCellPhone(LocalCellPhone);
		mCustomInfo.setRelaToLender(RelaToLender);
		mCustomInfo.setUrgencyLinkMan(UrgencyLinkMan);
		mCustomInfo.setUrgencyTel(UrgencyTel);
		mCustomInfo.setCustomerResource(CustomerResource);
		Contants.tempCustomInfo = mCustomInfo;
		super.saveData();
	}
	
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
		if(Contants.tempCustomInfo == null){
			return;
		}
		CustomInfo customInfo = Contants.tempCustomInfo;

		et_CustomerResource.setText(customInfo.getCustomerResource());
		et_LocalAdd.setText(customInfo.getLocalAdd());
		et_LocalCellPhone.setText(customInfo.getLocalCellPhone());
		et_LocalLinkMan.setText(customInfo.getLocalLinkMan());
		et_LocalTel.setText(customInfo.getLocalTel());
		et_RelaToLender.setText(customInfo.getRelaToLender());
		et_UrgencyLinkMan.setText(customInfo.getUrgencyLinkMan());
		et_UrgencyTel.setText(customInfo.getUrgencyTel());
		
	}
}
