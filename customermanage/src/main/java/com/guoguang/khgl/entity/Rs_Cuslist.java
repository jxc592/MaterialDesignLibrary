package com.guoguang.khgl.entity;

import com.guoguang.khgl.database.CusInfo;

import java.util.List;


public class Rs_Cuslist {
	List<CusInfo> DraftList;
	
	String totalNum;
	String targetPage;
	String totalPage;



	public List<CusInfo> getDraftList() {
		return DraftList;
	}

	public void setDraftList(List<CusInfo> draftList) {
		DraftList = draftList;
	}

	public String getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}

	public String getTargetPage() {
		return targetPage;
	}

	public void setTargetPage(String targetPage) {
		this.targetPage = targetPage;
	}

	public String getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}


	
}
