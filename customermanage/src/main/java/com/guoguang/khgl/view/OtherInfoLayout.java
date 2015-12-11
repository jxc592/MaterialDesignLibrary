package com.guoguang.khgl.view;


import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import com.aidl.cilent.entity.HeaderCommonResponse;
import com.aidl.cilent.util.BaseUtil;
import com.aidl.cilent.util.ToastCoustom;
import com.aidl.cilent.util.VertificationIdCardUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guoguang.dkkhgl.R;
import com.guoguang.khgl.application.WincomApplication;
import com.guoguang.khgl.entity.CustomInfo;
import com.guoguang.khgl.model.Contants;
import com.guoguang.khgl.widget.AutoLoadSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OtherInfoLayout extends BaseLayout {
//	BillForm	,//	账单形式
//	HasRecommendor	,//	是否有推荐人
//	recommendorCertType	,//	推荐人证件类型
//	recommendorCertID	,//	推荐人证件号码
//	recommendor	,//	推荐人名称
//	recommendorMainCustomerID	,//	推荐人核心客户号
//	recommendScale	,//	推荐人提成比例
//	Remark	,//	备注
//	ManageUserName	,//	管户人
//	ManageOrgName	,//	管户机构
//	InputUserID	,//	登记人
//	UserName	,//	登记人

    AutoLoadSpinner sp_HasRecommendor,//	是否有推荐人
            sp_recommendorCertType;//	推荐人证件类型
    EditText et_recommendorCertID,//	推荐人证件号码
            et_recommendor,//	推荐人名称
            et_recommendorMainCustomerID,//	推荐人核心客户号
            et_recommendScale,//	推荐人提成比例
            et_Remark,//	备注
            et_ManageUserName,//	管户人
            et_ManageOrgName,//	管户机构
            et_InputUserName,//	登记人
            UserName;//	登记人


    public OtherInfoLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public OtherInfoLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public OtherInfoLayout(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    Gson gson = new GsonBuilder().create();

    @Override
    View onCreateView() {
        // TODO Auto-generated method stub
        inflater.inflate(R.layout.view_businfo, this);

        sp_HasRecommendor = (AutoLoadSpinner) findViewById(R.id.sp_HasRecommendor);//	是否有推荐人
        sp_recommendorCertType = (AutoLoadSpinner) findViewById(R.id.sp_recommendorCertType);//	推荐人证件类型
        et_recommendorCertID = (EditText) findViewById(R.id.et_recommendorCertID);//	推荐人证件号码
        et_recommendor = (EditText) findViewById(R.id.et_recommendor);//	推荐人名称
        et_recommendorMainCustomerID = (EditText) findViewById(R.id.et_recommendorMainCustomerID);//	推荐人核心客户号
        et_recommendScale = (EditText) findViewById(R.id.et_recommendScale);//	推荐人提成比例
        et_Remark = (EditText) findViewById(R.id.et_Remark);//	备注
        et_ManageUserName = (EditText) findViewById(R.id.et_ManageUserName);//	管户人
        et_ManageOrgName = (EditText) findViewById(R.id.et_ManageOrgName);//	管户机构
        et_InputUserName = (EditText) findViewById(R.id.et_InputUserName);//	登记人
        //UserName	,//	登记人
        sp_HasRecommendor.setCode("2");
        sp_HasRecommendor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String hasrecormer = sp_HasRecommendor.getSelectedCode();
                if ("2".equals(hasrecormer)) {
                    sp_recommendorCertType.setCode("");
                    sp_recommendorCertType.setEnabled(false);
                    et_recommendor.setEnabled(false);
                    et_recommendScale.setEnabled(false);
                    et_recommendorMainCustomerID.setEnabled(false);
                    et_recommendorCertID.setEnabled(false);
                } else {
                    sp_recommendorCertType.setCode("Ind01");
                    sp_recommendorCertType.setEnabled(true);
                    et_recommendor.setEnabled(true);
                    et_recommendScale.setEnabled(true);
                    et_recommendorMainCustomerID.setEnabled(true);
                    et_recommendorCertID.setEnabled(true);
                    et_recommendScale.setText("50");
                    String certid = et_recommendorCertID.getText().toString();
                    if (new VertificationIdCardUtil().verify(certid)) {
                        search(certid);
                    } else {
                        ToastCoustom.show("证件号码不正确");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        et_recommendorCertID.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    String certid = et_recommendorCertID.getText().toString();
                    if (new VertificationIdCardUtil().verify(certid)) {
                        search(certid);
                    } else {
                        ToastCoustom.show("证件号码不正确");
                    }
                }
            }
        });
        return null;

    }

    void search(final String certid) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                HashMap<String, String> map = new HashMap<>();
                map.put("TransCode", "1294");
                map.put("CertId", certid);
                String result[] = WincomApplication.getmAidlClient().exec(Contants.header, gson.toJson(map));
                mHandler.obtainMessage(1001).sendToTarget();
                HeaderCommonResponse res = gson.fromJson(result[1], HeaderCommonResponse.class);
                if ("0000".equals(res.getResultCode())) {
                    if (result[2] != null && !"".equals(result[2])) {
                        mHandler.obtainMessage(100, result[2]).sendToTarget();
                    }
                } else {
                    mHandler.obtainMessage(101, result[1]).sendToTarget();
                }
            }
        }.start();
    }

    @Override
    public boolean getData() {
        // TODO Auto-generated method stub
        String HasRecommendor = sp_HasRecommendor.getSelectedCode(),//	是否有推荐人
        recommendorCertType = sp_recommendorCertType.getSelectedCode(),//	推荐人证件类型
        recommendorCertID = et_recommendorCertID.getText().toString(),//	推荐人证件号码
        recommendor = et_recommendor.getText().toString(),//	推荐人名称
        recommendorMainCustomerID = et_recommendorMainCustomerID.getText().toString(),//	推荐人核心客户号
        recommendScale = et_recommendScale.getText().toString(),//	推荐人提成比例
                Remark = et_Remark.getText().toString(),//	备注
                ManageUserName = et_ManageUserName.getText().toString(),//	管户人
                ManageOrgName = et_ManageOrgName.getText().toString(),//	管户机构
                InputUserName = et_InputUserName.getText().toString();//	登记人
//		UserName	,//	登记人

        if("1".equals(HasRecommendor)){
            if("".equals(recommendorCertID)){
                ToastCoustom.show("推荐人信息没有填写");
                return false;
            }
        }

        CustomInfo mCustomInfo = Contants.tempCustomInfo;
        if (mCustomInfo == null) {
            mCustomInfo = new CustomInfo();
        }
        mCustomInfo.setHasRecommendor(HasRecommendor);
        mCustomInfo.setRecommendorCertType(recommendorCertType);
        mCustomInfo.setRecommendor(recommendor);
        mCustomInfo.setRecommendorCertID(recommendorCertID);
        mCustomInfo.setRecommendorMainCustomerID(recommendorMainCustomerID);
        mCustomInfo.setRecommendScale(recommendScale);
        mCustomInfo.setRemark(Remark);
        mCustomInfo.setManageUserName(ManageUserName);
        mCustomInfo.setManageOrgName(ManageOrgName);
        mCustomInfo.setUserName(InputUserName);
        mCustomInfo.setInputUserID(Contants.registInfo.getInputUserID());
        mCustomInfo.setInputOrgID(Contants.registInfo.getInputOrgID());

        Contants.tempCustomInfo = mCustomInfo;
        return super.getData();
    }

    @Override
    public void saveData() {
        // TODO Auto-generated method stub
        super.saveData();
        getData();
    }

    @Override
    public void initData() {
        // TODO Auto-generated method stub
        super.initData();


        if (Contants.registInfo != null) {
            et_InputUserName.setText(Contants.registInfo.getInputUserName());
            et_ManageUserName.setText(Contants.registInfo.getInputUserName());
            et_ManageOrgName.setText(Contants.registInfo.getInputOrgName());
        }
        if (Contants.tempCustomInfo == null) {
            return;
        }
        CustomInfo customInfo = Contants.tempCustomInfo;
        sp_HasRecommendor.setCode(customInfo.getHasRecommendor());
        sp_recommendorCertType.setCode(customInfo.getRecommendorCertType());
        et_recommendorCertID.setText(customInfo.getRecommendorCertID());
        et_recommendor.setText(customInfo.getRecommendor());
        et_recommendorMainCustomerID.setText(customInfo.getRecommendorMainCustomerID());
        et_recommendScale.setText(customInfo.getRecommendScale());
        et_Remark.setText(customInfo.getRemark());
        et_ManageUserName.setText(customInfo.getManageUserName());
        et_ManageOrgName.setText(customInfo.getManageOrgName());
        et_InputUserName.setText(customInfo.getUserName());
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 100:
                    String reString = (String) msg.obj;
                    Map<String, Object> map = BaseUtil.JsonStrToMap(reString);
                    JSONArray jl = (JSONArray) map.get("List");
                    if (jl.length() == 0) {
                        ToastCoustom.show("没有找到客户信息");
                        break;
                    }
                    JSONObject jObject = null;
                    try {
                        jObject = (JSONObject) jl.get(0);
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    try {
                        et_recommendor.setText(jObject.getString("CustomerName"));
                    } catch (Exception e) {
                        // TODO: handle exception
                    }

                    break;
                case 101:
                    String headstr = (String) msg.obj;
                    HeaderCommonResponse hr = new GsonBuilder().create().fromJson(
                            headstr, HeaderCommonResponse.class);
                    ToastCoustom.show(hr.getMessage());
                    break;
            }
        }
    };
}


