package com.aurionpro.bankapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.aurionpro.bankapp.error.BankError;

public class GlobalExceptionHandler {
	
	@ExceptionHandler

	ResponseEntity<BankError> handleBankApiException(BankApiException exception) {

		BankError error = new BankError();

		error.setError(exception.getMessage());

		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

		error.setTimestamp(System.currentTimeMillis());
 
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@ExceptionHandler(IllegalArgumentException.class)

	public ResponseEntity<BankError> handleIllegalArgumentException(IllegalArgumentException ex) {

		BankError error = new BankError();

		error.setError(ex.getMessage()); // Only show the message, not the full exception

		error.setStatus(HttpStatus.BAD_REQUEST.value());

		error.setTimestamp(System.currentTimeMillis());
 
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

	}

}
