package com.guoguang.dksq.customerview;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.aidl.cilent.util.AllCompositeViewAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guoguang.dksq.R;
import com.guoguang.dksq.application.ProLoadApp;
import com.guoguang.dksq.contants.Contants;
import com.guoguang.dksq.database.CodeLib;
import com.guoguang.dksq.database.CodeLibDao;
import com.guoguang.dksq.entity.CodelibEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by tk on 2015/11/24.
 */
public class GuarantyDataDia extends Dialog {
    Gson gson =new GsonBuilder().create();
    ListView listView;
    Button btn_sure,btn_cancel;

    List<String> codes;
    public GuarantyDataDia(Context context,String data) {
        super(context, R.style.alert_dialog);
        setContentView(R.layout.dialog_guarantydata);
        initView();

        initTreeData();

        if(data!=null&&!"".equals(data)){
            String datas[] = data.split("@");
            if(datas.length>0){
                codes = new ArrayList<>();
                for (int i=0;i<datas.length;i++){
                    codes.add(datas[i]);
                }
            }
        }
    }


    public GuarantyDataDia(Context context) {
        super(context, R.style.alert_dialog);
        setContentView(R.layout.dialog_guarantydata);
        initView();
        initTreeData();
    }


    void initView(){
        listView = (ListView)
                findViewById(R.id.lv_tree);
        btn_sure= (Button) findViewById(R.id.btn_sure);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_sure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (onSureClickListener != null) {
                    onSureClickListener.onSureClick(GuarantyDataDia.this, getData());
                } else {
                    GuarantyDataDia.this.dismiss();
                }
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                GuarantyDataDia.this.dismiss();
            }
        });
    }

    List<CodeLib> codeLibs;
    
    void initTreeData(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                codeLibs = ProLoadApp.getmDaoSession().queryBuilder(CodeLib.class).where(CodeLibDao.Properties.CODENO.eq("GuarantyData")).list();
                if(codeLibs==null||codeLibs.size()==0){
                    HashMap<String, String> map = new HashMap<>();
                    map.put("TransCode", "1298");
                    map.put("CODENO","GuarantyData");
                    String body= gson.toJson(map);
                    String result[] = ProLoadApp.getmAidlClient().exec(Contants.header, body);
                    if(result!=null&&result[2]!=null&&!"".equals(result[2])){
                        CodelibEntity libEntity=gson.fromJson(result[2], CodelibEntity.class);
                        codeLibs = libEntity.getCodeList();
                        if(codeLibs!=null&&codeLibs.size()>0){
                            ProLoadApp.getmDaoSession().getCodeLibDao().insertInTx(codeLibs);
                            mHandler.sendEmptyMessage(100);
                        }
                    }else{
                    }
                }else {
                    mHandler.sendEmptyMessage(100);
                }
            }
        }.start();
    }
    AllCompositeViewAdapter<CodeLib> adapter;
    Handler mHandler  = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            adapter= new AllCompositeViewAdapter<>(R.layout.item_guarantydata,codeLibs);
            adapter.setgetViewListener(new AllCompositeViewAdapter.getViewListener() {
                @Override
                public void startView(View view, Object data, int position, ViewGroup parent) {
                    CodeLib lib = (CodeLib) data;
                    CheckBox checkBox = (CheckBox) view.findViewById(R.id.cb_guarantyData);

                    checkBox.setTag(lib);

                    checkBox.setText(lib.getITEMNAME());
                    if(codes!=null){
                        if(codes.contains(lib.getITEMNO())){
                            checkBox.setChecked(true);
                        }
                    }

                }
            });
            listView.setAdapter(adapter);
        }
    };

    HashMap<String,String> getData(){
        if(adapter != null&&adapter.getCount()>0){
            String code="";
            String values = "";
            for(int i =0;i<adapter.getCount();i++){
                FrameLayout fl = (FrameLayout) listView.getChildAt(i);
                CheckBox cb = (CheckBox) fl.findViewById(R.id.cb_guarantyData);
                CodeLib codeLib = (CodeLib) cb.getTag();
                if(cb.isChecked()&&codeLib!=null){
                    code+=("@"+codeLib.getITEMNO());
                    values +=(codeLib.getITEMNAME()+"\n");
                }
            }
            HashMap<String,String> map = new HashMap<>();
            map.put("code",code);
            map.put("value",values);
            return  map;
        }
        return null;
    }

    SureClick onSureClickListener;

    public GuarantyDataDia setOnSureClickListener(SureClick onSureClickListener) {
        this.onSureClickListener = onSureClickListener;
        return GuarantyDataDia.this;
    }

    public interface SureClick {
        void onSureClick(GuarantyDataDia dialog, HashMap<String,String> map);
    }
}
