package com.aidl.cilent.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;


public class BaseUtil {

	private static Context mContext;

	public final static void init(Context context) {
		mContext = context;
	}

    // 将时间格式转化为 yyyy/mm/dd
    public static String formatDate(){
        String datestr =new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        return  datestr;
    }

	public static boolean between(int min,int max,int x){
		if(x>min&&x<=max)
			return true;
		return false;
	}

	/**
	 * 获取软件版本号
	 *
	 * @param context
	 * @return
	 */
	public static String getVersion(Context context) {
		PackageManager manager = context.getPackageManager();
		try {
			PackageInfo info = manager.getPackageInfo(context.getPackageName(),0);
			return info.versionName;
		} catch (PackageManager.NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "1.0";
		}

	}

	public static void setListViewHeightBasedOnChildren(GridView listView) {
		// 获取listview的adapter
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}
		// 固定列宽，有多少列
		int col = 4;// listView.getNumColumns();
//	        int col=listView.getNumColumns();
		int totalHeight = 0;
		// i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
		// listAdapter.getCount()小于等于8时计算两次高度相加
		for (int i = 0; i < listAdapter.getCount(); i += col) {
			// 获取listview的每一个item
			View listItem =
					listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			// 获取item的高度和
			totalHeight += listItem.getMeasuredHeight();
		}

		// 获取listview的布局参数
		ViewGroup.LayoutParams params = listView.getLayoutParams();

		// 设置高度
		params.height = totalHeight;
		// 设置margin
		//((MarginLayoutParams) params).setMargins(10, 10, 10, 10);
		// 设置参数
		listView.setLayoutParams(params);
	}
