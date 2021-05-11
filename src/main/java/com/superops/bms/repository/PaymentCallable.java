package com.superops.bms.repository;

import java.util.concurrent.Callable;

public class PaymentCallable implements Callable<String> {

	private int price;
	private String userId;
	private String passWord;
	private Long paymentTimeOut;

	public PaymentCallable(int price, String userId, String passWord, String paymentTimeOut) {
		this.price = price;
		this.userId = userId;
		this.passWord = passWord;
		this.paymentTimeOut = Long.parseLong(paymentTimeOut);
	}

	@SuppressWarnings("static-access")
	@Override
	public String call() throws Exception {
		// mock implementation for payment
		Thread.currentThread().sleep(paymentTimeOut);
		Long value = Math.round(Math.random() * 10) + 1;
		return value.intValue() > 5 ? "SUCCESS" : "FAILURE";
	}

}