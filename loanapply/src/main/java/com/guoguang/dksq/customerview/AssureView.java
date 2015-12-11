package com.guoguang.dksq.customerview;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import com.guoguang.dksq.view.BaseLayout;
import com.guoguang.dksq.widget.AutoLoadSpinner;

/**
 * Created by tk on 2015/11/19.
 */
public class AssureView extends BaseLayout{

    AutoLoadSpinner sp_ContractType	,//	担保类型
                    sp_GuarantyType	,//	担保方式
                    sp_CertType	;//	担保人证件类型

    EditText et_CertID,//	担保人证件号
    et_GuarantorName,//	抵押人名称
	et_LoanCardNo;//	抵押人贷款卡编号

    AutoLoadSpinner sp_GuarantyCurrency	;//	币种
    EditText et_GuarantyValue;//	担保总金额


    public AssureView(Context context) {
        super(context);
    }

    public AssureView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AssureView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public View onCreateView() {

        return null;
    }

    @Override
    public boolean checkData() {
        return super.checkData();
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void saveData() {
        super.saveData();
    }
}
