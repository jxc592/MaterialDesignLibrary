package com.guoguang.dksq.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aidl.cilent.util.ToastCoustom;
import com.guoguang.dksq.R;
import com.guoguang.dksq.customerview.AllCusSelectDialog;
import com.guoguang.dksq.customerview.GuarantyDataDia;
import com.guoguang.dksq.customerview.ListlimitDialog;
import com.guoguang.dksq.database.Assure;
import com.guoguang.dksq.database.Guarantee;
import com.guoguang.dksq.entity.Cusbrief;
import com.guoguang.dksq.entity.Limt;
import com.guoguang.dksq.model.AssureHandle;
import com.guoguang.dksq.model.DefaultAssureHandle;
import com.guoguang.dksq.model.GurantAssureHandle;
import com.guoguang.dksq.widget.AutoLoadSpinner;

import java.util.HashMap;

public class AssureInfoLayout extends BaseLayout {
    public static final int DefauleType = 100000;
    public static final int GurantType = 200000;

	public AssureInfoLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
        checkType(null);
	}
	public AssureInfoLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
        checkType(null);
	}
	public AssureInfoLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
        checkType(null);
	}
    int GuranteeType =DefauleType;
    public AssureInfoLayout(Context context,int type,Object guarant) {
        super(context);
        // TODO Auto-generated constructor stub
        GuranteeType = type;
        checkType(guarant);
    }
