package com.aurionpro.bankapp.entity;

import java.sql.Timestamp;
import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="accounts")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Account {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long accountId;
	
//	@Column
//	private long customerId;
	
	@Column
	private String accountNumber; //keeping account number as string
	
	@Column
	private String accountType;
	
	@Column
	private double balance;
	
	@Column
	private String status;
	
	@Column
	private String branch;
	
	@Column
	private String ifscCode;
	
	@Column
	private Timestamp createdAt;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	// Transactions where this account is the source
		@OneToMany(mappedBy = "sourceAccount")
		private List<Transaction> sentTransactions;

		// Transactions where this account is the destination
		@OneToMany(mappedBy = "destinationAccount")
		private List<Transaction> receivedTransactions;
}
