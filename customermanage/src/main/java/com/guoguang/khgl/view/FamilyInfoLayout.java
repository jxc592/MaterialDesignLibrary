package com.guoguang.khgl.view;

import android.app.Dialog;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.aidl.cilent.library.sweetdialog.SweetAlertDialog;
import com.guoguang.dkkhgl.R;
import com.guoguang.khgl.commonview.AddFamilyinfoDialog;
import com.guoguang.khgl.entity.CustomInfo;
import com.guoguang.khgl.entity.CustomRelaInfo;
import com.guoguang.khgl.model.Contants;

import java.util.ArrayList;
import java.util.List;

public class FamilyInfoLayout extends BaseLayout {
    ListView lv_familys;
    FloatingActionButton fab;

    public FamilyInfoLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public FamilyInfoLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public FamilyInfoLayout(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    List<CustomRelaInfo> mList;
    MyAdapter myAdapter;

    @Override
    View onCreateView() {
        // TODO Auto-generated method stub
        inflater.inflate(R.layout.view_familyinfo, this);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        lv_familys = (ListView) findViewById(R.id.lv_families);
        mList = new ArrayList<>();
        myAdapter = new MyAdapter(mList);
        lv_familys.setAdapter(myAdapter);
        lv_familys.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                itemClick(view, position);
            }
        });
        lv_familys.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                itemLongClick(i);
                return true;
            }
        });
        return null;
    }

    void itemClick(View view, final int position) {
        CustomRelaInfo info = (CustomRelaInfo) view.getTag();
        new AddFamilyinfoDialog(getContext(), R.style.alert_dialog, info).setOnClickListener(new AddFamilyinfoDialog.OnClickListener() {
            @Override
            public void onClick(Dialog dialog, CustomRelaInfo customRelaInfo) {
                // TODO Auto-generated method stub
                mList.remove(position);
                customRelaInfo.setCustomerID(Contants.tempCustomInfo.getCustomerID());
                mList.add(position, customRelaInfo);
                lv_familys.setAdapter(new MyAdapter(mList));
                dialog.dismiss();
            }
        }).show();
    }

    void itemLongClick(final int position){
        new SweetAlertDialog(getContext(),SweetAlertDialog.NORMAL_TYPE).setTitleText("你将删除这条家庭成员记录").
                setConfirmText("确认").setCancelText("取消")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                        mList.remove(position);
                        lv_familys.setAdapter(new MyAdapter(mList));
                    }
                }).show();
    }

    class MyAdapter extends BaseAdapter {
        List<CustomRelaInfo> listCustomRelaInfos;

        public MyAdapter(List<CustomRelaInfo> listCustomRelaInfos) {
            super();
            this.listCustomRelaInfos = listCustomRelaInfos;
        }

        public void addCustomRelaInfo(CustomRelaInfo cus) {
            listCustomRelaInfos.add(cus);
            notifyDataSetChanged();
        }

        public void addCustomRelaInfo(List<CustomRelaInfo> cus) {
            for (CustomRelaInfo customRelaInfo : cus) {
                listCustomRelaInfos.add(customRelaInfo);
            }
            notifyDataSetChanged();
        }

        public void setListCustomRelaInfos(List<CustomRelaInfo> customRelaInfos){
            this.listCustomRelaInfos = customRelaInfos;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return listCustomRelaInfos.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_familyinfo, null);
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(1000,85);
                convertView.setLayoutParams(lp);
            }
            CustomRelaInfo customRelaInfo = listCustomRelaInfos.get(position);
            convertView.setTag(customRelaInfo);

            TextView tv_certID = (TextView) convertView.findViewById(R.id.tv_certID);
            TextView tv_fullname = (TextView) convertView.findViewById(R.id.tv_fullname);
            TextView tv_mobile = (TextView) convertView.findViewById(R.id.tv_mobile);
            TextView tv_customer = (TextView) convertView.findViewById(R.id.tv_CustomerId);

            TextView tv_InputUserName = (TextView) convertView.findViewById(R.id.tv_InputUserName);
            TextView tv_orgName = (TextView) convertView.findViewById(R.id.tv_InputOrgName);
            TextView tv_inserttime = (TextView) convertView.findViewById(R.id.tv_InputDate);

            ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_del);

            tv_fullname.setText(customRelaInfo.getCustomerName());
            tv_certID.setText(customRelaInfo.getCertID());
            tv_customer.setText(customRelaInfo.getRelativeID());
            tv_mobile.setText(customRelaInfo.getMobileTelephone());
            tv_InputUserName.setText(customRelaInfo.getUserName());
            tv_orgName.setText(customRelaInfo.getOrgName());
            tv_inserttime.setText(customRelaInfo.getInputDate());

            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemLongClick(position);
                }
            });
            return convertView;
        }

    }

    public void showDialog() {
        new AddFamilyinfoDialog(getContext(), R.style.alert_dialog).
                setOnClickListener(new AddFamilyinfoDialog.OnClickListener() {
                    @Override
                    public void onClick(Dialog dialog, CustomRelaInfo customRelaInfo) {
                        // TODO Auto-generated method stub
                        //myAdapter.addCustomRelaInfo(customRelaInfo);
                        mList.add(customRelaInfo);
                        lv_familys.setAdapter(new MyAdapter(mList));
                        dialog.dismiss();
                    }
                }).show();
    }

    @Override
    public void initData() {
        // TODO Auto-generated method stub
        super.initData();
        if(Contants.tempCustomInfo == null){
            return;
        }
        CustomInfo customInfo = Contants.tempCustomInfo;
        if (customInfo.getCustomerObjectRelatives() != null) {
            //mList.add(customInfo.getCustomerObjectRelatives());
            myAdapter.setListCustomRelaInfos(customInfo.getCustomerObjectRelatives());
        }

    }


    @Override
    public boolean getData() {
        Contants.tempCustomInfo.setCustomerObjectRelatives(mList);
        return super.getData();
    }
}
