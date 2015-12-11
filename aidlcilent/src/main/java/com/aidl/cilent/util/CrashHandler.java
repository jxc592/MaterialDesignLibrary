package com.aidl.cilent.util;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TreeSet;

public class CrashHandler  implements UncaughtExceptionHandler{

	/** Debug Log Tag */  
    public static final String TAG = "CrashHandler";  
    /** 是否开启日志输出, 在Debug状态下开启, 在Release状态下关闭以提升程序性能 */  
    public static final boolean DEBUG = true;  
    /** CrashHandler实例 */  
    private static CrashHandler INSTANCE;  
    /** 程序的Context对象 */  
    private Context mContext;  
    /** 系统默认的UncaughtException处理类 */  
    private UncaughtExceptionHandler mDefaultHandler;
      
    /** 使用Properties来保存设备的信息和错误堆栈信息 */  
    private Properties mDeviceCrashInfo = new Properties();  
    private static final String VERSION_NAME = "versionName";  
    private static final String VERSION_CODE = "versionCode";  
    private static final String STACK_TRACE = "STACK_TRACE";  
    /** 错误报告文件的扩展名 */  
    private static final String CRASH_REPORTER_EXTENSION = ".cr";  
      
    /** 保证只有一个CrashHandler实例 */  
    private CrashHandler() {  
    }  
  
    /** 获取CrashHandler实例 ,单例模式 */  
    public static CrashHandler getInstance() {  
        if (INSTANCE == null)  
            INSTANCE = new CrashHandler();  
        return INSTANCE;  
    }  
      
    /** 
     * 初始化,注册Context对象, 获取系统默认的UncaughtException处理器, 设置该CrashHandler为程序的默认处理器 
     *  
     * @param ctx 
     */  
    public void init(Context ctx) {  
        mContext = ctx;  
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();  
        Thread.setDefaultUncaughtExceptionHandler(this);  
    }  
      
