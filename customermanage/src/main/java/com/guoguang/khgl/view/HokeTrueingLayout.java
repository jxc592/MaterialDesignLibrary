package com.guoguang.khgl.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.aidl.cilent.AidlClient;
import com.aidl.cilent.entity.CheckTrueingRequest;
import com.aidl.cilent.entity.CheckTrueingResponse;
import com.aidl.cilent.entity.HeaderCommonEntityRequest;
import com.aidl.cilent.entity.HeaderCommonResponse;
import com.aidl.cilent.entity.LoginResponse;
import com.aidl.cilent.entity.PersonInfo;
import com.aidl.cilent.library.sweetdialog.SweetAlertDialog;
import com.aidl.cilent.util.BaseUtil;
import com.aidl.cilent.util.BitmapUtil;
import com.aidl.cilent.util.DevicePluginsTool;
import com.aidl.cilent.util.ToastCoustom;
import com.aidl.cilent.util.TransationThread;
import com.aidl.cilent.util.VertificationIdCardUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guoguang.dkkhgl.R;
import com.guoguang.khgl.application.WincomApplication;
import com.guoguang.khgl.entity.CustomInfo;
import com.guoguang.khgl.model.Contants;
import com.guoguang.khgl.widget.MySpinnerAdapter;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HokeTrueingLayout extends BaseLayout {
	
	ImageView iv_responce;
	TextView tv_reid,tv_ResignOrgan,tv_checkResult,tv_ReName;
	Button btn_pass,btn_nopass,btn_hoke,btn_read;
	View view_pass,view_result;
	EditText et_idinput,et_custname;
	Spinner sp_certtype;
	String idNum,seqNo;
	
	SweetAlertDialog waitDialog;
	
	Gson gson;
	AidlClient mAidlClient;
	
	DevicePluginsTool devicePluginsTool;
	
	HeaderCommonEntityRequest header;
	CheckTrueingResponse response;
	LoginResponse loginResponse;
	
	boolean isChecked;
	
	boolean base_sup_ind = true;
	
	public HokeTrueingLayout(Context context, LoginResponse loginResponse2, AidlClient aidlClient) {
		super(context);
		this.loginResponse=loginResponse2;
		this.mAidlClient=aidlClient;
		createView();
	}
	
	public HokeTrueingLayout(Context context, LoginResponse loginResponse2, AidlClient aidlClient,boolean base_sup_ind) {
		super(context);
		this.loginResponse=loginResponse2;
		this.mAidlClient=aidlClient;
		this.base_sup_ind = base_sup_ind;
		createView();
	}
	
	
	private HokeTrueingLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
	}

	private HokeTrueingLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public HokeTrueingLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	PersonInfo personInfo;
	SweetAlertDialog reAlertDialog;
	Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if (reAlertDialog != null && reAlertDialog.isShowing()) {
				reAlertDialog.dismiss();
			}
			switch (msg.what) {
			// 读卡成功
				case 100:
					if (reAlertDialog != null && reAlertDialog.isShowing()) {
						reAlertDialog.dismiss();
					}
					String result = (String) msg.obj;
					HashMap<String, Object> hashMap = (HashMap<String, Object>) BaseUtil
							.JsonStrToMap(result);
					if (hashMap.get("result") == null
							|| "".equals(hashMap.get("result"))
							|| (boolean) hashMap.get("result") == false) {
						ToastCoustom.show("读取身份证信息失败");
					} else {
						try {
							personInfo = new GsonBuilder().create().fromJson(
									result, PersonInfo.class);
						} catch (Exception e) {
							ToastCoustom.show("解析身份证信息失败");
						}
					}
					if (personInfo != null) {
						//readTextView.setText(personInfo.getNumber());
						et_custname.setText(personInfo.getName());
						et_idinput.setText(personInfo.getNumber());

					} else {
						ToastCoustom.show("读取身份证信息失败");
					}
					break;
				case 200:
					ToastCoustom.show("查询到客户信息存在");
					paractivity.refresh();
					break;
				case 110:
					String errerinfo = (String) msg.obj;
					SweetAlertDialog dialog = new SweetAlertDialog(getContext(),SweetAlertDialog.NORMAL_TYPE).setTitleText(errerinfo);
					dialog.setTitleText(errerinfo).setCancelable(false);
					dialog.setConfirmText("确认");
					dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
						@Override
						public void onClick(SweetAlertDialog sweetAlertDialog) {
							sweetAlertDialog.dismiss();
							et_idinput.setText("");
							et_custname.setText("");
							tv_checkResult.setText("");
							tv_reid.setText("");
							tv_ReName.setText("");
							tv_ResignOrgan.setText("");
							iv_responce.setImageDrawable(new BitmapDrawable());
							isChecked = false;
						}
					});
					dialog.show();
					break;
				case 120:
					String errerinfo120 = (String) msg.obj;
					new SweetAlertDialog(getContext(),SweetAlertDialog.NORMAL_TYPE).setTitleText(errerinfo120).show();
					break;
				case 130:
					paractivity.setEditMode(false);
					new SweetAlertDialog(getContext(),SweetAlertDialog.NORMAL_TYPE).
							setTitleText("你对该客户没有维护权请注意！").show();
					break;
				case 150:
					paractivity.setEditMode(true);
					break;
			}
		};
	};
	public void createView() {
		// TODO Auto-generated method stub
		LayoutInflater.from(getContext()).inflate(R.layout.fragment_hoke_trueing2, this);
		//((FrameLayout)getParent()).setBackgroundResource(R.drawable.dialog_bg);
		et_idinput = (EditText) findViewById(R.id.et_idinput);
		et_custname = (EditText) findViewById(R.id.et_custname);
		btn_hoke= (Button) findViewById(R.id.btn_hoke);
		btn_read = (Button) findViewById(R.id.btn_hoke2check);
		view_pass = findViewById(R.id.ll_passlayout);
		view_result=findViewById(R.id.ll_result);
		
		ll_resut = (LinearLayout) findViewById(R.id.layout_res);
		remind = (LinearLayout) findViewById(R.id.layout_mind);
		
		btn_nopass = (Button) findViewById(R.id.btn_nopassof2);
		btn_pass=(Button) findViewById(R.id.btn_passof2);
		
		iv_responce=(ImageView) findViewById(R.id.iv_photoresponce);
		
		tv_ReName = (TextView) findViewById(R.id.tv_ReName);
		tv_reid=(TextView) findViewById(R.id.tv_Reid);
		tv_ResignOrgan=(TextView) findViewById(R.id.tv_ResignOrgan);
		tv_checkResult=(TextView) findViewById(R.id.tv_checkResult);
		sp_certtype=(Spinner) findViewById(R.id.sp_certtype);
		
		sp_certtype.setAdapter(new MySpinnerAdapter(R.array.cert_type, getContext()));
		sp_certtype.setEnabled(false);
		
		btn_hoke.setOnClickListener(new MyOnClick());
		btn_read .setOnClickListener(new MyOnClick());
		
		gson = new GsonBuilder().create();
		header = new HeaderCommonEntityRequest();
		header.setFlag("0");
		header.setEncryptInd("0");
		header.setUserId(loginResponse.getUserId());
		header.setSdSysId("0000");
		header.setRcvSysId("0002");
	}
	void doCheck(final CheckTrueingRequest request){
		setResultInvisable();
		int numcode = (int) ((Math.random() * 9 + 1) * 100000);
		String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		seqNo = date + numcode;
		header.setSeqNo(seqNo);
		new TransationThread<CheckTrueingRequest, CheckTrueingResponse>(getContext(),mAidlClient,header,request, TransationThread.TYPE_RESULTMAP,CheckTrueingResponse.class, "正在联网核查") {
			@Override
			public void onSuccess(CheckTrueingResponse body) {
				// TODO Auto-generated method stub
				response  = body;
				if(reAlertDialog != null && reAlertDialog.isShowing()){
					reAlertDialog.dismiss();
				}
				reAlertDialog = new SweetAlertDialog(getContext(),SweetAlertDialog.PROGRESS_TYPE);
				reAlertDialog.setTitleText("正在查询客户信息");
				reAlertDialog.show();
				new Thread(){
					@Override
					public void run() {
						super.run();
						HashMap<String,String> map = new HashMap<String, String>();
						map.put("CertID",request.getID());
						map.put("CertType", "Ind01");
						map.put("TransCode","1210");
						String result[] = WincomApplication.getmAidlClient().exec(Contants.header,gson.toJson(map));
						mHandler.sendEmptyMessage(100021312);
						HeaderCommonResponse res = gson.fromJson(result[1], HeaderCommonResponse.class);
						if("0000".equals(res.getResultCode())){
							if(result[2]!=null&&!"".equals(result[2])){
								Map<String,Object> map1 =BaseUtil.JsonStrToMap(result[2]);
								JSONObject jo = (JSONObject) map1.get("CustomerInfo");
								CustomInfo customInfo = gson.fromJson(jo.toString(), CustomInfo.class);
								mHandler.obtainMessage(200).sendToTarget();
								Contants.tempCustomInfo = customInfo;
								if("2".equals(customInfo.getHasUpdatePermision())){

									mHandler.sendEmptyMessage(130);
								}else{
									mHandler.sendEmptyMessage(150);
								}
							}
						}else if("1111".equals(res.getResultCode())){
							//TODO 客户不存在：
							Contants.tempCustomInfo = null;
						}else if("2222".equals(res.getResultCode())){
							//TODO 存量客户
							mHandler.obtainMessage(110,"您对该客户没有查看权").sendToTarget();
						}else {
							//TODO 客户查询失败
							mHandler.obtainMessage(120,res.getMessage()).sendToTarget();
						}
					}
				}.start();
				initResponce();
			}
		}.start();
	}
	
	LinearLayout remind ,ll_resut;
	public void setResultShow() {
		remind.setVisibility(View.INVISIBLE);
		ll_resut.setVisibility(View.VISIBLE);
	}
	public void setResultInvisable() {
		remind.setVisibility(View.VISIBLE);
		ll_resut.setVisibility(View.INVISIBLE);
	}
	
	boolean debug=false;
	@Override
	public boolean getData() {
		// TODO Auto-generated method stub
		String id_no;
		String full_name;
		String title_id;
		String birth;
		String sex;
		if(debug){
			id_no = et_idinput.getText().toString();
			full_name = et_custname.getText().toString();
			title_id="";
			birth="";
			sex="";
		}else{
			if(!isChecked){
				ToastCoustom.show("尚未核查通过");
				return false;
			}
			if(response==null){
				ToastCoustom.show("联网核查反馈结果为空");
				return false;
			}
			id_no = response.getID();
			full_name = response.getName();
			title_id="";
			birth="";
			sex="";
			if(id_no.length()==18){
				birth = id_no.substring(6, 14);
				sex = id_no.substring(16, 17);
				if(Integer.parseInt(sex)%2==0){
					sex="F";
					title_id="MRS";
				}else {
					sex="M";
					title_id="MR";
				}
			}else if(id_no.length()==15){
				birth ="19" + id_no.substring(6, 12);
				sex = id_no.substring(14, 15);
				if(Integer.parseInt(sex)%2==0){
					sex="F";
					title_id="MRS";
				}else {
					sex="M";
					title_id="MR";
				}
			}
		}

		CustomInfo mCustomInfo = Contants.tempCustomInfo;
		if(mCustomInfo == null){
			mCustomInfo=new CustomInfo();
		}
		mCustomInfo.setCertType("Ind01");
		mCustomInfo.setCertID(id_no);
		mCustomInfo.setBirthday(birth);
		mCustomInfo.setFullName(full_name);
		Contants.tempCustomInfo = mCustomInfo;
		//parActy.setData(applyInfo);
		return true;
	}
	private void initResponce() {
		view_result.setVisibility(View.VISIBLE);
		view_pass.setVisibility(View.VISIBLE);
		if(response.getCheckResult()!=null&&"00".equals(response.getCheckResult())){
			isChecked=true;
			btn_pass.setEnabled(false);
			btn_nopass.setEnabled(false);
			btn_pass.setVisibility(View.INVISIBLE);
			btn_nopass.setVisibility(View.INVISIBLE);
			ToastCoustom.show("核查通过请继续录入信息");
		}else if ("01".equals(response.getCheckResult())) {
			isChecked=true;
			btn_pass.setVisibility(View.VISIBLE);
			btn_nopass.setVisibility(View.VISIBLE);
			btn_pass.setEnabled(true);
			btn_nopass.setEnabled(true);
			ToastCoustom.show("请手动选择是否通过");
		}else{
			isChecked = false;
			btn_pass.setVisibility(View.INVISIBLE);
			btn_nopass.setVisibility(View.INVISIBLE);
			btn_pass.setEnabled(false);
			btn_nopass.setEnabled(false);
			new SweetAlertDialog(getContext(), SweetAlertDialog.NORMAL_TYPE).setTitleText("核查不通过请重新输入证件号码").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
				@Override
				public void onClick(SweetAlertDialog sweetAlertDialog) {
					// TODO Auto-generated method stub
					sweetAlertDialog.dismiss();
					et_idinput.setText("");
					et_idinput.setHint("请重新输入证件号码");
				}
			}).show();
		}
		setResultShow();
		if(response.getName()!=null){
			tv_ReName.setText(response.getName());
		}
		if(response.getCheckResultName()!=null){
			tv_checkResult.setText(response.getCheckResultName());		
		}
		if(response.getID()!=null){
			tv_reid.setText(response.getID());
		}
		if(response.getIssueOffice()!=null){
			tv_ResignOrgan.setText(response.getIssueOffice());
		}
		if(response.getPhoto()!=null){
			try{
				iv_responce.setImageBitmap(BitmapUtil.base64toBitmap2(response.getPhoto()));
			}
			catch (Exception e) {
				// TODO: handle exception
				ToastCoustom.show("读取图片出错");
			}
		}
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveData() {
		// TODO Auto-generated method stub
		
	}
	
	class MyOnClick implements OnClickListener{
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_hoke:
				response = null;
				isChecked = false;
				view_pass.setVisibility(View.INVISIBLE);
				view_result.setVisibility(View.INVISIBLE);
				idNum=et_idinput.getText().toString();
				String name = et_custname.getText().toString();
				if("".equals(name)){
					ToastCoustom.show("姓名不能为空");
					return;
				}
				if(!new VertificationIdCardUtil().verify(idNum)){
					ToastCoustom.show("您证件号输入有误");
					return;
				}
				
				CheckTrueingRequest request=new CheckTrueingRequest();
				request.setID(idNum);
				request.setName(name);
				request.setIDType("01");
				request.setCheckPhoto("1");
				if (personInfo != null) {
					if (personInfo.getAddress() != null) {
						request.setAddress(personInfo.getAddress());
					}
					if (personInfo.getName() != null) {
						request.setName(personInfo.getName());
					}
					if (personInfo.getSex() != null) {
						request.setGender(personInfo.getSex());
					}
					if (personInfo.getStartDate() != null) {
						Date taDate;
						try {
							taDate = new SimpleDateFormat("yyyy:MM:dd")
									.parse(personInfo.getStartDate());
							String dateString = new SimpleDateFormat("yyyyMMdd")
									.format(taDate);
							request.setStartIndate(dateString);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					if (personInfo.getEndDate() != null) {
						try {
							Date taDate = new SimpleDateFormat("yyyy:MM:dd")
									.parse(personInfo.getEndDate());
							String dateString = new SimpleDateFormat("yyyyMMdd")
									.format(taDate);
							request.setEndIndate(dateString);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (personInfo.getIssueDepartmanet() != null) {
						request.setIssueOffice(personInfo.getIssueDepartmanet());
					}
					if (personInfo.getPhoto() != null) {
						request.setPhoto(personInfo.getPhoto());
					} else {
						request.setPhoto("01");
					}
					// request.setPhoto("01");
					request.setCheckPhoto("1");
					if (personInfo.getNumber() != null) {
						request.setID(personInfo.getNumber());
					}
				}
				personInfo = null;
				doCheck(request);
				break;
			case R.id.btn_hoke2check:
//				if(devicePluginsTool==null){
//					devicePluginsTool=((CreditApplication)parActy.getApplication()).getDevicePluginsTool();
//					devicePluginsTool.setmContext(getContext());
//				}
//				if(devicePluginsTool.isContectDevice()){
//					devicePluginsTool.readIdCard(mHandler, 100, getContext());
//				}
				break;
			default:
				break;
			}
			
		}
	}

	@Override
	View onCreateView() {
		// TODO Auto-generated method stub
		return null;
	}

}
