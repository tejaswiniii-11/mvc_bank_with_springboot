package com.aurionpro.bankapp.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aurionpro.bankapp.customer.dto.AccountCreationRequestDto;
import com.aurionpro.bankapp.customer.dto.AccountCreationResponseDto;
import com.aurionpro.bankapp.customer.dto.AccountDetailsDto;
import com.aurionpro.bankapp.customer.dto.BeneficiaryRequestDto;
import com.aurionpro.bankapp.customer.dto.BeneficiaryResponseDto;
import com.aurionpro.bankapp.customer.dto.CustomerProfileResponseDto;
import com.aurionpro.bankapp.customer.dto.LoginRequestDto;
import com.aurionpro.bankapp.customer.dto.LoginResponseDto;
import com.aurionpro.bankapp.customer.dto.PassbookEntryDto;
import com.aurionpro.bankapp.customer.dto.PassbookResponseDto;
import com.aurionpro.bankapp.customer.dto.RegistrationRequestDto;
import com.aurionpro.bankapp.customer.dto.RegistrationResponseDto;
import com.aurionpro.bankapp.customer.dto.TransactionRequestDto;
import com.aurionpro.bankapp.customer.dto.TransactionResponseDto;
import com.aurionpro.bankapp.entity.Account;
import com.aurionpro.bankapp.entity.Beneficiary;
import com.aurionpro.bankapp.entity.Customer;
import com.aurionpro.bankapp.entity.Transaction;
import com.aurionpro.bankapp.exception.BankApiException;
import com.aurionpro.bankapp.repository.AccountRepository;
import com.aurionpro.bankapp.repository.BeneficiaryRepository;
import com.aurionpro.bankapp.repository.CustomerRepository;
import com.aurionpro.bankapp.repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private BeneficiaryRepository beneficiaryRepo;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper mapper;

	@Override
	public RegistrationResponseDto registerNewCustomer(RegistrationRequestDto registrationRequest) {
		// Step 1: Map the DTO to a Customer entity
		Customer newCustomer = mapper.map(registrationRequest, Customer.class);

		// Step 2: Hash the password for security
		newCustomer.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

		// Step 3: Set creation timestamp
		newCustomer.setCreatedAt(Timestamp.from(Instant.now()));

		// Step 4: Use the injected repository to save the new customer
		Customer savedCustomer = customerRepo.save(newCustomer);

		// Step 5: Map the saved entity back to the response DTO
		return mapper.map(savedCustomer, RegistrationResponseDto.class);
	}

	@Override
	public LoginResponseDto logincustomer(LoginRequestDto loginRequest) {

		Optional<Customer> optionalCustomer = customerRepo.findByUsername(loginRequest.getUsername());

		if (optionalCustomer.isPresent()) {
			Customer customer = optionalCustomer.get();
			// Check if the provided password matches the stored, hashed password
			if (passwordEncoder.matches(loginRequest.getPassword(), customer.getPassword())) {

				// Create a new LoginResponseDto to return on success
				LoginResponseDto loginresdto = new LoginResponseDto();
				loginresdto.setUsername(customer.getUsername());
				loginresdto.setMessage("Login successful!");

				return loginresdto;
			}
		}

		// Create a new LoginResponseDto to return on failure
		LoginResponseDto loginresdto = new LoginResponseDto();
		loginresdto.setUsername(loginRequest.getUsername());
		loginresdto.setMessage("Login failed: Invalid username or password.");

		return loginresdto;
	}

	// For account number generation
	private String generateUniqueAccountNumber() {
		return "ACC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase(); // 11 digit account number
	}

	@Override
	public AccountCreationResponseDto createAccountForCustomer(long customerId, AccountCreationRequestDto request) {
		Customer customer = customerRepo.findById(customerId)
				.orElseThrow(() -> new BankApiException("Customer not found with ID: " + customerId));
		Account newAccount = new Account();

		newAccount.setAccountNumber(generateUniqueAccountNumber());
		newAccount.setAccountType(request.getAccountType());
		newAccount.setBalance(request.getBalance());
		newAccount.setStatus("PENDING");
		newAccount.setBranch("Mumbai");
		newAccount.setIfscCode("MUMB0000567");
		newAccount.setCreatedAt(Timestamp.from(Instant.now()));
		newAccount.setCustomer(customer);

		Account savedAccount = accountRepo.save(newAccount);

		AccountCreationResponseDto response = new AccountCreationResponseDto();
		response.setAccountId(savedAccount.getAccountId());
		response.setCustomerId(savedAccount.getCustomer().getCustomerId());
		response.setAccountNumber(savedAccount.getAccountNumber()); // Using String
		response.setAccountType(savedAccount.getAccountType());
		response.setBalance(savedAccount.getBalance());
		response.setStatus(savedAccount.getStatus());
		response.setBranch(savedAccount.getBranch());
		response.setIfscCode(savedAccount.getIfscCode());
		response.setMessage("Account created successfully. Status is PENDING.");

		return response;
	}

	@Transactional
	@Override
	public BeneficiaryResponseDto addBeneficiary(long customerId, BeneficiaryRequestDto request) {
		Customer customer = customerRepo.findById(customerId)
				.orElseThrow(() -> new BankApiException("Customer not found with ID: " + customerId));

		// check if customer has approved account and atleast 1 approved account
		boolean hasActiveAccount = customer.getAccounts().stream()
				.anyMatch(account -> "ACTIVE".equals(account.getStatus()));

		if (!hasActiveAccount) {
			throw new BankApiException("Customer must have at least one approved account to add a beneficiary.");
		}

		Beneficiary newBeneficiary = new Beneficiary();
		newBeneficiary.setAccountNumber(request.getAccountNumber());
		newBeneficiary.setBranch(request.getBranch());
		newBeneficiary.setIfscCode(request.getIfscCode());
		newBeneficiary.setNickname(request.getNickname());
		newBeneficiary.setCustomer(customer);
		newBeneficiary.setCreatedAt(Timestamp.from(Instant.now()));

		Beneficiary savedBeneficiary = beneficiaryRepo.save(newBeneficiary);

		BeneficiaryResponseDto response = new BeneficiaryResponseDto();
		response.setBeneficiaryId(savedBeneficiary.getBeneficiaryId());
		response.setAccountNumber(savedBeneficiary.getAccountNumber());
		response.setBranch(savedBeneficiary.getBranch());
		response.setIfscCode(savedBeneficiary.getIfscCode());
		response.setNickname(savedBeneficiary.getNickname());
		response.setCreatedAt(savedBeneficiary.getCreatedAt());
		response.setMessage("Beneficiary added successfully.");

		return response;

	}

	@Transactional
	@Override
	public TransactionResponseDto makeTransfer(long customerId, TransactionRequestDto request) {
		Account sourceAccount = accountRepo.findByAccountNumber(request.getSourceAccountNumber())
	            .orElseThrow(() -> new BankApiException("Source account not found."));
	    
		//validations
		if (!"ACTIVE".equals(sourceAccount.getStatus())) {
            throw new IllegalStateException("Source account is not active.");
        }

        if (sourceAccount.getBalance() < request.getAmount().doubleValue()) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        
	    Account destinationAccount = accountRepo.findByAccountNumber(request.getDestinationAccountNumber())
	            .orElseThrow(() -> new BankApiException("Beneficiary account not found."));
	    
	    sourceAccount.setBalance(sourceAccount.getBalance() - request.getAmount().doubleValue());
        destinationAccount.setBalance(destinationAccount.getBalance() + request.getAmount().doubleValue());
        
        accountRepo.save(sourceAccount);
        accountRepo.save(destinationAccount);
        
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(request.getAmount());
        newTransaction.setSourceAccount(sourceAccount);
        newTransaction.setDestinationAccount(destinationAccount);
        newTransaction.setTransactionType("DEBIT");
        newTransaction.setStatus("SUCCESS");
        newTransaction.setCreatedAt(Timestamp.from(Instant.now()));
        
        Transaction savedTransaction = transactionRepository.save(newTransaction);
        
        TransactionResponseDto response = new TransactionResponseDto();
        response.setTransactionId(savedTransaction.getTransactionId());
        response.setAmount(savedTransaction.getAmount());
        response.setTransactionType(savedTransaction.getTransactionType());
        response.setStatus(savedTransaction.getStatus());
        response.setMessage("Debited successfully.");
        response.setCreatedAt(savedTransaction.getCreatedAt());
        
        return response;
	}
	
	 @Transactional
	    public void creditAccount(String destinationAccountNumber, BigDecimal amount) {
	        // This is for external credits
	        Account destinationAccount = accountRepo.findByAccountNumber(destinationAccountNumber)
	                .orElseThrow(() -> new BankApiException("Destination account not found."));

	        destinationAccount.setBalance(destinationAccount.getBalance() + amount.doubleValue());
	        accountRepo.save(destinationAccount);
	        
	     // After saving the account with the new balance
	        Transaction creditTransaction = new Transaction();
	        creditTransaction.setAmount(amount);
	        creditTransaction.setDestinationAccount(destinationAccount); // The credit went to this account
	        creditTransaction.setTransactionType("CREDIT");
	        creditTransaction.setStatus("SUCCESS");
	        creditTransaction.setCreatedAt(Timestamp.from(Instant.now()));

	        transactionRepository.save(creditTransaction);
	    }

	 
	 @Transactional
	    @Override
	    public PassbookResponseDto getPassbook(long customerId) {
	        Customer customer = customerRepo.findById(customerId)
	                .orElseThrow(() -> new BankApiException("Customer not found with ID: " + customerId));

	        //to collect all transactions from all of the customer's accounts
	        List<Transaction> allTransactions = customer.getAccounts().stream()
	                .flatMap(account -> Stream.concat(
	                        account.getSentTransactions().stream(),
	                        account.getReceivedTransactions().stream()
	                ))
	                .sorted(Comparator.comparing(Transaction::getCreatedAt)) // Sort by date
	                .collect(Collectors.toList());

	        // Map the combined list of transactions to the DTOs
	        List<PassbookEntryDto> passbookEntries = allTransactions.stream()
	                .map(transaction -> {
	                    PassbookEntryDto dto = new PassbookEntryDto();
	                    dto.setTransactionId(transaction.getTransactionId());
	                    dto.setTransactionType(transaction.getTransactionType());
	                    dto.setAmount(transaction.getAmount());
	                    // Use a ternary operator to handle null sources/destinations
	                    dto.setSourceAccountNumber(transaction.getSourceAccount() != null ? transaction.getSourceAccount().getAccountNumber() : "External");
	                    dto.setDestinationAccountNumber(transaction.getDestinationAccount() != null ? transaction.getDestinationAccount().getAccountNumber() : "External");
	                    dto.setCreatedAt(transaction.getCreatedAt());
	                    return dto;
	                })
	                .collect(Collectors.toList());

	        PassbookResponseDto response = new PassbookResponseDto();
	        response.setCustomerName(customer.getFullName());
	        response.setTransactions(passbookEntries);
	        
	        return response;
	    }
	 
	 
	 @Transactional
	    @Override
	    public CustomerProfileResponseDto getCustomerProfile(long customerId) {
	        Customer customer = customerRepo.findById(customerId)
	                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));

	        CustomerProfileResponseDto response = mapper.map(customer, CustomerProfileResponseDto.class);

	        // Map the list of Account entities to AccountDetailsDto
	        response.setAccounts(customer.getAccounts().stream()
	                .map(account -> mapper.map(account, AccountDetailsDto.class))
	                .collect(Collectors.toList()));

	        return response;
	    }
}
