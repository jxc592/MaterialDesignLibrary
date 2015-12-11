package com.guoguang.dksq.view;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import com.aidl.cilent.library.datepicker.IosDateTimePicker.MDate;
import com.aidl.cilent.util.ToastCoustom;
import com.guoguang.dksq.R;
import com.guoguang.dksq.contants.Contants;
import com.guoguang.dksq.customerview.AddGurDialog;
import com.guoguang.dksq.customerview.AddGurDialog.OnSureClickListener;
import com.guoguang.dksq.customerview.ListAreaDialog;
import com.guoguang.dksq.customerview.TimePop;
import com.guoguang.dksq.customerview.TreeDialog;
import com.guoguang.dksq.database.House;
import com.guoguang.dksq.entity.ValueInfo;
import com.guoguang.dksq.util.TreeModelBean;
import com.guoguang.dksq.widget.AutoLoadSpinner;
import com.guoguang.dksq.widget.ImageTextView;


public class HostageInfoLayout extends BaseLayout {

	public HostageInfoLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public HostageInfoLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public HostageInfoLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	EditText et_GuarantyType;//抵押物类型
	AutoLoadSpinner sp_CertType;//权利人证件类型
	EditText et_CertID;//权利人证件号码
	EditText et_OwnerName;//权利人名称
	AutoLoadSpinner sp_OwnerType;//权利人类型
	EditText et_Togetheronwer;//共有人信息
	AutoLoadSpinner sp_GuarantySubType;//房产类型
	AutoLoadSpinner sp_HourseFormat;//房产状况
	EditText et_GuarantyLocation;//房屋详细地址
	EditText et_GuarantyArea;//抵押物区域地点
	EditText et_GuarantyAmount;//房屋建筑面积
	EditText et_GuarantyDescript;//抵押物说明
	AutoLoadSpinner sp_EvalMethod;//抵押物评估方式
	EditText et_EvalOrgName;//评估机构名称
	EditText et_EvalDate;//价值评估时间
	EditText et_EvalNetValue;//抵押物评估价值
	AutoLoadSpinner sp_GuarantyCurrency;//抵押物债权币种
	EditText et_ConfirmValue;//抵押物债券价值
	