//	SerialNo	,//	担保信息编号
//	CustomerID	,//	客户编号
//	ContractType	,//	担保类型
//	GuarantyType	,//	担保方式
//	ContractStatus	,//	合同状态
//	GuarantorID	,//	担保人编号
//	CertType	,//	抵押人证件类型
//	CertID	,//	抵押人证件号码
//	GuarantorName	,//	抵押人名称
//	LoanCardNo	,//	抵押人贷款卡编号
//	GuarantyCurrency	,//	币种
//	GuarantyValue	Number	担保总金额
//	GuarantyInfo	,//	担保物概况
//	OtherDescribe	,//	其它特别约定
//	Remark	,//	备注
//	InputUserID	,//	登记人
//	InputUserName	,//	登记人
//	InputOrgID	,//	登记机构
//	InputOrgName	,//	登记机构
//	InputDate	,//	登记时间
//	UpdateDate	,//	更新时间
//	SerialNo	,//	担保信息编号
//	CustomerID	,//	客户编号

	AutoLoadSpinner sp_ContractType	,//	担保类型
	sp_GuarantyType	,//	担保方式
	sp_CertType	,//	抵押人证件类型
    sp_VouchWays,// 担保形式
    sp_GuarantyStage, //担保阶段
    sp_GuarantyFreeType ;//担保释放形式

	EditText et_CertID	,//	抵押人证件号码
	et_GuarantorName	,//	抵押人名称
	et_LoanCardNo	,//	抵押人贷款卡编号
	et_ContractSerialNo ,//担保额度协议好
    et_GuarantyDataName; //提交材料

	AutoLoadSpinner sp_GuarantyCurrency	;//	币种
	EditText et_GuarantyValue	;//	担保总金额

    LinearLayout ll1,ll2,ll3,ll4;
    TextView tv_CertID,tv_CertType,tv_GuarantorName;


    AssureHandle assurehandle = null;

	@Override
	public View onCreateView() {
		// TODO Auto-generated method stub
		inflater.inflate(R.layout.view_assureinfo, this);

        ll1 = (LinearLayout) findViewById(R.id.ll_assures_01);
        ll2 = (LinearLayout) findViewById(R.id.ll_assures_02);
        ll3 = (LinearLayout) findViewById(R.id.ll_assures_03);
        ll4 = (LinearLayout) findViewById(R.id.ll_assures_04);

        tv_CertID = (TextView) findViewById(R.id.tv_CertID);
        tv_CertType = (TextView) findViewById(R.id.tv_CertType);
        tv_GuarantorName = (TextView) findViewById(R.id.tv_GuarantorName);
        
        sp_ContractType = (AutoLoadSpinner) findViewById(R.id.sp_ContractType);
		sp_GuarantyType = (AutoLoadSpinner) findViewById(R.id.sp_GuarantyType);
		sp_CertType = (AutoLoadSpinner) findViewById(R.id.sp_CertType);
		sp_GuarantyCurrency = (AutoLoadSpinner) findViewById(R.id.sp_GuarantyCurrency);
		et_GuarantyValue = (EditText) findViewById(R.id.et_GuarantyValue);
		et_CertID = (EditText) findViewById(R.id.et_CertID);
		et_GuarantorName = (EditText) findViewById(R.id.et_GuarantorName);
        et_LoanCardNo  = (EditText) findViewById(R.id.et_LoanCardNo);
		sp_VouchWays = (AutoLoadSpinner) findViewById(R.id.sp_VouchWays);
        sp_GuarantyStage = (AutoLoadSpinner) findViewById(R.id.sp_GuarantyStage);
        sp_GuarantyFreeType = (AutoLoadSpinner) findViewById(R.id.sp_GuarantyFreeType);
        et_ContractSerialNo = (EditText) findViewById(R.id.et_ContractSerialNo);
        et_GuarantyDataName = (EditText) findViewById(R.id.et_GuarantyDataName);
        et_CertID.setOnClickListener(new MyOnclick());
        et_GuarantyDataName.setOnClickListener(new MyOnclick());
        et_ContractSerialNo.setOnClickListener(new MyOnclick());
        sp_GuarantyType.setEnabled(false);
        sp_GuarantyCurrency.setCode("01");
        sp_ContractType.setCode("010");
        sp_CertType.setCode("Ind01");
        sp_VouchWays.setCode("002");
        sp_GuarantyStage.setCode("002");
		return null;
	}


	@Override
	public boolean checkData() {
		return assurehandle.checkData();
	}

    public Object getData(){
        return assurehandle.getData();
    }

    // 无用，哪里都没有调用到。
	@Override
	public void saveData() {
		// TODO Auto-generated method stub
		super.saveData();
        assurehandle.saveData();
	}

    void checkType(Object guarant){
        if(GuranteeType == DefauleType){
            if(guarant == null){
                assurehandle = new DefaultAssureHandle(this,null);
            }else {
                assurehandle = new DefaultAssureHandle(this,(Assure)guarant);
            }
            sp_GuarantyType.setCode("050");
        }else if(GuranteeType == GurantType){
            if(guarant == null){
                assurehandle = new GurantAssureHandle(this,null);
            }else {
                assurehandle = new GurantAssureHandle(this,(Guarantee)guarant);
            }

            sp_GuarantyType.setCode("010010");
            ll1.setVisibility(VISIBLE);
            ll2.setVisibility(VISIBLE);
            ll3.setVisibility(VISIBLE);
            ll4.setVisibility(VISIBLE);
            tv_GuarantorName.setText("保证人名称");
            tv_CertType.setText("保证人证件类型");
            tv_CertID.setText("保证人证件号码");
            invalidate();
        }
        initData();
    }

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
        if(assurehandle == null){
            return;
        }
		assurehandle.initData();
	}
	
	class MyOnclick implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
                case R.id.et_CertID:
                    new AllCusSelectDialog(getContext()).setOnItemSureClickListener(new AllCusSelectDialog.OnItemSureClickListener() {
                        @Override
                        public void OnItemClick(AllCusSelectDialog dialog, Cusbrief node) {
                            dialog.dismiss();
                            sp_CertType.setCode(node.getCertType());
                            et_CertID.setText(node.getCertID());
                            et_GuarantorName.setText(node.getCustomerName());
                            et_LoanCardNo.setText(node.getLoanCardNo());



                            assurehandle.setGuarantorID(node.getCustomerID());

                        }
                    }).show();
                    break;
                case R.id.et_GuarantyDataName:
                        new GuarantyDataDia(getContext()).setOnSureClickListener(new GuarantyDataDia.SureClick() {
                            @Override
                            public void onSureClick(GuarantyDataDia dialog, HashMap<String, String> map) {
                                dialog.dismiss();
                                if(map!=null){
                                    et_GuarantyDataName.setText(map.get("value"));
//
                                    assurehandle.setGuarantyData(map);
                                }
                            }
                        }).show();
                    break;
                case R.id.et_ContractSerialNo:
                    String certtype = sp_CertType.getSelectedCode();
                    if(certtype.contains("Ind")){
                        ToastCoustom.show("个人客户不需要额度协议号");
                    }else {
                        String customerid = "";
                        customerid = assurehandle.getGuarantorID();
                        if("".equals(customerid)){
                            ToastCoustom.show("没有选择担保人");
                            break;
                        }
                        new ListlimitDialog(getContext(),customerid).setOnItemSureClickListener(new ListlimitDialog.OnItemSureClickListener() {
                            @Override
                            public void OnItemClick(ListlimitDialog dialog, Limt node) {
                                dialog.dismiss();
                                et_ContractSerialNo.setText(node.getSerialNo());
                            }
                        }).show();
                    }

                    break;
                default:
                    break;
            }
		}
	}

    public EditText getEt_ContractSerialNo() {
        return et_ContractSerialNo;
    }


    public EditText getEt_GuarantorName() {
        return et_GuarantorName;
    }


    public EditText getEt_GuarantyValue() {
        return et_GuarantyValue;
    }


    public EditText getEt_LoanCardNo() {
        return et_LoanCardNo;
    }


    public AutoLoadSpinner getSp_CertType() {
        return sp_CertType;
    }


    public AutoLoadSpinner getSp_VouchWays() {
        return sp_VouchWays;
    }


    public AutoLoadSpinner getSp_ContractType() {
        return sp_ContractType;
    }


    public AutoLoadSpinner getSp_GuarantyCurrency() {
        return sp_GuarantyCurrency;
    }


    public AutoLoadSpinner getSp_GuarantyFreeType() {
        return sp_GuarantyFreeType;
    }


    public AutoLoadSpinner getSp_GuarantyStage() {
        return sp_GuarantyStage;
    }


    public AutoLoadSpinner getSp_GuarantyType() {
        return sp_GuarantyType;
    }

    public EditText getEt_CertID() {
        return et_CertID;
    }

    public EditText getEt_GuarantyDataName() {
        return et_GuarantyDataName;
    }

}
