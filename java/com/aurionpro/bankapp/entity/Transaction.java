package com.aurionpro.bankapp.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="transactions")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Transaction {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;
	
//	@Column
//    private long fromAccount;
	
//	@Column
//    private long toAccount;
	
	@Column
    private BigDecimal amount;
	
	@Column
    private String transactionType; // DEBIT or CREDIT
	
	@Column
    private String status;          // SUCCESS or FAILED
	
	@Column
    private Timestamp createdAt;
	
//	@Column
//    private int accountId;
	
//	@Column
//    private int customerId;
	
//	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.DETACH})
//	@JoinColumn(name="customer_id")
//	private Customer customers;
//	
//	
//	 @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.DETACH})
//	    @JoinColumn(name = "account_number")
//	    private Account accounts;
//	 
//	 @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.DETACH})
//	    @JoinColumn(name = "account_number")
//	    private Beneficiary benefeciaries;
//	 
//	 @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.DETACH})
//	    @JoinColumn(name = "account_id")
//	    private Account accountsid;
	

	@ManyToOne
	@JoinColumn(name = "source_account_id")
	private Account sourceAccount;

	@ManyToOne
	@JoinColumn(name = "destination_account_id")
	private Account destinationAccount;
	 

}
