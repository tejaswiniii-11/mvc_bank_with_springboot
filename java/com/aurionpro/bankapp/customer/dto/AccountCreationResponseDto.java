package com.aurionpro.bankapp.customer.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class AccountCreationResponseDto {

	private long accountId;

	private long customerId;

	private String accountNumber; //since account number is stored as string

	private String accountType;

	private double balance;

	private String status;

	private String branch;

	private String ifscCode;

	private String message;
}
