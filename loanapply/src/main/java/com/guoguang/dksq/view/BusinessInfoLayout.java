package com.guoguang.dksq.view;

import android.content.Context;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.aidl.cilent.util.BaseUtil;
import com.aidl.cilent.util.ToastCoustom;
import com.google.gson.GsonBuilder;
import com.guoguang.dksq.R;
import com.guoguang.dksq.adapter.MSpinnerAdapter;
import com.guoguang.dksq.application.ProLoadApp;
import com.guoguang.dksq.contants.Contants;
import com.guoguang.dksq.customerview.ListAreaDialog;
import com.guoguang.dksq.customerview.ListCoPartnerDialog;
import com.guoguang.dksq.database.LoanBusiness;
import com.guoguang.dksq.entity.BaseRate;
import com.guoguang.dksq.entity.CoPartner;
import com.guoguang.dksq.entity.Rs_AdressList;
import com.guoguang.dksq.entity.Rs_BaseRate;
import com.guoguang.dksq.entity.ValueInfo;
import com.guoguang.dksq.transation.Tx_1292;
import com.guoguang.dksq.transation.Tx_1296;
import com.guoguang.dksq.widget.AutoLoadSpinner;

import java.text.DecimalFormat;
import java.util.List;
/**
 * 贷前调查__业务信息
 * @author thinkpad
 *
 */
public class BusinessInfoLayout extends BaseLayout {

	String applyType,businessType;
	
	public void setApplyType(String applyType) {
		if("1".equals(applyType)){
			sp_OperationMode.setCode("010");
			ll_LineNo.setVisibility(View.GONE);
		}else {
			sp_OperationMode.setCode("020");
			ll_LineNo.setVisibility(View.VISIBLE);
			invalidate();
			et_LineNo.setOnClickListener(new MyOnclick());
		}

		this.applyType = applyType;

		if(Contants.loanBusinessInfo == null){
			 Contants.loanBusinessInfo = new LoanBusiness();
		}
		 Contants.loanBusinessInfo.setApplyType(applyType);
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
		if("1110020".equals(businessType)||"1110040".equals(businessType)){
			ll_coptera.setVisibility(View.GONE);
		}else {
			ll_coptera.setVisibility(View.VISIBLE);
			invalidate();
		}
	}

	public BusinessInfoLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public BusinessInfoLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public BusinessInfoLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
//	ThirdParty3	,//	合作商名称
//	CooperatetEntName	,//	合作商名称
//	DESCRIBE1	,//	项目名称
//	OperationMode	,//	业务办理模式
//	BusinessCurrency	,//	业务币种
//	BusinessSum	,//	申请金额
//	TermMonth	,//	期限
//	TermDay	,//	零
//	RateMode	,//	利率模式
//	BaseRateType	,//	基准利率类型
//	BaseRate	,//	基准年利率
//	RateFloatType	,//	利率浮动模式
//	RateFloat	,//	利率浮动值
//	BusinessRate	,//	执行年利率
//	AdjustRateType	,//	利率调整方式
//	DESIGNATEDATE	,//	对月对日的利率重定价日期
//	CYCLEMONTHS	,//	指定月的利率重定价月数
//	MAINREPAYMENTMETHOD	,//	主还款方式
//	REPAYMENTMETHOD	,//	还款方式
//	PayCyc	,//	还款周期
//	GainCyc	,//	递变周期
//	GainAmount	,//	递变幅度
//	HoldCorpus	,//	保留本金
//	AMORTIZETERM	,//	总摊还期限
//	GRACEPERIODDATE	,//	宽限期限
//	PayAccountName	,//	结算账号名
//	PayAccountNo	,//	结算账号
//	DefaultPayAcctNoType	,//	结算账号类型
//	IncomeOrgID	,//	入账机构
//	IncomeOrgName	,//	入账机构
//	PaymentMode	,//	支付方式
//	HouseNum	,//	当前房产属于第几套房产
//	CurrentHouseCount	,//	本次购房数
//	PutOutType	,//	出账方式
//	ThirdParty1	,//	购房合同号
//	ThirdPartyID1	,//	房屋详址
//	ThirdParty2	,//	建筑面积单价
//	ConstructionArea	,//	建筑面积
//	ThirdPartyID2	,//	房屋总价
//	ThirdPartyAdd1	,//	首付金额
//	ThirdPartyZIP1	,//	首付比例
//	ThirdPartyAdd2	,//	首付款来源
//	ThirdPartyZIP2	,//	按揭贷款成数
//	ThirdPartyAdd3	,//	房屋形式
//	ThirdPartyZIP3	,//	房屋类别
//	ISSMALLFlow	,//	是否小企业贷款
//	VouchType	,//	主要担保方式
//	VouchTypeName	,//	主要担保方式
//	Flag1	,//	是否开发商阶段性担保
//	FARMINGTYPE	,//	是否涉农贷款
//	IsLowRisk	,//	是否低风险业务
//	Purpose	,//	用途
	
//	ThirdParty3	,//	合作商名称
	EditText et_CooperatetEntName	,//	合作商名称
	et_DESCRIBE1	;//	项目名称
	AutoLoadSpinner sp_OperationMode	,//	业务办理模式
	sp_BusinessCurrency	;//	业务币种
	
