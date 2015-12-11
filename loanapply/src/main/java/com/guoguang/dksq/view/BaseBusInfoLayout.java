package com.guoguang.dksq.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.aidl.cilent.entity.HeaderCommonResponse;
import com.aidl.cilent.library.sweetdialog.SweetAlertDialog;
import com.aidl.cilent.util.ToastCoustom;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guoguang.dksq.R;
import com.guoguang.dksq.adapter.MSpinnerAdapter;
import com.guoguang.dksq.application.ProLoadApp;
import com.guoguang.dksq.contants.Contants;
import com.guoguang.dksq.customerview.ListCusDialog;
import com.guoguang.dksq.database.CodeLib;
import com.guoguang.dksq.database.LoanBusiness;
import com.guoguang.dksq.entity.Cusbrief;
import com.guoguang.dksq.entity.Rs_Cuslist;
import com.guoguang.dksq.entity.ValueInfo;
import com.guoguang.dksq.model.DateUtil;
import com.guoguang.dksq.widget.AutoLoadSpinner;
import com.guoguang.dksq.widget.AutoLoadSpinnerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * 业务信息的基本信息
 * @author thinkpad
 *
 */
public class BaseBusInfoLayout extends BaseLayout{
	

	public BaseBusInfoLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}


	public BaseBusInfoLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}


	public BaseBusInfoLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	TextView tv_change;

	AutoLoadSpinner sp_CertType;
	
	EditText et_CertID;
	TextView tv_CustomerID;
	TextView tv_CustomerName;
	
	Spinner sp_BussinessType;
	
	Gson gson;
	@Override
	public View onCreateView() {
		// TODO Auto-generated method stub
		gson =new GsonBuilder().create();
		inflater.inflate(R.layout.view_basebusinfo, this);
		tv_change = (TextView) findViewById(R.id.tv_change);
		sp_CertType = (AutoLoadSpinner) findViewById(R.id.sp_CertType);
		et_CertID = (EditText) findViewById(R.id.et_CertID);
		tv_CustomerID = (TextView) findViewById(R.id.et_CustomerID);
		tv_CustomerName = (TextView) findViewById(R.id.et_CustomerName);
		sp_BussinessType = (Spinner) findViewById(R.id.sp_BusinessType);

		List<CodeLib> libs = new ArrayList<>();
		for(int i=0;i<5;i++){
			CodeLib codeLib = new CodeLib();
			if(i==0){
				codeLib.setITEMNAME("个人一手住房按揭贷款");
				codeLib.setCODENO("BusinessType");
				codeLib.setITEMNO("1110010");
			}else if(i==1){
				codeLib.setITEMNAME("个人一手商业用房按揭贷款");
				codeLib.setCODENO("BusinessType");
				codeLib.setITEMNO("1110030");
			}else if(i==2){
				codeLib.setITEMNAME("个人组合贷款");
				codeLib.setCODENO("BusinessType");
				codeLib.setITEMNO("1110250");
			}else if(i==3){
				codeLib.setITEMNAME("个人二手住房按揭贷款");
				codeLib.setCODENO("BusinessType");
				codeLib.setITEMNO("1110020");
			}else if(i==4){
				codeLib.setITEMNAME("个人二手商业用房按揭贷款");
				codeLib.setCODENO("BusinessType");
				codeLib.setITEMNO("1110040");
			}
			libs.add(codeLib);
		}
		sp_BussinessType.setAdapter(new AutoLoadSpinnerAdapter(libs));
		sp_CertType.setCode("Ind01");
		tv_CustomerID.setEnabled(false);
		tv_CustomerName.setEnabled(false);
		
		
		tv_change.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
		tv_change.getPaint().setAntiAlias(true);//抗锯齿
		tv_change.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						tv_change.setTextColor(Color.BLACK);
						break;
					case MotionEvent.ACTION_CANCEL:
						tv_change.setTextColor(Color.RED);
						break;
					case MotionEvent.ACTION_UP:
						doSearch();
						tv_change.setTextColor(Color.RED);
						break;
					default:
						break;
				}
				return true;
			}


		});
		tv_change.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub


			}
		});

		et_CertID.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View view, boolean b) {
				String certid = et_CertID.getText().toString();

			}
		});

		return null;
	}
	
	SweetAlertDialog searchDialog;
	private void doSearch() {
		// TODO Auto-generated method stub
		if("".equals(et_CertID.getText().toString())){
			ToastCoustom.show("证件号码尚未输入");
			return;
		}
		searchDialog = new SweetAlertDialog(getContext(),SweetAlertDialog.PROGRESS_TYPE).setTitleText("正在查询客户信息");
		searchDialog.show();
		new Thread(){
			public void run() {
				HashMap<String, String> map  =  new HashMap<>();
				map.put("TransCode", "1294");
				map.put("CertId", et_CertID.getText().toString());
				map.put("CertType", sp_CertType.getSelectedCode());
				String result[] = ProLoadApp.getmAidlClient().exec(Contants.header, new GsonBuilder().create().toJson(map));
				mHandler.obtainMessage(1001).sendToTarget();
				HeaderCommonResponse res=gson.fromJson(result[1], HeaderCommonResponse.class);
				if("0000".equals(res.getResultCode())){
					if(result[2]!=null&&!"".equals(result[2])){
						mHandler.obtainMessage(100,result[2]).sendToTarget();
					}
				}else {
					mHandler.obtainMessage(101,result[1]).sendToTarget();
				}

			}
		}.start();
	}
	Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if (searchDialog != null && searchDialog.isShowing()) {
				searchDialog.dismiss();
			}
			switch (msg.what) {
			case 100:
				String reString = (String) msg.obj;
                Rs_Cuslist rs_cuslist = gson.fromJson(reString, Rs_Cuslist.class);
				if(rs_cuslist.getList() == null||rs_cuslist.getList().size()==0){
					ToastCoustom.show("没有找到相应客户");
					break;
				}
                new ListCusDialog(getContext(),rs_cuslist.getList()).setOnItemSureClickListener(new ListCusDialog.OnItemSureClickListener() {
                    @Override
                    public void OnItemClick(ListCusDialog dialog, Cusbrief node) {
                        dialog.dismiss();
                        tv_CustomerID.setText(node.getCustomerID());
                        tv_CustomerName.setText(node.getCustomerName());
                    }
                }).show();
				break;
			case 101:
				String headstr = (String) msg.obj;
				HeaderCommonResponse hr = new GsonBuilder().create().fromJson(
						headstr, HeaderCommonResponse.class);
				ToastCoustom.show(hr.getMessage());
				break;
			case 103:
				List<ValueInfo> list = (List<ValueInfo>) msg.obj;
				sp_BussinessType.setAdapter(new MSpinnerAdapter(list));
				break;
			default:
				break;
			}
		}
	};
	


	public boolean checkData() {
		
		String CustomerID = tv_CustomerID.getText().toString();
		String Fullname = tv_CustomerName.getText().toString();
		if("".equals(CustomerID)){
			ToastCoustom.show("客户编号不能为空");
			return false;
		}
		if("".equals(Fullname)){
			ToastCoustom.show("客户姓名不能为空");
			return false;
		}
		saveData();
		return super.checkData();
	};
	
	
	@Override
	public void saveData() {
		// TODO Auto-generated method stub
		super.saveData();
		String CustomerID = tv_CustomerID.getText().toString();
		String CustomerName = tv_CustomerName.getText().toString();
		String BusinessType = ((CodeLib)sp_BussinessType.getSelectedItem()).getITEMNO();
		if(Contants.loanBusinessInfo==null){
			Contants.loanBusinessInfo = new LoanBusiness();
		}
		Contants.loanBusinessInfo.setCustomerID(CustomerID);
		Contants.loanBusinessInfo.setCustomerName(CustomerName);
		Contants.loanBusinessInfo.setOccurType("010");//新发生
		Contants.loanBusinessInfo.setOccurDate(DateUtil.formatDkDate());
		Contants.loanBusinessInfo.setBusinessType(BusinessType);
		
	}


	@Override
	public void initData() {
		super.initData();
		if(Contants.loanBusinessInfo == null){
			return;
		}
		tv_CustomerID.setText(Contants.loanBusinessInfo.getCustomerID());
		tv_CustomerName.setText(Contants.loanBusinessInfo.getCustomerName());
		String bustype = Contants.loanBusinessInfo.getBusinessType();
		if ("1110010".equals(bustype)) {
			sp_BussinessType.setSelection(0);
		} else if ("1110020".equals(bustype)) {
			sp_BussinessType.setSelection(3);
		} else if ("1110250".equals(bustype)) {
			sp_BussinessType.setSelection(2);
		} else if ("1110030".equals(bustype)) {
			sp_BussinessType.setSelection(1);
		} else if ("1110040".equals(bustype)) {
			sp_BussinessType.setSelection(4);
		}
	}


/*
		用来查询业务类型
		new Thread(){
			public void run() {
				HashMap<String, String> map= new HashMap<>();
				map.put("TransCode", "1296");
				map.put("code", "BusinessType");
				String result[] = ProLoadApp.getmAidlClient().exec(Contants.header, gson.toJson(map));
				if(result[2]!=null&&!"".equals(result[2])){
					Rs_AdressList list1 = gson.fromJson(result[2],Rs_AdressList.class);
					mHandler.obtainMessage(103,list1.getList()).sendToTarget();
				}
			};
		}.start();*/
}
