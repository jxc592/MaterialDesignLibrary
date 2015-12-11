package com.guoguang.khgl.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import com.aidl.cilent.util.ToastCoustom;
import com.guoguang.dkkhgl.R;
import com.guoguang.khgl.commonview.ListAreaDialog;
import com.guoguang.khgl.entity.CustomInfo;
import com.guoguang.khgl.entity.ValueInfo;
import com.guoguang.khgl.model.Contants;
import com.guoguang.khgl.widget.AutoLoadSpinner;

public class ProfessionInfoLayout extends BaseLayout {
//	Occupation	,//	职业
//	HeadShip	,//	职务
//	Position	,//	职称
//	UnitKind	,//	单位所属行业
//	WorkCorp	,//	单位名称
//	DetailWorkAdd	,//	单位地址
//	DetailWorkAddName	,//	单位地址
//	WorkAdd	,//	单位详细地址
//	WorkZip	,//	单位地址邮编
//	WorkTel	,//	单位电话
//	WorkYearCount	,//	现工作单位年限
//	IndustryYearCount	,//	目前行业从业年限
//	PositionYear	,//	任职年限
//	WorkBeginDate	,//	本单位工作起始日
//	CommAddType	,//	通讯地址类型
//	CommAdd	,//	通讯地址
//	CommZip	,//	通讯地址邮编
	
	AutoLoadSpinner sp_Occupation	,//	职业
	sp_HeadShip	,//	职务
	sp_Position	,//	职称
	sp_UnitKind	;//	单位所属行业
	EditText et_WorkCorp	,//	单位名称
	et_DetailWorkAdd	,//	单位地址
	et_DetailWorkAddName	,//	单位地址
	et_WorkAdd	,//	单位详细地址
	et_WorkZip	,//	单位地址邮编
	et_WorkTel	,//	单位电话
	et_WorkYearCount	,//	现工作单位年限
	et_IndustryYearCount	,//	目前行业从业年限
	//et_PositionYear	,//	任职年限
	et_WorkBeginDate	;//	本单位工作起始日
	AutoLoadSpinner sp_CommAddType	;//	通讯地址类型
	EditText et_CommAdd	,//	通讯地址
	et_CommZip	;//	通讯地址邮编


