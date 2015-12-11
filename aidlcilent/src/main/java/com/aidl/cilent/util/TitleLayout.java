package com.aidl.cilent.util;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aidl.cilent.R;
import com.aidl.cilent.library.sweetdialog.SweetAlertDialog;
import com.aidl.cilent.library.sweetdialog.SweetAlertDialog.OnSweetClickListener;

/**
 * 自定义title
 * 
 * @author yf
 *
 */
public class TitleLayout extends LinearLayout {

	public TitleLayout(final Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		LayoutInflater.from(context).inflate(R.layout.title, this);
		try {
			// 把使用该title的类加入到活动管理类
			Activity activity = (Activity) context;
		} catch (Exception e) {
			// TODO: handle exception
		}
		ImageView iView = (ImageView) findViewById(R.id.iv_logo);
		TextView tv_title = (TextView) findViewById(R.id.tv_title_all);
		final Button btn_exit = (Button) findViewById(R.id.btn_home_all);
		tv_title.setText(getResources().getString(R.string.app_name) + "V"
				+ BaseUtil.getVersion(context));// 设置标题
		// 监听退出按钮
		btn_exit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new SweetAlertDialog((Activity) getContext(),
						SweetAlertDialog.NORMAL_TYPE)
						.setTitleText(
								"退出"
										+ getResources().getString(
												R.string.app_name))
						.setConfirmClickListener(new OnSweetClickListener() {

							@Override
							public void onClick(
									SweetAlertDialog sweetAlertDialog) {
								// TODO Auto-generated method stub
								((Activity)context).finish();
								if (onExitClickListener != null) {
									setOnExitClickListener(onExitClickListener);
								}
							}
						}).setConfirmText("确认").setCancelText("取消").show();
			}
		});
		// 按压效果
		btn_exit.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					btn_exit.setAlpha(0.6f);
					break;
				case MotionEvent.ACTION_CANCEL:
					btn_exit.setAlpha(1f);
					break;
				case MotionEvent.ACTION_UP:
					btn_exit.setAlpha(1f);
					break;
				default:
					break;
				}
				return false;
			}
		});
	}

	private OnExitClickListener onExitClickListener;

	public interface OnExitClickListener {
		void onClik();
	}

	/**
	 * 设置退出事件要做的处理（不包括结束Activity）
	 * 
	 * @param onExitClickListener
	 */

	public void setOnExitClickListener(OnExitClickListener onExitClickListener) {
		this.onExitClickListener = onExitClickListener;
	}
}
