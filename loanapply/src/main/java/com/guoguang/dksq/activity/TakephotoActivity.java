package com.guoguang.dksq.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aidl.cilent.util.BitmapUtil;
import com.guoguang.dksq.R;
import com.guoguang.dksq.contants.Contants;
import com.guoguang.dksq.model.PhotoFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class TakephotoActivity extends AppCompatActivity {

    RecyclerView rv;
    List<String> photos;
    HashMap<String,Bitmap> bitmaps;
    MyAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takephoto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Contants.defaultcolor));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTake();
            }
        });
        rv = (RecyclerView) findViewById(R.id.rv);
        GridLayoutManager manager = new GridLayoutManager(this,3);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        rv.setLayoutManager(manager);
        rv.addItemDecoration(new MyItemDecoraton());
        rvAdapter = new MyAdapter();
        rv.setAdapter(rvAdapter);

        prepare();



    }

    String id, node;

    private void prepare(){

        bitmaps = new HashMap<>();
        id = getIntent().getStringExtra("id");
        node = getIntent().getStringExtra("node");
        if(Contants.toSavePhotoHashMap == null){
            startTake();
            return;
        }
        HashMap<String,String> map = Contants.toSavePhotoHashMap.get(node);
        if(map == null||map.size()==0){
            startTake();
            return;
        }
        Iterator<String> iterator = map.keySet().iterator();
        photos = new ArrayList<>();
        while (iterator.hasNext()){
            String key = iterator.next();
            String path = map.get(key);
            photos.add(path);
        }
        if(photos.size()>0){
            rvAdapter.add(photos);
        }
    }



    private void startTake(){
        if(photos == null){
            takePhoto(0);
        }else {
            takePhoto(photos.size());
        }
    }

    String filepath,tempPath;
	int tempPosition;
	private void takePhoto(int position) {
		// TODO Auto-generated method stub
        tempPosition =position;
		Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		File mFile = new File(Contants.savePicPath);
		if (!mFile.exists()) {
			mFile.mkdirs();
		}
		String filename=null;

        char c=(char)(int)(Math.random()*26+97);
        int  a =(int)Math.random()*10;

		filename=id+node+position+c+a;

		filepath = Contants.savePicPath
				+ filename + ".jpg";
		tempPath=Contants.savePicPath+"temp"+".jpg";

		File mSaveFile = new File(tempPath);
		if (!mSaveFile.exists()) {
			try {
				mFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Uri imageUri = Uri.fromFile(mSaveFile);
		// 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
		photoIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		startActivityForResult(photoIntent, position);


	}

    class MyItemDecoraton extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            if (parent.getPaddingLeft() != 10) {
                parent.setPadding(20, 10, 10, 10);
                parent.setClipToPadding(false);
            }
            outRect.top = 10;
            outRect.bottom = 10;
            outRect.left = 10;
            outRect.right = 10;
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);
        }
    }

    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0 && resultCode ==RESULT_CANCELED){
            finish();
            return;
        }
		if(resultCode== Activity.RESULT_OK){
			Bitmap bitmap= BitmapUtil.getimage(tempPath);
			BitmapUtil.saveBitmap(bitmap, filepath);

			//**以下map用于保存进入ram
			if(photos==null){
				photos=new ArrayList<>();
			}
			photos.add(tempPosition, filepath);

			//一下用户临时保存bitmap的
			if(bitmaps==null){
				bitmaps=new HashMap<>();
			}else {
				Bitmap mBitmap=bitmaps.get(filepath);
				if(mBitmap!=null){
					if(!mBitmap.isRecycled()){
						mBitmap.recycle();
						mBitmap = null;
						System.gc();
					}
				}
			}
			Bitmap restBitmap = BitmapUtil.centerSquareScaleBitmap(bitmap, 270);
			bitmaps.put(filepath, restBitmap);
            bitmap.recycle();
            rvAdapter.add(filepath);
            //rvAdapter.notifyDataSetChanged();
		}
	}

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_content;
        View v_del;
        TextView tv_name;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv_content = (ImageView) (itemView . findViewById(R.id.iv_bigphoto));
            v_del = itemView.findViewById(R.id.del);
            tv_name = (TextView) itemView.findViewById(R.id.tv_pname);
            v_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String toDeleteFile = photos.get(po);
                    File mfile = new File(toDeleteFile);
                    if(mfile.exists()){
                        mfile.delete();
                    }
                    photos.remove(po);
                    Bitmap bitmap = bitmaps.get(toDeleteFile);
                    bitmaps.remove(toDeleteFile);
                    if(bitmap!=null&&!bitmap.isRecycled()){
                        bitmap.recycle();
                    }
                    rvAdapter.remove(po);
                }
            });
        }

        int po;

        public int getPo() {
            return po;
        }

        public void setPo(int po) {
            this.po = po;
        }
    }
    class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        List<String> mphotos;

        public MyAdapter() {
            super();
            mphotos = new ArrayList<>();
        }


        @Override
        public int getItemCount() {
            return mphotos.size();
        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bigphoto, null);
            return new MyViewHolder(itemview);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            String filePath = mphotos.get(position);
            Bitmap bitmap = bitmaps.get(filePath);
            if(bitmap!=null&&!bitmap.isRecycled()){
                holder.iv_content.setImageBitmap(bitmap);
            }else {
                bitmap = BitmapUtil.getimage(filePath);
                Bitmap resbitmap = BitmapUtil.centerSquareScaleBitmap(bitmap, 270);
                bitmap.recycle();
                bitmaps.put(filePath, resbitmap);
                holder.iv_content.setImageBitmap(resbitmap);
            }
            holder.tv_name.setText(PhotoFactory.photo_Key_value.get(node) + "-" + filePath);
            holder.setPo(position);
        }

        public void add(String filepath) {
            mphotos.add(filepath);
            notifyItemInserted(mphotos.size() - 1);
            notifyDataSetChanged();
        }

        public void add(List<String> files) {
            int frompo = mphotos.size();
            mphotos.addAll(files);
            notifyItemRangeInserted(0,files.size());
            notifyDataSetChanged();
        }

        public void remove(int po){
            mphotos.remove(po);
            //notifyDataSetChanged();
            notifyItemRemoved(po);
            notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_phototakeactivity,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                setResult();
                finish();
                break;
            case R.id.finish:
                //setResult(RESULT_OK);
                setResult();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                setResult();
                finish();
                break;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放recycler的bitmap的垃圾回收
        System.gc();
    }

    void setResult(){
        //第几张照片 与 照片路径的 map键值对 保存到ram中。
        if(photos==null){
            setResult(RESULT_CANCELED);
        }else {
            if(Contants.toSavePhotoHashMap==null){
                Contants.toSavePhotoHashMap = new HashMap<>();
            }
            HashMap<String,String> map = new HashMap<>();
            for(int i=0;i<photos.size();i++){
                map.put(""+i,photos.get(i));
            }
            Contants.toSavePhotoHashMap.put(node, map);

            Intent mIntent  = new Intent();
            mIntent.putExtra("photos",map);
            setResult(RESULT_OK, mIntent);
        }
    }
}
