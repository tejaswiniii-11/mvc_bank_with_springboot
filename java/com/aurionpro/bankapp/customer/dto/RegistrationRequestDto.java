package com.aurionpro.bankapp.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class RegistrationRequestDto {

	private String username;

	private String password;

	private String fullName;

	private String email;

	private String phone;

}
