package com.guoguang.khgl.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import com.aidl.cilent.util.ToastCoustom;
import com.guoguang.dkkhgl.R;
import com.guoguang.khgl.commonview.ListAreaDialog;
import com.guoguang.khgl.commonview.TreeDialog;
import com.guoguang.khgl.commonview.TreeDialog.OnItemSureClickListener;
import com.guoguang.khgl.entity.CustomInfo;
import com.guoguang.khgl.entity.ValueInfo;
import com.guoguang.khgl.library.treeview.Node;
import com.guoguang.khgl.model.Contants;
import com.guoguang.khgl.model.DateStrFormUtil;
import com.guoguang.khgl.widget.AutoLoadSpinner;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseInfoLayout extends BaseLayout {
//	CustomerID+B2:B36	String	客户编号
//	FullName	String	姓名
//	CertType	String	证件类型
//	CertID	String	证件号码
//	Sex	String	性别
//	Birthday	String	出生日期
//	Age	String	年龄
//	EduExperience	String	最高学历
//	EduRecord	String	毕业学校(取得最高学历)
//	GraduateYear	String	毕业年份(取得最高学历)
//	EduDegree	String	最高学位
//	SINo	String	社会保险号
//	IsRela	String	是否我行关联方
//	Staff	String	是否本行员工
//	CustomerLevel	String	行内级别
//	CreditRecordType	String	信用记录类型
//	Nationality	String	民族
//	NativePlace	String	户籍地址
//	PoliticalFace	String	政治面貌
//	Marriage	String	婚姻状况
//	BabyNum	Number	子女数
//	DetailAddName	String	家庭地址
//	DetailAdd	String	家庭地址
//	CustomerBelongArea	String	客户所属行政区划代码
//	CustomerBelongAreaName	String	客户所属行政区划代码
//	FamilyAdd	String	家庭详细地址
//	FamilyZIP	String	家庭地址邮编
//	FamilyTel	String	家庭电话
//	FamilyStatus	String	居住状况
//	MobileTelephone	String	手机号码
//	EmailAdd	String	电子邮箱

	EditText et_CustomerID,
	et_CertID,
	et_SINo,
	et_FullName,
	et_Age,
	et_Birthday,
	et_NativePlace,
	et_CustomerBelongArea,
	et_DetailAdd,
	et_FamilyAdd,
	et_FamilyZIP,
	et_FamilyTel,
	et_MobileTelephone,
	et_EmailAdd;
	 
	AutoLoadSpinner sp_CertType,
	sp_Sex,
	sp_EduExperience,
	sp_EduDegree,
	sp_IsRela,
	sp_Staff,
	sp_CreditRecordType,
	sp_Nationality,
	sp_PoliticalFace,
	sp_Marriage,
	sp_FamilyStatus,
	sp_IsResideLocalLongTime;
	
	boolean hasCustom = false;
	
	public BaseInfoLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public BaseInfoLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public BaseInfoLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	View onCreateView() {
		// TODO Auto-generated method stub
		inflater.inflate(R.layout.view_personbaseinfo, this);
		et_CustomerID = (EditText) findViewById(R.id.et_CustomerID);
		et_CertID = (EditText) findViewById(R.id.et_CertID);
		et_SINo = (EditText) findViewById(R.id.et_SINo);
		et_FullName = (EditText) findViewById(R.id.et_FullName);
		et_Age = (EditText) findViewById(R.id.et_Age);
		et_Birthday = (EditText) findViewById(R.id.et_Birthday);
		et_NativePlace = (EditText) findViewById(R.id.et_NativePlace);
		et_CustomerBelongArea = (EditText) findViewById(R.id.et_CustomerBelongArea);
		et_DetailAdd = (EditText) findViewById(R.id.et_DetailAdd);
		et_FamilyAdd = (EditText) findViewById(R.id.et_FamilyAdd);
		et_FamilyZIP = (EditText) findViewById(R.id.et_FamilyZIP);
		et_FamilyTel = (EditText) findViewById(R.id.et_FamilyTel);
		et_MobileTelephone = (EditText) findViewById(R.id.et_MobileTelephone);
		et_EmailAdd = (EditText) findViewById(R.id.et_EmailAdd);
		 
		sp_CertType = (AutoLoadSpinner) findViewById(R.id.sp_CertType);
		sp_Sex = (AutoLoadSpinner) findViewById(R.id.sp_Sex);
		sp_EduExperience = (AutoLoadSpinner) findViewById(R.id.sp_EduExperience);
		sp_EduDegree = (AutoLoadSpinner) findViewById(R.id.sp_EduDegree);
		sp_IsRela = (AutoLoadSpinner) findViewById(R.id.sp_IsRela);
		sp_Staff = (AutoLoadSpinner) findViewById(R.id.sp_Staff);
		sp_CreditRecordType = (AutoLoadSpinner) findViewById(R.id.sp_CreditRecordType);
		sp_Nationality = (AutoLoadSpinner) findViewById(R.id.sp_Nationality);
		sp_PoliticalFace = (AutoLoadSpinner) findViewById(R.id.sp_PoliticalFace);
		sp_Marriage = (AutoLoadSpinner) findViewById(R.id.sp_Marriage);
		sp_FamilyStatus = (AutoLoadSpinner) findViewById(R.id.sp_FamilyStatus);
		sp_IsResideLocalLongTime = (AutoLoadSpinner) findViewById(R.id.sp_IsResideLocalLongTime);
		
		et_CustomerBelongArea.setOnClickListener(new MyOnclick());
		et_DetailAdd.setOnClickListener(new MyOnclick());

		//默认值设置
		sp_IsRela.setCode("2");
		sp_Staff.setCode("2");
		sp_CertType.setCode("Ind01");
		sp_CreditRecordType.setCode("010");
		sp_Marriage.setCode("21");
		sp_EduExperience.setCode("20");
		sp_EduDegree.setCode("4");
		sp_FamilyStatus.setCode("010");
		sp_PoliticalFace.setCode("04");
		sp_Nationality.setCode("01");
		sp_IsResideLocalLongTime.setCode("1");
        et_Age.setEnabled(false);
        et_FullName.setEnabled(false);
        sp_CertType.setEnabled(false);
        et_CertID.setEnabled(false);
        sp_Sex.setEnabled(false);
		return null;
	}

	
	@Override
	public boolean getData() {
		// TODO Auto-generated method stub
		String CustomerID = et_CustomerID.getText().toString();
		String CertID = et_CertID.getText().toString();
		String SINo = et_SINo.getText().toString();
		String FullName = et_FullName.getText().toString();
		String Age = et_Age.getText().toString();
		String Birthday = et_Birthday.getText().toString();
		String NativePlace = et_NativePlace.getText().toString();
		String CustomerBelongAreaName = et_CustomerBelongArea.getText().toString();
		String DetailAdd = et_DetailAdd.getText().toString();
		String FamilyAdd = et_FamilyAdd.getText().toString();
		String FamilyZIP = et_FamilyZIP.getText().toString();
		String FamilyTel = et_FamilyTel.getText().toString();
		String MobileTelephone = et_MobileTelephone.getText().toString();
		String EmailAdd = et_EmailAdd.getText().toString();
		
		String CertType = sp_CertType.getSelectedCode();
		String Sex = sp_Sex.getSelectedCode();
		String EduExperience = sp_EduExperience.getSelectedCode();
		String EduDegree = sp_EduDegree.getSelectedCode();
		String IsRela = sp_IsRela.getSelectedCode();
		String Staff = sp_Staff.getSelectedCode();
		String CreditRecordType = sp_CreditRecordType.getSelectedCode();
		String Nationality = sp_Nationality.getSelectedCode();
		String PoliticalFace = sp_PoliticalFace.getSelectedCode();
		String Marriage = sp_Marriage.getSelectedCode();
		String FamilyStatus = sp_FamilyStatus.getSelectedCode();
		String IsResideLocalLongTime = sp_IsResideLocalLongTime.getSelectedCode();
		
		if("".equals(CertID)){
			ToastCoustom.show("证件号码不能为空");
			return false;
		}

		if("".equals(EduDegree)){
			ToastCoustom.show("学位不能为空");
			return false;
		}
		if("".equals(MobileTelephone)){
			ToastCoustom.show("手机号码不能为空");
			return false;
		}else {
			if(MobileTelephone.length()!=11){
				ToastCoustom.show("手机号码应为11位");
				return false;
			}
		}
		if("".equals(EduExperience)){
			ToastCoustom.show("学历不能为空");
			return false;
		}

		if("".equals(IsRela)){
			ToastCoustom.show("是否我行关联方不能为空");
			return false;
		}
		if("".equals(Staff)){
			ToastCoustom.show("是否我行员工不能为空");
			return false;
		}
		if("".equals(NativePlace)){
			ToastCoustom.show("户籍地址不能为空");
			return false;
		}
		if("".equals(Marriage)){
			ToastCoustom.show("婚姻状况不能为空");
			return false;
		}
		if("".equals(FamilyAdd)){
			ToastCoustom.show("家庭详细地址不能为空");
			return false;
		}
		if("".equals(DetailAdd)){
			ToastCoustom.show("家庭地址不能为空");
			return false;
		}
		if("".equals(CustomerBelongAreaName)){
			ToastCoustom.show("客户所在行政区代码不能为空");
			return false;
		}
		if("".equals(FamilyZIP)){
			ToastCoustom.show("家庭地址邮编不能为空");
			return false;
		}else if(FamilyZIP.length()!=6){
			ToastCoustom.show("家庭地址邮编应为6位数字"+FamilyZIP+FamilyZIP.length());
			return false;
		}

		if("".equals(FamilyStatus)){
			ToastCoustom.show("居住状况不能为空");
			return false;
		}


		
		saveData();
		return super.getData();
	}
	
	

	@Override
	public void saveData() {
		// TODO Auto-generated method stub
		String CustomerID = et_CustomerID.getText().toString();
		String CertID = et_CertID.getText().toString();
		String SINo = et_SINo.getText().toString();
		String FullName = et_FullName.getText().toString();
		String Age = et_Age.getText().toString();
		String Birthday = et_Birthday.getText().toString();
		String NativePlace = et_NativePlace.getText().toString();

		//这里注意一下，由于我们只需传CustomerBelongArea,CustomerBelongAreaName适用于ui呈现
		String CustomerBelongAreaName = et_CustomerBelongArea.getText().toString();


		String DetailAddName = et_DetailAdd.getText().toString();
		String FamilyAdd = et_FamilyAdd.getText().toString();
		String FamilyZIP = et_FamilyZIP.getText().toString();
		String FamilyTel = et_FamilyTel.getText().toString();
		String MobileTelephone = et_MobileTelephone.getText().toString();
		String EmailAdd = et_EmailAdd.getText().toString();
		
		String CertType = sp_CertType.getSelectedCode();
		String Sex = sp_Sex.getSelectedCode();
		String EduExperience = sp_EduExperience.getSelectedCode();
		String EduDegree = sp_EduDegree.getSelectedCode();
		String IsRela = sp_IsRela.getSelectedCode();
		String Staff = sp_Staff.getSelectedCode();
		String CreditRecordType = sp_CreditRecordType.getSelectedCode();
		String Nationality = sp_Nationality.getSelectedCode();
		String PoliticalFace = sp_PoliticalFace.getSelectedCode();
		String Marriage = sp_Marriage.getSelectedCode();
		String FamilyStatus = sp_FamilyStatus.getSelectedCode();
		String IsResideLocalLongTime = sp_IsResideLocalLongTime.getSelectedCode();
		
		CustomInfo mCustomInfo = Contants.tempCustomInfo;
		if(mCustomInfo == null){
			mCustomInfo = new CustomInfo();
		}
		mCustomInfo.setCustomerID(CustomerID);
		mCustomInfo.setCertID(CertID);
		mCustomInfo.setSINo(SINo);
		mCustomInfo.setFullName(FullName);
		mCustomInfo.setAge(Age);
		mCustomInfo.setBirthday(Birthday);
		mCustomInfo.setNativePlace(NativePlace);
//		mCustomInfo.setCustomerBelongArea(CustomerBelongArea);
		mCustomInfo.setCustomerBelongAreaName(CustomerBelongAreaName);
		mCustomInfo.setDetailAddName(DetailAddName);
		mCustomInfo.setFamilyAdd(FamilyAdd);
		mCustomInfo.setFamilyZIP(FamilyZIP);
		mCustomInfo.setFamilyTel(FamilyTel);
		mCustomInfo.setMobileTelephone(MobileTelephone);
		mCustomInfo.setEmailAdd(EmailAdd);
		mCustomInfo.setCertType(CertType);
		mCustomInfo.setSex(Sex);
		mCustomInfo.setEduDegree(EduDegree);
		mCustomInfo.setEduExperience(EduExperience);
		mCustomInfo.setIsRela(IsRela);
		mCustomInfo.setStaff(Staff);
		mCustomInfo.setNationality(Nationality);
		mCustomInfo.setMarriage(Marriage);
		mCustomInfo.setFamilyStatus(FamilyStatus);		
		mCustomInfo.setIsResideLocalLongTime(IsResideLocalLongTime);
		mCustomInfo.setCreditRecordType(CreditRecordType);
		mCustomInfo.setPoliticalFace(PoliticalFace);
		
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
		et_CustomerID.setText(customInfo.getCustomerID());
		et_CertID.setText(customInfo.getCertID());
		et_SINo.setText(customInfo.getSINo());
		et_FullName.setText(customInfo.getFullName());
		et_Age.setText(customInfo.getAge());
		et_Birthday.setText(customInfo.getBirthday());
		et_CustomerBelongArea.setText(customInfo.getCustomerBelongAreaName());
		et_NativePlace.setText(customInfo.getNativePlace());
		et_DetailAdd.setText(customInfo.getDetailAddName());
		et_FamilyAdd.setText(customInfo.getFamilyAdd());
		et_FamilyZIP.setText(customInfo.getFamilyZIP());
		et_FamilyTel.setText(customInfo.getFamilyTel());
		et_MobileTelephone.setText(customInfo.getMobileTelephone());
		et_EmailAdd.setText(customInfo.getEmailAdd());

		if(!"".equals(customInfo.getCertType())){
			sp_CertType.setCode(customInfo.getCertType());
		}
		if(!"".equals(customInfo.getSex())){
			sp_Sex.setCode(customInfo.getSex());
		}
		if(!"".equals(customInfo.getEduDegree())){
			sp_EduDegree.setCode(customInfo.getEduDegree());
		}
		if(!"".equals(customInfo.getEduExperience())){
			sp_EduExperience.setCode(customInfo.getEduExperience());
		}
		if(!"".equals(customInfo.getIsRela())){
			sp_IsRela.setCode(customInfo.getIsRela());
		}
		if(!"".equals(customInfo.getStaff())){
			sp_Staff.setCode(customInfo.getStaff());
		}
		if(!"".equals(customInfo.getMarriage())){
			sp_Marriage.setCode(customInfo.getMarriage());
		}
		if(!"".equals(customInfo.getNationality())){
			sp_Nationality.setCode(customInfo.getNationality());
		}
		if(!"".equals(customInfo.getPoliticalFace())){
			sp_PoliticalFace.setCode(customInfo.getPoliticalFace());
		}
		if(!"".equals(customInfo.getCreditRecordType())){
			sp_CreditRecordType.setCode(customInfo.getCreditRecordType());
		}
		if(!"".equals(customInfo.getFamilyStatus())){
			sp_FamilyStatus.setCode(customInfo.getFamilyStatus());
		}
		if(!"".equals(customInfo.getIsResideLocalLongTime())){
			sp_IsResideLocalLongTime .setCode(customInfo.getIsResideLocalLongTime());
		}

	}
	
	class MyOnclick implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.et_CustomerBelongArea:
				new TreeDialog(getContext()).setOnItemSureClickListener(new OnItemSureClickListener() {
					@Override
					public void OnItemClick(TreeDialog dialog, Node node) {
						// TODO Auto-generated method stub
						String areaName = node.getName();
						int intcode = node.getId();
						String code = intcode +"";
						et_CustomerBelongArea.setText(areaName + "(" + code + ")");
                        if(Contants.tempCustomInfo == null){
                            Contants.tempCustomInfo  = new CustomInfo();
                            Contants.tempCustomInfo.setCustomerBelongArea(code);
                        }else {
                            Contants.tempCustomInfo.setCustomerBelongArea(code);
                        }
						dialog.dismiss();
					}
				}).show();
				break;
			case R.id.et_DetailAdd:
				new ListAreaDialog(getContext()).setOnItemSureClickListener(new ListAreaDialog.OnItemSureClickListener() {
					@Override
					public void OnItemClick(ListAreaDialog dialog,
							ValueInfo node) {
						// TODO Auto-generated method stub
						et_DetailAdd.setText(node.getName());
                        if(Contants.tempCustomInfo == null){
                            Contants.tempCustomInfo  = new CustomInfo();
                            Contants.tempCustomInfo.setDetailAdd(node.getID());
                        }else {
                            Contants.tempCustomInfo.setDetailAdd(node.getID());
                        }
						dialog.dismiss();
					}
				}).show();
				break;
			default:
				break;
			}
		}
		
	}

	public void onIdChanged() {
		// TODO Auto-generated method stub
		String CertID = Contants.tempCustomInfo.getCertID();
		String Fullname =  Contants.tempCustomInfo.getFullName();
		et_CertID .setText(CertID);
		et_FullName.setText(Fullname);

		String Sex ="";
		String Birthday = "";
		String Age = "";
		if(CertID.length()==18){
			Sex = CertID.substring(16, 17);
			Birthday =CertID.substring(6, 14);
			Age = CertID.substring(6, 10);
		}else if(CertID.length()==15){
			Sex =  CertID.substring(14, 15);
			Birthday =CertID.substring(6, 14);
			Age = CertID.substring(6, 10);
		}

		Birthday = DateStrFormUtil.formatDateString(Birthday);

		int intSex = Integer.parseInt(Sex);
		if(intSex%2 == 0){
			sp_Sex.setCode("2");
		}else {
			sp_Sex.setCode("1");
		}
		et_Birthday.setText(Birthday);

		String toyear = new SimpleDateFormat("yyyy").format(new Date());

		int intAge = Integer.parseInt(toyear)  -  Integer.parseInt(Age);
		Age = intAge+"";

		et_Age.setText(Age);
	}
}