	FloatingActionButton btn_add;
	ImageTextView itv_EvalOrgName;
	@Override
	public View onCreateView() {
		// TODO Auto-generated method stub
		inflater.inflate(R.layout.view_hostage, this);

		et_GuarantyType = (EditText) findViewById(R.id.et_GuarantyType);//抵押物类型
		sp_CertType=(AutoLoadSpinner) findViewById(R.id.sp_CertType);//权利人证件类型
		et_CertID=(EditText) findViewById(R.id.et_CertID);//权利人证件号码
		et_OwnerName=(EditText) findViewById(R.id.et_OwnerName);//权利人名称
		sp_OwnerType=(AutoLoadSpinner) findViewById(R.id.sp_OwnerType);//权利人类型
		et_Togetheronwer=(EditText) findViewById(R.id.et_Togetheronwer);//共有人信息
		sp_GuarantySubType=(AutoLoadSpinner) findViewById(R.id.sp_GuarantySubType);//房产类型
		sp_HourseFormat=(AutoLoadSpinner) findViewById(R.id.sp_HourseFormat);//房产状况
		et_GuarantyLocation=(EditText) findViewById(R.id.et_GuarantyLocation);//房屋详细地址
		et_GuarantyArea=(EditText) findViewById(R.id.et_GuarantyArea);//抵押物区域地点
		et_GuarantyAmount=(EditText) findViewById(R.id.et_GuarantyAmount);//房屋建筑面积
		et_GuarantyDescript=(EditText) findViewById(R.id.et_GuarantyDescript);//抵押物说明
		sp_EvalMethod=(AutoLoadSpinner) findViewById(R.id.sp_EvalMethod);//抵押物评估方式
		et_EvalOrgName=(EditText) findViewById(R.id.et_EvalOrgName);//评估机构名称
		et_EvalDate=(EditText) findViewById(R.id.et_EvalDate);//价值评估时间
		et_EvalNetValue=(EditText) findViewById(R.id.et_EvalNetValue);//抵押物评估价值
		sp_GuarantyCurrency=(AutoLoadSpinner) findViewById(R.id.sp_GuarantyCurrency);//抵押物债权币种
		et_ConfirmValue=(EditText) findViewById(R.id.et_ConfirmValue);//抵押物债券价值

		itv_EvalOrgName = (ImageTextView) findViewById(R.id.itv_EvalOrgName);
		btn_add = (FloatingActionButton) findViewById(R.id.btn_add);

        sp_EvalMethod.setOnItemSelectedListener(new MyOnItemSlectedListener());
		et_EvalOrgName.setOnClickListener(new MyOnclick());
		et_EvalDate.setOnClickListener(new MyOnclick());
		btn_add.setOnClickListener(new MyOnclick());
		et_GuarantyArea.setOnClickListener(new MyOnclick());

		//房产类型 ，普通住宅
		sp_CertType.setCode("Ind01");
		sp_GuarantySubType.setCode("01");
		sp_HourseFormat.setCode("01");
		sp_OwnerType.setCode("06");
		sp_GuarantyCurrency.setCode("01");
        et_GuarantyType .setText("抵押-房产");//抵押物类型
		return null;
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		if(Contants.houseInfo==null){
			return;
		}
		et_GuarantyType .setText("抵押-房产");//抵押物类型
		sp_CertType.setCode(Contants.houseInfo.getCertType());//权利人证件类型
		et_CertID .setText(Contants.houseInfo.getCertID());//权利人证件号码
		et_OwnerName .setText(Contants.houseInfo.getOwnerName());//权利人名称
		sp_OwnerType.setCode(Contants.houseInfo.getOwnerType());//权利人类型
		et_Togetheronwer .setText(Contants.houseInfo.getTogetheronwer());//共有人信息
		sp_GuarantySubType.setCode(Contants.houseInfo.getGuarantySubType());//房产类型
		sp_HourseFormat.setCode(Contants.houseInfo.getHourseFormat());//房产状况
		et_GuarantyLocation .setText(Contants.houseInfo.getGuarantyLocation());//房屋详细地址
		et_GuarantyArea .setText(Contants.houseInfo.getGuarantyAreaName());//抵押物区域地点
		et_GuarantyAmount .setText(Contants.houseInfo.getGuarantyAmount());//房屋建筑面积
		et_GuarantyDescript .setText(Contants.houseInfo.getGuarantyDescript());//抵押物说明
		sp_EvalMethod.setCode(Contants.houseInfo.getEvalMethod());//抵押物评估方式
		et_EvalOrgName .setText(Contants.houseInfo.getEvalOrgName());//评估机构名称
		et_EvalDate .setText(Contants.houseInfo.getEvalDate());//价值评估时间
		et_EvalNetValue .setText(Contants.houseInfo.getEvalNetValue());//抵押物评估价值
		sp_GuarantyCurrency.setCode(Contants.houseInfo.getGuarantyCurrency());//抵押物债权币种
		et_ConfirmValue .setText(Contants.houseInfo.getConfirmValue());//抵押物债券价值

		super.initData();
	}
	@Override
	public boolean checkData() {
		// TODO Auto-generated method stub
//		String GuarantyType = et_GuarantyType.getText().toString();//抵押物类型
		String CertType = sp_CertType.getSelectedCode();//权利人证件类型
		String CertID = et_CertID.getText().toString();//权利人证件号码
		String OwnerName = et_OwnerName.getText().toString();//权利人名称
		String OwnerType = sp_OwnerType.getSelectedCode();//权利人类型
//		String Togetheronwer = et_Togetheronwer.getText().toString();//共有人信息
		String GuarantySubType = sp_GuarantySubType.getSelectedCode();//房产类型
		String HourseFormat = sp_HourseFormat.getSelectedCode();//房产状况
		String GuarantyLocation = et_GuarantyLocation.getText().toString();//房屋详细地址
		String GuarantyAmount = et_GuarantyAmount.getText().toString();//房屋建筑面积
		String GuarantyDescript = et_GuarantyDescript.getText().toString();//抵押物说明
		String EvalMethod = sp_EvalMethod.getSelectedCode();//抵押物评估方式
		String EvalOrgName = et_EvalOrgName.getText().toString();//评估机构名称
		String EvalDate = et_EvalDate.getText().toString();//价值评估时间
		String EvalNetValue = et_EvalNetValue.getText().toString();//抵押物评估价值
		String GuarantyCurrency = sp_GuarantyCurrency.getSelectedCode();//抵押物债权币种
		String ConfirmValue = et_ConfirmValue.getText().toString();//抵押物债券价值
//
//		if("".equals(GuarantyType)){
//			ToastCoustom.show("抵押物类型不能为空");
//			return false;
//		}
		if("".equals(CertType)){
			ToastCoustom.show("权利人证件类型不能为空");
			return false;
		}
		if("".equals(CertID)){
			ToastCoustom.show("权利人证件号码不能为空");
			return false;
		}
		if("".equals(OwnerName)){
			ToastCoustom.show("权利人名称不能为空");
			return false;
		}
		if("".equals(OwnerType)){
			ToastCoustom.show("权利人类型不能为空");
			return false;
		}
//		if("".equals(Togetheronwer)){
//			ToastCoustom.show("共有人信息不能为空");
//			return false;
//		}
		if("".equals(GuarantySubType)){
			ToastCoustom.show("房产类型不能为空");
			return false;
		}
		if("".equals(HourseFormat)){
			ToastCoustom.show("房产状况不能为空");
			return false;
		}
		if("".equals(GuarantyLocation)){
			ToastCoustom.show("房屋详细地址不能为空");
			return false;
		}
		//抵押物区域地点的检查 特别 ，首先判断是否存在数据更新。
		if(Contants.houseInfo!=null){
			if("".equals(Contants.houseInfo.getGuarantyArea())){
				ToastCoustom.show("抵押物区域地点不能为空");
				return false;
			}
		}
		if("".equals(GuarantyAmount)){
			ToastCoustom.show("房屋建筑面积不能为空");
			return false;
		}
		if("".equals(GuarantyDescript)){
			ToastCoustom.show("抵押物说明不能为空");
			return false;
		}
		if("".equals(EvalMethod)){
			ToastCoustom.show("抵押物评估方式不能为空");
			return false;
		}
		if("01".equals(sp_EvalMethod.getSelectedCode())){
			if("".equals(EvalOrgName)){
				ToastCoustom.show("抵押物评估机构不能为空");
				return false;
			}
		}

		if("".equals(EvalDate)){
			ToastCoustom.show("抵押物评估时间不能为空");
			return false;
		}
		if("".equals(EvalNetValue)){
			ToastCoustom.show("抵押物评估价值不能为空");
			return false;
		}
		if("".equals(GuarantyCurrency)){
			ToastCoustom.show("抵押物债权币种不能为空");
			return false;
		}
		if("".equals(ConfirmValue)){
			ToastCoustom.show("抵押物债权价值不能为空");
			return false;
		}
        if(Float.parseFloat(ConfirmValue)>Float.parseFloat(EvalNetValue)){
            ToastCoustom.show("抵押物债权价值不能高于抵押物评估价值");
            return false;
        }
		saveData();
		return super.checkData();
	}
	
