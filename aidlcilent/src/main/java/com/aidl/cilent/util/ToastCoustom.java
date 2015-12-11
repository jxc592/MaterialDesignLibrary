package com.aidl.cilent.util;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aidl.cilent.R;

/**
 * 自定义Toast
 * 
 * @author yf
 *
 */
public class ToastCoustom {
	private static Toast toast = null;

	private static LinearLayout toastView = null;

	private final static int textId = 2345;

	private static int mLocationX, mLocatonY;// toast显示的位置

	private static int single = 0;// toast默认的显示位置 0
									// 与普通toast一致，1屏幕中间显示，2自定义显示位置，3屏幕顶部居中
	private static int mWidth = LinearLayout.LayoutParams.WRAP_CONTENT;// toast自定义大小
	// 默认自适应
	private static int mHeight = LinearLayout.LayoutParams.WRAP_CONTENT;// toast自定义大小
	// 默认自适应
	private static int mTime = 3;// toast 显示时间 默认3s

	private static int msgColor = Color.WHITE;// toast显示文字的颜色
	private static float msgSize = 15;// toast字体大小

	private static int background = Color.GRAY;// Toast背景色
	private static Context mContext;

	private static CountDownTimer countDownTimer;

	// public ToastCoustom(Context mContext) {
	// super();
	// this.mContext = mContext;
	// }
	public static void init(Context context) {
		mContext = context;
	}

	/**
	 * 自定义Toast要显示字体的颜色
	 * 
	 * @param color
	 */
	public static void setMsgColor(int color) {
		msgColor = color;
	}

	/**
	 * Toast 字体大小
	 * 
	 * @param size
	 */
	public static void setMsgSize(float size) {
		msgSize = size;
	}

	/**
	 * Toast背景色
	 * 
	 * @param color
	 */
	public static void setBackGround(int color) {
		background = color;
	}

	/**
	 * Toast 要显示的位置
	 * 
	 * @param locationX
	 * @param locatonY
	 */
	public static void setLocation(int locationX, int locatonY) {
		single = 2;
		mLocationX = locationX;
		mLocatonY = locatonY;
	}

	/**
	 * Toast 显示在屏幕中间
	 */
	public static void setLocationCenter() {
		single = 1;
	}

	/**
	 * Toast 显示在屏幕上部 居中
	 */
	public static void setLocationTop() {
		single = 3;
	}

	/**
	 * Toast 大小
	 * 
	 * @param width
	 * @param height
	 */
	public static void setSize(int width, int height) {
		mWidth = width;
		mHeight = height;
	}

	/**
	 * Toast 显示的时间 单位为秒
	 * 
	 * @param time
	 */
	public static void setTime(int time) {
		mTime = time;
	}

	private static void setText(String msg) {

		TextView tv = (TextView) toastView.findViewById(textId);
		if (tv != null) {
			tv.setText(msg);
		}
	}

	private static void createToast() {

		if (toastView == null) {
			toastView = new LinearLayout(mContext);
			toastView.setOrientation(LinearLayout.VERTICAL);
			toastView.setMinimumWidth(300);
			toastView.setGravity(Gravity.CENTER);
			// toastView.setBackgroundResource(resid);
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
					mWidth, mHeight);
			toastView.setLayoutParams(layoutParams);
			toastView.setBackgroundColor(background);
			toastView.setBackgroundResource(R.drawable.toaststyle);

			TextView textView = new TextView(mContext);
			textView.setText("");
			textView.setPadding(10, 10, 10, 10);
			textView.setTextColor(msgColor);
			textView.setTextSize(msgSize);
			textView.setGravity(Gravity.CENTER);
			textView.setLayoutParams(layoutParams);
			// 注意设置ID
			textView.setId(textId);

			toastView.addView(textView);
		}

	}

	/**
	 * 展现Toast
	 * 
	 * @param msg
	 *            消息
	 */
	public static void show(String msg) {
		cancel();
		if (toast == null) {
			createToast();
			toast = new Toast(mContext);
			toast.setView(toastView);
			setText(msg);
			toast.setDuration(mTime);
			if (single == 1) {// 屏幕中间显示
				toast.setGravity(Gravity.CENTER, 0, 0);
			} else if (single == 2) {// 自定义显示位置
				toast.setGravity(Gravity.LEFT | Gravity.TOP, mLocationX,
						mLocatonY);
			} else if (single == 3) {
				toast.setGravity(Gravity.TOP, Gravity.CENTER, Gravity.CENTER+80);
			}

		} else {
			// toast.cancel();
			toast.setDuration(mTime);
			toast.setText(msg);
		}

		countDownTimer = new CountDownTimer(mTime * 1000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				toast.show();
			}

			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				toast.cancel();
				countDownTimer.cancel();
			}
		}.start();

	}

	/**
	 * 取消显示
	 */
	public static void cancel() {
		if (toast != null) {
			countDownTimer.cancel();
			toast.cancel();
			toast = null;
		}

	}

}
