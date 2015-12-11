package com.guoguang.dksq.activity;

import android.app.Activity;
import android.os.Bundle;

public class PhotoTakeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //	GridView gridView;
//	TextView tv_title;
//	AllCompositeViewAdapter<String> mAdapter;
//	
//	String id ; 
//	String node ;
//	
//	HashMap<String, String>  photos;
//	
//	
//	Button btn_photocomplete;
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		setContentView(R.layout.activity_takephoto);
//		
//		try{
//			id=getIntent().getStringExtra("id");
//			node=getIntent().getStringExtra("node");
//		}catch (Exception e) {
//			// TODO: handle exception
//			finish();
//		}
//		btn_photocomplete = (Button) findViewById(R.id.btn_photocomplete);
//		gridView =(GridView) findViewById(R.id.gv_photo_add);
//		gridView.setOnItemClickListener(new MyOnClick());
//		gridView.setOnItemLongClickListener(new MyOnLongClick());
//		tv_title = (TextView) findViewById(R.id.tv_photenodename);
//		if("90".equals(node)||"92".equals(node)){
//			tv_title.setText(PhotoFactory.photo_Key_value.get(node)+"  (必拍项)");
//		}else {
//			tv_title.setText(PhotoFactory.photo_Key_value.get(node)+"  (选拍项)");
//		}
//		btn_photocomplete.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent mIntent = new Intent();
//				mIntent.putExtra("photos", photos);
//				setResult(RESULT_OK, mIntent);
//				finish();
//			}
//		});
//		
//		mAdapter = new AllCompositeViewAdapter<String>(R.layout.item_photo_activity,new ArrayList<String>(), new MyGetView());
//		gridView.setAdapter(mAdapter);
//		mAdapter.add("");
//		
//		
//		if(Contants.toSavePhotoHashMap==null){
//			Contants.toSavePhotoHashMap=new HashMap<>();
//			return;
//		}
//		photos=Contants.toSavePhotoHashMap.get(node);
//		if(photos!=null&&photos.size()>0){
//			loadDialog =new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
//			loadDialog.setTitleText("正在加载图片");
//			loadDialog.show();
//			mHandler.postDelayed(new Runnable() {
//				@Override
//				public void run() {
//					// TODO Auto-generated method stub
//					initPhoto();
//					mHandler.sendEmptyMessage(100);
//				}
//			}, 1000);
//		}
//	}
//	SweetAlertDialog loadDialog;
//	Handler mHandler =new Handler(){
//		public void handleMessage(android.os.Message msg) {
//			switch (msg.what) {
//			case 100:
//				if(loadDialog!=null&&loadDialog.isShowing())loadDialog.dismiss();
//				//initPhoto();
//				break;
//			case 1000:
//				mAdapter.add("");
//				break;
//			default:
//				break;
//			}
//		};
//	};
//	private void initPhoto() {
//		// TODO Auto-generated method stub
//		List<String> list=new ArrayList<>();
//		Set<String> keySet = photos.keySet();
//		for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
//			String key = (String) iterator.next();
//			File mFile = new File(photos.get(key));
//			if(!mFile.exists()){
//				continue;
//			}
//			Bitmap mBitmap=BitmapUtil.getTargetimage(photos.get(key));
//			if(tempSavePhotomap==null){
//				tempSavePhotomap =new HashMap<>();
//			}
//			tempSavePhotomap.put(key, mBitmap);
//			mHandler.sendEmptyMessage(1000);
//		}
//	}
//	
//	
//	class MyOnLongClick implements OnItemLongClickListener{
//		@Override
//		public boolean onItemLongClick(AdapterView<?> parent, View view,
//				int position, long id) {
//			// TODO Auto-generated method stub
//			int size = gridView.getChildCount();
//			if(position!=size-1){
//				if(tempSavePhotomap.containsKey(""+position)){
//					Bitmap mBitmap=tempSavePhotomap.remove(""+position);
//					if(mBitmap!=null){
//						mBitmap.recycle();
//					}
//				}
//				if(photos.containsKey(""+position)){
//					File file = new File(photos.get(""+position));
// 					if(file.exists()){
// 						file.delete();
//// 						LocalConnector mConnector=new LocalConnector((CreditApplication)getApplication());
//// 						mConnector.deletePhoto(id,position);
// 					}
//					photos.remove(""+position);
//				}
//				mAdapter.remove(position);
//			}else {
//				return false;
//			}
//			return true;
//		}
//	} 
//	class MyGetView implements getViewListener{
//		@Override
//		public void startView(View view, Object data, int position,
//				ViewGroup parent) {
//			ImageView imageView= (ImageView) view.findViewById(R.id.iv_photo_add);
//			if(tempSavePhotomap==null)
//				return;
//			Bitmap mBitmap = tempSavePhotomap.get(position+"");
//			if(mBitmap!=null){
//				if(!mBitmap.isRecycled())
//					imageView.setImageBitmap(mBitmap);
//				else {
//					mBitmap=BitmapUtil.getimage(photos.get(position+""));
//					imageView.setImageBitmap(mBitmap);
//					tempSavePhotomap.put(position+"", mBitmap);
//				}
//			}else {
//				imageView.setImageResource(R.drawable.photo_add);
//			}
//		}
//	}
//	
//	boolean couldAdd=false;
//	
//	class MyOnClick implements OnItemClickListener{
//		@Override
//		public void onItemClick(AdapterView<?> parent, View view, int position,
//				long id) {
//			// TODO Auto-generated method stub
//			tempImageView = (ImageView) view.findViewById(R.id.iv_photo_add);
//			tempPosition = position;
//			int size = gridView.getChildCount();
//			if(position==size-1){
//				couldAdd = true;
//				takePhoto(position);
//			}else{
//				couldAdd =false;
//				takePhoto(position);
//			}
//		}
//	}
//	
//	String filepath,tempPath;
//	ImageView tempImageView;
//	int tempPosition;
//	private void takePhoto(int position) {
//		// TODO Auto-generated method stub
//		Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//		File mFile = new File(Contants.savePicPath);
//		if (!mFile.exists()) {
//			mFile.mkdirs();
//		}
//		String filename=null;
//		filename=id+node+position;
//		
//		filepath = Contants.savePicPath
//				+ filename + ".jpg";
//		tempPath=Contants.savePicPath+"temp"+".jpg";
//		
//		File mSaveFile = new File(tempPath);
//		if (!mSaveFile.exists()) {
//			try {
//				mFile.createNewFile();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}			
//		}
//		Uri imageUri = Uri.fromFile(mSaveFile);
//		// 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
//		photoIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//		//getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//		//getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
//		startActivityForResult(photoIntent, 1);
//	}
//	
//	
//	//forGridsav Bitmap
//	HashMap<String, Bitmap> tempSavePhotomap;
//	
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		// TODO Auto-generated method stub
//		super.onActivityResult(requestCode, resultCode, data);
//		if(resultCode==Activity.RESULT_OK){
//			Bitmap bitmap=BitmapUtil.getimage(tempPath);
//			BitmapUtil.saveBitmap(bitmap, filepath);
//			
//			//**以下map用于保存进入ram
//			if(photos==null){
//				photos=new HashMap<>();
//			}
//			photos.put(""+tempPosition,filepath);
//			
//			//一下用户临时保存bitmap的
//			if(tempSavePhotomap==null){
//				tempSavePhotomap=new HashMap<>();
//			}else {
//				Bitmap mBitmap=tempSavePhotomap.get(""+tempPosition);
//				if(mBitmap!=null){
//					if(!mBitmap.isRecycled()){
//						mBitmap.recycle();
//						mBitmap = null;
//						System.gc();
//					}
//				}
//			}
//			Bitmap restBitmap = BitmapUtil.ResizeBitmap(bitmap, 250);
//			tempSavePhotomap.put(""+tempPosition, restBitmap);
//			
//			tempImageView.setImageBitmap(restBitmap);
//			
//			if(couldAdd){
//				mAdapter.add("");
//			}else {
//				
//			}
//		}
//	}
//	
//	@Override
//	protected void onDestroy() {
//		// TODO Auto-generated method stub
//		super.onDestroy();
//		if(tempSavePhotomap!=null&&tempSavePhotomap.size()>0){
//			Set<String> keys = tempSavePhotomap.keySet();
//			for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
//				String key = (String) iterator.next();
//				Bitmap mBitmap = tempSavePhotomap.get(key);
//				if(mBitmap!=null&&!mBitmap.isRecycled()){
//					mBitmap.recycle();
//				}
//			}
//			System.gc();
//		}
//		if(photos!=null){
//			if(Contants.toSavePhotoHashMap == null){
//				Contants.toSavePhotoHashMap = new HashMap<>();
//			}
//			Contants.toSavePhotoHashMap.put(node, photos);
//		}
//	}
//	
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		// TODO Auto-generated method stub
//		switch (keyCode) {
//		case KeyEvent.KEYCODE_BACK:
//			Intent mIntent = new Intent();
//			mIntent.putExtra("photos", photos);
//			setResult(RESULT_OK, mIntent);
//			break;
//		default:
//			break;
//		}
//		return super.onKeyDown(keyCode, event);
//	}
//	
	
}
