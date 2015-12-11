package com.guoguang.dkcx.app;

import android.app.Application;

import com.aidl.cilent.AidlClient;
import com.aidl.cilent.entity.HeaderCommonEntityRequest;
import com.aidl.cilent.entity.LoginResponse;
import com.aidl.cilent.util.ToastCoustom;
import com.google.gson.GsonBuilder;
/**
 * Created by tk on 2015/10/24.
 */
public class LoanSearchApp extends Application{
    public static AidlClient mAidlClient;

    @Override
    public void onCreate() {
        super.onCreate();
        ToastCoustom.init(this);
        new Thread(){
            @Override
            public void run() {
                super.run();
                mAidlClient = new AidlClient(getApplicationContext());
            }
        }.start();
    }

    public static AidlClient getmAidlClient() {
        return mAidlClient;
    }

    public static LoginResponse mLoginResponse;

    public static LoginResponse getmLoginResponse() {
        return mLoginResponse;
    }
    public static String header;

    public static void setmLoginResponse(LoginResponse mLoginResponse) {
        LoanSearchApp.mLoginResponse = mLoginResponse;
        HeaderCommonEntityRequest headerCommonEntityRequest = new HeaderCommonEntityRequest();
        headerCommonEntityRequest.setEncryptInd("0");
        headerCommonEntityRequest.setSdSysId("0000");
        headerCommonEntityRequest.setRcvSysId("0002");
        headerCommonEntityRequest.setFlag("0");
        headerCommonEntityRequest.setUserId(mLoginResponse.getUserId());
        header = new GsonBuilder().create().toJson(headerCommonEntityRequest);

    }
}
