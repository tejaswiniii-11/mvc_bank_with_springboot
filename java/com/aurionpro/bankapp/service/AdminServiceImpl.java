package com.aurionpro.bankapp.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aurionpro.bankapp.admin.dto.AccountStatusUpdateRequestDto;
import com.aurionpro.bankapp.admin.dto.AccountStatusUpdateResponseDto;
import com.aurionpro.bankapp.admin.dto.AccountsByStatusResponseDto;
import com.aurionpro.bankapp.admin.dto.AdminCustomerDetailsDto;
import com.aurionpro.bankapp.admin.dto.AdminLoginRequestDto;
import com.aurionpro.bankapp.admin.dto.AdminLoginResponseDto;
import com.aurionpro.bankapp.admin.dto.AllCustomersResponseDto;
import com.aurionpro.bankapp.admin.dto.AllTransactionsResponseDto;
import com.aurionpro.bankapp.admin.dto.TransactionDetailsDto;
import com.aurionpro.bankapp.admin.dto.TransactionFilterRequestDto;
import com.aurionpro.bankapp.customer.dto.AccountDetailsDto;
import com.aurionpro.bankapp.entity.Account;
import com.aurionpro.bankapp.entity.Admin;
import com.aurionpro.bankapp.entity.Customer;
import com.aurionpro.bankapp.entity.Transaction;
import com.aurionpro.bankapp.exception.BankApiException;
import com.aurionpro.bankapp.repository.AccountRepository;
import com.aurionpro.bankapp.repository.AdminRepository;
import com.aurionpro.bankapp.repository.CustomerRepository;
import com.aurionpro.bankapp.repository.TransactionRepository;

import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public AdminLoginResponseDto loginAdmin(AdminLoginRequestDto loginRequest) {
		Optional<Admin> optionalAdmin = adminRepository.findByUsername(loginRequest.getUsername());

		if (optionalAdmin.isPresent()) {
			Admin admin = optionalAdmin.get();
			if (passwordEncoder.matches(loginRequest.getPassword(), admin.getPassword())) {
				AdminLoginResponseDto response = new AdminLoginResponseDto();
				response.setUsername(admin.getUsername());
				response.setMessage("Admin login successful!");
				return response;
			}
		}
		AdminLoginResponseDto response = new AdminLoginResponseDto();
		response.setUsername(loginRequest.getUsername());
		response.setMessage("Admin login failed: Invalid username or password.");
		return response;
	}

	@Override
	public AccountStatusUpdateResponseDto updateAccountStatus(long accountId, AccountStatusUpdateRequestDto request) {
		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> new BankApiException("Account not found with ID: " + accountId));
		// To validate new status
		String newStatus = request.getStatus().toUpperCase();
		if (!newStatus.equals("ACTIVE") && !newStatus.equals("REJECTED")) {
			throw new IllegalArgumentException("Invalid status. Must be 'ACTIVE' or 'REJECTED'.");
		}
		account.setStatus(newStatus);
		accountRepository.save(account);

		AccountStatusUpdateResponseDto response = new AccountStatusUpdateResponseDto();
		response.setAccountId(account.getAccountId());
		response.setAccountNumber(account.getAccountNumber());
		response.setAccountType(account.getAccountType());
		response.setBalance(account.getBalance());
		response.setBranch(account.getBranch());
		response.setIfscCode(account.getIfscCode());
		response.setStatus(account.getStatus());
		response.setUpdatedAt(Timestamp.from(Instant.now()));
		response.setMessage("Account status updated to " + newStatus);

		return response;
	}

	@Transactional
	@Override
	public AllCustomersResponseDto getAllCustomers() {

		List<Customer> customers = customerRepo.findAll();

		List<AdminCustomerDetailsDto> customerDetailsList = customers.stream().map(customer -> {
			AdminCustomerDetailsDto dto = mapper.map(customer, AdminCustomerDetailsDto.class);
			dto.setAccounts(customer.getAccounts().stream().map(account -> mapper.map(account, AccountDetailsDto.class))
					.collect(Collectors.toList()));
			return dto;
		}).collect(Collectors.toList());

		return new AllCustomersResponseDto(customerDetailsList, "All customers retrieved successfully.");
	}

	@Transactional
	@Override
	public AccountsByStatusResponseDto getAccountsByStatus(String status) {

		List<Account> accounts = accountRepository.findByStatus(status.toUpperCase());

		List<AccountDetailsDto> accountDetailsList = accounts.stream()
				.map(account -> mapper.map(account, AccountDetailsDto.class)).collect(Collectors.toList());

		return new AccountsByStatusResponseDto(accountDetailsList,
				"Accounts with status '" + status + "' retrieved successfully.");
	}
	
	@Transactional
    @Override
    public AllTransactionsResponseDto getFilteredTransactions(TransactionFilterRequestDto filter) {
        Specification<Transaction> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getTransactionType() != null && !filter.getTransactionType().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("transactionType"), filter.getTransactionType()));
            }

            if (filter.getStartDate() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), filter.getStartDate()));
            }

            if (filter.getEndDate() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), filter.getEndDate()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        List<Transaction> transactions = transactionRepository.findAll(spec);

        List<TransactionDetailsDto> transactionDetails = transactions.stream()
            .map(transaction -> {
                TransactionDetailsDto dto = mapper.map(transaction, TransactionDetailsDto.class);
                if (transaction.getSourceAccount() != null) {
                    dto.setSourceAccountNumber(transaction.getSourceAccount().getAccountNumber());
                    dto.setSourceAccountId(transaction.getSourceAccount().getAccountId());
                }
                if (transaction.getDestinationAccount() != null) {
                    dto.setDestinationAccountNumber(transaction.getDestinationAccount().getAccountNumber());
                    dto.setDestinationAccountId(transaction.getDestinationAccount().getAccountId());
                }
                return dto;
            })
            .collect(Collectors.toList());

        return new AllTransactionsResponseDto(transactionDetails, "Transactions retrieved successfully.");
    }

}
