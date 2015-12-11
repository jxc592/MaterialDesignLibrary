package com.guoguang.dksq.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import com.aidl.cilent.util.BaseUtil;
import com.aidl.cilent.util.ToastCoustom;
import com.guoguang.dksq.R;
import com.guoguang.dksq.contants.Contants;
import com.guoguang.dksq.customerview.ListAreaDialog;
import com.guoguang.dksq.database.LoanBusiness;
import com.guoguang.dksq.entity.ValueInfo;


public class OtherInfoLayout extends BaseLayout {

	public OtherInfoLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public OtherInfoLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public OtherInfoLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	EditText et_OperateUserName	,//	经办人
	et_OperateOrgName	,//	经办机构
	et_OperateUserName1	,//	协办客户经理
	et_InputUserName	,//	登记人
	et_InputOrgName	,//	登记机构
	et_InputDate	,//	登记日期
	et_UpdateDate	,//	更新日期
	et_PhaseOpinion ;//意见
	
	@Override
	public View onCreateView() {
		// TODO Auto-generated method stub
		inflater.inflate(R.layout.view_otherinfo, this);
		et_OperateUserName = (EditText) findViewById(R.id.et_OperateUserName);
		et_OperateOrgName = (EditText) findViewById(R.id.et_OperateOrgName);
		et_InputDate = (EditText) findViewById(R.id.et_InputDate);
		et_UpdateDate = (EditText) findViewById(R.id.et_UpdateDate);
		et_InputUserName = (EditText) findViewById(R.id.et_InputUserName);
		et_InputOrgName = (EditText) findViewById(R.id.et_InputOrgName);
		et_OperateUserName1 = (EditText) findViewById(R.id.et_OperateUserName1);
		et_PhaseOpinion = (EditText) findViewById(R.id.et_PhaseOpinion);
		
		et_OperateUserName1.setOnClickListener(new MyOnclick());
		et_PhaseOpinion.setText("同意");
		return null;
	}

	
	@Override
	public boolean checkData() {
		// TODO Auto-generated method stub
		if("".equals(et_PhaseOpinion.getText().toString())){
			ToastCoustom.show("签署意见不能为空");
			return false;
		}
		if("".equals(et_OperateUserName1.getText().toString())){
			ToastCoustom.show("协助客户经理不能为空");
			return false;
		}
		saveData();
		return true;
	}
	
	@Override
	public void saveData() {
		// TODO Auto-generated method stub
		super.saveData();
		String OperateUserName	= et_OperateUserName.getText().toString(),//	经办人
		OperateOrgName = et_OperateOrgName.getText().toString()	,//	经办机构
		OperateUserName1 = et_OperateUserName1.getText().toString()	,//	协办客户经理
		InputUserName = et_InputUserName.getText().toString()	,//	登记人
		InputOrgName = et_InputOrgName.getText().toString()	,//	登记机构
		InputDate = et_InputDate.getText().toString()	,//	登记日期
		UpdateDate = et_UpdateDate.getText().toString()	,//	更新日期
		PhaseOpinion = et_PhaseOpinion.getText().toString() ;//意见
		
		String OperateUserID = Contants.registInfo.getInputUserID();
		String OperateOrgID = Contants.registInfo.getInputOrgID();
		String InputOrgID = Contants.registInfo.getInputOrgID();
		String InputUserID = Contants.registInfo.getInputUserID();

		//操作人
		Contants.loanBusinessInfo.setOperateOrgID(OperateOrgID);
		Contants.loanBusinessInfo.setOperateUserID(OperateUserID);

		//登记机构
		Contants.loanBusinessInfo.setInputOrgID(InputOrgID);
		Contants.loanBusinessInfo.setInputOrgName(InputOrgName);

		//等级人
		Contants.loanBusinessInfo.setInputUserID(InputUserID);
		Contants.loanBusinessInfo.setInputUserName(InputUserName);
		Contants.loanBusinessInfo.setOperateUserName(OperateUserName);
		//登记时间
		Contants.loanBusinessInfo.setInputDate(InputDate);
		Contants.loanBusinessInfo.setUpdateDate(UpdateDate);

		Contants.loanBusinessInfo.setPhaseOpinion(PhaseOpinion);

		Contants.loanBusinessInfo.setOperateUserID(OperateUserID);
		Contants.loanBusinessInfo.setOperateOrgName(OperateOrgName);


		Contants.loanBusinessInfo.setOperateUserName1(OperateUserName1);

	}
	
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
        LoanBusiness loanBusiness = Contants.loanBusinessInfo;
		if(loanBusiness!=null){
            et_OperateUserName .setText(loanBusiness.getInputUserName());
            et_OperateOrgName .setText(loanBusiness.getInputOrgName());
            et_InputDate .setText(loanBusiness.getInputDate());
            et_UpdateDate .setText(loanBusiness.getUpdateDate());
            et_InputUserName .setText(loanBusiness.getInputUserName());
            et_InputOrgName .setText(loanBusiness.getInputUserName());
			et_OperateUserName1 .setText(loanBusiness.getOperateUserName1());
			et_PhaseOpinion .setText(loanBusiness.getPhaseOpinion());
		}
        if(Contants.registInfo!=null){
            et_OperateUserName .setText(Contants.registInfo.getInputUserName());
            et_OperateOrgName .setText(Contants.registInfo.getInputOrgName());
            et_InputUserName .setText(Contants.registInfo.getInputUserName());
            et_InputOrgName .setText(Contants.registInfo.getInputUserName());
        }
        et_UpdateDate.setText(BaseUtil.formatDate());
        et_InputDate.setText(BaseUtil.formatDate());
	}
	
	ValueInfo OperateUser1;

	class MyOnclick implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.et_OperateUserName1:
				new ListAreaDialog(getContext(), "OperateUser1").setOnItemSureClickListener(new ListAreaDialog.OnItemSureClickListener() {
					@Override
					public void OnItemClick(ListAreaDialog dialog,
							ValueInfo node) {
						// TODO Auto-generated method stub
						et_OperateUserName1.setText(node.getName());
						Contants.loanBusinessInfo.setOperateUserID1(node.getID());
						OperateUser1 =node;
						dialog.dismiss();
					}
				}).show();
				break;
			default:
				break;
			}
		}
	}
	
}
