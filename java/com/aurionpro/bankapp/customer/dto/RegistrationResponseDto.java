package com.aurionpro.bankapp.customer.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class RegistrationResponseDto {

	private long customerId;

	private String username;

	private String password;

	private String fullName;

	private String email;

	private String phone;

	private Timestamp createdAt;

}
