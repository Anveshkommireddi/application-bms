package com.superops.bms.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


public class CommonResponse {

	@JsonInclude(value = Include.NON_EMPTY)
	private List<ErrorResponse> errList = new ArrayList<>();
	
	@JsonInclude(value = Include.NON_NULL)
	private String status;
	
	@JsonInclude(value = Include.NON_NULL)
	private String message;

	// not providing setters since already initialized
	public List<ErrorResponse> getErrList() {
		return errList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean hasErrors() {
		return errList.size() > 0;
	}

	
}
