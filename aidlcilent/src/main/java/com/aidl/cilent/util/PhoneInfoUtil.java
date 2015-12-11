package com.aidl.cilent.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * 说明:获取手机信息的工具类 1. 手机的IMEI 2.手机的制式类型，GSM OR CDMA 手机 3.手机网络国家编码 4.手机网络运营商ID。
 * 5.手机网络运营商名称 6.手机的数据链接类型 7.是否有可用数据链接 8.当前的数据链接类型 9.手机剩余内存 10.手机总内存 11.手机CPU型号
 * 12.手机名称 13.手机型号 14.手机设备制造商名称 retrieve phone info
 * 
 * 
 */
public class PhoneInfoUtil {
	private static final String TAG = PhoneInfoUtil.class.getSimpleName();
	private static final String FILE_MEMORY = "/proc/meminfo";
	private static final String FILE_CPU = "/proc/cpuinfo";
	public String mIMEI;
	public int mPhoneType;
	public int mSysVersion;
	public String mNetWorkCountryIso;
	public String mNetWorkOperator;
	public String mNetWorkOperatorName;
	public int mNetWorkType;
	public boolean mIsOnLine;
	public String mConnectTypeName;
	public long mFreeMem;
	public long mTotalMem;
	public String mCupInfo;
	public String mProductName;
	public String mModelName;
	public String mManufacturerName;

	/**
	 * private constructor
	 */
	private PhoneInfoUtil() {

	}

	/**
	 * get imei
	 * 
	 * @return
	 */
	public static String getIMEI(Context context) {
		String strReturn = "";
		try {

			TelephonyManager manager = (TelephonyManager) context
					.getSystemService(Activity.TELEPHONY_SERVICE);
			// check if has the permission
			if (PackageManager.PERMISSION_GRANTED == context
					.getPackageManager().checkPermission(
							Manifest.permission.READ_PHONE_STATE,
							context.getPackageName())) {
				strReturn = manager.getDeviceId();
			} else {
				strReturn = "";
			}
		} catch (Exception e) {
			strReturn = "";
		}
		// strReturn="123456";
		return strReturn;
	}

	/***
	 * 获取sim卡号
	 * 
	 * @param context
	 * @return
	 */
	public static String getSim(Context context) {
		String strReturn = "";
		try {
			// 创建电话管理
			// 与手机建立连接
			TelephonyManager tm = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			// 获取手机号码
			String phoneId = tm.getSimSerialNumber();
			// String phoneId = tm.getLine1Number();
			strReturn = phoneId;
		} catch (Exception e) {
			strReturn = "654321";

		}

		String jiesb = "";
		if (strReturn == null || "".equals(strReturn)) {
			jiesb = "654321";
		} else {
			jiesb = strReturn.substring(0, 19);
		}
		return jiesb;

	}

	/**
	 * get phone type,like :GSM
	 * 
	 * @param context
	 * @return
	 */
	public static int getPhoneType(Context context) {
		TelephonyManager manager = (TelephonyManager) context
				.getSystemService(Activity.TELEPHONY_SERVICE);
		return manager.getPhoneType();
	}

	/**
	 * get phone sys version
	 * 
	 * @return
	 */
	public static int getSysVersion() {
		return Build.VERSION.SDK_INT;
	}

	/**
	 * Returns the ISO country code equivalent of the current registered
	 * operator's MCC (Mobile Country Code).
	 * 
	 * @param context
	 * @return
	 */
	public static String getNetWorkCountryIso(Context context) {
		TelephonyManager manager = (TelephonyManager) context
				.getSystemService(Activity.TELEPHONY_SERVICE);
		return manager.getNetworkCountryIso();
	}

	/**
	 * Returns the numeric name (MCC+MNC) of current registered operator.may not
	 * work on CDMA phone
	 * 
	 * @param context
	 * @return
	 */
	public static String getNetWorkOperator(Context context) {
		TelephonyManager manager = (TelephonyManager) context
				.getSystemService(Activity.TELEPHONY_SERVICE);
		return manager.getNetworkOperator();
	}

	/**
	 * Returns the alphabetic name of current registered operator.may not work
	 * on CDMA phone
	 * 
	 * @param context
	 * @return
	 */
	public static String getNetWorkOperatorName(Context context) {
		TelephonyManager manager = (TelephonyManager) context
				.getSystemService(Activity.TELEPHONY_SERVICE);
		return manager.getNetworkOperatorName();
	}

	/**
	 * get type of current network
	 * 
	 * @param context
	 * @return
	 */
	public static int getNetworkType(Context context) {
		TelephonyManager manager = (TelephonyManager) context
				.getSystemService(Activity.TELEPHONY_SERVICE);
		return manager.getNetworkType();
	}

