package com.aurionpro.bankapp.admin.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountStatusUpdateResponseDto {

	private long accountId;
	private String accountNumber;

	private String accountType;

	private double balance;
	private String branch;

	private String ifscCode;
	private String status;
	private String message;
	private Timestamp updatedAt;

}
