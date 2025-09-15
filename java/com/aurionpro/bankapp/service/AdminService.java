package com.aurionpro.bankapp.service;

import com.aurionpro.bankapp.admin.dto.AccountStatusUpdateRequestDto;
import com.aurionpro.bankapp.admin.dto.AccountStatusUpdateResponseDto;
import com.aurionpro.bankapp.admin.dto.AccountsByStatusResponseDto;
import com.aurionpro.bankapp.admin.dto.AdminLoginRequestDto;
import com.aurionpro.bankapp.admin.dto.AdminLoginResponseDto;
import com.aurionpro.bankapp.admin.dto.AllCustomersResponseDto;
import com.aurionpro.bankapp.admin.dto.AllTransactionsResponseDto;
import com.aurionpro.bankapp.admin.dto.TransactionFilterRequestDto;

public interface AdminService {
	
	AdminLoginResponseDto loginAdmin(AdminLoginRequestDto loginRequest);
	
	AccountStatusUpdateResponseDto updateAccountStatus(long accountId, AccountStatusUpdateRequestDto request);

	AllCustomersResponseDto getAllCustomers();
	
	 AccountsByStatusResponseDto getAccountsByStatus(String status);
	 
	 AllTransactionsResponseDto getFilteredTransactions(TransactionFilterRequestDto filter);
}
