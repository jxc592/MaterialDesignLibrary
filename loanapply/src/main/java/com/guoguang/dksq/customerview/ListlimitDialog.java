package com.guoguang.dksq.customerview;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
import com.guoguang.dksq.entity.Limt;
import com.guoguang.dksq.entity.Rs_Limits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListlimitDialog extends Dialog{
	Context mContext = null;
	AidlClient mAidlClient;
	Gson gson;
	View loadView;
	SearchView sv;
	TextView tv_title;

	String customerID;
	public ListlimitDialog(Context context,String customerId) {
		super(context, R.style.alert_dialog);
		this.customerID = customerId;
		setContentView(R.layout.dialog_listarea);
		WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
		layoutParams.width = 1280;
		getWindow().setAttributes(layoutParams);

		sv= (SearchView) findViewById(R.id.sv_search);
		sv.setIconified(false);
		sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String s) {
				if ("".equals(s)) {
					ToastCoustom.show("尚未输入搜索条件");
					return false;
				}
				if (listdata != null && listdata.size() > 0) {
					List<Limt> targetList = new ArrayList<>();
					for (Limt valueInfo : listdata) {
						if (valueInfo.getCustomerName().contains(s)) {
							targetList.add(valueInfo);
						}
					}
					if (targetList.size() == 0) {
						ToastCoustom.show("没有找到相应数据");
					} else {
						Limt limt = new Limt();
						limt.setSerialNo("合同号");
						limt.setCustomerName("客户名称");
						limt.setBalance("可用额度");
						limt.setBusinessType("业务种类");
						limt.setBusinessSum("合同金额");
						targetList.add(0,limt);
                        listView.setAdapter(new AllCompositeViewAdapter<>(R.layout.item_edxyh, listdata, new AllCompositeViewAdapter.getViewListener() {
                            @Override
                            public void startView(View view, Object data, int position,
                                                  ViewGroup parent) {
                                // TODO Auto-generated method stub
                                Limt valueInfo = (Limt) data;
                                TextView tv_copartner = (TextView) view.findViewById(R.id.tv_serious);
                                TextView tv_project= (TextView) view.findViewById(R.id.tv_CustomeName);
                                TextView tv_BusinessType = (TextView) view.findViewById(R.id.tv_BusinessType);
                                TextView tv_totalLimit = (TextView) view.findViewById(R.id.tv_totalLimit);
                                TextView tv_leftLimit  = (TextView) view.findViewById(R.id.tv_leftLimit);

                                tv_copartner.setText(valueInfo.getCustomerName());
                                tv_project.setText(valueInfo.getSerialNo());
                                tv_totalLimit.setText(valueInfo.getBusinessSum());
                                tv_BusinessType.setText(valueInfo.getBusinessType());
                                tv_leftLimit.setText(valueInfo.getBalance());
                            }
                        }));
					}
				} else {
					ToastCoustom.show("数据尚未加载完成");
				}
				return false;
			}

			@Override
			public boolean onQueryTextChange(String s) {
				return false;
			}
		});

		loadView = findViewById(R.id.view_load);
		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_title.setText("请选择额度协议号");
		mContext = context;
		mAidlClient = Contants.mAidlClient;
		gson = new GsonBuilder().create();
		listView = (ListView) findViewById(R.id.lv_tree);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Limt valueInfo = (Limt) view.getTag();
				if(position == 0){
					return;
				}
				if(onItemSureClickListener!=null){
					onItemSureClickListener.OnItemClick(ListlimitDialog.this,valueInfo);
				}else {
					ListlimitDialog.this.dismiss();
				}
			}
		});
		initTreeData();

	}

	
	OnItemSureClickListener onItemSureClickListener;
	
	public OnItemSureClickListener getOnItemSureClickListener() {
		return onItemSureClickListener;
	}
	public ListlimitDialog setOnItemSureClickListener(
			OnItemSureClickListener onItemSureClickListener) {
		this.onItemSureClickListener = onItemSureClickListener;
		return this;
	}
	public interface OnItemSureClickListener{
		void OnItemClick(ListlimitDialog dialog, Limt node);
	}
	
	Handler mHandler  = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			loadView.setVisibility(View.INVISIBLE);

			switch (msg.what) {
			case 100:
				listdata = (List<Limt>) msg.obj;
				if(listdata!=null){
					Limt limt = new Limt();
					limt.setSerialNo("合同号");
					limt.setCustomerName("客户名称");
					limt.setBalance("可用额度");
					limt.setBusinessType("业务种类");
					limt.setBusinessSum("合同金额");
					listdata.add(0,limt);
				}
				loadView.setVisibility(View.GONE);
				listView.setAdapter(new AllCompositeViewAdapter<>(R.layout.item_edxyh, listdata, new AllCompositeViewAdapter.getViewListener() {
					@Override
					public void startView(View view, Object data, int position,
							ViewGroup parent) {
						// TODO Auto-generated method stub
						Limt valueInfo = (Limt) data;
						TextView tv_copartner = (TextView) view.findViewById(R.id.tv_serious);
						TextView tv_project= (TextView) view.findViewById(R.id.tv_CustomeName);
						TextView tv_BusinessType = (TextView) view.findViewById(R.id.tv_BusinessType);
						TextView tv_totalLimit = (TextView) view.findViewById(R.id.tv_totalLimit);
						TextView tv_leftLimit  = (TextView) view.findViewById(R.id.tv_leftLimit);

						tv_copartner.setText(valueInfo.getCustomerName());
						tv_project.setText(valueInfo.getSerialNo());
						tv_totalLimit.setText(valueInfo.getBusinessSum());
						tv_BusinessType.setText(valueInfo.getBusinessType());
						tv_leftLimit.setText(valueInfo.getBalance());
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
	List<Limt> listdata;
	ListView listView;
	/**
	 * 模拟加载数据
	 */
	public void initTreeData() {
		new Thread(){
			public void run() {
				HashMap<String, String> bodymap = new HashMap<>();
				bodymap.put("TransCode", "1231");
				bodymap.put("CustomerId",customerID);
				String body = gson.toJson(bodymap);
				String result[] = mAidlClient.exec(Contants.header, body);
				if(result!=null&&result[2]!=null&&!"".equals(result[2])){
					Rs_Limits list1 = gson.fromJson(result[2],Rs_Limits.class);
					mHandler.obtainMessage(100,list1.getList()).sendToTarget();
				}else {
					mHandler.sendEmptyMessage(1001);
				}
			}
		}.start();
		

	}
}
