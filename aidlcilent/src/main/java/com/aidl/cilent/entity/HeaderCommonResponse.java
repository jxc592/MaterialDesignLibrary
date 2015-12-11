package com.aidl.cilent.entity;

public class HeaderCommonResponse {
	private String Message; //消息 
	private String ResultCode;//返回状�?�?000：成�?其他失败
	private String SeqNo; //交易序号
	private String SdSysId; //发�?方系统编�?渠道 0001:前置服务器�?此字段应加密
	private String RcvSysId;//
	private String Flag;
	private String UserId;
	private String PadId;
	private String EncryptInd;
	
	
	
	public String getSeqNo() {
		return SeqNo;
	}
	public void setSeqNo(String seqNo) {
		SeqNo = seqNo;
	}
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
	public String getFlag() {
		return Flag;
	}
	public void setFlag(String flag) {
		Flag = flag;
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
	public String getEncryptInd() {
		return EncryptInd;
	}
	public void setEncryptInd(String encryptInd) {
		EncryptInd = encryptInd;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public String getResultCode() {
		return ResultCode;
	}
	public void setResultCode(String resultCode) {
		ResultCode = resultCode;
	}
	
}
