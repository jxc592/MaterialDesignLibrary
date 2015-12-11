package com.guoguang.dksq.socket;

import com.guoguang.mitfs.client.bean.MitFsException;
import com.guoguang.mitfs.client.bean.MitFsRequest;
import com.guoguang.mitfs.client.bean.MitFsResponse;

import android.os.Handler;
import android.os.Message;

public class MitTask extends Thread {
	public static final int RESULR=1000;
	public static final int ERROR=2000;
	
	protected IFileSocketHandle mIFileSocketHandle;
	
	Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case RESULR:
				if(mIFileSocketHandle==null) break;
				mIFileSocketHandle.onResult((MitFsResponse)msg.obj);
				break;
			case ERROR:
				if(mIFileSocketHandle==null)break;
				mIFileSocketHandle.onError((MitFsException)msg.obj, request);
				break;
			}
		}};
	
	MitFsRequest request;
	
	public MitTask(MitFsRequest request) {
		super();
		this.request = request;
	}

	public MitTask(IFileSocketHandle mIFileSocketHandle, MitFsRequest request) {
		super();
		this.mIFileSocketHandle = mIFileSocketHandle;
		this.request = request;
	}

	public IFileSocketHandle getmIFileSocketHandle() {
		return mIFileSocketHandle;
	}

	public void setmIFileSocketHandle(IFileSocketHandle mIFileSocketHandle) {
		this.mIFileSocketHandle = mIFileSocketHandle;
	}


	
}
