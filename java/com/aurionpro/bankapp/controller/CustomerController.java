package com.aurionpro.bankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import com.aurionpro.bankapp.service.CustomerService;

@RestController
@RequestMapping("/api/v1/auth") // A better practice for authentication endpoints
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	
	// Handles customer registration
	@PostMapping("/register")
	public ResponseEntity<RegistrationResponseDto> registerNewCustomer(@RequestBody RegistrationRequestDto registrationRequest){
		return ResponseEntity.status(HttpStatus.CREATED).body(customerService.registerNewCustomer(registrationRequest));
	}
	
	// Handles customer login
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> loginCustomer(@RequestBody LoginRequestDto loginRequest){
		LoginResponseDto response = customerService.logincustomer(loginRequest);
		
		if (response.getMessage().contains("successful")) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
	}
	
	//Account Creation
	 @PostMapping("/customers/{customerId}/accounts")
	    public ResponseEntity<AccountCreationResponseDto> createAccount(
	            @PathVariable long customerId,
	            @RequestBody AccountCreationRequestDto request) {
	        
	        AccountCreationResponseDto response = customerService.createAccountForCustomer(customerId, request);
	        return new ResponseEntity<>(response, HttpStatus.CREATED);
	    }
	 
	 @PostMapping("/customers/{customerId}/beneficiaries")
	    public ResponseEntity<BeneficiaryResponseDto> addBeneficiary(
	            @PathVariable long customerId,
	            @RequestBody BeneficiaryRequestDto request) {
	        
	        BeneficiaryResponseDto response = customerService.addBeneficiary(customerId, request);
	        return new ResponseEntity<>(response, HttpStatus.CREATED);
	    }
	 
	 @PostMapping("/customers/{customerId}/transactions")
	    public ResponseEntity<TransactionResponseDto> makeTransaction(
	            @PathVariable long customerId,
	            @RequestBody TransactionRequestDto request) {
	        
	        TransactionResponseDto response = customerService.makeTransfer(customerId, request);
	        return new ResponseEntity<>(response, HttpStatus.CREATED);
	    }
	 
	 @GetMapping("/customers/{customerId}/passbook")
	    public ResponseEntity<PassbookResponseDto> getPassbook(@PathVariable long customerId) {
	        PassbookResponseDto response = customerService.getPassbook(customerId);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	 
	 @GetMapping("/customers/{customerId}/profile")
	    public ResponseEntity<CustomerProfileResponseDto> getCustomerProfile(@PathVariable long customerId) {
	        CustomerProfileResponseDto response = customerService.getCustomerProfile(customerId);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
}