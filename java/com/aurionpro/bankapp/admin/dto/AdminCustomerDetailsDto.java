package com.aurionpro.bankapp.admin.dto;

import java.sql.Timestamp;
import java.util.List;

import com.aurionpro.bankapp.customer.dto.AccountDetailsDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminCustomerDetailsDto {
    
    private long customerId;
    private String username;
    private String fullName;
    private String email;
    private String phone;
    private Timestamp createdAt;
    private List<AccountDetailsDto> accounts; // Use the DTO from the customer profile feature
}
