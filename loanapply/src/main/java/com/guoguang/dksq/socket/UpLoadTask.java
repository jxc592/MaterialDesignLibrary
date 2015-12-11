package com.guoguang.dksq.socket;

import com.guoguang.dksq.contants.Contants;
import com.guoguang.mitfs.client.MitFsClient;
import com.guoguang.mitfs.client.bean.MitFsException;
import com.guoguang.mitfs.client.bean.MitFsRequest;
import com.guoguang.mitfs.client.bean.MitFsResponse;

public class UpLoadTask extends MitTask {
	MitFsClient client;
	
	public UpLoadTask(IFileSocketHandle mIFileSocketHandle, MitFsRequest request) {
		super(mIFileSocketHandle, request);
		// TODO Auto-generated constructor stub
	}
	public UpLoadTask(MitFsRequest request,MitFsClient mitFsClient,IFileSocketHandle handle){
		this(request, mitFsClient);
		mIFileSocketHandle = handle;
	}

	public UpLoadTask(MitFsRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}

	public UpLoadTask(MitFsRequest request, MitFsClient client) {
		super(request);
		this.client = client;
	}
	int connectTime = 0;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			if(!Connect()){
				client = null;
				client = new MitFsClient(Contants.UPLOADFILE_IP,Contants.UPLOADFILE_PORT);
				Connect();
			}
			MitFsResponse response = client.upload(request);
			mHandler.obtainMessage(RESULR,response).sendToTarget();
		} catch (MitFsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mHandler.obtainMessage(ERROR,e).sendToTarget();
		}finally{
			disConnect();
		}
		
	}
	
	/**
	 * 打开服务器连接
	 * 
	 * @return true 连接成功 false 连接失败
	 */
	public boolean Connect() {
		if (client !=null) {
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

}
