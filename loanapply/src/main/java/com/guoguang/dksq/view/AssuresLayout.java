package com.guoguang.dksq.view;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.aidl.cilent.library.sweetdialog.SweetAlertDialog;
import com.aidl.cilent.util.ToastCoustom;
import com.google.gson.GsonBuilder;
import com.guoguang.dksq.R;
import com.guoguang.dksq.application.ProLoadApp;
import com.guoguang.dksq.contants.Contants;
import com.guoguang.dksq.customerview.AddAssureDialog;
import com.guoguang.dksq.database.Assure;
import com.guoguang.dksq.database.Guarantee;
import com.guoguang.dksq.database.GuaranteeDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by tk on 2015/11/19.
 */
public class AssuresLayout extends BaseLayout {
    public AssuresLayout(Context context) {
        super(context);
    }

    public AssuresLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AssuresLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    FloatingActionButton btn_add;
    ListView listView;
    //担保信息 - 抵押
    Assure assure;
    //担保信息 -保证
    List<Guarantee> guaranteeList;

    MyAdapter myAdapter;
    @Override
    public View onCreateView() {
        inflater.inflate(R.layout.view_assures,this);
        listView = (ListView) findViewById(R.id.lv_assureList);
        btn_add = (FloatingActionButton) findViewById(R.id.btn_addassure);
        btn_add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (assure == null) {
                    new AddAssureDialog<Assure>(getContext(), 1, null).setOnSureClickListener(new AddAssureDialog.SureClick() {
                        @Override
                        public void onSureClick(AddAssureDialog dialog, Object data) {
                            dialog.dismiss();
                            assure = (Assure) data;
                            myAdapter.notifyDataSetChanged();
                        }
                    }).show();
                } else {
                    new AddAssureDialog<Guarantee>(getContext(), 2, null).setOnSureClickListener(new AddAssureDialog.SureClick() {
                        @Override
                        public void onSureClick(AddAssureDialog dialog, Object data) {
                            dialog.dismiss();
                            if (guaranteeList == null) {
                                guaranteeList = new ArrayList<Guarantee>();
                            }
                            guaranteeList.add((Guarantee) data);
                            myAdapter.notifyDataSetChanged();
                        }
                    }).show();
                }
            }
        });
        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                if (i == 0) {
                    new AddAssureDialog<Assure>(getContext(), 1, assure).setOnSureClickListener(new AddAssureDialog.SureClick() {
                        @Override
                        public void onSureClick(AddAssureDialog dialog, Object data) {
                            dialog.dismiss();
                            assure = (Assure) data;
                            myAdapter.notifyDataSetChanged();
                        }
                    }).show();
                } else {
                    new AddAssureDialog<Guarantee>(getContext(), 2, guaranteeList.get(i - 1)).setOnSureClickListener(new AddAssureDialog.SureClick() {
                        @Override
                        public void onSureClick(AddAssureDialog dialog, Object data) {
                            dialog.dismiss();
                            if (guaranteeList == null) {
                                guaranteeList = new ArrayList<Guarantee>();
                            }
                            guaranteeList.remove(i - 1);
                            guaranteeList.add(i - 1, (Guarantee) data);
                            myAdapter.notifyDataSetChanged();
                        }
                    }).show();
                }
            }
        });
        return null;
    }


    @Override
    public void initData() {
        super.initData();
        if(Contants.assureInfo == null){
            return;
        }
        assure = Contants.assureInfo;

        if(Contants.guranteeinfos == null){
            return;
        }
        guaranteeList = Contants.guranteeinfos;

        myAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean checkData() {
        if(assure == null){
            ToastCoustom.show("担保抵押信息不能为空");
            return false;
        }
        saveData();
        return true;
    }

    @Override
    public void saveData() {
        super.saveData();
        Contants.assureInfo = assure;

        if(guaranteeList!=null&&guaranteeList.size()>0){
            List<Guarantee> list = guaranteeList;
            guaranteeList = new ArrayList<>();
            for(int i=0;i<list.size();i++){
                Guarantee guarantee = list.get(i);
                guarantee.setRemark(i + "");
                guaranteeList.add(guarantee);
            }
        }
        Contants.guranteeinfos = guaranteeList;
    }

    void delete(final String seq){
        List<Guarantee> list = ProLoadApp.getmDaoSession().queryBuilder(Guarantee.class).where(GuaranteeDao.Properties.SeqNo.eq(seq)).list();
        if(list.size()>0){
            ProLoadApp.getmDaoSession().delete(list.get(0));
        }
        new Thread(){
            @Override
            public void run() {
                super.run();
                HashMap<String,String> map = new HashMap<String, String>();
                map.put("TransCode","1226");
                map.put("Guarantee","1");
                map.put("SeqNo",seq);
                String body = new GsonBuilder().create().toJson(map);
                ProLoadApp.getmAidlClient().exec(Contants.header, body);
            }
        }.start();
    }




    class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {

            if(assure == null){
                return 0;
            }else if((assure!=null&&guaranteeList!=null&&guaranteeList.size()==0)||(assure!=null&&guaranteeList==null)){
                return 1;
            }else {
                 return  1+guaranteeList.size();
            }

        }

        @Override
        public Object getItem(int i) {
            if(i == 0){
                return assure;
            }else {
                return guaranteeList.get(i);
            }
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if(view == null){
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_assure,null);
                holder = new ViewHolder(view);
                view.setTag(holder);
            }else {
                holder = (ViewHolder) view.getTag();
            }
            if(i == 0){
                holder.tv_name.setText(assure.getGuarantorName());
                holder.tv_guarantSum.setText(assure.getGuarantyValue());
                holder.tv_guarantWay.setText("抵押");
            }else{
                holder.tv_name.setText(guaranteeList.get(i - 1).getGuarantorName());
                holder.tv_guarantSum.setText(guaranteeList.get(i-1).getGuarantyValue());
                //holder.tv_guarantWay.setText(guaranteeList.get(i-1).getGuarantyType());
                holder.tv_guarantWay.setText("保证");
            }
            holder.iv_del.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    new SweetAlertDialog(getContext(),SweetAlertDialog.NORMAL_TYPE).setTitleText("确认删除该担保信息吗？").setConfirmText("确认")
                            .setCancelText("取消").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                            if(i == 0){
                                assure =null;
                            }else {
                                guaranteeList.remove(i-1);
                            }
                            myAdapter.notifyDataSetChanged();
                        }
                    }).show();
                }
            });
            return view;
        }
    }

    class ViewHolder{
        View view;
        TextView tv_name,tv_guarantWay,tv_guarantSum;
        ImageView iv_del;
        public ViewHolder(View view) {
            this.view = view;
            tv_name = (TextView) view.findViewById(R.id.tv_Name);
            tv_guarantSum = (TextView) view.findViewById(R.id.tv_guarantSum);
            tv_guarantWay = (TextView) view.findViewById(R.id.tv_guarantWay);
            iv_del = (ImageView) view.findViewById(R.id.iv_del);
        }
    }
}
