package com.guoguang.khgl.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import com.aidl.cilent.util.ToastCoustom;
import com.guoguang.dkkhgl.R;
import com.guoguang.khgl.commonview.IncomeProveDialog;
import com.guoguang.khgl.entity.CustomInfo;
import com.guoguang.khgl.model.Contants;

public class FinanceInfoLayout extends BaseLayout {
	
//	MonthIncome	,//	个人月收入
//	YearIncome	,//	个人年收入
//	MonthRent	,//	个人月租金收入
//	OtherMonthIncome	,//	其他月收入
//	FamilyMonthIncome	,//	家庭月收入
//	FamilyAssets	,//	家庭总资产
//	Familyindebted	,//	家庭总负债
//	AlreadyFurnish	,//	已供款额
//	PrepareFurnish	,//	拟供款额
//	PropertyFee	,//	物业管理费(月)
//	IncomeProve	,//	收入证明
//	INVESTMENTFIRM	,//	个人投资企业名称
//	INVESTMENTVALUE	,//	实际投资金额

	
	EditText et_MonthIncome	,//	个人月收入
	//et_YearIncome	,//	个人年收入
	et_MonthRent	,//	个人月租金收入
	et_OtherMonthIncome	,//	其他月收入
	et_FamilyMonthIncome	,//	家庭月收入
	et_FamilyAssets	,//	家庭总资产
	et_Familyindebted	,//	家庭总负债
	et_AlreadyFurnish	,//	已供款额
	et_PrepareFurnish	,//	拟供款额
	et_PropertyFee	,//	物业管理费(月)
	et_IncomeProve	,//	收入证明
	et_INVESTMENTFIRM	,//	个人投资企业名称
	et_INVESTMENTVALUE	;//	实际投资金额

	
	
	public FinanceInfoLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public FinanceInfoLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public FinanceInfoLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	View onCreateView() {
		// TODO Auto-generated method stub
		inflater.inflate(R.layout.view_financeinfo, this);
		et_MonthIncome	= (EditText) findViewById(R.id.et_MonthIncome);//	个人月收入
		//et_YearIncome = (EditText) findViewById(R.id.et_YearIncome);//	个人年收入
		et_MonthRent = (EditText) findViewById(R.id.et_MonthRent);//	个人月租金收入
		et_OtherMonthIncome	= (EditText) findViewById(R.id.et_OtherMonthIncome);//	其他月收入
		et_FamilyMonthIncome	= (EditText) findViewById(R.id.et_FamilyMonthIncome);//	家庭月收入
		et_FamilyAssets	= (EditText) findViewById(R.id.et_FamilyAssets);//	家庭总资产
		et_Familyindebted	= (EditText) findViewById(R.id.et_Familyindebted);//	家庭总负债
		et_AlreadyFurnish	= (EditText) findViewById(R.id.et_AlreadyFurnish);//	已供款额
		et_PrepareFurnish	= (EditText) findViewById(R.id.et_PrepareFurnish);//	拟供款额
		et_PropertyFee	= (EditText) findViewById(R.id.et_PropertyFee);//	物业管理费(月)
		et_IncomeProve	= (EditText) findViewById(R.id.et_IncomeProve);//	收入证明
		et_INVESTMENTFIRM	= (EditText) findViewById(R.id.et_INVESTMENTFIRM);//	个人投资企业名称
		et_INVESTMENTVALUE	= (EditText) findViewById(R.id.et_INVESTMENTVALUE);//	实际投资金额

		et_IncomeProve.setOnClickListener(new MOnclickListener());
		return null;
	}



	@Override
	public boolean getData() {
		// TODO Auto-generated method stub
		String MonthIncome	= et_MonthIncome.getText().toString();//	个人月收入
//		YearIncome	,//	个人年收入
		String MonthRent	= et_MonthRent.getText().toString();//	个人月租金收入
		String OtherMonthIncome	=et_OtherMonthIncome.getText().toString();//	其他月收入
		String FamilyMonthIncome	= et_FamilyMonthIncome.getText().toString();//	家庭月收入
		String FamilyAssets	= et_FamilyAssets.getText().toString();//	家庭总资产
		String Familyindebted	= et_Familyindebted.getText().toString();//	家庭总负债
		String AlreadyFurnish	=et_AlreadyFurnish.getText().toString();//	已供款额
		String PrepareFurnish	=et_PrepareFurnish.getText().toString();//	拟供款额
		String PropertyFee	=et_PropertyFee.getText().toString();//	物业管理费(月)
		String IncomeProve	=et_IncomeProve.getText().toString();//	收入证明
		String INVESTMENTFIRM	=et_INVESTMENTFIRM.getText().toString();//	个人投资企业名称
		String INVESTMENTVALUE	=et_INVESTMENTVALUE.getText().toString();//	实际投资金额
		
		if("".equals(MonthIncome)){
			ToastCoustom.show("个人月收入不能为空");
			return false;
		}
		if("".equals(FamilyAssets)){
			ToastCoustom.show("家庭总资产不能为空");
			return false;
		}

		if("".equals(Familyindebted)){
			ToastCoustom.show("家庭总负债不能为空");
			return false;
		}

		if("".equals(AlreadyFurnish)){
			ToastCoustom.show("已供款额不能为空");
			return false;
		}
		if("".equals(IncomeProve)){
			ToastCoustom.show("收入证明不能为空");
			return false;
		}
		
		saveData();
		return super.getData();
	}

