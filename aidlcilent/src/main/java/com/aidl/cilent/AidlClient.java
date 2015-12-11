package com.aidl.cilent;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.aidl.communication;

/**
 * Created by tk on 2015/10/23.
 */
public class AidlClient {
    private static String tag = "AidlClient";
    private communication madilClient;
    //是否已经bind成功
    private boolean bind = false;
    //是否执行过bind操作
    private boolean hasBind = false;

    private Context mContext = null;

    public AidlClient(Context mContext) {
        super();
        this.mContext = mContext;
        bindservice(mContext);
        Log.d(tag, "创通通讯客户端实例，开始帮定通讯服务...");
    }

    private void bindservice(Context mContext) {
        // Bundle mButtle = new Bundle();
        Intent intent = new Intent("com.communicationservice");
        mContext.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        hasBind = true;
    }

    public ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName classname) {
            madilClient = null;
            bind=false;
            hasBind = false;
            Log.d(tag, "通讯断开服务。");
        }

        @Override
        public void onServiceConnected(ComponentName classname, IBinder service) {
            madilClient = communication.Stub.asInterface(service);
            bind = true;
            Log.d(tag, "通讯服务邦定成功。");
        }
    };

    public boolean isBind() {
        return bind;
    }

    public String[] exec(String header, String body) {
        int timeout=0;
        if(madilClient==null){
            if(!hasBind){
                bindservice(mContext);
            }
        }
        while(!this.bind){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timeout+=100;
            if(timeout>1000*20){
                Log.e(tag, "邦定通讯服务超时");
                return null;
            }
        }
        String[] resultStr = null;
        try {
            resultStr = madilClient.exec(header, body);
        } catch (RemoteException e) {
            Log.e(tag, "通讯异常", e);
            //e.printStackTrace();
        }
        return resultStr;
    }

}
