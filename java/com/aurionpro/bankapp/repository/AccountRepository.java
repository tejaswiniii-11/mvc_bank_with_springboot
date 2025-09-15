package com.aurionpro.bankapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.bankapp.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	 Optional<Account> findByAccountNumber(String accountNumber);
	
	 List<Account> findByStatus(String status);
}
