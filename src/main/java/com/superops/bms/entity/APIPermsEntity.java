package com.superops.bms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "API_PERMS", uniqueConstraints = @UniqueConstraint(columnNames = {"URI", "ACTION"}))
public class APIPermsEntity {

	@Id
	@Column(name = "URI")
	private String uri;

	@Column(name = "ACTION")
	private String action;

	@Column(name = "ROLES")
	private String roles;

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
}
