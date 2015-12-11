package com.guoguang.dkjdcx.activity;

import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
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
import com.guoguang.dkjdcx.R;
import com.guoguang.dkjdcx.app.LoanProSearchApp;
import com.guoguang.dkjdcx.contants.Contants;
import com.guoguang.dkjdcx.entity.Cusbrief;
import com.guoguang.dkjdcx.entity.ProgressDetail;
import com.guoguang.dkjdcx.entity.Rs_Cuslist;
import com.guoguang.dkjdcx.entity.Rs_prolist;
import com.guoguang.dkjdcx.entity.ValueInfo;
import com.guoguang.dkjdcx.pannel.ListAreaDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button btn_search;
    List<ProgressDetail> list;
    AppCompatSpinner spinner;

    EditText et_CertID;

    Gson gson ;


    MyAdapter myAdapter;

    LoginResponse mLoginResponse;

    int type= 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gson = new GsonBuilder().create();

        findViewById(R.id.titlelayout).setBackground(new ColorDrawable(Contants.defaultcolor));

        String loginString = getIntent().getStringExtra("logininfo");
        if (loginString == null) {
            ToastCoustom.show("请重新登录后再进入");
            finish();
            return;
        }
        mLoginResponse = new GsonBuilder().create().fromJson(loginString,
                LoginResponse.class);

        ((LoanProSearchApp)getApplication()).setmLoginResponse(mLoginResponse);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        btn_search = (Button) findViewById(R.id.button);
        spinner = (AppCompatSpinner) findViewById(R.id.spinner);

        et_CertID= (EditText) findViewById(R.id.et_CertID);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager .setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        List<ValueInfo> list1 = new ArrayList<>();
        for (int i=0;i<2;i++){
            ValueInfo valueInfo = new ValueInfo();
            if(i==0){
                valueInfo.setID("121");
                valueInfo.setName("身份证");
            }else {
                valueInfo.setID("122");
                valueInfo.setName("姓名");
            }

            list1.add(valueInfo);
        }
        spinner.setAdapter(new MSpinnerAdapter(list1));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    type =1;
                }else {
                    type =2;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        list = new ArrayList<>();
        myAdapter = new MyAdapter(list);
        recyclerView.setAdapter(myAdapter);


        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String CertID =et_CertID.getText().toString();
                if("".equals(CertID)){
                    Snackbar.make(btn_search,"证件号码不能为空",Snackbar.LENGTH_SHORT).setAction("asd", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            et_CertID.setText("");
                        }
                    }).show();
                    return;
                }
                myAdapter.clear();

                getCusList(CertID);

            }
        });

    }

    android.os.Handler handler = new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(loadDialog!=null&&loadDialog.isShowing()){
                loadDialog.dismiss();
            }
            switch (msg.what){
                case 100:
                    List<ProgressDetail> list = (List<ProgressDetail>) msg.obj;
                    myAdapter.add(list);
                    break;
                case 101:
                    HeaderCommonResponse response = (HeaderCommonResponse) msg.obj;
                    //Snackbar.make(getWindow().getDecorView(),response.getMessage(),Snackbar.LENGTH_LONG).show();
                    break;
                case 110:
                    List<Cusbrief> list2 = (List<Cusbrief>) msg.obj;
                    new ListAreaDialog(MainActivity.this,list2).setOnItemSureClickListener(new ListAreaDialog.OnItemSureClickListener() {
                        @Override
                        public void OnItemClick(ListAreaDialog dialog, final Cusbrief node) {
                            dialog.dismiss();
                            getLoanProGressinfo(node.getCertID(),node.getCustomerName());
                        }
                    }).show();
                    break;
            }
        }
    };

    SweetAlertDialog loadDialog;
    void getCusList(final String certid) {
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
                if(type == 1){
                    map.put("CertId", certid);
                    map.put("CustomerName", "");
                }else {
                    map.put("CertId", "");
                    map.put("CustomerName", certid);
                }

                String body = gson.toJson(map);
                String[] result1 = LoanProSearchApp.mAidlClient.exec(LoanProSearchApp.header, body);
                HeaderCommonResponse response = gson.fromJson(result1[1], HeaderCommonResponse.class);
                handler.obtainMessage(101, response).sendToTarget();
                if (result1[2] != null && !"".equals(result1[2])) {
                    Rs_Cuslist cuslist = gson.fromJson(result1[2], Rs_Cuslist.class);
                    if (cuslist != null && cuslist.getList().size() > 0) {
                        handler.obtainMessage(110, cuslist.getList()).sendToTarget();
                    } else {
                        handler.sendEmptyMessage(200);
                    }
                }
            }
        }.start();
    }

    String tempCustomerName="";
    void getLoanProGressinfo(final String certid,String Customer){
        tempCustomerName = Customer;
        if(loadDialog!=null&&loadDialog.isShowing()) loadDialog.dismiss();
        loadDialog = new SweetAlertDialog(MainActivity.this,SweetAlertDialog.PROGRESS_TYPE);
        loadDialog.setTitleText("正在查询贷款申请进度");
        loadDialog.show();
        new Thread(){
            @Override
            public void run() {
                super.run();
                HashMap<String,String> map = new HashMap<String, String>();
                map.put("TransCode","1221");
                map.put("CertID",certid);
                String[] result = LoanProSearchApp.mAidlClient.exec(LoanProSearchApp.header,gson.toJson(map));
                if(result[2]!=null&&!"".equals(result[2])){
                    Rs_prolist rs_prolist  =  gson.fromJson(result[2], Rs_prolist.class);
                    handler.obtainMessage(100,rs_prolist.getList()).sendToTarget();
                }else {
                    HeaderCommonResponse response = gson.fromJson(result[1], HeaderCommonResponse.class);
                    handler.obtainMessage(101,response).sendToTarget();
                }
            }
        }.start();
    }


    class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
        List<ProgressDetail> list;

        public void add(ProgressDetail progressDetail){
            list.add(progressDetail);
            notifyItemInserted(list.size());
        }

        public void add(List<ProgressDetail> progressDetails){
            list.addAll(progressDetails);
            //notifyItemInserted(list.size());
            notifyDataSetChanged();
        }

        public  void clear(){
            list.clear();
            notifyDataSetChanged();
        }

        public MyAdapter(List<ProgressDetail> list) {
            this.list = list;
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.setTag(list.get(position));
            ProgressDetail detail = list.get(position);
            holder.tv_CustomerID.setText(detail.getSerialNo());
            holder.tv_CustomerName.setText(tempCustomerName);
            holder.tv_Number.setText((position+1)+"");
            holder.tv_BusinessType.setText(detail.getPhaseName());
            holder.tv_BusinessSum.setText(detail.getUserName());
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null);
            MyViewHolder myViewHolder = new MyViewHolder(itemView);
            return myViewHolder;
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_CustomerID;
        TextView tv_CustomerName;
        TextView tv_Number;
        TextView tv_BusinessType;
        TextView tv_BusinessSum;

        Object tag ;
        public MyViewHolder(final View itemView) {
            super(itemView);
            tv_CustomerID= (TextView) itemView.findViewById(R.id.tv_CustomerID);
            tv_CustomerName= (TextView) itemView.findViewById(R.id.tv_CustomerName);
            tv_Number= (TextView) itemView.findViewById(R.id.tv_number);
            tv_BusinessType = (TextView) itemView.findViewById(R.id.tv_BusinessType);
            tv_BusinessSum = (TextView) itemView.findViewById(R.id.tv_BusinessSum);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent mIntent = new Intent(MainActivity.this,ScrollingActivity.class);
                    mIntent.putExtra("LoanProgress",gson.toJson(tag));
                    startActivity(mIntent);
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

    class myItendecor extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);
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
            TextView textview=new TextView(parent.getContext());
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
            TextView textview=new TextView(parent.getContext());
            AbsListView.LayoutParams lParams=new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, 40);
            textview.setLayoutParams(lParams);
            textview.setText(codeLibs.get(position).getName());
            textview.setTextColor(0xff292a2a);
            textview.setGravity(Gravity.CENTER_VERTICAL);
            textview.setPadding(18, 0, 0, 0);
            return textview;
        }

    }
}
