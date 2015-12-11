package com.aidl.cilent.entity;

/**
 * 登陆返回 报文体
 * 
 * @author thinkpad
 *
 */
public class LoginResponse {
	private String TransCode = "";// 交易码
	private String UserId = "";// 柜员号
	private String Name = "";// 姓名
	private String Sex = "";// 0：男 1：女。
	private String BranchName = "";// 所属机构
	private String Tel = ""; // 电话号码
	private String EmailAddr = "";// Email地址
	private String Mobileno = "";// 手机号码
	private String BranchId="";
	// 系统参数
	private String PwdInputTimes = "";// 密码输错次数
	private String LastLoginTime = "";// 最后登录时间
	private String RoleId = "";// 2001-操作员，2002-授权员，2003-客 户经理
	private String systemParams = "";// 系统参数
	private String Balance;// 网银操作时验证余额是否超限的判断条件

	public String getBalance() {
		return Balance;
	}

	public void setBalance(String balance) {
		Balance = balance;
	}

	public String getTransCode() {
		return TransCode;
	}

	public void setTransCode(String transCode) {
		TransCode = transCode;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public String getBranchName() {
		return BranchName;
	}

	public void setBranchName(String branchName) {
		BranchName = branchName;
	}

	public String getTel() {
		return Tel;
	}

	public void setTel(String tel) {
		Tel = tel;
	}

	public String getEmailAddr() {
		return EmailAddr;
	}

	public void setEmailAddr(String emailAddr) {
		EmailAddr = emailAddr;
	}

	public String getMobileno() {
		return Mobileno;
	}

	public void setMobileno(String mobileno) {
		Mobileno = mobileno;
	}

	public String getPwdInputTimes() {
		return PwdInputTimes;
	}

	public void setPwdInputTimes(String pwdInputTimes) {
		PwdInputTimes = pwdInputTimes;
	}

	public String getLastLoginTime() {
		return LastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		LastLoginTime = lastLoginTime;
	}

	public String getRoleId() {
		return RoleId;
	}

	public void setRoleId(String roleId) {
		RoleId = roleId;
	}

	public String getSystemParams() {
		return systemParams;
	}

	public void setSystemParams(String systemParams) {
		this.systemParams = systemParams;
	}

	public String getBranchId() {
		return BranchId;
	}

	public void setBranchId(String branchId) {
		BranchId = branchId;
	}
	
}
