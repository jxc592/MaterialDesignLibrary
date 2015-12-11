package com.guoguang.dksq.socket;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.aidl.cilent.library.sweetdialog.SweetAlertDialog;
import com.aidl.cilent.util.EasyX509TrustManager;
import com.guoguang.dksq.contants.Contants;
import com.guoguang.mitfs.client.IMitFsClientProcess;
import com.guoguang.mitfs.client.MitFsClient;
import com.guoguang.mitfs.client.MitFsHttpClient;
import com.guoguang.mitfs.client.bean.MitFsException;
import com.guoguang.mitfs.client.bean.MitFsRequest;
import com.guoguang.mitfs.client.bean.MitFsResponse;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 操作文件的工具类
 * 
 * @author thinkpad
 *
 */
public class FileSocketUtil {
	// private MitFsRequest mRequest = null;
	Context mContext = null;
	public MitFsClient client;// 文件操作客户端对象

	public MitFsHttpClient mitFsHttpClient;
	String downurl = "";
//	String downurl = "http://192.168.51.151:9080/mitfsps/download.mitfs.action";

	public synchronized MitFsClient getClient() {
		return client;
	}

	public void setClient(MitFsClient client) {
		this.client = client;
	}
	
	public MitFsHttpClient getMitFsHttpClient() {
		return mitFsHttpClient;
	}

	public void setMitFsHttpClient(MitFsHttpClient mitFsHttpClient) {
		this.mitFsHttpClient = mitFsHttpClient;
	}

