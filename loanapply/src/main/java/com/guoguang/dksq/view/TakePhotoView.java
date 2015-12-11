package com.guoguang.dksq.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.aidl.cilent.util.AllCompositeViewAdapter;
import com.aidl.cilent.util.AllCompositeViewAdapter.getViewListener;
import com.aidl.cilent.util.BaseUtil;
import com.aidl.cilent.util.BitmapUtil;
import com.guoguang.dksq.R;
import com.guoguang.dksq.activity.ProLoadBusInfoActivity;
import com.guoguang.dksq.activity.TakephotoActivity;
import com.guoguang.dksq.contants.Contants;
import com.guoguang.dksq.model.PhotoFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TakePhotoView extends BaseLayout {

    public TakePhotoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public TakePhotoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    ProLoadBusInfoActivity mAcitivity;

    public TakePhotoView(Context context) {
        super(context);
        mAcitivity = (ProLoadBusInfoActivity) context;
    }

    List<String> bpNodes;
    GridView bpGridView;

    @Override
    public View onCreateView() {
        // TODO Auto-generated method stub
        inflater.inflate(R.layout.view_takephoto, this);

        bpNodes = new ArrayList<>();
        bpNodes.add("90");
        bpNodes.add("91");
        bpNodes.add("92");
        bpNodes.add("93");
        bpNodes.add("94");

        bpGridView = (GridView) findViewById(R.id.gridView_tp_bp);
        bpGridView.setAdapter(new AllCompositeViewAdapter<>(R.layout.item_xpphoto_nophoto, bpNodes, new MyGetView(1)));
        BaseUtil.setListViewHeightBasedOnChildren(bpGridView);

        bpGridView.setOnItemClickListener(new MyOnItemClickListener());

        return null;
    }


    @Override
    public boolean checkData() {
        // TODO Auto-generated method stub
        return super.checkData();
    }


    @Override
    public void initData() {
        // TODO Auto-generated method stub
//		String id=applyInfo.getId_no();
//		parActy.initSavedPhotoEntity();
//		reFresh();
    }

    @Override
    public void saveData() {
        // TODO Auto-generated method stub

    }

    //
    class MyGetView implements getViewListener {
        int type = 1;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public MyGetView(int type) {
            super();
            this.type = type;
        }

        @Override
        public void startView(View view, Object data, int position,
                              ViewGroup parent) {
            // TODO Auto-generated method stub
            String node = (String) data;
            TextView textView = (TextView) view.findViewById(R.id.text_takephoto_item);
            textView.setText(PhotoFactory.photo_Key_value.get(node));
            ImageView imageView = (ImageView) view.findViewById(R.id.photo_takephoto_item);
            if (Contants.toSavePhotoHashMap != null) {
                HashMap<String, String> map = Contants.toSavePhotoHashMap.get(node);
                if (map != null && map.size() > 0) {
                    imageView.setImageResource(R.drawable.photo_yipai);
                }
            }
        }


    }

    //
    ImageView tempImageView;

    class MyOnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // TODO Auto-generated method stub
            String node = (String) view.getTag();

            tempImageView = (ImageView) view.findViewById(R.id.photo_takephoto_item);

            Intent intent = new Intent(getContext(), TakephotoActivity.class);
            intent.putExtra("id", Contants.loanBusinessInfo.getCustomerID());
            intent.putExtra("node", node);
            mAcitivity.startActivityForResult(intent, 10001);
        }
    }

    //	HashMap<String, Bitmap> photoTemp;
//
//	void reFresh(){
//		photoTemp = new HashMap<>();
//		if(Contants.toSavePhotoHashMap == null){
//			return ;
//		}
//		if(Contants.toSavePhotoHashMap.size() == 0){
//			return;
//		}
//		HashMap<String, HashMap<String, String>> map = Contants.toSavePhotoHashMap;
//		Set<String> keys = map.keySet();
//		for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
//			String key = (String) iterator.next();
//			HashMap<String, String> nodeMap = map.get(key);
//			if(nodeMap.size()>0){
//				Set<String> poi = nodeMap.keySet();
//
//				List<Bitmap> tempBitmaps = new ArrayList<>();
//
//				for (Iterator iterator2 = poi.iterator(); iterator2.hasNext();) {
//					String po_key = (String) iterator2.next();
//					String path = nodeMap.get(po_key);
//					if(path == null ||"".equals(path) ){
//						continue;
//					}
//					File mFile = new File(path);
//					if(!mFile.exists()){
//						continue;
//					}
//					Bitmap mBitmap  = BitmapUtil.getLittleimage(path);
//					//bitmaps.//
//					tempBitmaps.add(mBitmap);
//				}
//				if(tempBitmaps.size()>0){
//
//					Bitmap[] Bitmaps = new Bitmap[tempBitmaps.size()];
//					for (int i = 0; i < Bitmaps.length; i++) {
//						Bitmaps[i]=tempBitmaps.get(i);
//					}
//					if(Bitmaps.length>0){
//						Bitmap mBitmap= BitmapUtil.combineBitmaps(2, Bitmaps);
////						lbbpBitmaps.add(mBitmap);
//						photoTemp.put(key, mBitmap);
//					}
//					for (int i = 0; i < Bitmaps.length; i++) {
//						if(Bitmaps[i]!=null&&!Bitmaps[i].isRecycled()){
//							Bitmaps[i].recycle();
//						}
//					}
//					System.gc();
//				}
//
//			}else {
//				continue;
//			}
//		}
//	}
    public void OnActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (resultCode == Activity.RESULT_OK) {
            HashMap<String, String> photoHashMap = (HashMap<String, String>) data.getSerializableExtra("photos");
            if (photoHashMap.size() > 0) {
                tempImageView.setImageResource(R.drawable.photo_yipai);
            } else if (photoHashMap.size() == 0) {
                tempImageView.setImageResource(R.drawable.photo_xuan_take);
            }
        }
    }


    void bindPhoto(HashMap<String, String> photoHashMap) {
        if (photoHashMap.size() > 0) {
            Set<String> keys = photoHashMap.keySet();
            int x = 0;
            List<Bitmap> mBitmaps = new ArrayList<>();
            for (Iterator iterator = keys.iterator(); iterator.hasNext(); ) {
                String key = (String) iterator.next();
                String path = photoHashMap.get(key);
                File mFile = new File(path);
                if (mFile.exists()) {
                    Bitmap bitmap = BitmapUtil.getLittleimage(path);
                    if (bitmap != null) {
                        mBitmaps.add(bitmap);
                    }
                }
            }
            if (mBitmaps.size() > 0) {
                int length = mBitmaps.size();
                Bitmap[] bitmapss = new Bitmap[length];
                for (int i = 0; i < length; i++) {
                    bitmapss[i] = mBitmaps.get(i);
                }
                Bitmap rest = BitmapUtil.combineBitmaps(2, bitmapss);
                //照片合成好后需要释放内存bitmap
                for (int i = 0; i < length; i++) {
                    bitmapss[i] = mBitmaps.get(i);
                    if (bitmapss[i] != null && !bitmapss[i].isRecycled()) {
                        bitmapss[i].recycle();
                    }
                }
                System.gc();
                tempImageView.setImageBitmap(rest);
            } else {
                //tempImageView.setImageResource(R.drawable.)
            }
        }
    }
}