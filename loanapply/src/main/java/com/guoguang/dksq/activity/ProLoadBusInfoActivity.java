package com.guoguang.dksq.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;

import com.aidl.cilent.entity.HeaderCommonResponse;
import com.aidl.cilent.library.comondialog.ShowResultDialog;
import com.aidl.cilent.library.comondialog.ShowResultDialog.OnSureClickListener;
import com.aidl.cilent.library.sweetdialog.SweetAlertDialog;
import com.aidl.cilent.library.sweetdialog.SweetAlertDialog.OnSweetClickListener;
import com.aidl.cilent.util.BaseUtil;
import com.aidl.cilent.util.CrashHandler;
import com.aidl.cilent.util.PhoneInfoUtil;
import com.aidl.cilent.util.ToastCoustom;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guoguang.dksq.application.PreLoadService;
import com.guoguang.dksq.application.ProLoadApp;
import com.guoguang.dksq.contants.Contants;
import com.guoguang.dksq.database.Assure;
import com.guoguang.dksq.database.AssureDao;
import com.guoguang.dksq.database.Guarantee;
import com.guoguang.dksq.database.GuaranteeDao;
import com.guoguang.dksq.database.LoanBusiness;
import com.guoguang.dksq.database.LoanBusinessDao;
import com.guoguang.dksq.database.LoanRecord;
import com.guoguang.dksq.database.LoanRecordDao;
import com.guoguang.dksq.database.PhotoEntity;
import com.guoguang.dksq.database.PhotoEntityDao;
import com.guoguang.dksq.model.FirstSave;
import com.guoguang.dksq.model.IProcedure;
import com.guoguang.dksq.model.LoanApply;
import com.guoguang.dksq.model.PhotoFactory;
import com.guoguang.dksq.model.SavedSave;
import com.guoguang.dksq.socket.IFileSocketHandle;
import com.guoguang.dksq.socket.UpLoadTask;
import com.guoguang.mitfs.client.bean.MitFsException;
import com.guoguang.mitfs.client.bean.MitFsRequest;
import com.guoguang.mitfs.client.bean.MitFsResponse;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 贷前调查的业务申请界面
 * @author thinkpad
 *
 */
