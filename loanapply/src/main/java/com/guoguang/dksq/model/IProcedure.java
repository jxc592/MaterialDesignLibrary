package com.guoguang.dksq.model;

import android.content.Intent;

import java.util.Observer;

/**
 * 流程接口,实现这个接口来表现一个信用卡申请的流程
 * @author tk
 *
 */
public interface IProcedure extends Observer{
	void onNext();
	void onLast();
	void firstLoad();
	void onActivityResult(int requestCode, int resultCode, Intent data);
	boolean onSave();
	void onResume();
	void clearFocus();
}
