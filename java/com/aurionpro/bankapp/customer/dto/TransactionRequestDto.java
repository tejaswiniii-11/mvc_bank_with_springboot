package com.aurionpro.bankapp.customer.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDto {

	private String sourceAccountNumber;
	private String destinationAccountNumber;
	private BigDecimal amount;

}
