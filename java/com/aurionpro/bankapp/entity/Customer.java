package com.aurionpro.bankapp.entity;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="customers")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Customer {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long customerId;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@Column
	private String fullName;
	
	@Column
	private String email;
	
	@Column
	private String phone;
	
	@Column
	private Timestamp createdAt;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Account> accounts;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Beneficiary> beneficiaries;
	
}
