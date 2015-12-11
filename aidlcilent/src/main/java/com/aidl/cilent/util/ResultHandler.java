package com.aidl.cilent.util;

import android.os.Handler;
import android.os.Message;

import com.aidl.cilent.entity.HeaderCommonResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Map;

public abstract class ResultHandler<DateType extends Object> extends Handler {
	String[] result;
	Gson gson;
	boolean tag=false;
	Type type;
	Class<DateType> bodyClass;
	public ResultHandler() {
		super();
		// TODO Auto-generated constructor stub
	}	
	
	public ResultHandler(String[] result,Class<DateType> classoft) {
		super();
		gson=new GsonBuilder().create();
		this.result = result;
		bodyClass=classoft;
		dear();
	}
	
	public ResultHandler(String[] result, boolean tag,Class<DateType> classoft) {
		super();
		this.result = result;
		this.tag = tag;
		gson=new GsonBuilder().create();
		bodyClass=classoft;
		dear();
	}

	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		super.handleMessage(msg);
		switch (msg.what) {
		case 1000:
			ToastCoustom.show("返回结果为空");
			break;
		case 1001:
			ToastCoustom.show("报文头数据为空");
			break;
		case 1002:
			ToastCoustom.show("报文身数据为空");
			break;
		case 1003:
			ToastCoustom.show((String) msg.obj);
			break;
		case 1004:
			ToastCoustom.show("数据解析异常");
			break;
		default:
			break;
		}
	}
	private void dear(){
		if(result==null){
			sendEmptyMessage(1000);
			return;
		}
		if(result[1]==null||"".equals(result[1])){
			sendEmptyMessage(1001);
			return;
		}
		try {
			HeaderCommonResponse headerCommonResponse=gson.fromJson(result[1], HeaderCommonResponse.class);
			if(headerCommonResponse==null){
				this.sendEmptyMessage(1001);
			}else if (headerCommonResponse.getResultCode()!=null&&"0000".equals(headerCommonResponse.getResultCode())) {
				if(result[2]==null||"".equals(result[2])){
					this.sendEmptyMessage(1002);
				}else {
					if(tag){
						Map<String, Object> map= BaseUtil.JsonStrToMap(result[2]);
						JSONObject jsonObject=(JSONObject) map.get("resultMap");
						if(jsonObject==null){
							sendEmptyMessage(1002);
							return;
						}
						DateType SuccessBody=(DateType) gson.fromJson(jsonObject.toString(), bodyClass);
						onSuccess(SuccessBody);
					}else {
						DateType SuccessBody=gson.fromJson(result[2], bodyClass);
						onSuccess(SuccessBody);
					}				
				}
			}else if (headerCommonResponse.getMessage()!=null){
				this.obtainMessage(1003,headerCommonResponse.getMessage()).sendToTarget();
			}
		} catch (Exception e) {
			// TODO: handle exception
			this.sendEmptyMessage(1004);
		}
	}
	public abstract void onSuccess(DateType Body);
}
