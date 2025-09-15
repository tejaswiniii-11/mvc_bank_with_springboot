package com.aurionpro.bankapp.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeneficiaryRequestDto {

	private String ifscCode;
	private String branch;
	private String accountNumber;
	private String nickname;

}
