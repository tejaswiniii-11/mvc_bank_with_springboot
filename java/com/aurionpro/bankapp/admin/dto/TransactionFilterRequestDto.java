package com.aurionpro.bankapp.admin.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionFilterRequestDto {
    private String transactionType; // e.g., "TRANSFER", "CREDIT", "DEBIT"
    private Timestamp startDate;
    private Timestamp endDate;
}
