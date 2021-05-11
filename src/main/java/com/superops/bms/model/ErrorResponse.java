package com.superops.bms.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ErrorResponse {

	private String errorMessage;

	@JsonInclude(value = Include.NON_NULL)
	private String requestURI;

	@JsonInclude(value = Include.NON_NULL)
	private String fieldName;

	private Timestamp timestamp;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getRequestURI() {
		return requestURI;
	}

	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public ErrorResponse(String errorMessage, String requestURI, String fieldName) {
		this.errorMessage = errorMessage;
		this.requestURI = requestURI;
		this.fieldName = fieldName;
		this.timestamp = new Timestamp(System.currentTimeMillis());
	}

	public ErrorResponse(String errorMessage, String fieldName) {
		this.errorMessage = errorMessage;
		this.fieldName = fieldName;
		this.timestamp = new Timestamp(System.currentTimeMillis());
	}
	
	public ErrorResponse(String errorMessage) {
		this.errorMessage = errorMessage;
		this.timestamp = new Timestamp(System.currentTimeMillis());
	}

	public ErrorResponse() {
	}

}
