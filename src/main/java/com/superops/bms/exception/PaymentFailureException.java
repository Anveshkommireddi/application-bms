package com.superops.bms.exception;

public class PaymentFailureException extends Exception {

	private static final long serialVersionUID = -1135976417184036420L;
	
	private String message;
	private Throwable throwable;

	public PaymentFailureException() {
		super();
	}

	public PaymentFailureException(String message) {
		super(message);
		this.message = message;
	}

	public PaymentFailureException(Throwable cause) {
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
