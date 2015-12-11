package com.guoguang.khgl.model;

import android.view.View;
import com.guoguang.khgl.activity.PreLoadCusinfoActivity;
import com.guoguang.khgl.view.BaseInfoLayout;
import com.guoguang.khgl.view.BaseLayout;
import com.guoguang.khgl.view.ContactInfoLayout;
import com.guoguang.khgl.view.FamilyInfoLayout;
import com.guoguang.khgl.view.FinanceInfoLayout;
import com.guoguang.khgl.view.OtherInfoLayout;
import com.guoguang.khgl.view.ProfessionInfoLayout;

import java.util.List;


public class BaseApply{
	BaseInfoLayout baseInfoLayout;
	OtherInfoLayout otherInfoLayout;
	ContactInfoLayout contactInfoLayout;
	FamilyInfoLayout familyInfoLayout;
	ProfessionInfoLayout professionInfoLayout;
	FinanceInfoLayout financeInfoLayout;
	
	PreLoadCusinfoActivity mActivity;
	List<String> stepList ; 
	int position=0;
	
	public BaseApply(PreLoadCusinfoActivity mActivity) {
		super();
		this.mActivity = mActivity;
		//baseBusInfoRl = new Base
		baseInfoLayout = new BaseInfoLayout(mActivity);
		contactInfoLayout = new ContactInfoLayout(mActivity);
		otherInfoLayout = new OtherInfoLayout(mActivity);
		familyInfoLayout = new FamilyInfoLayout(mActivity);
		professionInfoLayout = new ProfessionInfoLayout(mActivity); 
		financeInfoLayout = new FinanceInfoLayout(mActivity);
	}
	BaseLayout tempView;
	protected void setTastView(View view) {
		mActivity.setTaskView(view);
		tempView = (BaseLayout) view;
	}
	
	protected void setCurrentStep(int position){
		mActivity.setStepList(stepList,position);
	}

}
