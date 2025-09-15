package com.aurionpro.bankapp.exception;

public class BankApiException extends RuntimeException{

	private String message;
	
	public String getMessage() {
		return message;
	}

	public BankApiException(String message) {
		super();
		this.message = message;
	}
	
}
