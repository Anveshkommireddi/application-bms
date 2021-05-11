package com.superops.bms.exception;

public class ConcurrentBookingException extends Exception {

	private static final long serialVersionUID = 8018885760763208235L;

	private String message;
	
	private Throwable throwable;

	public ConcurrentBookingException() {
		super();
	}

	public ConcurrentBookingException(String message) {
		super(message);
		this.message = message;
	}

	public ConcurrentBookingException(Throwable cause) {
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
