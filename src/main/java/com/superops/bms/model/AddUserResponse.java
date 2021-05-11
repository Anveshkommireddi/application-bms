package com.superops.bms.model;

public class AddUserResponse extends CommonResponse {

	private String mobileNo;

	private String userId;

	private String mailId;

	private String role;

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public AddUserResponse(String mobileNo, String userId, String mailId, String role) {
		this.mobileNo = mobileNo;
		this.userId = userId;
		this.mailId = mailId;
		this.role = role;
	}

}
