package com.aidl.cilent.entity;
/**
 * 报文�?
 * @author thinkpad
 *
 */
public class HeaderCommonEntityRequest {
	private String EncryptInd;//加密标志
	private String UserId; //�?��设备合法性的时�?，不能空
	private String PadId;//设备�?IMEI号�?此字段应加密�?
	private String Flag;//0：请�?1：响�?
	private String SeqNo;// 序列�?
	private String SdSysId;//发�?方系统编�?渠道   0000:Pad。此字段应加密�?
	private String RcvSysId; //0001:前置服务器�?此字段应加密�?
	
	
	
	public String getSdSysId() {
		return SdSysId;
	}
	public void setSdSysId(String sdSysId) {
		SdSysId = sdSysId;
	}
	public String getRcvSysId() {
		return RcvSysId;
	}
	public void setRcvSysId(String rcvSysId) {
		RcvSysId = rcvSysId;
	}
	public String getEncryptInd() {
		return EncryptInd;
	}
	public void setEncryptInd(String encryptInd) {
		EncryptInd = encryptInd;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getPadId() {
		return PadId;
	}
	public void setPadId(String padId) {
		PadId = padId;
	}
	public String getFlag() {
		return Flag;
	}
	public void setFlag(String flag) {
		Flag = flag;
	}
	public String getSeqNo() {
		return SeqNo;
	}
	public void setSeqNo(String seqNo) {
		SeqNo = seqNo;
	}


	
	
}