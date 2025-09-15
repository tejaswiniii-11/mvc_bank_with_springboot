package com.aurionpro.bankapp.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

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
@Table(name="beneficiaries")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Beneficiary {

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long beneficiaryId;
	
//	@Column
//	private long customerId;
	
	@Column
	private String ifscCode;
	
	@Column
	private String branch;
	
	@Column
	private String accountNumber; // store as String
	
	@Column
	private String nickname;
	
	@Column
	private Timestamp createdAt;
	

	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
}