//	/**
//	 * 隐藏键盘
//	 *
//	 * @param context
//	 * @param v
//	 */
//	public static void HideSoftKey(Context context, View v) {
//		InputMethodManager imm = (InputMethodManager) v.getContext()
//				.getSystemService(context.INPUT_METHOD_SERVICE);
//		if (imm.isActive()) {
//			imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
//		}
//	}
//	public static String getMethodName(String fildeName) throws Exception {
//		byte[] items = fildeName.getBytes();
//		items[0] = (byte) ((char) items[0] - 'a' + 'A');
//		return new String(items);
//	}
//	/**
//	 * 格式化金额
//	 *
//	 * @param money
//	 *            格式化的金额
//	 * @param len
//	 *            小数点后面的几位
//	 * @return
//	 */
//	public static String formatMoney(String money, int len) {
//		if (money == null || money.length() < 1) {
//			return "";
//		}
//		NumberFormat formater = null;
//		double num = Double.parseDouble(money);
//		if (len == 0) {
//			formater = new DecimalFormat("###,###");
//
//		} else {
//			StringBuffer buff = new StringBuffer();
//			buff.append("###,###.");
//			for (int i = 0; i < len; i++) {
//				buff.append("#");
//			}
//			formater = new DecimalFormat(buff.toString());
//		}
//		String result = formater.format(num);
//		if (result.indexOf(".") == -1) {
//			result = "￥" + result + ".00";
//		} else {
//			result = "￥" + result;
//		}
//		return result;
//	}
//
//	/**
//	 * 格式化金额
//	 *
//	 * @param s
//	 *            格式化的金额
//	 * @param len
//	 *            小数点后面的几位
//	 * @return
//	 */
//	public static String formatMoney(double money, int len) {
//		NumberFormat formater = null;
//		if (len == 0) {
//			formater = new DecimalFormat("###,###");
//
//		} else {
//			StringBuffer buff = new StringBuffer();
//			buff.append("###,###.");
//			for (int i = 0; i < len; i++) {
//				buff.append("#");
//			}
//			formater = new DecimalFormat(buff.toString());
//		}
//		String result = formater.format(money);
//		if (result.indexOf(".") == -1) {
//			result = "￥" + result + ".00";
//		} else {
//			result = "￥" + result;
//		}
//		return result;
//	}
//
//	/**
//	 * 设置请求网络连接的问题
//	 */
//	public static final void resolvedHttpTransportSECall() {
//		try {
//			Integer intSdkVer = Integer.parseInt(android.os.Build.VERSION.SDK);
//			if (intSdkVer > 8) {
//				Class c;
//				c = Class.forName("android.os.StrictMode");
//				Class c2;
//				c2 = Class
//						.forName("android.os.StrictMode$ThreadPolicy$Builder");
//				Method m21 = c2.getMethod("detectDiskReads", null);
//				Method m22 = c2.getMethod("detectDiskWrites", null);
//				Method m23 = c2.getMethod("detectNetwork", null);
//				Method m24 = c2.getMethod("penaltyLog", null);
//				Method m25 = c2.getMethod("build", null);
//
//				Class types1[] = new Class[1];
//				types1[0] = Class.forName("android.os.StrictMode$ThreadPolicy");
//				Method m = c.getMethod("setThreadPolicy", types1);
//				m.invoke(c, m25.invoke(m24.invoke(m23.invoke(
//						m22.invoke(m21.invoke(c2.newInstance(), null), null),
//						null), null), null));
//
//				Class c3;
//				c3 = Class.forName("android.os.StrictMode$VmPolicy$Builder");
//				Method m31 = c3.getMethod("detectLeakedSqlLiteObjects", null);
//				Method m32 = c3.getMethod("detectLeakedSqlLiteObjects", null);
//				Method m33 = c3.getMethod("penaltyLog", null);
//				Method m34 = c3.getMethod("penaltyDeath", null);
//				Method m35 = c3.getMethod("build", null);
//
//				Class types2[] = new Class[1];
//				types2[0] = Class.forName("android.os.StrictMode$VmPolicy");
//				Method m2 = c.getMethod("setVmPolicy", types2);
//				m2.invoke(c, m35.invoke(m34.invoke(m33.invoke(
//						m32.invoke(m31.invoke(c3.newInstance(), null), null),
//						null), null), null));
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	/**
//	 * 把当前时间 减去 通过传入设定的时间得到我们需要和插入时间的数据时间对比。
//	 *
//	 * @param time
//	 *            传入设定时间
//	 * @return
//	 */
//	public static final long getInserTime(long time) {
//		long currentTime = System.currentTimeMillis();
//		long insertTime = currentTime - time;
//		return insertTime;
//	}
//
//	/**
//	 * 拷贝数据库到sdcard目录下
//	 *
//	 * @return
//	 */
//	public static void copyDataBase(String datapath) {
//		datapath = "log.db";
//		// 1,获得数据库路径
//		File dbFile = mContext.getDatabasePath(datapath);
//		// 2,创建保存的数据库的路径
//		File exportDir = new File(Environment.getExternalStorageDirectory(),
//				"copy");
//		if (!exportDir.exists()) {
//			exportDir.mkdirs();
//		}
//		File backup = new File(exportDir, dbFile.getName());
//		// 3,检查操作
//		// 复制文件
//		try {
//			backup.createNewFile();
//			fileCopy(dbFile, backup);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 拷贝文件
//	 *
//	 * @param source
//	 *            源文件
//	 * @param dest
//	 *            目的文件
//	 * @throws IOException
//	 */
//	public static void fileCopy(File source, File dest) throws IOException {
//		FileChannel inChannel = new FileInputStream(source).getChannel();
//		FileChannel outChannel = new FileOutputStream(dest).getChannel();
//		long size = inChannel.size();
//		try {
//			inChannel.transferTo(0, inChannel.size(), outChannel);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			if (inChannel != null) {
//				inChannel.close();
//			}
//			if (outChannel != null) {
//				outChannel.close();
//			}
//		}
//	}
//
//	/**
//	 * 把文件读成流
//	 *
//	 * @param fileName
//	 * @return
//	 */
//	public static final byte[] ReadByteFormFile(String fileName) {
//		// 也可以用String fileName = "mnt/sdcard/Y.txt";
//		String res = "";
//		byte[] buffer = null;
//		try {
//			FileInputStream fin = new FileInputStream(fileName);
//			// FileInputStream fin = openFileInput(fileName);
//			// 用这个就不行了，必须用FileInputStream
//			int length = fin.available();
//			buffer = new byte[length];
//			fin.read(buffer);
//			//res = EncodingUtils.getString(buffer, "UTF-8");
//			fin.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return buffer;
//	}
//
//	/**
//	 * 写在/mnt/sdcard/目录下面的文件
//	 */
//	public static void writeFileSdcard(String mFileName, byte[] bytes) {
//		try {
//			FileOutputStream fout = new FileOutputStream(mFileName, false);
//			fout.write(bytes);
//			fout.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 删除本地文件
//	 */
//	public static void deleteLocalFile(String filepath) {
//		File localFile = new File(filepath);
//		if (localFile.exists()) {
//			localFile.delete();
//		} else {
//			return;
//		}
//	}
//
//	/**
//	 * copy数据库
//	 *
//	 * @throws IOException
//	 */
//	public static final void copyDBByPath(String path) throws IOException {
//
//		// 不同的机型获得的会有所不同，先试试！！
//		String LOCAL_DB_PATH = Environment.getExternalStorageDirectory()
//				.getAbsolutePath() + "/jnbank530.db";
//
//		// String outFileName = DB_PATH + DB_NAME;
//
//		File dataFile = new File(LOCAL_DB_PATH);
//		// if(!dataFile.exists())
//		{
//			InputStream inStream = new FileInputStream(path);
//			OutputStream myOutput = new FileOutputStream(LOCAL_DB_PATH);
//			byte[] buffer = new byte[1024];
//			int length;
//			while ((length = inStream.read(buffer)) > 0) {
//				myOutput.write(buffer, 0, length);
//			}
//			myOutput.flush();
//			myOutput.close();
//			inStream.close();
//		}
//	}
//
//	/**
//	 * 复制单个文件
//	 *
//	 * @param oldPath
//	 *            String 原文件路径 如：c:/fqf.txt
//	 * @param newPath
//	 *            String 复制后路径 如：f:/fqf.txt
//	 * @return boolean
//	 */
//	public void copyFile(String oldPath, String newPath) {
//		try {
//			int bytesum = 0;
//			int byteread = 0;
//			File oldfile = new File(oldPath);
//			if (oldfile.exists()) { // 文件存在时
//				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
//				FileOutputStream fs = new FileOutputStream(newPath);
//				byte[] buffer = new byte[1444];
//				int length;
//				while ((byteread = inStream.read(buffer)) != -1) {
//					bytesum += byteread; // 字节数 文件大小
//					System.out.println(bytesum);
//					fs.write(buffer, 0, byteread);
//				}
//				inStream.close();
//			}
//		} catch (Exception e) {
//			System.out.println("复制单个文件操作出错");
//			e.printStackTrace();
//
//		}
//
//	}
//
////	/**
////	 * 复制单个文件
////	 *
////	 * @param oldPath
////	 *            String 原文件名
////	 * @param newPath
////	 *            String 复制后路径 如：f:/fqf.txt
////	 * @return boolean
////	 */
//	public static void copyAssetFile(String oldName) {
//		try {
//			File newFile = new File(Environment.getExternalStorageDirectory()
//					+ "/" + oldName);
//			if (newFile.exists()) {
//				newFile.delete();
//				// return ;
//			} else {
//				newFile.createNewFile();
//			}
//			int bytesum = 0;
//			int byteread = 0;
//			// File oldfile = new File(oldName);
//			// if (oldfile.exists()) { //文件存在时
//			InputStream inStream = mContext.getResources().getAssets()
//					.open(oldName); // 读入原文件
//			FileOutputStream fs = new FileOutputStream(newFile);
//			byte[] buffer = new byte[1444];
//			int length;
//			while ((byteread = inStream.read(buffer)) != -1) {
//				bytesum += byteread; // 字节数 文件大小
//				// System.out.println(bytesum);
//				fs.write(buffer, 0, byteread);
//			}
//			inStream.close();
//		}
//		// }
//		catch (Exception e) {
//			System.out.println("复制单个文件操作出错");
//			e.printStackTrace();
//
//		}
//	}
//
//	/**
//	 * 判断服务是否停掉
//	 *
//	 * @param className
//	 * @return
//	 */
//	public static boolean isServiceRunning(String className) {
//		boolean isRunning = false;
//		ActivityManager activityManager = (ActivityManager) mContext
//				.getSystemService(mContext.ACTIVITY_SERVICE);
//		List<ActivityManager.RunningServiceInfo> serviceList = activityManager
//				.getRunningServices(Integer.MAX_VALUE);
//		if (!(serviceList.size() > 0)) {
//			return false;
//		}
//		for (int i = 0; i < serviceList.size(); i++) {
//			if (serviceList.get(i).service.getClassName().equals(className) == true) {
//				isRunning = true;
//				break;
//			}
//		}
//		Log.i("aaa", "service is running?==" + isRunning);
//		return isRunning;
//
//	}
//
//	/**
//	 * wifi的操作
//	 *
//	 * @param isDo
//	 *            false 为关闭wifi true为打开wifi
//	 */
//	public static void closeWifi(boolean isDo) {
//		WifiManager wifiManager = (WifiManager) mContext
//				.getSystemService(Context.WIFI_SERVICE);
//		wifiManager.setWifiEnabled(isDo);
//	}
//
//	/**
//	 * 删除新下载的apk文件
//	 */
//	public static void deleteDownNewApk(String filepath) {
//		File mFile = new File(Environment.getExternalStorageDirectory(),
//				filepath);
//		if (mFile.exists()) {
//			System.out.println("----新下载的apk已删除-----");
//			mFile.delete();
//		}
//
//	}
//
//	/**
//	 * 获取sdcard根目录
//	 *
//	 * @return
//	 */
//	public static final String getSDPath() {
//		File sdDir = null;
//		boolean sdCardExist = Environment.getExternalStorageState().equals(
//				Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
//		if (sdCardExist) {
//			sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
//		}
//		return sdDir.toString();
//
//	}
//
//	/**
//	 * 线程更新UI 通过异步线程
//	 */
//	public static final void UpDataUIByThread(final Handler mHandler,
//			final int type, final long time) {
//
//		Thread mThread = new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				try {
//					Thread.sleep(time);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} finally {
//					/*
//					 * Message message = new Message(); message.what = type;
//					 * mHandler.sendMessage(message);
//					 */
//					mHandler.sendEmptyMessage(type);
//				}
//			}
//		});
//		mThread.start();
//	}
//
//	/**
//	 * 计算两个天数时间的时间差
//	 *
//	 * @param startday
//	 * @param endDay
//	 * @return
//	 */
//	public static int getDaySizeFormTwoDay(String startday, String endDay) {
//
//		/*
//		 * String str1 = "20090717"; //"yyyyMMdd"格式 如 20131022
//		 * System.out.println("\n结束时间:"); String str2 = "20140717";
//		 * //"yyyyMMdd"格式 如 20131022
//		 */SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");// 输入日期的格式
//		Date date1 = null;
//		try {
//			date1 = simpleDateFormat.parse(startday);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		Date date2 = null;
//		try {
//			date2 = simpleDateFormat.parse(endDay);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		GregorianCalendar cal1 = new GregorianCalendar();
//		GregorianCalendar cal2 = new GregorianCalendar();
//		cal1.setTime(date1);
//		cal2.setTime(date2);
//		int dayCount = (int) ((cal2.getTimeInMillis() - cal1.getTimeInMillis()) / (1000 * 3600 * 24));
//		return dayCount;
//	}
//
//	/**
//	 * 判断是否属于联通3G网络
//	 *
//	 * @return
//	 */
//	public static boolean isLianTong3G() {
//		// 先关闭WIFI
//		/*
//		 * BaseUtil.closeWifi(true); toggleMobileData(mContext,true);
//		 * ConnectivityManager connectMgr = (ConnectivityManager) mContext
//		 * .getSystemService(Context.CONNECTIVITY_SERVICE); NetworkInfo info =
//		 * connectMgr.getActiveNetworkInfo(); if (info != null && info.getType()
//		 * == ConnectivityManager.TYPE_MOBILE) { if (info.getSubtype() ==
//		 * TelephonyManager.NETWORK_TYPE_UMTS || info.getSubtype() ==
//		 * TelephonyManager.NETWORK_TYPE_HSDPA) {
//		 * System.out.println("-------name-----" + info.getSubtypeName());
//		 * return true; } }
//		 */
//		return true;
//	}
//
//	/**
//	 * 移动网络开关
//	 */
//	public static void toggleMobileData(Context context, boolean enabled) {
//		ConnectivityManager conMgr = (ConnectivityManager) context
//				.getSystemService(Context.CONNECTIVITY_SERVICE);
//		Class<?> conMgrClass = null; // ConnectivityManager类
//		Field iConMgrField = null; // ConnectivityManager类中的字段
//		Object iConMgr = null; // IConnectivityManager类的引用
//		Class<?> iConMgrClass = null; // IConnectivityManager类
//		Method setMobileDataEnabledMethod = null; // setMobileDataEnabled方法
//		try {
//			// 取得ConnectivityManager类
//			conMgrClass = Class.forName(conMgr.getClass().getName());
//			// 取得ConnectivityManager类中的对象mService
//			iConMgrField = conMgrClass.getDeclaredField("mService");
//			// 设置mService可访问
//			iConMgrField.setAccessible(true);
//			// 取得mService的实例化类IConnectivityManager
//			iConMgr = iConMgrField.get(conMgr);
//			// 取得IConnectivityManager类
//			iConMgrClass = Class.forName(iConMgr.getClass().getName());
//			// 取得IConnectivityManager类中的setMobileDataEnabled(boolean)方法
//			setMobileDataEnabledMethod = iConMgrClass.getDeclaredMethod(
//					"setMobileDataEnabled", Boolean.TYPE);
//			// 设置setMobileDataEnabled方法可访问
//			setMobileDataEnabledMethod.setAccessible(true);
//			// 调用setMobileDataEnabled方法
//			setMobileDataEnabledMethod.invoke(iConMgr, enabled);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (NoSuchFieldException e) {
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			e.printStackTrace();
//		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 把元格式化成万元为单位
//	 *
//	 * @return
//	 */
//	public static final String formatMoneyForMillion(String yuanMoney) {
//		if (yuanMoney == null || "".equals(yuanMoney) || "0".equals(yuanMoney)) {
//			return "0";
//		}
//		BigDecimal millone = null;
//		try {
//			millone = new BigDecimal(yuanMoney).divide(new BigDecimal(1), 0,
//					RoundingMode.HALF_UP);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return "0";
//		}
//		return millone.toPlainString();
//
//	}
//
//	public static final String[] ListToString(List<String> listArr) {
//		if (listArr == null || listArr.size() == 0) {
//			return null;
//		}
//		String[] toBeStored = listArr.toArray(new String[listArr.size()]);
//		/*
//		 * for(String s : toBeStored) { System.out.println(s); }
//		 */
//		return toBeStored;
//
//	}
//
//	public static final List<String> StringToList(String[] strs) {
//		if (strs == null || strs.length == 0) {
//			return null;
//		}
//		List<String> arrs = Arrays.asList(strs);
//		return arrs;
//
//	}
//
//	/**
//	 * 强制帮用户打开GPS
//	 *
//	 * @param context
//	 */
//	public static final void openGPS(Context context) {
//		Intent GPSIntent = new Intent();
//		GPSIntent.setClassName("com.android.settings",
//				"com.android.settings.widget.SettingsAppWidgetProvider");
//		GPSIntent.addCategory("android.intent.category.ALTERNATIVE");
//		GPSIntent.setData(Uri.parse("custom:3"));
//		try {
//			PendingIntent.getBroadcast(context, 0, GPSIntent, 0).send();
//		} catch (CanceledException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 *
//	 * @ryan
//	 * @date 2015年1月16日 下午1:26:03
//	 * @Title: openFile
//	 * @Description: 查看各种文件
//	 * @param file
//	 * @return void
//	 * @throws
//	 */
//	public static final void openFile(File file) {
//
//		try {
//			Intent intent = new Intent();
//			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			// 设置intent的Action属性
//			intent.setAction(Intent.ACTION_VIEW);
//			// 获取文件file的MIME类型
//			String type = getMIMEType(file);
//			// 设置intent的data和Type属性。
//			intent.setDataAndType(/* uri */Uri.fromFile(file), type);
//			// 跳转
//			mContext.startActivity(intent);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//	/**
//	 * 根据文件后缀名获得对应的MIME类型。
//	 *
//	 * @param file
//	 */
//	public static String getMIMEType(File file) {
//
//		String type = "*/*";
//		String fName = file.getName();
//		// 获取后缀名前的分隔符"."在fName中的位置。
//		int dotIndex = fName.lastIndexOf(".");
//		if (dotIndex < 0) {
//			return type;
//		}
//		/* 获取文件的后缀名 */
//		String end = fName.substring(dotIndex, fName.length()).toLowerCase();
//		if (end == "")
//			return type;
//		// 在MIME和文件类型的匹配表中找到对应的MIME类型。
//		for (int i = 0; i < MIME_MapTable.length; i++) { // MIME_MapTable??在这里你一定有疑问，这个MIME_MapTable是什么？
//			if (end.equals(MIME_MapTable[i][0]))
//				type = MIME_MapTable[i][1];
//		}
//		return type;
//	}
//
//	public static final String[][] MIME_MapTable = {
//			// {后缀名，MIME类型}
//			{ ".3gp", "video/3gpp" },
//			{ ".apk", "application/vnd.android.package-archive" },
//			{ ".asf", "video/x-ms-asf" },
//			{ ".avi", "video/x-msvideo" },
//			{ ".bin", "application/octet-stream" },
//			{ ".bmp", "image/bmp" },
//			{ ".c", "text/plain" },
//			{ ".class", "application/octet-stream" },
//			{ ".conf", "text/plain" },
//			{ ".cpp", "text/plain" },
//			{ ".doc", "application/msword" },
//			{ ".docx",
//					"application/vnd.openxmlformats-officedocument.wordprocessingml.document" },
//			{ ".xls", "application/vnd.ms-excel" },
//			{ ".xlsx",
//					"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" },
//			{ ".exe", "application/octet-stream" },
//			{ ".gif", "image/gif" },
//			{ ".gtar", "application/x-gtar" },
//			{ ".gz", "application/x-gzip" },
//			{ ".h", "text/plain" },
//			{ ".htm", "text/html" },
//			{ ".html", "text/html" },
//			{ ".jar", "application/java-archive" },
//			{ ".java", "text/plain" },
//			{ ".jpeg", "image/jpeg" },
//			{ ".jpg", "image/jpeg" },
//			{ ".js", "application/x-javascript" },
//			{ ".log", "text/plain" },
//			{ ".m3u", "audio/x-mpegurl" },
//			{ ".m4a", "audio/mp4a-latm" },
//			{ ".m4b", "audio/mp4a-latm" },
//			{ ".m4p", "audio/mp4a-latm" },
//			{ ".m4u", "video/vnd.mpegurl" },
//			{ ".m4v", "video/x-m4v" },
//			{ ".mov", "video/quicktime" },
//			{ ".mp2", "audio/x-mpeg" },
//			{ ".mp3", "audio/x-mpeg" },
//			{ ".mp4", "video/mp4" },
//			{ ".mpc", "application/vnd.mpohun.certificate" },
//			{ ".mpe", "video/mpeg" },
//			{ ".mpeg", "video/mpeg" },
//			{ ".mpg", "video/mpeg" },
//			{ ".mpg4", "video/mp4" },
//			{ ".mpga", "audio/mpeg" },
//			{ ".msg", "application/vnd.ms-outlook" },
//			{ ".ogg", "audio/ogg" },
//			{ ".pdf", "application/pdf" },
//			{ ".png", "image/png" },
//			{ ".pps", "application/vnd.ms-powerpoint" },
//			{ ".ppt", "application/vnd.ms-powerpoint" },
//			{ ".pptx",
//					"application/vnd.openxmlformats-officedocument.presentationml.presentation" },
//			{ ".prop", "text/plain" }, { ".rc", "text/plain" },
//			{ ".rmvb", "audio/x-pn-realaudio" }, { ".rtf", "application/rtf" },
//			{ ".sh", "text/plain" }, { ".tar", "application/x-tar" },
//			{ ".tgz", "application/x-compressed" }, { ".txt", "text/plain" },
//			{ ".wav", "audio/x-wav" }, { ".wma", "audio/x-ms-wma" },
//			{ ".wmv", "audio/x-ms-wmv" },
//			{ ".wps", "application/vnd.ms-works" }, { ".xml", "text/plain" },
//			{ ".z", "application/x-compress" },
//			{ ".zip", "application/x-zip-compressed" }, { "", "*/*" } };

	/**
	 * 生成序列号的随机数
	 * 
	 * @return
	 */
	public static final String RamdomSerNO() {
		int numcode = (int) ((Math.random() * 9 + 1) * 100000);
		String date = DateUtil.detaledLshFormat(new Date());
		String seqNo = date + numcode;
		return seqNo;
	}

	/**
	 * 将json 数组转换为Map 对象
	 * 
	 * @param jsonString
	 * @return
	 */

	public static Map<String, Object> JsonStrToMap(String jsonString) {
		JSONObject jsonObject;
		Map<String, Object> valueMap = new HashMap<String, Object>();
		try {
			jsonObject = new JSONObject(jsonString);
			@SuppressWarnings("unchecked")
			Iterator<String> keyIter = jsonObject.keys();
			String key;
			Object value;

			while (keyIter.hasNext()) {
				key = (String) keyIter.next();
				value = jsonObject.get(key);
				valueMap.put(key, value);
			}
			return valueMap;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return valueMap;
	}

	/**
	 * 将json 数组转换为Map<String,String> 对象
	 * 
	 * @param jsonString
	 * @return
	 */

	public static Map<String, String> JsonStrToMap1(String jsonString) {
		JSONObject jsonObject;
		Map<String, String> valueMap = new HashMap<String, String>();
		try {
			jsonObject = new JSONObject(jsonString);
			@SuppressWarnings("unchecked")
			Iterator<String> keyIter = jsonObject.keys();
			String key;
			String value;

			while (keyIter.hasNext()) {
				key = (String) keyIter.next();
				value = (String) jsonObject.get(key);
				valueMap.put(key, value);
			}
			return valueMap;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return valueMap;
	}

	
	public static String MapToJsonStr(Map<String, Object> map) {
		if (map == null || map.isEmpty()) {
			return "null";
		}
		JSONObject mJsonObject = new JSONObject(map);
	  String jsonStr =	mJsonObject.toString();
		/*String jsonStr = "{";
		Set<?> keySet = map.keySet();
		for (Object key : keySet) {
			jsonStr += "\"" + key + "\":\"" + map.get(key) + "\",";
		}
		jsonStr = jsonStr.substring(0, jsonStr.length() - 2);
		jsonStr += "}";*/
		return jsonStr;
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

		final String tmDevice, tmSerial, tmPhone, androidId;
		tmDevice = "" + tm.getDeviceId();
		tmSerial = "" + tm.getSimSerialNumber();
		androidId = ""
				+ android.provider.Settings.Secure.getString(
						context.getContentResolver(),
						android.provider.Settings.Secure.ANDROID_ID);

		UUID deviceUuid = new UUID(androidId.hashCode(),
				((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
		String uniqueId = deviceUuid.toString();

		return uniqueId;

	}

//	/*
//	 * 保存蓝牙地址
//	 */
//	public static final void saveAddress(String address) {
//		SharedPreferences sharedPreferences = mContext.getSharedPreferences(
//				"bind_mac_address", Context.MODE_PRIVATE);
//		Editor editor = sharedPreferences.edit();// 获取编辑器
//		editor.putString("address", address);
//		editor.commit();// 提交修改
//	}
//
	/**
	 * 得到已绑定的蓝牙地址
	 *
	 * @return
	 */
	@SuppressLint("NewApi")
	public static final String getAdress() {

		Uri uri=Uri.parse("content://com.wincom.android.provider.HBbankloudProvider/getData");
		Bundle bundle=  mContext.getContentResolver().call(uri, "getBangdingAddress", "", new Bundle());

		String address = bundle.getString("macAddress");
		/*SharedPreferences settings = mContext.getSharedPreferences(
				"bind_mac_address", Context.MODE_PRIVATE);
		String address = settings.getString("address", "");*/
		return address;
	}
//
//	public static final String SAVADATAFORTRADING = "savadata_trading";
//
//	/**
//	 * 获取一个人的身份证号
//	 *
//	 * @return
//	 */
//	@SuppressLint("NewApi")
//	public static final String getLastPersonID(Context mContext) {
//		Uri uri=Uri.parse("content://com.wincom.android.provider.HBbankloudProvider/getData");
//		Bundle bundle=  mContext.getContentResolver().call(uri, "getPersonID", "", new Bundle());
//		if (bundle.get("ID") ==  null || "".equals(bundle.get("ID")))  {
//			return "";
//		}
//		String id = bundle.get("ID").toString();
//	/*	Context other= null;
//		try {
//			 other = mContext.createPackageContext("com.HB_BankLoud", Context.CONTEXT_IGNORE_SECURITY);
//		} catch (NameNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if (other == null) {
//
//			return "";
//		}
//		SharedPreferences tradePreferences = other.getSharedPreferences(
//				SAVADATAFORTRADING,  Context.MODE_MULTI_PROCESS);
//		String id = tradePreferences.getString("ID", "");*/
//		return id;
//	}
//
////	/**
////	 *
////	 * @param mContext
////	 * @param falg
////	 *            1:不取上次的文件ID 0： 取上次的图片id
////	 */
////	public static final void savaDataFlag(Context mContext, String falg) {
////		Context other= null;
////		try {
////			 other = mContext.createPackageContext("com.HB_BankLoud", Context.CONTEXT_IGNORE_SECURITY);
////		} catch (NameNotFoundException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		SharedPreferences tradePreferences = other.getSharedPreferences(
////				SAVADATAFORTRADING, Context.MODE_MULTI_PROCESS);
////		Editor mEditor = tradePreferences.edit();
////		mEditor.putString("Flag", falg);
////		mEditor.commit();
////	}
////
////	/**
////	 * 获取Flag 判断联网核查是否与上次一致 0是一致，1是不一致
////	 *
////	 * @param mContext
////	 * @return
////	 */
////	@SuppressLint("NewApi")
////	public static final String getDataFlag(Context mContext) {
////		Uri uri=Uri.parse("content://com.wincom.android.provider.HBbankloudProvider/getData");
////		Bundle bundle=  mContext.getContentResolver().call(uri, "getPersonID", "", new Bundle());
////		if (bundle.get("ID") ==  null || "".equals(bundle.get("ID")))  {
////			return "1";
////		}else{
////			return "0";
////		}
//////		String id = bundle.get("ID").toString();
//////		SharedPreferences tradePreferences = mContext.getSharedPreferences(
//////				SAVADATAFORTRADING, Context.MODE_MULTI_PROCESS);
//////		Editor mEditor = tradePreferences.edit();
//////		String flag = tradePreferences.getString("Flag", "1");
////	}
////
////	/**
////	 * 获取一个人的信息
////	 *
////	 * @return
////	 */
////	@SuppressLint("NewApi")
////	public static final Map<String, Object> getLastPersonMessage(
////			Context mContext) {
////		Uri uri=Uri.parse("content://com.wincom.android.provider.HBbankloudProvider/getData");
////		Bundle bundle=  mContext.getContentResolver().call(uri, "getPersonInfo", "", new Bundle());
////		Map<String, Object> resultMap = new HashMap<String, Object>();
////		resultMap.put("ID", bundle.get("ID").toString());
////		resultMap.put("idcard_01", bundle.get("idcard_01").toString());
////		resultMap.put("idcard_02", bundle.get("idcard_02").toString());
////		resultMap.put("idcard_03", bundle.get("idcard_03").toString());
////		resultMap.put("idcard_04", bundle.get("idcard_04").toString());
////		resultMap.put("idcard_05", bundle.get("idcard_05").toString());
////		resultMap.put("idcard_06", bundle.get("idcard_06").toString());
////
////
/////*		Map<String, Object> resultMap = new HashMap<String, Object>();
////		Context other= null;
////		try {
////			 other = mContext.createPackageContext("com.HB_BankLoud", Context.CONTEXT_IGNORE_SECURITY);
////		} catch (NameNotFoundException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////			return resultMap;
////		}
////		SharedPreferences tradePreferences = other.getSharedPreferences(
////				SAVADATAFORTRADING, Context.MODE_MULTI_PROCESS);
////		String id = tradePreferences.getString("ID", "");
//////		resultMap.put("ID", tradePreferences.getString("ID", ""));
//////		resultMap.put("checkResultName",
//////				tradePreferences.getString("checkResultName", ""));
//////		resultMap.put("IssueOffice",
//////				tradePreferences.getString("IssueOffice", ""));
//////		resultMap.put("photo", tradePreferences.getString("photo", ""));
////		resultMap.put("ID", id);
////		resultMap.put("idcard_01", tradePreferences.getString("idcard_01", ""));
////		resultMap.put("idcard_02", tradePreferences.getString("idcard_02", ""));
////		resultMap.put("idcard_03", tradePreferences.getString("idcard_03", ""));
////		resultMap.put("idcard_04", tradePreferences.getString("idcard_04", ""));
////		resultMap.put("idcard_05", tradePreferences.getString("idcard_05", ""));
////		resultMap.put("idcard_06", tradePreferences.getString("idcard_06", ""));*/
////		return resultMap;
////	}
//
//	/*
//	 * public static Object jsonStrToObject(String jsonStr ){ OutCheckDevice
//	 * mOut = new OutCheckDevice(); try { mOut =
//	 * gson.fromJson(strOutcheckDevice, new TypeToken<OutCheckDevice>() {
//	 * }.getType()); } catch (JsonSyntaxException e) { e.printStackTrace(); mOut
//	 * = new OutCheckDevice(); mOut.setResultStatus(-1);
//	 * mOut.setResultMsg(Constants.WEB_STATUS_NG); } }
//	 */
//
////	public static void main(String[] args) {
////		// String obj =
////		// "[{\"address\":\"20:9:41:26:14:04:26\",\"name\":\"HC-06\"},{\"address\":\"EC:55:F9:DA:7F:5F\",\"name\":\"UF001633\"},{\"address\":\"9C:2A:70:82:2B:AF\",\"name\":\"51IBM-PC\"}]"
////	}
////	/**
////	 * 获取软件版本号
////	 *
////	 * @param context
////	 * @return
////	 */
////	public static String getVersion(Context context) {
////		PackageManager manager = context.getPackageManager();
////		try {
////			PackageInfo info = manager.getPackageInfo(context.getPackageName(),
////					0);
////			return info.versionName;
////		} catch (NameNotFoundException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////			return "1.0";
////		}
////
////	}
////
////	public static boolean StrIsUsefal(String str){
////		if(str==null||"".equals(str)){
////			return false;
////		}
////		return true;
////	}
////
////	 public static void work(Map<String, Object> map) {
////	        Collection<Object> c = map.values();
////	        Iterator it = c.iterator();
////	        for (; it.hasNext();) {
////	            System.out.println(it.next());
////	        }
////	    }
////
////	 public static void workBykeySet(Map<String, Object> map) {
////	        Set<String> key = map.keySet();
////	        for (Iterator it = key.iterator(); it.hasNext();) {
////	            String s = (String) it.next();
////	            System.out.println(map.get(s));
////	        }
////	    }
////
////	 public static void workByEntry(Map<String, Object> map) {
////	        Set<Map.Entry<String, Object>> set = map.entrySet();
////	        for (Iterator<Map.Entry<String, Object>> it = set.iterator(); it.hasNext();) {
////	            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
////	            System.out.println(entry.getKey() + "--->" + entry.getValue());
////	        }
////	    }
	
}
