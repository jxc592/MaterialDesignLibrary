package com.guoguang.dksq.socket;

import com.guoguang.mitfs.client.MitFsHttpClient;
import com.guoguang.mitfs.client.bean.MitFsException;
import com.guoguang.mitfs.client.bean.MitFsRequest;
import com.guoguang.mitfs.client.bean.MitFsResponse;

public class DownLoadTask extends MitTask {
	
	public DownLoadTask(MitFsRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}

	MitFsHttpClient mitFsHttpClient;

	public DownLoadTask(MitFsRequest request, MitFsHttpClient mitFsHttpClient) {
		super(request);
		this.mitFsHttpClient = mitFsHttpClient;
	}
	
	
	
	public DownLoadTask(IFileSocketHandle mIFileSocketHandle,
			MitFsRequest request, MitFsHttpClient mitFsHttpClient) {
		super(mIFileSocketHandle, request);
		this.mitFsHttpClient = mitFsHttpClient;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			MitFsResponse response = mitFsHttpClient.download(request);
			mHandler.obtainMessage(RESULR,response).sendToTarget();
		} catch (MitFsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mHandler.obtainMessage(ERROR,e).sendToTarget();
		}
	}

	public DownLoadTask(IFileSocketHandle mIFileSocketHandle,
			MitFsRequest request) {
		super(mIFileSocketHandle, request);
		// TODO Auto-generated constructor stub
	}





}
