package com.guoguang.khgl.entity;

public class Cusbrief {
	String CertID;
	String FullName;
	String number;
	String UpdateTime;
	String ManagerUserName;
	public String getCertID() {
		return CertID;
	}
	public void setCertID(String certID) {
		CertID = certID;
	}
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getManagerUserName() {
		return ManagerUserName;
	}
	public void setManagerUserName(String managerUserName) {
		ManagerUserName = managerUserName;
	}
	
}