    /** 
     * 当UncaughtException发生时会转入该函数来处理 
     */  
    @Override  
    public void uncaughtException(Thread thread, final Throwable ex) {
    	if (ex != null) {
    		   StackTraceElement[] stackTrace = ex.getStackTrace();  
    			MyLog.addMyLog("程序出异常： 原因：" + ex.toString() +"\n");
    		   for (int i = 0; i < stackTrace.length; i++) {  
    		      	if (i == 0) {
               	     MyLog.addMyLog("-----本次错误开始------" + ex.toString() + "\n" + "***********此次错误打印开始****************\n" + "  出错时间点 :   " + DateUtil.detaledFormat(new Date()) +
                 		       "  file(文件):   " + stackTrace[i].getFileName() +"\n" +
					               " 出错class(类名):  " + stackTrace[i].getClassName()  
                                + "\n"+" 出错method(方法):   " + stackTrace[i].getMethodName() +"\n"+ 
                 		       " 出错行:   " + stackTrace[i].getLineNumber()   + "\n" );
					}
    		      	else if (i == stackTrace.length -1 ) {
                   MyLog.addMyLog( "本次错误结束：" +
               		       "  file(文件):   " + stackTrace[i].getFileName() +"\n" +
					               " 出错class(类名):  " + stackTrace[i].getClassName()  
                              + "\n"+" 出错method(方法):   " + stackTrace[i].getMethodName() +"\n"+ 
               		       " 出错行:   " + stackTrace[i].getLineNumber()   + "\n" );
    		   }else{
    			      MyLog.addMyLog(
                  		       "  file(文件):   " + stackTrace[i].getFileName() +"\n" +
   					               " 出错class(类名):  " + stackTrace[i].getClassName()  
                                 + "\n"+" 出错method(方法):   " + stackTrace[i].getMethodName() +"\n"+ 
                  		       " 出错行:   " + stackTrace[i].getLineNumber()   + "\n" );
    		     }
    	
    		}
    		   Thread mThread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Looper.prepare();
					ToastCoustom.show("程序出现异常，3秒后关闭程序.");
					Looper.loop();
				}
			});
    		   mThread.start();
    			
    	     
		}
    	
    	try {
			Thread.sleep(3000);
			   android.os.Process.killProcess(android.os.Process.myPid());  
    	        System.exit(10); 
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//    	Thread mThread = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				Looper.prepare(); 
//				closeDialog("测试" ,ex);
////			  	 new AlertDialog.Builder(mContext).setTitle("提示").setCancelable(false)  
////                 .setMessage("程序崩溃了...").setNeutralButton("我知道了", new OnClickListener() {  
////                     public void onClick(DialogInterface dialog, int which) {  
////                         System.exit(0);  
////                     }  
////                 })  
////                  .create().show();  
//			  	Looper.loop();
//			}
//		});
//    	mThread.start();
//        if (!handleException(ex) && mDefaultHandler != null) {  
//            // 如果用户没有处理则让系统默认的异常处理器来处理  
//            mDefaultHandler.uncaughtException(thread, ex);  
//        } else {  
        	
        	/*Thread mThread1 = new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
				}
			});
        	mThread.start();*/
        	 
            // Sleep一会后结束程序  
            // 来让线程停止一会是为了显示Toast信息给用户，然后Kill程序  
//            try {  
//          
//
//                Thread.sleep(3000);  
//            } catch (InterruptedException e) {  
//                Log.e(TAG, "Error : ", e);  
//            }  
//        }  
     
      }  
    /** 
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成. 开发者可以根据自己的情况来自定义异常处理逻辑 
     *  
     * @param ex 
     * @return true:如果处理了该异常信息;否则返回false 
     */  
    private boolean handleException(final Throwable ex) {  
   /*     if (ex == null) {  
            return true;  
        }  
        final String msg = ex.getLocalizedMessage();  
        System.out.println("--------CrashHandler-----------错误信息-----" + msg);
        // 使用Toast来显示异常信息  
        new Thread() {  
            @Override  
            public void run() {  
            	closeDialog("测试" ,ex);
                // Toast 显示需要出现在一个线程的消息队列中  
//            	if (BaseUtil.isServiceRunning("com.sumecom.smms.android.activity.personaccount.MyFloatViewService")) {
//            		  Intent intent=new Intent(mContext,MyFloatViewService.class);
//              		  mContext.stopService(intent);
//        		}
//        		
//        		if (BaseUtil.isServiceRunning("com.sumecom.smms.android.service.RemoteVideoService")) {
//        			Intent intenService = new Intent(mContext,
//        					RemoteVideoService.class);
//        			mContext.stopService(intenService);
//        		}
        		
//        		Looper.prepare();  
//        		Toast.makeText(mContext, "程序出异常了，请关机重新启动.." + msg, Toast.LENGTH_LONG).show();  
//        		Looper.loop();  
                System.out.println("--------CrashHandler-----------错误信息-----" + msg);
            }  
        }.start();  
        //手机错误信息和保存异常信息
        collectDeviceInfo(mContext);
        saveCrashInfo2File(ex);
//        // 收集设备信息  
//        collectCrashDeviceInfo(mContext);  
//        // 保存错误报告文件  
//        String crashFileName = saveCrashInfoToFile(ex);  
        // 发送错误报告到服务器  
//        sendCrashReportsToServer(mContext);  
*/        return true;  
    }  

   
    
    /** 
     * 收集程序崩溃的设备信息 
     *  
     * @param ctx 
     */  
    public void collectCrashDeviceInfo(Context ctx) {  
        try {  
            // Class for retrieving various kinds of information related to the  
            // application packages that are currently installed on the device.  
            // You can find this class through getPackageManager().  
            PackageManager pm = ctx.getPackageManager();  
            // getPackageInfo(String packageName, int flags)  
            // Retrieve overall information about an application package that is installed on the system.  
            // public static final int GET_ACTIVITIES  
            // Since: API Level 1 PackageInfo flag: return information about activities in the package in activities.  
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);  
            if (pi != null) {  
                // public String versionName The version name of this package,  
                // as specified by the <manifest> tag's versionName attribute.  
                mDeviceCrashInfo.put(VERSION_NAME, pi.versionName == null ? "not set" : pi.versionName);  
                // public int versionCode The version number of this package,   
                // as specified by the <manifest> tag's versionCode attribute.  
                mDeviceCrashInfo.put(VERSION_CODE, pi.versionCode);  
            }  
        } catch (NameNotFoundException e) {  
            Log.e(TAG, "Error while collect package info", e);  
        }  
        // 使用反射来收集设备信息.在Build类中包含各种设备信息,  
        // 例如: 系统版本号,设备生产商 等帮助调试程序的有用信息  
        // 返回 Field 对象的一个数组，这些对象反映此 Class 对象所表示的类或接口所声明的所有字段  
        Field[] fields = Build.class.getDeclaredFields();  
        for (Field field : fields) {  
            try {  
                // setAccessible(boolean flag)  
                // 将此对象的 accessible 标志设置为指示的布尔值。  
                // 通过设置Accessible属性为true,才能对私有变量进行访问，不然会得到一个IllegalAccessException的异常  
                field.setAccessible(true);  
                mDeviceCrashInfo.put(field.getName(), field.get(null));  
                if (DEBUG) {  
                    Log.d(TAG, field.getName() + " : " + field.get(null));  
                }  
            } catch (Exception e) {  
                Log.e(TAG, "Error while collect crash info", e);  
            }  
        }  
    }  
      
    /** 
     * 保存错误信息到文件中 
     *  
     * @param ex 
     * @return 
     */  
    private String saveCrashInfoToFile(Throwable ex) {    
    	String logdir ;  
        if(Environment.getExternalStorageDirectory()!=null){  
            logdir = Environment.getExternalStorageDirectory().getAbsolutePath()  
                    + File.separator + "dsms运行错误日志"+File.separator+"log" ;  
              
              System.out.println("-----------保存logcat的路径--------------" + logdir);
            File file = new File(logdir);  
            boolean mkSuccess;  
            if (!file.isDirectory()) {  
                mkSuccess = file.mkdirs();  
                if (!mkSuccess) {  
                    mkSuccess = file.mkdirs();  
                }  
            }  
            try {  
                FileWriter fw=null;
//			           生成具体错误文件的路径
				fw = new FileWriter(logdir+File.separator+"error"+new SimpleDateFormat("yyyyMMdd").format(new Date(  Calendar.getInstance().getTimeInMillis()))+".txt",true);
                fw.write(new Date()+"\n");  
                fw.write( "***************************\n");
                fw.write(ex.getStackTrace() + "\n");
                fw.write( "***************************\n");
                StackTraceElement[] stackTrace = ex.getStackTrace();  
                fw.write(ex.getMessage() + "\n" +"--getCause----"+ ex.getCause() + "\n" + "--getLocalizedMessage----"+ ex.getLocalizedMessage() + "\n");  
                for (int i = 0; i < stackTrace.length; i++) {  
                	if (i == 0) {
                	     MyLog.addMyLog("-----本次错误开始------" + ex.toString() + "\n" + "***********此次错误打印开始****************\n" + "  出错时间点 :   " + DateUtil.detaledFormat(new Date()) +
                  		       "  file(文件):   " + stackTrace[i].getFileName() +"\n" +
					               " 出错class(类名):  " + stackTrace[i].getClassName()  
                                 + "\n"+" 出错method(方法):   " + stackTrace[i].getMethodName() +"\n"+ 
                  		       " 出错行:   " + stackTrace[i].getLineNumber()   + "\n" );
                	     fw.write( "***********此次错误打印开始****************\n" + "  出错时间点 :   " + DateUtil.detaledFormat(new Date()));
					}
                    fw.write( "  file(文件):   " + stackTrace[i].getFileName() +"\n"+ " 出错class(类名):  " + stackTrace[i].getClassName()  
                            + "\n"+" 出错method(方法):   " + stackTrace[i].getMethodName() +"\n"+ 
                    		" 出错行:   " + stackTrace[i].getLineNumber()   + "\n"
                            );  
                    if (i == stackTrace.length -1 ) {
                    	fw.write( "***********此次错误打印结束****************\n" + "  出错时间点 :   " + DateUtil.detaledFormat(new Date()));
					}
                    MyLog.addMyLog( 
                		       "  file(文件):   " + stackTrace[i].getFileName() +"\n" +
					               " 出错class(类名):  " + stackTrace[i].getClassName()  
                               + "\n"+" 出错method(方法):   " + stackTrace[i].getMethodName() +"\n"+ 
                		       " 出错行:   " + stackTrace[i].getLineNumber()   + "\n" );
               
                    if (i == stackTrace.length-1 ) {
                    	MyLog.addMyLog( "***********此次错误打印结束****************\n" + "  出错时间点 :   " + DateUtil.detaledFormat(new Date()) +
                   		       "  file(文件):   " + stackTrace[i].getFileName() +"\n" +
 					               " 出错class(类名):  " + stackTrace[i].getClassName()  
                                  + "\n"+" 出错method(方法):   " + stackTrace[i].getMethodName() +"\n"+ 
                   		       " 出错行:   " + stackTrace[i].getLineNumber()   + "\n" );
					}
                    
                }  
                fw.write("\n");  
                fw.close();  
            } catch (IOException e) {  
                Log.e("crash handler", "load file failed...", e.getCause());    
            }  
        }  
        ex.printStackTrace();  
		return null;   
    }  
      
    /** 
     * 把错误报告发送给服务器,包含新产生的和以前没发送的. 
     *  
     * @param ctx 
     */  
    private void sendCrashReportsToServer(Context ctx) {  
        String[] crFiles = getCrashReportFiles(ctx);  
        if (crFiles != null && crFiles.length > 0) {  
            TreeSet<String> sortedFiles = new TreeSet<String>();  
            sortedFiles.addAll(Arrays.asList(crFiles));  
  
            for (String fileName : sortedFiles) {  
                File cr = new File(ctx.getFilesDir(), fileName);  
                postReport(cr);  
                cr.delete();// 删除已发送的报告  
            }  
        }  
    }  
  
    /** 
     * 获取错误报告文件名 
     *  
     * @param ctx 
     * @return 
     */  
    private String[] getCrashReportFiles(Context ctx) {  
        File filesDir = ctx.getFilesDir();  
        // 实现FilenameFilter接口的类实例可用于过滤器文件名  
        FilenameFilter filter = new FilenameFilter() {  
            // accept(File dir, String name)  
            // 测试指定文件是否应该包含在某一文件列表中。  
            public boolean accept(File dir, String name) {  
                return name.endsWith(CRASH_REPORTER_EXTENSION);  
            }  
        };   
        // 返回一个字符串数组，这些字符串指定此抽象路径名表示的目录中满足指定过滤器的文件和目录  
        return filesDir.list(filter);  
    }  
  
    private void postReport(File file) {  
        // TODO 使用HTTP Post 发送错误报告到服务器  
        // 这里不再详述,开发者可以根据OPhoneSDN上的其他网络操作  
        // 教程来提交错误报告  
    }  
  
    /** 
     * 在程序启动时候, 可以调用该函数来发送以前没有发送的报告 
     */  
    public void sendPreviousReportsToServer() {  
        sendCrashReportsToServer(mContext);  
    }  
	/**
	 * 确认是否关机
	 */
	private void closeDialog(String info,final Throwable ex) {
		Looper.prepare();
		Handler mHandler = new Handler();
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Builder builder = new Builder(mContext);
				Dialog dialog;
				if (result == null || "".equals(result)) {
					
					builder.setMessage("程序出现异常，请联系管理人员."+"\n" +
//				 "联系人邮箱："+" shanggao@sumecom.com " + "\n" +
//				 "联系人电话：" + " 18761648117 " + "\n" +
					"错误日志：" +"\n"+ ex.toString());
				}else{
					builder.setMessage("程序出现异常，请联系管理人员."+"\n" +
//							 "联系人邮箱："+" shanggao@sumecom.com " + "\n" +
//							 "联系人电话：" + " 18761648117 " + "\n" +
								"错误日志：" +"\n"+  result);
				}
				MyLog.addMyLog(result);
				
				builder.setCancelable(false);
				builder.setPositiveButton("请按power关机", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
//						dialog.dismiss();
						//利用反射来解决点击对话框的问题
						try {
						/*	Field field = dialog.getClass().getSuperclass()
									.getDeclaredField("mShowing");
							field.setAccessible(true);
							field.set(dialog, false);*/

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			

				dialog = builder.create();
				dialog.show();
			}
		}, 500);
		Looper.loop();

	}
	
	//用来存储设备信息和异常信息  
	  private Map<String, String> infos = new HashMap<String, String>();  

	 /** 
     * 收集设备参数信息 
	     * @param ctx 
	     */  
	   public void collectDeviceInfo(Context ctx) {  
	       try {  
	            PackageManager pm = ctx.getPackageManager();  
	            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);  
	            if (pi != null) {  
	                String versionName = pi.versionName == null ? "null" : pi.versionName;  
	                String versionCode = pi.versionCode + "";  
	               infos.put("versionName", versionName);  
	               infos.put("versionCode", versionCode);  
	            }  
	        } catch (NameNotFoundException e) {  
	            Log.e(TAG, "an error occured when collect package info", e);  
	        }  
	        Field[] fields = Build.class.getDeclaredFields();  
	        for (Field field : fields) {  
	            try {  
	                field.setAccessible(true);  
	                infos.put(field.getName(), field.get(null).toString());  
	                Log.d(TAG, field.getName() + " : " + field.get(null));  
	            } catch (Exception e) {  
	                Log.e(TAG, "an error occured when collect crash info", e);  
	            }  
	        }  
	    }

	 //用于格式化日期,作为日志文件名的一部分  
	     private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	     String result ;//具体错误信息
	   /** 
	        * 保存错误信息到文件中 
	        *  
	        * @param ex 
	        * @return  返回文件名称,便于将文件传送到服务器 
	        */  
	private String saveCrashInfo2File(Throwable ex) {

		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, String> entry : infos.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			sb.append(key + "=" + value + "\n");
		}

		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		ex.printStackTrace(printWriter);
		Throwable cause = ex.getCause();
		while (cause != null) {
			cause.printStackTrace(printWriter);
			cause = cause.getCause();
		}
		printWriter.close();
		 result = writer.toString();
		sb.append(result);
		try {
			// long timestamp = System.currentTimeMillis();
			// String time = formatter.format(new Date());
			// String fileName = "crash-" + time + "-" + timestamp + ".txt";
			// 日志不加时分秒，表示当天的日志
			String time = formatter.format(new Date());
			String fileName = "crash-" + time + ".txt";
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				String path = Environment.getExternalStorageDirectory()
						+ "/hbbank_crash/";
				File dir = new File(path);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				File myLog = new File(path + fileName);
				if (!myLog.exists()) {
					try {
						myLog.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				FileOutputStream fos = new FileOutputStream(path + fileName,true);
				fos.write(sb.toString().getBytes());
				fos.close();
			}
			return fileName;
		} catch (Exception e) {
			Log.e(TAG, "an error occured while writing file...", e);
		}
		return null;
	}

}