public class ProLoadBusInfoActivity extends PreBaseActivity{
	Gson gson;
	IProcedure procedure;
	ProLoadApp application;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        CrashHandler.getInstance().init(this);
        IntentFilter mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("UserInfoLoaded");
		registerReceiver(new MyOnBroadCastReceiver(), mIntentFilter);
		prepare();
		procedure.firstLoad();
	}
	private void prepare() {
		// TODO Auto-generated method stub
		gson = new GsonBuilder().create();
		Intent intent = getIntent();
		String isNew = intent.getStringExtra("isNew"); 
		String applyType = intent.getStringExtra("applyType");
		//原来区分草稿还是一般申请，但是我们这里没有区别所以暂时这样处理。后期拓展再做处理。
		if("true".equals(isNew)){
			procedure = new LoanApply(this,applyType);
		}else {
			procedure = new LoanApply(this,applyType);
		}
		application = (ProLoadApp) getApplication();
	}
	@Override
	protected void onLast() {
		// TODO Auto-generated method stub
		super.onLast();
		procedure.onLast();
	}
	@Override
	protected void onNext() {
		// TODO Auto-generated method stub
		super.onNext();
		procedure.onNext();
	}
	@Override
	protected void onSave() {
		// TODO Auto-generated method stub
		super.onSave();
		new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE).setTitleText("是否保存草稿").setCancelText("舍弃").setCancelClickListener(new OnSweetClickListener() {
			@Override
			public void onClick(SweetAlertDialog sweetAlertDialog) {
				sweetAlertDialog.dismiss();
				ProLoadBusInfoActivity.this.finish();
			}
		}).setConfirmClickListener(new OnSweetClickListener() {
			@Override
			public void onClick(SweetAlertDialog sweetAlertDialog) {
				// TODO Auto-generated method stub
				sweetAlertDialog.dismiss();
				procedure.onSave();
				localSave();
			}
		}).show();


	}
	private void localSave() {
		// TODO Auto-generated method stub
		String SeqNo = Contants.loanBusinessInfo.getSeqNo();
        if(SeqNo == null || "".equals(SeqNo)){
            SeqNo = BaseUtil.RamdomSerNO();
            new FirstSave(SeqNo).save();
        }else {
            List<LoanRecord> list =ProLoadApp.getmDaoSession().queryBuilder(LoanRecord.class).where(LoanRecordDao.Properties.SeqNo.eq(SeqNo)).list();
            if(list.size() > 0){
                new SavedSave(SeqNo).save();
            }else {
                new FirstSave(SeqNo).save();
            }
        }
        Intent mIntent = new Intent(this, PreLoadService.class);
        mIntent.putExtra("Action","UPLOADDRAFT");
		mIntent.putExtra("SeqNo",Contants.loanBusinessInfo.getSeqNo());
        startService(mIntent);
		finish();
	}

	public void setCurrentStep(List<String>  list,int position) {
		// TODO Auto-generated method stub
		setStepList(list,position);
	}




	SweetAlertDialog loaDialog;
	public void onSubmit(){
		new SweetAlertDialog(ProLoadBusInfoActivity.this,SweetAlertDialog.NORMAL_TYPE).setTitleText("确认提交申请吗").setConfirmText("确认").setCancelText("取消").setConfirmClickListener(new OnSweetClickListener() {
			@Override
			public void onClick(SweetAlertDialog sweetAlertDialog) {
				sweetAlertDialog.dismiss();
				submitPhoto();
			}
		}).show();

	}



	Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(loaDialog!=null&&loaDialog.isShowing()){
				loaDialog.dismiss();
			}
			switch (msg.what) {
			case 100:
				HeaderCommonResponse hResponse= (HeaderCommonResponse) msg.obj;
				if("0000".equals(hResponse.getResultCode())){
					//上传影像资料
//					Intent mIntent = new Intent(ProLoadBusInfoActivity.this,PreLoadService.class);
//					mIntent.putExtra("Action","UPLOADPHOTOS");
//					startService(mIntent);
					deleteCaogao(Contants.loanBusinessInfo.getSeqNo());
					new ShowResultDialog(ProLoadBusInfoActivity.this).
					setType(ShowResultDialog.SUCCESS_TYPR).
					setMessage(hResponse.getMessage()).
					setOnSureClickListener(new OnSureClickListener() {
						@Override
						public void onSure() {
							// TODO Auto-generated method stub
							ProLoadBusInfoActivity.this.finish();
						}
					}).show();
				}else {
					new ShowResultDialog(ProLoadBusInfoActivity.this).
					setType(ShowResultDialog.FAILED_TYPR).
					setMessage(hResponse.getMessage()).
					setOnSureClickListener(new OnSureClickListener() {
						@Override
						public void onSure() {
							// TODO Auto-generated method stub
						    new SweetAlertDialog(ProLoadBusInfoActivity.this,SweetAlertDialog.NORMAL_TYPE).
                                    setTitleText("交易失败，你需要保存草稿吗").setConfirmText("确定").
                                    setCancelText("取消").
                                    setConfirmClickListener(new OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            sweetAlertDialog.dismiss();
                                            localSave();
                                        }
                                    }).show();
                        }
					}).show();
				}
				break;
			default:
				break;
			}
		}
	};


	PhotoEntity curPhoto;
	HashMap<String,Object> aiMap;
	Iterator<PhotoEntity> iterator;
	/**
	 * 提交照片
	 */
	void submitPhoto(){
		List<PhotoEntity> list = null;
		if(Contants.toSavePhotoHashMap == null||Contants.toSavePhotoHashMap.size() == 0){
			//TODO 没有照片直接提交
			submit();
		}else {
			list = new ArrayList<>();
			Set<String> keySet = Contants.toSavePhotoHashMap.keySet();
			Iterator<String> keyIter = keySet.iterator();
			while(keyIter.hasNext()){
				String key  = keyIter.next();
				HashMap<String,String> map = Contants.toSavePhotoHashMap.get(key);
				if(map == null||map.size()==0){
					continue;
				}else {
					Set<String> z = map.keySet();
					Iterator<String> sIter = z.iterator();
					while (sIter.hasNext()){
						String key2 = sIter.next();
						PhotoEntity entity = new PhotoEntity();
						entity.setPhotoTypeCode(key);
						entity.setPhotoPath(map.get(key2));
						entity.setPosition(key2);
						list.add(entity);
					}
				}
			}
		}
		aiMap = new HashMap<>();
		if(list==null ||list.size() ==0){
			submit();
			return;
		}
		iterator = list.iterator();

		loaDialog = new SweetAlertDialog(this,SweetAlertDialog.PROGRESS_TYPE).setTitleText("正在上传照片");
		loaDialog.show();

		upLoadNext();
	}


	void submit(){
		loaDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
		loaDialog.setTitleText("正在提交业务申请");
		loaDialog.show();
		new Thread(){
			public void run(){
				HashMap<String, Object> map= new HashMap<>();
				map.put("TransCode", "1220");
				if(Contants.loanBusinessInfo.getSeqNo()==null||"".equals(Contants.loanBusinessInfo.getSeqNo())){
					Contants.loanBusinessInfo.setSeqNo(BaseUtil.RamdomSerNO());
				}

				map.put("loanBusiness", Contants.loanBusinessInfo);
				map.put("assure", Contants.assureInfo);
				map.put("house", Contants.houseInfo);
				map.put("guarantees",Contants.guranteeinfos);

                if(aiMap !=null&&aiMap.size()!=0){
					map.put("photoMap",aiMap);
				}
				String result[] = ProLoadApp.getmAidlClient().exec(Contants.header, gson.toJson(map));
				HeaderCommonResponse hr = null;
				if(result[1]!=null&&!"".equals(result[1])){
					hr = gson.fromJson(result[1], HeaderCommonResponse.class);
				}
				mHandler.obtainMessage(100, hr).sendToTarget();
			}
		}.start();
	}



	void upLoadNext(){
		if(!iterator.hasNext()){
			if(loaDialog != null&&loaDialog.isShowing()){
				loaDialog.dismiss();
			}
			submit();
			return;
		}
		while(iterator.hasNext()){
			curPhoto = iterator.next();
			File mFile = new File(curPhoto.getPhotoPath());
			if(mFile.exists()){
				MitFsRequest request=MitFsRequest.CreateUploadReqBean("GFMM",application.getmLoginResponse().getUserId(), PhoneInfoUtil.getMyUUID(ProLoadBusInfoActivity.this), BaseUtil.RamdomSerNO(), mFile);
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
				MitFsRequest request=MitFsRequest.CreateUploadReqBean("GFMM",application.getmLoginResponse().getUserId(), PhoneInfoUtil.getMyUUID(ProLoadBusInfoActivity.this), BaseUtil.RamdomSerNO(), response.getLocalFile());
				new UpLoadTask(request, application.getUploadClient(),this).start();
			}
		}

		@Override
		public void onError(MitFsException mitFsException, final MitFsRequest request) {
			failedTime ++;
			if(failedTime>3){
				failedTime=0;
				new SweetAlertDialog(ProLoadBusInfoActivity.this,SweetAlertDialog.NORMAL_TYPE).
						setTitleText(PhotoFactory.photo_Key_value.get(curPhoto.getPhotoTypeCode())+",存在照片多次上传出现异常\n是否继续上传")
				.setCancelText("忽略").setConfirmText("继续上传").setConfirmClickListener(new OnSweetClickListener() {
					@Override
					public void onClick(SweetAlertDialog sweetAlertDialog) {
						sweetAlertDialog.dismiss();
						upLoadNext();
					}
				}).setCancelClickListener(new OnSweetClickListener() {
					@Override
					public void onClick(SweetAlertDialog sweetAlertDialog) {
						sweetAlertDialog.dismiss();
						new UpLoadTask(request, application.getUploadClient(),new MyFileSocketHandle()).start();
					}
				}).show();
			}else {
				new UpLoadTask(request, application.getUploadClient(),this).start();
			}
		}
	}

	int failedTime;

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Contants.clear();
	}

	void deleteCaogao(String seqno){
		List<LoanRecord> records = ProLoadApp.getmDaoSession().queryBuilder(LoanRecord.class).
				where(LoanRecordDao.Properties.SeqNo.eq(seqno)).list();
		if(records.size()>0){
			ProLoadApp.getmDaoSession().delete(records.get(0));
		}

		List<LoanBusiness> loanBusinesses = ProLoadApp.getmDaoSession().queryBuilder(LoanBusiness.class).
				where(LoanBusinessDao.Properties.SeqNo.eq(seqno)).list();
		if(loanBusinesses.size()>0){
			ProLoadApp.getmDaoSession().delete(loanBusinesses.get(0));
		}

		List<Assure> assures = ProLoadApp.getmDaoSession().queryBuilder(Assure.class).
				where(AssureDao.Properties.SeqNo.eq(seqno)).list();
		if(assures.size()>0){
			ProLoadApp.getmDaoSession().delete(assures.get(0));
		}

		List<Guarantee> Guarantees = ProLoadApp.getmDaoSession().queryBuilder(Guarantee.class).
				where(GuaranteeDao.Properties.SeqNo.eq(seqno)).list();
		if(Guarantees.size()>0){
            ProLoadApp.getmDaoSession().getGuaranteeDao().deleteInTx(Guarantees);
		}

        List<PhotoEntity> photoEntities = ProLoadApp.getmDaoSession().queryBuilder(PhotoEntity.class)
                .where(PhotoEntityDao.Properties.SeqNo.eq(seqno)).list();
        if(photoEntities.size()>0){
            for(PhotoEntity photoEntity:photoEntities){
                String filepath  = photoEntity.getPhotoPath();
                File mFile = new File(filepath);
                if(mFile.exists()){
                    boolean deleted =mFile.delete();
                    Log.d("DELETEFILE",filepath + "is Deleted?" + deleted);
                }
            }
        }
	}


	class MyOnBroadCastReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			String x=intent.getAction();
			if("UserInfoLoaded".equals(x)){
                ToastCoustom.show("用户信息已经查询成功");
            }
		}
	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode){
			case KeyEvent.KEYCODE_BACK:
				new SweetAlertDialog(ProLoadBusInfoActivity.this,SweetAlertDialog.NORMAL_TYPE).setTitleText("退出贷款申请").setCancelText("取消")
						.setConfirmText("确认").setConfirmClickListener(new OnSweetClickListener() {
					@Override
					public void onClick(SweetAlertDialog sweetAlertDialog) {
						sweetAlertDialog.dismiss();
						finish();
					}
				}).show();
				break;
		}
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK&&data!=null){
			switch (requestCode){
				case 10001:
					procedure.onActivityResult(requestCode,resultCode,data);
					break;
			}
		}
	}
}