	EditText et_BusinessSum	,//	申请金额
	et_TermMonth	;//	期限
//	TermDay	,//	零
	AutoLoadSpinner sp_RateMode	,//	利率模式
	sp_BaseRateType	,//	基准利率类型
	
	sp_RateFloatType	;//	利率浮动模式
	EditText et_RateFloat	,//	利率浮动值
	et_BaseRate	,//	基准年利率
	et_BusinessRate	;//	执行年利率
	AutoLoadSpinner sp_AdjustRateType	;//	利率调整方式
//	DESIGNATEDATE	,//	对月对日的利率重定价日期
//	CYCLEMONTHS	,//	指定月的利率重定价月数
	Spinner sp_MAINREPAYMENTMETHOD	;//	主还款方式
	Spinner sp_REPAYMENTMETHOD	;//	还款方式
	AutoLoadSpinner sp_PayCyc	;//	还款周期
//	GainCyc	,//	递变周期
//	GainAmount	,//	递变幅度
//	HoldCorpus	,//	保留本金
//	AMORTIZETERM	,//	总摊还期限
	EditText et_GRACEPERIODDATE	,//	宽限期限
	et_PayAccountName	,//	结算账号名
	et_PayAccountNo	;//	结算账号
	AutoLoadSpinner sp_DefaultPayAcctNoType	;//	结算账号类型
//	IncomeOrgID	,//	入账机构
	EditText et_IncomeOrgName	;//	入账机构
	AutoLoadSpinner sp_PaymentMode	,//	支付方式
	
	sp_ISSMALLFlow	;//	是否小企业贷款
	AutoLoadSpinner sp_VouchType;//	主要担保方式
//	VouchTypeName	,//	主要担保方式
	AutoLoadSpinner sp_Flag1	,//	是否开发商阶段性担保
	sp_FARMINGTYPE	,//	是否涉农贷款
	sp_IsLowRisk	;//	是否低风险业务
	EditText et_Purpose	;//	用途
	EditText et_LineNo;

	LinearLayout ll_coptera;
	LinearLayout ll_LineNo;


