package com.aidl.cilent.entity;

public class PersonInfo {
	String issueDepartmanet;// 签发机关
	String name;// 姓名
	String number;// 身份证号
	String startDate;// 证件有效期开始
	String endDate;// 证件有效期结束
	String address;//户籍地址
	String photo;//人物头像数据
	String sex;
//	Bitmap photo;
	boolean result;// true 成功 false 失败
	String resultMsg;
	
	public String getIssueDepartmanet() {
		return issueDepartmanet;
	}
	public void setIssueDepartmanet(String issueDepartmanet) {
		this.issueDepartmanet = issueDepartmanet;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
}
