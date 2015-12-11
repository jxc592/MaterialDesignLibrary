package com.guoguang.dkjdcx.pannel;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.aidl.cilent.AidlClient;
import com.aidl.cilent.util.AllCompositeViewAdapter;
import com.aidl.cilent.util.ToastCoustom;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guoguang.dkjdcx.R;
import com.guoguang.dkjdcx.app.LoanProSearchApp;
import com.guoguang.dkjdcx.entity.Cusbrief;

import java.util.List;

public class ListAreaDialog extends Dialog{
	Context mContext = null;
	AidlClient mAidlClient;
	Gson gson;
	View loadView;
	TextView tv_title;
	public ListAreaDialog(Context context, List<Cusbrief> list) {
		super(context, R.style.alert_dialog);
		
		setContentView(R.layout.dialog_listarea);
		
		tv_title = (TextView) findViewById(R.id.tv_title);
		
		loadView = findViewById(R.id.view_load);
		mContext = context;
		mAidlClient = LoanProSearchApp.mAidlClient;
		gson = new GsonBuilder().create();
		listView = (ListView) findViewById(R.id.lv_tree);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Cusbrief cusbrief = (Cusbrief) view.getTag();
				if(onItemSureClickListener!=null){
					onItemSureClickListener.OnItemClick(ListAreaDialog.this,cusbrief);
				}else {
					ListAreaDialog.this.dismiss();
				}
			}
		});
		initTreeData();
		mHandler.obtainMessage(100,list).sendToTarget();
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
		void OnItemClick(ListAreaDialog dialog, Cusbrief node);
	}
	
	Handler mHandler  = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			loadView.setVisibility(View.INVISIBLE);

			switch (msg.what) {
			case 100:
				List<Cusbrief> list = (List<Cusbrief>) msg.obj;
				loadView.setVisibility(View.GONE);
				listView.setAdapter(new AllCompositeViewAdapter<>(R.layout.item_value, list, new AllCompositeViewAdapter.getViewListener() {
					@Override
					public void startView(View view, Object data, int position,
							ViewGroup parent) {
						// TODO Auto-generated method stub
						Cusbrief valueInfo = (Cusbrief) data;
						TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
						TextView tv_id= (TextView) view.findViewById(R.id.tv_id);
						tv_id.setText(valueInfo.getCertID());
//						tv_name.setTextColor(Color.BLACK);
						tv_name.setText(valueInfo.getCustomerName());
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
	
	ListView listView;

	List<Cusbrief> listData;
	/**
	 * 模拟加载数据
	 */
	public void initTreeData() {

	}
}
