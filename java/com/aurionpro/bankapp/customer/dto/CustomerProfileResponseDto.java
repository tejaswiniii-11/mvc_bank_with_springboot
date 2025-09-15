package com.aurionpro.bankapp.customer.dto;
import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerProfileResponseDto {

    private long customerId;
    private String username;
    private String fullName;
    private String email;
    private String phone;
    private Timestamp createdAt;
    private List<AccountDetailsDto> accounts;
}
