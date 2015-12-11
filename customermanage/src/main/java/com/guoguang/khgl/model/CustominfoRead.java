package com.guoguang.khgl.model;
import android.content.Intent;

import com.aidl.cilent.library.sweetdialog.SweetAlertDialog;
import com.guoguang.khgl.activity.PreLoadCusinfoActivity;

import java.util.ArrayList;
import java.util.Observable;
public class CustominfoRead extends BaseApply implements IProcedure {

	
	public CustominfoRead(PreLoadCusinfoActivity mActivity) {
		super(mActivity);
		// TODO Auto-generated constructor stub
		stepList =new ArrayList<>();
		stepList.add("基本信息");
		stepList.add("财务信息");
		stepList.add("职务信息");
		stepList.add("联系人信息");
		stepList.add("家庭成员信息");
		stepList.add("其他信息");
		setCurrentStep(0);
	}

	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNext() {
		// TODO Auto-generated method stub
		if(editAble){
			//编辑权限需要校验。
			if (position == 1) {
				if(!baseInfoLayout.getData()){
					return;
				}
				setTastView(financeInfoLayout);
			}else if (position ==2) {
				if(!financeInfoLayout.getData()){
					return;
				}
				setTastView(professionInfoLayout);
			}else if (position == 3) {
				if(!professionInfoLayout.getData()){
					return;
				}
				setTastView(contactInfoLayout);
			}else if (position == 4) {
				if(!contactInfoLayout.getData()){
					return;
				}
				setTastView(familyInfoLayout);
			}else if (position == 5) {
				if(!familyInfoLayout.getData()){
					return;
				}
				setTastView(otherInfoLayout);
			}else if (position == 6) {
				if(!otherInfoLayout.getData()){
					return;
				}//
				mActivity.onSubmit();
			}
		}else {
			//没有修改权 不需要校验
			if (position == 1) {
				setTastView(financeInfoLayout);
			}else if (position ==2) {
				setTastView(professionInfoLayout);
			}else if (position == 3) {
				setTastView(contactInfoLayout);
			}else if (position == 4) {
				setTastView(familyInfoLayout);
			}else if (position == 5) {
				setTastView(otherInfoLayout);
			}else if (position == 6) {
				//mActivity.onSubmit();
				new SweetAlertDialog(tempView.getContext(),SweetAlertDialog.NORMAL_TYPE).setTitleText("您对该客户没有维护权!")
						.show();
			}
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
			setTastView(baseInfoLayout);
		}else if(position==3){
			setTastView(financeInfoLayout);
		}else if(position==4){
			setTastView(professionInfoLayout);
		}else if(position==5){
			setTastView(contactInfoLayout);
		}else if(position==6){
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
		setTastView(baseInfoLayout);
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
		//mActivity.onSave();
		return false;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh() {

	}

	boolean editAble  =true;
	@Override
	public void setEditAble(boolean editAble) {
		this.editAble = editAble;
	}
}
