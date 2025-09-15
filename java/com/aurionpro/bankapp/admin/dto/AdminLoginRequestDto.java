package com.aurionpro.bankapp.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class AdminLoginRequestDto {
	
	private String username;
    private String password;

}
