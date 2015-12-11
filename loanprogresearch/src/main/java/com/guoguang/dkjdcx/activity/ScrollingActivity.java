package com.guoguang.dkjdcx.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guoguang.dkjdcx.R;
import com.guoguang.dkjdcx.app.LoanProSearchApp;
import com.guoguang.dkjdcx.contants.Contants;
import com.guoguang.dkjdcx.entity.ProgressDetail;
import com.guoguang.dkjdcx.entity.Rs_Bussresult;
import com.guoguang.dkjdcx.entity.ValueInfo;

import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ScrollingActivity extends AppCompatActivity {

    @InjectView(R.id.tv_AppSerialno)
    TextView tv_AppSerialno;
    @InjectView(R.id.tv_SerialNo)
    TextView tv_SerialNo;
    @InjectView(R.id.tv_CustomerId)
    TextView tv_CustomerId;
    @InjectView(R.id.tv_BusinessType)
    TextView tv_BusinessType;
    @InjectView(R.id.tv_BusinessSum)
    TextView tv_BusinessSum;
    @InjectView(R.id.tv_InputDate)
    TextView tv_InputDate;
    @InjectView(R.id.tv_UserName)
    TextView tv_UserName;
    @InjectView(R.id.tv_OrgId)
    TextView tv_OrgId;
    @InjectView(R.id.tv_FlowName)
    TextView tv_FlowName;
    @InjectView(R.id.tv_PhaseName)
    TextView tv_PhaseName;
    @InjectView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;

    @InjectView(R.id.fab)
    FloatingActionButton fab;


    String businesstype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        ButterKnife.inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbarLayout.setBackground(new ColorDrawable(Contants.defaultcolor));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Contants.defaultcolor));
        String LoanProgressstr = getIntent().getStringExtra("LoanProgress");
        ProgressDetail detail = new GsonBuilder().create().fromJson(LoanProgressstr, ProgressDetail.class);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_CustomerId.setVisibility(View.GONE);

        businesstype = detail.getBusinessType();

        if(!"".equals(detail.getBusinessType())){
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    HashMap<String,String> map = new HashMap<String, String>();
                    map.put("TransCode","1296");
                    map.put("code", "BusinessType1");
                    Gson gson =new GsonBuilder().create();
                    String result[]=LoanProSearchApp.getmAidlClient().exec(LoanProSearchApp.header, gson.toJson(map));
                    if(result[2]!=null&&!"".equals(result[2])){
                        Rs_Bussresult list= gson.fromJson(result[2], Rs_Bussresult.class);
                        mHandler.obtainMessage(100,list.getList()).sendToTarget();
                    }

                }
            }.start();
        }

        tv_AppSerialno.setText(detail.getAppSerialno());
        tv_SerialNo.setText(detail.getSerialNo());
        tv_BusinessSum.setText(detail.getBusinessSum());
        tv_BusinessType.setText(detail.getBusinessType());
        tv_CustomerId.setText(detail.getCustomerId());
        tv_FlowName.setText(detail.getFlowName());
        tv_InputDate.setText(detail.getInputDate());
        tv_PhaseName.setText(detail.getPhaseName());
        tv_UserName.setText(detail.getUserName());
        tv_OrgId.setText(detail.getOrgId());
        //tv_userId.setText();

    }

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            List<ValueInfo> libs = (List<ValueInfo>) msg.obj;
            if(libs.size()>0){
                for(ValueInfo lib:libs){
                    if(businesstype.equals(lib.getID())){
                        tv_BusinessType.setText(lib.getName());
                        break;
                    }
                }
            }
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