	List<BaseRate> rates = null;
	@Override
	public View onCreateView() {
		// TODO Auto-generated method stub
		inflater.inflate(R.layout.view_businessinfo, this);
		
		ll_coptera = (LinearLayout) findViewById(R.id.ll_coperta);
		ll_LineNo = (LinearLayout) findViewById(R.id.ll_LineNo);
		
		et_LineNo = (EditText) findViewById(R.id.et_LineNo);
		et_CooperatetEntName	= (EditText) findViewById(R.id.et_CooperatetEntName);//	合作商名称
		et_DESCRIBE1	= (EditText) findViewById(R.id.et_DESCRIBE1);//	项目名称
		sp_OperationMode	= (AutoLoadSpinner) findViewById(R.id.sp_OperationMode);//	业务办理模式
		sp_BusinessCurrency	= (AutoLoadSpinner) findViewById(R.id.sp_BusinessCurrency);//	业务币种
		et_BusinessSum	= (EditText) findViewById(R.id.et_BusinessSum);//	申请金额
		et_TermMonth	= (EditText) findViewById(R.id.et_TermMonth);//	期限
		sp_RateMode	= (AutoLoadSpinner) findViewById(R.id.sp_RateMode);//	利率模式
		sp_BaseRateType	= (AutoLoadSpinner) findViewById(R.id.sp_BaseRateType);//	基准利率类型
		et_BaseRate	= (EditText) findViewById(R.id.et_BaseRate);//	基准年利率
		sp_RateFloatType	= (AutoLoadSpinner) findViewById(R.id.sp_RateFloatType);//	利率浮动模式
		et_RateFloat	= (EditText) findViewById(R.id.et_RateFloat);//	利率浮动值
		et_BusinessRate	= (EditText) findViewById(R.id.et_BusinessRate);//	执行年利率
		sp_AdjustRateType	= (AutoLoadSpinner) findViewById(R.id.sp_AdjustRateType);//	利率调整方式
		sp_MAINREPAYMENTMETHOD	= (Spinner) findViewById(R.id.sp_MAINREPAYMENTMETHOD);//	主还款方式
		sp_REPAYMENTMETHOD	= (Spinner) findViewById(R.id.sp_REPAYMENTMETHOD);//	还款方式
		et_GRACEPERIODDATE	= (EditText) findViewById(R.id.et_GRACEPERIODDATE);//	宽限期限
		sp_DefaultPayAcctNoType	= (AutoLoadSpinner) findViewById(R.id.sp_DefaultPayAcctNoType);//	结算账号类型
		et_PayAccountNo = (EditText) findViewById(R.id.et_PayAccountNo);//结算账户号
		et_PayAccountName =(EditText) findViewById(R.id.et_PayAccountName);//结算账户名
		et_IncomeOrgName	= (EditText) findViewById(R.id.et_IncomeOrgName);//	入账机构
		sp_PaymentMode	= (AutoLoadSpinner) findViewById(R.id.sp_PaymentMode);//	支付方式
		sp_PayCyc = (AutoLoadSpinner) findViewById(R.id.sp_PayCyc); //还款周期
		//sp_ISSMALLFlow	= (AutoLoadSpinner) findViewById(R.id.sp_ISSMALLFlow);//	是否小企业贷款
		sp_VouchType= (AutoLoadSpinner) findViewById(R.id.sp_VouchType);//	主要担保方式
		sp_Flag1	= (AutoLoadSpinner) findViewById(R.id.sp_Flag1);//	是否开发商阶段性担保
		sp_FARMINGTYPE	= (AutoLoadSpinner) findViewById(R.id.sp_FARMINGTYPE);//	是否涉农贷款
		sp_IsLowRisk	= (AutoLoadSpinner) findViewById(R.id.sp_IsLowRisk);//	是否低风险业务
		et_Purpose	= (EditText) findViewById(R.id.et_Purpose);//	用途


        //业务类型 和 申请类型对页面的一些影响表现
        if("1".equals(applyType)){
            sp_OperationMode.setCode("010");
            ll_LineNo.setVisibility(View.GONE);
        }else {
            sp_OperationMode.setCode("020");
            ll_LineNo.setVisibility(View.VISIBLE);
            invalidate();
            et_LineNo.setOnClickListener(new MyOnclick());
        }
        if("1110020".equals(businessType)||"1110040".equals(businessType)){
            ll_coptera.setVisibility(View.GONE);
        }else {
            ll_coptera.setVisibility(View.VISIBLE);
            invalidate();
        }

        //业务币种
        sp_BusinessCurrency.setCode("01");
        et_Purpose.setText("购房");
        sp_PaymentMode.setCode("2");
        sp_PayCyc.setCode("1");
        sp_RateMode.setCode("1");
        sp_RateFloatType.setCode("0");
        sp_AdjustRateType.setCode("2");
		//是否涉及农贷默认 否 turefalse 0
		sp_FARMINGTYPE.setCode("0");
		//是否开发商阶段性担保。
		sp_Flag1.setCode("1");
		//主要担保方式 抵押-商品房(住宅)产权房期房
		sp_VouchType.setCode("0201020");
        //利率调整方式 不可变 固定为不调整，code是7
        //sp_AdjustRateType.setCode("7");
        //sp_AdjustRateType.setEnabled(false);
        //基准年利率不可编辑，获取服务器
        et_BaseRate.setEnabled(false);
        //业务办理模式 不可编辑 且固定为直客式，code：010
        sp_OperationMode.setEnabled(false);
        //是否低风险业务 一期房屋贷款都为高风险 所以低风险项应为否 code为2
        sp_IsLowRisk.setCode("2");
		//获取一些基本数据
		new Thread(){
			public void run() {
				Tx_1296 tx_1296 = new Tx_1296("TermType");
				String result[] = ProLoadApp.getmAidlClient().exec(Contants.header, tx_1296.getBody());
				if(result == null){
					return;
				}
				if(result[2]!=null&&!"".equals(result[2])){
					Rs_AdressList list= new GsonBuilder().create().fromJson(result[2],Rs_AdressList.class);
					mHandler.obtainMessage(100,list.getList()).sendToTarget();
				}

				String results[] = ProLoadApp.getmAidlClient().exec(Contants.header, new Tx_1292().getBody());
				if(results[2]!=null&&!"".equals(results[2])){
					Rs_BaseRate ratelist = new GsonBuilder().create().fromJson(results[2],Rs_BaseRate.class);
					rates = ratelist.getList();
				}
			};
		}.start();

		et_CooperatetEntName.setOnClickListener(new MyOnclick());
        et_IncomeOrgName.setOnClickListener(new MyOnclick());
		et_TermMonth.setOnFocusChangeListener(new MyOnfocusChangedListener());
        et_RateFloat.setOnFocusChangeListener(new MyOnfocusChangedListener());
        sp_RateMode.setOnItemSelectedListener(new MyOnItemSelectListener());

		//入账机构
		if(Contants.registInfo!=null){
			et_IncomeOrgName.setText(Contants.registInfo.getInputOrgName());
			if(Contants.loanBusinessInfo == null){
				Contants.loanBusinessInfo = new LoanBusiness();
			}
			Contants.loanBusinessInfo.setIncomeOrgID(Contants.registInfo.getInputOrgID());
		}

		return null;
	}
	Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 100:
				List<ValueInfo> list = (List<ValueInfo>) msg.obj;
				sp_MAINREPAYMENTMETHOD.setAdapter(new MSpinnerAdapter(list));
				sp_REPAYMENTMETHOD.setAdapter(new MSpinnerAdapter(list));
				String MAINREPAYMENTMETHOD ="";
				String REPAYMENTMETHOD="";
				if(Contants.loanBusinessInfo!=null){
					MAINREPAYMENTMETHOD = Contants.loanBusinessInfo.getMAINREPAYMENTMETHOD();
					REPAYMENTMETHOD = Contants.loanBusinessInfo.getREPAYMENTMETHOD();
				}
				if(!"".equals(MAINREPAYMENTMETHOD)){
					for(int i=0;i<list.size();i++){
						ValueInfo valueInfo = list.get(i);
						if(MAINREPAYMENTMETHOD.equals(valueInfo.getID())){
							sp_MAINREPAYMENTMETHOD.setSelection(i);
						}
						if(REPAYMENTMETHOD.equals(valueInfo.getID())){
							sp_REPAYMENTMETHOD.setSelection(i);
						}
					}
				}
				break;
			default:
				break;
			}
		};
	};
	
	@Override
	public boolean checkData() {
		// TODO Auto-generated method stub
//		et_CooperatetEntName.getText().toString();//	合作商名称
//		et_DESCRIBE1.getText().toString();//	项目名称
		String OperationMode = sp_OperationMode.getSelectedCode();//	业务办理模式
		String BusinessCurrency = sp_BusinessCurrency.getSelectedCode();//	业务币种
		String BusinessSum = et_BusinessSum.getText().toString();//	申请金额
		String TermMonth = et_TermMonth.getText().toString();//	期限
		String RateMode = sp_RateMode	.getSelectedCode();//	利率模式
		
//		String BaseRateType =sp_BaseRateType.getSelectedCode();//	基准利率类型
		String BaseRate =et_BaseRate	.getText().toString();//	基准年利率
		String RateFloatType =sp_RateFloatType.getSelectedCode();//	利率浮动模式
		String RateFloat =et_RateFloat.getText().toString();//	利率浮动值
		
		String BusinessRate = et_BusinessRate	.getText().toString();//	执行年利率
		String AdjustRateType = sp_AdjustRateType.getSelectedCode();//	利率调整方式
		if(sp_MAINREPAYMENTMETHOD.getSelectedItem()==null){
			ToastCoustom.show("还款方式列表尚未加载完成");
			return false;
		}
		String MAINREPAYMENTMETHOD =((ValueInfo) sp_MAINREPAYMENTMETHOD.getSelectedItem()).getID();//	主还款方式
		String REPAYMENTMETHOD = ((ValueInfo) sp_REPAYMENTMETHOD.getSelectedItem()).getID();//	还款方式
		String PayCyc = sp_PayCyc.getSelectedCode(); //还款周期
//		et_GRACEPERIODDATE.getText().toString();//	宽限期限
//		sp_DefaultPayAcctNoType.getSelectedCode();//	结算账号类型
//		et_PayAccountName.getText().toString();
//		et_PayAccountNo.getText().toString();
		String IncomeOrgName = et_IncomeOrgName.getText().toString();//	入账机构
		String PaymentMode = sp_PaymentMode.getSelectedCode();//	支付方式
//		String ISSMALLFlow = sp_ISSMALLFlow.getSelectedCode();//	是否小企业贷款
		String VouchType = sp_VouchType.getSelectedCode();//	主要担保方式
		String Flag1 = sp_Flag1.getSelectedCode()	;//	是否开发商阶段性担保
		String FARMINGTYPE = sp_FARMINGTYPE.getSelectedCode()	;//	是否涉农贷款
		String IsLowRisk = sp_IsLowRisk.getSelectedCode()	;//	是否低风险业务
		String Purpose = et_Purpose.getText().toString()	;//	用途
        String LineNo = et_LineNo.getText().toString(); //关联额度协议号

		if("2".equals(applyType)){
            if("".equals(LineNo)){
                ToastCoustom.show("关联额度协议号不能为空");
                return false;
            }
        }
		
		if("".equals(OperationMode)){
			ToastCoustom.show("业务办理模式不能为空");
			return false;
		}
		if("".equals(BusinessCurrency)){
			ToastCoustom.show("业务币种不能为空");
			return false;
		}
		if("".equals(BusinessSum)){
			ToastCoustom.show("申请金额不能为空");
			return false;
		}
		if("".equals(TermMonth)){
			ToastCoustom.show("期限不能为空");
			return false;
		}
		if("".equals(RateMode)){
			ToastCoustom.show("利率模式不能为空");
			return false;
		}

		if(!"".equals(BaseRate)){
			try{
				if(Float.parseFloat(BaseRate)>100){
					ToastCoustom.show("基准年利率不能大于100");
					return false;
				}
			}catch (Exception e) {
				// TODO: handle exception
				ToastCoustom.show("请输入合法的基准年利率");
				return false;
			}
		}

		if("".equals(BusinessRate)){
			ToastCoustom.show("执行年利率不能为空");
			return false;
		}
		if("".equals(AdjustRateType)){
			ToastCoustom.show("利率调整方式不能为空");
			return false;
		}
		if("".equals(MAINREPAYMENTMETHOD)){
			ToastCoustom.show("主还款方式不能为空");
			return false;
		}
		if("".equals(REPAYMENTMETHOD)){
			ToastCoustom.show("还款方式不能为空");
			return false;
		}
		if("".equals(PayCyc)){
			ToastCoustom.show("还款周期不能为空");
			return false;
		}
		if("".equals(IncomeOrgName)){
			ToastCoustom.show("入账机构不能为空");
			return false;
		}
		if("".equals(PaymentMode)){
			ToastCoustom.show("支付方式不能为空");
			return false;
		}
//		if("".equals(ISSMALLFlow)){
//			ToastCoustom.show("是否小企业贷款不能为空");
//			return false;
//		}
		if("".equals(VouchType)){
			ToastCoustom.show("主要担保方式不能为空");
			return false;
		}
		if("".equals(Flag1)){
			ToastCoustom.show("是否开发商阶段担保不能为空");
			return false;
		}
		if("".equals(FARMINGTYPE)){
			ToastCoustom.show("是否涉及农业贷款不能为空");
			return false;
		}
		if("".equals(IsLowRisk)){
			ToastCoustom.show("是否低风险业务不能为空");
			return false;
		}
		if("".equals(Purpose)){
			ToastCoustom.show("用途不能为空");
			return false;
		}
		saveData();
		return super.checkData();
	}

	@Override
	public void saveData() {
		// TODO Auto-generated method stub
		super.saveData();
		String CooperatetEntName=et_CooperatetEntName.getText().toString();//	合作商名称
		String DESCRIBE1 =et_DESCRIBE1.getText().toString();//	项目名称
		String OperationMode = sp_OperationMode.getSelectedCode();//	业务办理模式
		String BusinessCurrency = sp_BusinessCurrency.getSelectedCode();//	业务币种
		String BusinessSum = et_BusinessSum.getText().toString();//	申请金额
		String TermMonth = et_TermMonth.getText().toString();//	期限
		String RateMode = sp_RateMode	.getSelectedCode();//	利率模式
		String BaseRateType = sp_BaseRateType.getSelectedCode();//	基准利率类型
		String BaseRate = et_BaseRate	.getText().toString();//	基准年利率
		String RateFloatType = sp_RateFloatType.getSelectedCode();//	利率浮动模式
		String RateFloat = et_RateFloat.getText().toString();//	利率浮动值
		String BusinessRate = et_BusinessRate	.getText().toString();//	执行年利率
		String AdjustRateType = sp_AdjustRateType.getSelectedCode();//	利率调整方式
		String MAINREPAYMENTMETHOD = ((ValueInfo) sp_MAINREPAYMENTMETHOD.getSelectedItem()).getID();//	主还款方式
		String REPAYMENTMETHOD = ((ValueInfo) sp_MAINREPAYMENTMETHOD.getSelectedItem()).getID();//	还款方式
		String PayCyc = sp_PayCyc.getSelectedCode(); //还款周期
		String GRACEPERIODDATE = et_GRACEPERIODDATE.getText().toString();//	宽限期限
		String DefaultPayAcctNoType = sp_DefaultPayAcctNoType.getSelectedCode();//	结算账号类型
		String PayAccountName = et_PayAccountName.getText().toString();
		String PayAccountNo = et_PayAccountNo.getText().toString();
		String IncomeOrgName = et_IncomeOrgName.getText().toString();//	入账机构
		String PaymentMode = sp_PaymentMode.getSelectedCode();//	支付方式
//		String ISSMALLFlow = sp_ISSMALLFlow.getSelectedCode();//	是否小企业贷款
		String VouchType = sp_VouchType.getSelectedCode();//	主要担保方式
		String Flag1 = sp_Flag1.getSelectedCode()	;//	是否开发商阶段性担保
		String FARMINGTYPE = sp_FARMINGTYPE.getSelectedCode()	;//	是否涉农贷款
		String IsLowRisk = sp_IsLowRisk.getSelectedCode()	;//	是否低风险业务
		String Purpose = et_Purpose.getText().toString()	;//	用途
		String LineNo = et_LineNo.getText().toString(); //关联额度协议号

		LoanBusiness loanBusinessinfo = Contants.loanBusinessInfo;
		if(loanBusinessinfo == null){
			loanBusinessinfo = new LoanBusiness();
		}
		loanBusinessinfo.setCooperatetEntName(CooperatetEntName);
		loanBusinessinfo.setDESCRIBE1(DESCRIBE1);
		loanBusinessinfo.setOperationMode(OperationMode);
		loanBusinessinfo.setBusinessCurrency(BusinessCurrency);
		loanBusinessinfo.setBusinessSum(BusinessSum);
		loanBusinessinfo.setTermMonth(TermMonth);
		loanBusinessinfo.setRateMode(RateMode);
		loanBusinessinfo.setBaseRateType(BaseRateType);
		loanBusinessinfo.setBaseRate(BaseRate);
		loanBusinessinfo.setRateFloatType(RateFloatType);
		loanBusinessinfo.setRateFloat(RateFloat);
		loanBusinessinfo.setBusinessRate(BusinessRate);
		loanBusinessinfo.setAdjustRateType(AdjustRateType);
		
		loanBusinessinfo.setMAINREPAYMENTMETHOD(MAINREPAYMENTMETHOD);
		loanBusinessinfo.setREPAYMENTMETHOD(REPAYMENTMETHOD);
		loanBusinessinfo.setPayCyc(PayCyc);
		loanBusinessinfo.setGRACEPERIODDATE(GRACEPERIODDATE);
		loanBusinessinfo.setDefaultPayAcctNoType(DefaultPayAcctNoType);
		loanBusinessinfo.setPayAccountName(PayAccountName);
		loanBusinessinfo.setPayAccountNo(PayAccountNo);
		loanBusinessinfo.setIncomeOrgName(IncomeOrgName);
		loanBusinessinfo.setPaymentMode(PaymentMode);
//		loanBusinessinfo.setISSMALLFlow(ISSMALLFlow);
		loanBusinessinfo.setVouchType(VouchType);
		loanBusinessinfo.setFlag1(Flag1);
		loanBusinessinfo.setIsLowRisk(IsLowRisk);
		loanBusinessinfo.setPurpose(Purpose);
		loanBusinessinfo.setFARMINGTYPE(FARMINGTYPE);
		loanBusinessinfo.setLineNo(LineNo);
		Contants.loanBusinessInfo = loanBusinessinfo;
	}
	
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
		if(Contants.loanBusinessInfo==null){
			return;
		}
		if("".equals(Contants.loanBusinessInfo.getCustomerID())){
			return;
		}
		if("1" .equals(Contants.loanBusinessInfo.getRateMode())){
			sp_BaseRateType.setCode("010");
			sp_BaseRateType.setEnabled(false);
		}
		et_CooperatetEntName.setText(Contants.loanBusinessInfo.getCooperatetEntName());//	合作商名称
		et_DESCRIBE1.setText(Contants.loanBusinessInfo.getDESCRIBE1());//	项目名称
		sp_OperationMode.setCode(Contants.loanBusinessInfo.getOperationMode());//	业务办理模式
		sp_BusinessCurrency.setCode(Contants.loanBusinessInfo.getBusinessCurrency());//	业务币种
		et_BusinessSum.setText(Contants.loanBusinessInfo.getBusinessSum());//	申请金额
		et_TermMonth.setText(Contants.loanBusinessInfo.getTermMonth());//	期限
		sp_RateMode.setCode(Contants.loanBusinessInfo.getRateMode());//	利率模式
		sp_BaseRateType.setCode(Contants.loanBusinessInfo.getBaseRateType());//	基准利率类型
		et_BaseRate.setText(Contants.loanBusinessInfo.getBaseRate());//	基准年利率
		sp_RateFloatType.setCode(Contants.loanBusinessInfo.getRateFloatType());//	利率浮动模式
		et_RateFloat.setText(Contants.loanBusinessInfo.getRateFloat());//	利率浮动值
		et_BusinessRate.setText(Contants.loanBusinessInfo.getBusinessRate());//	执行年利率
		sp_AdjustRateType.setCode(Contants.loanBusinessInfo.getAdjustRateType());//	利率调整方式
