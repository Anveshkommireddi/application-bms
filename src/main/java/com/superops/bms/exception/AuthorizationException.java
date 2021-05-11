package com.superops.bms.exception;

public class AuthorizationException extends Exception {
	
	private static final long serialVersionUID = -7345532203416332885L;
	
	private String message;
	
	private Throwable throwable;

	public AuthorizationException() {
		super();
	}

	public AuthorizationException(String message) {
		super(message);
		this.message = message;
	}

	public AuthorizationException(Throwable cause) {
		super(cause);
		this.throwable = cause;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public Throwable getThrowable() {
		return throwable;
	}
}