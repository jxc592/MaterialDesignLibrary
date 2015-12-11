package com.guoguang.dksq.application;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.Display;
import android.view.WindowManager;

import com.aidl.cilent.entity.HeaderCommonResponse;
import com.aidl.cilent.util.BaseUtil;
import com.aidl.cilent.util.PhoneInfoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guoguang.dksq.R;
import com.guoguang.dksq.contants.Contants;
import com.guoguang.dksq.database.Assure;
import com.guoguang.dksq.database.AssureDao;
import com.guoguang.dksq.database.Guarantee;
import com.guoguang.dksq.database.GuaranteeDao;
import com.guoguang.dksq.database.House;
import com.guoguang.dksq.database.HouseDao;
import com.guoguang.dksq.database.LoanBusiness;
import com.guoguang.dksq.database.LoanBusinessDao;
import com.guoguang.dksq.database.PhotoEntity;
import com.guoguang.dksq.database.PhotoEntityDao;
import com.guoguang.dksq.entity.AreaEntity;
import com.guoguang.dksq.entity.RegistInfo;
import com.guoguang.dksq.model.PhotoFactory;
import com.guoguang.dksq.socket.IFileSocketHandle;
import com.guoguang.dksq.socket.UpLoadTask;
import com.guoguang.mitfs.client.bean.MitFsException;
import com.guoguang.mitfs.client.bean.MitFsRequest;
import com.guoguang.mitfs.client.bean.MitFsResponse;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PreLoadService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	Gson gson;
	ProLoadApp application;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		application  = (ProLoadApp) getApplication();
		gson = new GsonBuilder().create();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		String action =intent.getStringExtra("Action");
		if(action == null){
			return super.onStartCommand(intent, flags, startId);
		}else {
			if("GETUSERINFO".equals(action)){
				String userId = intent.getStringExtra("userId");
				getUserInfo(userId);
			}else if("UPLOADDRAFT".equals(action)){
				String SeqNo = intent.getStringExtra("SeqNo");
				submitPhoto(SeqNo);
			}else if("CodeLib".equals(action)){
				new Thread(){
					public void run() {
						//根据sharedPre的codlob的值判断数据是否加载过。
						SharedPreferences sp= getSharedPreferences("PreLoadInvest", Context.MODE_APPEND);
						String areaTag = sp.getString("AreaLib", "00");
						if(!"00".equals(areaTag)){
							AreaEntity libEntity=gson.fromJson(areaTag, AreaEntity.class);
							Contants.areaEntity = libEntity;
						}else {
							HashMap<String, String> map = new HashMap<>();
							map.put("TransCode", "1299");
							String body= gson.toJson(map);
							String result[] = ProLoadApp.mAidlClient.exec(Contants.header, body);
							if(result!=null&&result[2]!=null&&!"".equals(result[2])){
								//List<HashMap<String, ValueInfo>>
								sp.edit().putString("AreaLib", result[2]).commit();
								AreaEntity areaEntity=gson.fromJson(result[2], AreaEntity.class);
								Contants.areaEntity = areaEntity;
							}
						}
					}
				}.start();
			}

		}
		return super.onStartCommand(intent, flags, startId);
	}

	private void getUserInfo(final String userId) {
		// TODO Auto-generated method stub
		new Thread(){
			public void run() {
				HashMap<String, String> bodyMap = new HashMap<>();
				bodyMap.put("TransCode", "1297");
				bodyMap.put("userId", userId);
				String body =gson.toJson(bodyMap);
				String result[]=ProLoadApp.getmAidlClient().exec(Contants.header, body);
				if(result!=null&&result[2]!=null&&!"".equals(result[2])){
					RegistInfo registInfo = gson.fromJson(result[2], RegistInfo.class);
					Contants.registInfo = registInfo;
					Intent mIntent = new Intent();
					mIntent.setAction("UserInfoLoaded");
					sendBroadcast(mIntent);
				}
			}
		}.start();
	}

    public static final int UPLOANDRATFAILED = 10101010;

	private void upLoanDraft(final String seqno){
        new Thread(){
            @Override
            public void run() {
                super.run();
                HashMap<String,Object> bodyMap = new HashMap<String, Object>();
                bodyMap.put("TransCode", "1223");
				List<LoanBusiness> loanBusinesss = application.getmDaoSession().queryBuilder(LoanBusiness.class).where(LoanBusinessDao.Properties.SeqNo.eq(seqno))
						.list();
				if(loanBusinesss.size()>0){
					bodyMap.put("loanBusiness", loanBusinesss.get(0));
				}

				List<Assure> assureInfos = application.getmDaoSession().queryBuilder(Assure.class).where(AssureDao.Properties.SeqNo.eq(seqno))
						.list();
				if(assureInfos.size()>0){
					bodyMap.put("assure", assureInfos.get(0));
				}

				List<House> houses = application.getmDaoSession().queryBuilder(House.class).where(HouseDao.Properties.SeqNo.eq(seqno))
						.list();
				if(houses.size()>0){
					bodyMap.put("house", houses.get(0));
				}
				List<Guarantee> gurantees = application.getmDaoSession().queryBuilder(Guarantee.class).where(GuaranteeDao.Properties.SeqNo.eq(seqno))
						.list();
                if(gurantees.size()>0){
					bodyMap.put("guarantees",gurantees);
				}

				if(aiMap!=null&&aiMap.size()>0){
					bodyMap.put("photoMap",aiMap);
				}
                String body = gson.toJson(bodyMap);
                String result[] = ProLoadApp.getmAidlClient().exec(Contants.header, body);
                if(result!=null&&result[1]!=null&&!"".equals(result[1])){
                    HeaderCommonResponse headerCommonResponse = gson.fromJson(result[1], HeaderCommonResponse.class);
                    if("0000".equals(headerCommonResponse.getResultCode())){
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(PreLoadService.this);
                        Intent intent = new Intent(PreLoadService.this,PreLoadService.class);
                        intent.putExtra("Action", "null");
                        PendingIntent pendingIntent = PendingIntent.getService(PreLoadService.this, 100, intent, PendingIntent.FLAG_ONE_SHOT);
                        builder.setContentIntent(pendingIntent);
						builder.setSubText("贷款草稿同步");
                        builder.setContentText("结果：草稿已同步到服务器");
                        builder.setSmallIcon(R.drawable.icon_dksq);
                        builder.setShowWhen(true);
                        builder.setWhen(System.currentTimeMillis());
                        Notification notification = builder.build();
                        NotificationManagerCompat.from(PreLoadService.this).notify(UPLOANDRATFAILED,notification);
                    }else {

					}
                }
            }
        }.start();
    }


	PhotoEntity curPhoto;
	HashMap<String,Object> aiMap;
	Iterator<PhotoEntity> iterator;
	String SeqNo;
	/**
	 * 提交照片
	 */
	void submitPhoto(String SeqNo){
		this.SeqNo  = SeqNo;
		aiMap = new HashMap<>();
		List<PhotoEntity> list = application.getmDaoSession().queryBuilder(PhotoEntity.class).where(PhotoEntityDao.Properties.SeqNo.eq(SeqNo)).list();
		if(list==null ||list.size() ==0){
			upLoanDraft(SeqNo);
			return;
		}
		iterator = list.iterator();
		upLoadNext();
	}



	void upLoadNext(){
		if(!iterator.hasNext()){
			upLoanDraft(SeqNo);
			return;
		}
		while(iterator.hasNext()){
			curPhoto = iterator.next();
			File mFile = new File(curPhoto.getPhotoPath());
			if(mFile.exists()){
				MitFsRequest request=MitFsRequest.CreateUploadReqBean("GFMM",application.getmLoginResponse().getUserId(), PhoneInfoUtil.getMyUUID(PreLoadService.this), BaseUtil.RamdomSerNO(), mFile);
				new UpLoadTask(request, application.getUploadClient(), new MyFileSocketHandle()).start();
				break;
			}else {
				upLoadNext();
			}
		}
	}

	class MyFileSocketHandle implements IFileSocketHandle {
		@Override
		public void onResult(MitFsResponse response) {
			// TODO Auto-generated method stub
			if("00".equals(response.getResultCode())){
				String node = curPhoto.getPhotoTypeCode();
				if(aiMap.get(node)!=null){
					HashMap<String,  HashMap<String, String>> map = (HashMap<String,  HashMap<String, String>>) aiMap.get(node);
					HashMap<String, String> po_map =new HashMap<>();
					po_map.put("PhotoName", response.getLocalFile().getName());
					po_map.put("PhotoUUID", response.getResid());
					map.put(curPhoto.getPosition(), po_map);
					aiMap.put(node, map);
				}else {
					HashMap<String, HashMap<String, String>> map =new HashMap<>();
					HashMap<String, String> po_map =new HashMap<>();
					po_map.put("PhotoName", response.getLocalFile().getName());
					po_map.put("PhotoUUID", response.getResid());
					map.put(curPhoto.getPosition(), po_map);
					aiMap.put(node, map);
				}
				upLoadNext();
			}else {
				MitFsRequest request = MitFsRequest.CreateUploadReqBean("GFMM", application.getmLoginResponse().getUserId(), PhoneInfoUtil.getMyUUID(PreLoadService.this), BaseUtil.RamdomSerNO(), response.getLocalFile());
				new UpLoadTask(request, application.getUploadClient(), this).start();
			}
		}

		@Override
		public void onError(MitFsException mitFsException, MitFsRequest request) {
			failedTime ++;
			if(failedTime>3){
				failedTime=0;
				//upLoadNext();
				showSystemDialog(request);
			}else {
				new UpLoadTask(request, application.getUploadClient(),this).start();
			}
		}
	}

	int failedTime;


	AlertDialog d;

	private void showSystemDialog(final MitFsRequest request) {
    /* create ui */
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setTitle("影像异常");
		b.setMessage(PhotoFactory.photo_Key_value.get(curPhoto.getPhotoTypeCode())+"存在照片多次上传出现异常是否继续上传").
				setPositiveButton("继续上传", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						new UpLoadTask(request, application.getUploadClient(),new MyFileSocketHandle()).start();
					}
				}).
				setNegativeButton("忽略", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						upLoadNext();
					}
				});
		d = b.create();
		d.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		//d.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY);
		d.show();

        /* set size & pos */
		WindowManager.LayoutParams lp = d.getWindow().getAttributes();
		WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		if (display.getHeight() > display.getWidth()) {
			//lp.height = (int) (display.getHeight() * 0.5);
			lp.width = (int) (display.getWidth() * 1.0);
		} else {
			//lp.height = (int) (display.getHeight() * 0.75);
			lp.width = (int) (display.getWidth() * 0.5);
		}
		d.getWindow().setAttributes(lp);
	}
}
