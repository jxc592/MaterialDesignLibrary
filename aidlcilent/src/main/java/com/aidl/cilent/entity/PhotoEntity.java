package com.aidl.cilent.entity;

public class PhotoEntity {
	
	boolean isBp;//是否必拍标签
	String photoTypeCode;//照片类型
	String photoTypeName;//照片名称
	String photoName;//照片名称
	String photoPath;//照片路劲
	String cusNumId;//人员ID
	boolean canBeTake=true;//是否可拍
	
	public boolean isBp() {
		return isBp;
	}
	public void setBp(boolean isBp) {
		this.isBp = isBp;
	}
	public String getPhotoTypeCode() {
		return photoTypeCode;
	}
	public void setPhotoTypeCode(String photoTypeCode) {
		this.photoTypeCode = photoTypeCode;
	}
	public String getPhotoTypeName() {
		return photoTypeName;
	}
	public void setPhotoTypeName(String photoTypeName) {
		this.photoTypeName = photoTypeName;
	}
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public String getCusNumId() {
		return cusNumId;
	}
	public void setCusNumId(String cusNumId) {
		this.cusNumId = cusNumId;
	}
	public boolean isCanBeTake() {
		return canBeTake;
	}
	public void setCanBeTake(boolean canBeTake) {
		this.canBeTake = canBeTake;
	}
	
}
