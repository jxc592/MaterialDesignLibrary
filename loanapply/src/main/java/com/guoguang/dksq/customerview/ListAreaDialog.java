package com.guoguang.dksq.customerview;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.aidl.cilent.AidlClient;
import com.aidl.cilent.util.AllCompositeViewAdapter;
import com.aidl.cilent.util.ToastCoustom;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guoguang.dksq.R;
import com.guoguang.dksq.contants.Contants;
import com.guoguang.dksq.entity.Rs_AdressList;
import com.guoguang.dksq.entity.ValueInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListAreaDialog extends Dialog{
	Context mContext = null;
	AidlClient mAidlClient;
	Gson gson;
	View loadView;
	TextView tv_title;
	SearchView sv;
	public ListAreaDialog(Context context) {
		super(context, R.style.alert_dialog);
		
		setContentView(R.layout.dialog_listarea);

		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.width = 600;
		getWindow().setAttributes(lp);
		sv= (SearchView) findViewById(R.id.sv_search);
        sv.setIconified(false);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if("".equals(s)){
                    ToastCoustom.show("尚未输入搜索条件");
                    return false;
                }
                if(listdata!=null&&listdata.size()>0){
                    List<ValueInfo> targetList = new ArrayList<ValueInfo>();
                    for(ValueInfo valueInfo:listdata){
                        if(valueInfo.getID().contains(s)||valueInfo.getName().contains(s)){
                            targetList.add(valueInfo);
                        }
                    }
                    if(targetList.size()==0){
                        ToastCoustom.show("没有找到相应数据");
                    }else {
                        listView.setAdapter(new AllCompositeViewAdapter<>(R.layout.item_tree_listview, targetList, new AllCompositeViewAdapter.getViewListener() {
                            @Override
                            public void startView(View view, Object data, int position,
                                                  ViewGroup parent) {
                                // TODO Auto-generated method stub
                                ValueInfo valueInfo = (ValueInfo) data;
                                TextView tv_name = (TextView) view.findViewById(R.id.id_treenode_label);
                                ImageView iv_icoImageView= (ImageView) view.findViewById(R.id.id_treenode_icon);
                                iv_icoImageView.setVisibility(View.INVISIBLE);
                                tv_name.setTextColor(Color.BLACK);
                                tv_name.setText(valueInfo.getName());
                            }
                        }));
                    }
                }else {
                    ToastCoustom.show("数据尚未加载完成");
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
		tv_title = (TextView) findViewById(R.id.tv_title);
		loadView = findViewById(R.id.view_load);
		mContext = context;
		mAidlClient = Contants.mAidlClient;
		gson = new GsonBuilder().create();
		listView = (ListView) findViewById(R.id.lv_tree);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				ValueInfo valueInfo = (ValueInfo) view.getTag();
				if(onItemSureClickListener!=null){
					onItemSureClickListener.OnItemClick(ListAreaDialog.this,valueInfo);
				}else {
					ListAreaDialog.this.dismiss();
				}
			}
		});
		initTreeData();
		
	}
	String code = null;
	public ListAreaDialog(Context context,String code){
		this(context);
		this.code  = code;
		if("IncomeOrg".equals(code)){
			tv_title.setText("请选择入账机构");
		}else if ("EvalOrg".equals(code)) {
			tv_title.setText("请选择价值评估机构");
		}else if ("OperateUser1".equals(code)) {
			tv_title.setText("请选择协助客户经理");
		}
		
	}
		
	
	OnItemSureClickListener onItemSureClickListener;
	
	public OnItemSureClickListener getOnItemSureClickListener() {
		return onItemSureClickListener;
	}
	public ListAreaDialog setOnItemSureClickListener(
			OnItemSureClickListener onItemSureClickListener) {
		this.onItemSureClickListener = onItemSureClickListener;
		return this;
	}
	public interface OnItemSureClickListener{
		void OnItemClick(ListAreaDialog dialog, ValueInfo node);
	}
	
	Handler mHandler  = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			loadView.setVisibility(View.INVISIBLE);

			switch (msg.what) {
			case 100:
				listdata = (List<ValueInfo>) msg.obj;
                loadView.setVisibility(View.GONE);
				listView.setAdapter(new AllCompositeViewAdapter<>(R.layout.item_tree_listview, listdata, new AllCompositeViewAdapter.getViewListener() {
					@Override
					public void startView(View view, Object data, int position,
							ViewGroup parent) {
						// TODO Auto-generated method stub
						ValueInfo valueInfo = (ValueInfo) data;
						TextView tv_name = (TextView) view.findViewById(R.id.id_treenode_label);
						ImageView iv_icoImageView= (ImageView) view.findViewById(R.id.id_treenode_icon);
						iv_icoImageView.setVisibility(View.INVISIBLE);
						tv_name.setTextColor(Color.BLACK);
						tv_name.setText(valueInfo.getName());
					}
				}));
				break;
			case 1001:
				ToastCoustom.show("数据加载失败");
				break;
			default:
				break;
			}
		}
		
	};


    List<ValueInfo> listdata ;
	ListView listView;
	/**
	 * 模拟加载数据
	 */
	public void initTreeData() {
		new Thread(){
			public void run() {
				HashMap<String, String> bodymap = new HashMap<>();
				bodymap.put("TransCode", "1296");
				bodymap.put("code",code);
				String body = gson.toJson(bodymap);
				String result[] = mAidlClient.exec(Contants.header, body);
				if(result!=null&&result[2]!=null&&!"".equals(result[2])){
					Rs_AdressList list1 = gson.fromJson(result[2],Rs_AdressList.class);
					mHandler.obtainMessage(100,list1.getList()).sendToTarget();
				}else {
					mHandler.sendEmptyMessage(1001);
				}
			}
		}.start();
		

	}
}
