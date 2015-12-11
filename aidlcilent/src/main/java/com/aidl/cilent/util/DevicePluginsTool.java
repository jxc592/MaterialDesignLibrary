package com.aidl.cilent.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;

import com.aidl.cilent.R;
import com.aidl.cilent.library.sweetdialog.SweetAlertDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guoguang.device.Cj1000bDevice;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 读取外设的工具类
 * 
 * @author thinkpad
 *
 */
public class DevicePluginsTool {
	/**
	 * Android 上下文环境
	 */
	public Context mContext;
	/**
	 * 读取外设的对象
	 */
	public static Cj1000bDevice mCj1000bDevice;

	public static final String IOERROR = "连接异常，请检查后重新连接！";

	/**
	 * 构造读取外设工具类
	 * 
	 * @param mContext
	 */
	public DevicePluginsTool(Context mContext) {
		super();
		this.mContext = mContext;
		// mCj1000bDevice = new Cj1000bDevice(mContext);
		mCj1000bDevice = getIntanse(mContext);
	}
	
	public Context getmContext() {
		return mContext;
	}

	public void setmContext(Context mContext) {
		this.mContext = mContext;
	}

	/**
	 * 获取外设的对象
	 * 
	 * @param mContext
	 *            Android 上下文对象
	 * @return
	 */
	public static final Cj1000bDevice getIntanse(Context mContext) {
		if (mCj1000bDevice == null) {
			mCj1000bDevice = new Cj1000bDevice(mContext);
		}

		return mCj1000bDevice;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isContectDevice() {
		String result = "";
		try {
			if(!mCj1000bDevice.isBindComplete()){
				return false;
			}
			result = mCj1000bDevice.getConnectFlag();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		if (result.contains("false")) {
			selectBingDevice(mContext);
			return false;
		} else if (result.contains("true")) {
			return true;
		}
		return false;

	}

	private class addressEntity {
		private String address;
		private String name;

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	/**
	 * 通过蓝牙，连接外设
	 * 
	 * @param updHandler
	 *            用于回传读取外设数据(obj)
	 * @param what
	 *            用于处理回传数据的状态码
	 * @param deviceAddress
	 *            需要连接的蓝牙设备地址
	 */
	public void connectDevice(Context mContext, final Handler updHandler,
			final int what, final String deviceAddress) {
		mAlertDialog = new SweetAlertDialog(mContext,
				SweetAlertDialog.PROGRESS_TYPE);
		mAlertDialog.setTitleText("请稍候，正在连接外设.");
		mAlertDialog.show();
		Thread mThread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				GsonBuilder mGsonBuilder = new GsonBuilder();
				Gson mGson = mGsonBuilder.create();
				addressEntity mEntity = new addressEntity();
				mEntity.setAddress(deviceAddress);
				// mEntity.setAddress("20:14:04:29:41:26");
				mEntity.setName("移动金融外设");
				String idinfo = null;
				String jsonAddress = mGson.toJson(mEntity);
				String str = "";
				try {
					str = mCj1000bDevice.connectBlueteeth(jsonAddress);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("result", false);
					map.put("resultMsg", "外设出异常，请重新连接...");
					str = BaseUtil.MapToJsonStr(map);
				}
				// String str = "{\"result\":true}";
				Message msg2 = new Message();
				msg2.what = 1;
				updStatusHander.sendMessage(msg2);
				Message msg = new Message();
				msg.what = what;
				msg.obj = str;
				updHandler.sendMessage(msg);
			}
		});

		mThread.start();
	}

