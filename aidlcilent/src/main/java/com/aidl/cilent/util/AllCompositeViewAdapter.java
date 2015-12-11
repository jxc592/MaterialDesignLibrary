package com.aidl.cilent.util;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author 
 *
 * @param <T>
 */
public class AllCompositeViewAdapter<T> extends BaseAdapter {
	int soureid=0;
	LayoutInflater inflater;
	List<T> list;
	getViewListener getViewListener;
	public AllCompositeViewAdapter(int soureid) {
		super();
		this.soureid = soureid;
		this.list = new ArrayList<T>();
	}
	
	public AllCompositeViewAdapter(
			List<T> list,
			getViewListener getViewListener) {
		super();
		this.list = list;
		this.getViewListener = getViewListener;
	}

	public AllCompositeViewAdapter(int soureid, List<T> list) {
		super();
		this.soureid = soureid;
		this.list = list;
	}
	
	public AllCompositeViewAdapter(int soureid,List<T> list,getViewListener getViewListener) {
		super();
		this.soureid = soureid;
		this.list = list;
		this.getViewListener = getViewListener;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(inflater==null){
			inflater=LayoutInflater.from(parent.getContext());
		}
		if(convertView==null){
			convertView=inflater.inflate(soureid, null);
		}		
		convertView.setTag(list.get(position));
		getViewListener.startView(convertView, list.get(position),position,parent);		
		return convertView;
	}
	/**
	 * 接口-
	 * @author jixucheng
	 * 自定义的布局下自定义内容的接口
	 */
	public interface getViewListener{
		void startView(View view, Object data, int position, ViewGroup parent);
	}
	/**
	 * 为这个适配器提供设置监听的方法
	 * @param listener
	 */
	public void setgetViewListener(getViewListener listener){
		this.getViewListener=listener;	
	}
	
	
	public void add(T item){
		list.add(item);
		notifyDataSetChanged();
	}
	
	public void add(List<T> items){
		for(T item:items){
			list.add(item);
		}
		notifyDataSetChanged();
	}
	
	public void clear(){
		list.clear();
		notifyDataSetChanged();
	}
	public void remove(int position){
		if(position>list.size()-1){
			return;
		}
		list.remove(position);
		notifyDataSetChanged();
	} 
	
}
