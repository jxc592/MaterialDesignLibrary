package com.guoguang.dkcx.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.guoguang.dkcx.R;
import com.guoguang.dkcx.contants.Contants;
import com.guoguang.dkcx.entity.LoanInfoEntity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ScrollingActivity extends AppCompatActivity {

    @InjectView(R.id.tv_CustomerName)
    TextView tvCustomerName;
    @InjectView(R.id.tv_BusinessType)
    TextView tvBusinessType;
    @InjectView(R.id.tv_BusinessSum)
    TextView tvBusinessSum;
    @InjectView(R.id.tv_TermMonth)
    TextView tvTermMonth;
    @InjectView(R.id.tv_BusinessRate)
    TextView tvBusinessRate;
    @InjectView(R.id.tv_VouchType)
    TextView tvVouchType;
    @InjectView(R.id.tv_REPAYMENTMETHOD)
    TextView tvREPAYMENTMETHOD;
    @InjectView(R.id.tv_Balance)
    TextView tvBalance;
    @InjectView(R.id.tv_PutOutDate)
    TextView tvPutOutDate;
    @InjectView(R.id.tv_Maturity)
    TextView tvMaturity;
    @InjectView(R.id.tv_DefaultPayDate)
    TextView tvDefaultPayDate;
    @InjectView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @InjectView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        ButterKnife.inject(this);


        String loanInfo = getIntent().getStringExtra("LoanInfo");
        LoanInfoEntity loanInfoEntity = new GsonBuilder().create().fromJson(loanInfo, LoanInfoEntity.class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Contants.defaultcolor));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        toolbarLayout.setBackground(new ColorDrawable(Contants.defaultcolor));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                finish();
            }
        });
        tvBalance.setText(loanInfoEntity.getBalance());
        tvBusinessType.setText(loanInfoEntity.getBusinessType());
        tvBusinessRate.setText(loanInfoEntity.getBusinessRate());
        tvBusinessSum.setText(loanInfoEntity.getBusinessSum());
        tvCustomerName.setText(loanInfoEntity.getCustomerName());
        tvDefaultPayDate.setText(loanInfoEntity.getDefaultPayDate());
        tvMaturity.setText(loanInfoEntity.getMaturity());
        tvPutOutDate.setText(loanInfoEntity.getPutOutDate());
        tvREPAYMENTMETHOD.setText(loanInfoEntity.getREPAYMENTMETHOD());
        tvTermMonth.setText(loanInfoEntity.getTermMonth());
        tvVouchType.setText(loanInfoEntity.getVouchType());
    }
}
