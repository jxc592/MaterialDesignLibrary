package com.guoguang.khgl.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import com.aidl.cilent.entity.HeaderCommonEntityRequest;
import com.aidl.cilent.entity.HeaderCommonResponse;
import com.aidl.cilent.library.comondialog.ShowResultDialog;
import com.aidl.cilent.library.comondialog.ShowResultDialog.OnSureClickListener;
import com.aidl.cilent.library.sweetdialog.SweetAlertDialog;
import com.aidl.cilent.library.sweetdialog.SweetAlertDialog.OnSweetClickListener;
import com.aidl.cilent.util.BaseUtil;
import com.aidl.cilent.util.CrashHandler;
import com.aidl.cilent.util.DateUtil;
import com.aidl.cilent.util.ToastCoustom;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guoguang.khgl.application.WincomApplication;
import com.guoguang.khgl.database.CusInfo;
import com.guoguang.khgl.database.CusInfoDao;
import com.guoguang.khgl.model.Contants;
import com.guoguang.khgl.model.CustominfoAdd;
import com.guoguang.khgl.model.CustominfoRead;
import com.guoguang.khgl.model.IProcedure;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PreLoadCusinfoActivity extends PreBaseActivity {
	IProcedure procedure;
	WincomApplication app;
	HeaderCommonEntityRequest headRequest;
	Gson gson;
	String header;
	
	String seqNo =null ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        CrashHandler.getInstance().init(this);
		prepare();
		getWindow().getDecorView().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				procedure.firstLoad();
			}
		}, 100);
	}
	
	private void prepare() {
		// TODO Auto-generated method stub
		gson = new GsonBuilder().create();
		app = (WincomApplication) getApplication();
		
		Intent mIntent =getIntent();
		String isNew = mIntent.getStringExtra("isNew");
		if("true".equals(isNew)){
			procedure = new CustominfoAdd(this);
		}else {
			procedure = new CustominfoRead(this);
			seqNo = mIntent.getStringExtra("seqNo");
		}
	}

	@Override
	protected void onLast() {
		// TODO Auto-generated method stub
		super.onLast();
		procedure.onLast();
	}
	@Override
	protected void onNext() {
		// TODO Auto-generated method stub
		super.onNext();
		procedure.onNext();
	}
	@Override
	public void onSave() {
		// TODO Auto-generated method stub
		super.onSave();
		new SweetAlertDialog(this,SweetAlertDialog.NORMAL_TYPE).setTitleText("确认保存草稿吗")
				.setConfirmText("确认").setCancelText("取消").setConfirmClickListener(new OnSweetClickListener() {
			@Override
			public void onClick(SweetAlertDialog sweetAlertDialog) {
				sweetAlertDialog.dismiss();
				saveToWeb();
			}
		}).show();


	}


	void saveToWeb(){
		procedure.onSave();
		if(subDialog!=null&&subDialog.isShowing()){
			subDialog.dismiss();
		}
		subDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
		subDialog.setTitleText("正在保存客户信息");
		subDialog.setCancelable(false);
		subDialog.show();
		if(Contants.tempCustomInfo==null){
			ToastCoustom.show("尚未录入任何信息");
			return;
		}
		String inputUserName=Contants.tempCustomInfo.getUserName();

		if("".equals(inputUserName)){
			Contants.tempCustomInfo.setUserName(Contants.registInfo.getInputUserName());
			Contants.tempCustomInfo.setInputUserID(Contants.registInfo.getInputUserID());
			Contants.tempCustomInfo.setInputOrgID(Contants.registInfo.getInputOrgID());
			Contants.tempCustomInfo.setOrgName(Contants.registInfo.getInputOrgName());
			Contants.tempCustomInfo.setManageUserName(Contants.registInfo.getInputUserName());
			Contants.tempCustomInfo.setManageOrgName(Contants.registInfo.getInputOrgName());
			Contants.tempCustomInfo.setInputDate(BaseUtil.formatDate());
		}
		Contants.tempCustomInfo.setUpdateDate(BaseUtil.formatDate());
		new Thread(){
			public void run() {
				HashMap<String, String> map = new HashMap<>();
				map.put("TransCode", "1029");
				String testBody = gson.toJson(map);
				String test[] = app.getmAidlClient().exec(Contants.header, testBody);
				HeaderCommonResponse response = gson.fromJson(test[1], HeaderCommonResponse.class);

				seqNo = Contants.tempCustomInfo.getSeqNo();

				if("0000".equals(response.getResultCode())){
					if(seqNo==null||"".equals(seqNo)){
						Contants.tempCustomInfo.setSeqNo(BaseUtil.RamdomSerNO());
					}

					Map<String, Object> map1 = new HashMap<>();
					map1.put("CusInfo", Contants.tempCustomInfo);
					map1.put("TransCode", "1213");

					String body = gson.toJson(map1);
					String result[] = app.getmAidlClient().exec(Contants.header, body);
					HeaderCommonResponse response1 = gson.fromJson(result[1],HeaderCommonResponse.class);

					if("0000".equals(response1.getResultCode())){
						mHandler.obtainMessage(10002, "客户信息已存为草稿").sendToTarget();
					}else {
						mHandler.obtainMessage(10001,response1.getMessage()).sendToTarget();
					}
				}else {
					mHandler.obtainMessage(10001,"与服务器断开连接，请检查网络").sendToTarget();
				}
			}
		}.start();
	}


	void saveToLocal(){
		CusInfo cusInfo = null;
		List<CusInfo> cusInfos =((WincomApplication)getApplication()).getmDaoSession().queryBuilder(CusInfo.class).where(CusInfoDao.Properties.CertID.eq(Contants.tempCustomInfo.getCertID())).list();
		if(cusInfos!=null&&cusInfos.size()>0){
			cusInfo = cusInfos.get(0);
			cusInfo.setCertID(Contants.tempCustomInfo.getCertID());
			cusInfo.setCertType(Contants.tempCustomInfo.getCertType());
			cusInfo.setFullName(Contants.tempCustomInfo.getFullName());
			cusInfo.setManageUserName(Contants.tempCustomInfo.getManageUserName());
			cusInfo.setCusInfo(gson.toJson(Contants.tempCustomInfo));
			cusInfo.setUpdateTime(DateUtil.detaledLshFormat(new Date()));
			((WincomApplication)getApplication()).getmDaoSession().update(cusInfo);
		}else {
			cusInfo =new CusInfo();
			cusInfo.setSeqNo(BaseUtil.RamdomSerNO());
			cusInfo.setCertID(Contants.tempCustomInfo.getCertID());
			cusInfo.setCertType(Contants.tempCustomInfo.getCertType());
			cusInfo.setFullName(Contants.tempCustomInfo.getFullName());
			cusInfo.setManageUserName(Contants.tempCustomInfo.getManageUserName());
			cusInfo.setInsertTime(DateUtil.detaledLshFormat(new Date()));
			cusInfo.setUpdateTime(DateUtil.detaledLshFormat(new Date()));
			cusInfo.setCusInfo(gson.toJson(Contants.tempCustomInfo));
			((WincomApplication)getApplication()).getmDaoSession().insert(cusInfo);
		}
	}
	
	SweetAlertDialog subDialog;
	public void onSubmit() {
		new SweetAlertDialog(this,SweetAlertDialog.NORMAL_TYPE).setTitleText("确认信息填写无误")
				.setConfirmText("确认").setCancelText("取消").setConfirmClickListener(new OnSweetClickListener() {
			@Override
			public void onClick(SweetAlertDialog sweetAlertDialog) {
				sweetAlertDialog.dismiss();
				submit();
			}
		}).show();
	}



	void submit(){
		if(subDialog!=null&&subDialog.isShowing()){
			return;
		}
		subDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
		subDialog.setTitleText("正在提交客户信息");
		subDialog.setCancelable(false);
		subDialog.show();

		new Thread(){
			public void run() {
				HashMap<String, String> map = new HashMap<>();
				map.put("TransCode", "1029");
				String testBody = gson.toJson(map);
				String test[] = app.getmAidlClient().exec(Contants.header, testBody);
				HeaderCommonResponse response = gson.fromJson(test[1], HeaderCommonResponse.class);
				if("0000".equals(response.getResultCode())){

					seqNo = Contants.tempCustomInfo.getSeqNo();
					if(seqNo == null ||"".equals(seqNo)){
						Contants.tempCustomInfo.setSeqNo(BaseUtil.RamdomSerNO());
					}

					Map<String, Object> map1 = new HashMap<>();
					map1.put("CusInfo", Contants.tempCustomInfo);
					map1.put("TransCode", "1211");

					String body = gson.toJson(map1);
					String result[] = app.getmAidlClient().exec(Contants.header, body);

					HeaderCommonResponse header = gson.fromJson(result[1],HeaderCommonResponse.class);
					if("0000".equals(header.getResultCode())){
						mHandler.obtainMessage(10002,header.getMessage()).sendToTarget();
					}else {
						mHandler.obtainMessage(10001,header.getMessage()).sendToTarget();
					}
				}else {
					mHandler.obtainMessage(10001,response.getMessage()).sendToTarget();
				}
			}
		}.start();
	}
	
	Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(subDialog!=null&&subDialog.isShowing()){
				subDialog.dismiss();
			}
			switch (msg.what) {
			case 10001:
				String result = (String) msg.obj;
				new ShowResultDialog(PreLoadCusinfoActivity.this).
				setMessage(result).
				setType(ShowResultDialog.FAILED_TYPR).
				setOnSureClickListener(new OnSureClickListener() {
					@Override
					public void onSure() {
						// TODO Auto-generated method stub
						onSave();
					}
				}).show();
				break;
			case 10002:
				String result1 = (String) msg.obj;
				new ShowResultDialog(PreLoadCusinfoActivity.this).
				setMessage(result1).
				setType(ShowResultDialog.SUCCESS_TYPR).
				setOnSureClickListener(new OnSureClickListener() {
					@Override
					public void onSure() {
						// TODO Auto-generated method stub
						PreLoadCusinfoActivity.this.finish();
					}
				}).show();
				break;
			default:
				break;
			}
		}
	};
	
	protected void onDestroy() {
		super.onDestroy();
		Contants.tempCustomInfo = null;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
			case android.R.id.home:
				new SweetAlertDialog(PreLoadCusinfoActivity.this,SweetAlertDialog.NORMAL_TYPE).
						setTitleText("您将舍弃录入信息并退出").setConfirmText("确认").
						setCancelText("取消").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
					@Override
					public void onClick(SweetAlertDialog sweetAlertDialog) {
						finish();
					}
				}).show();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	public void refresh(){
		procedure.refresh();
	}

    public void setEditMode(boolean hasEditPermision){
        if(!hasEditPermision){
			procedure.setEditAble(false);
        	btn_save.setVisibility(View.INVISIBLE);
		}else {
			procedure.setEditAble(true);
			btn_save.setVisibility(View.VISIBLE);
		}
    }


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode){
			case KeyEvent.KEYCODE_BACK:
				new SweetAlertDialog(PreLoadCusinfoActivity.this,SweetAlertDialog.NORMAL_TYPE).setTitleText("退出客户管理").setCancelText("取消")
						.setConfirmText("确认").setConfirmClickListener(new OnSweetClickListener() {
					@Override
					public void onClick(SweetAlertDialog sweetAlertDialog) {
						sweetAlertDialog.dismiss();
						finish();
					}
				}).show();
				break;
		}
		return true;
	}


	boolean saveAble = true;
	String saveAbleMessage = "";
	public void setSaveEnable(boolean saveEnable){
		if(saveEnable){
			btn_save.setVisibility(View.VISIBLE);
		}else {
			btn_save.setVisibility(View.INVISIBLE);
		}
	}
}
