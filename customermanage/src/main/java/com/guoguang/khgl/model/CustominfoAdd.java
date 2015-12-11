package com.guoguang.khgl.model;

import android.content.Intent;

import com.aidl.cilent.library.sweetdialog.SweetAlertDialog;
import com.guoguang.khgl.activity.PreLoadCusinfoActivity;
import com.guoguang.khgl.application.WincomApplication;
import com.guoguang.khgl.view.BaseInfoLayout;
import com.guoguang.khgl.view.ContactInfoLayout;
import com.guoguang.khgl.view.FamilyInfoLayout;
import com.guoguang.khgl.view.FinanceInfoLayout;
import com.guoguang.khgl.view.HokeTrueingLayout;
import com.guoguang.khgl.view.OtherInfoLayout;

import java.util.ArrayList;
import java.util.Observable;


public class CustominfoAdd extends BaseApply implements IProcedure {

	HokeTrueingLayout hokeTrueingLayout ;

	PreLoadCusinfoActivity mActivity;
	public CustominfoAdd(PreLoadCusinfoActivity mActivity) {
		super(mActivity);
		this.mActivity = mActivity;
		hokeTrueingLayout = new HokeTrueingLayout(mActivity,((WincomApplication)mActivity.getApplication()).getmLoginResponse(), Contants.mAidlClient);
		stepList =new ArrayList<>();
		stepList.add("联网核查");
		stepList.add("基本信息");
		stepList.add("财务信息");
		stepList.add("职务信息");
		stepList.add("联系人信息");
		stepList.add("家庭成员信息");
		stepList.add("其他信息");
		setCurrentStep(0);
		mActivity.setSaveEnable(false);
	}

	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub

	}

	String certid="";
	boolean isfirstinit = true;
	@Override
	public void onNext() {
		// TODO Auto-generated method stub
		if(editAble){
			if (position == 1) {
				if(!hokeTrueingLayout.getData()){
					return;
				}
				if(isfirstinit){
					isfirstinit = false;
					certid = Contants.tempCustomInfo.getCertID();
				}else {
					if(!certid.equals(Contants.tempCustomInfo.getCertID())){
						certid = Contants.tempCustomInfo.getCertID();
						cleanView();
					}
				}
				baseInfoLayout.onIdChanged();
				setTastView(baseInfoLayout);
			}else if (position == 2) {
				if(!baseInfoLayout.getData()){
					return;
				}
				setTastView(financeInfoLayout);
			}else if (position ==3) {
				if(!financeInfoLayout.getData()){
					return;
				}
				setTastView(professionInfoLayout);
			}else if (position == 4) {
				if(!professionInfoLayout.getData()){
					return;
				}
				setTastView(contactInfoLayout);
			}else if (position ==5) {
				if(!contactInfoLayout.getData()){
					return;
				}
				setTastView(familyInfoLayout);
			}else if (position == 6) {
				if(!familyInfoLayout.getData()){
					return;
				}
				setTastView(otherInfoLayout);
			}else if (position == 7) {
				if(!otherInfoLayout.getData()){
					return;
				}//
				//ToastCoustom.show("到达终点请先歇息歇息");
				mActivity.onSubmit();
			}

		}else {
			if (position == 1) {
				if(!hokeTrueingLayout.getData()){
					return;
				}
				if(isfirstinit){
					isfirstinit = false;
					certid = Contants.tempCustomInfo.getCertID();
				}else {
					if(!certid.equals(Contants.tempCustomInfo.getCertID())){
						certid = Contants.tempCustomInfo.getCertID();
						cleanView();
					}
				}
				baseInfoLayout.onIdChanged();
				setTastView(baseInfoLayout);
			}else if (position == 2) {
				setTastView(financeInfoLayout);
			}else if (position ==3) {
				setTastView(professionInfoLayout);
			}else if (position == 4) {
				setTastView(contactInfoLayout);
			}else if (position ==5) {
				setTastView(familyInfoLayout);
			}else if (position == 6) {
				setTastView(otherInfoLayout);
			}else if (position == 7) {
				new SweetAlertDialog(tempView.getContext(),SweetAlertDialog.NORMAL_TYPE).setTitleText("您对该客户没有维护权!")
						.show();
			}

		}
		mActivity.setSaveEnable(true);
		if(position<7){
			position++;
			setCurrentStep(position-1);
		}
	}

	@Override
	public void onLast() {
		// TODO Auto-generated method stub
		if(position==2){
			setTastView(hokeTrueingLayout);
		}else if(position==3){
			setTastView(baseInfoLayout);
		}else if(position==4){
			setTastView(financeInfoLayout);
		}else if(position==5){
			setTastView(professionInfoLayout);
		}else if(position==6){
			setTastView(contactInfoLayout);
		}else if(position==7){
			setTastView(familyInfoLayout);
		}
		if(position>1){
			position--;
			setCurrentStep(position-1);
		}
			
	}

	@Override
	public void firstLoad() {
		// TODO Auto-generated method stub
		setTastView(hokeTrueingLayout);
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

	@Override
	public void refresh() {
		baseInfoLayout.initData();
		professionInfoLayout.initData();
		financeInfoLayout.initData();
		contactInfoLayout.initData();
		familyInfoLayout.initData();
		otherInfoLayout.initData();
	}


	private void cleanView(){
		baseInfoLayout = new BaseInfoLayout(mActivity);
		financeInfoLayout = new FinanceInfoLayout(mActivity);
		contactInfoLayout = new ContactInfoLayout(mActivity);
		familyInfoLayout = new FamilyInfoLayout(mActivity);
		otherInfoLayout = new OtherInfoLayout(mActivity);
	}

	boolean editAble =true;

	@Override
	public void setEditAble(boolean editAble) {
		this.editAble =editAble;
	}
}
	