	/**
	 * 
	 * @param mCon
	 *            Android上下文
	 *
	 */
	public FileSocketUtil(Context mCon) {
		super();
		// TODO Auto-generated constructor stub
		mContext = mCon;
		String[] ipStr =  getfileServerIP();
		if (ipStr != null) {
			try {
				Contants.UPLOADFILE_IP = ipStr[0];
				Contants.UPLOADFILE_PORT = Integer.valueOf(ipStr[1]);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		client = new MitFsClient(Contants.UPLOADFILE_IP,Contants.UPLOADFILE_PORT);
		client.setMitFsClientProcessListener(new processListener());
		
		String[] downStr = getfileServerIPDown();
		if (downStr != null) {
			try {
				Contants.FILE_DONW_IP = downStr[0];
				Contants.FILE_UPDOWN_PORT = Integer.valueOf(downStr[1]);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		downurl = "http://"+Contants.FILE_DONW_IP+ "" + ":" + Contants.FILE_UPDOWN_PORT +"/mitfsps/download.mitfs.action";
		//downurl = "https://"+Contants.FILE_DONW_IP+"/mitfsps/download.mitfs.action";
		mitFsHttpClient = new MitFsHttpClient(downurl);
		mitFsHttpClient.setMitFsClientProcessListener(new processListener());
		EasyX509TrustManager.checkSSL();
	}
	
	public FileSocketUtil(Context mContext,String ip,int port){
		this(mContext);
	}

	/**
	 * 打开服务器连接
	 * 
	 * @return true 连接成功 false 连接失败
	 */
	public boolean Connect() {
		if (client != null) {
			return client.Connect();
		}
		return false;
	}

	/**
	 * 断开连接
	 */
	public void disConnect() {
		if (client != null) {
			client.disConnect();
		}
	}

	SweetAlertDialog mdAlertDialog = null;

	/**
	 * 上传一个文件
	 * 
	 * @param sysid
	 *            系统号
	 * @param tlid
	 *            柜员号
	 * @param termid
	 *            终端号
	 * @param filepah
	 *            上传文件路径
	 * @return
	 */
	public void upLoadFile(final String sysid, final String tlid,
			final String termid, final String filepah, final Handler update,
			final int what) {
		mdAlertDialog = showSweetAlertDialog("正在上传文件，请稍候...");
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Connect();
				File mFile = new File(filepah);
				if (mFile.exists()) {
					MitFsRequest bean = MitFsRequest.CreateUploadReqBean(sysid,
							tlid, termid, RamdomSerNO(), new File(filepah));
					MitFsResponse res=null;
					try {
						res = client.upload(bean);
					} catch (MitFsException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						if (client != null)
							client.disConnect();
						res = new MitFsResponse("99", "文件上传失败，请重试....");
					} finally {
						disConnect();
						Message msg = new Message();
						msg.obj = res;
						msg.what = what;
						update.sendMessage(msg);
						upHander.sendEmptyMessage(100);
					}
				}

			}
		});
		mThread.start();
	}

	/**
	 * 上传批量文件
	 * 
	 * @param sysid
	 *            系统号
	 * @param tlid
	 *            柜员号
	 * @param termid
	 *            终端号
	 * @param beanlist
	 * @return
	 */
	public void upLoadFileList(final String sysid, final String tlid,
			final String termid, final List<MitFsRequest> beanlist,
			final Handler update, final int what) {
		mdAlertDialog = showSweetAlertDialog("正在上传文件，请稍候...");
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				List<MitFsResponse> mitFsResList = new ArrayList<MitFsResponse>();
				Connect();
				MitFsResponse res = null;
				try {
					for (int i = 0; i < beanlist.size(); i++) {
						MitFsRequest bean = beanlist.get(i);
						res = client.upload(bean);
						if("00".equals(res.getResultCode())){
							mitFsResList.add(res);
						}else {
							res = client.upload(bean);
						}						
					}
				} catch (Exception e) {
					// TODO: handle exception
					res = new MitFsResponse("99", "文件上传失败，请重试....");
					mitFsResList.add(res);
				} finally {
					disConnect();
					Message msg = new Message();
					msg.obj = mitFsResList;
					msg.what = what;
					update.sendMessage(msg);
					upHander.sendEmptyMessage(100);
				}
			}
		});
		mThread.start();
	}

	public void upLoadFileList1(final String sysid, final String tlid,
			final String termid, final List<String> filelist,
			final Handler update, final int what) {
		mdAlertDialog = showSweetAlertDialog("正在上传文件，请稍候...");
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				List<MitFsResponse> mitFsResList = new ArrayList<MitFsResponse>();
				Connect();
				MitFsResponse res = null;
				try {
					for (int i = 0; i < filelist.size(); i++) {
						MitFsRequest bean = MitFsRequest.CreateUploadReqBean(
								sysid, tlid, termid, RamdomSerNO(), new File(
										filelist.get(i)));
						try {
							res = client.upload(bean);
							//测试 
							/*if (filelist.size() > 3 && i == 2) {
									res = new MitFsResponse("99", filelist.get(i));
									mitFsResList.add(res);
									continue;
							}*/
							
							
						} catch (Exception e) {
							res = new MitFsResponse("99", filelist.get(i));
//							mitFsResList.add(res);
						}finally{
							mitFsResList.add(res);
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
//					res = new MitFsResponse("99", filelist.get(i));
//					mitFsResList.add(res);
				} finally {
					disConnect();
					Message msg = new Message();
					msg.obj = mitFsResList;
					msg.what = what;
					update.sendMessage(msg);
					upHander.sendEmptyMessage(100);
				}
			}
		});
		mThread.start();
	}
	public void downloadFileList1(final String sysid, final String tlid,
			final String termid, final List<String> ids,
			final String downloadpath, final Handler update, final int what) {
		mdAlertDialog = showSweetAlertDialog("正在上传文件，请稍候...");
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				List<MitFsResponse> mitFsResList = new ArrayList<MitFsResponse>();
				Connect();
				MitFsResponse res = null;
				try{
					for (int i = 0; i < ids.size(); i++) {
						MitFsRequest dbean = MitFsRequest
								.CreateDownLoadReqBean(sysid, tlid, termid,
										ids.get(i), downloadpath);
						res = client.download(dbean);
						mitFsResList.add(res);
					}
				}catch (Exception e) {
					// TODO: handle exception
					res = new MitFsResponse("99", "文件上传失败，请重试....");
					mitFsResList.add(res);
				}finally{
					disConnect();
					Message msg = new Message();
					msg.obj = mitFsResList;
					msg.what = what;
					update.sendMessage(msg);
					upHander.sendEmptyMessage(100);
				}	
			}
		});
		mThread.start();
	}

	boolean isOk = false;
	MitFsResponse res = null; // 上传下载返回的对象

	/**
	 * 下载一个文件的方法
	 * 
	 * @param sysid
	 *            系统号
	 * @param tlid
	 *            柜员号
	 * @param termid
	 *            终端号
	 * @param resid
	 *            文件ID
	 * @param filepah
	 *            下载文件目录
	 */
	public void downFile(final String sysid, final String tlid,
			final String termid, final String resid, final String downpath,
			final Handler updateHandler, final int what) {
		mdAlertDialog = showSweetAlertDialog("请稍候，正在下载文件..");
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				Connect();
				MitFsRequest dbean = MitFsRequest.CreateDownLoadReqBean(sysid,
						tlid, termid, resid, downpath);
				long starttime = System.currentTimeMillis();
				try {
					res = mitFsHttpClient.download(dbean);
					// res = client.download(dbean);
				} catch (MitFsException e) {
					e.printStackTrace();
					// if (client != null)
					// client.disConnect();
					res = new MitFsResponse("99", "文件下载失败，请重试....");
					System.out.println("  MitFsException    " + e.toString());
				} finally {
					disConnect();
					Message msg = new Message();
					msg.obj = res;
					msg.what = what;
					long endtime = System.currentTimeMillis();
					System.out.println("花费时间： " + (endtime - starttime));
					updateHandler.sendMessage(msg);
					upHander.sendEmptyMessage(100);
				}
			}
		});
		mThread.start();
	}

	private SweetAlertDialog showSweetAlertDialog(String message) {
		SweetAlertDialog Dialog = new SweetAlertDialog(mContext,
				SweetAlertDialog.PROGRESS_TYPE);
		Dialog.setTitleText(message);
		Dialog.show();
		return Dialog;
	}

	private void dismissSweetAlertDialog(SweetAlertDialog mdAlertDialog) {
		if (mdAlertDialog != null && mdAlertDialog.isShowing()) {
			mdAlertDialog.dismiss();
		}
	}

	/**
	 * 生成序列号的随机数
	 * 
	 * @return
	 */
	public static final String RamdomSerNO() {
		int numcode = (int) ((Math.random() * 9 + 1) * 100000);
		String date = detaledLshFormat(new Date());
		String seqNo = date + numcode;
		return seqNo;
	}

	/**
	 * 返回详细的流水号中使用的日期格式： yyyyMMddHHmmss
	 * 
	 * @param date
	 * @return
	 */
	private static String detaledLshFormat(Date date) {
		if (date == null) {
			return "";
		} else {
			return detaledLshFormat.format(date);
		}
	}

	private static final SimpleDateFormat detaledLshFormat = new SimpleDateFormat(
			"yyyyMMddHHmmss");

	/**
	 * 上传进度的监听事件
	 * 
	 * @author thinkpad
	 */
	private class processListener implements IMitFsClientProcess {
		@Override
		public void onConnect() {
			// TODO Auto-generated method stub
			System.out.println("-----onConnect-----");
		}

		@Override
		public void onDisConnect() {
			// TODO Auto-generated method stub
			System.out.println("-----onDisConnect-----");
		}

		@Override
		public void process(String filename, int filesize, int processSize) {
			// TODO Auto-generated method stub
			System.out.println("-----filename----- " + filename
					+ "-----filesize----- " + filesize
					+ "------processSize----- " + processSize);
		}
	}

	Handler upHander = new Handler() {
		public void handleMessage(Message msg) {
			dismissSweetAlertDialog(mdAlertDialog);
			switch (msg.what) {
			case 100:

				break;

			default:
				break;
			}
		}
	};

	
	/**
	 * 获取文件服务器IP
	 * @return
	 */
	@SuppressLint("NewApi")
	public  final String[] getfileServerIP(){
		String[] resStr =  null;
		Uri uri=Uri.parse("content://com.wincom.android.provider.HBbankloudProvider/getData");
		Bundle bundle=mContext.getContentResolver().call(uri, "getFileServerIP", "", new Bundle());
		String id=(String) bundle.get("AppServerIP");
		
		if (id != null && !"".equals(id)) {
			if (id.contains(":")) {
				resStr = id.split(":");
			}else{
				return null;
			}
			return resStr;
		}
		return null;
	}
	
	@SuppressLint("NewApi")
	public  final String[] getfileServerIPDown(){
		String[] resStr =  null;
		Uri uri=Uri.parse("content://com.wincom.android.provider.HBbankloudProvider/getData");
		Bundle bundle=mContext.getContentResolver().call(uri, "getFileServerIPDown", "", new Bundle());
		String id=(String) bundle.get("getFileServerIPDown");
		
		if (id != null && !"".equals(id)) {
			if (id.contains(":")) {
				resStr = id.split(":");
			}else{
				return null;
			}
			return resStr;
		}
		return null;
	}
	
	
	public abstract class DownLoadTask extends Thread{
		int progress = 0;
		MitFsRequest request;
		private DownLoadTask() {
			super();
		}
		public DownLoadTask(MitFsRequest request) {
			super();
			this.request = request;
		}
		Handler mHandler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				switch (msg.what) {
				case 100:
					onResult((MitFsResponse)msg.obj);
					break;
				case 101:
					onError((MitFsException)msg.obj,request);
					break;
				}
			}
		};
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			try {
				MitFsResponse response=mitFsHttpClient.download(request);
				mHandler.obtainMessage(100, response).sendToTarget();
			} catch (MitFsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mHandler.obtainMessage(101,mitFsHttpClient).sendToTarget();
			}
		}
		abstract void onResult(MitFsResponse response);
		abstract void onError(MitFsException mitFsException,MitFsRequest request);
		
		public int getProgress() {
			
			return progress;
		}
	}
	
	public abstract class UploadTask extends Thread{
		int progress = 0;
		MitFsRequest request;
		
		
		public UploadTask(MitFsRequest request) {
			super();
			this.request = request;
		}

		Handler mHandler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				switch (msg.what) {
				case 100:
					onResult((MitFsResponse)msg.obj);
					break;
				case 101:
					onError((MitFsException)msg.obj,request);
					break;
				}
			}
		};
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			try {
				MitFsResponse response=client.upload(request);
				mHandler.obtainMessage(100, response).sendToTarget();
			} catch (MitFsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mHandler.obtainMessage(101,mitFsHttpClient).sendToTarget();
			}
		}
		abstract void onResult(MitFsResponse response);
		abstract void onError(MitFsException mitFsException,MitFsRequest request);
		
		public int getProgress() {
			
			return progress;
		}
	}
	
	DownLoadTask newDownloadTask(MitFsRequest request){
		return new DownLoadTask(request){
			@Override
			void onResult(MitFsResponse response) {
				// TODO Auto-generated method stub
				
			}
			@Override
			void onError(MitFsException mitFsException, MitFsRequest request) {
				// TODO Auto-generated method stub
				
			}};
	}
}
