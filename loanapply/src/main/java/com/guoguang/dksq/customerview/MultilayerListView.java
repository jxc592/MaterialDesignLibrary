package com.guoguang.dksq.customerview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.guoguang.dksq.R;
import com.guoguang.dksq.util.Node;
import com.guoguang.dksq.util.SimpleTreeAdapter;
import com.guoguang.dksq.util.TreeListViewAdapter;
import com.guoguang.dksq.util.TreeListViewAdapter.OnTreeNodeClickListener;
import com.guoguang.dksq.util.TreeModelBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义树形控件
 * 
 * @author yf
 *
 */
public class MultilayerListView extends RelativeLayout {
	private Context mContext;
	private OnChildClickListener onChildClickListener;
	private ListView mListView;
	private TreeListViewAdapter mAdapter;
	private List<TreeModelBean> mDatas2 = new ArrayList<TreeModelBean>();
	private List<TreeModelBean> treeModelBeans;

	public MultilayerListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initView();
	} 

	/**
	 * 初始化控件
	 */
	private void initView() {
		mListView = new ListView(mContext);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		mListView.setLayoutParams(params);
		mListView.setDivider(mContext.getResources().getDrawable(R.drawable.divider_vertical));
		this.addView(mListView);
	}

	/**
	 * 模拟加载数据
	 */
	public void initTreeData() {
		if (treeModelBeans != null) {
			for (TreeModelBean treeModelBean : treeModelBeans) {
				mDatas2.add(treeModelBean);
			}
			inintTreeListView();

		} else {
			Toast.makeText(mContext, "请先调用setTreeModelBeans方法添加数据",
					Toast.LENGTH_LONG).show();
		}

	}

	/**
	 * 加载树的内容
	 */
	private void inintTreeListView() {
		// TODO Auto-generated method stub
		try {
			mAdapter = new SimpleTreeAdapter<TreeModelBean>(mListView,
					mContext, mDatas2, 0);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mAdapter.setOnTreeNodeClickListener(new OnTreeNodeClickListener() {
			@Override
			public void onClick(Node node, int position) {
				if (node.isLeaf()) {
					if (onChildClickListener != null) {
						onChildClickListener.onClick(node, position);
					}

				} else {
					// 后期可添加有子节点的父节点的监听事件
				}

			}

		});
		mListView.setAdapter(mAdapter);

	}

	/**
	 * 子节点的点击监听事件 只有当点击的节点是最终的子节点时，点击事件才生效
	 * 
	 * @param onChildClickListener
	 */
	public void setOnChildClickListener(
			OnChildClickListener onChildClickListener) {
		this.onChildClickListener = onChildClickListener;
	}

	public interface OnChildClickListener {
		void onClick(Node node, int position);
	}

	/**
	 * 设置树控件要展示的数据，其结构要严格按照TreeModelBean 工具类的结构，否则显示不成功
	 * 
	 * @param treeModelBeans
	 */
	public void setTreeModelBeans(List<TreeModelBean> treeModelBeans) {
		this.treeModelBeans = treeModelBeans;
	}

	/**
	 * 展示树的内容
	 */
	public void show() {
		initTreeData();

	}
}
