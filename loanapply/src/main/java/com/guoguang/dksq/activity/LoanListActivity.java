package com.guoguang.dksq.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.aidl.cilent.entity.HeaderCommonResponse;
import com.aidl.cilent.library.sweetdialog.SweetAlertDialog;
import com.aidl.cilent.util.PhoneInfoUtil;
import com.aidl.cilent.util.ToastCoustom;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guoguang.dksq.R;
import com.guoguang.dksq.application.ProLoadApp;
import com.guoguang.dksq.contants.Contants;
import com.guoguang.dksq.database.Assure;
import com.guoguang.dksq.database.AssureDao;
import com.guoguang.dksq.database.Guarantee;
import com.guoguang.dksq.database.GuaranteeDao;
import com.guoguang.dksq.database.House;
import com.guoguang.dksq.database.HouseDao;
import com.guoguang.dksq.database.LoanBusiness;
import com.guoguang.dksq.database.LoanBusinessDao;
import com.guoguang.dksq.database.LoanRecord;
import com.guoguang.dksq.database.LoanRecordDao;
import com.guoguang.dksq.database.PhotoEntity;
import com.guoguang.dksq.database.PhotoEntityDao;
import com.guoguang.dksq.entity.LoanInfoEntity;
import com.guoguang.dksq.entity.Rs_DraftList;
import com.guoguang.dksq.socket.DownLoadTask;
import com.guoguang.dksq.socket.IFileSocketHandle;
import com.guoguang.mitfs.client.bean.MitFsException;
import com.guoguang.mitfs.client.bean.MitFsRequest;
import com.guoguang.mitfs.client.bean.MitFsResponse;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class LoanListActivity extends AppCompatActivity{

	SwipeRefreshLayout refreshLayout;
	RecyclerView recyclerView;
    MyAdapter rvAdapter;
    LinearLayoutManager manager;

    ProLoadApp app;

    FrameLayout loadview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_datalist);

        app = (ProLoadApp) getApplication();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Contants.defaultcolor));

        loadview = (FrameLayout) findViewById(R.id.fl_loadview);

		recyclerView = (RecyclerView) findViewById(R.id.rv);
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvAdapter = new MyAdapter();
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(rvAdapter);
        recyclerView.addOnScrollListener(new MyOnScrowChangeListener());
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
		refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				firstLoad();
			}
		});
        firstLoad();
	}
    /**
     * 根据草稿的流水查询对应的  业务信息，担保信息，抵押物信息，还有影像信息；
     * @param seqno 草稿流水号
     */
    void findLoanInfo(String seqno){
        int isExit = ProLoadApp.getmDaoSession().queryBuilder(LoanRecord.class).where(LoanRecordDao.Properties.SeqNo.eq(seqno)).list().size();
        if(isExit>0){
            List<LoanBusiness> loanBusinessess = ProLoadApp.getmDaoSession().queryBuilder(LoanBusiness.class)
                    .where(LoanBusinessDao.Properties.SeqNo.eq(seqno)).list();
            if(loanBusinessess.size()==0){
                Snackbar.make(getWindow().getDecorView(),"查询不到相关的草稿信息，程序存在异常，请联系技术人员",Snackbar.LENGTH_LONG).show();
                return;
            }else {
                Contants.loanBusinessInfo = loanBusinessess.get(0);
            }
            List<Assure> assures = ProLoadApp.getmDaoSession().queryBuilder(Assure.class)
                    .where(AssureDao.Properties.SeqNo.eq(seqno)).list();
            if(assures.size()>0){
                Contants.assureInfo = assures.get(0);
            }

            List<House> houses = ProLoadApp.getmDaoSession().queryBuilder(House.class)
                    .where(HouseDao.Properties.SeqNo.eq(seqno)).list();
            if(houses.size()>0){
                Contants.houseInfo = houses.get(0);
            }
            List<Guarantee> guarantees = ProLoadApp.getmDaoSession().queryBuilder(Guarantee.class)
                    .where(GuaranteeDao.Properties.SeqNo.eq(seqno)).list();
            if(guarantees.size()>0){
                Contants.guranteeinfos = guarantees;
            }
            List<PhotoEntity> photoEntities = ProLoadApp.getmDaoSession().queryBuilder(PhotoEntity.class)
                    .where(PhotoEntityDao.Properties.SeqNo.eq(seqno)).list();
            HashMap<String, HashMap<String, String>> tosaveHashMap=new HashMap<>();

            for(PhotoEntity entity:photoEntities){
                String node = entity.getPhotoTypeCode();
                String pos_node =entity.getPosition();
                String valuePath  = entity.getPhotoPath();
                HashMap<String, String> map =tosaveHashMap.get(node);
                if(map!=null){
                    map.put(pos_node, valuePath);
                }else {
                    map=new HashMap<>();
                    map.put(pos_node, valuePath);
                }
                tosaveHashMap.put(node, map);
            }
            Contants.toSavePhotoHashMap = tosaveHashMap;

            Intent mIntent = new Intent(this,ProLoadBusInfoActivity.class);
            mIntent.putExtra("isNew","false");
            mIntent.putExtra("applyType",Contants.loanBusinessInfo.getApplyType());
            startActivity(mIntent);

        }else{
            //TODO 网络查询草稿
            getDetail(seqno);
        }
    }


    void setToTempPhoto(String seqno){
        List<PhotoEntity> photoEntities = ProLoadApp.getmDaoSession().queryBuilder(PhotoEntity.class)
                .where(PhotoEntityDao.Properties.SeqNo.eq(seqno)).list();
        HashMap<String, HashMap<String, String>> tosaveHashMap=new HashMap<>();

        for(PhotoEntity entity:photoEntities){
            String node = entity.getPhotoTypeCode();
            String pos_node =entity.getPosition();
            String valuePath  = entity.getPhotoPath();
            HashMap<String, String> map =tosaveHashMap.get(node);
            if(map!=null){
                map.put(pos_node, valuePath);
            }else {
                map=new HashMap<>();
                map.put(pos_node, valuePath);
            }
            tosaveHashMap.put(node, map);
        }
        Contants.toSavePhotoHashMap = tosaveHashMap;
    }
    /**
     * viewholder
     */
    class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tv_nm, tv_fullname, tv_ContractFlag, tv_updatetime, tv_manager, tv_BusinessType;
            LoanRecord loanRecord;
            ImageView iv_del;

            public MyViewHolder(View itemView) {
                super(itemView);
                holderView = itemView;
                tv_nm = (TextView) itemView.findViewById(R.id.tv_NM);
                tv_fullname = (TextView) itemView.findViewById(R.id.tv_FullName);
                tv_ContractFlag = (TextView) itemView.findViewById(R.id.tv_ContractFlag);
                tv_updatetime = (TextView) itemView.findViewById(R.id.tv_UpdateTime);
                tv_manager = (TextView) itemView.findViewById(R.id.tv_manager);
                tv_BusinessType = (TextView) itemView.findViewById(R.id.tv_BusinessType);
                iv_del = (ImageView) itemView.findViewById(R.id.iv_del);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        findLoanInfo(loanRecord.getSeqNo());
                    }
                });

            }

        View holderView;

        public LoanRecord getLoanRecord() {
            return loanRecord;
        }

        public void setLoanRecord(LoanRecord loanRecord) {
            this.loanRecord = loanRecord;
        }
    }

    /**
     * recycler的自定义adapter
     */
	class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
        List<LoanRecord> loanRecords;

        public MyAdapter(List<LoanRecord> loanRecords) {
            this.loanRecords = loanRecords;
        }

        public MyAdapter(){
            loanRecords = new ArrayList<>();
        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_box,null);
                itemview.setTag(1);
                return new MyViewHolder(itemview);

        }

        public List<LoanRecord> getDatas(){
            return  loanRecords;
        }

        @Override
        public int getItemViewType(int position) {
            return 0;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            final LoanRecord loanRecord = loanRecords.get(position);
            holder.setLoanRecord(loanRecord);
            holder.tv_nm.setText(""+(position+1));
            holder.tv_fullname.setText(loanRecord.getCustomerName());
            if("1".equals(loanRecord.getContractFlag())){
                holder.tv_ContractFlag.setText("单笔授信业务申请");
            }else if ("2".equals(loanRecord.getContractFlag())) {
                holder.tv_ContractFlag.setText("额度项下业务申请");
            }else {
                holder.tv_ContractFlag.setText("");
            }

            holder.tv_manager.setText(loanRecord.getInputUserName());
            holder.tv_updatetime.setText(loanRecord.getUpdateTime());

            if("1110010".equals(loanRecord.getBusinessType())){
                holder.tv_BusinessType.setText("个人一手住房按揭贷款");
            }else if ("1110020".equals(loanRecord.getBusinessType())) {
                holder.tv_BusinessType.setText("个人二手住房按揭贷款");
            }else if ("1110250".equals(loanRecord.getBusinessType())) {
                holder.tv_BusinessType.setText("个人组合贷款");
            }else if ("1110030".equals(loanRecord.getBusinessType())) {
                holder.tv_BusinessType.setText("个人一手商业用房按揭贷款");
            }else if ("1110040".equals(loanRecord.getBusinessType())) {
                holder.tv_BusinessType.setText("个人二手商业用房按揭贷款");
            }

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
                                    if (loanRecord.getId() != null && !"".equals(loanRecord.getId())) {
                                        deleteCaogao(loanRecord.getSeqNo());
                                    }
                                    deleteCaogaos(loanRecord.getSeqNo());
                                    remove(position);
                                }
                            })
                            .show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return loanRecords.size();
        }

        public void addLoanRecord(LoanRecord loanRecord){
            loanRecords.add(loanRecord);
            notifyItemInserted(loanRecords.size() - 1);
        }

        public void addLoanRecords(List<LoanRecord> list){
            int fromposition = loanRecords.size();
            loanRecords.addAll(list);
            notifyItemRangeInserted(fromposition, list.size());
        }

        public void clearRecord(){
            int fromposition = loanRecords.size()-1;
            loanRecords.clear();
            notifyItemMoved(0, fromposition);
            notifyDataSetChanged();
        }

        public void changeStateToManage(boolean x){
            for(int i =0 ;i<loanRecords.size();i++){
                loanRecords.get(i).setDeltag(x);
            }
            notifyDataSetChanged();
        }

        public void remove(int position){
            loanRecords.remove(position);
            notifyItemRemoved(position);
            notifyDataSetChanged();
        }
    }


	int targetPage=1;
	SweetAlertDialog loaDialog;
	private void firstLoad() {
		// TODO Auto-generated method stub
        targetPage =1;
        rvAdapter.clearRecord();
        List<LoanRecord> list = ProLoadApp.getmDaoSession().queryBuilder(LoanRecord.class)
                .where(LoanRecordDao.Properties.InputUserID.eq(Contants.registInfo.getInputUserID()))
                .list();
        rvAdapter.addLoanRecords(list);
		new Thread(){
			public void run() {
				Gson gson = new GsonBuilder().create();
				HashMap<String, Object> map = new HashMap<>();
				map.put("TransCode", "1224");
				map.put("targetPage", 1);
				map.put("perPageNum", 15);
				String body = gson.toJson(map);
				String result[] = Contants.mAidlClient.exec(Contants.header, body);
				if(result!=null&&result[2]!=null&&!"".equals(result[2])){
                    Rs_DraftList draftList = gson.fromJson(result[2], Rs_DraftList.class);
                    if(draftList!=null&&draftList.getDraftList()!=null&&draftList.getDraftList().size()>0){
                        List<LoanRecord> loanRecords = draftList.getDraftList();
                        mHandler.obtainMessage(100, draftList.getDraftList()).sendToTarget();
                    }else {
                        mHandler.sendEmptyMessage(120);
                    }
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
				map.put("TransCode", "1224");
				map.put("targetPage",targetPage);
				map.put("perPageNum", 15);
				String body = gson.toJson(map);
				String result[] =Contants.mAidlClient.exec(Contants.header, body);
                if(result!=null&&result[2]!=null&&!"".equals(result[2])){
                    Rs_DraftList draftList = gson.fromJson(result[2], Rs_DraftList.class);
                    if(draftList!=null&&draftList.getDraftList()!=null&&draftList.getDraftList().size()>0){
                        List<LoanRecord> loanRecords = draftList.getDraftList();
                        mHandler.obtainMessage(110, draftList.getDraftList()).sendToTarget();
                    }else {
                        mHandler.sendEmptyMessage(120);
                    }
                }else {
                    mHandler.obtainMessage(102).sendToTarget();
                }
			}
		}.start();
	}

	void getLocalList(){
		List<LoanRecord> list= ProLoadApp.getmDaoSession().queryBuilder(LoanRecord.class)
		.list();
		if(list==null||list.size()==0){
			return;
		}else {
			//mAdapter.add(list);
		}
	}

    LoanInfoEntity mLoanInfoEntity;
    Iterator<PhotoEntity> toDownIterator;

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
				map.put("TransCode", "1225");
				map.put("seqNo",seq );
				String body = gson.toJson(map);
				String result[] =Contants.mAidlClient.exec(Contants.header, body);
				if(result!=null&&result[2]!=null&&!"".equals(result[2])){
                    LoanInfoEntity loanInfoEntity= gson.fromJson(result[2], LoanInfoEntity.class);
                    Contants.setLoanInfoEntity(loanInfoEntity);
                    mLoanInfoEntity = loanInfoEntity;
                    saveDownload(mLoanInfoEntity);
                    List<PhotoEntity> list = loanInfoEntity.getPhotoMap();
                    if(list==null||list.size()==0){
                        mHandler.obtainMessage(101, seq).sendToTarget();
                        return;
                    }
                    toDownIterator = list.iterator();
                    mHandler.sendEmptyMessage(130);
				}else {
					mHandler.obtainMessage(102).sendToTarget();
				}
			}
		}.start();
	}

	android.os.Handler mHandler = new android.os.Handler(){
		public void handleMessage(android.os.Message msg) {
            //
            loadview.setVisibility(View.INVISIBLE);
            isLoadingMore = false;

            if(loaDialog!=null&&loaDialog.isShowing()){
				loaDialog.dismiss();
			}
			if(refreshLayout.isRefreshing()){
                refreshLayout.setRefreshing(false);
			}
			switch (msg.what) {
                case 100:
                    targetPage++;
                    List<LoanRecord> list = (List<LoanRecord>) msg.obj;
                    if(list == null||list.size()==0){
                        break;
                    }
                    List<String> allData = new ArrayList<>();
                    for(LoanRecord record:rvAdapter.getDatas()){
                        String seq =record.getSeqNo();
                        allData.add(seq);
                    }
                    for(LoanRecord loanRecord:list){
                        if(!allData.contains(loanRecord.getSeqNo())){
                            rvAdapter.addLoanRecord(loanRecord);
                        }
                    }
//                    rvAdapter.addLoanRecords(list);
                    break;
                case 110:
                    targetPage++;
                    List<LoanRecord> newlist = (List<LoanRecord>) msg.obj;
                    if(newlist == null||newlist.size()==0){
                        break;
                    }
                    List<String> allData2 = new ArrayList<>();
                    for(LoanRecord record:rvAdapter.getDatas()){
                        String seq =record.getSeqNo();
                        allData2.add(seq);
                    }
                    for(LoanRecord loanRecord:newlist){
                        if(!allData2.contains(loanRecord.getSeqNo())){
                            rvAdapter.addLoanRecord(loanRecord);
                        }
                    }
                    break;
                case 101:
                    String seqNo = (String) msg.obj;
                    Intent mIntent = new Intent(LoanListActivity.this, ProLoadBusInfoActivity.class);
                    mIntent.putExtra("isNew", "false");
                    startActivity(mIntent);
                    break;
                case 102:
                    new SweetAlertDialog(LoanListActivity.this, SweetAlertDialog.NORMAL_TYPE).setTitleText("与服务器断开连接，请检查网络").show();
                    break;
                case 120:
                    break;
                case 130:
                    showloadDialog("正在下载照片");
                    downNext();
                    break;
                case 1000:
                    break;
                //删除草稿的响应
                case 1920:
                    HeaderCommonResponse resp = (HeaderCommonResponse) msg.obj;
                    if(!"0000".equals(resp.getResultCode())){
                        ToastCoustom.show("草稿删除失败");
                    }
                    break;
                default:
				break;
			}
		}
	};


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.finish:
                if("管理".equals(item.getTitle())){
                    rvAdapter.changeStateToManage(true);
                    item.setTitle("完成");
                }else {
                    item.setTitle("管理");
                    rvAdapter.changeStateToManage(false);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    IFileSocketHandle fileSocketHandle= new IFileSocketHandle(){
            @Override
            public void onResult(MitFsResponse response) {
                // TODO Auto-generated method stub
                if("00".equals(response.getResultCode())){
                    PhotoEntity mEntity = new PhotoEntity();
                    mEntity.setPhotoName(response.getLocalFile().getName());
                    mEntity.setPhotoTypeCode(curPhoto.getPhotoTypeCode());
                    mEntity.setPosition(curPhoto.getPosition());
                    mEntity.setCusNumId(Contants.loanBusinessInfo.getCustomerID());
                    mEntity.setSeqNo(Contants.loanBusinessInfo.getSeqNo());
                    mEntity.setPhotoPath(response.getLocalFile().getPath());
                    app.getmDaoSession().insert(mEntity);
                    downNext();
                }else {
                    //showWarnDialog(iterator.next());
                    //showWarnDialog(curPhoto);
                }
            }
            @Override
            public void onError(MitFsException mitFsException, MitFsRequest request) {
                // TODO Auto-generated method stub
                //showWarnDialog(iterator.next());
                //showWarnDialog(curPhoto);
            }
    };

    PhotoEntity curPhoto;
    private void downNext() {
        if(!toDownIterator.hasNext()){
            dissMissDialog();
            setToTempPhoto(Contants.loanBusinessInfo.getSeqNo());
            Intent mIntent = new Intent(LoanListActivity.this,ProLoadBusInfoActivity.class);
            mIntent.putExtra("applyType",Contants.loanBusinessInfo.getApplyType());
            mIntent.putExtra("isNew", "false");
            startActivity(mIntent);
        }
        while (toDownIterator.hasNext()) {
            curPhoto = toDownIterator.next();
            MitFsRequest request=MitFsRequest.CreateDownLoadReqBean("GFMM",app.getmLoginResponse().getUserId(),
                    PhoneInfoUtil.getMyUUID(LoanListActivity.this),curPhoto.getPhotoPath(),
                    Environment.getExternalStorageDirectory()+"/hbbank/dksq");
            new DownLoadTask(fileSocketHandle, request, app.getDownloadClient()).start();
            break;
        }
    }

    SweetAlertDialog loadDialog;
    void showloadDialog(String errorinfo){
        if(loadDialog!=null&&loadDialog.isShowing()){
            loadDialog.dismiss();
        }
        loadDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        loadDialog.setTitleText(errorinfo);
        loadDialog.show();
    }
    void dissMissDialog(){
        if(loadDialog!=null&&loadDialog.isShowing()){
            loadDialog.dismiss();
        }
    }

    void saveDownload(LoanInfoEntity loanInfoEntity){
        LoanBusiness loanBusiness = loanInfoEntity.getLoanBusiness();

        LoanRecord loanRecord = new LoanRecord();

        loanRecord.setInputUserID(loanBusiness.getInputUserID());
        loanRecord.setInputUserName(loanBusiness.getInputUserName());
        loanRecord.setBusinessType(loanBusiness.getBusinessType());
        loanRecord.setContractFlag(loanBusiness.getApplyType());
        loanRecord.setUpdateTime(loanBusiness.getUpdateDate());
        loanRecord.setCustomerID(loanBusiness.getCustomerID());
        loanRecord.setSeqNo(loanBusiness.getSeqNo());
        loanRecord.setCustomerName(loanBusiness.getCustomerName());

        ProLoadApp.getmDaoSession().insert(loanRecord);
        ProLoadApp.getmDaoSession().insert(loanInfoEntity.getLoanBusiness());
        //插入担保-抵押信息
        if(loanInfoEntity.getAssure()!=null){
            loanInfoEntity.getAssure().setSeqNo(loanBusiness.getSeqNo());
            ProLoadApp.getmDaoSession().insert(loanInfoEntity.getAssure());
        }
        //插入担保 -保证
        if(loanInfoEntity.getGuarantees()!=null){
            for(Guarantee guarantee :loanInfoEntity.getGuarantees()){
                guarantee.setSeqNo(loanBusiness.getSeqNo());
                ProLoadApp.getmDaoSession().insert(guarantee);
            }
        }
        //插入抵押物信息
        if(loanInfoEntity.getHouse()!=null){
            loanInfoEntity.getHouse().setSeqNo(loanBusiness.getSeqNo());
            ProLoadApp.getmDaoSession().insert(loanInfoEntity.getHouse());
        }

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_loanlist,menu);
        return true;
    }




    void deleteCaogaos(final String seqno){
        new Thread(){
            @Override
            public void run() {
                super.run();
                Gson gson = new GsonBuilder().create();
                HashMap<String,String> map = new HashMap<String, String>();
                map.put("TransCode","1226");
                map.put("SeqNo", seqno);
                String result[] = ProLoadApp.getmAidlClient().exec(Contants.header, gson.toJson(map));
                HeaderCommonResponse responce = gson.fromJson(result[1], HeaderCommonResponse.class);
                mHandler.obtainMessage(1920,responce).sendToTarget();
            }
        }.start();
    }

    void deleteCaogao(final String seqno){


        List<LoanRecord> records = ProLoadApp.getmDaoSession().queryBuilder(LoanRecord.class).
                where(LoanRecordDao.Properties.SeqNo.eq(seqno)).list();
        if(records.size()>0){
            ProLoadApp.getmDaoSession().delete(records.get(0));
        }

        List<LoanBusiness> loanBusinesses = ProLoadApp.getmDaoSession().queryBuilder(LoanBusiness.class).
                where(LoanBusinessDao.Properties.SeqNo.eq(seqno)).list();
        if(loanBusinesses.size()>0){
            ProLoadApp.getmDaoSession().delete(loanBusinesses.get(0));
        }

        List<Assure> assures = ProLoadApp.getmDaoSession().queryBuilder(Assure.class).
                where(AssureDao.Properties.SeqNo.eq(seqno)).list();
        if(assures.size()>0){
            ProLoadApp.getmDaoSession().delete(assures.get(0));
        }

        List<Guarantee> Guarantees = ProLoadApp.getmDaoSession().queryBuilder(Guarantee.class).
                where(GuaranteeDao.Properties.SeqNo.eq(seqno)).list();
        if(Guarantees.size()>0){
            ProLoadApp.getmDaoSession().getGuaranteeDao().deleteInTx(Guarantees);
        }

        List<PhotoEntity> photoEntities = ProLoadApp.getmDaoSession().queryBuilder(PhotoEntity.class)
                .where(PhotoEntityDao.Properties.SeqNo.eq(seqno)).list();
        if(photoEntities.size()>0){
            for(PhotoEntity photoEntity:photoEntities){
                String filepath  = photoEntity.getPhotoPath();
                File mFile = new File(filepath);
                if(mFile.exists()){
                    boolean deleted =mFile.delete();
                    Log.d("DELETEFILE",filepath + "is Deleted?" + deleted);
                }
            }
        }
    }
}