	@Override
	public void saveData() {
		// TODO Auto-generated method stub
		String MonthIncome	= et_MonthIncome.getText().toString();//	个人月收入
//		YearIncome	,//	个人年收入
		String MonthRent	= et_MonthRent.getText().toString();//	个人月租金收入
		String OtherMonthIncome	=et_OtherMonthIncome.getText().toString();//	其他月收入
		String FamilyMonthIncome	= et_FamilyMonthIncome.getText().toString();//	家庭月收入
		String FamilyAssets	= et_FamilyAssets.getText().toString();//	家庭总资产
		String Familyindebted	= et_Familyindebted.getText().toString();//	家庭总负债
		String AlreadyFurnish	=et_AlreadyFurnish.getText().toString();//	已供款额
		String PrepareFurnish	=et_PrepareFurnish.getText().toString();//	拟供款额
		String PropertyFee	=et_PropertyFee.getText().toString();//	物业管理费(月)
		String IncomeProve	=et_IncomeProve.getText().toString();//	收入证明
		String INVESTMENTFIRM	=et_INVESTMENTFIRM.getText().toString();//	个人投资企业名称
		String INVESTMENTVALUE	=et_INVESTMENTVALUE.getText().toString();//	实际投资金额
		
		CustomInfo mCustomInfo = Contants.tempCustomInfo;
		if(mCustomInfo == null){
			mCustomInfo=new CustomInfo();
		}
		mCustomInfo.setMonthIncome(MonthIncome);
		mCustomInfo.setMonthRent(MonthRent);
		mCustomInfo.setOtherMonthIncome(OtherMonthIncome);
		mCustomInfo.setFamilyMonthIncome(FamilyMonthIncome);
		mCustomInfo.setFamilyAssets(FamilyAssets);
		mCustomInfo.setFamilyindebted(Familyindebted);
		mCustomInfo.setAlreadyFurnish(AlreadyFurnish);
		mCustomInfo.setPrepareFurnish(PrepareFurnish);
		mCustomInfo.setPropertyFee(PropertyFee);
		mCustomInfo.setIncomeProve(IncomeProve);
		mCustomInfo.setINVESTMENTFIRM(INVESTMENTFIRM);
		mCustomInfo.setINVESTMENTVALUE(INVESTMENTVALUE);
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

		et_MonthIncome.setText(customInfo.getMonthIncome());
		et_MonthRent.setText(customInfo.getMonthRent());
		et_AlreadyFurnish.setText(customInfo.getAlreadyFurnish());
		et_FamilyAssets.setText(customInfo.getFamilyAssets());
		et_Familyindebted.setText(customInfo.getFamilyindebted());
		et_IncomeProve.setText(customInfo.getIncomeProve());
		et_OtherMonthIncome.setText( customInfo.getOtherMonthIncome());
		et_PrepareFurnish.setText(customInfo.getPrepareFurnish());
		et_PropertyFee.setText(customInfo.getPropertyFee());
		et_INVESTMENTFIRM.setText(customInfo.getINVESTMENTFIRM());
		et_INVESTMENTVALUE.setText(customInfo.getINVESTMENTVALUE());
		et_FamilyMonthIncome.setText(customInfo.getFamilyMonthIncome());
	}


	class MOnclickListener implements OnClickListener{
		@Override
		public void onClick(View view) {
			new IncomeProveDialog(view.getContext()).setOnSureClickListener(new IncomeProveDialog.OnSureClickListener() {
				@Override
				public void onSureClick(IncomeProveDialog dialog, String value) {
					dialog.dismiss();
					et_IncomeProve.setText(value);
				}
			}).show();
		}
	}
}
