package com.guoguang.dksq.model;

import java.util.Map;
/**
 * 界面数据处理的接口
 * @author thinkpad
 *
 */
public interface IDataInterface {
	/**
	 * 给界面上控件设置数据
	 */
	public void setBusinessData();
	/**
	 * 得到界面上控件的数据
	 * @return
	 */
	public Map<String, Object> getBusinessData();
	/**
	 * 上一步的处理事件
	 */
	public void lastClick();
	/**
	 * 下一步处理事件
	 */
	public void nextClick();
	/**
	 * 保存数据到草稿
	 */
	public void saveDraft();
}
