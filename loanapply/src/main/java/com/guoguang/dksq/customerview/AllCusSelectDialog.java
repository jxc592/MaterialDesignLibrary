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
import com.guoguang.dksq.entity.Cusbrief;
import com.guoguang.dksq.entity.Rs_Cuslist;

import java.util.HashMap;
import java.util.List;

public class AllCusSelectDialog extends Dialog{
	Context mContext = null;
	AidlClient mAidlClient;
	Gson gson;
	View loadView;
	TextView tv_title;
    SearchView sv;
	public AllCusSelectDialog(Context context) {
		super(context, R.style.alert_dialog);
		setContentView(R.layout.dialog_allcussel);

		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.width = 600;
		getWindow().setAttributes(lp);
        sv= (SearchView) findViewById(R.id.sv_search);
        sv.setIconified(false);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if ("".equals(s)) {
                    ToastCoustom.show("尚未输入搜索条件");
                    return false;
                }
				searchCuslist(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_title.setText("请选择客户");
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
				Cusbrief valueInfo = (Cusbrief) view.getTag();
				if(onItemSureClickListener!=null){
					onItemSureClickListener.OnItemClick(AllCusSelectDialog.this,valueInfo);
				}else {
					AllCusSelectDialog.this.dismiss();
				}
			}
		});

	}

	OnItemSureClickListener onItemSureClickListener;
	
	public OnItemSureClickListener getOnItemSureClickListener() {
		return onItemSureClickListener;
	}
	public AllCusSelectDialog setOnItemSureClickListener(
			OnItemSureClickListener onItemSureClickListener) {
		this.onItemSureClickListener = onItemSureClickListener;
		return this;
	}
	public interface OnItemSureClickListener{
		void OnItemClick(AllCusSelectDialog dialog, Cusbrief node);
	}
	
	Handler mHandler  = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			loadView.setVisibility(View.INVISIBLE);

			switch (msg.what) {
			case 100:
				listdata = (List<Cusbrief>) msg.obj;
				loadView.setVisibility(View.GONE);
				listView.setAdapter(new AllCompositeViewAdapter<>(R.layout.item_doublelist, listdata, new AllCompositeViewAdapter.getViewListener() {
					@Override
					public void startView(View view, Object data, int position,
							ViewGroup parent) {
						// TODO Auto-generated method stub
						Cusbrief valueInfo = (Cusbrief) data;
						TextView tv_certID = (TextView) view.findViewById(R.id.id_ID);
						TextView tv_name= (TextView) view.findViewById(R.id.id_Name);
						//iv_icoImageView.setVisibility(View.INVISIBLE);
						//tv_name.setTextColor(Color.BLACK);
						tv_certID.setText(valueInfo.getCertID());
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
	
	List<Cusbrief> listdata;
	ListView listView;
	/**
	 * 模拟加载数据
	 */
	public void searchCuslist(final String custname) {
        loadView.setVisibility(View.VISIBLE);
		new Thread(){
			public void run() {
				HashMap<String, String> bodymap = new HashMap<>();
				bodymap.put("TransCode", "1232");
				bodymap.put("CustomerId", "");
				bodymap.put("CustomerName", custname);
				bodymap.put("CertId", "");
				String body = gson.toJson(bodymap);
				String result[] = mAidlClient.exec(Contants.header, body);
				if(result!=null&&result[2]!=null&&!"".equals(result[2])){
					Rs_Cuslist list1 = gson.fromJson(result[2],Rs_Cuslist.class);
					mHandler.obtainMessage(100,list1.getList()).sendToTarget();
				}else {
					mHandler.sendEmptyMessage(1001);
				}
			}
		}.start();
	}
}
