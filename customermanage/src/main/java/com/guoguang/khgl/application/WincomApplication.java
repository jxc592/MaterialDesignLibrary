package com.guoguang.khgl.application;

import android.app.Application;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import com.aidl.cilent.AidlClient;
import com.aidl.cilent.entity.HeaderCommonEntityRequest;
import com.aidl.cilent.entity.LoginResponse;
import com.aidl.cilent.util.ToastCoustom;
import com.google.gson.GsonBuilder;
import com.guoguang.khgl.database.DaoMaster;
import com.guoguang.khgl.database.DaoMaster.DevOpenHelper;
import com.guoguang.khgl.database.DaoSession;
import com.guoguang.khgl.model.Contants;


/**
 * 
 * @author thinkpad
 *
 */
public class WincomApplication extends Application {
	boolean isBindService = false;
//	ExecutorService executorService;
	public boolean isBindService() {
		return isBindService;
	}

	public void setBindService(boolean isBindService) {
		this.isBindService = isBindService;
	}

//	/**
//	 * 全局线程池
//	 * 
//	 * @return
//	 */
//	public ExecutorService getNewCachedThreadPool() {
//		return executorService;
//	}
//
//	/**
//	 * 销毁线程池
//	 */
//	public void setExecutorServiceDestory() {
//		executorService.shutdown();
//	}

	LoginResponse mLoginResponse;
	static DaoSession mDaoSession;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
//		executorService = Executors.newCachedThreadPool();// 可缓存的线程池
		initObject();
		ToastCoustom.init(this);
		initObject();
		
		DevOpenHelper helper = new DevOpenHelper(this, "preload", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
//		CrashHandler.getInstance().init(getApplicationContext());
		Intent codeLoadService = new Intent(this,PreditCusService.class);
		codeLoadService .setAction("CodeLib");
		startService(codeLoadService);
	}
	
	//DevicePluginsTool mDevicePluginsTool = null;
	static AidlClient mAidlClient = null;
	/**
	 * 初始化全局对象
	 */
	private void initObject(){
	//	mDevicePluginsTool = new DevicePluginsTool(getApplicationContext());

		new Thread(){
			public void run() {
				mAidlClient = new AidlClient(getApplicationContext());
				Contants.mAidlClient =mAidlClient;
			};
		}.start();
		
	}

	public static AidlClient getmAidlClient() {
		return mAidlClient;
	}

	public void setmAidlClient(AidlClient mAidlClient) {
		this.mAidlClient = mAidlClient;
	}

	public static DaoSession getmDaoSession() {
		return mDaoSession;
	}

	public void setmDaoSession(DaoSession mDaoSession) {
		this.mDaoSession = mDaoSession;
	}

	public LoginResponse getmLoginResponse() {
		return mLoginResponse;
	}
	public void setmLoginResponse(LoginResponse mLoginResponse) {
		this.mLoginResponse = mLoginResponse;
		HeaderCommonEntityRequest headerCommonEntityRequest = new HeaderCommonEntityRequest();
		headerCommonEntityRequest.setEncryptInd("0");
		headerCommonEntityRequest.setSdSysId("0000");
		headerCommonEntityRequest.setRcvSysId("0002");
		headerCommonEntityRequest.setFlag("0");
		headerCommonEntityRequest.setUserId(mLoginResponse.getUserId());
		Contants.header = new GsonBuilder().create().toJson(headerCommonEntityRequest);
	}
	
	
}
