package com.aurionpro.bankapp.customer.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailsDto {

    private long accountId;
    private String accountNumber;
    private double balance;
    private String accountType;
    private String status;
    private String branch;
    private String ifscCode;
    private Timestamp createdAt;
}
