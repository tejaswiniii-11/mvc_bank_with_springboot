package com.aurionpro.bankapp.customer.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeneficiaryResponseDto {
	
	private long beneficiaryId;
    private String accountNumber;
    private String ifscCode;
	private String branch;
    private String nickname;
    private String message;
    private Timestamp createdAt;

}
