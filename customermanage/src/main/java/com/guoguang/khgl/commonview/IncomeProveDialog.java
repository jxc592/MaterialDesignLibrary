package com.guoguang.khgl.commonview;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.aidl.cilent.util.ToastCoustom;
import com.guoguang.dkkhgl.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class IncomeProveDialog extends Dialog {
    @butterknife.InjectView(R.id.cb_income1)
    CheckBox cbIncome1;
    @butterknife.InjectView(R.id.cb_income2)
    CheckBox cbIncome2;
    @butterknife.InjectView(R.id.et_income1)
    EditText etIncome1;
    @butterknife.InjectView(R.id.cb_income3)
    CheckBox cbIncome3;
    @butterknife.InjectView(R.id.et_income2)
    EditText etIncome2;
    @butterknife.InjectView(R.id.cb_income4)
    CheckBox cbIncome4;

    public IncomeProveDialog(Context context) {
        super(context,R.style.alert_dialog);
        // TODO Auto-generated constructor stub
    }

    public IncomeProveDialog(Context context, int strle) {
        super(context, R.style.alert_dialog);

    }


    EditText inputEditText;
    Button btn_sure, btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_incomeprove);
        ButterKnife.inject(this);

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = 400;
        getWindow().setAttributes(lp);

        btn_sure = (Button) findViewById(R.id.btn_sure);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                IncomeProveDialog.this.dismiss();
            }
        });
        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> list = new ArrayList<String>();
                if(cbIncome1.isChecked()){
                    list.add("仅提供单位证明或仅提供租赁合同 ");
                }
                if(cbIncome2.isChecked()){
                    if("".equals(etIncome1.getText().toString())){
                        ToastCoustom.show("您选择了这一项，请在这一项的后面输入月份");
                        return;
                    }
                    list.add("收入证明＋近期连续"+etIncome1.getText().toString()+"个月的银行帐户流水记录");
                }
                if(cbIncome3.isChecked()){
                    if("".equals(etIncome2.getText().toString())){
                        ToastCoustom.show("您选择了这一项，请在这一项的后面输入月份");
                        return;
                    }
                    list.add("收入证明＋近期连续"+etIncome2.getText().toString()+"个月的纳税记录");
                }
                if(cbIncome4.isChecked()){
                    list.add("其他类型的收入文件（需加以说明）");
                }
                String value = "";
                for(int i =0;i<list.size();i++){
                    value +=((i+1) +"、"+list.get(i)+"\n");
                }
                if (onSureClickListener != null) {
                    onSureClickListener.onSureClick(IncomeProveDialog.this, value);
                } else {
                    IncomeProveDialog.this.dismiss();
                }
            }
        });
    }

    OnSureClickListener onSureClickListener;

    public OnSureClickListener getOnTimeSureClickListener() {
        return onSureClickListener;
    }


    public IncomeProveDialog setOnSureClickListener(OnSureClickListener onSureClickListener) {
        this.onSureClickListener = onSureClickListener;
        return this;
    }


    public interface OnSureClickListener {
        void onSureClick(IncomeProveDialog dialog, String value);
    }
}
