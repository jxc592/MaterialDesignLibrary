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
import com.guoguang.dksq.application.ProLoadApp;
import com.guoguang.dksq.entity.Cusbrief;

import java.util.ArrayList;
import java.util.List;


public class ListCusDialog extends Dialog{
	Context mContext = null;
	AidlClient mAidlClient;
	Gson gson;
	View loadView;
	TextView tv_title;
	SearchView sv;
	public ListCusDialog(Context context, final List<Cusbrief> list) {
		super(context, R.style.alert_dialog);
		
		setContentView(R.layout.dialog_listarea);
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.width = 600;
		getWindow().setAttributes(lp);
		sv = (SearchView) findViewById(R.id.sv_search);
        sv= (SearchView) findViewById(R.id.sv_search);
        //sv.setIconified(false);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if ("".equals(s)) {
                    ToastCoustom.show("尚未输入搜索条件");
                    return false;
                }
                if (list != null && list.size() > 0) {
                    List<Cusbrief> targetList = new ArrayList<Cusbrief>();
                    for (Cusbrief valueInfo : list) {
                        if (valueInfo.getCertID().contains(s) || valueInfo.getCustomerName().contains(s)) {
                            targetList.add(valueInfo);
                        }
                    }
                    if (targetList.size()  == 0) {
                        ToastCoustom.show("没有找到相应数据");
                    } else {
                        listView.setAdapter(new AllCompositeViewAdapter<>(R.layout.item_doublelist, targetList, new AllCompositeViewAdapter.getViewListener() {
                            @Override
                            public void startView(View view, Object data, int position,
                                                  ViewGroup parent) {
                                // TODO Auto-generated method stub
                                Cusbrief valueInfo = (Cusbrief) data;
                                TextView tv_certID = (TextView) view.findViewById(R.id.id_ID);
                                TextView tv_name= (TextView) view.findViewById(R.id.id_Name);
                                tv_certID.setText(valueInfo.getCertID());
                                tv_name.setText(valueInfo.getCustomerName());
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
		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_title.setText("请选择待办理客户");
		loadView = findViewById(R.id.view_load);
		mContext = context;
		mAidlClient = ProLoadApp.getmAidlClient();
		gson = new GsonBuilder().create();
		listView = (ListView) findViewById(R.id.lv_tree);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Cusbrief cusbrief = (Cusbrief) view.getTag();
				if(onItemSureClickListener!=null){
					onItemSureClickListener.OnItemClick(ListCusDialog.this,cusbrief);
				}else {
					ListCusDialog.this.dismiss();
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
	public ListCusDialog setOnItemSureClickListener(
			OnItemSureClickListener onItemSureClickListener) {
		this.onItemSureClickListener = onItemSureClickListener;
		return this;
	}
	public interface OnItemSureClickListener{
		void OnItemClick(ListCusDialog dialog, Cusbrief node);
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
				listView.setAdapter(new AllCompositeViewAdapter<>(R.layout.item_doublelist, list, new AllCompositeViewAdapter.getViewListener() {
					@Override
					public void startView(View view, Object data, int position,
							ViewGroup parent) {
						// TODO Auto-generated method stub
						Cusbrief valueInfo = (Cusbrief) data;
						TextView tv_name = (TextView) view.findViewById(R.id.id_ID);
						TextView tv_id= (TextView) view.findViewById(R.id.id_Name);
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
