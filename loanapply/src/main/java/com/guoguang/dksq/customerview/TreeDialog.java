package com.guoguang.dksq.customerview;

import android.app.Dialog;
import android.content.Context;
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
import com.guoguang.dksq.R;
import com.guoguang.dksq.contants.Contants;
import com.guoguang.dksq.database.AreaLib;
import com.guoguang.dksq.entity.AreaEntity;
import com.guoguang.dksq.util.TreeModelBean;

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
        loadView.setVisibility(View.INVISIBLE);
		mContext = context;
		gson = new GsonBuilder().create();
		listView = (ListView) findViewById(R.id.lv_tree);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TreeModelBean bean = (TreeModelBean) view.getTag();
                if(tag ==1){
                    setCitys(bean.getId());
                }else if(tag ==2){
                    setAreas(bean.getId());
                }else {
                    onNodeClick(bean);
                }
            }
		});
		findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tag==3){
                    tag=2;
                    mAdapter = new AllCompositeViewAdapter<TreeModelBean>(R.layout.item_tree_listview, treeModelBeanscity, new AllCompositeViewAdapter.getViewListener() {
                        @Override
                        public void startView(View view, Object data, int position, ViewGroup parent) {
                            TreeModelBean node = (TreeModelBean) data;
                            ImageView iv = (ImageView) view.findViewById(R.id.id_treenode_icon);
                            TextView tv = (TextView) view.findViewById(R.id.id_treenode_label);
                            if(tag == 3){
                                iv.setVisibility(View.INVISIBLE);
                            }
                            tv.setText(node.getLabel());
                        }
                    });
                    listView.setAdapter(mAdapter);
                }else if(tag ==2){
                    tag=1;
                    mAdapter = new AllCompositeViewAdapter<TreeModelBean>(R.layout.item_tree_listview, treeModelBeans, new AllCompositeViewAdapter.getViewListener() {
                        @Override
                        public void startView(View view, Object data, int position, ViewGroup parent) {
                            TreeModelBean node = (TreeModelBean) data;
                            ImageView iv = (ImageView) view.findViewById(R.id.id_treenode_icon);
                            TextView tv = (TextView) view.findViewById(R.id.id_treenode_label);
                            if(tag == 3){
                                iv.setVisibility(View.INVISIBLE);
                            }
                            tv.setText(node.getLabel());
                        }
                    });
                    listView.setAdapter(mAdapter);
                }else if(tag ==1){
//                    ToastCoustom.show("");
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
		void OnItemClick(TreeDialog dialog, TreeModelBean bean);
	}
	
	
	
	Handler mHandler  = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 100:
				loadView.setVisibility(View.GONE);
                mAdapter = new AllCompositeViewAdapter<TreeModelBean>(R.layout.item_tree_listview, new ArrayList<TreeModelBean>(), new AllCompositeViewAdapter.getViewListener() {
                    @Override
                    public void startView(View view, Object data, int position, ViewGroup parent) {
                        TreeModelBean node = (TreeModelBean) data;
                        ImageView iv = (ImageView) view.findViewById(R.id.id_treenode_icon);
                        TextView tv = (TextView) view.findViewById(R.id.id_treenode_label);
                        if(tag == 3){
                            iv.setVisibility(View.INVISIBLE);
                        }
                        tv.setText(node.getLabel());
                    }
                });
                listView.setAdapter(mAdapter);
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
	int tag = 1;

	public void parcelData(final List<AreaLib> provinceList,
			final List<AreaLib> cityList, final List<AreaLib> areaList) {
		// TODO Auto-generated method stub
        tag = 1;
        treeModelBeans = new ArrayList<TreeModelBean>();
				for(int i=0 ;i<provinceList.size();i++){
					String idstr = provinceList.get(i).getID();
					int id = Integer.parseInt(idstr);
					String nameString = provinceList.get(i).getName();
					treeModelBeans.add(new TreeModelBean(id, 0, nameString, idstr));
				}
        mAdapter = new AllCompositeViewAdapter<TreeModelBean>(R.layout.item_tree_listview,treeModelBeans, new AllCompositeViewAdapter.getViewListener() {
            @Override
            public void startView(View view, Object data, int position, ViewGroup parent) {
                TreeModelBean node = (TreeModelBean) data;
                ImageView iv = (ImageView) view.findViewById(R.id.id_treenode_icon);
                TextView tv = (TextView) view.findViewById(R.id.id_treenode_label);
                if(tag == 3){
                    iv.setVisibility(View.INVISIBLE);
                }
                tv.setText(node.getLabel());
            }
        });
        listView.setAdapter(mAdapter);

	}
	ListView listView;
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
						List<AreaLib> areaList = areaEntity.getAreaList();
						parcelData(provinceList,cityList,areaList);
						break;
					}
				}
				
			}
			
		}, 300);
		

	}

    AllCompositeViewAdapter<TreeModelBean> mAdapter;


    List<TreeModelBean> treeModelBeanscity;
    void setCitys(int Pvovinceid){
        tag=2;

        List<AreaLib> cityList = Contants.areaEntity.getCityList();

        treeModelBeanscity = new ArrayList<>();

        for(int i=0 ;i<cityList.size();i++){

            String idstr = cityList.get(i).getID();

            String pidstr = idstr.substring(0,2)+"0000";

            int id = Integer.parseInt(idstr);

            if(pidstr.equals(Pvovinceid+"")){
                treeModelBeanscity.add(new TreeModelBean(id,0,cityList.get(i).getName(),idstr));
            }
        }
        mAdapter = new AllCompositeViewAdapter<TreeModelBean>(R.layout.item_tree_listview, treeModelBeanscity, new AllCompositeViewAdapter.getViewListener() {
            @Override
            public void startView(View view, Object data, int position, ViewGroup parent) {
                TreeModelBean node = (TreeModelBean) data;
                ImageView iv = (ImageView) view.findViewById(R.id.id_treenode_icon);
                TextView tv = (TextView) view.findViewById(R.id.id_treenode_label);
                if(tag == 3){
                    iv.setVisibility(View.INVISIBLE);
                }
                tv.setText(node.getLabel());
            }
        });
        listView.setAdapter(mAdapter);
    }

    List<TreeModelBean> treeModelBeansarea;
    void setAreas(int Cityid){
        tag = 3;
        treeModelBeansarea = new ArrayList<>();
        List<AreaLib> areaList = Contants.areaEntity.getAreaList();
        for(int i=0 ;i<areaList.size();i++){
            String idstr = areaList.get(i).getID();

            String pidstr = idstr.substring(0,4)+"00";
            int pid = Integer.parseInt(pidstr);

            String nameString = areaList.get(i).getName();
            int id  = Integer.parseInt(idstr);
            if(Cityid == pid){
                treeModelBeansarea.add(new TreeModelBean(id, pid, nameString, idstr));
            }
        }
        mAdapter = new AllCompositeViewAdapter<TreeModelBean>(R.layout.item_tree_listview,treeModelBeansarea, new AllCompositeViewAdapter.getViewListener() {
            @Override
            public void startView(View view, Object data, int position, ViewGroup parent) {
                TreeModelBean node = (TreeModelBean) data;
                ImageView iv = (ImageView) view.findViewById(R.id.id_treenode_icon);
                TextView tv = (TextView) view.findViewById(R.id.id_treenode_label);
                if(tag == 3){
                    iv.setVisibility(View.INVISIBLE);
                }
                tv.setText(node.getLabel());
            }
        });
        listView.setAdapter(mAdapter);
    }
    void onNodeClick(TreeModelBean bean){
        if(onItemSureClickListener!=null){
            onItemSureClickListener.OnItemClick(TreeDialog.this,bean);
        }
    }
}
