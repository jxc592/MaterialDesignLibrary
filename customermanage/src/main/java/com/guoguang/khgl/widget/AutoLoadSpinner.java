package com.guoguang.khgl.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.widget.AppCompatSpinner;
import android.util.AttributeSet;

import com.aidl.cilent.AidlClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guoguang.dkkhgl.R;
import com.guoguang.khgl.application.WincomApplication;
import com.guoguang.khgl.database.CodeLib;
import com.guoguang.khgl.database.CodeLibDao;
import com.guoguang.khgl.database.DaoMaster;
import com.guoguang.khgl.database.DaoMaster.DevOpenHelper;
import com.guoguang.khgl.database.DaoSession;
import com.guoguang.khgl.entity.CodelibEntity;
import com.guoguang.khgl.model.Contants;

import java.util.HashMap;
import java.util.List;

public class AutoLoadSpinner extends AppCompatSpinner {
	public AutoLoadSpinner(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initView(context, attrs, 0);
	}
	public AutoLoadSpinner(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initView(context, null, 0);
	}
	DaoSession mDaoSession;
	String unit;
	List<CodeLib> codeLibs;
	AidlClient mAidlClient;
	int loadTime=0;
	Gson gson;
	String header;

    boolean isLoadOk = false;
	Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
            isLoadOk = true;
			CodeLib lib = new CodeLib();
			lib.setCODENO(unit);
			lib.setITEMNO("");
			lib.setITEMNAME("");
			codeLibs.add(0,lib);
			setAdapter(new AutoLoadSpinnerAdapter(codeLibs));
			onLoadSucceed();
		}
	};
	void initView(Context mcontext,AttributeSet attrs,
			int defStyleAttr){
		DevOpenHelper helper = new DevOpenHelper(mcontext, "preload", null);
		SQLiteDatabase db = helper.getWritableDatabase();
		DaoMaster daoMaster = new DaoMaster(db);
		mDaoSession = daoMaster.newSession();
		gson = new GsonBuilder().create();
		mAidlClient = WincomApplication.getmAidlClient();
		
		
		final Resources.Theme theme = mcontext.getTheme();
	    TypedArray a = theme.obtainStyledAttributes(attrs,
	                R.styleable.withunitEdittext, defStyleAttr,0);
	    
		if (a != null) {
			int n = a.getIndexCount();
			for (int i = 0; i < n; i++) {
				int attr = a.getIndex(i);
				switch (attr) {
				case R.styleable.withunitEdittext_unittext:
					unit = a.getString(attr);
					if(unit == null||"".equals(unit)){
						break;
					}
					new Thread(){
						public void run() {
							codeLibs = mDaoSession.queryBuilder(CodeLib.class).where(CodeLibDao.Properties.CODENO.eq(unit)).list();
							if(codeLibs==null||codeLibs.size()==0){
								HashMap<String, String> map = new HashMap<>();
								map.put("TransCode", "1298");
								map.put("CODENO", unit);
								String body= gson.toJson(map);
								String result[] = mAidlClient.exec(Contants.header, body);
								if(result!=null&&result[2]!=null&&!"".equals(result[2])){
									//List<HashMap<String, ValueInfo>>
									CodelibEntity libEntity=gson.fromJson(result[2], CodelibEntity.class);
									//Contants.codelibEntity = libEntity;
									List<CodeLib> codeLibs = libEntity.getCodeList();

									if("YesNo".equals(unit)){
										if(!Contants.YesNoLoaded){
											Contants.YesNoLoaded = true;
											mDaoSession.getCodeLibDao().insertInTx(codeLibs);
										}
									}else {
										mDaoSession.getCodeLibDao().insertInTx(codeLibs);
									}

									AutoLoadSpinner.this.codeLibs = libEntity.getCodeList();
									mHandler.sendEmptyMessage(100);
								}else{
									//ToastCoustom.show("与服务器断开连接"+unit+"数据加载失败，请退出后检查网络并再次打开本程序");
								}
							}else {
								mHandler.sendEmptyMessage(100);
							}
//							while(true){
//								if(loadTime>50){
//									break;
//								}
//								if(codeLibs == null||codeLibs.size()==0){
//									loadTime++;
//									try {
//										Thread.sleep(200);
//									} catch (InterruptedException e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//									}
//									codeLibs = mDaoSession.queryBuilder(CodeLib.class).where(CodeLibDao.Properties.CODENO.eq(unit)).list();
//								}else {
//									
//									break;
//								}
//							}
						}
					}.start();
					
					break;
				default:
					break;
				}
			}
		}
		a.recycle();
	}
	
	@Override
	public Object getSelectedItem() {
		// TODO Auto-generated method stub
		return super.getSelectedItem();
	}
	
	public String getSelectedCode(){
		CodeLib lib= (CodeLib) getSelectedItem();
		if(lib ==null){
			return "";
		}
		return lib.getITEMNO();
	}
	String currentCode = null;
	public void setCode(String code){
		currentCode =code;
		//onLoadSucceed();
	    if(isLoadOk){
            onLoadSucceed();
        }
    }


	
	void onLoadSucceed(){
		if(currentCode == null||"".equals(currentCode)){
			return;
		}
		if(codeLibs!=null&&codeLibs.size()>0){
			for(int i=0;i<codeLibs.size();i++){
				CodeLib lib= codeLibs.get(i); 
				if(currentCode.equals(lib.getITEMNO())){
					setSelection(i);
					break;
				}
			}
		}
	}
}
