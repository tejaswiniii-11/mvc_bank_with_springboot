package com.aurionpro.bankapp.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetailsDto {

    private long transactionId;
    private BigDecimal amount;
    private String transactionType;
    private String status;
    private Timestamp createdAt;
    private String sourceAccountNumber;
    private String destinationAccountNumber;
    private long sourceAccountId;
    private long destinationAccountId;
}