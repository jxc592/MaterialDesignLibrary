package com.guoguang.khgl.commonview;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.aidl.cilent.AidlClient;
import com.aidl.cilent.util.AllCompositeViewAdapter;
import com.aidl.cilent.util.ToastCoustom;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guoguang.dkkhgl.R;
import com.guoguang.khgl.entity.Rs_AdressList;
import com.guoguang.khgl.entity.ValueInfo;
import com.guoguang.khgl.library.treeview.TreeModelBean;
import com.guoguang.khgl.model.Contants;

import java.util.HashMap;
import java.util.List;
public class ListAreaDialog extends Dialog{
	Context mContext = null;
	AidlClient mAidlClient;
	Gson gson;
	
	View loadView;
	public ListAreaDialog(Context context) {
		super(context, R.style.alert_dialog);
		
		setContentView(R.layout.dialog_listarea);
		
		loadView = findViewById(R.id.view_load);
		mContext = context;
		mAidlClient = Contants.mAidlClient;
		gson = new GsonBuilder().create();
		listView = (ListView) findViewById(R.id.lv_tree);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

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
				List<ValueInfo> list = (List<ValueInfo>) msg.obj;
				loadView.setVisibility(View.GONE);
				listView.setAdapter(new AllCompositeViewAdapter<>(R.layout.item_tree_listview, list, new AllCompositeViewAdapter.getViewListener() {
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
	
	List<String> citys;
	ListView listView;
	List<TreeModelBean> treeModelBeans;
	/**
	 * 模拟加载数据
	 */
	public void initTreeData() {
		
		new Thread(){
			public void run() {
				HashMap<String, String> bodymap = new HashMap<>();
				bodymap.put("TransCode", "1295");
				String body = gson.toJson(bodymap);
				String result[] = mAidlClient.exec(Contants.header, body);
				List<ValueInfo> list = null;
				if(result!=null&&result[2]!=null&&!"".equals(result[2])){
					Rs_AdressList list1 = gson.fromJson(result[2],Rs_AdressList.class);
					mHandler.obtainMessage(100,list1.getList()).sendToTarget();
				}else {
					mHandler.sendEmptyMessage(1001);
				}
				
			}
		}.start();
		

	}
	IConfigOnclick mConfigOnclick;
	private interface IConfigOnclick{
		 void configClick();
	}
	public IConfigOnclick getmConfigOnclick() {
		return mConfigOnclick;
	}
	public void setmConfigOnclick(IConfigOnclick mConfigOnclick) {
		this.mConfigOnclick = mConfigOnclick;
	}
}