	public void connectDevice(final Handler updHandler, final int what,
			final String deviceAddress) {
		mAlertDialog = new SweetAlertDialog(mContext,
				SweetAlertDialog.PROGRESS_TYPE);
		mAlertDialog.setTitleText("请稍候，正在连接外设.");
		mAlertDialog.show();
		Thread mThread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				GsonBuilder mGsonBuilder = new GsonBuilder();
				Gson mGson = mGsonBuilder.create();
				addressEntity mEntity = new addressEntity();
				mEntity.setAddress(deviceAddress);
				// mEntity.setAddress("20:14:04:29:41:26");
				mEntity.setName("移动金融外设");
				String idinfo = null;
				String jsonAddress = mGson.toJson(mEntity);
				String str = "";
				try {
					str = mCj1000bDevice.connectBlueteeth(jsonAddress);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("result", false);
					map.put("resultMsg", "外设出异常，请重新连接...");
					str = BaseUtil.MapToJsonStr(map);
				}
				// String str = "{\"result\":true}";
				Message msg2 = new Message();
				msg2.what = 1;
				updStatusHander.sendMessage(msg2);
				Message msg = new Message();
				msg.what = what;
				msg.obj = str;
				updHandler.sendMessage(msg);
			}
		});

		mThread.start();
	}

	boolean isreadIDCard = false;
	SweetAlertDialog mAlertDialog = null;

	/**
	 * 读取二代身份证
	 * 
	 * @param updHandler
	 *            用于回传读取外设数据(obj)
	 * @param what
	 *            用于处理回传数据的状态码
	 */
	public void readIdCard(final Handler updHandler, final int what,
			Context context) {
		if (!isContectDevice()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resultMsg", "外设连接已断开，请重新连接");
			map.put("result", false);
			Message msg = new Message();
			msg.what = what;
			msg.obj = BaseUtil.MapToJsonStr(map);
			updHandler.sendMessage(msg);
			return;
		}
		mAlertDialog = new SweetAlertDialog(context,
				SweetAlertDialog.PROGRESS_TYPE);
		mAlertDialog.setTitleText("请稍候，正在读取二代证信息.");
		mAlertDialog.show();
		Thread mThread = new Thread(new Runnable() {
			public void run() {
				String idcardInfo = "";// 身份证信息
				try {
					idcardInfo = mCj1000bDevice.readIdCard();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("result", false);
					map.put("resultMsg", "外设出异常，请重新连接...");
					idcardInfo = BaseUtil.MapToJsonStr(map);
				}
				// idcardInfo =
				// "{\"startDate\":\"2013:05:08\",\"result\":true,\"sex\":\"男\",\"issueDepartmanet\":\"上栗县公安局\",\"address\":\"江西省萍乡市上栗县赤山镇赤山村沿河路10号\",\""
				// +
				// "name\":\"李根\",\"endDate\":\"2033:05:08\",\"number\":\"360311198510074012\",\"photo\":\"Qk3OlwAAAAAAADYAAAAoAAAAZgAAAH4AAAABABgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAQjs4\nPzg1PTYzOjMwODEuODE\"}";
				judgeBTDisconnect(idcardInfo);

				Message msg = new Message();
				msg.what = 100;
				updStatusHander.sendMessage(msg);
				Message msg2 = new Message();
				msg2.what = what;
				if (idcardInfo == null || "".equals(idcardInfo)) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("resultMsg", "读取二代证失败，请重试");
					map.put("result", false);
					msg2.obj = BaseUtil.MapToJsonStr(map);
				} else {
					msg2.obj = idcardInfo;
				}
				updHandler.sendMessage(msg2);
			}
		});
		mThread.start();
	}

	boolean isreadFinger = false;

	/**
	 * 读取指纹
	 * 
	 * @param updHandler
	 *            用于回传读取外设数据(obj)
	 * @param what
	 *            用于处理回传数据的状态码
	 */
	public void readFinger(final Handler updHandler, final int what,
			Context context) {
		if (!isContectDevice()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resultMsg", "外设连接已断开，请重新连接");
			map.put("result", false);
			Message msg = new Message();
			msg.what = what;
			msg.obj = BaseUtil.MapToJsonStr(map);
			updHandler.sendMessage(msg);
			return;
		}
		mAlertDialog = new SweetAlertDialog(context,
				SweetAlertDialog.PROGRESS_TYPE);
		mAlertDialog.setTitleText("请稍候，正在读取指纹信息.");
		mAlertDialog.show();
		Thread mThread = new Thread(new Runnable() {
			public void run() {
				String fingerInfo = "";// 身份证信息
				try {
					fingerInfo = mCj1000bDevice.readFinger();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("result", false);
					map.put("resultMsg", "外设出异常，请重新连接...");
					fingerInfo = BaseUtil.MapToJsonStr(map);
				}
				// fingerInfo =
				// "{\"resultMsg\":\"14868114A782B1800012707C180002BC12CA000044826EE0000984670C00015B0FE2C0000A3250F0000514688D000028CD43C0001089B5D4000051393C0000568789700001A8FA20000009A09A000027F42C7000005886E800008A12AE400013BACE7400004065A20000070D255000000455\",\"result\":true}";
				judgeBTDisconnect(fingerInfo);

				Message msg = new Message();
				msg.what = 200;
				msg.obj = fingerInfo;
				updStatusHander.sendMessage(msg);
				Message msg2 = new Message();
				msg2.what = what;
				if (fingerInfo == null || "".equals(fingerInfo)) {
					Map<String, Object> resultMap = new HashMap<String, Object>();
					resultMap.put("resultMsg", "读指纹失败,请重试..");
					resultMap.put("result", false);
					// BaseUtil.MapToJsonStr(resultMap);
					msg2.obj = BaseUtil.MapToJsonStr(resultMap);
				} else {
					msg2.obj = fingerInfo;
				}
				updHandler.sendMessage(msg2);
			}
		});
		mThread.start();
	}

	boolean registFinger = false;

	/**
	 * 读取指纹
	 * 
	 * @param updHandler
	 *            用于回传读取外设数据（obj）
	 * @param what
	 */
	public void registFinger(final Handler updHandler, final int what) {
		if (!isContectDevice()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resultMsg", "外设连接已断开，请重新连接");
			map.put("result", false);
			String str = BaseUtil.MapToJsonStr(map);
			Message msg = new Message();
			msg.what = what;
			msg.obj = str;
			updHandler.sendMessage(msg);
			return;
		}
		mAlertDialog = new SweetAlertDialog(mContext,
				SweetAlertDialog.PROGRESS_TYPE);
		mAlertDialog.setTitleText("请稍候，正在读取指纹信息.");
		mAlertDialog.show();
		Thread mThread = new Thread(new Runnable() {
			public void run() {
				String registInfo = "";// 身份证信息
				try {
					registInfo = mCj1000bDevice.registFinger();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("result", false);
					map.put("resultMsg", "外设出异常，请重新连接...");
					registInfo = BaseUtil.MapToJsonStr(map);
				}
				judgeBTDisconnect(registInfo);
				// fingerInfo =
				// "{\"resultMsg\":\"14868114A782B1800012707C180002BC12CA000044826EE0000984670C00015B0FE2C0000A3250F0000514688D000028CD43C0001089B5D4000051393C0000568789700001A8FA20000009A09A000027F42C7000005886E800008A12AE400013BACE7400004065A20000070D255000000455\",\"result\":true}";
				Message msg = new Message();
				msg.what = 200;
				msg.obj = registInfo;
				updStatusHander.sendMessage(msg);
				Message msg2 = new Message();
				msg2.what = what;
				if (registInfo == null || "".equals(registInfo)) {
					Map<String, Object> resultMap = new HashMap<String, Object>();
					resultMap.put("resultMsg", "注册指纹失败,请重试..");
					resultMap.put("result", false);
					// BaseUtil.MapToJsonStr(resultMap);
					msg2.obj = BaseUtil.MapToJsonStr(resultMap);
				} else {
					msg2.obj = registInfo;
				}
				updHandler.sendMessage(msg2);
			}
		});
		mThread.start();
	}

	/**
	 * 读取ic卡
	 * 
	 * @param updHandler
	 *            用于回传读取外设数据(obj)
	 * @param what
	 *            用于处理回传数据的状态码
	 */
	public void readICCard(final Handler updHandler, final int what,
			Context context) {
		if (!isContectDevice()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resultMsg", "外设连接已断开，请重新连接");
			map.put("result", false);
			Message msg = new Message();
			msg.what = what;
			msg.obj = BaseUtil.MapToJsonStr(map);
			updHandler.sendMessage(msg);
			return;
		}
		mAlertDialog = new SweetAlertDialog(context,
				SweetAlertDialog.PROGRESS_TYPE);
		mAlertDialog.setTitleText("请稍候，正在读取IC卡信息.");
		mAlertDialog.show();
		Thread mThread = new Thread(new Runnable() {
			public void run() {
				String icInfo = "";//
				try {
					icInfo = mCj1000bDevice.readIcCard(2);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("result", false);
					map.put("resultMsg", "外设出异常，请重新连接...");
					icInfo = BaseUtil.MapToJsonStr(map);
				} // 1：接触卡 2:非接触
				judgeBTDisconnect(icInfo);
				// icInfo =
				// "{\"id\":\"6217710500436845D24\",\"resultMsg\":\"success\",\"result\":true}";
				Message msg = new Message();
				msg.what = 300;
				msg.obj = icInfo;
				updStatusHander.sendMessage(msg);
				Message msg2 = new Message();
				msg2.what = what;
				if (icInfo == null || "".equals(icInfo)) {
					Map<String, Object> resultMap = new HashMap<String, Object>();
					resultMap.put("resultMsg", "读IC卡失败,请重试..");
					resultMap.put("result", false);
					// BaseUtil.MapToJsonStr(resultMap);
					msg2.obj = BaseUtil.MapToJsonStr(resultMap);
				} else {
					msg2.obj = icInfo;
				}
				updHandler.sendMessage(msg2);
			}
		});
		mThread.start();
	}

	/**
	 * 读取ic卡
	 * 
	 * @param updHandler
	 *            用于回传读取外设数据（obj）
	 * @param what
	 */
	public void readICCardArgc(final Handler updHandler, final int what) {
		if (!isContectDevice()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resultMsg", "外设连接已断开，请重新连接");
			map.put("result", false);
			Message msg = new Message();
			msg.what = what;
			msg.obj = BaseUtil.MapToJsonStr(map);
			updHandler.sendMessage(msg);
			return;
		}
		mAlertDialog = new SweetAlertDialog(mContext,
				SweetAlertDialog.PROGRESS_TYPE);
		mAlertDialog.setTitleText("请稍候，正在读取IC卡信息.");
		mAlertDialog.show();
		Thread mThread = new Thread(new Runnable() {
			public void run() {
				String icarqc = "";//
				// type 1：接触卡 2:非接触 AIDList A0000003330101 tglList "AJL"
				try {
					icarqc = mCj1000bDevice.readIcCardArqc(1, "A0000003330101",
							"AJL", /* "P012000000000001Q012000000000000R0040156S006141009T002U006112406W004GZNX" */
							createTxData("0"));
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("result", false);
					map.put("resultMsg", "外设出异常，请重新连接...");
					icarqc = BaseUtil.MapToJsonStr(map);
				} // 1：接触卡 2:非接触
				judgeBTDisconnect(icarqc);
				// icInfo =
				// "{\"id\":\"6217710500436845D24\",\"resultMsg\":\"success\",\"result\":true}";
				Message msg = new Message();
				msg.what = 300;
				msg.obj = icarqc;
				updStatusHander.sendMessage(msg);
				Message msg2 = new Message();
				msg2.what = what;
				if (icarqc == null || "".equals(icarqc)) {
					Map<String, Object> resultMap = new HashMap<String, Object>();
					resultMap.put("resultMsg", "读IC卡失败,请重试..");
					resultMap.put("result", false);
					// BaseUtil.MapToJsonStr(resultMap);
					msg2.obj = BaseUtil.MapToJsonStr(resultMap);
				} else {
					msg2.obj = icarqc;
				}
				updHandler.sendMessage(msg2);
			}
		});
		mThread.start();
	}

	/**
	 * 读取ic卡
	 * 
	 * @param updHandler
	 *            用于回传读取外设数据（obj）
	 * @param what
	 */
	public void readICCardArgc(final Handler updHandler, final int what,
			Context mCon) {
		if (!isContectDevice()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resultMsg", "外设连接已断开，请重新连接");
			map.put("result", false);
			Message msg = new Message();
			msg.what = what;
			msg.obj = BaseUtil.MapToJsonStr(map);
			updHandler.sendMessage(msg);
			return;
		}
		mAlertDialog = new SweetAlertDialog(mCon,
				SweetAlertDialog.PROGRESS_TYPE);
		mAlertDialog.setTitleText("请稍候，正在读取IC卡.");
		mAlertDialog.show();
		Thread mThread = new Thread(new Runnable() {
			public void run() {
				String icarqc = "";//
				// type 1：接触卡 2:非接触 AIDList A0000003330101 tglList "AJL"
				try {
					icarqc = mCj1000bDevice.readIcCardArqc(1, "A0000003330101",
							"AJL", /* "P012000000000001Q012000000000000R0040156S006141009T002U006112406W004GZNX" */
							createTxData("0"));
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("result", false);
					map.put("resultMsg", "外设出异常，请重新连接...");
					icarqc = BaseUtil.MapToJsonStr(map);

				} // 1：接触卡 2:非接触
				judgeBTDisconnect(icarqc);
				// icInfo =
				// "{\"id\":\"6217710500436845D24\",\"resultMsg\":\"success\",\"result\":true}";
				Message msg = new Message();
				msg.what = 300;
				msg.obj = icarqc;
				updStatusHander.sendMessage(msg);
				Message msg2 = new Message();
				msg2.what = what;
				if (icarqc == null || "".equals(icarqc)) {
					Map<String, Object> resultMap = new HashMap<String, Object>();
					resultMap.put("resultMsg", "读IC卡失败,请重试..");
					resultMap.put("result", false);
					// BaseUtil.MapToJsonStr(resultMap);
					msg2.obj = BaseUtil.MapToJsonStr(resultMap);
				} else {
					msg2.obj = icarqc;
				}
				updHandler.sendMessage(msg2);
			}
		});
		mThread.start();
	}

	public String createTxData(String amt) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String date = sdf.format(new Date());
		amt = amt + "00";
		int len = amt.length();
		for (int i = 0; i < 12 - len; i++) {
			amt = "0" + amt;
		}
		String dateStr = date.substring(0, 6);
		dateStr = "S006" + dateStr;
		String timeStr = date.substring(6, 12);
		timeStr = "U006" + timeStr;
		String txData = "P012" + amt + "Q012000000000000R0040156" + dateStr
				+ "T00200" + "W004GZNX";

		System.out.println("--txData--  " + txData);

		// String
		// txData="P012000000000001Q012000000000000R0040156S006141009T002U006112406W004GZNX";
		// P012000000000001Q012000000000000R0040156S006141009T002U006112406W004GZNX
		// String
		// txData="P012000050000000Q012000000000000R0040156S006140611T002U006140611W004GZNX"
		return txData;
	}

	/**
	 * 读取磁条卡
	 * 
	 * @param updHander
	 *            用于回传读取外设数据(obj)
	 * @param what
	 *            用于处理回传数据的状态码
	 */
	public void readmsgCard(final Handler updHandler, final int what,
			Context context) {
		if (!isContectDevice()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resultMsg", "外设连接已断开，请重新连接");
			map.put("result", false);
			Message msg = new Message();
			msg.what = what;
			msg.obj = BaseUtil.MapToJsonStr(map);
			updHandler.sendMessage(msg);
			return;
		}
		mAlertDialog = new SweetAlertDialog(context,
				SweetAlertDialog.PROGRESS_TYPE);
		mAlertDialog.setTitleText("请稍候，正在读取IC卡信息.");
		mAlertDialog.show();
		Thread mThread = new Thread(new Runnable() {
			public void run() {
				String msgCardinfo = "";//
				try {
					msgCardinfo = mCj1000bDevice.readMagCard();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("result", false);
					map.put("resultMsg", "外设出异常，请重新连接...");
					msgCardinfo = BaseUtil.MapToJsonStr(map);
				} // 1：接触卡 2:非接触
				judgeBTDisconnect(msgCardinfo);
				Message msg = new Message();
				msg.what = 400;
				msg.obj = msgCardinfo;
				updStatusHander.sendMessage(msg);
				Message msg2 = new Message();
				msg2.what = what;
				if (msgCardinfo == null || "".equals(msgCardinfo)) {
					Map<String, Object> resultMap = new HashMap<String, Object>();
					resultMap.put("resultMsg", "读取磁条卡失败,请重试..");
					resultMap.put("result", false);
					// BaseUtil.MapToJsonStr(resultMap);
					msg2.obj = BaseUtil.MapToJsonStr(resultMap);
				} else {
					msg2.obj = msgCardinfo;
				}
				updHandler.sendMessage(msg2);
			}
		});
		mThread.start();
	}

	String printdata = "";//

	/**
	 * 连接外设打印
	 * 
	 * @param printData
	 *            需要打印的数据
	 * 
	 */
	public void printData(final String printData) {

//		mAlertDialog = new SweetAlertDialog(mContext,
//				SweetAlertDialog.PROGRESS_TYPE);
//		mAlertDialog.setTitleText("请稍候，正在打印.");
//		mAlertDialog.show();

		Thread mThread = new Thread(new Runnable() {
			public void run() {
				// 打印
				try {
					mCj1000bDevice.printData(printData);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				; // 1：接触卡 2:非接触
//				Message msg = new Message();
//				msg.what = 500;
//				// msg.obj = msgCardinfo;
//				updStatusHander.sendMessage(msg);
				// Message msg2 = new Message();
				// msg2.what= what;
				/*
				 * if (msgCardinfo == null ||"".equals(msgCardinfo)) {
				 * Map<String, Object> resultMap= new HashMap<String, Object>();
				 * resultMap.put("resultMsg", "读取磁条卡失败,请重试..");
				 * resultMap.put("result", false); //
				 * BaseUtil.MapToJsonStr(resultMap); msg2.obj =
				 * BaseUtil.MapToJsonStr(resultMap); }else{ msg2.obj =
				 * msgCardinfo; } updHandler.sendMessage(msg2);
				 */
			}
		});
		mThread.start();
	}

	/**
	 * 连接外设打印
	 * 
	 * @param printData
	 *            需要打印的数据
	 * 
	 */
	public void printData(final String printData, Context con) {

		mAlertDialog = new SweetAlertDialog(con, SweetAlertDialog.PROGRESS_TYPE);
		mAlertDialog.setTitleText("请稍候，正在打印.");
		mAlertDialog.show();

		Thread mThread = new Thread(new Runnable() {
			public void run() {
				// 打印
				try {
					mCj1000bDevice.printData(printData);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				; // 1：接触卡 2:非接触

				Message msg = new Message();
				msg.what = 500;
				// msg.obj = msgCardinfo;
				updStatusHander.sendMessage(msg);
				// Message msg2 = new Message();
				// msg2.what= what;
				/*
				 * if (msgCardinfo == null ||"".equals(msgCardinfo)) {
				 * Map<String, Object> resultMap= new HashMap<String, Object>();
				 * resultMap.put("resultMsg", "读取磁条卡失败,请重试..");
				 * resultMap.put("result", false); //
				 * BaseUtil.MapToJsonStr(resultMap); msg2.obj =
				 * BaseUtil.MapToJsonStr(resultMap); }else{ msg2.obj =
				 * msgCardinfo; } updHandler.sendMessage(msg2);
				 */
			}
		});
		mThread.start();
	}

	/**
	 * 获取密码键盘的明文
	 * 
	 * @param updHandler
	 *            用于回传读取外设数据（obj）
	 * @param what
	 *            用于处理回传数据的状态码
	 */
	public void getPin(final Handler updHandler, final int what, Context context) {
		if (!isContectDevice()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resultMsg", "外设连接已断开，请重新连接");
			map.put("result", false);
			Message msg = new Message();
			msg.what = what;
			msg.obj = BaseUtil.MapToJsonStr(map);
			updHandler.sendMessage(msg);
			return;
		}
		mAlertDialog = new SweetAlertDialog(context,
				SweetAlertDialog.PROGRESS_TYPE);
		mAlertDialog.setTitleText("请稍候，正在输入密码.");
		mAlertDialog.show();
		Thread mThread = new Thread(new Runnable() {
			public void run() {
				String pwdMw = "";//
				try {
					pwdMw = mCj1000bDevice.getPin();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("result", false);
					map.put("resultMsg", "外设出异常，请重新连接...");
					pwdMw = BaseUtil.MapToJsonStr(map);
				} // 1：接触卡 2:非接触
				judgeBTDisconnect(pwdMw);
				Message msg = new Message();
				msg.what = 600;
				// msg.obj = pwdMw;
				updStatusHander.sendMessage(msg);
				Message msg2 = new Message();
				msg2.what = what;
				if (pwdMw == null || "".equals(pwdMw)) {
					Map<String, Object> resultMap = new HashMap<String, Object>();
					resultMap.put("resultMsg", "输入密码错误,请重试..");
					resultMap.put("result", false);
					// BaseUtil.MapToJsonStr(resultMap);
					msg2.obj = BaseUtil.MapToJsonStr(resultMap);
				} else {
					msg2.obj = pwdMw;
				}
				updHandler.sendMessage(msg2);
			}
		});
		mThread.start();
	}

	/**
	 * 
	 * 初始化工作密钥
	 * 
	 * @param updHandler
	 *            用于回传读取外设数据（obj）
	 * @param what
	 *            用于处理回传数据的状态码
	 * @param workKey
	 *            工作密钥
	 */
	public void getinitEpinPadWork(final Handler updHandler, final int what,
			String workKey, Context context) {
		if (!isContectDevice()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resultMsg", "外设连接已断开，请重新连接");
			map.put("result", false);
			Message msg = new Message();
			msg.what = what;
			msg.obj = BaseUtil.MapToJsonStr(map);
			updHandler.sendMessage(msg);
			return;
		}
		mAlertDialog = new SweetAlertDialog(context,
				SweetAlertDialog.PROGRESS_TYPE);
		mAlertDialog.setTitleText("请稍候，初始化密码键盘.");
		mAlertDialog.show();
		// final String workKeyChange =
		// "F37E4C0D29D27635207ACBECD12B697C7D3DAD30A97212C7";
		final String workKeyChange = workKey;
		Thread mThread = new Thread(new Runnable() {
			public void run() {
				String initWorkkey = "";//
				try {
					initWorkkey = mCj1000bDevice.initEpinpad(
							"00000000000000000000000000000000", workKeyChange);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("result", false);
					map.put("resultMsg", "外设出异常，请重新连接...");
					initWorkkey = BaseUtil.MapToJsonStr(map);

				} // 1：接触卡 2:非接触
					// initWorkkey = "{\"resultMsg\":\"NULL\",\"result\":true}";
				judgeBTDisconnect(initWorkkey);
				Message msg = new Message();
				msg.what = 800;
				// msg.obj = pwdMw;
				updStatusHander.sendMessage(msg);
				Message msg2 = new Message();
				msg2.what = what;
				if (initWorkkey == null || "".equals(initWorkkey)) {
					Map<String, Object> resultMap = new HashMap<String, Object>();
					resultMap.put("resultMsg", "初始化密码键盘错误..");
					resultMap.put("result", false);
					// BaseUtil.MapToJsonStr(resultMap);
					msg2.obj = BaseUtil.MapToJsonStr(resultMap);
				} else {
					msg2.obj = initWorkkey;
				}
				updHandler.sendMessage(msg2);
			}
		});
		mThread.start();
	}

	/**
	 * 获取密码键盘的明文
	 * 
	 * @param context
	 *            用于回传读取外设数据（obj）
	 * @param what
	 *            用于处理回传数据的状态码
	 */
	public void getPinBlock(final Handler updHandler, final int what,
			Context context, final String idcardNumber) {
		if (!isContectDevice()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resultMsg", "外设连接已断开，请重新连接");
			map.put("result", false);
			Message msg = new Message();
			msg.what = what;
			msg.obj = BaseUtil.MapToJsonStr(map);
			updHandler.sendMessage(msg);
			return;
		}
		mAlertDialog = new SweetAlertDialog(context,
				SweetAlertDialog.PROGRESS_TYPE);
		mAlertDialog.setTitleText("请稍候，正在输入密码.");
		mAlertDialog.show();
		Thread mThread = new Thread(new Runnable() {
			public void run() {
				// 播放视频
				MediaPlayer player = MediaPlayer.create(mContext,
						R.raw.linsheng);
				player.start();
				String pinblock = "";//
				try {
					pinblock = mCj1000bDevice
							.getPinBlock(idcardNumber/* "622511102700000021" */);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("result", false);
					map.put("resultMsg", "外设出异常，请重新连接...");
					pinblock = BaseUtil.MapToJsonStr(map);
				}
				// //1：接触卡 2:非接触
				// pinblock =
				// "{\"resultMsg\":\"083AEA9B7A85B989\",\"result\":true}";
				judgeBTDisconnect(pinblock);
				Message msg = new Message();
				msg.what = 900;
				// msg.obj = pwdMw;
				updStatusHander.sendMessage(msg);
				Message msg2 = new Message();
				msg2.what = what;
				if (pinblock == null || "".equals(pinblock)) {
					Map<String, Object> resultMap = new HashMap<String, Object>();
					resultMap.put("resultMsg", "读取密码错误..");
					resultMap.put("result", false);
					// BaseUtil.MapToJsonStr(resultMap);
					msg2.obj = BaseUtil.MapToJsonStr(resultMap);
				} else {
					msg2.obj = pinblock;
				}
				updHandler.sendMessage(msg2);
			}
		});
		mThread.start();
	}

	/**
	 * 用于更新UI，弹出框消失
	 */
	Handler updStatusHander = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				if (mAlertDialog != null && mAlertDialog.isShowing()) {
					mAlertDialog.dismiss();
				}
				break;
			case 100:// 读取身份证
				if (mAlertDialog != null && mAlertDialog.isShowing()) {
					mAlertDialog.dismiss();
				}
				break;
			case 200:// 读取指纹
				if (mAlertDialog != null && mAlertDialog.isShowing()) {
					mAlertDialog.dismiss();
				}
			case 300:// ic卡
				if (mAlertDialog != null && mAlertDialog.isShowing()) {
					mAlertDialog.dismiss();
				}

			case 400:// 词条卡
				if (mAlertDialog != null && mAlertDialog.isShowing()) {
					mAlertDialog.dismiss();
				}
			case 500:// 打印数据
//				if (mAlertDialog != null && mAlertDialog.isShowing()) {
//					mAlertDialog.dismiss();
//				}
			case 600:// 获取明文
				if (mAlertDialog != null && mAlertDialog.isShowing()) {
					mAlertDialog.dismiss();
				}
			case 800:// 获取明文
				if (mAlertDialog != null && mAlertDialog.isShowing()) {
					mAlertDialog.dismiss();
				}
				break;
			case 900:// 获取明文
				if (mAlertDialog != null && mAlertDialog.isShowing()) {
					mAlertDialog.dismiss();
				}
				break;
			case 1001://
				if (mAlertDialog != null && mAlertDialog.isShowing()) {
					mAlertDialog.dismiss();
				}
				ToastCoustom.show("外设连接成功..");
				break;
			case 1002://
				if (mAlertDialog != null && mAlertDialog.isShowing()) {
					mAlertDialog.dismiss();
				}
				ToastCoustom.show("外设连接失败..");
				break;
			default:
				break;
			}
		}
	};

	/**
	 * 判断蓝牙是否断开连接
	 */
	private void judgeBTDisconnect(String resultMessage) {
		try {
			HashMap<String, Object> hashMap = (HashMap<String, Object>) BaseUtil
					.JsonStrToMap(resultMessage);
			if ("false".equals(hashMap.get("result"))) {
				if (IOERROR.equals(hashMap.get("resultMsg"))) {
					Thread mThread = new Thread(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							String str = "";
							int xunsize = 0;
							while (xunsize < 3) {
								try {
									if (BaseUtil.getAdress() != null
											&& !"".equals(BaseUtil.getAdress())) {
										str = mCj1000bDevice
												.connectBlueteeth(BaseUtil
														.getAdress());
									}
								} catch (RemoteException e) {
									xunsize = 3;
									break;
								}
								if (!"".equals(str)) {
									HashMap<String, Object> hashMapstr = (HashMap<String, Object>) BaseUtil
											.JsonStrToMap(str);
									if ("true".equals(hashMapstr.get("result"))) {
										xunsize = 3;
										break;
									}
								}
								xunsize++;
							}
						}
					});
					mThread.start();
				}
			}
		} catch (Exception e) {

		}
	}
	
	Context dialogContext;
	
	
	public Context getDialogContext() {
		return dialogContext;
	}

	public void setDialogContext(Context dialogContext) {
		this.dialogContext = dialogContext;
	}

	public void selectBingDevice(Context mContext){
		if (BaseUtil.getAdress() == null || "".equals(BaseUtil.getAdress())) {
			return;
		}
		if (mAlertDialog != null && mAlertDialog.isShowing()) {
			mAlertDialog.setTitleText("请稍候，正在连接外设.");
		}else{
			mAlertDialog = new SweetAlertDialog(mContext,
					SweetAlertDialog.PROGRESS_TYPE);
			mAlertDialog.setTitleText("请稍候，正在连接外设.");
			mAlertDialog.show();
		}
	
		try {
			Thread mThread = new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					String	str = "";
						try {
					if (BaseUtil.getAdress() != null && !"".equals(BaseUtil.getAdress())) {
						GsonBuilder mGsonBuilder = new GsonBuilder();
						Gson mGson = mGsonBuilder.create();
						addressEntity mEntity = new addressEntity();
						mEntity.setAddress(BaseUtil.getAdress());
						// mEntity.setAddress("20:14:04:29:41:26");
						mEntity.setName("hc_06");
							str = mCj1000bDevice.connectBlueteeth(mGson.toJson(mEntity));
						  }
						} catch (RemoteException e) {
						}
						if (!"".equals(str)) {
							HashMap<String, Object> hashMapstr = (HashMap<String, Object>) BaseUtil
									.JsonStrToMap(str);
							if ((boolean) hashMapstr.get("result")) {
							updStatusHander.sendEmptyMessage(1001);
							}else{
							updStatusHander.sendEmptyMessage(1002);	
							}
						}
				}
			});
			mThread.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
