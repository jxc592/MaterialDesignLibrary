package com.aidl.cilent.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.aidl.cilent.AidlClient;
import com.aidl.cilent.entity.HeaderCommonEntityRequest;
import com.aidl.cilent.library.sweetdialog.SweetAlertDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

;

public abstract class TransationThread <REQUESTBODY extends Object,RESPONCE extends Object> extends Thread{
		
		HeaderCommonEntityRequest headerCommonEntityRequest;
		REQUESTBODY body;
		Class innerClass;
		Gson gson;
		String showingText;
		Context context;
		SweetAlertDialog sweetAlertDialog;
		RESPONCE responce;
		
		public static int TYPE_RESULTMAP=1;
		public static int TYPE_RESULT=2;
		
		private int type=TYPE_RESULT;
		
		private TransationThread() {
			
		}
		public TransationThread(Context context,AidlClient mAidlClient,
				HeaderCommonEntityRequest headerCommonEntityRequest, REQUESTBODY body,
				Class innerClass,String showtext) {
			super();
			this.headerCommonEntityRequest = headerCommonEntityRequest;
			this.body = body;
			this.innerClass = innerClass;
			showingText=showtext;
			this.context=context;
			this.mAidlClient=mAidlClient;
			gson=new GsonBuilder().create();
		}
		public TransationThread(Context context,AidlClient mAidlClient,
				HeaderCommonEntityRequest headerCommonEntityRequest, REQUESTBODY body,int type,
 				Class innerClass,String showtext) {
			super();
			this.headerCommonEntityRequest = headerCommonEntityRequest;
			this.body = body;
			this.innerClass = innerClass;
			showingText=showtext;
			this.context=context;
			this.mAidlClient=mAidlClient;
			gson=new GsonBuilder().create();
			this.type=type;
		}
		
		
		AidlClient mAidlClient;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			handler.sendEmptyMessage(100);
			String[] results = mAidlClient.exec(gson.toJson(headerCommonEntityRequest), gson.toJson(body));
			handler.sendEmptyMessage(101);
			Looper.prepare();
			if(type==TYPE_RESULTMAP){
				new ResultHandler<RESPONCE>(results,true,innerClass) {
					@Override
					public void onSuccess(RESPONCE Body) {
						// TODO Auto-generated method stub
						responce=Body;
						handler.sendEmptyMessage(102);
					}
				};
			}else {
				new ResultHandler<RESPONCE>(results,innerClass) {
					@Override
					public void onSuccess(RESPONCE Body) {
						// TODO Auto-generated method stub
						responce=Body;
						handler.sendEmptyMessage(102);
					}
				};
			}
			Looper.loop();
		}
		Handler handler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				switch (msg.what) {
				case 100:
					showDialog();
					break;
				case 101:
					disMisDialog();
					break;
				case 102:
					onSuccess(responce);
					break;
				default:
					break;
				}
			}
		};
		void showDialog(){
			if(sweetAlertDialog==null){
				sweetAlertDialog=new SweetAlertDialog(context,SweetAlertDialog.PROGRESS_TYPE);
				sweetAlertDialog.setTitleText(showingText);
				sweetAlertDialog.setCancelable(false);
			}
			sweetAlertDialog.show();
		}
		void disMisDialog(){
			if(sweetAlertDialog!=null&&sweetAlertDialog.isShowing()){
				sweetAlertDialog.dismiss();
			}
		}
		public abstract void onSuccess(RESPONCE body);
	}