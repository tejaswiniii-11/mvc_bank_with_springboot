package com.aurionpro.bankapp.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class AccountCreationRequestDto {

	private String accountType;

	private double balance;
	
}
