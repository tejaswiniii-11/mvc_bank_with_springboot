package com.aurionpro.bankapp.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="admins")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Admin {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long adminId;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@Column
	private String fullName;

}
