package com.aidl.cilent.entity;

public class CheckTrueingResponse {
	String CheckResultName;
	String info;
	String Name;
	String TransCode;
	String CheckResult;
	String ChkType;
	String ID;
	String HasCustInfo;
	String EntrustDate;
	String LocalTrace;
	String packFlag;
	String Success;
	String Photo;
	String IssueOffice;
	
	String Flag; //1：重新核查 0：直接读取
	
	
	public String getFlag() {
		return Flag;
	}
	public void setFlag(String flag) {
		Flag = flag;
	}
	public String getIssueOffice() {
		return IssueOffice;
	}
	public void setIssueOffice(String issueOffice) {
		IssueOffice = issueOffice;
	}
	public String getCheckResultName() {
		return CheckResultName;
	}
	public void setCheckResultName(String checkResultName) {
		CheckResultName = checkResultName;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getTransCode() {
		return TransCode;
	}
	public void setTransCode(String transCode) {
		TransCode = transCode;
	}
	public String getCheckResult() {
		return CheckResult;
	}
	public void setCheckResult(String checkResult) {
		CheckResult = checkResult;
	}
	public String getChkType() {
		return ChkType;
	}
	public void setChkType(String chkType) {
		ChkType = chkType;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getHasCustInfo() {
		return HasCustInfo;
	}
	public void setHasCustInfo(String hasCustInfo) {
		HasCustInfo = hasCustInfo;
	}
	public String getEntrustDate() {
		return EntrustDate;
	}
	public void setEntrustDate(String entrustDate) {
		EntrustDate = entrustDate;
	}
	public String getLocalTrace() {
		return LocalTrace;
	}
	public void setLocalTrace(String localTrace) {
		LocalTrace = localTrace;
	}
	public String getPackFlag() {
		return packFlag;
	}
	public void setPackFlag(String packFlag) {
		this.packFlag = packFlag;
	}
	public String getSuccess() {
		return Success;
	}
	public void setSuccess(String success) {
		Success = success;
	}
	public String getPhoto() {
		return Photo;
	}
	public void setPhoto(String photo) {
		Photo = photo;
	}
	
	
}
