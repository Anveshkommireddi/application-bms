package com.superops.bms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "USER")
public class UserEntity {

	@Id
	@Column(name = "USER_PHNE_NB")
	private Long mobileNo;

	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "USER_MAIL_ID")
	private String mailId;

	@Column(name = "USER_PASS_WD")
	private String passWord;

	@Column(name = "USER_TOKEN")
	private String token;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "USER_TOKEN_UPDT_TIME")
	private Date tokenUpdtTime;

	@Column(name = "USER_ROLE")
	private String role;

	@Version
	@Column(name = "USR_VERSION")
	private Long version;

	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
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

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getTokenUpdtTime() {
		return tokenUpdtTime;
	}

	public void setTokenUpdtTime(Date tokenUpdtTime) {
		this.tokenUpdtTime = tokenUpdtTime;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
	public UserEntity() {
	}
	
	public UserEntity(Long mobileNo, String userId, String mailId, String passWord, String role) {
		this.mobileNo = mobileNo;
		this.userId = userId;
		this.mailId = mailId;
		this.passWord = passWord;
		this.role = role;
	}
	
}
