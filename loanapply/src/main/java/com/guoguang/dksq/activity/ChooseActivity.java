package com.guoguang.dksq.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.aidl.cilent.entity.LoginResponse;
import com.aidl.cilent.library.sweetdialog.SweetAlertDialog;
import com.aidl.cilent.util.AllCompositeViewAdapter;
import com.aidl.cilent.util.AllCompositeViewAdapter.getViewListener;
import com.aidl.cilent.util.ToastCoustom;
import com.google.gson.GsonBuilder;
import com.guoguang.dksq.R;
import com.guoguang.dksq.application.ProLoadApp;
import com.guoguang.dksq.contants.Contants;

import java.util.ArrayList;
import java.util.List;

public class ChooseActivity extends AppCompatActivity implements getViewListener {
	GridView listView;
	LoginResponse mLoginResponse;
	//LocalConnector localConnector;
	String cardName;

	ProLoadApp application;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);// 去除标题
		this.getWindow().setFlags(0x80000000, 0x80000000);// 关键代码

		Bundle bundle = getContentResolver()
				.call(Uri
								.parse("content://com.wincom.android.provider.HBbankloudProvider"),
						"getFileServerIP", null, new Bundle());
		String fileserverIp = bundle.getString("FileServerIP");
		if (fileserverIp != null && !"".equals(fileserverIp)
				&& fileserverIp.contains(":")) {
			String[] ipAndPort = fileserverIp.split(":");
			Contants.UPLOADFILE_IP = ipAndPort[0];
			Contants.UPLOADFILE_PORT = Integer.parseInt(ipAndPort[1]);
		}

		//localConnector=new LocalConnector(((CreditApplication)getApplication()));
		FrameLayout frameLayout = (FrameLayout) this.getWindow().getDecorView()
				.findViewById(android.R.id.content);
		frameLayout.setBackground(getResources().getDrawable(R.drawable.bgback));
		LayoutInflater.from(this).inflate(R.layout.activity_choose,
				frameLayout, true);

		findViewById(R.id.frame_content).setBackgroundResource(
				R.drawable.midspace);

		application = (ProLoadApp) getApplication();
		if(!Contants.debug){
			String loginString = getIntent().getStringExtra("logininfo");
			if (loginString == null) {
				ToastCoustom.show("请重新登录后再进入");
				finish();
				return;
			}
			mLoginResponse = new GsonBuilder().create().fromJson(loginString,
					LoginResponse.class);

			application.setmLoginResponse(mLoginResponse);
			cardName =getIntent().getStringExtra("card_product_name");
			if(cardName!=null&&!"".equals(cardName)){
				//showApplyTypeChoosePop();
				frameLayout.postDelayed(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
//						showApplyTypeChoosePop();
					}
				}, 100);
			}
		}else {
			mLoginResponse = new LoginResponse();
			mLoginResponse.setUserId("122784");
			application.setmLoginResponse(mLoginResponse);
		}

		initView();
		final Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bgback);
		Palette.from(mBitmap).generate(new Palette.PaletteAsyncListener() {
			@Override
			public void onGenerated(Palette palette) {
				int color = palette.getDarkVibrantColor(Color.RED);
				//getSupportActionBar().setBackgroundDrawable(new ColorDrawable(color));
				findViewById(R.id.toolbar).setBackground(new ColorDrawable(color));
				Contants.defaultcolor = color;
				Contants.othercolor = palette.getVibrantColor(Color.RED);
				mBitmap.recycle();
			}
		});

	}

	private void initView() {
		// TODO Auto-generated method stub
		listView = (GridView) findViewById(R.id.listView_choose);
		List<String> list = new ArrayList<>();
		list.add("单笔授信业务");
		list.add("额度项下业务");
		list.add("草稿箱");
		//list.add("进度查询");
		
		listView.setAdapter(new AllCompositeViewAdapter<>(R.layout.item_chose,
				list, this));
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					Intent intent = new Intent(ChooseActivity.this,ProLoadBusInfoActivity.class);
					intent.putExtra("isNew", "true");
					intent.putExtra("applyType", "1");
					startActivity(intent);
					//showApplyTypeChoosePop();
					break;
				case 1:
					Intent intent2 = new Intent(ChooseActivity.this,ProLoadBusInfoActivity.class);
					intent2.putExtra("isNew", "true");
					intent2.putExtra("applyType", "2");
					startActivity(intent2);
					break;
				case 2:
					Intent intent1 = new Intent(ChooseActivity.this, LoanListActivity.class);
					startActivity(intent1);
					break;
				}

			}
		});
	}


	void gotoAnother(Class rest) {
		Intent intent = new Intent(this, rest);
		startActivity(intent);
	}

	View view_applytypechoose;
	PopupWindow popupWindow;


	void changeLight(float bgAlpha) {
		/**
		 * 设置透明度
		 * 
		 * @param bgAlpha
		 */
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.alpha = bgAlpha; // 0.0-1.0
		getWindow().setAttributes(lp);
	}
	int[] images={R.drawable.sec1,R.drawable.sec3,R.drawable.sec4,R.drawable.sec5,R.drawable.sec2};
	@Override
	public void startView(View view, Object data, int position, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView textView = (TextView) view
				.findViewById(R.id.textView_itemtext);
		
		ImageView ic=(ImageView) view.findViewById(R.id.iv_icon);
		String sdString = (String) data;
		
		view.setBackgroundResource(images[position%5]);
		view.setLayoutParams(new AbsListView.LayoutParams(260, 120));
		if(position==5){
			view.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					return true;
				}
			});
			//view.findViewById(R.id.iv_ic).setVisibility(View.INVISIBLE);
			textView.setText(sdString);
			textView.setTextColor(0xffcb0051);
			textView.setTextSize(25);
			LayoutParams layoutParams=(LayoutParams) textView.getLayoutParams();
			layoutParams.height=70;
			layoutParams.topMargin=15;
			textView.setGravity(Gravity.BOTTOM);
			textView.setLayoutParams(layoutParams);
		}else {
			textView.setText(sdString);
			textView.setPadding(20, 0, 0, 0);
//			BadgeView badgeView = new BadgeView(parent.getContext(), textView);
//			badgeView.setBadgeMargin(10, 13);
//			badgeView.setText("1");
//			badgeView.show();
			ic.setVisibility(View.VISIBLE);
			if(position==1){
				ic.setImageResource(R.drawable.edxx);
			}else if (position==0) {
				ic.setImageResource(R.drawable.icon_new);
			}else if(position == 2){
				ic.setImageResource(R.drawable.icon_caogao);
			}
		}
		
	}

	class MyOntouch implements OnTouchListener {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				v.setAlpha(0.5f);
				break;
			case MotionEvent.ACTION_CANCEL:
				v.setAlpha(1.0f);
				break;
			case MotionEvent.ACTION_UP:
				v.setAlpha(1.0f);
				break;
			default:
				break;
			}
			return false;
		}
	}

	class MyOnClick implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
//			case R.id.ll_opera_z:
//				gotoAnother(CreditMainActivity.class,
//						CreditMainActivity.APPLYTYPE_A);
//				popupWindow.dismiss();
//				break;
//			case R.id.ll_opera_zf:
//				gotoAnother(CreditMainActivity.class,
//						CreditMainActivity.APPLYTYPE_B);
//				popupWindow.dismiss();
//				break;
			}
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE).setTitleText("退出贷款申请").setConfirmText("确认").setCancelText("取消").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
				@Override
				public void onClick(SweetAlertDialog sweetAlertDialog) {
					// TODO Auto-generated method stub
					finish();
				}
			}).show();
			break;
		default:
			break;
		}
		return false;
	}

}
