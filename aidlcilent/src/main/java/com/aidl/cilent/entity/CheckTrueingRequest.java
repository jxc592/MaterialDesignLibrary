package com.aidl.cilent.entity;

public class CheckTrueingRequest {
	//后台处理的字段 标记*号
	
	String SysNo="GFMM",//系统号固定为GFMM
	TermId,//请求的终端号
	TellerNum,//柜员号-*
	TellerName,//柜员姓名-*
	BranchId,//机构号-*
	BankCode,//人行交换行号-*
	BankTrace,//银行业务流水号-*
	
	TransCode,//交易码	
	ChkType="2",//核查方式固定为2
	BusinessCode="01",//业务种类 暂定为1 
	IDType,	//证件类型 01-是读身份证，05是手输入身份证
	ID,//证件号
	Name,//姓名
	Gender,//性别"男"或 “女”
	NativePlace,//籍贯 ""
	Address,//住址
	Birthday,//出生年月
	IssueOffice,//签发机构
	Photo,//wlt转jpg
	StartIndate,//证件有效日期
	EndIndate,//证件到期日期
	CheckPhoto="1";//是否需要反馈照片，固定为1
	
	//ryan add
	String Flag = "1";//0 不需要  1：需要
			
	public CheckTrueingRequest(String iD,
			String name, String gender, String nativePlace, String address,
			String birthday, String issueOffice, String photo,
			String startIndate, String endIndate) {
		super();
		this.CheckPhoto="1";
		TransCode = "1005";
		ID = iD;
		Name = name;
		Gender = gender;
		NativePlace = nativePlace;
		Address = address;
		Birthday = birthday;
		IssueOffice = issueOffice;
		Photo = photo;
		StartIndate = startIndate;
		EndIndate = endIndate;
		
	}
	
	public CheckTrueingRequest() {
		super();
		this.CheckPhoto="1";
		TransCode = "1005";
	}

	public String getSysNo() {
		return SysNo;
	}

	public void setSysNo(String sysNo) {
		SysNo = sysNo;
	}

	public String getTermId() {
		return TermId;
	}

	public void setTermId(String termId) {
		TermId = termId;
	}

	public String getTellerNum() {
		return TellerNum;
	}

	public void setTellerNum(String tellerNum) {
		TellerNum = tellerNum;
	}

	public String getTellerName() {
		return TellerName;
	}

	public void setTellerName(String tellerName) {
		TellerName = tellerName;
	}

	public String getBranchId() {
		return BranchId;
	}

	public void setBranchId(String branchId) {
		BranchId = branchId;
	}

	public String getBankCode() {
		return BankCode;
	}

	public void setBankCode(String bankCode) {
		BankCode = bankCode;
	}

	public String getBankTrace() {
		return BankTrace;
	}

	public void setBankTrace(String bankTrace) {
		BankTrace = bankTrace;
	}

	public String getTransCode() {
		return TransCode;
	}

	public void setTransCode(String transCode) {
		TransCode = transCode;
	}

	public String getChkType() {
		return ChkType;
	}

	public void setChkType(String chkType) {
		ChkType = chkType;
	}

	public String getBusinessCode() {
		return BusinessCode;
	}

	public void setBusinessCode(String businessCode) {
		BusinessCode = businessCode;
	}

	public String getIDType() {
		return IDType;
	}

	public void setIDType(String iDType) {
		IDType = iDType;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getNativePlace() {
		return NativePlace;
	}

	public void setNativePlace(String nativePlace) {
		NativePlace = nativePlace;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getBirthday() {
		return Birthday;
	}

	public void setBirthday(String birthday) {
		Birthday = birthday;
	}

	public String getIssueOffice() {
		return IssueOffice;
	}

	public void setIssueOffice(String issueOffice) {
		IssueOffice = issueOffice;
	}

	public String getPhoto() {
		return Photo;
	}

	public void setPhoto(String photo) {
		Photo = photo;
	}

	public String getStartIndate() {
		return StartIndate;
	}

	public void setStartIndate(String startIndate) {
		StartIndate = startIndate;
	}

	public String getEndIndate() {
		return EndIndate;
	}

	public void setEndIndate(String endIndate) {
		EndIndate = endIndate;
	}

	public String getCheckPhoto() {
		return CheckPhoto;
	}

	public void setCheckPhoto(String checkPhoto) {
		CheckPhoto = checkPhoto;
	}

	public String getFlag() {
		return Flag;
	}

	public void setFlag(String flag) {
		Flag = flag;
	}

	
	
	

}