	@Override
	public void saveData() {
		// TODO Auto-generated method stub
		super.saveData();
//		String GuarantyType = et_GuarantyType.getText().toString();//抵押物类型
		String CertType = sp_CertType.getSelectedCode();//权利人证件类型
		String CertID = et_CertID.getText().toString();//权利人证件号码
		String OwnerName = et_OwnerName.getText().toString();//权利人名称
		String OwnerType = sp_OwnerType.getSelectedCode();//权利人类型
		String Togetheronwer = et_Togetheronwer.getText().toString();//共有人信息
		String GuarantySubType = sp_GuarantySubType.getSelectedCode();//房产类型
		String HourseFormat = sp_HourseFormat.getSelectedCode();//房产状况
		String GuarantyLocation = et_GuarantyLocation.getText().toString();//房屋详细地址
		//String GuarantyArea = et_GuarantyArea.getText().toString();//抵押物区域地点
		String GuarantyAmount = et_GuarantyAmount.getText().toString();//房屋建筑面积
		String GuarantyDescript = et_GuarantyDescript.getText().toString();//抵押物说明
		String EvalMethod = sp_EvalMethod.getSelectedCode();//抵押物评估方式
		String EvalOrgName = et_EvalOrgName.getText().toString();//评估机构名称
		String EvalDate = et_EvalDate.getText().toString();//价值评估时间
		String EvalNetValue = et_EvalNetValue.getText().toString();//抵押物评估价值
		String GuarantyCurrency = sp_GuarantyCurrency.getSelectedCode();//抵押物债权币种
		String ConfirmValue = et_ConfirmValue.getText().toString();//抵押物债券价值
		
		if(Contants.houseInfo==null){
			Contants.houseInfo = new House();
		}
		//担保类型，pad端固定为010010，
		Contants.houseInfo.setGuarantyType("010010");
		Contants.houseInfo.setCertType(CertType);
		Contants.houseInfo.setCertID(CertID);
		Contants.houseInfo.setOwnerName(OwnerName);
		Contants.houseInfo.setOwnerType(OwnerType);
		Contants.houseInfo.setTogetheronwer(Togetheronwer);
		Contants.houseInfo.setGuarantySubType(GuarantySubType);
		Contants.houseInfo.setHourseFormat(HourseFormat);
		Contants.houseInfo.setGuarantyLocation(GuarantyLocation);

		//抵押物的区域地点设置 不需要 因为是动态设置的
//		Contants.houseInfo.setGuarantyArea(TheGuarantyArea);
//		Contants.houseInfo.setGuarantyAreaName(TheGuarantyAreaName);

		Contants.houseInfo.setGuarantyAmount(GuarantyAmount);
		Contants.houseInfo.setGuarantyDescript(GuarantyDescript);
		Contants.houseInfo.setEvalMethod(EvalMethod);
		Contants.houseInfo.setEvalOrgName(EvalOrgName);
		Contants.houseInfo.setEvalDate(EvalDate);
		Contants.houseInfo.setEvalNetValue(EvalNetValue);
		Contants.houseInfo.setGuarantyCurrency(GuarantyCurrency);
		Contants.houseInfo.setConfirmValue(ConfirmValue);
		
		Contants.houseInfo.setOwnerID(Contants.assureInfo.getCustomerID());
	}

	
	class MyOnclick implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
				case R.id.et_EvalOrgName:
					new ListAreaDialog(getContext(), "EvalOrg").setOnItemSureClickListener(new ListAreaDialog.OnItemSureClickListener() {
						@Override
						public void OnItemClick(ListAreaDialog dialog,
												ValueInfo node) {
							// TODO Auto-generated method stub
							et_EvalOrgName.setText(node.getName());
							dialog.dismiss();
						}
					}).show();
					break;
				case R.id.et_EvalDate:
					new TimePop(getContext(), R.style.style_withanimdialog).setOnTimeSureClickListener(new TimePop.OnTimeSureClickListener() {

						@Override
						public void onTimeSure(TimePop timePop, MDate date) {
							// TODO Auto-generated method stub
							timePop.dismiss();
							et_EvalDate.setText(date.getYear() + date.getMonth() + date.getDay());
						}
					}).show();
					break;
				case R.id.btn_add:
					new AddGurDialog(getContext(), R.style.alert_dialog).setOnSureClickListener(new OnSureClickListener() {

						@Override
						public void onSureClick(AddGurDialog dialog, String value) {
							// TODO Auto-generated method stub
							dialog.dismiss();
							if (et_Togetheronwer.getText().toString().length() > 0) {
								et_Togetheronwer.append("\n-----------------------------\n" + value);
							} else {
								et_Togetheronwer.setText(value);
							}
						}
					}).show();
					break;
				case R.id.et_GuarantyArea:
					new TreeDialog(getContext()).setOnItemSureClickListener(new TreeDialog.OnItemSureClickListener() {
						@Override
						public void OnItemClick(TreeDialog dialog, TreeModelBean node) {
							dialog.dismiss();
							et_GuarantyArea.setText(node.getLabel()+"(" +node.getId()+")");
							String TheGuarantyArea = node.getId()+"";
							String TheGuarantyAreaName = node.getLabel();
							if(Contants.houseInfo == null){
								Contants.houseInfo = new House();
							}
							Contants.houseInfo.setGuarantyAreaName(TheGuarantyAreaName);
							Contants.houseInfo.setGuarantyArea(TheGuarantyArea);
						}
					}).show();
					break;
			default:
				break;
			}
		}
		
	}
	
	public void refresh(){
		et_CertID.setText(Contants.assureInfo.getCertID());
		sp_CertType.setCode(Contants.assureInfo.getCertType());
		et_OwnerName.setText(Contants.assureInfo.getGuarantorName());
	}

	class MyOnItemSlectedListener implements AdapterView.OnItemSelectedListener{
		@Override
		public void onNothingSelected(AdapterView<?> adapterView) {

		}
		@Override
		public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
			AutoLoadSpinner spinner = (AutoLoadSpinner) adapterView;
			String itemno = spinner.getSelectedCode();
			if("01".equals(itemno)){
				itv_EvalOrgName.setNecessary(true);
				et_EvalOrgName.setEnabled(true);
			}else {
				itv_EvalOrgName.setNecessary(false);
                et_EvalOrgName.setEnabled(false);
                et_EvalOrgName.setText("");
            }
		}
	}
}
