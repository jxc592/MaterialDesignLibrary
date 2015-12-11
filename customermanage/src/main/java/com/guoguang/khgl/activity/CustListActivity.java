package com.guoguang.khgl.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.aidl.cilent.entity.HeaderCommonResponse;
import com.aidl.cilent.library.sweetdialog.SweetAlertDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guoguang.dkkhgl.R;
import com.guoguang.khgl.application.WincomApplication;
import com.guoguang.khgl.database.CusInfo;
import com.guoguang.khgl.database.CusInfoDao;
import com.guoguang.khgl.entity.CustomInfo;
import com.guoguang.khgl.entity.Rs_Cusinfo;
import com.guoguang.khgl.entity.Rs_Cuslist;
import com.guoguang.khgl.model.Contants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CustListActivity extends AppCompatActivity {
//	PullToRefreshListView pl_boxlist;
//	ListView lv_boxlListview;
//	AllCompositeViewAdapter<CusInfo> mAdapter;


	SwipeRefreshLayout refreshLayout;
	RecyclerView recyclerView;
	MyAdapter myAdapter;
	
	int targetPage=1;

    FrameLayout loadview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_datalist);


		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Contants.defaultcolor));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadview = (FrameLayout) findViewById(R.id.fl_loadview);
        recyclerView = (RecyclerView) findViewById(R.id.rv_draft);
		LinearLayoutManager manager = new LinearLayoutManager(this);
		manager.setOrientation(LinearLayoutManager.VERTICAL);
		recyclerView.setLayoutManager(manager);
		myAdapter = new MyAdapter();
		recyclerView.setAdapter(myAdapter);
        recyclerView.addOnScrollListener(new MyOnScrowChangeListener());
		refreshLayout  = (SwipeRefreshLayout) findViewById(R.id.srl_draft);
		refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				firstLoad();
			}
		});

		getLocalList();
		firstLoad();
	}
	

	SweetAlertDialog loaDialog;
	private void firstLoad() {
		// TODO Auto-generated method stub
        targetPage = 1;
		myAdapter.clear();
		loaDialog = new SweetAlertDialog(this,SweetAlertDialog.PROGRESS_TYPE);
		loaDialog.setCancelable(true);
		loaDialog.setTitleText("正在查询草稿箱");
		loaDialog.show();
		new Thread(){
			public void run() {
				Gson gson = new GsonBuilder().create();
				HashMap<String, Object> map = new HashMap<>();
				map.put("TransCode", "1214");
				map.put("targetPage", 1);
				map.put("perPageNum", 15);
				String body = gson.toJson(map);
				String result[] =Contants.mAidlClient.exec(Contants.header, body);
				if(result!=null&&result[2]!=null&&!"".equals(result[2])){
					Rs_Cuslist rs_Cuslist = gson.fromJson(result[2], Rs_Cuslist.class);
					List<CusInfo> list= rs_Cuslist.getDraftList();
					mHandler.obtainMessage(100, list).sendToTarget();
				}else {
					mHandler.obtainMessage(102).sendToTarget();
				}
			}
		}.start();
	}
	
	
	void loadMore(){
        loadview.setVisibility(View.VISIBLE);
		new Thread(){
			public void run() {
				Gson gson = new GsonBuilder().create();
				HashMap<String, Object> map = new HashMap<>();
				map.put("TransCode", "1214");
				map.put("targetPage",targetPage);
				map.put("perPageNum", 15);
				String body = gson.toJson(map);
				String result[] =Contants.mAidlClient.exec(Contants.header, body);
				if(result!=null&&result[2]!=null&&!"".equals(result[2])){
					Rs_Cuslist rs_Cuslist = gson.fromJson(result[2], Rs_Cuslist.class);
					List<CusInfo> list= rs_Cuslist.getDraftList();
					mHandler.obtainMessage(110, list).sendToTarget();
				}else {
					mHandler.sendEmptyMessage(102);
				}
			}
		}.start();
	}
	
	void getLocalList(){
		List<CusInfo> list=((WincomApplication)getApplication()).getmDaoSession().queryBuilder(CusInfo.class)
		.where(CusInfoDao.Properties.ManageUserName.eq(Contants.registInfo.getInputUserName()))
                .list();
		if(list==null||list.size()==0){
			return;
		}else {
			myAdapter.addList(list);
		}
	}
	
	void getDetail(final String seq){
		if(loaDialog!=null&&loaDialog.isShowing()){
			loaDialog.dismiss();
		}
		loaDialog = new SweetAlertDialog(this,SweetAlertDialog.PROGRESS_TYPE);
		loaDialog.setCancelable(true);
		loaDialog.setTitleText("正在获取草稿详情");
		loaDialog.show();
		new Thread(){
			public void run() {
				Gson gson = new GsonBuilder().create();
				HashMap<String, String> map = new HashMap<>();
				map.put("TransCode", "1215");
				map.put("seqNo",seq );
				String body = gson.toJson(map);
				String result[] =Contants.mAidlClient.exec(Contants.header, body);
				if(result!=null&&result[2]!=null&&!"".equals(result[2])){
					Rs_Cusinfo cusinfo= gson.fromJson(result[2],Rs_Cusinfo.class);
					Contants.tempCustomInfo = cusinfo.getCusInfo();
					mHandler.obtainMessage(101, seq).sendToTarget();
				}else {
					mHandler.obtainMessage(102).sendToTarget();
				}
			}
		}.start();
	}
	
	Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
            loadview.setVisibility(View.INVISIBLE);
			isLoadingMore = false;
			if (loaDialog != null && loaDialog.isShowing()) {
				loaDialog.dismiss();
			}
			if (refreshLayout.isRefreshing()) {
				refreshLayout.setRefreshing(false);
			}
			switch (msg.what) {
				case 100:
					targetPage++;
					myAdapter.addList((List<CusInfo>) msg.obj);
					break;
				case 101:
					String seqNo = (String) msg.obj;
					Intent mIntent = new Intent(CustListActivity.this, PreLoadCusinfoActivity.class);
					mIntent.putExtra("isNew", "false");
					mIntent.putExtra("seqNo", seqNo);
					startActivity(mIntent);
					break;
				case 102:
					new SweetAlertDialog(CustListActivity.this, SweetAlertDialog.NORMAL_TYPE).setTitleText("与服务器断开连接，请检查网络").show();
					break;
				case 110:
					targetPage++;
					myAdapter.addList((List<CusInfo>) msg.obj);
					break;
				case 1000:
					break;
				default:
					break;
			}
		}
	};
	

	class MyViewHolder extends RecyclerView.ViewHolder{
		TextView tv_nm ;
		TextView tv_fullname ;
		TextView tv_certid ;
		TextView tv_updatetime ;
		TextView tv_manager ;

		ImageView iv_del;
		private CusInfo tag;

		public MyViewHolder(View itemView) {
			super(itemView);
			tv_nm = (TextView) itemView.findViewById(R.id.tv_NM);
			tv_fullname = (TextView) itemView.findViewById(R.id.tv_FullName);
			tv_certid = (TextView) itemView.findViewById(R.id.tv_CertId);
			tv_updatetime = (TextView) itemView.findViewById(R.id.tv_UpdateTime);
			tv_manager = (TextView) itemView.findViewById(R.id.tv_manager);
			iv_del = (ImageView) itemView.findViewById(R.id.iv_del);

			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
				String cusstr = tag.getCusInfo();
				if(cusstr!=null&&cusstr.length()>0){
					Contants.tempCustomInfo = new GsonBuilder().create().fromJson(cusstr, CustomInfo.class);
					mHandler.obtainMessage(101, tag.getSeqNo()).sendToTarget();
				}else {
					getDetail(tag.getSeqNo());
				}
				}
			});
		}

		public void setTag(CusInfo tag) {
			this.tag = tag;
		}
	}
	class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

		List<CusInfo> list;

		public MyAdapter(List<CusInfo> list) {
			this.list = list;
		}

		public MyAdapter() {
			list = new ArrayList<>();
		}

		@Override
		public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_box,null);
			return new MyViewHolder(view);
		}

		@Override
		public void onBindViewHolder(MyViewHolder holder, final int position) {
			CusInfo cusInfo = list.get(position);
			holder.tv_nm.setText("" + (position + 1));
			holder.tv_fullname.setText(cusInfo.getFullName());
			holder.tv_certid.setText(cusInfo.getCertID());
			holder.tv_manager.setText(cusInfo.getManageUserName());
			holder.tv_updatetime.setText(cusInfo.getUpdateTime());
            holder.iv_del.setVisibility(View.VISIBLE);
			holder.iv_del.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					new SweetAlertDialog(view.getContext(), SweetAlertDialog.NORMAL_TYPE).setTitleText("确认删除该条草稿吗？")
							.setCancelText("取消").setConfirmText("确认")
							.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
								@Override
								public void onClick(SweetAlertDialog sweetAlertDialog) {
									sweetAlertDialog.dismiss();
									if (list.get(position).getId() != null && !"".equals(list.get(position).getId())) {
										deleteCaogao(list.get(position).getSeqNo());
									}
									deleteCaogaos(list.get(position).getSeqNo());
									remove(position);
								}
							})
							.show();
				}
			});
			holder.setTag(cusInfo);
		}
		@Override
		public int getItemCount() {
			return list.size();
		}

		void addList(List<CusInfo> cusInfos){
			int frompo = list.size();
			list.addAll(cusInfos);
			notifyItemRangeInserted(frompo,cusInfos.size());
		}

		void addOne(CusInfo cusInfo){
			int frompo = list.size();
			list.add(cusInfo);
			notifyItemInserted(frompo);
		}

		void clear(){
			int toPo = list.size()-1;
			list.clear();
			notifyItemMoved(0, toPo);
			notifyDataSetChanged();
		}

		public void remove(int position){
			list.remove(position);
			notifyItemRemoved(position);
			notifyDataSetChanged();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
			case android.R.id.home:
//				new SweetAlertDialog(CustListActivity.this,SweetAlertDialog.NORMAL_TYPE).
//						setTitleText("您将舍弃刚刚录入信息并退出").setConfirmText("确认").
//						setCancelText("取消").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//					@Override
//					public void onClick(SweetAlertDialog sweetAlertDialog) {
//						finish();
//					}
//				});
                finish();
				break;
		}
		return super.onOptionsItemSelected(item);
	}



	boolean isLoadingMore;
	class MyOnScrowChangeListener extends RecyclerView.OnScrollListener{
		public MyOnScrowChangeListener() {
		}
		@Override
		public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
			super.onScrolled(recyclerView, dx, dy);
			LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
			int lastVisibleItem =  manager.findLastVisibleItemPosition();
			int totalItemCount = manager.getItemCount();
			//lastVisibleItem >= totalItemCount - 4 表示剩下4个item自动加载，各位自由选择
			// dy>0 表示向下滑动
			if (lastVisibleItem >= totalItemCount - 4 && dy > 0) {
				if(!isLoadingMore){
					loadMore();//这里多线程也要手动控制isLoadingMore
					isLoadingMore = true;
				} else{
					Log.d("LoanListActivity", "ignore manually update!");
				}
			}
		}
		@Override
		public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
			super.onScrollStateChanged(recyclerView, newState);

		}
	}


	void deleteCaogao(String seqno){
		List<CusInfo> list =WincomApplication.getmDaoSession().queryBuilder(CusInfo.class).
				where(CusInfoDao.Properties.SeqNo.eq(seqno)).list();
		if(list.size()>0){
			WincomApplication.getmDaoSession().getCusInfoDao().deleteInTx(list);
		}
	}
	void deleteCaogaos(final String seqno){
		new Thread(){
			@Override
			public void run() {
				super.run();
				Gson gson = new GsonBuilder().create();
				HashMap<String,String> map = new HashMap<String,String>();
				map.put("TransCode","1216");
				map.put("SeqNo", seqno);
				String result[] = WincomApplication.getmAidlClient().exec(Contants.header, gson.toJson(map));
				HeaderCommonResponse responce = gson.fromJson(result[1], HeaderCommonResponse.class);
				mHandler.obtainMessage(1920,responce).sendToTarget();
			}
		}.start();
	}
}
