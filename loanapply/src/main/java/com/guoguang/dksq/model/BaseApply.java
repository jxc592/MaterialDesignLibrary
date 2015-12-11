package com.guoguang.dksq.model;

import android.view.View;

import com.guoguang.dksq.activity.ProLoadBusInfoActivity;
import com.guoguang.dksq.view.AssuresLayout;
import com.guoguang.dksq.view.BaseBusInfoLayout;
import com.guoguang.dksq.view.BaseLayout;
import com.guoguang.dksq.view.BusinessInfoLayout;
import com.guoguang.dksq.view.BuyHouseInfoLayout;
import com.guoguang.dksq.view.HostageInfoLayout;
import com.guoguang.dksq.view.OtherInfoLayout;
import com.guoguang.dksq.view.TakePhotoView;

import java.util.List;


public class BaseApply{
	BaseBusInfoLayout baseBusInfoLayout;
	BusinessInfoLayout businessInfoLayout;
	BuyHouseInfoLayout buHouseInfoLayout;
	OtherInfoLayout otherInfoLayout;
	HostageInfoLayout hostageInfoLayout;
	AssuresLayout assuresLayout;

	TakePhotoView takePhotoView;

	ProLoadBusInfoActivity mActivity;
	List<String> stepList ; 
	int position=0;
	
	public BaseApply(ProLoadBusInfoActivity mActivity) {
		super();
		this.mActivity = mActivity;
		//baseBusInfoRl = new Base
		assuresLayout = new AssuresLayout(mActivity);
		baseBusInfoLayout = new BaseBusInfoLayout(mActivity);
		businessInfoLayout = new BusinessInfoLayout(mActivity);
		buHouseInfoLayout = new BuyHouseInfoLayout(mActivity);
		otherInfoLayout = new OtherInfoLayout(mActivity);
		hostageInfoLayout = new HostageInfoLayout(mActivity);
		takePhotoView = new TakePhotoView(mActivity);
	}
	
	BaseLayout tempView ;
	protected void setTastView(View view) {
		tempView= (BaseLayout) view;
		mActivity.setTaskView(view);
	}
	
	protected void setCurrentStep(int position){
		mActivity.setCurrentStep(stepList,position);
	}
}
