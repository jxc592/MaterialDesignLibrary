package com.guoguang.khgl.library.treeview;

/**
 * 
 * @author yf
 *
 */
public class TreeModelBean {
	@TreeNodeId
	private int id; // 子Id
	@TreeNodePid
	private int pId; // 父Id
	@TreeNodeLabel
	private String label;// 要显示的内容

	@TreeNodeCode
	private String code;// 标签

	/**
	 * 
	 * @param id
	 *            当前Id
	 * @param pId
	 *            id的父Id
	 * @param label
	 *            要显示的内容
	 * @param code
	 *            标签
	 */
	public TreeModelBean(int id, int pId, String label, String code) {
		this.id = id;
		this.pId = pId;
		this.label = label;
		this.code = code;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