	/**
	 * is webservice aviliable
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isOnline(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Activity.CONNECTIVITY_SERVICE);
		NetworkInfo info = manager.getActiveNetworkInfo();
		if (info != null && info.isConnected()) {
			return true;
		}
		return false;
	}

	/**
	 * get current data connection type name ,like ,Mobile
	 * 
	 * @param context
	 * @return
	 */
	public static String getConnectTypeName(Context context) {
		if (!isOnline(context)) {
			return "OFFLINE";
		}
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Activity.CONNECTIVITY_SERVICE);
		NetworkInfo info = manager.getActiveNetworkInfo();
		if (info != null) {
			return info.getTypeName();
		} else {
			return "OFFLINE";
		}
	}

	/**
	 * get free memory of phone, in M
	 * 
	 * @param context
	 * @return
	 */
	public static long getFreeMem(Context context) {
		ActivityManager manager = (ActivityManager) context
				.getSystemService(Activity.ACTIVITY_SERVICE);
		MemoryInfo info = new MemoryInfo();
		manager.getMemoryInfo(info);
		long free = info.availMem / 1024 / 1024;
		return free;
	}

	/**
	 * get total memory of phone , in M
	 * 
	 * @param context
	 * @return
	 */
	public static long getTotalMem(Context context) {
		try {
			FileReader fr = new FileReader(FILE_MEMORY);
			BufferedReader br = new BufferedReader(fr);
			String text = br.readLine();
			String[] array = text.split("\\s+");
			Log.w(TAG, text);
			return Long.valueOf(array[1]) / 1024;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static String getCpuInfo() {
		try {
			FileReader fr = new FileReader(FILE_CPU);
			BufferedReader br = new BufferedReader(fr);
			String text = br.readLine();
			String[] array = text.split(":\\s+", 2);
			for (int i = 0; i < array.length; i++) {
				Log.w(TAG, " .....  " + array[i]);
			}
			Log.w(TAG, text);
			return array[1];
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * get product name of phone
	 * 
	 * @return
	 */
	public static String getProductName() {
		return Build.PRODUCT;
	}

	/**
	 * get model of phone
	 * 
	 * @return
	 */
	public static String getModelName() {
		return Build.MODEL;
	}

	/**
	 * UUID+设备号序列号 唯一识别码（不可变）
	 * 
	 * @param context
	 * @return
	 */
	public static String getMyUUID(Context context) {

		final TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);

//		final String tmDevice, tmSerial, tmPhone, androidId;

//		tmDevice = "" + tm.getDeviceId();

//		tmSerial = "" + tm.getSimSerialNumber();

		String androidId = ""
				+ android.provider.Settings.Secure.getString(
						context.getContentResolver(),
						android.provider.Settings.Secure.ANDROID_ID);

		/*UUID deviceUuid = new UUID(androidId.hashCode(),
				((long) tmDevice.hashCode() << 32) );

		String uniqueId = deviceUuid.toString();

		if (uniqueId.indexOf("-") > -1) {
			uniqueId = uniqueId.replaceAll("-", "");
		}
		Log.d("debug", "uuid=" + uniqueId);*/

		return androidId;

	}

	/**
	 * get Manufacturer Name of phone
	 * 
	 * @return
	 */
	public static String getManufacturerName() {
		return Build.MANUFACTURER;
	}

	public static PhoneInfoUtil getPhoneInfo(Context context) {
		PhoneInfoUtil result = new PhoneInfoUtil();
		result.mIMEI = getIMEI(context);
		result.mPhoneType = getPhoneType(context);
		result.mSysVersion = getSysVersion();
		result.mNetWorkCountryIso = getNetWorkCountryIso(context);
		result.mNetWorkOperator = getNetWorkOperator(context);
		result.mNetWorkOperatorName = getNetWorkOperatorName(context);
		result.mNetWorkType = getNetworkType(context);
		result.mIsOnLine = isOnline(context);
		result.mConnectTypeName = getConnectTypeName(context);
		result.mFreeMem = getFreeMem(context);
		result.mTotalMem = getTotalMem(context);
		result.mCupInfo = getCpuInfo();
		result.mProductName = getProductName();
		result.mModelName = getModelName();
		result.mManufacturerName = getManufacturerName();
		return result;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IMEI : " + mIMEI + "\n");
		builder.append("mPhoneType : " + mPhoneType + "\n");
		builder.append("mSysVersion : " + mSysVersion + "\n");
		builder.append("mNetWorkCountryIso : " + mNetWorkCountryIso + "\n");
		builder.append("mNetWorkOperator : " + mNetWorkOperator + "\n");
		builder.append("mNetWorkOperatorName : " + mNetWorkOperatorName + "\n");
		builder.append("mNetWorkType : " + mNetWorkType + "\n");
		builder.append("mIsOnLine : " + mIsOnLine + "\n");
		builder.append("mConnectTypeName : " + mConnectTypeName + "\n");
		builder.append("mFreeMem : " + mFreeMem + "M\n");
		builder.append("mTotalMem : " + mTotalMem + "M\n");
		builder.append("mCupInfo : " + mCupInfo + "\n");
		builder.append("mProductName : " + mProductName + "\n");
		builder.append("mModelName : " + mModelName + "\n");
		builder.append("mManufacturerName : " + mManufacturerName + "\n");
		return builder.toString();
	}

}
