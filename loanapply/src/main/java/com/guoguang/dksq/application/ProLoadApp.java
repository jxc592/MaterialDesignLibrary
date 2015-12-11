package com.guoguang.dksq.application;

import android.app.Application;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import com.aidl.cilent.AidlClient;
import com.aidl.cilent.entity.HeaderCommonEntityRequest;
import com.aidl.cilent.entity.LoginResponse;
import com.aidl.cilent.util.ToastCoustom;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guoguang.dksq.contants.Contants;
import com.guoguang.dksq.database.DaoMaster;
import com.guoguang.dksq.database.DaoMaster.DevOpenHelper;
import com.guoguang.dksq.database.DaoSession;
import com.guoguang.dksq.socket.FileSocketUtil;
import com.guoguang.mitfs.client.MitFsClient;
import com.guoguang.mitfs.client.MitFsHttpClient;

/**
 * 贷前调查的applicaiton的类
 * @author thinkpad
 *
 */
public class ProLoadApp extends Application{
	boolean isBindService = false;
//	ExecutorService executorService;
	public boolean isBindService() {
		return isBindService;
	}

	public void setBindService(boolean isBindService) {
		this.isBindService = isBindService;
	}

	Gson gson;
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
	public static DaoSession mDaoSession;
	FileSocketUtil fileSocketUtil;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		initObject();
		ToastCoustom.init(this);
		DevOpenHelper helper = new DevOpenHelper(this, "preload", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
		fileSocketUtil= new FileSocketUtil(this);
	}



	//DevicePluginsTool mDevicePluginsTool = null;
	public static AidlClient  mAidlClient = null;
	/**
	 * 初始化全局对象
	 */
	private void initObject(){

		new Thread(){
			public void run() {
				mAidlClient = new AidlClient(getApplicationContext());
				Contants.mAidlClient =mAidlClient;
			};
		}.start();

		Intent mIntent = new Intent(this,PreLoadService.class);
		mIntent.putExtra("Action", "CodeLib");
		startService(mIntent);

		
	}

	public static  AidlClient getmAidlClient() {
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
		Intent mIntent = new Intent(this, PreLoadService.class);
		mIntent.putExtra("Action", "GETUSERINFO");
		mIntent.putExtra("userId",mLoginResponse.getUserId());
		startService(mIntent);
	}
	public MitFsHttpClient getDownloadClient() {
		return fileSocketUtil.getMitFsHttpClient();
	}


	public MitFsClient getUploadClient() {
		return fileSocketUtil.getClient();
	}



}
