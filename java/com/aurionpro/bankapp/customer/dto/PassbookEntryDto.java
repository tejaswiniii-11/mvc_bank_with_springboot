package com.aurionpro.bankapp.customer.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassbookEntryDto {

    private long transactionId;
    private String transactionType; // e.g., "TRANSFER", "CREDIT", "DEBIT"
    private BigDecimal amount;
    private String sourceAccountNumber;
    private String destinationAccountNumber;
    private Timestamp createdAt;
}
