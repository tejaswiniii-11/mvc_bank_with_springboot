package com.aurionpro.bankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.bankapp.admin.dto.AccountStatusUpdateRequestDto;
import com.aurionpro.bankapp.admin.dto.AccountStatusUpdateResponseDto;
import com.aurionpro.bankapp.admin.dto.AccountsByStatusResponseDto;
import com.aurionpro.bankapp.admin.dto.AdminLoginRequestDto;
import com.aurionpro.bankapp.admin.dto.AdminLoginResponseDto;
import com.aurionpro.bankapp.admin.dto.AllCustomersResponseDto;
import com.aurionpro.bankapp.admin.dto.AllTransactionsResponseDto;
import com.aurionpro.bankapp.admin.dto.TransactionFilterRequestDto;
import com.aurionpro.bankapp.service.AdminService;

@RestController
@RequestMapping("/api/v1/auth/admin")
public class AdminController {
	
	 @Autowired
	    private AdminService adminService;
	 
	 @PostMapping("/login")
	    public ResponseEntity<AdminLoginResponseDto> loginAdmin(@RequestBody AdminLoginRequestDto loginRequest) {
	        AdminLoginResponseDto response = adminService.loginAdmin(loginRequest);

	        if (response.getMessage().contains("successful")) {
	            return new ResponseEntity<>(response, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	        }
	    }
	 
	 @PatchMapping("/accounts/{accountId}/status")
	    public ResponseEntity<AccountStatusUpdateResponseDto> updateAccountStatus(
	            @PathVariable long accountId,
	            @RequestBody AccountStatusUpdateRequestDto request) {
	        
	        AccountStatusUpdateResponseDto response = adminService.updateAccountStatus(accountId, request);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	 
	 @GetMapping("/customers")
	    public ResponseEntity<AllCustomersResponseDto> getAllCustomers() {
	        AllCustomersResponseDto response = adminService.getAllCustomers();
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	 
	 @GetMapping("/accounts/status/{status}")
	    public ResponseEntity<AccountsByStatusResponseDto> getAccountsByStatus(@PathVariable String status) {
	        AccountsByStatusResponseDto response = adminService.getAccountsByStatus(status);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }

	 
	 @PostMapping("/transactions/search")
	    public ResponseEntity<AllTransactionsResponseDto> getFilteredTransactions(@RequestBody TransactionFilterRequestDto filter) {
	        AllTransactionsResponseDto response = adminService.getFilteredTransactions(filter);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
}
