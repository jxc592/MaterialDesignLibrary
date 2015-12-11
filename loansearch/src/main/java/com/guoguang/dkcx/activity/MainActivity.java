package com.guoguang.dkcx.activity;

import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.aidl.cilent.entity.HeaderCommonResponse;
import com.aidl.cilent.entity.LoginResponse;
import com.aidl.cilent.library.sweetdialog.SweetAlertDialog;
import com.aidl.cilent.util.ToastCoustom;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guoguang.dkcx.R;
import com.guoguang.dkcx.app.LoanSearchApp;
import com.guoguang.dkcx.contants.Contants;
import com.guoguang.dkcx.entity.Cusbrief;
import com.guoguang.dkcx.entity.LoanInfoEntity;
import com.guoguang.dkcx.entity.Rs_Cuslist;
import com.guoguang.dkcx.entity.Rs_LoanInfo;
import com.guoguang.dkcx.entity.ValueInfo;
import com.guoguang.dkcx.view.ListAreaDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    Gson gson = new GsonBuilder().create();
    MyAdapter rvadapter;


    @InjectView(R.id.sp_CertType)
    AppCompatSpinner sp_CertType;
    @InjectView(R.id.et_CertID)
    EditText et_CertID;
    @InjectView(R.id.btn_search)
    Button btn_Search;
    @InjectView(R.id.rv)
    RecyclerView rv;
    LoginResponse mLoginResponse;

    int type = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        String loginString = getIntent().getStringExtra("logininfo");
        if (loginString == null) {
            ToastCoustom.show("请重新登录后再进入");
            finish();
            return;
        }
        mLoginResponse = new GsonBuilder().create().fromJson(loginString,
                LoginResponse.class);

        ((LoanSearchApp) getApplication()).setmLoginResponse(mLoginResponse);


        findViewById(R.id.titleLayout).setBackground(new ColorDrawable(Contants.defaultcolor));

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(manager);

        btn_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAndGetLoanInfo();
            }
        });
        List<ValueInfo> list1 = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            ValueInfo valueInfo = new ValueInfo();
            if (i == 0) {
                valueInfo.setID("121");
                valueInfo.setName("身份证");
            } else if (i == 1) {
                valueInfo.setID("122");
                valueInfo.setName("姓名");
            }
            list1.add(valueInfo);
        }
        sp_CertType.setAdapter(new MSpinnerAdapter(list1));
        sp_CertType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    type =1;
                } else if (i == 1) {
                    type =2;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    void checkAndGetLoanInfo() {
//        String CertType = sp_CertType.getse
        String CertID = et_CertID.getText().toString();
        if ("".equals(CertID)) {

        }
        getLoanInfo(CertID);
    }


    void getPersonList(final String certid) {
        new Thread() {
            @Override
            public void run() {
                super.run();

            }
        }.start();
    }


    SweetAlertDialog loadDialog;

    void getLoanInfo(final String certid) {
        if (loadDialog != null && loadDialog.isShowing()) {
            loadDialog.dismiss();
        }
        loadDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        loadDialog.setTitleText("正在查询客户信息");
        loadDialog.show();
        new Thread() {
            @Override
            public void run() {
                super.run();
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("TransCode", "1294");
                if(type ==1){
                    map.put("CertId", certid);
                    map.put("CustomerName", "");
                }else {
                    map.put("CertId", "");
                    map.put("CustomerName", certid);
                }
                String body = gson.toJson(map);
                String[] result1 = LoanSearchApp.mAidlClient.exec(LoanSearchApp.header, body);
                HeaderCommonResponse response = gson.fromJson(result1[1], HeaderCommonResponse.class);
                mHandler.obtainMessage(101, response).sendToTarget();
                if (result1[2] != null && !"".equals(result1[2])) {
                    Rs_Cuslist cuslist = gson.fromJson(result1[2], Rs_Cuslist.class);
                    if (cuslist != null && cuslist.getList().size() > 0) {
                        mHandler.obtainMessage(110, cuslist.getList()).sendToTarget();
                    } else {
                        mHandler.sendEmptyMessage(200);
                    }
                }


            }
        }.start();
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 100:
                    if (loadDialog != null && loadDialog.isShowing()) {
                        loadDialog.dismiss();
                        ;
                    }
                    List<LoanInfoEntity> loanInfoEntities = (List<LoanInfoEntity>) msg.obj;
                    //Snackbar.make(getWindow().getDecorView(), loanInfoEntities.toString(), Snackbar.LENGTH_LONG).show();
                    if (rvadapter == null) {
                        rvadapter = new MyAdapter(loanInfoEntities);
                        rv.setAdapter(rvadapter);
                    } else {
                        rvadapter.clear();
                        rvadapter.add(loanInfoEntities);
                    }
                    break;
                case 101:
                    if (loadDialog != null && loadDialog.isShowing()) {
                        loadDialog.dismiss();
                    }
                    HeaderCommonResponse response = (HeaderCommonResponse) msg.obj;
                    if (!"0000".equals(response.getResultCode())) {
                        Snackbar.make(getWindow().getDecorView(), response.getMessage(), Snackbar.LENGTH_LONG).show();
                        if (loadDialog != null && loadDialog.isShowing()) {
                            loadDialog.dismiss();
                        }
                    }
                    break;
                case 102:
                    HeaderCommonResponse res = (HeaderCommonResponse) msg.obj;
                    if (!"0000".equals(res.getResultCode())) {
                        Snackbar.make(getWindow().getDecorView(), res.getMessage(), Snackbar.LENGTH_LONG).show();
                        if (loadDialog != null && loadDialog.isShowing()) {
                            loadDialog.dismiss();
                        }
                    }
                    break;
                case 110:
                    List<Cusbrief> list = (List<Cusbrief>) msg.obj;
                    new ListAreaDialog(MainActivity.this, list).setOnItemSureClickListener(new ListAreaDialog.OnItemSureClickListener() {
                        @Override
                        public void OnItemClick(ListAreaDialog dialog, final Cusbrief node) {
                            dialog.dismiss();
                            if (loadDialog != null && loadDialog.isShowing()) {
                                loadDialog.dismiss();
                                loadDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                                loadDialog.setTitleText("正在查询贷款信息列表");
                                loadDialog.show();
                            }
                            new Thread() {
                                @Override
                                public void run() {
                                    super.run();
                                    HashMap<String, String> map = new HashMap<String, String>();
                                    map.put("TransCode", "1222");
                                    map.put("CustomerId", node.getCustomerID());
                                    String body1 = gson.toJson(map);
                                    String[] result = LoanSearchApp.mAidlClient.exec(LoanSearchApp.header, body1);
                                    if (result[2] != null && !"".equals(result[2])) {
                                        Rs_LoanInfo list = gson.fromJson(result[2], Rs_LoanInfo.class);
                                        mHandler.obtainMessage(100, list.getList()).sendToTarget();
                                    } else {
                                        HeaderCommonResponse response = gson.fromJson(result[1], HeaderCommonResponse.class);
                                        mHandler.obtainMessage(102, response).sendToTarget();
                                    }
                                }
                            }.start();
                        }
                    }).show();
                    break;
                case 111:
                    if (loadDialog != null && loadDialog.isShowing()) {
                        loadDialog.dismiss();
                        loadDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                        loadDialog.setTitleText("正在查询贷款信息列表");
                        loadDialog.show();
                    }
                    break;
                case 200:
                    Snackbar.make(getWindow().getDecorView(), "没有查询到相关客户", Snackbar.LENGTH_LONG).show();
                    break;
            }
        }
    };


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        List<LoanInfoEntity> list;

        public void add(LoanInfoEntity LoanInfoEntity) {
            list.add(LoanInfoEntity);
            notifyItemInserted(list.size());
        }

        public void add(List<LoanInfoEntity> LoanInfoEntitys) {
            list.addAll(LoanInfoEntitys);
            //notifyItemInserted(list.size());
            notifyDataSetChanged();
        }

        public void clear() {
            list.clear();
            notifyDataSetChanged();
        }

        public MyAdapter() {
            list = new ArrayList<>();
        }

        public MyAdapter(List<LoanInfoEntity> list) {
            this.list = list;
        }


        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.setTag(list.get(position));
            holder.tv_BusinessSum.setText(list.get(position).getBusinessSum());
            holder.tv_BusinessType.setText(list.get(position).getBusinessType());
            holder.tv_number.setText((position + 1) + "");
            holder.tv_CustomerID.setText(list.get(position).getCustomerID());
            holder.tv_CustomerName.setText(list.get(position).getCustomerName());
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null);
            MyViewHolder myViewHolder = new MyViewHolder(itemView);
            return myViewHolder;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return 1;
            }
            return 2;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_CustomerID;
        TextView tv_CustomerName;
        TextView tv_number, tv_BusinessType, tv_BusinessSum;

        Object tag;

        public MyViewHolder(final View itemView) {
            super(itemView);

            tv_CustomerID = (TextView) itemView.findViewById(R.id.tv_CustomerID);
            tv_CustomerName = (TextView) itemView.findViewById(R.id.tv_CustomerName);
            tv_number = (TextView) itemView.findViewById(R.id.tv_number);
            tv_BusinessType = (TextView) itemView.findViewById(R.id.tv_BusinessType);
            tv_BusinessSum = (TextView) itemView.findViewById(R.id.tv_BusinessSum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String x = gson.toJson(tag);
                    Intent intent = new Intent(MainActivity.this, ScrollingActivity.class);
                    intent.putExtra("LoanInfo", x);
                    startActivity(intent);
                }
            });
        }

        public Object getTag() {
            return tag;
        }

        public void setTag(Object tag) {
            this.tag = tag;
        }
    }


    public class MSpinnerAdapter implements SpinnerAdapter {

        List<ValueInfo> codeLibs;


        public MSpinnerAdapter(List<ValueInfo> codeLibs) {
            super();
            this.codeLibs = codeLibs;
        }

        @Override
        public void registerDataSetObserver(DataSetObserver observer) {
            // TODO Auto-generated method stub

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {
            // TODO Auto-generated method stub

        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return codeLibs.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return codeLibs.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public boolean hasStableIds() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            TextView textview = new TextView(parent.getContext());
//            AbsListView.LayoutParams lParams=new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, 40);
//            textview.setLayoutParams(lParams);
            textview.setText(codeLibs.get(position).getName());
            textview.setTextColor(0xff292a2a);
            textview.setGravity(Gravity.CENTER_VERTICAL);
            textview.setPadding(10, 0, 0, 0);
            textview.setTextSize(16);
            return textview;
        }

        @Override
        public int getItemViewType(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public boolean isEmpty() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            TextView textview = new TextView(parent.getContext());
            AbsListView.LayoutParams lParams = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, 40);
            textview.setLayoutParams(lParams);
            textview.setText(codeLibs.get(position).getName());
            textview.setTextColor(0xff292a2a);
            textview.setGravity(Gravity.CENTER_VERTICAL);
            textview.setPadding(18, 0, 0, 0);
            return textview;
        }

    }
}
