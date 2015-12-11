package com.guoguang.khgl.application;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;

import com.aidl.cilent.AidlClient;
import com.aidl.cilent.entity.HeaderCommonEntityRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guoguang.khgl.database.DaoSession;
import com.guoguang.khgl.entity.AreaEntity;
import com.guoguang.khgl.entity.RegistInfo;
import com.guoguang.khgl.model.Contants;

import java.util.HashMap;

public class PreditCusService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	WincomApplication mApplication;
	AidlClient mAidlClient;
	String header ; 
	Gson gson;
	DaoSession mDaoSession;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		//CrashHandler.getInstance().init(this);
		mApplication = (WincomApplication) getApplication();
		mAidlClient = mApplication.getmAidlClient();
		mDaoSession = mApplication.getmDaoSession();
		
		HeaderCommonEntityRequest headRequest=new HeaderCommonEntityRequest();
		headRequest.setEncryptInd("0");
		headRequest.setSdSysId("0000");
		headRequest.setRcvSysId("0002");
		headRequest.setFlag("0");
		headRequest.setUserId("0388");
		gson = new GsonBuilder().create();
		header = gson.toJson(headRequest);
		
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		String actionStr = intent.getAction();
		if("CodeLib".equals(actionStr)){
			new Thread(){
				public void run() {
					//根据sharedPre的codlob的值判断数据是否加载过。
					SharedPreferences sp= getSharedPreferences("PreLoadInvest", Context.MODE_APPEND);
//					String codeTag = sp.getString("CodeLib", "00");
//					if(!"00".equals(codeTag)){
//						//CodelibEntity libEntity=gson.fromJson(resStr, CodelibEntity.class);
//						//Contants.codelibEntity = libEntity;
//					}else {
//						HashMap<String, String> map = new HashMap<>();
//						map.put("TransCode", "1298");
//						String body= gson.toJson(map);
//						String result[] = mAidlClient.exec(header, body);
//						if(result!=null&&result[2]!=null&&!"".equals(result[2])){
//							//List<HashMap<String, ValueInfo>>
//							sp.edit().putString("CodeLib", "01").commit();
//							CodelibEntity libEntity=gson.fromJson(result[2], CodelibEntity.class);
//							//Contants.codelibEntity = libEntity;
//							List<CodeLib> codeLibs = libEntity.getCodeList();
//							mDaoSession.getCodeLibDao().insertInTx(codeLibs);
//						}
//					}
					String areaTag = sp.getString("AreaLib", "00");
					if(!"00".equals(areaTag)){
						AreaEntity libEntity=gson.fromJson(areaTag, AreaEntity.class);
						Contants.areaEntity = libEntity;
					}else {
						HashMap<String, String> map = new HashMap<>();
						map.put("TransCode", "1299");
						String body= gson.toJson(map);
						String result[] = mAidlClient.exec(header, body);
						if(result!=null&&result[2]!=null&&!"".equals(result[2])){
							//List<HashMap<String, ValueInfo>>
							sp.edit().putString("AreaLib", result[2]).commit();
							AreaEntity areaEntity=gson.fromJson(result[2], AreaEntity.class);
							Contants.areaEntity = areaEntity;
						}
					}
				}
			}.start();
		}else if("getUserInfo".equals(actionStr)){
			String userId = intent.getStringExtra("userId");
			getUserInfo(userId);

		}
		return super.onStartCommand(intent, flags, startId);
	}

	private void getUserInfo(final String userId) {
		// TODO Auto-generated method stub
		new Thread(){
			public void run() {
				HashMap<String, String> bodyMap = new HashMap<>();
				bodyMap.put("TransCode", "1297");
				bodyMap.put("userId", userId);
				String body =gson.toJson(bodyMap);
				String result[]=mApplication.getmAidlClient().exec(Contants.header, body);
				if(result!=null&&result[2]!=null&&!"".equals(result[2])){
					RegistInfo registInfo = gson.fromJson(result[2], RegistInfo.class);
					Contants.registInfo = registInfo;
				}
			}
		}.start();
	}
}