	public ProfessionInfoLayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public ProfessionInfoLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public ProfessionInfoLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	View onCreateView() {
		// TODO Auto-generated method stub
		inflater.inflate(R.layout.view_professioninfo, this);
		sp_Occupation	= (AutoLoadSpinner) findViewById(R.id.sp_Occupation);//	职业
		sp_HeadShip	= (AutoLoadSpinner) findViewById(R.id.sp_HeadShip);//	职务
		sp_Position	= (AutoLoadSpinner) findViewById(R.id.sp_Position);//	职称
		sp_UnitKind	= (AutoLoadSpinner) findViewById(R.id.sp_UnitKind);//	单位所属行业
		et_WorkCorp = (EditText) findViewById(R.id.et_WorkCorp);//	单位名称
		et_DetailWorkAdd = (EditText) findViewById(R.id.et_DetailWorkAdd);//	单位地址
		//et_DetailWorkAddName = (EditText) findViewById(R.id.et_WorkCorp);//	单位地址
		et_WorkAdd = (EditText) findViewById(R.id.et_WorkAdd);//	单位详细地址
		et_WorkZip = (EditText) findViewById(R.id.et_WorkZip);//	单位地址邮编
		et_WorkTel = (EditText) findViewById(R.id.et_WorkTel);//	单位电话
		et_WorkYearCount = (EditText) findViewById(R.id.et_WorkYearCount);//	现工作单位年限
		et_IndustryYearCount = (EditText) findViewById(R.id.et_IndustryYearCount);//	目前行业从业年限
		//et_PositionYear = (EditText) findViewById(R.id.et_PositionYear);//	任职年限
		et_WorkBeginDate = (EditText) findViewById(R.id.et_WorkBeginDate);//	本单位工作起始日
		sp_CommAddType	= (AutoLoadSpinner) findViewById(R.id.sp_CommAddType);//	通讯地址类型
		et_CommAdd = (EditText) findViewById(R.id.et_CommAdd);//	通讯地址
		et_CommZip = (EditText) findViewById(R.id.et_CommZip);//	通讯地址邮编

		sp_Occupation.setCode("4");
		sp_HeadShip.setCode("9");
		sp_Position.setCode("9");
		sp_UnitKind.setCode("C");
		sp_CommAddType.setCode("001");
		//et_CommAdd.setText(Contants.tempCustomInfo.getDetailAddName());

		et_DetailWorkAdd.setOnClickListener(new MyOnclick());

		sp_CommAddType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
				String code = sp_CommAddType.getSelectedCode();
				if("001".equals(code)){
					et_CommAdd.setText(Contants.tempCustomInfo.getDetailAddName());
				}else {
					et_CommAdd.setText(Contants.tempCustomInfo.getDetailWorkAddName());
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {

			}
		});
		return null;
	}
	@Override
	public boolean getData() {
		// TODO Auto-generated method stub
		String Occupation	=sp_Occupation.getSelectedCode();//	职业
		String HeadShip	= sp_HeadShip.getSelectedCode();//	职务
		String Position	= sp_Position.getSelectedCode();//	职称
		String UnitKind	=sp_UnitKind.getSelectedCode();//	单位所属行业
		
		String WorkCorp	= et_WorkCorp.getText().toString();//	单位名称
		String DetailWorkAdd	=et_DetailWorkAdd.getText().toString();//	单位地址
		String WorkAdd	=et_WorkAdd.getText().toString();//	单位详细地址
		String WorkZip	=et_WorkZip.getText().toString();//	单位地址邮编
		String WorkTel	=et_WorkTel.getText().toString();//	单位电话
		String WorkYearCount	=et_WorkYearCount.getText().toString();//	现工作单位年限
		String IndustryYearCount	=et_IndustryYearCount.getText().toString();//	目前行业从业年限
		String WorkBeginDate = et_WorkBeginDate.getText().toString();//	本单位工作起始日
		String CommAddType	=sp_CommAddType.getSelectedCode();//	通讯地址类型
		String CommAdd = et_CommAdd.getText().toString();//	通讯地址
		String CommZip = et_CommZip.getText().toString();//	通讯地址邮编
		
		if("".equals(Occupation)){
			ToastCoustom.show("职业不能为空");
			return false;
		}
		if("".equals(HeadShip)){
			ToastCoustom.show("职务不能为空");
			return false;
		}
		if("".equals(Position)){
			ToastCoustom.show("职称不能为空");
			return false;
		}
		if("".equals(UnitKind)){
			ToastCoustom.show("单位行业不能为空");
			return false;
		}
		if("".equals(WorkAdd)){
			ToastCoustom.show("单位详细地址不能为空");
			return false;
		}
		if("".equals(DetailWorkAdd)){
			ToastCoustom.show("单位地址不能为空");
			return false;
		}
		if("".equals(WorkZip)){
			ToastCoustom.show("单位邮编不能为空");
			return false;
		}else {
			if(WorkZip.length()!=6){
				ToastCoustom.show("邮编长度不正确");
				return false;
			}
		}
		if("".equals(CommAddType)){
			ToastCoustom.show("通讯地址类型不能为空");
			return false;
		}
		
		saveData();
//		CustomInfo mCustomInfo = Contants.tempCustomInfo;
//		if(mCustomInfo == null){
//			mCustomInfo = new CustomInfo();
//		}
//		mCustomInfo.setOccupation(Occupation);
//		mCustomInfo.setHeadShip(HeadShip);
//		mCustomInfo.setPosition(Position);
//		mCustomInfo.setUnitKind(UnitKind);
//		
//		mCustomInfo.setWorkCorp(WorkCorp);
//		mCustomInfo.setDetailWorkAdd(DetailWorkAdd);
//		mCustomInfo.setWorkAdd(WorkAdd);
//		mCustomInfo.setCommZip(WorkZip);
//		mCustomInfo.setWorkTel(WorkTel);
//		mCustomInfo.setWorkYearCount(WorkYearCount);
//		mCustomInfo.setIndustryYearCount(IndustryYearCount);
//		mCustomInfo.setWorkBeginDate(WorkBeginDate);
//		mCustomInfo.setCommAddType(CommAddType);
//		mCustomInfo.setCommAdd(CommAdd);
//		mCustomInfo.setCommZip(CommZip);
//		Contants.tempCustomInfo = mCustomInfo;
		return super.getData();
	}
	
	@Override
	public void saveData() {
		// TODO Auto-generated method stub
		super.saveData();
		String Occupation	=sp_Occupation.getSelectedCode();//	职业
		String HeadShip	= sp_HeadShip.getSelectedCode();//	职务
		String Position	= sp_Position.getSelectedCode();//	职称
		String UnitKind	=sp_UnitKind.getSelectedCode();//	单位所属行业
		
		String WorkCorp	= et_WorkCorp.getText().toString();//	单位名称
		String DetailWorkAddName	=et_DetailWorkAdd.getText().toString();//	单位地址
		String WorkAdd	=et_WorkAdd.getText().toString();//	单位详细地址
		String WorkZip	=et_WorkZip.getText().toString();//	单位地址邮编
		String WorkTel	=et_WorkTel.getText().toString();//	单位电话
		String WorkYearCount	=et_WorkYearCount.getText().toString();//	现工作单位年限
		String IndustryYearCount	=et_IndustryYearCount.getText().toString();//	目前行业从业年限
		String WorkBeginDate = et_WorkBeginDate.getText().toString();//	本单位工作起始日
		String CommAddType	=sp_CommAddType.getSelectedCode();//	通讯地址类型
		String CommAdd = et_CommAdd.getText().toString();//	通讯地址
		String CommZip = et_CommZip.getText().toString();//	通讯地址邮编
		CustomInfo mCustomInfo = Contants.tempCustomInfo;
		if(mCustomInfo == null){
			mCustomInfo = new CustomInfo();
		}
		mCustomInfo.setOccupation(Occupation);
		mCustomInfo.setHeadShip(HeadShip);
		mCustomInfo.setPosition(Position);
		mCustomInfo.setUnitKind(UnitKind);
		
		mCustomInfo.setWorkCorp(WorkCorp);
		mCustomInfo.setDetailWorkAddName(DetailWorkAddName);
		mCustomInfo.setWorkAdd(WorkAdd);
		mCustomInfo.setWorkZip(WorkZip);
		mCustomInfo.setWorkTel(WorkTel);
		mCustomInfo.setWorkYearCount(WorkYearCount);
		mCustomInfo.setIndustryYearCount(IndustryYearCount);
		mCustomInfo.setWorkBeginDate(WorkBeginDate);
		mCustomInfo.setCommAddType(CommAddType);
		mCustomInfo.setCommAdd(CommAdd);
		mCustomInfo.setCommZip(CommZip);
		Contants.tempCustomInfo = mCustomInfo;
	}
	
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
		if(Contants.tempCustomInfo == null){
			return;
		}
		CustomInfo customInfo = Contants.tempCustomInfo;
		if(!"".equals(customInfo.getOccupation())){
			sp_Occupation.setCode(customInfo.getOccupation());
		}
		if(!"".equals(customInfo.getHeadShip())){
			sp_HeadShip.setCode(customInfo.getHeadShip());
		}
		if(!"".equals(customInfo.getPosition())){
			sp_Position.setCode(customInfo.getPosition());
		}
		if(!"".equals(customInfo.getUnitKind())){
			sp_UnitKind.setCode(customInfo.getUnitKind());
		}
		if(!"".equals(customInfo.getCommAddType()))
			sp_CommAddType.setCode(customInfo.getCommAddType());

		et_WorkCorp .setText(customInfo.getWorkCorp());
		et_WorkAdd.setText(customInfo.getWorkAdd());
		et_DetailWorkAdd.setText(customInfo.getDetailWorkAddName());
		et_WorkTel.setText(customInfo.getWorkTel());
		et_WorkZip.setText(customInfo.getWorkZip());
		et_WorkYearCount.setText(customInfo.getWorkYearCount());
		et_IndustryYearCount.setText(customInfo.getIndustryYearCount());
		et_WorkBeginDate.setText(customInfo.getWorkBeginDate());
		et_CommAdd.setText(customInfo.getCommAdd());
		et_CommZip.setText(customInfo.getCommZip());
		
	}
	
	class MyOnclick implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.et_DetailWorkAdd:
				new ListAreaDialog(getContext()).setOnItemSureClickListener(new ListAreaDialog.OnItemSureClickListener() {
					@Override
					public void OnItemClick(ListAreaDialog dialog,
							ValueInfo node) {
						// TODO Auto-generated method stub
						et_DetailWorkAdd.setText(node.getName());
                        Contants.tempCustomInfo.setDetailWorkAddName(node.getName());
                        Contants.tempCustomInfo.setDetailWorkAdd(node.getID());
						dialog.dismiss();
					}
				}).show();
				break;
			}
		}
		
	}
}
