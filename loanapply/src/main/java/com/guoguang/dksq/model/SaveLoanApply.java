package com.guoguang.dksq.model;

import android.content.Intent;

import com.guoguang.dksq.activity.ProLoadBusInfoActivity;
import com.guoguang.dksq.contants.Contants;

import java.util.ArrayList;
import java.util.Observable;

public class SaveLoanApply extends BaseApply implements IProcedure {

	
	public SaveLoanApply(ProLoadBusInfoActivity mActivity) {
		super(mActivity);
		// TODO Auto-generated constructor stub
		stepList =new ArrayList<>();
		stepList.add("基本信息");
		stepList.add("业务信息");
		stepList.add("购房信息");
		stepList.add("其他信息");
		stepList.add("担保信息");
		stepList.add("抵押物信息");
		stepList.add("影像信息");
		setCurrentStep(0);
	}
	
	String applyType = "1";
	
	
	
	public SaveLoanApply(ProLoadBusInfoActivity mActivity, String applyType) {
		super(mActivity);
		stepList =new ArrayList<>();
		stepList.add("基本信息");
		stepList.add("业务信息");
		stepList.add("购房信息");
		stepList.add("其他信息");
		stepList.add("担保信息");
		stepList.add("抵押物信息");
		stepList.add("影像信息");
		setCurrentStep(0);
		this.applyType = applyType;
		businessInfoLayout.setApplyType(applyType);
	}

	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNext() {
		// TODO Auto-generated method stub
		if(position == 1){
			if(!baseBusInfoLayout.checkData()){
				return;
			}
			businessInfoLayout.setBusinessType(Contants.loanBusinessInfo.getBusinessType());
			setTastView(businessInfoLayout);
		}else if (position == 2) {
			if(!baseBusInfoLayout.checkData()){
				return;
			}
			buHouseInfoLayout.setBusinessType(Contants.loanBusinessInfo.getBusinessType());
			setTastView(buHouseInfoLayout);
		}else if (position ==3) {
			if(!buHouseInfoLayout.checkData()){
				return;
			}
			setTastView(otherInfoLayout);
		}else if (position == 4) {
			if(!otherInfoLayout.checkData()){
				return;
			}
			setTastView(assuresLayout);
		}else if (position == 5) {
			if(!assuresLayout.checkData()){
				return;
			}
			setTastView(hostageInfoLayout);
		}else if (position == 6) {
			if(!hostageInfoLayout.checkData()){
				return;
			}
			setTastView(takePhotoView);
		}else if(position == 7){
			mActivity.onSubmit();
		}
		if(position<6){
			position++;
			setCurrentStep(position-1);
		}
	}

	@Override
	public void onLast() {
		// TODO Auto-generated method stub
		if(position==2){
			setTastView(baseBusInfoLayout);
		}else if(position==3){
			setTastView(businessInfoLayout);
		}else if(position==4){
			setTastView(buHouseInfoLayout);
		}else if(position==5){
			setTastView(otherInfoLayout);
		}else if(position==6){
			setTastView(assuresLayout);
		}
		if(position>1){
			position--;
			setCurrentStep(position-1);
		}
			
	}

	@Override
	public void firstLoad() {
		// TODO Auto-generated method stub
		setTastView(baseBusInfoLayout);
		position=1;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSave() {
		// TODO Auto-generated method stub
		tempView.saveData();
		return false;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub

	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}


	@Override
	public void clearFocus() {

	}
}
