package com.aurionpro.bankapp.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class BankError {
	
	private String error;
	private int status;
	private long timestamp;


}
