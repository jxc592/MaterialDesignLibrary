package com.guoguang.dksq.socket;

import com.guoguang.mitfs.client.bean.MitFsException;
import com.guoguang.mitfs.client.bean.MitFsRequest;
import com.guoguang.mitfs.client.bean.MitFsResponse;

public interface IFileSocketHandle {
	void onResult(MitFsResponse response);
	void onError(MitFsException mitFsException, MitFsRequest request);
}
