package com.aurionpro.bankapp.service;

import com.aurionpro.bankapp.customer.dto.AccountCreationRequestDto;
import com.aurionpro.bankapp.customer.dto.AccountCreationResponseDto;
import com.aurionpro.bankapp.customer.dto.BeneficiaryRequestDto;
import com.aurionpro.bankapp.customer.dto.BeneficiaryResponseDto;
import com.aurionpro.bankapp.customer.dto.CustomerProfileResponseDto;
import com.aurionpro.bankapp.customer.dto.LoginRequestDto;
import com.aurionpro.bankapp.customer.dto.LoginResponseDto;
import com.aurionpro.bankapp.customer.dto.PassbookResponseDto;
import com.aurionpro.bankapp.customer.dto.RegistrationRequestDto;
import com.aurionpro.bankapp.customer.dto.RegistrationResponseDto;
import com.aurionpro.bankapp.customer.dto.TransactionRequestDto;
import com.aurionpro.bankapp.customer.dto.TransactionResponseDto;

public interface CustomerService {
	
	RegistrationResponseDto registerNewCustomer(RegistrationRequestDto registrationRequest);
	
	LoginResponseDto logincustomer(LoginRequestDto  loginRequest);
	
	AccountCreationResponseDto createAccountForCustomer(long customerId, AccountCreationRequestDto request);
	
	BeneficiaryResponseDto addBeneficiary(long customerId, BeneficiaryRequestDto request);
	
	TransactionResponseDto makeTransfer(long customerId, TransactionRequestDto request);
	
	 PassbookResponseDto getPassbook(long customerId);
	 
	 CustomerProfileResponseDto getCustomerProfile(long customerId);
}
