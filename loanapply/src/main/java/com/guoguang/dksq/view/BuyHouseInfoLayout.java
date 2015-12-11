package com.guoguang.dksq.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.aidl.cilent.util.ToastCoustom;
import com.guoguang.dksq.R;
import com.guoguang.dksq.contants.Contants;
import com.guoguang.dksq.widget.AutoLoadSpinner;

import java.text.DecimalFormat;

/**
 * 购房信息
 * @author thinkpad
 *
 */
public class BuyHouseInfoLayout extends BaseLayout{
	String businessType ;//业务类型
	View mBaseView = null;
	
	
	public BuyHouseInfoLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}


	public BuyHouseInfoLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}


	public BuyHouseInfoLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	
	public BuyHouseInfoLayout(Context context, AttributeSet attrs,
			String businessType) {
		super(context, attrs);
		this.businessType = businessType;
	}


	EditText et_HouseNum	,//	当前房产属于第几套房产
	et_MfeeSum,  //公积金贷款金额
	et_CurrentHouseCount	,//	本次购房数
	et_ThirdParty1	,//	购房合同号
	et_ThirdPartyID1	,//	房屋详址
	et_ThirdParty2	,//	建筑面积单价
	et_ConstructionArea	,//	建筑面积
	et_ThirdPartyID2	,//	房屋总价
	et_ThirdPartyAdd1	,//	首付金额
	et_ThirdPartyZIP1	,//	首付比例
	et_Remark; //备注
	AutoLoadSpinner sp_ThirdPartyAdd3	;//	房屋形式
	AutoLoadSpinner sp_ThirdPartyZIP3	;//	房屋类别
	AutoLoadSpinner sp_ISSMALLFlow; //是否小企业打款
	
	LinearLayout ll_curhouse,ll_gjj;
	@Override
	public View onCreateView() {
		// TODO Auto-generated method stub
		inflater.inflate(R.layout.view_buyhouseinfo, this);
		ll_curhouse = (LinearLayout) findViewById(R.id.ll_curhouse);
		ll_gjj = (LinearLayout) findViewById(R.id.ll_gjj);
		
		et_HouseNum = (EditText) findViewById(R.id.et_HouseNum);//	当前房产属于第几套房产
		et_MfeeSum = (EditText) findViewById(R.id.et_MfeeSum); //公积金贷款金额
		et_CurrentHouseCount = (EditText) findViewById(R.id.et_CurrentHouseCount);//	本次购房数
		et_ThirdParty1 = (EditText) findViewById(R.id.et_ThirdParty1);//	购房合同号
		et_ThirdPartyID1 = (EditText) findViewById(R.id.et_ThirdPartyID1);//	房屋详址
		et_ThirdParty2 = (EditText) findViewById(R.id.et_ThirdParty2);//	建筑面积单价
		et_ConstructionArea = (EditText) findViewById(R.id.et_ConstructionArea);//	建筑面积
		et_ThirdPartyID2 = (EditText) findViewById(R.id.et_ThirdPartyID2);//	房屋总价
		et_ThirdPartyAdd1 = (EditText) findViewById(R.id.et_ThirdPartyAdd1);//	首付金额
		et_ThirdPartyZIP1 = (EditText) findViewById(R.id.et_ThirdPartyZIP1);//	首付比例
		et_Remark = (EditText) findViewById(R.id.et_Remark);
		sp_ThirdPartyAdd3 = (AutoLoadSpinner) findViewById(R.id.sp_ThirdPartyAdd3);//	房屋形式
		sp_ThirdPartyZIP3 = (AutoLoadSpinner) findViewById(R.id.sp_ThirdPartyZIP3);//	房屋类别
		sp_ISSMALLFlow = (AutoLoadSpinner) findViewById(R.id.sp_ISSMALLFlow); //是否小企业打款
		
		et_ConstructionArea.setOnFocusChangeListener(new MyFocChangedListener());
		et_ThirdParty2.setOnFocusChangeListener(new MyFocChangedListener());
		et_ThirdPartyID2.setOnFocusChangeListener(new MyFocChangedListener());
		et_ThirdPartyAdd1.setOnFocusChangeListener(new MyFocChangedListener());

		sp_ThirdPartyAdd3.setCode("01");
		sp_ThirdPartyZIP3.setCode("01");
		sp_ISSMALLFlow.setCode("2");
		sp_ISSMALLFlow.setEnabled(false);
		return null;
	}
	
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
		if(!"1110250".equals(businessType)){
			//et_MfeeSum.setVisibility(View.GONE);
			ll_gjj.setVisibility(GONE);
		}else {
			ll_gjj.setVisibility(VISIBLE);
			invalidate();
		}
	}
	
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
		if(Contants.loanBusinessInfo==null){
			return;
		}
		et_HouseNum.setText(Contants.loanBusinessInfo.getHouseNum());//	当前房产属于第几套房产
		et_MfeeSum.setText(Contants.loanBusinessInfo.getMfeeSum());
		et_CurrentHouseCount.setText(Contants.loanBusinessInfo.getCurrentHouseCount());//	本次购房数
		et_ThirdParty1 .setText(Contants.loanBusinessInfo.getThirdParty1());//	购房合同号
		et_ThirdPartyID1 .setText(Contants.loanBusinessInfo.getThirdPartyID1());//	房屋详址
		et_ThirdParty2 .setText(Contants.loanBusinessInfo.getThirdParty2());//	建筑面积单价
		et_ConstructionArea .setText(Contants.loanBusinessInfo.getConstructionArea());//	建筑面积
		et_ThirdPartyID2 .setText(Contants.loanBusinessInfo.getThirdPartyID2());//	房屋总价
		et_ThirdPartyAdd1 .setText(Contants.loanBusinessInfo.getThirdPartyAdd1());//	首付金额
		et_ThirdPartyZIP1 .setText(Contants.loanBusinessInfo.getThirdPartyZIP1());//	首付比例
		et_Remark .setText(Contants.loanBusinessInfo.getRemark());
		if(!"".equals(Contants.loanBusinessInfo.getThirdPartyAdd3())){
			sp_ThirdPartyAdd3.setCode(Contants.loanBusinessInfo.getThirdPartyAdd3());//	房屋形式
		}
		if(!"".equals(Contants.loanBusinessInfo.getThirdPartyZIP3())){
			sp_ThirdPartyZIP3.setCode(Contants.loanBusinessInfo.getThirdPartyZIP3());//	房屋类别
		}
		if(!"".equals(Contants.loanBusinessInfo.getISSMALLFlow())){
			sp_ISSMALLFlow.setCode(Contants.loanBusinessInfo.getISSMALLFlow()); //是否小企业打款
		}

	}
	
	@Override
	public boolean checkData() {
		// TODO Auto-generated method stub
		String HouseNum = et_HouseNum.getText().toString();//	当前房产属于第几套房产
		String MfeeSum  = et_MfeeSum.getText().toString(); //公积金贷款金额
		String CurrentHouseCount = et_CurrentHouseCount.getText().toString();//	本次购房数
		String ThirdParty1 = et_ThirdParty1.getText().toString();//	购房合同号
		String ThirdPartyID1 = et_ThirdPartyID1.getText().toString();//	房屋详址
		String ThirdParty2 = et_ThirdParty2.getText().toString();//	建筑面积单价
		String ConstructionArea = et_ConstructionArea.getText().toString();//	建筑面积
		String ThirdPartyID2 = et_ThirdPartyID2.getText().toString();//	房屋总价
		String ThirdPartyAdd1 = et_ThirdPartyAdd1.getText().toString();//	首付金额
		String ThirdPartyZIP1 = et_ThirdPartyZIP1.getText().toString();//	首付比例
//		String Remark =et_Remark.getText().toString();
		String ThirdPartyAdd3 = sp_ThirdPartyAdd3.getSelectedCode();;//	房屋形式
		String ThirdPartyZIP3 = sp_ThirdPartyZIP3.getSelectedCode();//	房屋类别
		String ISSMALLFlow = sp_ISSMALLFlow.getSelectedCode(); //是否小企业打款
		
		
		if("".equals(HouseNum)){
			ToastCoustom.show("当前房产数不能为空");
			return false;
		}
		if("".equals(CurrentHouseCount)){
			ToastCoustom.show("本次购房数不能为空");
			return false;
		}
		if("".equals(ThirdParty1)){
			ToastCoustom.show("购房合同号不能为空");
			return false;
		}
		if("".equals(ThirdPartyID1)){
			ToastCoustom.show("房屋详址不能为空");
			return false;
		}
		if("".equals(ThirdParty2)){
			ToastCoustom.show("建筑面积单价不能为空");
			return false;
		}else {
			try {
				Float.parseFloat(ThirdParty2);
			} catch (Exception e) {
				ToastCoustom.show("请输入合法的建筑面积单价");
				return false;
			}
		}
		if("".equals(ConstructionArea)){
			ToastCoustom.show("建筑面积不能为空");
			return false;
		}else {
			try {
				Float.parseFloat(ThirdParty2);
			} catch (Exception e) {
				ToastCoustom.show("请输入合法的建筑面积");
				return false;
			}
		}
		if("".equals(ThirdPartyID2)){
			ToastCoustom.show("房屋总价不能为空");
			return false;
		}else {
			try {
				Float.parseFloat(ThirdPartyID2);
			} catch (Exception e) {
				ToastCoustom.show("请输入合法的房屋总价");
				return false;
			}
		}

		if("".equals(ThirdPartyAdd1)){
			ToastCoustom.show("首付金额不能为空");
			return false;
		}else {
			try {
				Float.parseFloat(ThirdPartyAdd1);
			} catch (Exception e) {
				// TODO: handle exception
				ToastCoustom.show("请输入合法的首付金额");
				return false;
			}
		}
		if("".equals(ThirdPartyZIP1)){
			ToastCoustom.show("首付比例不能为空");
			return false;
		}else {
			try {
				Float.parseFloat(ThirdParty2);
			} catch (Exception e) {
				// TODO: handle exception
				ToastCoustom.show("请输入合法的建筑面积");
				return false;
			}
		}
//
//		if("".equals(Remark)){
//			ToastCoustom.show("备注不能为空");
//			return false;
//		}
		if("".equals(ThirdPartyAdd3)){
			ToastCoustom.show("房屋形式不能为空");
			return false;
		}
		if("".equals(ThirdPartyZIP3)){
			ToastCoustom.show("房屋类别不能为空");
			return false;
		}
		if("".equals(ISSMALLFlow)){
			ToastCoustom.show("是否小企业贷款不能为空");
			return false;
		}
		boolean isec = false;
		try{
			if(!"".equals(MfeeSum)){
				isec =( Float.parseFloat(ThirdPartyID2) ==
						Float.parseFloat(ThirdPartyAdd1) +
						Float.parseFloat(MfeeSum)+
						Float.parseFloat(Contants.loanBusinessInfo.getBusinessSum()));
			}else {
				isec=( Float.parseFloat(ThirdPartyID2) ==
						Float.parseFloat(ThirdPartyAdd1) +
						Float.parseFloat(Contants.loanBusinessInfo.getBusinessSum()));
			}
		}catch (Exception e) {
			// TODO: handle exception
			isec =false;
		}

		if(!isec){
			if(ll_gjj.getVisibility() == GONE){
				ToastCoustom.show("房屋总价 与首付款金额 、业务金额之和必须相等！");
			}else {
				ToastCoustom.show("房屋总价 与首付款金额 、公积金贷款金额 、业务金额之和必须相等！");
			}
			return false;
		}
		saveData();
		return super.checkData();
	}
	
	@Override
	public void saveData() {
		// TODO Auto-generated method stub
		super.saveData();
		String HouseNum = et_HouseNum.getText().toString();//	当前房产属于第几套房产
		String MfeeSum  = et_MfeeSum.getText().toString(); //公积金贷款金额
		String CurrentHouseCount = et_CurrentHouseCount.getText().toString();//	本次购房数
		String ThirdParty1 = et_ThirdParty1.getText().toString();//	购房合同号
		String ThirdPartyID1 = et_ThirdPartyID1.getText().toString();//	房屋详址
		String ThirdParty2 = et_ThirdParty2.getText().toString();//	建筑面积单价
		String ConstructionArea = et_ConstructionArea.getText().toString();//	建筑面积
		String ThirdPartyID2 = et_ThirdPartyID2.getText().toString();//	房屋总价
		String ThirdPartyAdd1 = et_ThirdPartyAdd1.getText().toString();//	首付金额
		String ThirdPartyZIP1 = et_ThirdPartyZIP1.getText().toString();//	首付比例
		String Remark =et_Remark.getText().toString();
		String ThirdPartyAdd3 = sp_ThirdPartyAdd3.getSelectedCode();;//	房屋形式
		String ThirdPartyZIP3 = sp_ThirdPartyZIP3.getSelectedCode();//	房屋类别
		String ISSMALLFlow = sp_ISSMALLFlow.getSelectedCode(); //是否小企业打款
		
		Contants.loanBusinessInfo.setHouseNum(HouseNum);
		Contants.loanBusinessInfo.setMfeeSum(MfeeSum);
		Contants.loanBusinessInfo.setCurrentHouseCount(CurrentHouseCount);
		Contants.loanBusinessInfo.setThirdParty1(ThirdParty1);
		Contants.loanBusinessInfo.setThirdPartyID1(ThirdPartyID1);
		Contants.loanBusinessInfo.setThirdParty2(ThirdParty2);
		Contants.loanBusinessInfo.setConstructionArea(ConstructionArea);
		Contants.loanBusinessInfo.setThirdPartyID2(ThirdPartyID2);
		Contants.loanBusinessInfo.setThirdPartyAdd1(ThirdPartyAdd1);
		Contants.loanBusinessInfo.setThirdPartyZIP1(ThirdPartyZIP1);
		Contants.loanBusinessInfo.setRemark(Remark);
		Contants.loanBusinessInfo.setThirdPartyAdd3(ThirdPartyAdd3);
		Contants.loanBusinessInfo.setThirdPartyZIP3(ThirdPartyZIP3);
		Contants.loanBusinessInfo.setISSMALLFlow(ISSMALLFlow);
	}
	
	class MyFocChangedListener implements OnFocusChangeListener{
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.et_MfeeSum:
				if(!hasFocus){
					String mfeeSum = et_MfeeSum.getText().toString();
					if("".equals(mfeeSum)) break;
					
					Float flMfeeSum = Float.parseFloat(mfeeSum);
					et_MfeeSum.setText(new DecimalFormat("##0.00").format(flMfeeSum));
				}
				break;
			case R.id.et_ThirdParty2:
			case R.id.et_ConstructionArea:
				if(!hasFocus){
					String ThirdParty2 = et_ThirdParty2.getText().toString();//	建筑面积单价
					String ConstructionArea = et_ConstructionArea.getText().toString();//	建筑面积
					if("".equals(ThirdParty2)){
						break;
					}
					Float flthirdparty2 = Float.parseFloat(ThirdParty2);
					et_ThirdParty2.setText(new DecimalFormat("##0.00").format(flthirdparty2));
					if("".equals(ConstructionArea)){
						break;
					}
					Float zj =flthirdparty2 * Float.parseFloat(ConstructionArea);
					String zjstr=new DecimalFormat("##0.00").format(zj);
					et_ThirdPartyID2.setText(zjstr);
				}
				break;
			case R.id.et_ThirdPartyID2:
			case R.id.et_ThirdPartyAdd1:
				if(!hasFocus){
					String ThirdPartyID2 = et_ThirdPartyID2.getText().toString();//	房屋总价
					String ThirdPartyAdd1 = et_ThirdPartyAdd1.getText().toString();//	首付金额
					if("".equals(ThirdPartyID2)){
						break;
					}
					Float flthirdid2 = Float.parseFloat(ThirdPartyID2);
					et_ThirdPartyID2.setText(new DecimalFormat("##0.00").format(flthirdid2));
					if("".equals(ThirdPartyAdd1)){
						break;
					}
					Float flthirdadd1 = Float.parseFloat(ThirdPartyAdd1);
					et_ThirdPartyAdd1.setText(new DecimalFormat("##0.00").format(flthirdadd1));
					Float bl =flthirdadd1*100 / flthirdid2;
					String blstr=new DecimalFormat("##0.00").format(bl);
					et_ThirdPartyZIP1.setText(blstr);
				}
				break;
			default:
				break;
			}
			
		}
	}
}