//		sp_MAINREPAYMENTMETHOD.setCode(Contants.loanBusinessInfo.getMAINREPAYMENTMETHOD());//	主还款方式
//		sp_REPAYMENTMETHOD.setCode(Contants.loanBusinessInfo.getREPAYMENTMETHOD());//	还款方式
		sp_PayCyc.setCode(Contants.loanBusinessInfo.getPayCyc()); //还款周期
		et_GRACEPERIODDATE.setText(Contants.loanBusinessInfo.getGRACEPERIODDATE());//	宽限期限
		sp_DefaultPayAcctNoType.setCode(Contants.loanBusinessInfo.getDefaultPayAcctNoType());//	结算账号类型
		et_PayAccountName.setText(Contants.loanBusinessInfo.getPayAccountName());
		et_PayAccountNo.setText(Contants.loanBusinessInfo.getPayAccountNo());
		et_IncomeOrgName.setText(Contants.loanBusinessInfo.getIncomeOrgName());//	入账机构
		sp_PaymentMode.setCode(Contants.loanBusinessInfo.getPaymentMode());//	支付方式
		sp_VouchType.setCode(Contants.loanBusinessInfo.getVouchType());//	主要担保方式
		sp_Flag1.setCode(Contants.loanBusinessInfo.getFlag1())	;//	是否开发商阶段性担保
		sp_FARMINGTYPE.setCode(Contants.loanBusinessInfo.getFARMINGTYPE())	;//	是否涉农贷款
		sp_IsLowRisk.setCode(Contants.loanBusinessInfo.getIsLowRisk())	;//	是否低风险业务
		et_Purpose.setText(Contants.loanBusinessInfo.getPurpose())	;//	用途
        et_LineNo.setText(Contants.loanBusinessInfo.getLineNo()); //额度协议号
	}
	
	
	class MyOnclick implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
				case R.id.et_LineNo:
				case R.id.et_CooperatetEntName:
					new ListCoPartnerDialog(getContext()).setOnItemSureClickListener(new ListCoPartnerDialog.OnItemSureClickListener() {
						@Override
						public void OnItemClick(ListCoPartnerDialog dialog,
												CoPartner node) {
							// TODO Auto-generated method stub
							et_CooperatetEntName.setText(node.getCustomerName());
							et_DESCRIBE1.setText(node.getProjectName());
							et_LineNo.setText(node.getSerialNo());
							if(Contants.loanBusinessInfo==null){
								Contants.loanBusinessInfo = new LoanBusiness();
							}
							Contants.loanBusinessInfo.setThirdParty3(node.getCustomerID());
							dialog.dismiss();
						}
					}).show();
					break;
				case R.id.et_IncomeOrgName:
					new ListAreaDialog(getContext(), "IncomeOrg").setOnItemSureClickListener(new ListAreaDialog.OnItemSureClickListener() {
						@Override
						public void OnItemClick(ListAreaDialog dialog,
												ValueInfo node) {
							// TODO Auto-generated method stub
							et_IncomeOrgName.setText(node.getName());
							Contants.loanBusinessInfo.setIncomeOrgID(node.getID());
							dialog.dismiss();
						}
					}).show();
				case R.id.et_TermMonth:

					break;
			}
		}
	}

	class MyOnItemSelectListener implements AdapterView.OnItemSelectedListener{
		@Override
		public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
			AutoLoadSpinner spinner = (AutoLoadSpinner) adapterView;
			String itemno = spinner.getSelectedCode();
			//1是浮动2是固定
			if("2".endsWith(itemno)){
				sp_AdjustRateType.setCode("7");
				sp_AdjustRateType.setEnabled(false);

				sp_BaseRateType.setEnabled(false);
				sp_BaseRateType.setCode("");

				sp_RateFloatType.setCode("");
				sp_RateFloatType.setEnabled(false);

				et_RateFloat.setEnabled(false);
				et_BusinessRate.setEnabled(true);
				et_BaseRate.setText("");
			}else if("1".equals(itemno)){
				sp_AdjustRateType.setCode("2");
				sp_AdjustRateType.setEnabled(true);

				et_RateFloat.setEnabled(true);
				sp_RateFloatType.setEnabled(true);
				sp_BaseRateType.setCode("010");
				sp_BaseRateType.setEnabled(false);

				et_BusinessRate.setEnabled(false);
				//计算基准年利率：
				if(rates == null){
					Snackbar.make(BusinessInfoLayout.this,"利率列表加载失败",Snackbar.LENGTH_SHORT).show();
					return;
				}else {
					String qx = et_TermMonth.getText().toString();
					if("".equals(qx)){
						return;
					}
					int target = Integer.parseInt(qx);
					for(BaseRate rate :rates){
						if(BaseUtil.between(rate.getMinTerm(),rate.getMaxTerm(),target)){
							et_BaseRate.setText(rate.getRateValue());
						}
					}
				}
			}
		}
		@Override
		public void onNothingSelected(AdapterView<?> adapterView) {

		}
	}


	class MyOnfocusChangedListener implements View.OnFocusChangeListener{
		@Override
		public void onFocusChange(View view, boolean b) {
			if(!b){
				switch (view.getId()){
					case R.id.et_TermMonth:
						//计算基准年利率
						if(rates == null){
							Snackbar.make(BusinessInfoLayout.this,"利率列表加载失败",Snackbar.LENGTH_SHORT).show();
							return;
						}else {
							String ratemode = sp_RateMode.getSelectedCode();
							if("2".equals(ratemode)){
								return;
							}
							String qx = et_TermMonth.getText().toString();
							if("".equals(qx)){
								return;
							}
							int target = Integer.parseInt(qx);
							for(BaseRate rate :rates){
								if(BaseUtil.between(rate.getMinTerm(),rate.getMaxTerm(),target)){
									et_BaseRate.setText(rate.getRateValue());
								}
							}
						}
						break;
					case R.id.et_RateFloat:
						//计算执行年利率

						//下面两行计算 利率浮动值
						String ratefloat = et_RateFloat.getText().toString();
                        if("".equals(ratefloat)){
                            break;
                        }
						Float rate = Float.parseFloat(ratefloat);
						//下面两行计算基准利率
						String baseratestr = et_BaseRate.getText().toString();
                        if("".equals(baseratestr)){
                            break;
                        }
						Float  baserate = Float.parseFloat(baseratestr);
						//得出利率浮动类型
						String code = sp_RateFloatType.getSelectedCode();

						//执行年利率的计算
						Float busrate;
						if("0".equals(code)){
							busrate = (1 + (rate / 100))*baserate;
						}else {
							busrate = baserate + (rate/100);
						}
						String busratestr = new DecimalFormat("##0.000").format(busrate);
						et_BusinessRate.setText(busratestr);
						break;
				}
			}

		}
	}
}
