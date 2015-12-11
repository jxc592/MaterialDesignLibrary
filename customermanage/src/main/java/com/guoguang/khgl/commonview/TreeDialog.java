package com.guoguang.khgl.commonview;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.aidl.cilent.AidlClient;
import com.aidl.cilent.util.ToastCoustom;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guoguang.dkkhgl.R;
import com.guoguang.khgl.database.AreaLib;
import com.guoguang.khgl.entity.AreaEntity;
import com.guoguang.khgl.library.treeview.Node;
import com.guoguang.khgl.library.treeview.TreeModelBean;
import com.guoguang.khgl.model.Contants;

import java.util.ArrayList;
import java.util.List;


public class TreeDialog extends Dialog{
	Context mContext = null;
	AidlClient mAidlClient;
	Gson gson;
	
	View loadView;
	public TreeDialog(Context context) {
		super(context, R.style.alert_dialog);
		
		setContentView(R.layout.tree_main);
		
		loadView = findViewById(R.id.view_load);
		mContext = context;
		gson = new GsonBuilder().create();
		listView = (MultilayerListView) findViewById(R.id.lv_tree);
		listView.setOnChildClickListener(new MultilayerListView.OnChildClickListener() {

			@Override
			public void onClick(Node node, int position) {
				// TODO Auto-generated method stub\
				if(onItemSureClickListener!=null){
					onItemSureClickListener.OnItemClick(TreeDialog.this,node);
				}else {
					TreeDialog.this.dismiss();
				}
			}
		});
		
		initTreeData();
		
		
	}
	OnItemSureClickListener onItemSureClickListener;
	
	public OnItemSureClickListener getOnItemSureClickListener() {
		return onItemSureClickListener;
	}
	public TreeDialog setOnItemSureClickListener(
			OnItemSureClickListener onItemSureClickListener) {
		this.onItemSureClickListener = onItemSureClickListener;
		return this;
	}
	public interface OnItemSureClickListener{
		void OnItemClick(TreeDialog dialog, Node node);
	}
	
	
	
	Handler mHandler  = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 100:
				loadView.setVisibility(View.GONE);
				listView.setTreeModelBeans(treeModelBeans);
				listView.show();
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
	public void parcelData(final List<AreaLib> provinceList,
						   final List<AreaLib> cityList, final List<AreaLib> areaList) {
		// TODO Auto-generated method stub
		new Thread(){
			@Override
			public void run() {
				super.run();
				citys = new ArrayList<>();
				treeModelBeans = new ArrayList<TreeModelBean>();
				for(int i=0 ;i<provinceList.size();i++){
					String idstr = provinceList.get(i).getID();
					int id = Integer.parseInt(idstr);
					String nameString = provinceList.get(i).getName();
					treeModelBeans.add(new TreeModelBean(id, 0, nameString, idstr));
				}

				for(int i=0 ;i<cityList.size();i++){
					String idstr = cityList.get(i).getID();

					citys.add(idstr);

					String pidstr = idstr.substring(0,2)+"0000";

					int pid = Integer.parseInt(pidstr);

					int id = Integer.parseInt(idstr);
					String nameString = cityList.get(i).getName();
					treeModelBeans.add(new TreeModelBean(id, pid, nameString, idstr));

				}

				for(int i=0 ;i<areaList.size();i++){
					String idstr = areaList.get(i).getID();

					String pidstr = idstr.substring(0,4)+"00";
					int pid = Integer.parseInt(pidstr);

					int id = Integer.parseInt(idstr);
					String nameString = areaList.get(i).getName();
					treeModelBeans.add(new TreeModelBean(id, pid, nameString, idstr));
				}
				mHandler.obtainMessage(100).sendToTarget();
			}
		}.start();


	}
	MultilayerListView listView;
	List<TreeModelBean> treeModelBeans;
	/**
	 * 模拟加载数据
	 */
	public void initTreeData() {
		getWindow().getDecorView().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int time =1;
				while(true){
					if(Contants.areaEntity ==null){
						time ++;
						if(time>100){
							mHandler.obtainMessage(1001).sendToTarget();
							break;
							
						}
						try {
							Thread.sleep(2100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else {
						AreaEntity areaEntity =Contants.areaEntity;
						List<AreaLib> provinceList = areaEntity .getProvinceList();
						List<AreaLib> cityList = areaEntity .getCityList();
						List<AreaLib> areaList = areaEntity .getAreaList();
						
						parcelData(provinceList,cityList,areaList);
						break;
					}
				}
				
			}
			
		}, 300);
		

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
