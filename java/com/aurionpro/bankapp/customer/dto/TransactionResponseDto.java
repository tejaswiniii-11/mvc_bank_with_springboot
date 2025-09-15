package com.aurionpro.bankapp.customer.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDto {

	 private long transactionId;
	    private BigDecimal amount;
	    private String transactionType;
	    private String status;
	    private String message;
	    private Timestamp createdAt;
	
}
