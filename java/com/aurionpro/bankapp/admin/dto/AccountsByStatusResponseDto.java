package com.aurionpro.bankapp.admin.dto;


import java.util.List;

import com.aurionpro.bankapp.customer.dto.AccountDetailsDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountsByStatusResponseDto {
    private List<AccountDetailsDto> accounts;
    private String message;
}
