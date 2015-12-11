package com.guoguang.khgl.commonview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;

import com.aidl.cilent.entity.HeaderCommonResponse;
import com.aidl.cilent.library.sweetdialog.SweetAlertDialog;
import com.aidl.cilent.util.BaseUtil;
import com.aidl.cilent.util.ToastCoustom;
import com.aidl.cilent.util.VertificationIdCardUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guoguang.dkkhgl.R;
import com.guoguang.khgl.application.WincomApplication;
import com.guoguang.khgl.entity.CustomInfo;
import com.guoguang.khgl.entity.CustomRelaInfo;
import com.guoguang.khgl.model.Contants;
import com.guoguang.khgl.model.DateStrFormUtil;
import com.guoguang.khgl.widget.AutoLoadSpinner;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class AddFamilyinfoDialog extends Dialog {

	public AddFamilyinfoDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	 public AddFamilyinfoDialog(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
	}
	 
	 public AddFamilyinfoDialog(Context context, int theme ,CustomRelaInfo customRelaInfo) {
			super(context, theme);
			// TODO Auto-generated constructor stub
			this.customRelaInfo =customRelaInfo;
	}

    AutoLoadSpinner sp_CertType;
    EditText et_CertID;
    EditText et_Birthday;
    EditText et_FullName;
    EditText et_Age;
    AutoLoadSpinner sp_EduExperience;
    AutoLoadSpinner sp_RelationShip;
    EditText et_MonthIncome;
    EditText et_MobileTelephone;
    EditText et_WorkCorp;
    AutoLoadSpinner sp_EffStatus;

    Button sure, cancel;

    Gson gson;
    //用于初始化数据的临时变量
    CustomRelaInfo customRelaInfo;

    //用于记录客户家属信息的变量
    CustomRelaInfo mCustomRelaInfo = null;

    String tempCertID="";

    CustomInfo mCustomeInfo = null;
    //两种复杂的逻辑 ，1、信息是不是回显。 2、客户是不是新增

    boolean isExit = false;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_familyinfo);
        gson = new GsonBuilder().create();
		sp_CertType = (AutoLoadSpinner) findViewById(R.id.sp_CertType);
		et_CertID = (EditText) findViewById(R.id.et_CertID);
		et_Birthday = (EditText) findViewById(R.id.et_Birthday);
		et_FullName= (EditText) findViewById(R.id.et_CustomerName);
		et_Age= (EditText) findViewById(R.id.et_Age);
		sp_EduExperience= (AutoLoadSpinner) findViewById(R.id.sp_EduExperience);
		sp_RelationShip= (AutoLoadSpinner) findViewById(R.id.sp_RelationShip);
		et_MonthIncome= (EditText) findViewById(R.id.et_MonthIncome);
		et_MobileTelephone= (EditText) findViewById(R.id.et_MobileTelephone);
		sp_EffStatus  = (AutoLoadSpinner) findViewById(R.id.sp_EffStatus);
        et_WorkCorp = (EditText) findViewById(R.id.et_WorkCorp);

        //默认值设置
        sp_EffStatus.setCode("1");
        sp_RelationShip.setCode("0301");
        sp_EduExperience.setCode("20");
        sp_CertType.setCode("Ind01");

        if(customRelaInfo!=null){
            isExit = true;
            getWindow().getDecorView().postDelayed(new Runnable() {
                @Override
                public void run() {
                    search();
                }
            },200);
        }
		if(customRelaInfo != null){
            //信息时回显的
            //为家庭成员的信息赋值
            mCustomRelaInfo = customRelaInfo;
            sp_CertType.setCode(customRelaInfo.getCertType());
			et_CertID.setText(customRelaInfo.getCertID());
			et_Birthday.setText(customRelaInfo.getBirthday());
			et_FullName .setText(customRelaInfo.getCustomerName());
			et_Age.setText(customRelaInfo.getAge());
			sp_EduExperience.setCode(customRelaInfo.getEduExperience());
			sp_RelationShip.setCode(customRelaInfo.getRelationShip());
			sp_EffStatus.setCode(customRelaInfo.getEffStatus());
			et_MobileTelephone.setText(customRelaInfo.getMobileTelephone());
			et_MonthIncome.setText(customRelaInfo.getMonthIncome());
            et_WorkCorp.setText(customRelaInfo.getWorkCorp());
		}

		sure= (Button) findViewById(R.id.btn_addf_sure);
		cancel = (Button) findViewById(R.id.btn_addf_cancel);

		sure.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String CertType  =sp_CertType.getSelectedCode();
				String FullName =et_FullName.getText().toString();
				String Age = et_Age.getText().toString();
				String EduExperience = sp_EduExperience.getSelectedCode();
				String RelationShip = sp_RelationShip.getSelectedCode();
				String MonthIncome =et_MonthIncome.getText().toString();
				String MobileTelephone = et_MobileTelephone.getText().toString();
				String EffStatus =sp_EffStatus.getSelectedCode();
				String Birthday = et_Birthday.getText().toString();
				final String certIdString=et_CertID.getText().toString();
                String workCorp = et_WorkCorp.getText().toString();


				if("".equals(CertType)){
					ToastCoustom.show("证件类型为空");
					return;
				}
				if("".equals(certIdString)){
					ToastCoustom.show("证件号码不能为空");
					return;
				}else {
                    if(!new VertificationIdCardUtil().verify(certIdString)){
                        ToastCoustom.show("证件号码输入有误");
                        return;
                    }
                }
				if("".equals(Birthday)){
					ToastCoustom.show("出生日期不能为空");
					return;
				}
				if("".equals(FullName)){
					ToastCoustom.show("姓名不能为空");
					return;
				}
				if("".equals(Age)){
					ToastCoustom.show("年龄不能为空");
					return;
				}
				if("".equals(RelationShip)){
					ToastCoustom.show("与客户关系不能为空");
					return;
				}
				if("".equals(MonthIncome)){
					ToastCoustom.show("月收入不能为空");
					return ;
				}
				if("".equals(MobileTelephone)){
					ToastCoustom.show("手机号码不能为空");
					return ;
				}else {
					if(MobileTelephone.length()!=11){
						ToastCoustom.show("手机号码应为11位");
						return;
					}
				}

				if("".equals(EffStatus)){
					ToastCoustom.show("是否有效不能为空");
					return ;
				}
                //以下是点击确定按钮的 事件处理
                //若是不存在这个信息说明是 第一次登记 ，要记录一些登记人登记机构，以及登记时间等基本信息
                // 也可以根据是否为null来判断
                if(mCustomRelaInfo == null){
                    mCustomRelaInfo = new CustomRelaInfo();
                    mCustomRelaInfo.setInputDate(BaseUtil.formatDate());

                    mCustomRelaInfo.setUserName(Contants.registInfo.getInputUserName());
                    mCustomRelaInfo.setOrgName(Contants.registInfo.getInputOrgName());
                    mCustomRelaInfo.setInputUserId(Contants.registInfo.getInputUserID());
                    mCustomRelaInfo.setInputOrgId(Contants.registInfo.getInputOrgID());
                }
                //界面信息的设置
                mCustomRelaInfo.setCertType(CertType);
                mCustomRelaInfo.setCertID(certIdString);
                mCustomRelaInfo.setAge(Age);
                mCustomRelaInfo.setBirthday(Birthday);
                mCustomRelaInfo.setMonthIncome(MonthIncome);
                mCustomRelaInfo.setMobileTelephone(MobileTelephone);
                mCustomRelaInfo.setRelationShip(RelationShip);
                mCustomRelaInfo.setEffStatus(EffStatus);
                mCustomRelaInfo.setCustomerName(FullName);
                mCustomRelaInfo.setEduExperience(EduExperience);
                mCustomRelaInfo.setUpdateDate(BaseUtil.formatDate());
                mCustomRelaInfo.setWorkCorp(workCorp);
                //这里是处理 在本系统不存在的客户 登记人信息
                mCustomRelaInfo.setInputOrgId(Contants.registInfo.getInputOrgID());
                mCustomRelaInfo.setInputUserId(Contants.registInfo.getInputUserID());
                mCustomRelaInfo.setOrgName(Contants.registInfo.getInputOrgName());
                mCustomRelaInfo.setUserName(Contants.registInfo.getInputUserName());
                if(!isExit) {
                    if(canInsert){
                        //客户不存在新增。
                        showDialog("正在保存客户信息");
                        if(mCustomeInfo == null){
                            mCustomeInfo = new CustomInfo();
                        }
                        String Sex="";
                        if(certIdString.length()==18){
                            Sex = certIdString.substring(16, 17);
                        }else if(certIdString.length()==15){
                            Sex =  certIdString.substring(14, 15);
                        }
                        int intSex = Integer.parseInt(Sex);
                        if(intSex%2 == 0){
                            mCustomeInfo.setSex("2");
                        }else {
                            mCustomeInfo.setSex("2");
                        }
                        mCustomeInfo.setFullName(FullName);
                        mCustomeInfo.setAge(Age);
                        mCustomeInfo.setCertID(certIdString);
                        mCustomeInfo.setCertType(CertType);
                        mCustomeInfo.setInputOrgID(Contants.registInfo.getInputOrgID());
                        mCustomeInfo.setInputUserID(Contants.registInfo.getInputUserID());
                        mCustomeInfo.setBirthday(Birthday);
                        mCustomeInfo.setMonthIncome(MonthIncome);
                        mCustomeInfo.setMobileTelephone(MobileTelephone);
                        mCustomeInfo.setEduExperience(EduExperience);
                        mCustomeInfo.setSeqNo(BaseUtil.RamdomSerNO());
                        mCustomeInfo.setUpdateDate(BaseUtil.formatDate());
                        mCustomeInfo.setInputDate(BaseUtil.formatDate());
                        new Thread() {
                            public void run() {
                                HashMap<String, String> map = new HashMap<>();
                                map.put("TransCode", "1029");
                                String testBody = gson.toJson(map);
                                String test[] = WincomApplication.getmAidlClient().exec(Contants.header, testBody);
                                HeaderCommonResponse response = gson.fromJson(test[1], HeaderCommonResponse.class);
                                if ("0000".equals(response.getResultCode())) {
                                    Map<String, Object> map1 = new HashMap<>();
                                    map1.put("CusInfo", mCustomeInfo);
                                    map1.put("TransCode", "1218");
                                    map1.put("seqNo", BaseUtil.RamdomSerNO());
                                    String body = gson.toJson(map1);
                                    String result[] = WincomApplication.getmAidlClient().exec(Contants.header, body);
                                    HeaderCommonResponse res = gson.fromJson(result[1], HeaderCommonResponse.class);
                                    if ("0000".equals(res.getResultCode())) {
                                        Map<String, Object> rsbody = BaseUtil.JsonStrToMap(result[2]);
                                        rsbody.put("message", res.getMessage());
                                        mHandler.obtainMessage(1, rsbody).sendToTarget();
                                    } else {
                                        mHandler.obtainMessage(10002, res.getMessage()).sendToTarget();
                                    }
                                } else {
                                    mHandler.obtainMessage(10002, "与服务器断开连接").sendToTarget();
                                }
                            }
                        }.start();
                    }else {
                        //客户存在：
                        if(mCustomeInfo!=null&&!"".equals(mCustomeInfo.getCustomerID())){
                            mCustomRelaInfo.setRelativeID(mCustomeInfo.getCustomerID());
                            if(onClickListener!=null){
                                onClickListener.onClick(AddFamilyinfoDialog.this,mCustomRelaInfo);
                            }
                        }else {
                            //ToastCoustom.show("请检查证件");
                            ToastCoustom.show("查询客户信息异常没有客户编号");
                        }

                    }

                }else {
                    if(onClickListener!=null){
                        onClickListener.onClick(AddFamilyinfoDialog.this,mCustomRelaInfo);
                    }
                }

			}
		});
		cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AddFamilyinfoDialog.this.dismiss();
			}
		});

		sp_CertType.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
									   int position, long id) {
				// TODO Auto-generated method stub
				String CertType = sp_CertType.getSelectedCode();
				if ("Ind01".equals(CertType)) {
					et_Birthday.setEnabled(false);
					et_Age.setEnabled(false);
				} else {
					et_Birthday.setEnabled(true);
					et_Age.setEnabled(true);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		et_CertID.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View view, boolean b) {
				if(!b){
                    String certid = et_CertID.getText().toString();
                    String certtype = sp_CertType.getSelectedCode();
                    mCustomeInfo = null;
                    if(!new VertificationIdCardUtil().verify(certid)){
                        ToastCoustom.show("证号号码输入有误");
                        return;
                    }
                    search(certid,certtype);
					int length = certid.length();
					if(length==15||length == 18){
						String Sex ="";
						String Birthday = "";
						String Age = "";
						Birthday = certid.toString().substring(6, 14);
						Age = certid.toString().substring(6, 10);
                        Birthday = DateStrFormUtil.formatDateString(Birthday);
                        et_Birthday.setText(Birthday);
						String toyear = new SimpleDateFormat("yyyy").format(new Date());
						int intAge = Integer.parseInt(toyear)  -  Integer.parseInt(Age);
						Age = intAge+"";
						et_Age.setText(Age);
					}
				}
			}
		});

	}

    boolean canInsert = true;


    //重置权限信息
    void resetOkDate(){
        canInsert = true;
    }

    void search(){
        et_CertID.setEnabled(false);
        sp_CertType.setEnabled(false);
        tempCertID = customRelaInfo.getCertID();
        search(customRelaInfo.getCertID(),customRelaInfo.getCertType());
    }

    void search(String id,String certtype){

        resetOkDate();
        showDialog("正在查询客户信息是否存在");
        final HashMap<String,String> map  = new HashMap<String, String>();
        map.put("TransCode","1217");
        map.put("CertID",id);
        map.put("CertType", certtype);
        new Thread(){
            @Override
            public void run() {
                super.run();
                String result[] = WincomApplication.getmAidlClient().exec(Contants.header, gson.toJson(map));
                HeaderCommonResponse res = gson.fromJson(result[1], HeaderCommonResponse.class);
                if("0000".equals(res.getResultCode())){
                    if(result[2]!=null&&!"".equals(result[2])){
                        Map<String,Object> map1 =BaseUtil.JsonStrToMap(result[2]);
                        JSONObject jo = (JSONObject) map1.get("CustomerInfo");
                        mCustomeInfo = gson.fromJson(jo.toString(), CustomInfo.class);
                        mHandler.sendEmptyMessage(200);
                    }
                }
//                else if("1111".equals(res.getResultCode())){
//                    //TODO 客户不存在：
//                    mHandler.sendEmptyMessage(130);
//                }else if("2222".equals(res.getResultCode())){
//                    //TODO 存量客户
//                    mHandler.obtainMessage(110,"您对该客户没有查看权").sendToTarget();
//                }
                else {
                    //TODO 客户查询失败
                    mHandler.obtainMessage(120,res.getMessage()).sendToTarget();
                }
            }
        }.start();
    }


    void showDialog(String msg){
        if(loadDialog!=null&&loadDialog.isShowing()){
            loadDialog.dismiss();
        }
        loadDialog = new SweetAlertDialog(getContext(),SweetAlertDialog.PROGRESS_TYPE).setTitleText(msg);
        loadDialog.show();
    }
    SweetAlertDialog loadDialog ;
   android.os.Handler mHandler = new android.os.Handler(){
       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);
           if(loadDialog !=null&& loadDialog.isShowing()){
               loadDialog.dismiss();
           }
           switch (msg.what){
               case 1:
                   Map<String ,Object> map = (Map<String, Object>) msg.obj;
                   String customerId = (String) map.get("CustomerId");
                   mCustomRelaInfo.setRelativeID(customerId);
                   if(onClickListener!=null){
                       onClickListener.onClick(AddFamilyinfoDialog.this, mCustomRelaInfo);
                   }else {
                       AddFamilyinfoDialog.this.dismiss();
                   }
                   break;
               case 10002:
                   String message = (String) msg.obj;
                   ToastCoustom.show(message);
                   break;
               //查询到客户信息
               case 200:
                   if(!"".equals(mCustomeInfo.getCustomerID())){
                        canInsert = false;
                   }else {
                        canInsert = true;
                   }
                   setDigUi(mCustomeInfo);
                   break;
               ///客户查询失败。
               case 120:
                   canInsert = true;
                   break;
               //110存量客户无查看权。
               case 110:
                   canInsert = false;
                   break;
               //无该客户信息
               case 130:
                   canInsert = true;
                   break;
           }
       }
   };

	public  interface OnClickListener  {
		void onClick(Dialog dialog, CustomRelaInfo customRelaInfo);
	}
	
	OnClickListener onClickListener;
	public OnClickListener getOnClickListener() {
		return onClickListener;
	}

	public AddFamilyinfoDialog setOnClickListener(OnClickListener onClickListener) {
		this.onClickListener = onClickListener;
		return this;
	}

    void setDigUi(CustomInfo customerinfo){
        //客户是存在的
        et_FullName.setText(customerinfo.getFullName());
        et_MobileTelephone.setText(customerinfo.getMobileTelephone());
        et_MonthIncome.setText(customerinfo.getMonthIncome());
        sp_EduExperience.setCode(customerinfo.getEduExperience());
        et_WorkCorp.setText(customerinfo.getWorkCorp());
        //初始化工作
        if(mCustomRelaInfo == null){
            mCustomRelaInfo = new CustomRelaInfo();

            mCustomRelaInfo.setInputDate(BaseUtil.formatDate());
            mCustomRelaInfo.setInputUserId(customerinfo.getInputUserID());
            mCustomRelaInfo.setUserName(customerinfo.getUserName());
            mCustomRelaInfo.setInputOrgId(customerinfo.getInputOrgID());
            mCustomRelaInfo.setOrgName(customerinfo.getOrgName());
        }

        mCustomRelaInfo.setCustomerID(Contants.tempCustomInfo.getCustomerID());
        mCustomRelaInfo.setRelativeID(customerinfo.getCustomerID());
        mCustomRelaInfo.setCertID(customerinfo.getCertID());
    }
	
}
