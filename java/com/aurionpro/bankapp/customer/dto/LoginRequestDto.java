package com.aurionpro.bankapp.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class LoginRequestDto {
	
	private String username;

	private String password;

}
