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
import com.guoguang.dksq.entity.CoPartner;
import com.guoguang.dksq.entity.Rs_CoPartner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListCoPartnerDialog extends Dialog{
	Context mContext = null;
	AidlClient mAidlClient;
	Gson gson;
	View loadView;
	SearchView sv;
	TextView tv_title;
	public ListCoPartnerDialog(final Context context) {
		super(context, R.style.alert_dialog);
		
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
					List<CoPartner> targetList = new ArrayList<CoPartner>();
					for (CoPartner valueInfo : listdata) {
						if (valueInfo.getCustomerName().contains(s) || valueInfo.getProjectName().contains(s)) {
							targetList.add(valueInfo);
						}
					}
					if (targetList.size() == 0) {
						ToastCoustom.show("没有找到相应数据");
					} else {
						//targetList.add(new CoPartner());
						CoPartner coPartner = new CoPartner();
						coPartner.setCustomerName("合作商名称");
						coPartner.setProjectName("项目名称");
						coPartner.setBusinessType("业务类型");
						coPartner.setBusinessSum("总额度");
						coPartner.setBalance("已用额度");
						targetList.add(0,coPartner);
						listView.setAdapter(new AllCompositeViewAdapter<>(R.layout.item_copartner, targetList, new AllCompositeViewAdapter.getViewListener() {
							@Override
							public void startView(View view, Object data, int position,
												  ViewGroup parent) {
								// TODO Auto-generated method stub
								CoPartner valueInfo = (CoPartner) data;
								TextView tv_copartner = (TextView) view.findViewById(R.id.tv_copartner);
								TextView tv_project= (TextView) view.findViewById(R.id.tv_project);
								TextView tv_BusinessType = (TextView) view.findViewById(R.id.tv_BusinessType);
								TextView tv_totalLimit = (TextView) view.findViewById(R.id.tv_totalLimit);
								TextView tv_leftLimit  = (TextView) view.findViewById(R.id.tv_leftLimit);

								tv_copartner.setText(valueInfo.getCustomerName());
								tv_project.setText(valueInfo.getProjectName());
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
		tv_title.setText("请选择合作商");
		mContext = context;
		mAidlClient = Contants.mAidlClient;
		gson = new GsonBuilder().create();
		listView = (ListView) findViewById(R.id.lv_tree);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				CoPartner valueInfo = (CoPartner) view.getTag();
				if(position == 0){
					return;
				}
				if(onItemSureClickListener!=null){
					onItemSureClickListener.OnItemClick(ListCoPartnerDialog.this,valueInfo);
				}else {
					ListCoPartnerDialog.this.dismiss();
				}
			}
		});
		initTreeData();
		
	}
	String code = null;
	public ListCoPartnerDialog(Context context,String code){
		this(context);
		this.code  = code;
	}
		
	
	OnItemSureClickListener onItemSureClickListener;
	
	public OnItemSureClickListener getOnItemSureClickListener() {
		return onItemSureClickListener;
	}
	public ListCoPartnerDialog setOnItemSureClickListener(
			OnItemSureClickListener onItemSureClickListener) {
		this.onItemSureClickListener = onItemSureClickListener;
		return this;
	}
	public interface OnItemSureClickListener{
		void OnItemClick(ListCoPartnerDialog dialog, CoPartner node);
	}
	
	Handler mHandler  = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			loadView.setVisibility(View.INVISIBLE);

			switch (msg.what) {
			case 100:
				listdata = (List<CoPartner>) msg.obj;
				if(listdata!=null){
					CoPartner coPartner = new CoPartner();
					coPartner.setBalance("已用额度");
					coPartner.setBusinessSum("总额度");
					coPartner.setCustomerName("合作商名称");
					coPartner.setProjectName("项目名称");
					coPartner.setBusinessType("业务类型");
					listdata.add(0,coPartner);
				}
				loadView.setVisibility(View.GONE);
				listView.setAdapter(new AllCompositeViewAdapter<>(R.layout.item_copartner, listdata, new AllCompositeViewAdapter.getViewListener() {
					@Override
					public void startView(View view, Object data, int position,
							ViewGroup parent) {
						// TODO Auto-generated method stub
						CoPartner valueInfo = (CoPartner) data;
						TextView tv_copartner = (TextView) view.findViewById(R.id.tv_copartner);
						TextView tv_project= (TextView) view.findViewById(R.id.tv_project);
						TextView tv_BusinessType = (TextView) view.findViewById(R.id.tv_BusinessType);
						TextView tv_totalLimit = (TextView) view.findViewById(R.id.tv_totalLimit);
						TextView tv_leftLimit  = (TextView) view.findViewById(R.id.tv_leftLimit);

						tv_copartner.setText(valueInfo.getCustomerName());
						tv_project.setText(valueInfo.getProjectName());
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
	List<CoPartner> listdata;
	ListView listView;
	/**
	 * 模拟加载数据
	 */
	public void initTreeData() {
		new Thread(){
			public void run() {
				HashMap<String, String> bodymap = new HashMap<>();
				bodymap.put("TransCode", "1293");
				String body = gson.toJson(bodymap);
				String result[] = mAidlClient.exec(Contants.header, body);
				if(result!=null&&result[2]!=null&&!"".equals(result[2])){
					Rs_CoPartner list1 = gson.fromJson(result[2],Rs_CoPartner.class);
					mHandler.obtainMessage(100,list1.getList()).sendToTarget();
				}else {
					mHandler.sendEmptyMessage(1001);
				}
			}
		}.start();
		

	}
}
